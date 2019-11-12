package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PersoonService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PersoonDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Persoon}.
 */
@RestController
@RequestMapping("/api")
public class PersoonResource {

    private final Logger log = LoggerFactory.getLogger(PersoonResource.class);

    private static final String ENTITY_NAME = "persoon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersoonService persoonService;

    public PersoonResource(PersoonService persoonService) {
        this.persoonService = persoonService;
    }

    /**
     * {@code POST  /persoons} : Create a new persoon.
     *
     * @param persoonDTO the persoonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new persoonDTO, or with status {@code 400 (Bad Request)} if the persoon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/persoons")
    public ResponseEntity<PersoonDTO> createPersoon(@RequestBody PersoonDTO persoonDTO) throws URISyntaxException {
        log.debug("REST request to save Persoon : {}", persoonDTO);
        if (persoonDTO.getId() != null) {
            throw new BadRequestAlertException("A new persoon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersoonDTO result = persoonService.save(persoonDTO);
        return ResponseEntity.created(new URI("/api/persoons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /persoons} : Updates an existing persoon.
     *
     * @param persoonDTO the persoonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated persoonDTO,
     * or with status {@code 400 (Bad Request)} if the persoonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the persoonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/persoons")
    public ResponseEntity<PersoonDTO> updatePersoon(@RequestBody PersoonDTO persoonDTO) throws URISyntaxException {
        log.debug("REST request to update Persoon : {}", persoonDTO);
        if (persoonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersoonDTO result = persoonService.save(persoonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, persoonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /persoons} : get all the persoons.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of persoons in body.
     */
    @GetMapping("/persoons")
    public List<PersoonDTO> getAllPersoons() {
        log.debug("REST request to get all Persoons");
        return persoonService.findAll();
    }

    /**
     * {@code GET  /persoons/:id} : get the "id" persoon.
     *
     * @param id the id of the persoonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the persoonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/persoons/{id}")
    public ResponseEntity<PersoonDTO> getPersoon(@PathVariable Long id) {
        log.debug("REST request to get Persoon : {}", id);
        Optional<PersoonDTO> persoonDTO = persoonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(persoonDTO);
    }

    /**
     * {@code DELETE  /persoons/:id} : delete the "id" persoon.
     *
     * @param id the id of the persoonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/persoons/{id}")
    public ResponseEntity<Void> deletePersoon(@PathVariable Long id) {
        log.debug("REST request to delete Persoon : {}", id);
        persoonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/persoons?query=:query} : search for the persoon corresponding
     * to the query.
     *
     * @param query the query of the persoon search.
     * @return the result of the search.
     */
    @GetMapping("/_search/persoons")
    public List<PersoonDTO> searchPersoons(@RequestParam String query) {
        log.debug("REST request to search Persoons for query {}", query);
        return persoonService.search(query);
    }
}
