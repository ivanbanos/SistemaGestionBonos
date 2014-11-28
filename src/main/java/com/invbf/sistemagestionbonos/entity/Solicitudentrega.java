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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivan
 */
@Entity
@Table(name = "solicitudentrega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudentrega.findAll", query = "SELECT s FROM Solicitudentrega s"),
    @NamedQuery(name = "Solicitudentrega.findById", query = "SELECT s FROM Solicitudentrega s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudentrega.findByFecha", query = "SELECT s FROM Solicitudentrega s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitudentrega.findBySolicitante", query = "SELECT s FROM Solicitudentrega s WHERE s.solicitante = :solicitante"),
    @NamedQuery(name = "Solicitudentrega.findByAprobador", query = "SELECT s FROM Solicitudentrega s WHERE s.aprobador = :aprobador"),
    @NamedQuery(name = "Solicitudentrega.findByJustificacion", query = "SELECT s FROM Solicitudentrega s WHERE s.justificacion = :justificacion"),
    @NamedQuery(name = "Solicitudentrega.findByEstado", query = "SELECT s FROM Solicitudentrega s WHERE s.estado = :estado")})
public class Solicitudentrega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "solicitante")
    private Integer solicitante;
    @Column(name = "aprobador")
    private Integer aprobador;
    @Size(max = 400)
    @Column(name = "justificacion")
    private String justificacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudentrega")
    private List<Solicitudentregaclientes> solicitudentregaclientesList;
    @JoinColumn(name = "TipoBono", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tiposbonos tipoBono;
    @JoinColumn(name = "PropositoEntrega", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Propositosentrega propositoEntrega;

    public Solicitudentrega() {
    }

    public Solicitudentrega(Integer id) {
        this.id = id;
    }

    public Solicitudentrega(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
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

    public Integer getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Integer solicitante) {
        this.solicitante = solicitante;
    }

    public Integer getAprobador() {
        return aprobador;
    }

    public void setAprobador(Integer aprobador) {
        this.aprobador = aprobador;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Solicitudentregaclientes> getSolicitudentregaclientesList() {
        return solicitudentregaclientesList;
    }

    public void setSolicitudentregaclientesList(List<Solicitudentregaclientes> solicitudentregaclientesList) {
        this.solicitudentregaclientesList = solicitudentregaclientesList;
    }

    public Tiposbonos getTipoBono() {
        return tipoBono;
    }

    public void setTipoBono(Tiposbonos tipoBono) {
        this.tipoBono = tipoBono;
    }

    public Propositosentrega getPropositoEntrega() {
        return propositoEntrega;
    }

    public void setPropositoEntrega(Propositosentrega propositoEntrega) {
        this.propositoEntrega = propositoEntrega;
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
        if (!(object instanceof Solicitudentrega)) {
            return false;
        }
        Solicitudentrega other = (Solicitudentrega) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Solicitudentrega[ id=" + id + " ]";
    }
    
}
