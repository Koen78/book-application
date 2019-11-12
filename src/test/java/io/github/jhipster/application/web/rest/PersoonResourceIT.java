package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.BookApplicationApp;
import io.github.jhipster.application.domain.Persoon;
import io.github.jhipster.application.repository.PersoonRepository;
import io.github.jhipster.application.repository.search.PersoonSearchRepository;
import io.github.jhipster.application.service.PersoonService;
import io.github.jhipster.application.service.dto.PersoonDTO;
import io.github.jhipster.application.service.mapper.PersoonMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersoonResource} REST controller.
 */
@SpringBootTest(classes = BookApplicationApp.class)
public class PersoonResourceIT {

    private static final String DEFAULT_NAAM = "AAAAAAAAAA";
    private static final String UPDATED_NAAM = "BBBBBBBBBB";

    private static final String DEFAULT_VOORNAAM = "AAAAAAAAAA";
    private static final String UPDATED_VOORNAAM = "BBBBBBBBBB";

    @Autowired
    private PersoonRepository persoonRepository;

    @Autowired
    private PersoonMapper persoonMapper;

    @Autowired
    private PersoonService persoonService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.PersoonSearchRepositoryMockConfiguration
     */
    @Autowired
    private PersoonSearchRepository mockPersoonSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPersoonMockMvc;

    private Persoon persoon;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersoonResource persoonResource = new PersoonResource(persoonService);
        this.restPersoonMockMvc = MockMvcBuilders.standaloneSetup(persoonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persoon createEntity(EntityManager em) {
        Persoon persoon = new Persoon()
            .naam(DEFAULT_NAAM)
            .voornaam(DEFAULT_VOORNAAM);
        return persoon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persoon createUpdatedEntity(EntityManager em) {
        Persoon persoon = new Persoon()
            .naam(UPDATED_NAAM)
            .voornaam(UPDATED_VOORNAAM);
        return persoon;
    }

    @BeforeEach
    public void initTest() {
        persoon = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersoon() throws Exception {
        int databaseSizeBeforeCreate = persoonRepository.findAll().size();

        // Create the Persoon
        PersoonDTO persoonDTO = persoonMapper.toDto(persoon);
        restPersoonMockMvc.perform(post("/api/persoons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persoonDTO)))
            .andExpect(status().isCreated());

        // Validate the Persoon in the database
        List<Persoon> persoonList = persoonRepository.findAll();
        assertThat(persoonList).hasSize(databaseSizeBeforeCreate + 1);
        Persoon testPersoon = persoonList.get(persoonList.size() - 1);
        assertThat(testPersoon.getNaam()).isEqualTo(DEFAULT_NAAM);
        assertThat(testPersoon.getVoornaam()).isEqualTo(DEFAULT_VOORNAAM);

        // Validate the Persoon in Elasticsearch
        verify(mockPersoonSearchRepository, times(1)).save(testPersoon);
    }

    @Test
    @Transactional
    public void createPersoonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = persoonRepository.findAll().size();

        // Create the Persoon with an existing ID
        persoon.setId(1L);
        PersoonDTO persoonDTO = persoonMapper.toDto(persoon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersoonMockMvc.perform(post("/api/persoons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persoonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persoon in the database
        List<Persoon> persoonList = persoonRepository.findAll();
        assertThat(persoonList).hasSize(databaseSizeBeforeCreate);

        // Validate the Persoon in Elasticsearch
        verify(mockPersoonSearchRepository, times(0)).save(persoon);
    }


    @Test
    @Transactional
    public void getAllPersoons() throws Exception {
        // Initialize the database
        persoonRepository.saveAndFlush(persoon);

        // Get all the persoonList
        restPersoonMockMvc.perform(get("/api/persoons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persoon.getId().intValue())))
            .andExpect(jsonPath("$.[*].naam").value(hasItem(DEFAULT_NAAM)))
            .andExpect(jsonPath("$.[*].voornaam").value(hasItem(DEFAULT_VOORNAAM)));
    }
    
    @Test
    @Transactional
    public void getPersoon() throws Exception {
        // Initialize the database
        persoonRepository.saveAndFlush(persoon);

        // Get the persoon
        restPersoonMockMvc.perform(get("/api/persoons/{id}", persoon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(persoon.getId().intValue()))
            .andExpect(jsonPath("$.naam").value(DEFAULT_NAAM))
            .andExpect(jsonPath("$.voornaam").value(DEFAULT_VOORNAAM));
    }

    @Test
    @Transactional
    public void getNonExistingPersoon() throws Exception {
        // Get the persoon
        restPersoonMockMvc.perform(get("/api/persoons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersoon() throws Exception {
        // Initialize the database
        persoonRepository.saveAndFlush(persoon);

        int databaseSizeBeforeUpdate = persoonRepository.findAll().size();

        // Update the persoon
        Persoon updatedPersoon = persoonRepository.findById(persoon.getId()).get();
        // Disconnect from session so that the updates on updatedPersoon are not directly saved in db
        em.detach(updatedPersoon);
        updatedPersoon
            .naam(UPDATED_NAAM)
            .voornaam(UPDATED_VOORNAAM);
        PersoonDTO persoonDTO = persoonMapper.toDto(updatedPersoon);

        restPersoonMockMvc.perform(put("/api/persoons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persoonDTO)))
            .andExpect(status().isOk());

        // Validate the Persoon in the database
        List<Persoon> persoonList = persoonRepository.findAll();
        assertThat(persoonList).hasSize(databaseSizeBeforeUpdate);
        Persoon testPersoon = persoonList.get(persoonList.size() - 1);
        assertThat(testPersoon.getNaam()).isEqualTo(UPDATED_NAAM);
        assertThat(testPersoon.getVoornaam()).isEqualTo(UPDATED_VOORNAAM);

        // Validate the Persoon in Elasticsearch
        verify(mockPersoonSearchRepository, times(1)).save(testPersoon);
    }

    @Test
    @Transactional
    public void updateNonExistingPersoon() throws Exception {
        int databaseSizeBeforeUpdate = persoonRepository.findAll().size();

        // Create the Persoon
        PersoonDTO persoonDTO = persoonMapper.toDto(persoon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersoonMockMvc.perform(put("/api/persoons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persoonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persoon in the database
        List<Persoon> persoonList = persoonRepository.findAll();
        assertThat(persoonList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Persoon in Elasticsearch
        verify(mockPersoonSearchRepository, times(0)).save(persoon);
    }

    @Test
    @Transactional
    public void deletePersoon() throws Exception {
        // Initialize the database
        persoonRepository.saveAndFlush(persoon);

        int databaseSizeBeforeDelete = persoonRepository.findAll().size();

        // Delete the persoon
        restPersoonMockMvc.perform(delete("/api/persoons/{id}", persoon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persoon> persoonList = persoonRepository.findAll();
        assertThat(persoonList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Persoon in Elasticsearch
        verify(mockPersoonSearchRepository, times(1)).deleteById(persoon.getId());
    }

    @Test
    @Transactional
    public void searchPersoon() throws Exception {
        // Initialize the database
        persoonRepository.saveAndFlush(persoon);
        when(mockPersoonSearchRepository.search(queryStringQuery("id:" + persoon.getId())))
            .thenReturn(Collections.singletonList(persoon));
        // Search the persoon
        restPersoonMockMvc.perform(get("/api/_search/persoons?query=id:" + persoon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persoon.getId().intValue())))
            .andExpect(jsonPath("$.[*].naam").value(hasItem(DEFAULT_NAAM)))
            .andExpect(jsonPath("$.[*].voornaam").value(hasItem(DEFAULT_VOORNAAM)));
    }
}
