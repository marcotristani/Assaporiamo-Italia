package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.Categoria;
import project.italy.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorieOrdinate() {
        return categoriaRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Categoria> findCategoriaBySlug(String slugRegione) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findBySlug(slugRegione);

        return optionalCategoria;
    }

    public Categoria getCategoriaBySlug(String slugRegione) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findBySlug(slugRegione);
        return optionalCategoria.get();
    }

}
