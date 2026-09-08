package project.italy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.models.Vino;

public interface VinoRepository extends JpaRepository<Vino, Integer> {

    Optional<Vino> findBySlug(String slug);

    // prendo i vini collegati a una regione(e poi vado a indicare l'ordine di
    // visualizzazione)
    List<Vino> findByRegione(Regione regione);

    // prendo i vini collegati a una certa tipologia(e poi vado a indicare l'ordine
    // di visualizzazione)
    List<Vino> findByTipologia(Tipologia tipologia);

    // prendo i vini di una determinata regione e una determinatat tipologia(e poi
    // vado a indicare l'ordine di visualizzazione)
    List<Vino> findByRegioneAndTipologia(Regione regione, Tipologia tipologia);

    // prendo i vini correlati a un determinato prodotto
    List<Vino> findByProdottiTipiciSlug(String slugProdottoTipico);

    // RISCULTATI PER ORDINE ALFABETICO
    List<Vino> findAllByOrderByNomeAsc();

    List<Vino> findByRegioneOrderByNomeAsc(Regione regione);

    List<Vino> findByTipologiaOrderByNomeAsc(Tipologia tipologia);

    List<Vino> findByRegioneAndTipologiaOrderByNomeAsc(Regione regione, Tipologia tipologia);

    List<Vino> findByProdottiTipiciSlugOrderByNomeAsc(String slugProdottoTipico);

    List<Vino> findByNomeContainingIgnoreCase(String nome);

    List<Vino> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

    List<Vino> findByRegioneAndNomeContainingIgnoreCase(Regione regione, String nome);

    List<Vino> findByRegioneAndNomeContainingIgnoreCaseOrderByNomeAsc(Regione regione, String nome);

    List<Vino> findByTipologiaAndNomeContainingIgnoreCase(Tipologia tipologia, String nome);

    List<Vino> findByTipologiaAndNomeContainingIgnoreCaseOrderByNomeAsc(Tipologia tipologia, String nome);

    List<Vino> findByRegioneAndTipologiaAndNomeContainingIgnoreCase(
            Regione regione,
            Tipologia tipologia,
            String nome);

    List<Vino> findByRegioneAndTipologiaAndNomeContainingIgnoreCaseOrderByNomeAsc(
            Regione regione,
            Tipologia tipologia,
            String nome);

}
