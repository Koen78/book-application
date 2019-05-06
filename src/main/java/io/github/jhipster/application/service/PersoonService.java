package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.PersoonDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Persoon}.
 */
public interface PersoonService {

    /**
     * Save a persoon.
     *
     * @param persoonDTO the entity to save.
     * @return the persisted entity.
     */
    PersoonDTO save(PersoonDTO persoonDTO);

    /**
     * Get all the persoons.
     *
     * @return the list of entities.
     */
    List<PersoonDTO> findAll();


    /**
     * Get the "id" persoon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersoonDTO> findOne(Long id);

    /**
     * Delete the "id" persoon.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the persoon corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<PersoonDTO> search(String query);
}
