/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "solicitudentregaclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudentregaclientes.findAll", query = "SELECT s FROM Solicitudentregaclientes s"),
    @NamedQuery(name = "Solicitudentregaclientes.findBySolicitudEntregaid", query = "SELECT s FROM Solicitudentregaclientes s WHERE s.solicitudentregaclientesPK.solicitudEntregaid = :solicitudEntregaid"),
    @NamedQuery(name = "Solicitudentregaclientes.findByClientesid", query = "SELECT s FROM Solicitudentregaclientes s WHERE s.solicitudentregaclientesPK.clientesid = :clientesid"),
    @NamedQuery(name = "Solicitudentregaclientes.findByValorTotal", query = "SELECT s FROM Solicitudentregaclientes s WHERE s.valorTotal = :valorTotal"),
    @NamedQuery(name = "Solicitudentregaclientes.findByObservaciones", query = "SELECT s FROM Solicitudentregaclientes s WHERE s.observaciones = :observaciones")})
public class Solicitudentregaclientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudentregaclientesPK solicitudentregaclientesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotal")
    private Float valorTotal;
    @Size(max = 45)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "Areaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Areas areaid;
    @JoinColumn(name = "Clientesid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientessgb clientessgb;
    @JoinColumn(name = "SolicitudEntregaid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Solicitudentrega solicitudentrega;

    public Solicitudentregaclientes() {
    }

    public Solicitudentregaclientes(SolicitudentregaclientesPK solicitudentregaclientesPK) {
        this.solicitudentregaclientesPK = solicitudentregaclientesPK;
    }

    public Solicitudentregaclientes(int solicitudEntregaid, int clientesid) {
        this.solicitudentregaclientesPK = new SolicitudentregaclientesPK(solicitudEntregaid, clientesid);
    }

    public SolicitudentregaclientesPK getSolicitudentregaclientesPK() {
        return solicitudentregaclientesPK;
    }

    public void setSolicitudentregaclientesPK(SolicitudentregaclientesPK solicitudentregaclientesPK) {
        this.solicitudentregaclientesPK = solicitudentregaclientesPK;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Areas getAreaid() {
        return areaid;
    }

    public void setAreaid(Areas areaid) {
        this.areaid = areaid;
    }

    public Clientessgb getClientessgb() {
        return clientessgb;
    }

    public void setClientessgb(Clientessgb clientessgb) {
        this.clientessgb = clientessgb;
    }

    public Solicitudentrega getSolicitudentrega() {
        return solicitudentrega;
    }

    public void setSolicitudentrega(Solicitudentrega solicitudentrega) {
        this.solicitudentrega = solicitudentrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudentregaclientesPK != null ? solicitudentregaclientesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudentregaclientes)) {
            return false;
        }
        Solicitudentregaclientes other = (Solicitudentregaclientes) object;
        if ((this.solicitudentregaclientesPK == null && other.solicitudentregaclientesPK != null) || (this.solicitudentregaclientesPK != null && !this.solicitudentregaclientesPK.equals(other.solicitudentregaclientesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes[ solicitudentregaclientesPK=" + solicitudentregaclientesPK + " ]";
    }
    
}
