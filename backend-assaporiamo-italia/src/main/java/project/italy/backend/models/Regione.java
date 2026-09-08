package project.italy.backend.models;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "regioni")
public class Regione extends EntityBaseNomeSlug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // nome e slug li prendo direttamente dalla classe padre

    @Lob
    @NotBlank(message = "La descrizione non può essere vuota")
    private String descrizione;

    @URL(message = "Inserire un url valido")
    @NotBlank(message = "L'url non può essere vuoto")
    private String urlImmagine;

    @NotBlank
    @Lob
    private String svg_coordinate;

    public String getSvg_coordinate() {
        return svg_coordinate;
    }

    public void setSvg_coordinate(String svg_coordinate) {
        this.svg_coordinate = svg_coordinate;
    }

    @OneToMany(mappedBy = "regione")
    @JsonIgnore
    private List<ProdottoTipico> prodottiTipici;

    @OneToMany(mappedBy = "regione")
    @JsonIgnore
    private List<Vino> vini;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ProdottoTipico> getProdottiTipici() {
        return prodottiTipici;
    }

    public void setProdottiTipici(List<ProdottoTipico> prodottiTipici) {
        this.prodottiTipici = prodottiTipici;
    }

    public List<Vino> getVini() {
        return vini;
    }

    public void setVini(List<Vino> vini) {
        this.vini = vini;
    }
}
