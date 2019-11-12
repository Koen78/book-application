package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * Boek entity.\n@author Koen.
 */
@Entity
@Table(name = "boek")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "boek")
public class Boek implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "paginas")
    private Integer paginas;

    @Column(name = "korte_inhoud")
    private String korteInhoud;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public Boek titel(String titel) {
        this.titel = titel;
        return this;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public Boek auteur(String auteur) {
        this.auteur = auteur;
        return this;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public Boek paginas(Integer paginas) {
        this.paginas = paginas;
        return this;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public String getKorteInhoud() {
        return korteInhoud;
    }

    public Boek korteInhoud(String korteInhoud) {
        this.korteInhoud = korteInhoud;
        return this;
    }

    public void setKorteInhoud(String korteInhoud) {
        this.korteInhoud = korteInhoud;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boek)) {
            return false;
        }
        return id != null && id.equals(((Boek) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Boek{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", auteur='" + getAuteur() + "'" +
            ", paginas=" + getPaginas() +
            ", korteInhoud='" + getKorteInhoud() + "'" +
            "}";
    }
}
