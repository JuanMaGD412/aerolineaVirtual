package com.udea.service;

import com.udea.domain.Empleado;
import com.udea.repository.EmpleadoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.udea.domain.Empleado}.
 */
@Service
@Transactional
public class EmpleadoService {

    private static final Logger LOG = LoggerFactory.getLogger(EmpleadoService.class);

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Save a empleado.
     *
     * @param empleado the entity to save.
     * @return the persisted entity.
     */
    public Empleado save(Empleado empleado) {
        LOG.debug("Request to save Empleado : {}", empleado);
        return empleadoRepository.save(empleado);
    }

    /**
     * Update a empleado.
     *
     * @param empleado the entity to save.
     * @return the persisted entity.
     */
    public Empleado update(Empleado empleado) {
        LOG.debug("Request to update Empleado : {}", empleado);
        return empleadoRepository.save(empleado);
    }

    /**
     * Partially update a empleado.
     *
     * @param empleado the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Empleado> partialUpdate(Empleado empleado) {
        LOG.debug("Request to partially update Empleado : {}", empleado);

        return empleadoRepository
            .findById(empleado.getId())
            .map(existingEmpleado -> {
                if (empleado.getNombre() != null) {
                    existingEmpleado.setNombre(empleado.getNombre());
                }
                if (empleado.getApellido() != null) {
                    existingEmpleado.setApellido(empleado.getApellido());
                }
                if (empleado.getCargo() != null) {
                    existingEmpleado.setCargo(empleado.getCargo());
                }
                if (empleado.getEmail() != null) {
                    existingEmpleado.setEmail(empleado.getEmail());
                }
                if (empleado.getTelefono() != null) {
                    existingEmpleado.setTelefono(empleado.getTelefono());
                }

                return existingEmpleado;
            })
            .map(empleadoRepository::save);
    }

    /**
     * Get all the empleados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Empleado> findAll(Pageable pageable) {
        LOG.debug("Request to get all Empleados");
        return empleadoRepository.findAll(pageable);
    }

    /**
     * Get one empleado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Empleado> findOne(Long id) {
        LOG.debug("Request to get Empleado : {}", id);
        return empleadoRepository.findById(id);
    }

    /**
     * Delete the empleado by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Empleado : {}", id);
        empleadoRepository.deleteById(id);
    }
}
