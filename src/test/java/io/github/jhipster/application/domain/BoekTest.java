package io.github.jhipster.application.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.jhipster.application.web.rest.TestUtil;

public class BoekTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Boek.class);
        Boek boek1 = new Boek();
        boek1.setId(1L);
        Boek boek2 = new Boek();
        boek2.setId(boek1.getId());
        assertThat(boek1).isEqualTo(boek2);
        boek2.setId(2L);
        assertThat(boek1).isNotEqualTo(boek2);
        boek1.setId(null);
        assertThat(boek1).isNotEqualTo(boek2);
    }
}
