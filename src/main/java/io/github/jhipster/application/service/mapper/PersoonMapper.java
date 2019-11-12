package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PersoonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Persoon} and its DTO {@link PersoonDTO}.
 */
@Mapper(componentModel = "spring", uses = {BoekMapper.class})
public interface PersoonMapper extends EntityMapper<PersoonDTO, Persoon> {

    @Mapping(source = "wenslijst.id", target = "wenslijstId")
    @Mapping(source = "boekenlijst.id", target = "boekenlijstId")
    @Mapping(source = "gelezen.id", target = "gelezenId")
    PersoonDTO toDto(Persoon persoon);

    @Mapping(source = "wenslijstId", target = "wenslijst")
    @Mapping(source = "boekenlijstId", target = "boekenlijst")
    @Mapping(source = "gelezenId", target = "gelezen")
    Persoon toEntity(PersoonDTO persoonDTO);

    default Persoon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Persoon persoon = new Persoon();
        persoon.setId(id);
        return persoon;
    }
}
