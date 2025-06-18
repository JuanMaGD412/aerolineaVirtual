package com.udea.domain;

import static com.udea.domain.AeropuertoTestSamples.*;
import static com.udea.domain.AsientoTestSamples.*;
import static com.udea.domain.AvionTestSamples.*;
import static com.udea.domain.EmpleadoTestSamples.*;
import static com.udea.domain.ReservaTestSamples.*;
import static com.udea.domain.VueloTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VueloTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vuelo.class);
        Vuelo vuelo1 = getVueloSample1();
        Vuelo vuelo2 = new Vuelo();
        assertThat(vuelo1).isNotEqualTo(vuelo2);

        vuelo2.setId(vuelo1.getId());
        assertThat(vuelo1).isEqualTo(vuelo2);

        vuelo2 = getVueloSample2();
        assertThat(vuelo1).isNotEqualTo(vuelo2);
    }

    @Test
    void aeropuertoOrigenTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Aeropuerto aeropuertoBack = getAeropuertoRandomSampleGenerator();

        vuelo.setAeropuertoOrigen(aeropuertoBack);
        assertThat(vuelo.getAeropuertoOrigen()).isEqualTo(aeropuertoBack);

        vuelo.aeropuertoOrigen(null);
        assertThat(vuelo.getAeropuertoOrigen()).isNull();
    }

    @Test
    void aeropuertoDestinoTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Aeropuerto aeropuertoBack = getAeropuertoRandomSampleGenerator();

        vuelo.setAeropuertoDestino(aeropuertoBack);
        assertThat(vuelo.getAeropuertoDestino()).isEqualTo(aeropuertoBack);

        vuelo.aeropuertoDestino(null);
        assertThat(vuelo.getAeropuertoDestino()).isNull();
    }

    @Test
    void avionTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Avion avionBack = getAvionRandomSampleGenerator();

        vuelo.setAvion(avionBack);
        assertThat(vuelo.getAvion()).isEqualTo(avionBack);

        vuelo.avion(null);
        assertThat(vuelo.getAvion()).isNull();
    }

    @Test
    void pilotoTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Empleado empleadoBack = getEmpleadoRandomSampleGenerator();

        vuelo.setPiloto(empleadoBack);
        assertThat(vuelo.getPiloto()).isEqualTo(empleadoBack);

        vuelo.piloto(null);
        assertThat(vuelo.getPiloto()).isNull();
    }

    @Test
    void asientoTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Asiento asientoBack = getAsientoRandomSampleGenerator();

        vuelo.addAsiento(asientoBack);
        assertThat(vuelo.getAsientos()).containsOnly(asientoBack);
        assertThat(asientoBack.getVuelo()).isEqualTo(vuelo);

        vuelo.removeAsiento(asientoBack);
        assertThat(vuelo.getAsientos()).doesNotContain(asientoBack);
        assertThat(asientoBack.getVuelo()).isNull();

        vuelo.asientos(new HashSet<>(Set.of(asientoBack)));
        assertThat(vuelo.getAsientos()).containsOnly(asientoBack);
        assertThat(asientoBack.getVuelo()).isEqualTo(vuelo);

        vuelo.setAsientos(new HashSet<>());
        assertThat(vuelo.getAsientos()).doesNotContain(asientoBack);
        assertThat(asientoBack.getVuelo()).isNull();
    }

    @Test
    void reservaTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Reserva reservaBack = getReservaRandomSampleGenerator();

        vuelo.addReserva(reservaBack);
        assertThat(vuelo.getReservas()).containsOnly(reservaBack);
        assertThat(reservaBack.getVuelo()).isEqualTo(vuelo);

        vuelo.removeReserva(reservaBack);
        assertThat(vuelo.getReservas()).doesNotContain(reservaBack);
        assertThat(reservaBack.getVuelo()).isNull();

        vuelo.reservas(new HashSet<>(Set.of(reservaBack)));
        assertThat(vuelo.getReservas()).containsOnly(reservaBack);
        assertThat(reservaBack.getVuelo()).isEqualTo(vuelo);

        vuelo.setReservas(new HashSet<>());
        assertThat(vuelo.getReservas()).doesNotContain(reservaBack);
        assertThat(reservaBack.getVuelo()).isNull();
    }
}
