package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.Categoria;
import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Vino;
import project.italy.backend.repository.ProdottoTipicoRepository;

@Service
public class ProdottoTipicoService {

    @Autowired
    ProdottoTipicoRepository prodottoTipicoRepository;

    @Autowired
    RegioneService regioneService;

    @Autowired
    CategoriaService categoriaService;

    public Optional<ProdottoTipico> findBySlug(String slugProdotto) {
        return prodottoTipicoRepository.findBySlug(slugProdotto);
    }

    public ProdottoTipico getBySlug(String slugProdotto) {
        return prodottoTipicoRepository.findBySlug(slugProdotto).get();
    }

    public List<ProdottoTipico> findAllProdottiOrdinati(String order, String ricerca) {
        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return prodottoTipicoRepository.findAllByOrderByNomeAsc();
        }
        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(ricerca);
        }
        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByNomeContainingIgnoreCase(ricerca);
        }
        return prodottoTipicoRepository.findAll();
    }

    public List<ProdottoTipico> getProdottiPerRegione(String slugRegione, String order, String ricerca) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneOrderByNomeAsc(regione);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneAndNomeContainingIgnoreCaseOrderByNomeAsc(regione, ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneAndNomeContainingIgnoreCase(regione, ricerca);
        }

        return prodottoTipicoRepository.findByRegione(regione);
    }

    public List<ProdottoTipico> getProdottiPerCategoria(String slugCategoria, String order, String ricerca) {

        Categoria categoria = categoriaService.getCategoriaBySlug(slugCategoria);

        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return prodottoTipicoRepository.findByCategoriaOrderByNomeAsc(categoria);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByCategoriaAndNomeContainingIgnoreCaseOrderByNomeAsc(categoria,
                    ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByCategoriaAndNomeContainingIgnoreCase(categoria, ricerca);
        }

        return prodottoTipicoRepository.findByCategoria(categoria);
    }

    public List<ProdottoTipico> getProdottiPerRegioneECategoria(String slugRegione, String slugCategoria,
            String order, String ricerca) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        Categoria categoria = categoriaService.getCategoriaBySlug(slugCategoria);

        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneAndCategoriaOrderByNomeAsc(regione, categoria);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneAndCategoriaAndNomeContainingIgnoreCaseOrderByNomeAsc(regione,
                    categoria,
                    ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return prodottoTipicoRepository.findByRegioneAndCategoriaAndNomeContainingIgnoreCase(regione, categoria,
                    ricerca);
        }

        return prodottoTipicoRepository.findByRegioneAndCategoria(regione,
                categoria);
    }

    public List<ProdottoTipico> getProdottiPerVino(String slugVino,
            String order) {

        if (order.equalsIgnoreCase("alfabetico")) {
            return prodottoTipicoRepository.findByViniSlugOrderByNomeAsc(slugVino);
        }

        return prodottoTipicoRepository.findByViniSlug(slugVino);
    }

    public ProdottoTipico create(ProdottoTipico nuovoProdottoTipico, Regione regioneSelezionata,
            Categoria categoriaSelezionata, List<Vino> viniSelezionati) {
        nuovoProdottoTipico.setRegione(regioneSelezionata);
        nuovoProdottoTipico.setCategoria(categoriaSelezionata);
        nuovoProdottoTipico.setVini(viniSelezionati);
        return prodottoTipicoRepository.save(nuovoProdottoTipico);
    }

    public ProdottoTipico save(ProdottoTipico prodottoTipico) {
        return prodottoTipicoRepository.save(prodottoTipico);
    }

    public ProdottoTipico update(ProdottoTipico prodottoDaModificare, ProdottoTipico formProdotto, Regione regione,
            Categoria categoria) {

        prodottoDaModificare.setNome(formProdotto.getNome());
        prodottoDaModificare.setDescrizione(formProdotto.getDescrizione());
        prodottoDaModificare.setLinkStore(formProdotto.getLinkStore());
        prodottoDaModificare.setUrlImmagine(formProdotto.getUrlImmagine());

        prodottoDaModificare.setRegione(regione);
        prodottoDaModificare.setCategoria(categoria);

        return prodottoTipicoRepository.save(prodottoDaModificare);
    }

    public void delete(ProdottoTipico prodottoTipico) {
        List<Vino> viniAssociati = prodottoTipico.getVini();
        for (Vino vino : viniAssociati) {
            if (vino.getProdottiTipici() != null) {
                vino.getProdottiTipici().remove(prodottoTipico);
            }
        }

        prodottoTipicoRepository.delete(prodottoTipico);
    }
}
