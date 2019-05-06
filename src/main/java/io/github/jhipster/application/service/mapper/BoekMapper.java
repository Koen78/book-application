package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.BoekDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Boek} and its DTO {@link BoekDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BoekMapper extends EntityMapper<BoekDTO, Boek> {



    default Boek fromId(Long id) {
        if (id == null) {
            return null;
        }
        Boek boek = new Boek();
        boek.setId(id);
        return boek;
    }
}
