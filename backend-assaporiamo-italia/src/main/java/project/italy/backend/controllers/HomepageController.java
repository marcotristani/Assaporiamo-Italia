package project.italy.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.italy.backend.models.Regione;
import project.italy.backend.service.RegioneService;

@Controller
@RequestMapping
public class HomepageController {

    @Autowired
    RegioneService regioneService;

    @GetMapping
    public String home() {
        return "redirect:/backoffice/homepage";
    }

    @GetMapping("/backoffice/homepage")
    public String index(Model model) {
        List<Regione> regioni = regioneService.getRegioniOrdinate();
        model.addAttribute("regioni", regioni);
        return "regione/index";
    }
}
