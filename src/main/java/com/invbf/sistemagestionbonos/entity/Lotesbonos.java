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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "LotesBonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotesbonos.findAll", query = "SELECT l FROM Lotesbonos l"),
    @NamedQuery(name = "Lotesbonos.findById", query = "SELECT l FROM Lotesbonos l WHERE l.id = :id"),
    @NamedQuery(name = "Lotesbonos.findByDesde", query = "SELECT l FROM Lotesbonos l WHERE l.desde = :desde"),
    @NamedQuery(name = "Lotesbonos.findByHasta", query = "SELECT l FROM Lotesbonos l WHERE l.hasta = :hasta"),
    @NamedQuery(name = "Lotesbonos.findByIdCasino", query = "SELECT l FROM Lotesbonos l WHERE l.idCasino = :idCasino"),
    @NamedQuery(name = "getexistesnte", query = "SELECT l FROM Lotesbonos l WHERE l.idCasino = :idCasino AND l.denominacion = :denominacion AND l.tipoBono = :tipoBono")})
public class Lotesbonos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "desde")
    private String desde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hasta")
    private String hasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCasino")
    private int idCasino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotesBonosid")
    private List<Solicitudentregalotes> solicitudentregalotesList;
    @JoinColumn(name = "TipoBono", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tiposbonos tipoBono;
    @JoinColumn(name = "Denominacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Denominaciones denominacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotesBonosid")
    private List<Bonosnofisicos> bonosnofisicosList;

    public Lotesbonos() {
    }

    public Lotesbonos(Integer id) {
        this.id = id;
    }

    public Lotesbonos(Integer id, String desde, String hasta, int idCasino) {
        this.id = id;
        this.desde = desde;
        this.hasta = hasta;
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

    @XmlTransient
    public List<Solicitudentregalotes> getSolicitudentregalotesList() {
        return solicitudentregalotesList;
    }

    public void setSolicitudentregalotesList(List<Solicitudentregalotes> solicitudentregalotesList) {
        this.solicitudentregalotesList = solicitudentregalotesList;
    }

    public Tiposbonos getTipoBono() {
        return tipoBono;
    }

    public void setTipoBono(Tiposbonos tipoBono) {
        this.tipoBono = tipoBono;
    }

    public Denominaciones getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Denominaciones denominacion) {
        this.denominacion = denominacion;
    }

    @XmlTransient
    public List<Bonosnofisicos> getBonosnofisicosList() {
        return bonosnofisicosList;
    }

    public void setBonosnofisicosList(List<Bonosnofisicos> bonosnofisicosList) {
        this.bonosnofisicosList = bonosnofisicosList;
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
        if (!(object instanceof Lotesbonos)) {
            return false;
        }
        Lotesbonos other = (Lotesbonos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Lotesbonos[ id=" + id + " ]";
    }
    
}
