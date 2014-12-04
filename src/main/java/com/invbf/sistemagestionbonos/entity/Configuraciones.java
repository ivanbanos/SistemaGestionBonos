/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "Configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuraciones.findAll", query = "SELECT c FROM Configuraciones c"),
    @NamedQuery(name = "Configuraciones.findById", query = "SELECT c FROM Configuraciones c WHERE c.id = :id"),
    @NamedQuery(name = "Configuraciones.findByTipo", query = "SELECT c FROM Configuraciones c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Configuraciones.findByValorString", query = "SELECT c FROM Configuraciones c WHERE c.valorString = :valorString"),
    @NamedQuery(name = "Configuraciones.findByValorInt", query = "SELECT c FROM Configuraciones c WHERE c.valorInt = :valorInt"),
    @NamedQuery(name = "Configuraciones.findByValorDate", query = "SELECT c FROM Configuraciones c WHERE c.valorDate = :valorDate"),
    @NamedQuery(name = "Configuraciones.findByValorFloat", query = "SELECT c FROM Configuraciones c WHERE c.valorFloat = :valorFloat")})
public class Configuraciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "valorString")
    private String valorString;
    @Column(name = "valorInt")
    private Integer valorInt;
    @Column(name = "valorDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorFloat")
    private Float valorFloat;

    public Configuraciones() {
    }

    public Configuraciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public Integer getValorInt() {
        return valorInt;
    }

    public void setValorInt(Integer valorInt) {
        this.valorInt = valorInt;
    }

    public Date getValorDate() {
        return valorDate;
    }

    public void setValorDate(Date valorDate) {
        this.valorDate = valorDate;
    }

    public Float getValorFloat() {
        return valorFloat;
    }

    public void setValorFloat(Float valorFloat) {
        this.valorFloat = valorFloat;
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
        if (!(object instanceof Configuraciones)) {
            return false;
        }
        Configuraciones other = (Configuraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Configuraciones[ id=" + id + " ]";
    }
    
}
