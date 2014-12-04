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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "Denominaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Denominaciones.findAll", query = "SELECT d FROM Denominaciones d"),
    @NamedQuery(name = "Denominaciones.findById", query = "SELECT d FROM Denominaciones d WHERE d.id = :id"),
    @NamedQuery(name = "Denominaciones.findByValor", query = "SELECT d FROM Denominaciones d WHERE d.valor = :valor")})
public class Denominaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denominacion")
    private List<Lotesbonos> lotesbonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "denominacion")
    private List<Bono> bonoList;

    public Denominaciones() {
    }

    public Denominaciones(Integer id) {
        this.id = id;
    }

    public Denominaciones(Integer id, float valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<Lotesbonos> getLotesbonosList() {
        return lotesbonosList;
    }

    public void setLotesbonosList(List<Lotesbonos> lotesbonosList) {
        this.lotesbonosList = lotesbonosList;
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
        if (!(object instanceof Denominaciones)) {
            return false;
        }
        Denominaciones other = (Denominaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Denominaciones[ id=" + id + " ]";
    }
    
}
