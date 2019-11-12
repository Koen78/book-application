package io.github.jhipster.application.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PersoonMapperTest {

    private PersoonMapper persoonMapper;

    @BeforeEach
    public void setUp() {
        persoonMapper = new PersoonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(persoonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(persoonMapper.fromId(null)).isNull();
    }
}
