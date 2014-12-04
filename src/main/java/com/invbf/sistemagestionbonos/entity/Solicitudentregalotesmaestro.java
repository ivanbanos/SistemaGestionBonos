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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "SolicitudEntregaLotesMaestro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudentregalotesmaestro.findAll", query = "SELECT s FROM Solicitudentregalotesmaestro s"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findById", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findByFecha", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findByObservaciones", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.observaciones = :observaciones"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findByEstado", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findNoAceptada", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.estado != 'ACEPTADA'"),
    @NamedQuery(name = "Solicitudentregalotesmaestro.findByRemitente", query = "SELECT s FROM Solicitudentregalotesmaestro s WHERE s.remitente = :remitente")})
public class Solicitudentregalotesmaestro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 450)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "remitente")
    private int remitente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudEntregaLotesMaestro")
    private List<Solicitudentregalotes> solicitudentregalotesList;

    public Solicitudentregalotesmaestro() {
    }

    public Solicitudentregalotesmaestro(Integer id) {
        this.id = id;
    }

    public Solicitudentregalotesmaestro(Integer id, int remitente) {
        this.id = id;
        this.remitente = remitente;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getRemitente() {
        return remitente;
    }

    public void setRemitente(int remitente) {
        this.remitente = remitente;
    }

    @XmlTransient
    public List<Solicitudentregalotes> getSolicitudentregalotesList() {
        return solicitudentregalotesList;
    }

    public void setSolicitudentregalotesList(List<Solicitudentregalotes> solicitudentregalotesList) {
        this.solicitudentregalotesList = solicitudentregalotesList;
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
        if (!(object instanceof Solicitudentregalotesmaestro)) {
            return false;
        }
        Solicitudentregalotesmaestro other = (Solicitudentregalotesmaestro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro[ id=" + id + " ]";
    }
    
}
