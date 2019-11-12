package io.github.jhipster.application.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.jhipster.application.web.rest.TestUtil;

public class PersoonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Persoon.class);
        Persoon persoon1 = new Persoon();
        persoon1.setId(1L);
        Persoon persoon2 = new Persoon();
        persoon2.setId(persoon1.getId());
        assertThat(persoon1).isEqualTo(persoon2);
        persoon2.setId(2L);
        assertThat(persoon1).isNotEqualTo(persoon2);
        persoon1.setId(null);
        assertThat(persoon1).isNotEqualTo(persoon2);
    }
}
