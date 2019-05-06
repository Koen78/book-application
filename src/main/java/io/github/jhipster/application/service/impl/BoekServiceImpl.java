package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.BoekService;
import io.github.jhipster.application.domain.Boek;
import io.github.jhipster.application.repository.BoekRepository;
import io.github.jhipster.application.repository.search.BoekSearchRepository;
import io.github.jhipster.application.service.dto.BoekDTO;
import io.github.jhipster.application.service.mapper.BoekMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Boek}.
 */
@Service
@Transactional
public class BoekServiceImpl implements BoekService {

    private final Logger log = LoggerFactory.getLogger(BoekServiceImpl.class);

    private final BoekRepository boekRepository;

    private final BoekMapper boekMapper;

    private final BoekSearchRepository boekSearchRepository;

    public BoekServiceImpl(BoekRepository boekRepository, BoekMapper boekMapper, BoekSearchRepository boekSearchRepository) {
        this.boekRepository = boekRepository;
        this.boekMapper = boekMapper;
        this.boekSearchRepository = boekSearchRepository;
    }

    /**
     * Save a boek.
     *
     * @param boekDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BoekDTO save(BoekDTO boekDTO) {
        log.debug("Request to save Boek : {}", boekDTO);
        Boek boek = boekMapper.toEntity(boekDTO);
        boek = boekRepository.save(boek);
        BoekDTO result = boekMapper.toDto(boek);
        boekSearchRepository.save(boek);
        return result;
    }

    /**
     * Get all the boeks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoekDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Boeks");
        return boekRepository.findAll(pageable)
            .map(boekMapper::toDto);
    }


    /**
     * Get one boek by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BoekDTO> findOne(Long id) {
        log.debug("Request to get Boek : {}", id);
        return boekRepository.findById(id)
            .map(boekMapper::toDto);
    }

    /**
     * Delete the boek by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Boek : {}", id);
        boekRepository.deleteById(id);
        boekSearchRepository.deleteById(id);
    }

    /**
     * Search for the boek corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoekDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Boeks for query {}", query);
        return boekSearchRepository.search(queryStringQuery(query), pageable)
            .map(boekMapper::toDto);
    }
}
