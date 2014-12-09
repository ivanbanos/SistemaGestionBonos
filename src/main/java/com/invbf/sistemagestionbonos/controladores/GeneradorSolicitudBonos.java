/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Propositosentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
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
public class GeneradorSolicitudBonos {

    private Solicitudentrega elemento;
    private List<Casinos> casinos;
    private List<Tiposbonos> tiposbonos;
    private List<Propositosentrega> propositosentrega;
    private List<Usuarios> usuarios;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public GeneradorSolicitudBonos() {
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
            } catch (ParseException ex) {
                Logger.getLogger(GeneradorSolicitudBonos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        casinos = sessionBean.adminFacade.findAllCasinos();
        tiposbonos = sessionBean.adminFacade.findAllTiposbonos();
        usuarios = sessionBean.adminFacade.findAllUsuarios();
        propositosentrega = sessionBean.adminFacade.findAllPropositosentrega();
        
    }

    public Solicitudentrega getElemento() {
        return elemento;
    }

    public void setElemento(Solicitudentrega elemento) {
        this.elemento = elemento;
    }

    public void guardar() {
        sessionBean.marketingFacade.guardarSolicitudentrega(elemento);
        sessionBean.registrarlog("Generada solicitud Usuario:" + sessionBean.getUsuario().getNombreUsuario());
        FacesUtil.addInfoMessage("Solicitud guardada con exito!", "Notificación enviada");

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
}
