package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BoekService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BoekDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Boek}.
 */
@RestController
@RequestMapping("/api")
public class BoekResource {

    private final Logger log = LoggerFactory.getLogger(BoekResource.class);

    private static final String ENTITY_NAME = "boek";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoekService boekService;

    public BoekResource(BoekService boekService) {
        this.boekService = boekService;
    }

    /**
     * {@code POST  /boeks} : Create a new boek.
     *
     * @param boekDTO the boekDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boekDTO, or with status {@code 400 (Bad Request)} if the boek has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/boeks")
    public ResponseEntity<BoekDTO> createBoek(@RequestBody BoekDTO boekDTO) throws URISyntaxException {
        log.debug("REST request to save Boek : {}", boekDTO);
        if (boekDTO.getId() != null) {
            throw new BadRequestAlertException("A new boek cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoekDTO result = boekService.save(boekDTO);
        return ResponseEntity.created(new URI("/api/boeks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /boeks} : Updates an existing boek.
     *
     * @param boekDTO the boekDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boekDTO,
     * or with status {@code 400 (Bad Request)} if the boekDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boekDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/boeks")
    public ResponseEntity<BoekDTO> updateBoek(@RequestBody BoekDTO boekDTO) throws URISyntaxException {
        log.debug("REST request to update Boek : {}", boekDTO);
        if (boekDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BoekDTO result = boekService.save(boekDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, boekDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /boeks} : get all the boeks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boeks in body.
     */
    @GetMapping("/boeks")
    public ResponseEntity<List<BoekDTO>> getAllBoeks(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Boeks");
        Page<BoekDTO> page = boekService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /boeks/:id} : get the "id" boek.
     *
     * @param id the id of the boekDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boekDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/boeks/{id}")
    public ResponseEntity<BoekDTO> getBoek(@PathVariable Long id) {
        log.debug("REST request to get Boek : {}", id);
        Optional<BoekDTO> boekDTO = boekService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boekDTO);
    }

    /**
     * {@code DELETE  /boeks/:id} : delete the "id" boek.
     *
     * @param id the id of the boekDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/boeks/{id}")
    public ResponseEntity<Void> deleteBoek(@PathVariable Long id) {
        log.debug("REST request to delete Boek : {}", id);
        boekService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/boeks?query=:query} : search for the boek corresponding
     * to the query.
     *
     * @param query the query of the boek search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/boeks")
    public ResponseEntity<List<BoekDTO>> searchBoeks(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Boeks for query {}", query);
        Page<BoekDTO> page = boekService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
