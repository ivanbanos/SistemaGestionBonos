/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import java.io.IOException;
import java.util.List;
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
public class ListaSolicitudBonosBean {

    private List<Solicitudentrega> lista;
    private Solicitudentrega elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;
    
    private List<Casinos> casinos;
    private List<Usuarios> usuarios;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public ListaSolicitudBonosBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("solicitudbonos");
        if (!sessionBean.perfilViewMatch("SolicitudLotes")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
        if (sessionBean.perfilViewMatch("AprobarSolicitudBono") || sessionBean.perfilViewMatch("PreAprobarSolicitudBono")) {

            lista = sessionBean.marketingFacade.getAllSolicitudentrega();
        } else {
            lista = sessionBean.marketingFacade.getAllSolicitudentregaSolicitante(sessionBean.getUsuario().getIdUsuario());
        }
        
        casinos = sessionBean.adminFacade.findAllCasinos();
        usuarios = sessionBean.adminFacade.findAllUsuarios();
    }

    public List<Solicitudentrega> getLista() {
        return lista;
    }

    public void setLista(List<Solicitudentrega> lista) {
        this.lista = lista;
    }

    public Solicitudentrega getElemento() {
        return elemento;
    }

    public void setElemento(Solicitudentrega elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        sessionBean.marketingFacade.deleteSolicitudentrega(elemento);
        FacesUtil.addInfoMessage("Solicitud eliminada", "");
        elemento = new Solicitudentrega();

        if (sessionBean.perfilViewMatch("AprobarSolicitudBono") || sessionBean.perfilViewMatch("PreAprobarSolicitudBono")) {
            lista = sessionBean.marketingFacade.getAllSolicitudentrega();
        } else {
            lista = sessionBean.marketingFacade.getAllSolicitudentregaSolicitante(sessionBean.getUsuario().getIdUsuario());
        }
    }

    public void goSolicitud(Integer i) {
        try {
            sessionBean.getAttributes().put("idSolicitudentrega", i);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GeneradorSolicitudBonos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaSolicitudesEntregaLotesBonosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goSolicitudAceptar(Integer i) {
        try {
            sessionBean.getAttributes().put("idsolicitudentregalotes", i);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AprobarSolicitudBonos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaSolicitudesEntregaLotesBonosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goSolicitudpreAceptar(Integer i) {
        try {
            sessionBean.getAttributes().put("idsolicitudentregalotes", i);
            FacesContext.getCurrentInstance().getExternalContext().redirect("PreAprobarSolicitudBonos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaSolicitudesEntregaLotesBonosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Casinos getCasinoById(Integer idCasino) {
        int casinoIndex = casinos.indexOf(new Casinos(idCasino));
        if (casinoIndex != -1) {
            return casinos.get(casinoIndex);
        }
        return new Casinos();
    }
    public Usuarios getUsuarioById(Integer idUsuario) {
        int casinoIndex = usuarios.indexOf(new Usuarios(idUsuario));
        if (casinoIndex != -1) {
            return usuarios.get(casinoIndex);
        }
        return new Usuarios();
    }
}
