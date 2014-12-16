/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "ControlSalidaBonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Controlsalidabonos.findAll", query = "SELECT c FROM Controlsalidabonos c"),
    @NamedQuery(name = "Controlsalidabonos.findById", query = "SELECT c FROM Controlsalidabonos c WHERE c.id = :id"),
    @NamedQuery(name = "Controlsalidabonos.findByFecha", query = "SELECT c FROM Controlsalidabonos c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Controlsalidabonos.findBySolicitante", query = "SELECT c FROM Controlsalidabonos c WHERE c.solicitante = :solicitante")})
public class Controlsalidabonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "solicitante")
    private Integer solicitante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlsalidabonos")
    private List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonosList;
    @JoinColumn(name = "SolicitudEntrega_id", referencedColumnName = "id")
    @ManyToOne
    private Solicitudentrega solicitudEntregaid;
    @OneToMany(mappedBy = "controlSalidaBonosid")
    private List<Bono> bonoList;
    
    @Column(name = "fechavencimientobonos")
    @Temporal(TemporalType.DATE)
    private Date fechavencimientobonos;

    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    public Controlsalidabonos() {
    }

    public Controlsalidabonos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechavencimientobonos() {
        return fechavencimientobonos;
    }

    public void setFechavencimientobonos(Date fechavencimientobonos) {
        this.fechavencimientobonos = fechavencimientobonos;
    }

    public Integer getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Integer solicitante) {
        this.solicitante = solicitante;
    }

    @XmlTransient
    public List<ControlsalidabonosHasLotesbonos> getControlsalidabonosHasLotesbonosList() {
        return controlsalidabonosHasLotesbonosList;
    }

    public void setControlsalidabonosHasLotesbonosList(List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonosList) {
        this.controlsalidabonosHasLotesbonosList = controlsalidabonosHasLotesbonosList;
    }

    public Solicitudentrega getSolicitudEntregaid() {
        return solicitudEntregaid;
    }

    public void setSolicitudEntregaid(Solicitudentrega solicitudEntregaid) {
        this.solicitudEntregaid = solicitudEntregaid;
    }

    @XmlTransient
    public List<Bono> getBonoList() {
        return bonoList;
    }

    public void setBonoList(List<Bono> bonoList) {
        this.bonoList = bonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controlsalidabonos)) {
            return false;
        }
        Controlsalidabonos other = (Controlsalidabonos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Controlsalidabonos[ id=" + id + " ]";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
