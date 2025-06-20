package com.udea.domain;

import static com.udea.domain.AsientoTestSamples.*;
import static com.udea.domain.PagoTestSamples.*;
import static com.udea.domain.PasajeroTestSamples.*;
import static com.udea.domain.ReservaTestSamples.*;
import static com.udea.domain.VueloTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReservaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reserva.class);
        Reserva reserva1 = getReservaSample1();
        Reserva reserva2 = new Reserva();
        assertThat(reserva1).isNotEqualTo(reserva2);

        reserva2.setId(reserva1.getId());
        assertThat(reserva1).isEqualTo(reserva2);

        reserva2 = getReservaSample2();
        assertThat(reserva1).isNotEqualTo(reserva2);
    }

    @Test
    void vueloTest() {
        Reserva reserva = getReservaRandomSampleGenerator();
        Vuelo vueloBack = getVueloRandomSampleGenerator();

        reserva.setVuelo(vueloBack);
        assertThat(reserva.getVuelo()).isEqualTo(vueloBack);

        reserva.vuelo(null);
        assertThat(reserva.getVuelo()).isNull();
    }

    @Test
    void asientoTest() {
        Reserva reserva = getReservaRandomSampleGenerator();
        Asiento asientoBack = getAsientoRandomSampleGenerator();

        reserva.setAsiento(asientoBack);
        assertThat(reserva.getAsiento()).isEqualTo(asientoBack);

        reserva.asiento(null);
        assertThat(reserva.getAsiento()).isNull();
    }

    @Test
    void pasajeroTest() {
        Reserva reserva = getReservaRandomSampleGenerator();
        Pasajero pasajeroBack = getPasajeroRandomSampleGenerator();

        reserva.setPasajero(pasajeroBack);
        assertThat(reserva.getPasajero()).isEqualTo(pasajeroBack);

        reserva.pasajero(null);
        assertThat(reserva.getPasajero()).isNull();
    }

    @Test
    void pagoTest() {
        Reserva reserva = getReservaRandomSampleGenerator();
        Pago pagoBack = getPagoRandomSampleGenerator();

        reserva.addPago(pagoBack);
        assertThat(reserva.getPagos()).containsOnly(pagoBack);
        assertThat(pagoBack.getReserva()).isEqualTo(reserva);

        reserva.removePago(pagoBack);
        assertThat(reserva.getPagos()).doesNotContain(pagoBack);
        assertThat(pagoBack.getReserva()).isNull();

        reserva.pagos(new HashSet<>(Set.of(pagoBack)));
        assertThat(reserva.getPagos()).containsOnly(pagoBack);
        assertThat(pagoBack.getReserva()).isEqualTo(reserva);

        reserva.setPagos(new HashSet<>());
        assertThat(reserva.getPagos()).doesNotContain(pagoBack);
        assertThat(pagoBack.getReserva()).isNull();
    }
}
