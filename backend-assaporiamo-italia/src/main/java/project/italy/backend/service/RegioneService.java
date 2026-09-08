package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.Regione;
import project.italy.backend.repository.RegioneRepository;

@Service
public class RegioneService {

    @Autowired
    RegioneRepository regioneRepository;

    public List<Regione> getRegioniOrdinate() {
        return regioneRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Regione> findRegioneBySlug(String slugRegione) {
        Optional<Regione> optionalRegione = regioneRepository.findBySlug(slugRegione);

        return optionalRegione;
    }

    public Regione getRegioneBySlug(String slugRegione) {
        Optional<Regione> optionalRegione = regioneRepository.findBySlug(slugRegione);
        return optionalRegione.get();
    }

    public Regione update(Regione regioneDaModificare, Regione formregioneModificata) {
        regioneDaModificare.setDescrizione(formregioneModificata.getDescrizione());
        regioneDaModificare.setUrlImmagine(formregioneModificata.getUrlImmagine());

        return regioneRepository.save(regioneDaModificare);

    }
}
