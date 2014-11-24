/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entitySGC;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "vistas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistas.findAll", query = "SELECT v FROM Vistas v"),
    @NamedQuery(name = "Vistas.findByIdVista", query = "SELECT v FROM Vistas v WHERE v.idVista = :idVista"),
    @NamedQuery(name = "Vistas.findByNombreVista", query = "SELECT v FROM Vistas v WHERE v.nombreVista = :nombreVista")})
public class Vistas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVista")
    private Integer idVista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreVista")
    private String nombreVista;
    @ManyToMany(mappedBy = "vistasList")
    private List<Perfiles> perfilesList;

    public Vistas() {
    }

    public Vistas(Integer idVista) {
        this.idVista = idVista;
    }

    public Vistas(Integer idVista, String nombreVista) {
        this.idVista = idVista;
        this.nombreVista = nombreVista;
    }

    public Integer getIdVista() {
        return idVista;
    }

    public void setIdVista(Integer idVista) {
        this.idVista = idVista;
    }

    public String getNombreVista() {
        return nombreVista;
    }

    public void setNombreVista(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    @XmlTransient
    public List<Perfiles> getPerfilesList() {
        return perfilesList;
    }

    public void setPerfilesList(List<Perfiles> perfilesList) {
        this.perfilesList = perfilesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVista != null ? idVista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vistas)) {
            return false;
        }
        Vistas other = (Vistas) object;
        if ((this.idVista == null && other.idVista != null) || (this.idVista != null && !this.idVista.equals(other.idVista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entitySGC.Vistas[ idVista=" + idVista + " ]";
    }
    
}
