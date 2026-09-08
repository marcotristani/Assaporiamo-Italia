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
import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Vino;
import project.italy.backend.service.CategoriaService;
import project.italy.backend.service.ProdottoTipicoService;
import project.italy.backend.service.RegioneService;
import project.italy.backend.service.VinoService;

@Controller
@RequestMapping("/backoffice/prodotti")
public class ProdottoTipicoController {

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    RegioneService regioneService;

    @Autowired
    VinoService vinoService;

    @GetMapping("/all")
    public String index(@RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca, Model model) {
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.findAllProdottiOrdinati(order, ricerca);
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", null);
        model.addAttribute("regioneSelezionata", null);
        model.addAttribute("edit", false);
        return "prodottoTipico/index";
    }

    @GetMapping("/categoria/{slugCategoria}")
    public String indexCategoria(@PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty()) {
            return "error/404";
        }
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerCategoria(slugCategoria, order,
                ricerca);
        Categoria categoriaSelezionata = categoriaService.getCategoriaBySlug(slugCategoria);
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", categoriaSelezionata);
        model.addAttribute("regioneSelezionata", null);

        return "prodottoTipico/index";
    }

    @GetMapping("/regione/{slugRegione}")
    public String indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca, Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegione(slugRegione, order, ricerca);
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", null);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "prodottoTipico/index";
    }

    @GetMapping("/categoria/{slugCategoria}/regione/{slugRegione}")
    public String indexRegioneECategoria(@PathVariable("slugCategoria") String slugCategoria,
            @PathVariable("slugRegione") String slugRegione, @RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }
        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty()) {
            return "error/404";
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegioneECategoria(slugRegione,
                slugCategoria, order, ricerca);
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        Categoria categoriaSelezionata = categoriaService.getCategoriaBySlug(slugCategoria);
        Regione regioneSelezionata = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("prodottiTipici", prodottiTipici);
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("categoriaSelezionata", categoriaSelezionata);
        model.addAttribute("regioneSelezionata", regioneSelezionata);

        return "prodottoTipico/index";
    }

    @GetMapping("/dettaglio/{slug}")
    public String show(@PathVariable("slug") String slug, Model model) {
        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slug);
        if (optionalProdotto.isEmpty()) {
            return "error/404";
        }
        ProdottoTipico prodottoTipico = prodottoTipicoService.getBySlug(slug);
        model.addAttribute("prodottoTipico", prodottoTipico);
        return "prodottoTipico/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        ProdottoTipico nuovoProdottoTipico = new ProdottoTipico();
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("listaRegioni", listaRegioni);
        model.addAttribute("nuovoProdottoTipico", nuovoProdottoTipico);
        return "prodottoTipico/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("nuovoProdottoTipico") ProdottoTipico formProdottoTipico,
            BindingResult bindingResult, Model model) {
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("TEST ERRORE FORM: " + error.toString()));
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }

        Optional<Regione> optionalRegioneSelezionata = regioneService
                .findRegioneBySlug(formProdottoTipico.getRegione().getSlug());
        if (optionalRegioneSelezionata.isEmpty()) {
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }
        Regione regioneSelezionata = optionalRegioneSelezionata.get();

        Optional<Categoria> optionalCategoriaSelezionata = categoriaService
                .findCategoriaBySlug(formProdottoTipico.getCategoria().getSlug());
        if (optionalCategoriaSelezionata.isEmpty()) {
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }
        Categoria categoriaSelezionata = optionalCategoriaSelezionata.get();

        List<Vino> viniSelezionati = new ArrayList<>();

        ProdottoTipico nuovoProdottoSalvato = prodottoTipicoService.create(formProdottoTipico, regioneSelezionata,
                categoriaSelezionata, viniSelezionati);
        return "redirect:/backoffice/prodotti/dettaglio/" + nuovoProdottoSalvato.getSlug();
    }

    @GetMapping("/dettaglio/{slug}/gestisci-vini")
    public String mostraGestioneVini(@PathVariable("slug") String slug, @RequestParam(defaultValue = "") String ricerca,
            Model model) {
        Optional<ProdottoTipico> optionalProdottoTipico = prodottoTipicoService.findBySlug(slug);
        if (optionalProdottoTipico.isEmpty()) {
            return "error/404";
        }
        ProdottoTipico prodotto = prodottoTipicoService.getBySlug(slug);

        List<Vino> listaVini = vinoService.findAllViniOrdinati("alfabetico", ricerca);

        model.addAttribute("prodottoTipico", prodotto);
        model.addAttribute("listaVini", listaVini);

        return "prodottoTipico/gestisci-vini";
    }

    @PostMapping("/dettaglio/{slugProdotto}/associa-vino/{slugVino}")
    public String associaVino(
            @PathVariable("slugProdotto") String slugProdotto,
            @PathVariable("slugVino") String slugVino) {

        Optional<ProdottoTipico> optionalProdottoTipico = prodottoTipicoService.findBySlug(slugProdotto);
        if (optionalProdottoTipico.isEmpty()) {
            return "error/404";
        }

        ProdottoTipico prodottoTipico = prodottoTipicoService.getBySlug(slugProdotto);
        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);

        if (!optionalVino.isEmpty()) {
            Vino vinoselezionato = optionalVino.get();
            if (!prodottoTipico.getVini().contains(vinoselezionato)) {
                prodottoTipico.getVini().add(vinoselezionato);
                prodottoTipicoService.save(prodottoTipico);
            }
        }

        return "redirect:/backoffice/prodotti/dettaglio/" + slugProdotto + "/gestisci-vini";
    }

    @PostMapping("/dettaglio/{slugProdotto}/scollega-vino/{slugVino}")
    public String scollegaVino(
            @PathVariable("slugProdotto") String slugProdotto,
            @PathVariable("slugVino") String slugVino) {
        Optional<ProdottoTipico> optionalProdottoTipico = prodottoTipicoService.findBySlug(slugProdotto);
        if (optionalProdottoTipico.isEmpty()) {
            return "error/404";
        }

        ProdottoTipico prodottoTipico = prodottoTipicoService.getBySlug(slugProdotto);
        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);

        if (!optionalVino.isEmpty()) {
            Vino vinoDeselezionato = optionalVino.get();
            if (prodottoTipico.getVini().contains(vinoDeselezionato)) {
                prodottoTipico.getVini().remove(vinoDeselezionato);
                prodottoTipicoService.save(prodottoTipico); // Aggiorna la tabella di giunzione
            }
        }

        return "redirect:/backoffice/prodotti/dettaglio/" + slugProdotto + "/gestisci-vini";
    }

    @GetMapping("/{slug}/edit")
    public String edit(@PathVariable("slug") String slug, Model model) {
        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slug);
        if (optionalProdotto.isEmpty()) {
            return "error/404";
        }
        ProdottoTipico prodottoTipico = prodottoTipicoService.getBySlug(slug);
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();
        model.addAttribute("listaCategorie", listaCategorie);
        model.addAttribute("listaRegioni", listaRegioni);
        model.addAttribute("nuovoProdottoTipico", prodottoTipico);
        model.addAttribute("edit", true);
        return "prodottoTipico/create-or-edit";
    }

    @PostMapping("/{slug}/edit")
    public String modify(@PathVariable("slug") String slug,
            @Valid @ModelAttribute("nuovoProdottoTipico") ProdottoTipico formProdottoTipico,
            BindingResult bindingResult, Model model) {
        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slug);
        if (optionalProdotto.isEmpty()) {
            return "error/404";
        }
        ProdottoTipico prodottoDaModificare = prodottoTipicoService.getBySlug(slug);
        List<Regione> listaRegioni = regioneService.getRegioniOrdinate();
        List<Categoria> listaCategorie = categoriaService.getCategorieOrdinate();

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }

        Optional<Regione> optionalRegioneSelezionata = regioneService
                .findRegioneBySlug(formProdottoTipico.getRegione().getSlug());
        if (optionalRegioneSelezionata.isEmpty()) {
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }
        Regione regioneSelezionata = optionalRegioneSelezionata.get();

        Optional<Categoria> optionalCategoriaSelezionata = categoriaService
                .findCategoriaBySlug(formProdottoTipico.getCategoria().getSlug());
        if (optionalCategoriaSelezionata.isEmpty()) {
            model.addAttribute("listaCategorie", listaCategorie);
            model.addAttribute("listaRegioni", listaRegioni);

            return "prodottoTipico/create-or-edit";
        }
        Categoria categoriaSelezionata = optionalCategoriaSelezionata.get();

        ProdottoTipico prodottoModificato = prodottoTipicoService.update(prodottoDaModificare, formProdottoTipico,
                regioneSelezionata,
                categoriaSelezionata);

        return "redirect:/backoffice/prodotti/dettaglio/" + prodottoModificato.getSlug();
    }

    @PostMapping("/{slug}/delete")
    public String delete(@PathVariable("slug") String slug, Model model) {
        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slug);
        if (optionalProdotto.isEmpty()) {
            return "error/404";
        }

        ProdottoTipico prodottoDaEliminare = prodottoTipicoService.getBySlug(slug);

        prodottoTipicoService.delete(prodottoDaEliminare);
        return "redirect:/backoffice/prodotti/all";
    }

}
