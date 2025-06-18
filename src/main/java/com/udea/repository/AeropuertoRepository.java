package com.udea.repository;

import com.udea.domain.Aeropuerto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Aeropuerto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {}
