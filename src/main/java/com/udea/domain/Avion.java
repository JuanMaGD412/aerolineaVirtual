package com.udea.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Avion.
 */
@Entity
@Table(name = "avion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Avion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @NotNull
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @NotNull
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Avion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public Avion matricula(String matricula) {
        this.setMatricula(matricula);
        return this;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return this.modelo;
    }

    public Avion modelo(String modelo) {
        this.setModelo(modelo);
        return this;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidad() {
        return this.capacidad;
    }

    public Avion capacidad(Integer capacidad) {
        this.setCapacidad(capacidad);
        return this;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Avion)) {
            return false;
        }
        return getId() != null && getId().equals(((Avion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Avion{" +
            "id=" + getId() +
            ", matricula='" + getMatricula() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", capacidad=" + getCapacidad() +
            "}";
    }
}
