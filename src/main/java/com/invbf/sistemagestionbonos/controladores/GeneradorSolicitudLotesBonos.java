/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class GeneradorSolicitudLotesBonos {

    private Solicitudentregalotesmaestro elemento;
    private List<Casinos> casinos;

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public GeneradorSolicitudLotesBonos() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("lotesdebonos");
        if (!sessionBean.perfilViewMatch("GenerarSolicitudLotesBonos")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
        
            System.out.println("Buscando info de la solictud si existe");
        if (sessionBean.getAttributes().containsKey("idsolicitudentregalotes")) {
            Integer id = (Integer) sessionBean.getAttributes().get("idsolicitudentregalotes");
            elemento = sessionBean.marketingFacade.getSolicitudentregalotesbono(id);
        } else {
            elemento = new Solicitudentregalotesmaestro();
            elemento.setSolicitudentregalotesList(new ArrayList<Solicitudentregalotes>(0));
            List<Lotesbonos> lotesbonoses = sessionBean.marketingFacade.getAllLotesBonos();
            List<Solicitudentregalotes> solicitudesentregalotes = new ArrayList<Solicitudentregalotes>();
            for (Lotesbonos lotesbonose : lotesbonoses) {
                Solicitudentregalotes s = new Solicitudentregalotes();
                s.setDenominacionesid(lotesbonose.getDenominacion());
                s.setIdCasino(lotesbonose.getIdCasino());
                s.setTiposBonosid(lotesbonose.getTipoBono());
                s.setDesde(lotesbonose.getHasta());
                solicitudesentregalotes.add(s);
            }
            elemento.setSolicitudentregalotesList(solicitudesentregalotes);
            System.out.println("Buscando Casinos");
            casinos = sessionBean.adminFacade.findAllCasinos();
            System.out.println("Encontrados Casinos");
        }
    }

    public Solicitudentregalotesmaestro getElemento() {
        return elemento;
    }

    public void setElemento(Solicitudentregalotesmaestro elemento) {
        this.elemento = elemento;
    }

    public void guardar() {
        elemento.setUsuariosidUsuario(sessionBean.getUsuario().getIdUsuario());
        sessionBean.marketingFacade.guardarSolicitudentregabonos(elemento);
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
}
