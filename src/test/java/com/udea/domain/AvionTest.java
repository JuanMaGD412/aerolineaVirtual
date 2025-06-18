package com.udea.domain;

import static com.udea.domain.AvionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AvionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avion.class);
        Avion avion1 = getAvionSample1();
        Avion avion2 = new Avion();
        assertThat(avion1).isNotEqualTo(avion2);

        avion2.setId(avion1.getId());
        assertThat(avion1).isEqualTo(avion2);

        avion2 = getAvionSample2();
        assertThat(avion1).isNotEqualTo(avion2);
    }
}
