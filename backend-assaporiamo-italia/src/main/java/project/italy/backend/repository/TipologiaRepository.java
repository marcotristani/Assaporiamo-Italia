package project.italy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.italy.backend.models.Tipologia;

public interface TipologiaRepository extends JpaRepository<Tipologia, Integer> {

    List<Tipologia> findAllByOrderByNomeAsc();

    Optional<Tipologia> findBySlug(String slug);
}
