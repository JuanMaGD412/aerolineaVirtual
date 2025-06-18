package com.udea.domain;

import static com.udea.domain.AeropuertoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AeropuertoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aeropuerto.class);
        Aeropuerto aeropuerto1 = getAeropuertoSample1();
        Aeropuerto aeropuerto2 = new Aeropuerto();
        assertThat(aeropuerto1).isNotEqualTo(aeropuerto2);

        aeropuerto2.setId(aeropuerto1.getId());
        assertThat(aeropuerto1).isEqualTo(aeropuerto2);

        aeropuerto2 = getAeropuertoSample2();
        assertThat(aeropuerto1).isNotEqualTo(aeropuerto2);
    }
}
