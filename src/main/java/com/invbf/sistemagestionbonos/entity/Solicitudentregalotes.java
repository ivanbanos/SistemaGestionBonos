/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "SolicitudEntregaLotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudentregalotes.findAll", query = "SELECT s FROM Solicitudentregalotes s"),
    @NamedQuery(name = "Solicitudentregalotes.findById", query = "SELECT s FROM Solicitudentregalotes s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudentregalotes.findByCantidad", query = "SELECT s FROM Solicitudentregalotes s WHERE s.cantidad = :cantidad")})
public class Solicitudentregalotes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "SolicitudEntregaLotesMaestro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Solicitudentregalotesmaestro solicitudEntregaLotesMaestro;
    @JoinColumn(name = "LotesBonos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lotesbonos lotesBonosid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudEntregaLotesid")
    private List<Bonosnoincluidos> bonosnoincluidosList;

    public Solicitudentregalotes() {
    }

    public Solicitudentregalotes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Solicitudentregalotesmaestro getSolicitudEntregaLotesMaestro() {
        return solicitudEntregaLotesMaestro;
    }

    public void setSolicitudEntregaLotesMaestro(Solicitudentregalotesmaestro solicitudEntregaLotesMaestro) {
        this.solicitudEntregaLotesMaestro = solicitudEntregaLotesMaestro;
    }

    public Lotesbonos getLotesBonosid() {
        return lotesBonosid;
    }

    public void setLotesBonosid(Lotesbonos lotesBonosid) {
        this.lotesBonosid = lotesBonosid;
    }

    @XmlTransient
    public List<Bonosnoincluidos> getBonosnoincluidosList() {
        return bonosnoincluidosList;
    }

    public void setBonosnoincluidosList(List<Bonosnoincluidos> bonosnoincluidosList) {
        this.bonosnoincluidosList = bonosnoincluidosList;
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
        if (!(object instanceof Solicitudentregalotes)) {
            return false;
        }
        Solicitudentregalotes other = (Solicitudentregalotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Solicitudentregalotes[ id=" + id + " ]";
    }
    
}
