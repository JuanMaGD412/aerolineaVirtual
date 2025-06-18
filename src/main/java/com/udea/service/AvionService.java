package com.udea.service;

import com.udea.domain.Avion;
import com.udea.repository.AvionRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.udea.domain.Avion}.
 */
@Service
@Transactional
public class AvionService {

    private static final Logger LOG = LoggerFactory.getLogger(AvionService.class);

    private final AvionRepository avionRepository;

    public AvionService(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }

    /**
     * Save a avion.
     *
     * @param avion the entity to save.
     * @return the persisted entity.
     */
    public Avion save(Avion avion) {
        LOG.debug("Request to save Avion : {}", avion);
        return avionRepository.save(avion);
    }

    /**
     * Update a avion.
     *
     * @param avion the entity to save.
     * @return the persisted entity.
     */
    public Avion update(Avion avion) {
        LOG.debug("Request to update Avion : {}", avion);
        return avionRepository.save(avion);
    }

    /**
     * Partially update a avion.
     *
     * @param avion the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Avion> partialUpdate(Avion avion) {
        LOG.debug("Request to partially update Avion : {}", avion);

        return avionRepository
            .findById(avion.getId())
            .map(existingAvion -> {
                if (avion.getMatricula() != null) {
                    existingAvion.setMatricula(avion.getMatricula());
                }
                if (avion.getModelo() != null) {
                    existingAvion.setModelo(avion.getModelo());
                }
                if (avion.getCapacidad() != null) {
                    existingAvion.setCapacidad(avion.getCapacidad());
                }

                return existingAvion;
            })
            .map(avionRepository::save);
    }

    /**
     * Get all the avions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Avion> findAll(Pageable pageable) {
        LOG.debug("Request to get all Avions");
        return avionRepository.findAll(pageable);
    }

    /**
     * Get one avion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Avion> findOne(Long id) {
        LOG.debug("Request to get Avion : {}", id);
        return avionRepository.findById(id);
    }

    /**
     * Delete the avion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Avion : {}", id);
        avionRepository.deleteById(id);
    }
}
