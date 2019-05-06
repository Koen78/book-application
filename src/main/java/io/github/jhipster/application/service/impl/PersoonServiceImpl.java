package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PersoonService;
import io.github.jhipster.application.domain.Persoon;
import io.github.jhipster.application.repository.PersoonRepository;
import io.github.jhipster.application.repository.search.PersoonSearchRepository;
import io.github.jhipster.application.service.dto.PersoonDTO;
import io.github.jhipster.application.service.mapper.PersoonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Persoon}.
 */
@Service
@Transactional
public class PersoonServiceImpl implements PersoonService {

    private final Logger log = LoggerFactory.getLogger(PersoonServiceImpl.class);

    private final PersoonRepository persoonRepository;

    private final PersoonMapper persoonMapper;

    private final PersoonSearchRepository persoonSearchRepository;

    public PersoonServiceImpl(PersoonRepository persoonRepository, PersoonMapper persoonMapper, PersoonSearchRepository persoonSearchRepository) {
        this.persoonRepository = persoonRepository;
        this.persoonMapper = persoonMapper;
        this.persoonSearchRepository = persoonSearchRepository;
    }

    /**
     * Save a persoon.
     *
     * @param persoonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersoonDTO save(PersoonDTO persoonDTO) {
        log.debug("Request to save Persoon : {}", persoonDTO);
        Persoon persoon = persoonMapper.toEntity(persoonDTO);
        persoon = persoonRepository.save(persoon);
        PersoonDTO result = persoonMapper.toDto(persoon);
        persoonSearchRepository.save(persoon);
        return result;
    }

    /**
     * Get all the persoons.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersoonDTO> findAll() {
        log.debug("Request to get all Persoons");
        return persoonRepository.findAll().stream()
            .map(persoonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one persoon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersoonDTO> findOne(Long id) {
        log.debug("Request to get Persoon : {}", id);
        return persoonRepository.findById(id)
            .map(persoonMapper::toDto);
    }

    /**
     * Delete the persoon by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Persoon : {}", id);
        persoonRepository.deleteById(id);
        persoonSearchRepository.deleteById(id);
    }

    /**
     * Search for the persoon corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersoonDTO> search(String query) {
        log.debug("Request to search Persoons for query {}", query);
        return StreamSupport
            .stream(persoonSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(persoonMapper::toDto)
            .collect(Collectors.toList());
    }
}
