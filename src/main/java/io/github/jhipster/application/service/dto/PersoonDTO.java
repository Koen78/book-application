package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Persoon} entity.
 */
@ApiModel(description = "Persoon entity.\n@author Koen.")
public class PersoonDTO implements Serializable {

    private Long id;

    private String naam;

    private String voornaam;


    private Long wenslijstId;

    private Long boekenlijstId;

    private Long gelezenId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Long getWenslijstId() {
        return wenslijstId;
    }

    public void setWenslijstId(Long boekId) {
        this.wenslijstId = boekId;
    }

    public Long getBoekenlijstId() {
        return boekenlijstId;
    }

    public void setBoekenlijstId(Long boekId) {
        this.boekenlijstId = boekId;
    }

    public Long getGelezenId() {
        return gelezenId;
    }

    public void setGelezenId(Long boekId) {
        this.gelezenId = boekId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersoonDTO persoonDTO = (PersoonDTO) o;
        if (persoonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), persoonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersoonDTO{" +
            "id=" + getId() +
            ", naam='" + getNaam() + "'" +
            ", voornaam='" + getVoornaam() + "'" +
            ", wenslijst=" + getWenslijstId() +
            ", boekenlijst=" + getBoekenlijstId() +
            ", gelezen=" + getGelezenId() +
            "}";
    }
}
