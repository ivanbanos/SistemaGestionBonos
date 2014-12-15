/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Controlsalidabonos;
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
public class ListaSolicitudesdeSalidadeBonos {

    private List<Controlsalidabonos> lista;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public ListaSolicitudesdeSalidadeBonos() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("salidadebonos");
        if (!sessionBean.perfilViewMatch("Controlsalidabonos")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
        lista = sessionBean.marketingFacade.getAllControlsalidabonos();
    }

    public List<Controlsalidabonos> getLista() {
        return lista;
    }

    public void setLista(List<Controlsalidabonos> lista) {
        this.lista = lista;
    }

    public void goSolicitud(Integer i) {
        try {
            sessionBean.getAttributes().put("idsolicitudsalida", i);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GeneradorSolicitudControlsalidabonos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaSolicitudesEntregaLotesBonosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goSolicitudAceptar(Integer i) {
        try {
            sessionBean.getAttributes().put("idsolicitudsalida", i);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AceptarSolicitudControlsalidabonos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListaSolicitudesEntregaLotesBonosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
