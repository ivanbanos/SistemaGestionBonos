/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "Bono")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bono.findAll", query = "SELECT b FROM Bono b"),
    @NamedQuery(name = "Bono.findByConsecutivo", query = "SELECT b FROM Bono b WHERE b.consecutivo = :consecutivo"),
    @NamedQuery(name = "Bono.findByCasino", query = "SELECT b FROM Bono b WHERE b.casino = :casino"),
    @NamedQuery(name = "Bono.findByEstado", query = "SELECT b FROM Bono b WHERE b.estado = :estado"),
    @NamedQuery(name = "Bono.findByFechaValidacion", query = "SELECT b FROM Bono b WHERE b.fechaValidacion = :fechaValidacion"),
    @NamedQuery(name = "Bono.findByFechaExpiracion", query = "SELECT b FROM Bono b WHERE b.fechaExpiracion = :fechaExpiracion"),
    @NamedQuery(name = "Bono.findByFechaEntrega", query = "SELECT b FROM Bono b WHERE b.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Bono.findByValidador", query = "SELECT b FROM Bono b WHERE b.validador = :validador"),
    @NamedQuery(name = "Bono.findByAutorizador", query = "SELECT b FROM Bono b WHERE b.autorizador = :autorizador")})
public class Bono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "casino")
    private int casino;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaValidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValidacion;
    @Column(name = "fechaExpiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validador")
    private int validador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autorizador")
    private int autorizador;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tiposbonos tipo;
    @JoinColumn(name = "Denominacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Denominaciones denominacion;
    @JoinColumn(name = "Cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientessgb cliente;

    public Bono() {
    }

    public Bono(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Bono(String consecutivo, int casino, int validador, int autorizador) {
        this.consecutivo = consecutivo;
        this.casino = casino;
        this.validador = validador;
        this.autorizador = autorizador;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getCasino() {
        return casino;
    }

    public void setCasino(int casino) {
        this.casino = casino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getValidador() {
        return validador;
    }

    public void setValidador(int validador) {
        this.validador = validador;
    }

    public int getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(int autorizador) {
        this.autorizador = autorizador;
    }

    public Tiposbonos getTipo() {
        return tipo;
    }

    public void setTipo(Tiposbonos tipo) {
        this.tipo = tipo;
    }

    public Denominaciones getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Denominaciones denominacion) {
        this.denominacion = denominacion;
    }

    public Clientessgb getCliente() {
        return cliente;
    }

    public void setCliente(Clientessgb cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivo != null ? consecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bono)) {
            return false;
        }
        Bono other = (Bono) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Bono[ consecutivo=" + consecutivo + " ]";
    }
    
}
