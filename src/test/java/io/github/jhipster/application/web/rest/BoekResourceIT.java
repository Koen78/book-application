package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.BookApplicationApp;
import io.github.jhipster.application.domain.Boek;
import io.github.jhipster.application.repository.BoekRepository;
import io.github.jhipster.application.repository.search.BoekSearchRepository;
import io.github.jhipster.application.service.BoekService;
import io.github.jhipster.application.service.dto.BoekDTO;
import io.github.jhipster.application.service.mapper.BoekMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * Integration tests for the {@link BoekResource} REST controller.
 */
@SpringBootTest(classes = BookApplicationApp.class)
public class BoekResourceIT {

    private static final String DEFAULT_TITEL = "AAAAAAAAAA";
    private static final String UPDATED_TITEL = "BBBBBBBBBB";

    private static final String DEFAULT_AUTEUR = "AAAAAAAAAA";
    private static final String UPDATED_AUTEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAGINAS = 1;
    private static final Integer UPDATED_PAGINAS = 2;

    private static final String DEFAULT_KORTE_INHOUD = "AAAAAAAAAA";
    private static final String UPDATED_KORTE_INHOUD = "BBBBBBBBBB";

    @Autowired
    private BoekRepository boekRepository;

    @Autowired
    private BoekMapper boekMapper;

    @Autowired
    private BoekService boekService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.BoekSearchRepositoryMockConfiguration
     */
    @Autowired
    private BoekSearchRepository mockBoekSearchRepository;

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

    private MockMvc restBoekMockMvc;

    private Boek boek;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BoekResource boekResource = new BoekResource(boekService);
        this.restBoekMockMvc = MockMvcBuilders.standaloneSetup(boekResource)
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
    public static Boek createEntity(EntityManager em) {
        Boek boek = new Boek()
            .titel(DEFAULT_TITEL)
            .auteur(DEFAULT_AUTEUR)
            .paginas(DEFAULT_PAGINAS)
            .korteInhoud(DEFAULT_KORTE_INHOUD);
        return boek;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boek createUpdatedEntity(EntityManager em) {
        Boek boek = new Boek()
            .titel(UPDATED_TITEL)
            .auteur(UPDATED_AUTEUR)
            .paginas(UPDATED_PAGINAS)
            .korteInhoud(UPDATED_KORTE_INHOUD);
        return boek;
    }

    @BeforeEach
    public void initTest() {
        boek = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoek() throws Exception {
        int databaseSizeBeforeCreate = boekRepository.findAll().size();

        // Create the Boek
        BoekDTO boekDTO = boekMapper.toDto(boek);
        restBoekMockMvc.perform(post("/api/boeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boekDTO)))
            .andExpect(status().isCreated());

        // Validate the Boek in the database
        List<Boek> boekList = boekRepository.findAll();
        assertThat(boekList).hasSize(databaseSizeBeforeCreate + 1);
        Boek testBoek = boekList.get(boekList.size() - 1);
        assertThat(testBoek.getTitel()).isEqualTo(DEFAULT_TITEL);
        assertThat(testBoek.getAuteur()).isEqualTo(DEFAULT_AUTEUR);
        assertThat(testBoek.getPaginas()).isEqualTo(DEFAULT_PAGINAS);
        assertThat(testBoek.getKorteInhoud()).isEqualTo(DEFAULT_KORTE_INHOUD);

        // Validate the Boek in Elasticsearch
        verify(mockBoekSearchRepository, times(1)).save(testBoek);
    }

    @Test
    @Transactional
    public void createBoekWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boekRepository.findAll().size();

        // Create the Boek with an existing ID
        boek.setId(1L);
        BoekDTO boekDTO = boekMapper.toDto(boek);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoekMockMvc.perform(post("/api/boeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boekDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Boek in the database
        List<Boek> boekList = boekRepository.findAll();
        assertThat(boekList).hasSize(databaseSizeBeforeCreate);

        // Validate the Boek in Elasticsearch
        verify(mockBoekSearchRepository, times(0)).save(boek);
    }


    @Test
    @Transactional
    public void getAllBoeks() throws Exception {
        // Initialize the database
        boekRepository.saveAndFlush(boek);

        // Get all the boekList
        restBoekMockMvc.perform(get("/api/boeks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boek.getId().intValue())))
            .andExpect(jsonPath("$.[*].titel").value(hasItem(DEFAULT_TITEL)))
            .andExpect(jsonPath("$.[*].auteur").value(hasItem(DEFAULT_AUTEUR)))
            .andExpect(jsonPath("$.[*].paginas").value(hasItem(DEFAULT_PAGINAS)))
            .andExpect(jsonPath("$.[*].korteInhoud").value(hasItem(DEFAULT_KORTE_INHOUD)));
    }
    
    @Test
    @Transactional
    public void getBoek() throws Exception {
        // Initialize the database
        boekRepository.saveAndFlush(boek);

        // Get the boek
        restBoekMockMvc.perform(get("/api/boeks/{id}", boek.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boek.getId().intValue()))
            .andExpect(jsonPath("$.titel").value(DEFAULT_TITEL))
            .andExpect(jsonPath("$.auteur").value(DEFAULT_AUTEUR))
            .andExpect(jsonPath("$.paginas").value(DEFAULT_PAGINAS))
            .andExpect(jsonPath("$.korteInhoud").value(DEFAULT_KORTE_INHOUD));
    }

    @Test
    @Transactional
    public void getNonExistingBoek() throws Exception {
        // Get the boek
        restBoekMockMvc.perform(get("/api/boeks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoek() throws Exception {
        // Initialize the database
        boekRepository.saveAndFlush(boek);

        int databaseSizeBeforeUpdate = boekRepository.findAll().size();

        // Update the boek
        Boek updatedBoek = boekRepository.findById(boek.getId()).get();
        // Disconnect from session so that the updates on updatedBoek are not directly saved in db
        em.detach(updatedBoek);
        updatedBoek
            .titel(UPDATED_TITEL)
            .auteur(UPDATED_AUTEUR)
            .paginas(UPDATED_PAGINAS)
            .korteInhoud(UPDATED_KORTE_INHOUD);
        BoekDTO boekDTO = boekMapper.toDto(updatedBoek);

        restBoekMockMvc.perform(put("/api/boeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boekDTO)))
            .andExpect(status().isOk());

        // Validate the Boek in the database
        List<Boek> boekList = boekRepository.findAll();
        assertThat(boekList).hasSize(databaseSizeBeforeUpdate);
        Boek testBoek = boekList.get(boekList.size() - 1);
        assertThat(testBoek.getTitel()).isEqualTo(UPDATED_TITEL);
        assertThat(testBoek.getAuteur()).isEqualTo(UPDATED_AUTEUR);
        assertThat(testBoek.getPaginas()).isEqualTo(UPDATED_PAGINAS);
        assertThat(testBoek.getKorteInhoud()).isEqualTo(UPDATED_KORTE_INHOUD);

        // Validate the Boek in Elasticsearch
        verify(mockBoekSearchRepository, times(1)).save(testBoek);
    }

    @Test
    @Transactional
    public void updateNonExistingBoek() throws Exception {
        int databaseSizeBeforeUpdate = boekRepository.findAll().size();

        // Create the Boek
        BoekDTO boekDTO = boekMapper.toDto(boek);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoekMockMvc.perform(put("/api/boeks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boekDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Boek in the database
        List<Boek> boekList = boekRepository.findAll();
        assertThat(boekList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Boek in Elasticsearch
        verify(mockBoekSearchRepository, times(0)).save(boek);
    }

    @Test
    @Transactional
    public void deleteBoek() throws Exception {
        // Initialize the database
        boekRepository.saveAndFlush(boek);

        int databaseSizeBeforeDelete = boekRepository.findAll().size();

        // Delete the boek
        restBoekMockMvc.perform(delete("/api/boeks/{id}", boek.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Boek> boekList = boekRepository.findAll();
        assertThat(boekList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Boek in Elasticsearch
        verify(mockBoekSearchRepository, times(1)).deleteById(boek.getId());
    }

    @Test
    @Transactional
    public void searchBoek() throws Exception {
        // Initialize the database
        boekRepository.saveAndFlush(boek);
        when(mockBoekSearchRepository.search(queryStringQuery("id:" + boek.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(boek), PageRequest.of(0, 1), 1));
        // Search the boek
        restBoekMockMvc.perform(get("/api/_search/boeks?query=id:" + boek.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boek.getId().intValue())))
            .andExpect(jsonPath("$.[*].titel").value(hasItem(DEFAULT_TITEL)))
            .andExpect(jsonPath("$.[*].auteur").value(hasItem(DEFAULT_AUTEUR)))
            .andExpect(jsonPath("$.[*].paginas").value(hasItem(DEFAULT_PAGINAS)))
            .andExpect(jsonPath("$.[*].korteInhoud").value(hasItem(DEFAULT_KORTE_INHOUD)));
    }
}
