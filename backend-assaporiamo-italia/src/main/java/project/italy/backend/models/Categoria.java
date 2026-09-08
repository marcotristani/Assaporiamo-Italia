package project.italy.backend.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categoria extends EntityBaseNomeSlug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<ProdottoTipico> prodottiTipici;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProdottoTipico> getProdottiTipici() {
        return prodottiTipici;
    }

    public void setProdottiTipici(List<ProdottoTipico> prodottiTipici) {
        this.prodottiTipici = prodottiTipici;
    }
}
