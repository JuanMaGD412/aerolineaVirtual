package com.udea.repository;

import com.udea.domain.Avion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Avion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {}
