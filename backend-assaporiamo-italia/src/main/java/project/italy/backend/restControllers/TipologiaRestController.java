package project.italy.backend.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Tipologia;
import project.italy.backend.service.TipologiaService;

@RestController
@CrossOrigin
@RequestMapping("/api/vini/tipologie")
public class TipologiaRestController {

    @Autowired
    TipologiaService tipologiaService;

    @GetMapping
    public List<Tipologia> index() {
        return tipologiaService.getTipologieOrdinate();
    }
}
