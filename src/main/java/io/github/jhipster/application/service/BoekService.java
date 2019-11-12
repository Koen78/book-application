package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.BoekDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Boek}.
 */
public interface BoekService {

    /**
     * Save a boek.
     *
     * @param boekDTO the entity to save.
     * @return the persisted entity.
     */
    BoekDTO save(BoekDTO boekDTO);

    /**
     * Get all the boeks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoekDTO> findAll(Pageable pageable);


    /**
     * Get the "id" boek.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BoekDTO> findOne(Long id);

    /**
     * Delete the "id" boek.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the boek corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoekDTO> search(String query, Pageable pageable);
}
