package project.italy.backend.restControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.models.Vino;
import project.italy.backend.service.ProdottoTipicoService;
import project.italy.backend.service.RegioneService;
import project.italy.backend.service.TipologiaService;
import project.italy.backend.service.VinoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/vini")
public class VinoRestController {

    @Autowired
    VinoService vinoService;

    @Autowired
    RegioneService regioneService;

    @Autowired
    TipologiaService tipologiaService;

    @Autowired
    ProdottoTipicoService prodottoTipicoService;

    @GetMapping("/all")
    public ResponseEntity<List<Vino>> index(@RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca) {
        List<Vino> vini = vinoService.findAllViniOrdinati(order, ricerca);

        return new ResponseEntity<List<Vino>>(vini, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}")
    public ResponseEntity<List<Vino>> indexRegione(@PathVariable("slugRegione") String slugRegione,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca) {

        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Vino> vini = vinoService.getViniPerRegione(slugRegione, order, ricerca);

        return new ResponseEntity<List<Vino>>(vini, HttpStatus.OK);
    }

    @GetMapping("/categoria/{slugTipologia}")
    public ResponseEntity<List<Vino>> indexTipologia(@PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order, @RequestParam(defaultValue = "") String ricerca) {

        Optional<Tipologia> optionalTipologia = tipologiaService.findTipologiaBySlug(slugTipologia);
        if (optionalTipologia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Vino> vini = vinoService.getViniPerTipologia(slugTipologia, order, ricerca);

        return new ResponseEntity<List<Vino>>(vini, HttpStatus.OK);
    }

    @GetMapping("/regione/{slugRegione}/categoria/{slugTipologia}")
    public ResponseEntity<List<Vino>> indexRegioneETipolgia(
            @PathVariable("slugRegione") String slugRegione,
            @PathVariable("slugTipologia") String slugTipologia,
            @RequestParam(defaultValue = "default") String order,
            @RequestParam(defaultValue = "") String ricerca) {

        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        Optional<Tipologia> optionalTipologia = tipologiaService.findTipologiaBySlug(slugTipologia);
        if (optionalTipologia.isEmpty() || optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Vino> vini = vinoService.getViniPerRegioneETipologia(slugRegione, slugTipologia, order, ricerca);

        return new ResponseEntity<List<Vino>>(vini, HttpStatus.OK);
    }

    @GetMapping("/prodotto/{slugProdotto}")
    public ResponseEntity<List<Vino>> indexProdotto(@PathVariable("slugProdotto") String slugProdotto,
            @RequestParam(defaultValue = "default") String order) {

        Optional<ProdottoTipico> optionalProdotto = prodottoTipicoService.findBySlug(slugProdotto);
        if (optionalProdotto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Vino> vini = vinoService.getViniPerProdotto(slugProdotto, order);

        return new ResponseEntity<List<Vino>>(vini, HttpStatus.OK);
    }

    @GetMapping("/{slugVino}")
    public ResponseEntity<Vino> show(@PathVariable("slugVino") String slugVino) {

        Optional<Vino> optionalVino = vinoService.findBySlug(slugVino);
        if (optionalVino.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Vino vino = vinoService.getBySlug(slugVino);
        return new ResponseEntity<Vino>(vino, HttpStatus.OK);
    }

}
