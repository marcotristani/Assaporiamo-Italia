
package project.italy.backend.restControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Vino;
import project.italy.backend.service.CategoriaService;
import project.italy.backend.service.ProdottoTipicoService;
import project.italy.backend.service.RegioneService;
import project.italy.backend.service.VinoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/prodotti")
public class ProdottoTipicoRestController {

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @Autowired
    RegioneService regioneService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    VinoService vinoService;

    @GetMapping("/all")
    public ResponseEntity<List<ProdottoTipico>> index(@RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca) {
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.findAllProdottiOrdinati(order, ricerca);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}")
    public ResponseEntity<List<ProdottoTipico>> indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);

        if (optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegione(slugRegione, order, ricerca);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/categoria/{slugCategoria}")
    public ResponseEntity<List<ProdottoTipico>> indexCategoria(@PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca) {

        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerCategoria(slugCategoria, order,
                ricerca);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}/categoria/{slugCategoria}")
    public ResponseEntity<List<ProdottoTipico>> indexRegioneECategoria(
            @PathVariable("slugRegione") String slugRegione,
            @PathVariable("slugCategoria") String slugCategoria,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca) {

        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaBySlug(slugCategoria);
        if (optionalCategoria.isEmpty() || optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerRegioneECategoria(slugRegione,
                slugCategoria, order, ricerca);
        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/vino/{slugVino}")
    public ResponseEntity<List<ProdottoTipico>> indexProdotto(@PathVariable("slugVino") String slugVino,
            @RequestParam(defaultValue = "default") String order) {

        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);
        if (optionalVino.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProdottoTipico> prodottiTipici = prodottoTipicoService.getProdottiPerVino(slugVino, order);

        return new ResponseEntity<List<ProdottoTipico>>(prodottiTipici, HttpStatus.OK);
    }

    @GetMapping("/{slugProdotto}")
    public ResponseEntity<ProdottoTipico> show(@PathVariable("slugProdotto") String slugProdotto) {

        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slugProdotto);
        if (optionalProdotto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProdottoTipico prodottoTipico = prodottoTipicoService.getBySlug(slugProdotto);
        return new ResponseEntity<ProdottoTipico>(prodottoTipico, HttpStatus.OK);
    }
}
