package com.web.taller1.DTO;

import java.util.Date;

import com.web.taller1.entities.Pago;

public class PagoDTO {

    private Long id;
    private Double monto;
    private Date fechaPago;
    private Long usuarioId;  // FK hacia Usuario
    private Long propiedadId;  // FK hacia Propiedad

    // Constructor vacío (obligatorio para algunos frameworks)
    public PagoDTO() {}

    // Constructor que recibe una entidad Pago
    public PagoDTO(Pago pago) {
        this.id = pago.getId();
        this.monto = pago.getMonto();
        this.fechaPago = pago.getFechaPago();
        this.usuarioId = pago.getUsuario().getId();  // Asegúrate de que no sea null
        this.propiedadId = pago.getPropiedad().getId();  // Asegúrate de que no sea null
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(Long propiedadId) {
        this.propiedadId = propiedadId;
    }
}
