package io.github.jhipster.application.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BoekMapperTest {

    private BoekMapper boekMapper;

    @BeforeEach
    public void setUp() {
        boekMapper = new BoekMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(boekMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(boekMapper.fromId(null)).isNull();
    }
}
