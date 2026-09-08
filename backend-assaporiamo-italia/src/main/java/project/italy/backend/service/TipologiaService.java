package project.italy.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.italy.backend.models.Tipologia;
import project.italy.backend.repository.TipologiaRepository;

@Service
public class TipologiaService {

    @Autowired
    TipologiaRepository tipologiaRepository;

    public List<Tipologia> getTipologieOrdinate() {
        return tipologiaRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Tipologia> findTipologiaBySlug(String slugRegione) {
        Optional<Tipologia> optionalTipologia = tipologiaRepository.findBySlug(slugRegione);

        return optionalTipologia;
    }

    public Tipologia getTipologiaBySlug(String slugRegione) {
        Optional<Tipologia> optionalTipologia = tipologiaRepository.findBySlug(slugRegione);
        return optionalTipologia.get();
    }
}
