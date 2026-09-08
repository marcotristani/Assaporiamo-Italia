package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.ProdottoTipico;
import project.italy.backend.models.Regione;
import project.italy.backend.models.Tipologia;
import project.italy.backend.models.Vino;
import project.italy.backend.repository.VinoRepository;

@Service
public class VinoService {

    @Autowired
    VinoRepository vinoRepository;

    @Autowired
    RegioneService regioneService;

    @Autowired
    TipologiaService tipologiaService;

    public Optional<Vino> findBySlug(String slugVino) {
        return vinoRepository.findBySlug(slugVino);
    }

    public Vino getBySlug(String slugVino) {
        return vinoRepository.findBySlug(slugVino).get();
    }

    public List<Vino> findAllViniOrdinati(String order, String ricerca) {
        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return vinoRepository.findAllByOrderByNomeAsc();
        }
        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(ricerca);
        }
        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByNomeContainingIgnoreCase(ricerca);
        }
        return vinoRepository.findAll();
    }

    public List<Vino> getViniPerRegione(String slugRegione, String order, String ricerca) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);
        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return vinoRepository.findByRegioneOrderByNomeAsc(regione);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByRegioneAndNomeContainingIgnoreCaseOrderByNomeAsc(regione, ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByRegioneAndNomeContainingIgnoreCase(regione, ricerca);
        }

        return vinoRepository.findByRegione(regione);
    }

    public List<Vino> getViniPerTipologia(String slugTipologia, String order, String ricerca) {

        Tipologia tipologia = tipologiaService.getTipologiaBySlug(slugTipologia);

        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return vinoRepository.findByTipologiaOrderByNomeAsc(tipologia);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByTipologiaAndNomeContainingIgnoreCaseOrderByNomeAsc(tipologia,
                    ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByTipologiaAndNomeContainingIgnoreCase(tipologia, ricerca);
        }

        return vinoRepository.findByTipologia(tipologia);
    }

    public List<Vino> getViniPerRegioneETipologia(String slugRegione, String slugTipologia,
            String order, String ricerca) {

        Regione regione = regioneService.getRegioneBySlug(slugRegione);

        Tipologia tipologia = tipologiaService.getTipologiaBySlug(slugTipologia);

        if (order.equalsIgnoreCase("alfabetico") && ricerca.isBlank()) {
            return vinoRepository.findByRegioneAndTipologiaOrderByNomeAsc(regione, tipologia);
        }

        if (order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByRegioneAndTipologiaAndNomeContainingIgnoreCaseOrderByNomeAsc(regione,
                    tipologia,
                    ricerca);
        }

        if (!order.equalsIgnoreCase("alfabetico") && !ricerca.isBlank()) {
            return vinoRepository.findByRegioneAndTipologiaAndNomeContainingIgnoreCase(regione, tipologia,
                    ricerca);
        }

        return vinoRepository.findByRegioneAndTipologia(regione, tipologia);
    }

    public List<Vino> getViniPerProdotto(String slugProdotto,
            String order) {

        if (order.equalsIgnoreCase("alfabetico")) {
            return vinoRepository.findByProdottiTipiciSlugOrderByNomeAsc(slugProdotto);
        }

        return vinoRepository.findByProdottiTipiciSlug(slugProdotto);
    }

    public Vino create(Vino nuovoVino, Regione regioneSelezionata,
            Tipologia tipologiaSelezionata, List<ProdottoTipico> prodottiSelezionati) {
        nuovoVino.setRegione(regioneSelezionata);
        nuovoVino.setTipologia(tipologiaSelezionata);
        nuovoVino.setProdottiTipici(prodottiSelezionati);
        return vinoRepository.save(nuovoVino);
    }

    public Vino save(Vino vino) {
        return vinoRepository.save(vino);
    }

    public Vino update(Vino vinoDaModificare, Vino formVino, Regione regione,
            Tipologia tipologia) {

        vinoDaModificare.setNome(formVino.getNome());
        vinoDaModificare.setDescrizione(formVino.getDescrizione());
        vinoDaModificare.setLinkStore(formVino.getLinkStore());
        vinoDaModificare.setUrlImmagine(formVino.getUrlImmagine());
        vinoDaModificare.setCertificazione(formVino.getCertificazione());
        vinoDaModificare.setRegione(regione);
        vinoDaModificare.setTipologia(tipologia);

        return vinoRepository.save(vinoDaModificare);
    }

    public void delete(Vino vino) {
        List<ProdottoTipico> prodottiAssociati = vino.getProdottiTipici();
        for (ProdottoTipico prodottoTipico : prodottiAssociati) {
            if (prodottoTipico.getVini() != null) {
                prodottoTipico.getVini().remove(vino);
            }
        }

        vinoRepository.delete(vino);
    }
}
