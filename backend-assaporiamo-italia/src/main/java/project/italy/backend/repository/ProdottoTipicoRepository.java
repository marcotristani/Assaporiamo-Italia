package project.italy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;

import java.util.List;
import java.util.Optional;

public interface ProdottoTipicoRepository extends JpaRepository<ProdottoTipico, Integer> {

    // DEFINISCO TUTTI I METODI PER RECUPERARE I PRODOTTI ATTRAVERSO DEI FILTRI DI
    // RICERCA
    // recupero tutti i prodotti tramite lo slug che mi faccio passare come
    // pathvariable
    Optional<ProdottoTipico> findBySlug(String slug);

    // Dalla regione voglio recuperare tutti i prodotti collegato ad essa(e poi
    // indico l'ordinamento)
    List<ProdottoTipico> findByRegione(Regione regione);

    // Da una categoria specifica prendo i prodotti che gli appartengono
    List<ProdottoTipico> findByCategoria(Categoria categoria);

    // filtro la lista dei prodotti per regione e categoria(e poi indico
    // l'ordinamento)
    List<ProdottoTipico> findByRegioneAndCategoria(Regione regione, Categoria categoria);

    // Prendo la lista dei prodotti collegati a un vino specifico
    List<ProdottoTipico> findByViniSlug(String slugVino);

    // A QUESTI FILTRI VADO AD AGGIUNGERE LA CONDIZIONE DI ORDINAMENTO IN ORDINE
    // ALFABETICO
    List<ProdottoTipico> findAllByOrderByNomeAsc();

    List<ProdottoTipico> findByRegioneOrderByNomeAsc(Regione regione);

    List<ProdottoTipico> findByCategoriaOrderByNomeAsc(Categoria categoria);

    List<ProdottoTipico> findByRegioneAndCategoriaOrderByNomeAsc(Regione regione, Categoria categoria);

    List<ProdottoTipico> findByViniSlugOrderByNomeAsc(String slugVino);

    List<ProdottoTipico> findByNomeContainingIgnoreCase(String nome);

    List<ProdottoTipico> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

    List<ProdottoTipico> findByRegioneAndNomeContainingIgnoreCase(Regione regione, String nome);

    List<ProdottoTipico> findByRegioneAndNomeContainingIgnoreCaseOrderByNomeAsc(Regione regione, String nome);

    List<ProdottoTipico> findByCategoriaAndNomeContainingIgnoreCase(Categoria categoria, String nome);

    List<ProdottoTipico> findByCategoriaAndNomeContainingIgnoreCaseOrderByNomeAsc(Categoria categoria, String nome);

    List<ProdottoTipico> findByRegioneAndCategoriaAndNomeContainingIgnoreCase(
            Regione regione,
            Categoria categoria,
            String nome);

    List<ProdottoTipico> findByRegioneAndCategoriaAndNomeContainingIgnoreCaseOrderByNomeAsc(
            Regione regione,
            Categoria categoria,
            String nome);
}
