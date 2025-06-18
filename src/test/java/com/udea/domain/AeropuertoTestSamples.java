package com.udea.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AeropuertoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Aeropuerto getAeropuertoSample1() {
        return new Aeropuerto().id(1L).codigo("codigo1").nombre("nombre1").ciudad("ciudad1").pais("pais1");
    }

    public static Aeropuerto getAeropuertoSample2() {
        return new Aeropuerto().id(2L).codigo("codigo2").nombre("nombre2").ciudad("ciudad2").pais("pais2");
    }

    public static Aeropuerto getAeropuertoRandomSampleGenerator() {
        return new Aeropuerto()
            .id(longCount.incrementAndGet())
            .codigo(UUID.randomUUID().toString())
            .nombre(UUID.randomUUID().toString())
            .ciudad(UUID.randomUUID().toString())
            .pais(UUID.randomUUID().toString());
    }
}
