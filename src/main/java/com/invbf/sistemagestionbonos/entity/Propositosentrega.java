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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "PropositosEntrega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propositosentrega.findAll", query = "SELECT p FROM Propositosentrega p"),
    @NamedQuery(name = "Propositosentrega.findById", query = "SELECT p FROM Propositosentrega p WHERE p.id = :id"),
    @NamedQuery(name = "Propositosentrega.findByNombre", query = "SELECT p FROM Propositosentrega p WHERE p.nombre = :nombre")})
public class Propositosentrega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propositoEntrega")
    private List<Solicitudentrega> solicitudentregaList;

    public Propositosentrega() {
    }

    public Propositosentrega(Integer id) {
        this.id = id;
    }

    public Propositosentrega(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Solicitudentrega> getSolicitudentregaList() {
        return solicitudentregaList;
    }

    public void setSolicitudentregaList(List<Solicitudentrega> solicitudentregaList) {
        this.solicitudentregaList = solicitudentregaList;
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
        if (!(object instanceof Propositosentrega)) {
            return false;
        }
        Propositosentrega other = (Propositosentrega) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Propositosentrega[ id=" + id + " ]";
    }
    
}
