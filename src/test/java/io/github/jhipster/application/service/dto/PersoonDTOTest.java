package io.github.jhipster.application.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.jhipster.application.web.rest.TestUtil;

public class PersoonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersoonDTO.class);
        PersoonDTO persoonDTO1 = new PersoonDTO();
        persoonDTO1.setId(1L);
        PersoonDTO persoonDTO2 = new PersoonDTO();
        assertThat(persoonDTO1).isNotEqualTo(persoonDTO2);
        persoonDTO2.setId(persoonDTO1.getId());
        assertThat(persoonDTO1).isEqualTo(persoonDTO2);
        persoonDTO2.setId(2L);
        assertThat(persoonDTO1).isNotEqualTo(persoonDTO2);
        persoonDTO1.setId(null);
        assertThat(persoonDTO1).isNotEqualTo(persoonDTO2);
    }
}
