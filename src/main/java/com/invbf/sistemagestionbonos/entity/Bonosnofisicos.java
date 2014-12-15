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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "BonosNoFisicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bonosnofisicos.findAll", query = "SELECT b FROM Bonosnofisicos b"),
    @NamedQuery(name = "Bonosnofisicos.findById", query = "SELECT b FROM Bonosnofisicos b WHERE b.id = :id"),
    @NamedQuery(name = "Bonosnofisicos.findByConsecutivo", query = "SELECT b FROM Bonosnofisicos b WHERE b.consecutivo = :consecutivo")})
public class Bonosnofisicos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "consecutivo")
    private String consecutivo;
    @JoinColumn(name = "LotesBonos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lotesbonos lotesBonosid;

    public Bonosnofisicos() {
    }

    public Bonosnofisicos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Lotesbonos getLotesBonosid() {
        return lotesBonosid;
    }

    public void setLotesBonosid(Lotesbonos lotesBonosid) {
        this.lotesBonosid = lotesBonosid;
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
        if (!(object instanceof Bonosnofisicos)) {
            return false;
        }
        Bonosnofisicos other = (Bonosnofisicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Bonosnofisicos[ id=" + id + " ]";
    }
    
}
