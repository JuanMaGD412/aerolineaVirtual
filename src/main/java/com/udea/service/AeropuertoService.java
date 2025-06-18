package com.udea.service;

import com.udea.domain.Aeropuerto;
import com.udea.repository.AeropuertoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.udea.domain.Aeropuerto}.
 */
@Service
@Transactional
public class AeropuertoService {

    private static final Logger LOG = LoggerFactory.getLogger(AeropuertoService.class);

    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoService(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    /**
     * Save a aeropuerto.
     *
     * @param aeropuerto the entity to save.
     * @return the persisted entity.
     */
    public Aeropuerto save(Aeropuerto aeropuerto) {
        LOG.debug("Request to save Aeropuerto : {}", aeropuerto);
        return aeropuertoRepository.save(aeropuerto);
    }

    /**
     * Update a aeropuerto.
     *
     * @param aeropuerto the entity to save.
     * @return the persisted entity.
     */
    public Aeropuerto update(Aeropuerto aeropuerto) {
        LOG.debug("Request to update Aeropuerto : {}", aeropuerto);
        return aeropuertoRepository.save(aeropuerto);
    }

    /**
     * Partially update a aeropuerto.
     *
     * @param aeropuerto the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Aeropuerto> partialUpdate(Aeropuerto aeropuerto) {
        LOG.debug("Request to partially update Aeropuerto : {}", aeropuerto);

        return aeropuertoRepository
            .findById(aeropuerto.getId())
            .map(existingAeropuerto -> {
                if (aeropuerto.getCodigo() != null) {
                    existingAeropuerto.setCodigo(aeropuerto.getCodigo());
                }
                if (aeropuerto.getNombre() != null) {
                    existingAeropuerto.setNombre(aeropuerto.getNombre());
                }
                if (aeropuerto.getCiudad() != null) {
                    existingAeropuerto.setCiudad(aeropuerto.getCiudad());
                }
                if (aeropuerto.getPais() != null) {
                    existingAeropuerto.setPais(aeropuerto.getPais());
                }

                return existingAeropuerto;
            })
            .map(aeropuertoRepository::save);
    }

    /**
     * Get all the aeropuertos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Aeropuerto> findAll(Pageable pageable) {
        LOG.debug("Request to get all Aeropuertos");
        return aeropuertoRepository.findAll(pageable);
    }

    /**
     * Get one aeropuerto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Aeropuerto> findOne(Long id) {
        LOG.debug("Request to get Aeropuerto : {}", id);
        return aeropuertoRepository.findById(id);
    }

    /**
     * Delete the aeropuerto by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Aeropuerto : {}", id);
        aeropuertoRepository.deleteById(id);
    }
}
