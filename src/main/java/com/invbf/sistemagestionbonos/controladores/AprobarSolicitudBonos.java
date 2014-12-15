/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Areas;
import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entity.Propositosentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ivan
 */
@ManagedBean
@ViewScoped
public class AprobarSolicitudBonos {
    
    private Solicitudentrega elemento;
    private List<Casinos> casinos;
    private List<Tiposbonos> tiposbonos;
    private List<Propositosentrega> propositosentrega;
    private List<Usuarios> usuarios;
    private List<Usuariosdetalles> usuariosdetalleses;
    private List<Areas> areas;
    private List<Clientessgb> clientessgbs;
    private String nombres;
    private String apellidos;
    private List<Clientessgb> selectedClientessgbs;
    private List<Solicitudentregaclientes> solicitudentregaclienteses;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public AprobarSolicitudBonos() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("solicitudbonos");
        if (!sessionBean.perfilViewMatch("GenerarSolicitudBono")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }

        System.out.println("Buscando info de la solictud si existe");
        if (sessionBean.getAttributes().containsKey("idSolicitudentrega") && (Integer) sessionBean.getAttributes().get("idSolicitudentrega") != 0) {
            Integer id = (Integer) sessionBean.getAttributes().get("idSolicitudentrega");
            elemento = sessionBean.marketingFacade.getSolicitudbono(id);
            solicitudentregaclienteses = elemento.getSolicitudentregaclientesList();
        } else {
            try {
                elemento = new Solicitudentrega();
                elemento.setEstado("EN CREACION");
                DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
                DateFormat df2 = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
                TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
                df.setTimeZone(timeZone);
                Calendar nowDate = Calendar.getInstance();
                nowDate.setTime(df2.parse(df.format(nowDate.getTime())));
                elemento.setFecha(nowDate.getTime());
                elemento.setIdCasino(sessionBean.getUsuario().getIdCasino().getIdCasino());
                elemento.setPropositoEntrega(new Propositosentrega());
                elemento.setSolicitante(sessionBean.getUsuario().getIdUsuario());
                elemento.setTipoBono(new Tiposbonos());
                elemento.setSolicitudentregaclientesList(new ArrayList<Solicitudentregaclientes>());
                solicitudentregaclienteses = new ArrayList<Solicitudentregaclientes>();
            } catch (ParseException ex) {
                Logger.getLogger(GeneradorSolicitudBonos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        casinos = sessionBean.adminFacade.findAllCasinos();
        tiposbonos = sessionBean.adminFacade.findAllTiposbonos();
        usuarios = sessionBean.adminFacade.findAllUsuarios();
        propositosentrega = sessionBean.adminFacade.findAllPropositosentrega();
        usuariosdetalleses = sessionBean.adminFacade.findAllUsuariosdetlles();
        areas = sessionBean.adminFacade.findAllAreas();
        clientessgbs = new ArrayList<Clientessgb>();
        selectedClientessgbs = new ArrayList<Clientessgb>();
        busquedaClientes();

    }

    public Solicitudentrega getElemento() {
        return elemento;
    }

    public void setElemento(Solicitudentrega elemento) {

        boolean cambiocasino = this.elemento.getIdCasino().equals(elemento.getIdCasino());

        this.elemento = elemento;
        if (cambiocasino) {
            this.elemento.getSolicitudentregaclientesList().clear();
            busquedaClientes();
        }
    }

    public void guardar() {
        elemento.setEstado("APROBADA");
        elemento.setSolicitudentregaclientesList(solicitudentregaclienteses);
        sessionBean.marketingFacade.guardarSolicitudentrega(elemento);
        sessionBean.marketingFacade.crearSolicitudSalidaBonos(elemento);
        sessionBean.registrarlog("Preaprobada solicitud Usuario:" + sessionBean.getUsuario().getNombreUsuario());
        FacesUtil.addInfoMessage("Solicitud aprobada con exito!", "");
    }

    public Casinos getCasinoById(Integer idCasino) {
        int casinoIndex = casinos.indexOf(new Casinos(idCasino));
        if (casinoIndex != -1) {
            return casinos.get(casinoIndex);
        }
        return new Casinos();
    }

    public String getNombreDeUsuario(Integer id) {
        return sessionBean.sessionFacade.getNombreDeUsuario(id);
    }

    public List<Casinos> getCasinos() {
        return casinos;
    }

    public void setCasinos(List<Casinos> casinos) {
        this.casinos = casinos;
    }

    public List<Tiposbonos> getTiposbonos() {
        return tiposbonos;
    }

    public void setTiposbonos(List<Tiposbonos> tiposbonos) {
        this.tiposbonos = tiposbonos;
    }

    public List<Propositosentrega> getPropositosentrega() {
        return propositosentrega;
    }

    public void setPropositosentrega(List<Propositosentrega> propositosentrega) {
        this.propositosentrega = propositosentrega;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios getUsuarioById(Integer idUsuario) {
        int casinoIndex = usuarios.indexOf(new Usuarios(idUsuario));
        if (casinoIndex != -1) {
            return usuarios.get(casinoIndex);
        }
        return new Usuarios();
    }

    public Usuariosdetalles getUsuariodetalleById(Integer idUsuario) {
        int casinoIndex = usuariosdetalleses.indexOf(new Usuariosdetalles(idUsuario));
        if (casinoIndex != -1) {
            return usuariosdetalleses.get(casinoIndex);
        }
        return new Usuariosdetalles();
    }

    public List<Usuariosdetalles> getUsuariosdetalleses() {
        return usuariosdetalleses;
    }

    public void setUsuariosdetalleses(List<Usuariosdetalles> usuariosdetalleses) {
        this.usuariosdetalleses = usuariosdetalleses;
    }

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
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

    public List<Clientessgb> getClientessgbs() {
        return clientessgbs;
    }

    public void setClientessgbs(List<Clientessgb> clientessgbs) {
        this.clientessgbs = clientessgbs;
    }

    public void busquedaClientes() {
        clientessgbs = sessionBean.adminFacade.findClientessgbByCasino(new Casinos(elemento.getIdCasino()));
    }

    public List<Clientessgb> getSelectedClientessgbs() {
        return selectedClientessgbs;
    }

    public void setSelectedClientessgbs(List<Clientessgb> selectedClientessgbs) {
        this.selectedClientessgbs = selectedClientessgbs;
    }

    public List<Solicitudentregaclientes> getSolicitudentregaclienteses() {
        return solicitudentregaclienteses;
    }

    public void setSolicitudentregaclienteses(List<Solicitudentregaclientes> solicitudentregaclienteses) {
        this.solicitudentregaclienteses = solicitudentregaclienteses;
    }

    public void setSalatoCliente(Integer idSala, Integer indexCliente) {
        this.solicitudentregaclienteses.get(indexCliente).setAreaid(new Areas(idSala));
    }
}
