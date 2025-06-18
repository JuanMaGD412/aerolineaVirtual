package com.udea.service;

import com.udea.domain.Pago;
import com.udea.repository.PagoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.udea.domain.Pago}.
 */
@Service
@Transactional
public class PagoService {

    private static final Logger LOG = LoggerFactory.getLogger(PagoService.class);

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    /**
     * Save a pago.
     *
     * @param pago the entity to save.
     * @return the persisted entity.
     */
    public Pago save(Pago pago) {
        LOG.debug("Request to save Pago : {}", pago);
        return pagoRepository.save(pago);
    }

    /**
     * Update a pago.
     *
     * @param pago the entity to save.
     * @return the persisted entity.
     */
    public Pago update(Pago pago) {
        LOG.debug("Request to update Pago : {}", pago);
        return pagoRepository.save(pago);
    }

    /**
     * Partially update a pago.
     *
     * @param pago the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Pago> partialUpdate(Pago pago) {
        LOG.debug("Request to partially update Pago : {}", pago);

        return pagoRepository
            .findById(pago.getId())
            .map(existingPago -> {
                if (pago.getFechaPago() != null) {
                    existingPago.setFechaPago(pago.getFechaPago());
                }
                if (pago.getMonto() != null) {
                    existingPago.setMonto(pago.getMonto());
                }
                if (pago.getMetodo() != null) {
                    existingPago.setMetodo(pago.getMetodo());
                }

                return existingPago;
            })
            .map(pagoRepository::save);
    }

    /**
     * Get all the pagos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Pago> findAll(Pageable pageable) {
        LOG.debug("Request to get all Pagos");
        return pagoRepository.findAll(pageable);
    }

    /**
     * Get one pago by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Pago> findOne(Long id) {
        LOG.debug("Request to get Pago : {}", id);
        return pagoRepository.findById(id);
    }

    /**
     * Delete the pago by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Pago : {}", id);
        pagoRepository.deleteById(id);
    }
}
