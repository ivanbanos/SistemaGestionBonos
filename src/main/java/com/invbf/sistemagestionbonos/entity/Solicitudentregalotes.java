/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "solicitudentregalotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudentregalotes.findAll", query = "SELECT s FROM Solicitudentregalotes s"),
    @NamedQuery(name = "Solicitudentregalotes.findById", query = "SELECT s FROM Solicitudentregalotes s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudentregalotes.findByDesde", query = "SELECT s FROM Solicitudentregalotes s WHERE s.desde = :desde"),
    @NamedQuery(name = "Solicitudentregalotes.findByHasta", query = "SELECT s FROM Solicitudentregalotes s WHERE s.hasta = :hasta"),
    @NamedQuery(name = "Solicitudentregalotes.findByIdCasino", query = "SELECT s FROM Solicitudentregalotes s WHERE s.idCasino = :idCasino")})
public class Solicitudentregalotes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "desde")
    private String desde;
    @Size(max = 45)
    @Column(name = "hasta")
    private String hasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCasino")
    private int idCasino;
    @JoinColumn(name = "TiposBonosid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tiposbonos tiposBonosid;
    @JoinColumn(name = "Denominacionesid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Denominaciones denominacionesid;
    @JoinColumn(name = "SolicitudEntregaLotesMaestro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Solicitudentregalotesmaestro solicitudEntregaLotesMaestro;

    public Solicitudentregalotes() {
    }

    public Solicitudentregalotes(Integer id) {
        this.id = id;
    }

    public Solicitudentregalotes(Integer id, int idCasino) {
        this.id = id;
        this.idCasino = idCasino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getIdCasino() {
        return idCasino;
    }

    public void setIdCasino(int idCasino) {
        this.idCasino = idCasino;
    }

    public Tiposbonos getTiposBonosid() {
        return tiposBonosid;
    }

    public void setTiposBonosid(Tiposbonos tiposBonosid) {
        this.tiposBonosid = tiposBonosid;
    }

    public Denominaciones getDenominacionesid() {
        return denominacionesid;
    }

    public void setDenominacionesid(Denominaciones denominacionesid) {
        this.denominacionesid = denominacionesid;
    }

    public Solicitudentregalotesmaestro getSolicitudEntregaLotesMaestro() {
        return solicitudEntregaLotesMaestro;
    }

    public void setSolicitudEntregaLotesMaestro(Solicitudentregalotesmaestro solicitudEntregaLotesMaestro) {
        this.solicitudEntregaLotesMaestro = solicitudEntregaLotesMaestro;
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
