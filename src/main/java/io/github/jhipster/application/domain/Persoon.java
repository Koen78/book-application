package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * Persoon entity.\n@author Koen.
 */
@Entity
@Table(name = "persoon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "persoon")
public class Persoon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "voornaam")
    private String voornaam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("persoons")
    private Boek wenslijst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("persoons")
    private Boek boekenlijst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("persoons")
    private Boek gelezen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public Persoon naam(String naam) {
        this.naam = naam;
        return this;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public Persoon voornaam(String voornaam) {
        this.voornaam = voornaam;
        return this;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Boek getWenslijst() {
        return wenslijst;
    }

    public Persoon wenslijst(Boek boek) {
        this.wenslijst = boek;
        return this;
    }

    public void setWenslijst(Boek boek) {
        this.wenslijst = boek;
    }

    public Boek getBoekenlijst() {
        return boekenlijst;
    }

    public Persoon boekenlijst(Boek boek) {
        this.boekenlijst = boek;
        return this;
    }

    public void setBoekenlijst(Boek boek) {
        this.boekenlijst = boek;
    }

    public Boek getGelezen() {
        return gelezen;
    }

    public Persoon gelezen(Boek boek) {
        this.gelezen = boek;
        return this;
    }

    public void setGelezen(Boek boek) {
        this.gelezen = boek;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persoon)) {
            return false;
        }
        return id != null && id.equals(((Persoon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Persoon{" +
            "id=" + getId() +
            ", naam='" + getNaam() + "'" +
            ", voornaam='" + getVoornaam() + "'" +
            "}";
    }
}
