package project.italy.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import project.italy.backend.models.Vino;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.service.ProdottoTipicoService;
import project.italy.backend.service.RegioneService;
import project.italy.backend.service.TipologiaService;
import project.italy.backend.service.VinoService;

@Controller
@RequestMapping("/backoffice/vini")
public class VinoController {

    @Autowired
    VinoService vinoService;

    @Autowired
    TipologiaService tipologiaService;

    @Autowired
    RegioneService regioneService;

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @GetMapping("/all")
    public String index(@RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca, Model model) {
        List<Vino> vini = vinoService.findAllViniOrdinati(order, ricerca);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", null);
        model.addAttribute("regioneSelezionata", null);
        return "vino/index";
    }

    @GetMapping("/tipologia/{slugTipologia}")
    public String indexTipologia(@PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Tipologia> optionalTipologia = tipologiaService.findTipologiaBySlug(slugTipologia);
        if (optionalTipologia.isEmpty()) {
            return "error/404";
        }
        List<Vino> vini = vinoService.getViniPerTipologia(slugTipologia, order, ricerca);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Tipologia tipologiaSelezionata = tipologiaService.getTipologiaBySlug(slugTipologia);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", tipologiaSelezionata);
        model.addAttribute("regioneSelezionata", null);

        return "vino/index";
    }

    @GetMapping("/regione/{slugRegione}")
    public String indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }
        List<Vino> vini = vinoService.getViniPerRegione(slugRegione, order, ricerca);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", null);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "vino/index";
    }

    @GetMapping("/tipologia/{slugTipologia}/regione/{slugRegione}")
    public String indexRegioneETipologia(@PathVariable("slugTipologia") String slugTipologia,
            @PathVariable("slugRegione") String slugRegione, @RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }
        Optional<Tipologia> optionalTipologia = tipologiaService.findTipologiaBySlug(slugTipologia);
        if (optionalTipologia.isEmpty()) {
            return "error/404";
        }
        List<Vino> vini = vinoService.getViniPerRegioneETipologia(slugRegione,
                slugTipologia, order, ricerca);
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        Tipologia tipologiaSelezionata = tipologiaService.getTipologiaBySlug(slugTipologia);
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("vini", vini);
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("tipologiaSelezionata", tipologiaSelezionata);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "vino/index";
    }

    @GetMapping("/dettaglio/{slug}")
    public String show(@PathVariable("slug") String slug, Model model) {
        Optional<Vino> optionalVino = vinoService.findBySlug(slug);
        if (optionalVino.isEmpty()) {
            return "error/404";
        }
        Vino vino = vinoService.getBySlug(slug);
        model.addAttribute("vino", vino);
        return "vino/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Vino nuovoVino = new Vino();
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("listaRegioni", listaRegioni);
        model.addAttribute("nuovoVino", nuovoVino);
        return "vino/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("nuovoVino") Vino formVino,
            BindingResult bindingResult, Model model) {
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("TEST ERRORE FORM: " + error.toString()));
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }

        Optional<Regione> optionalRegioneSelezionata = regioneService
                .findRegioneBySlug(formVino.getRegione().getSlug());
        if (optionalRegioneSelezionata.isEmpty()) {
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }
        Regione regioneSelezionata = optionalRegioneSelezionata.get();

        Optional<Tipologia> optionalTipologiaSelezionata = tipologiaService
                .findTipologiaBySlug(formVino.getTipologia().getSlug());
        if (optionalTipologiaSelezionata.isEmpty()) {
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }
        Tipologia tipologiaSelezionata = optionalTipologiaSelezionata.get();

        List<ProdottoTipico> prodottiTipiciSelezionati = new ArrayList<ProdottoTipico>();

        Vino nuovoVinoSalvato = vinoService.create(formVino, regioneSelezionata,
                tipologiaSelezionata, prodottiTipiciSelezionati);
        return "redirect:/backoffice/vini/dettaglio/" + nuovoVinoSalvato.getSlug();
    }

    @GetMapping("/dettaglio/{slug}/gestisci-prodotti")
    public String mostraGestioneVini(@PathVariable("slug") String slug, @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Vino> optionalVino = vinoService.findBySlug(slug);
        if (optionalVino.isEmpty()) {
            return "error/404";
        }
        Vino vino = vinoService.getBySlug(slug);

        List<ProdottoTipico> listaProdottiTipici = prodottoTipicoService.findAllProdottiOrdinati("alfabetico", ricerca);

        model.addAttribute("vino", vino);
        model.addAttribute("listaProdottiTipici", listaProdottiTipici);

        return "vino/gestisci-prodotti";
    }

    @PostMapping("/dettaglio/{slugVino}/associa-prodotto/{slugProdotto}")
    public String associaProdotto(
            @PathVariable("slugVino") String slugVino,
            @PathVariable("slugProdotto") String slugProdotto) {

        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);

        if (optionalVino.isEmpty()) {
            return "error/404";
        }

        Vino vino = vinoService.getBySlug(slugVino);
        Optional<ProdottoTipico> optionalProdottoTipico = prodottoTipicoService.findBySlug(slugProdotto);

        if (!optionalProdottoTipico.isEmpty()) {
            ProdottoTipico prodottoTipicoSelezionato = optionalProdottoTipico.get();
            if (!prodottoTipicoSelezionato.getVini().contains(vino)) {
                prodottoTipicoSelezionato.getVini().add(vino);
                prodottoTipicoService.save(prodottoTipicoSelezionato); // Aggiorna la tabella di giunzione
            }

        }

        return "redirect:/backoffice/vini/dettaglio/" + slugVino + "/gestisci-prodotti";
    }

    @PostMapping("/dettaglio/{slugVino}/scollega-prodotto/{slugProdotto}")
    public String scollegaProdotto(

            @PathVariable("slugVino") String slugVino, @PathVariable("slugProdotto") String slugProdotto) {

        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);

        if (optionalVino.isEmpty()) {
            return "error/404";
        }

        Vino vino = vinoService.getBySlug(slugVino);
        Optional<ProdottoTipico> optionalProdottoTipico = prodottoTipicoService.findBySlug(slugProdotto);

        if (!optionalProdottoTipico.isEmpty()) {
            ProdottoTipico prodottoTipicoSelezionato = optionalProdottoTipico.get();
            if (prodottoTipicoSelezionato.getVini().contains(vino)) {
                prodottoTipicoSelezionato.getVini().remove(vino);
                prodottoTipicoService.save(prodottoTipicoSelezionato); // Aggiorna la tabella di giunzione
            }

        }

        return "redirect:/backoffice/vini/dettaglio/" + slugVino + "/gestisci-prodotti";
    }

    @GetMapping("/{slug}/edit")
    public String modify(@PathVariable("slug") String slug, Model model) {
        Optional<Vino> optionalVino = vinoService.findBySlug(slug);
        if (optionalVino.isEmpty()) {
            return "error/404";
        }
        Vino vino = vinoService.getBySlug(slug);
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();
        model.addAttribute("listaTipologie", listaTipologie);
        model.addAttribute("listaRegioni", listaRegioni);
        model.addAttribute("nuovoVino", vino);
        model.addAttribute("edit", true);
        return "vino/create-or-edit";
    }

    @PostMapping("/{slug}/edit")
    public String modify(@PathVariable("slug") String slug,
            @Valid @ModelAttribute("nuovoVino") Vino formVino,
            BindingResult bindingResult, Model model) {
        Optional<Vino> optionalVino = vinoService.findBySlug(slug);
        if (optionalVino.isEmpty()) {
            return "error/404";
        }
        Vino vinoDaModificare = vinoService.getBySlug(slug);
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Tipologia> listaTipologie = tipologiaService.getTipologieOrdinate();

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }

        Optional<Regione> optionalRegioneSelezionata = regioneService
                .findRegioneBySlug(formVino.getRegione().getSlug());
        if (optionalRegioneSelezionata.isEmpty()) {
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }
        Regione regioneSelezionata = optionalRegioneSelezionata.get();

        Optional<Tipologia> optionalTipologia = tipologiaService
                .findTipologiaBySlug(formVino.getTipologia().getSlug());
        if (optionalTipologia.isEmpty()) {
            model.addAttribute("listaTipologie", listaTipologie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "vino/create-or-edit";
        }
        Tipologia tipologiaSelezionata = optionalTipologia.get();

        Vino vinoModificato = vinoService.update(vinoDaModificare, formVino,
                regioneSelezionata,
                tipologiaSelezionata);

        return "redirect:/backoffice/vini/dettaglio/" + vinoModificato.getSlug();
    }

    @PostMapping("/{slug}/delete")
    public String delete(@PathVariable("slug") String slug, Model model) {
        Optional<Vino> optionalVino = vinoService.findBySlug(slug);
        if (optionalVino.isEmpty()) {
            return "error/404";
        }

        Vino vinoDaEliminare = vinoService.getBySlug(slug);

        vinoService.delete(vinoDaEliminare);
        return "redirect:/backoffice/vini/all";
    }

}
