package io.github.jhipster.application.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.jhipster.application.web.rest.TestUtil;

public class BoekDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoekDTO.class);
        BoekDTO boekDTO1 = new BoekDTO();
        boekDTO1.setId(1L);
        BoekDTO boekDTO2 = new BoekDTO();
        assertThat(boekDTO1).isNotEqualTo(boekDTO2);
        boekDTO2.setId(boekDTO1.getId());
        assertThat(boekDTO1).isEqualTo(boekDTO2);
        boekDTO2.setId(2L);
        assertThat(boekDTO1).isNotEqualTo(boekDTO2);
        boekDTO1.setId(null);
        assertThat(boekDTO1).isNotEqualTo(boekDTO2);
    }
}
