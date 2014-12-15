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
import javax.persistence.Lob;
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
@Table(name = "controlsalidabonos_has_lotesbonos", catalog = "easl4284_GestionBonos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlsalidabonosHasLotesbonos.findAll", query = "SELECT c FROM ControlsalidabonosHasLotesbonos c"),
    @NamedQuery(name = "ControlsalidabonosHasLotesbonos.findByControlSalidaBonosid", query = "SELECT c FROM ControlsalidabonosHasLotesbonos c WHERE c.controlsalidabonosHasLotesbonosPK.controlSalidaBonosid = :controlSalidaBonosid"),
    @NamedQuery(name = "ControlsalidabonosHasLotesbonos.findByLotesBonosid", query = "SELECT c FROM ControlsalidabonosHasLotesbonos c WHERE c.controlsalidabonosHasLotesbonosPK.lotesBonosid = :lotesBonosid"),
    @NamedQuery(name = "ControlsalidabonosHasLotesbonos.findByIdCliente", query = "SELECT c FROM ControlsalidabonosHasLotesbonos c WHERE c.controlsalidabonosHasLotesbonosPK.idCliente = :idCliente")})
public class ControlsalidabonosHasLotesbonos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ControlsalidabonosHasLotesbonosPK controlsalidabonosHasLotesbonosPK;
    @Lob
    @Size(max = 16777215)
    @Column(name = "cantidad")
    private String cantidad;
    @JoinColumn(name = "LotesBonos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lotesbonos lotesbonos;
    @JoinColumn(name = "ControlSalidaBonos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Controlsalidabonos controlsalidabonos;
    @JoinColumn(name = "idCliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientessgb clientessgb;

    public ControlsalidabonosHasLotesbonos() {
    }

    public ControlsalidabonosHasLotesbonos(ControlsalidabonosHasLotesbonosPK controlsalidabonosHasLotesbonosPK) {
        this.controlsalidabonosHasLotesbonosPK = controlsalidabonosHasLotesbonosPK;
    }

    public ControlsalidabonosHasLotesbonos(int controlSalidaBonosid, int lotesBonosid, int idCliente) {
        this.controlsalidabonosHasLotesbonosPK = new ControlsalidabonosHasLotesbonosPK(controlSalidaBonosid, lotesBonosid, idCliente);
    }

    public ControlsalidabonosHasLotesbonosPK getControlsalidabonosHasLotesbonosPK() {
        return controlsalidabonosHasLotesbonosPK;
    }

    public void setControlsalidabonosHasLotesbonosPK(ControlsalidabonosHasLotesbonosPK controlsalidabonosHasLotesbonosPK) {
        this.controlsalidabonosHasLotesbonosPK = controlsalidabonosHasLotesbonosPK;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Lotesbonos getLotesbonos() {
        return lotesbonos;
    }

    public void setLotesbonos(Lotesbonos lotesbonos) {
        this.lotesbonos = lotesbonos;
    }

    public Controlsalidabonos getControlsalidabonos() {
        return controlsalidabonos;
    }

    public void setControlsalidabonos(Controlsalidabonos controlsalidabonos) {
        this.controlsalidabonos = controlsalidabonos;
    }

    public Clientessgb getClientessgb() {
        return clientessgb;
    }

    public void setClientessgb(Clientessgb clientessgb) {
        this.clientessgb = clientessgb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlsalidabonosHasLotesbonosPK != null ? controlsalidabonosHasLotesbonosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlsalidabonosHasLotesbonos)) {
            return false;
        }
        ControlsalidabonosHasLotesbonos other = (ControlsalidabonosHasLotesbonos) object;
        if ((this.controlsalidabonosHasLotesbonosPK == null && other.controlsalidabonosHasLotesbonosPK != null) || (this.controlsalidabonosHasLotesbonosPK != null && !this.controlsalidabonosHasLotesbonosPK.equals(other.controlsalidabonosHasLotesbonosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.ControlsalidabonosHasLotesbonos[ controlsalidabonosHasLotesbonosPK=" + controlsalidabonosHasLotesbonosPK + " ]";
    }
    
}
