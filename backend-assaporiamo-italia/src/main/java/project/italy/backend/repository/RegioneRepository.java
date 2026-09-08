package project.italy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.italy.backend.models.Regione;

public interface RegioneRepository extends JpaRepository<Regione, Integer> {

    Optional<Regione> findBySlug(String slug);

    // PER PRENDERE LA LISTA IN ORDINE ALFABETICO
    List<Regione> findAllByOrderByNomeAsc();
}
