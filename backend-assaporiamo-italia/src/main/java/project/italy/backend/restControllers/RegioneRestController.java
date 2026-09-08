package project.italy.backend.restControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Regione;
import project.italy.backend.service.RegioneService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/regioni")
public class RegioneRestController {

    @Autowired
    RegioneService regioneService;

    @GetMapping
    public List<Regione> index() {
        return regioneService.getRegioniOrdinate();
    }

    @GetMapping("/{slugRegione}")
    public ResponseEntity<Regione> show(@PathVariable("slugRegione") String slugRegione) {

        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);

        if (optionalRegione.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Regione regione = regioneService.getRegioneBySlug(slugRegione);
        return new ResponseEntity<Regione>(regione, HttpStatus.OK);

    }

}
