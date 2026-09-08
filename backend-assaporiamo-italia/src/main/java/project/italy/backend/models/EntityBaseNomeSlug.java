package project.italy.backend.models;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class EntityBaseNomeSlug {

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @Column(nullable = false, unique = true)
    private String slug;

    @PrePersist
    @PreUpdate
    public void generateSlug() {
        if (this.nome == null || this.nome.trim().isEmpty()) {
            this.slug = "";
            return;
        }

        // 1. Rimuove gli accenti
        String nfdNormalizedString = Normalizer.normalize(this.nome, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String senzaAccenti = pattern.matcher(nfdNormalizedString).replaceAll("");

        // 2. Converte in minuscolo e rimuove caratteri speciali
        this.slug = senzaAccenti
                .toLowerCase(Locale.ITALIAN)
                .replaceAll("[^a-z0-9\\s-]", "") // Rimuove tutto ciò che non è alfanumerico o spazio
                .replaceAll("\\s+", "-") // Sostituisce gli spazi con un singolo trattino
                .replaceAll("-+", "-") // Evita trattini doppi consecutivi
                .replaceAll("^-", "") // Rimuove il trattino iniziale se presente
                .replaceAll("-$", ""); // Rimuove il trattino finale se presente
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
