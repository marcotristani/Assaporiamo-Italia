package project.italy.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.italy.backend.models.Regione;
import project.italy.backend.service.RegioneService;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/backoffice/regioni")
public class RegioneController {

    @Autowired
    RegioneService regioneService;

    @GetMapping("/{slugRegione}")
    public String show(@PathVariable("slugRegione") String slugRegione, Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        model.addAttribute("regione", regione);
        return "regione/show";
    }

    @GetMapping("/{slug}/edit")
    public String edit(@PathVariable("slug") String slugRegione,
            Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }

        Regione regione = regioneService.getRegioneBySlug(slugRegione);
        model.addAttribute("regione", regione);

        return "regione/edit";
    }

    @PostMapping("/{slug}/edit")
    public String modify(@PathVariable("slug") String slugRegione,
            @Valid @ModelAttribute("regione") Regione formRegioneModificata, BindingResult bindingResult, Model model) {
        Optional<Regione> optionalRegione = regioneService.findRegioneBySlug(slugRegione);
        if (optionalRegione.isEmpty()) {
            return "error/404";
        }

        if (bindingResult.hasErrors()) {
            System.out.println("LOG ERRORI: " + bindingResult.toString());
            return "regione/edit";
        }

        Regione regioneDaModificare = regioneService.getRegioneBySlug(slugRegione);
        Regione regioneModificata = regioneService.update(regioneDaModificare, formRegioneModificata);
        return "redirect:/backoffice/regioni/" + regioneModificata.getSlug();
    }

}
