package project.italy.backend.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.italy.backend.models.Categoria;
import project.italy.backend.service.CategoriaService;

@RestController
@CrossOrigin
@RequestMapping("/api/prodotti/categorie")
public class CategoriaRestController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> index() {
        return categoriaService.getCategorieOrdinate();
    }
}
