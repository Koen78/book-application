package io.github.jhipster.application.service.dto;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Boek} entity.
 */
@ApiModel(description = "Boek entity.\n@author Koen.")
public class BoekDTO implements Serializable {

    private Long id;

    private String titel;

    private String auteur;

    private Integer paginas;

    private String korteInhoud;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public String getKorteInhoud() {
        return korteInhoud;
    }

    public void setKorteInhoud(String korteInhoud) {
        this.korteInhoud = korteInhoud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BoekDTO boekDTO = (BoekDTO) o;
        if (boekDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), boekDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BoekDTO{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", auteur='" + getAuteur() + "'" +
            ", paginas=" + getPaginas() +
            ", korteInhoud='" + getKorteInhoud() + "'" +
            "}";
    }
}
