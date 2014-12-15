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
@Table(name = "ClientesSGB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientessgb.findAll", query = "SELECT c FROM Clientessgb c"),
    @NamedQuery(name = "Clientessgb.findById", query = "SELECT c FROM Clientessgb c WHERE c.id = :id"),
    @NamedQuery(name = "Clientessgb.findByNombres", query = "SELECT c FROM Clientessgb c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "Clientessgb.findByApellidos", query = "SELECT c FROM Clientessgb c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Clientessgb.findByIdCliente", query = "SELECT c FROM Clientessgb c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Clientessgb.findByIdCasino", query = "SELECT c FROM Clientessgb c WHERE c.idCasino = :idCasino")})
public class Clientessgb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "idCliente")
    private Integer idCliente;
    @Column(name = "idCasino")
    private Integer idCasino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientessgb")
    private List<Solicitudentregaclientes> solicitudentregaclientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientessgb")
    private List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Bono> bonoList;

    public Clientessgb() {
    }

    public Clientessgb(Integer id) {
        this.id = id;
    }

    public Clientessgb(Integer id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCasino() {
        return idCasino;
    }

    public void setIdCasino(Integer idCasino) {
        this.idCasino = idCasino;
    }

    @XmlTransient
    public List<Solicitudentregaclientes> getSolicitudentregaclientesList() {
        return solicitudentregaclientesList;
    }

    public void setSolicitudentregaclientesList(List<Solicitudentregaclientes> solicitudentregaclientesList) {
        this.solicitudentregaclientesList = solicitudentregaclientesList;
    }

    @XmlTransient
    public List<ControlsalidabonosHasLotesbonos> getControlsalidabonosHasLotesbonosList() {
        return controlsalidabonosHasLotesbonosList;
    }

    public void setControlsalidabonosHasLotesbonosList(List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonosList) {
        this.controlsalidabonosHasLotesbonosList = controlsalidabonosHasLotesbonosList;
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
        if (!(object instanceof Clientessgb)) {
            return false;
        }
        Clientessgb other = (Clientessgb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invbf.sistemagestionbonos.entity.Clientessgb[ id=" + id + " ]";
    }
    
}
