package com.udea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.udea.domain.enumeration.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Reserva.
 */
@Entity
@Table(name = "reserva")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @NotNull
    @Column(name = "fecha_reserva", nullable = false)
    private Instant fechaReserva;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReserva estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "aeropuertoOrigen", "aeropuertoDestino", "avion", "piloto", "asientos", "reservas" },
        allowSetters = true
    )
    private Vuelo vuelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "vuelo", "reservas" }, allowSetters = true)
    private Asiento asiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "reservas" }, allowSetters = true)
    private Pasajero pasajero;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "reserva" }, allowSetters = true)
    private Set<Pago> pagos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Reserva id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Reserva codigo(String codigo) {
        this.setCodigo(codigo);
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Instant getFechaReserva() {
        return this.fechaReserva;
    }

    public Reserva fechaReserva(Instant fechaReserva) {
        this.setFechaReserva(fechaReserva);
        return this;
    }

    public void setFechaReserva(Instant fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return this.estado;
    }

    public Reserva estado(EstadoReserva estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Vuelo getVuelo() {
        return this.vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Reserva vuelo(Vuelo vuelo) {
        this.setVuelo(vuelo);
        return this;
    }

    public Asiento getAsiento() {
        return this.asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Reserva asiento(Asiento asiento) {
        this.setAsiento(asiento);
        return this;
    }

    public Pasajero getPasajero() {
        return this.pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Reserva pasajero(Pasajero pasajero) {
        this.setPasajero(pasajero);
        return this;
    }

    public Set<Pago> getPagos() {
        return this.pagos;
    }

    public void setPagos(Set<Pago> pagos) {
        if (this.pagos != null) {
            this.pagos.forEach(i -> i.setReserva(null));
        }
        if (pagos != null) {
            pagos.forEach(i -> i.setReserva(this));
        }
        this.pagos = pagos;
    }

    public Reserva pagos(Set<Pago> pagos) {
        this.setPagos(pagos);
        return this;
    }

    public Reserva addPago(Pago pago) {
        this.pagos.add(pago);
        pago.setReserva(this);
        return this;
    }

    public Reserva removePago(Pago pago) {
        this.pagos.remove(pago);
        pago.setReserva(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reserva)) {
            return false;
        }
        return getId() != null && getId().equals(((Reserva) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reserva{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", fechaReserva='" + getFechaReserva() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
