package project.italy.backend.models;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vini")
public class Vino extends EntityBaseNomeSlug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "certificazione", length = 4, nullable = true)
    private String certificazione;

    @NotBlank(message = "Inserire una descrizione")
    @Lob
    private String descrizione;

    @URL(message = "inserire un URL valido")
    @NotBlank(message = "Inserire un url per l'immagine")
    private String urlImmagine;

    @URL(message = "inserire un URL valido")
    @NotBlank(message = "Inserire una link per lo store")
    private String linkStore;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "regione_id", nullable = false)
    private Regione regione;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tipologia_id", nullable = false)
    private Tipologia tipologia;

    @ManyToMany(mappedBy = "vini")
    @JsonIgnore
    private List<ProdottoTipico> prodottiTipici;

    // Espone nel JSON la proprietà "regione" come stringa con solo il nome
    @JsonGetter("regione")
    public String getRegioneNome() {
        return regione.getNome();
    }

    // Espone nel JSON la proprietà "categoria" como stringa con solo il nome
    @JsonGetter("categoria")
    public String getCategoriaNome() {
        return tipologia.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificazione() {
        return certificazione;
    }

    public void setCertificazione(String certificazione) {
        this.certificazione = certificazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public String getLinkStore() {
        return linkStore;
    }

    public void setLinkStore(String linkStore) {
        this.linkStore = linkStore;
    }

    public Regione getRegione() {
        return regione;
    }

    public void setRegione(Regione regione) {
        this.regione = regione;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public List<ProdottoTipico> getProdottiTipici() {
        return prodottiTipici;
    }

    public void setProdottiTipici(List<ProdottoTipico> prodottiTipici) {
        this.prodottiTipici = prodottiTipici;
    }
}
