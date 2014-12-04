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
        if (!sessionBean.perfilFormMatch("SolicitudLotes","crear")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }

        System.out.println("Buscando info de la solictud si existe");
        if (sessionBean.getAttributes().containsKey("idsolicitudentregalotes")&&(Integer)sessionBean.getAttributes().get("idsolicitudentregalotes")!=0) {
            Integer id = (Integer) sessionBean.getAttributes().get("idsolicitudentregalotes");
            elemento = sessionBean.marketingFacade.getSolicitudentregalotesbono(id);
        } else {
            elemento = new Solicitudentregalotesmaestro();
            elemento.setSolicitudentregalotesList(new ArrayList<Solicitudentregalotes>(0));
            List<Lotesbonos> lotesbonoses = sessionBean.marketingFacade.getAllLotesBonos();
            List<Solicitudentregalotes> solicitudesentregalotes = new ArrayList<Solicitudentregalotes>();
            for (Lotesbonos lotesbonose : lotesbonoses) {
                Solicitudentregalotes s = new Solicitudentregalotes();
                s.setLotesBonosid(lotesbonose);
                s.setCantidad(0);
                solicitudesentregalotes.add(s);
            }
            elemento.setSolicitudentregalotesList(solicitudesentregalotes);
            System.out.println("Buscando Casinos");
            System.out.println("Encontrados Casinos");
        }
        
        casinos = sessionBean.adminFacade.findAllCasinos();
    }

    public Solicitudentregalotesmaestro getElemento() {
        return elemento;
    }

    public void setElemento(Solicitudentregalotesmaestro elemento) {
        this.elemento = elemento;
    }

    public void guardar() {
        try {
            elemento.setRemitente(sessionBean.getUsuario().getIdUsuario());
            elemento.setEstado("CREADA");
            DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            DateFormat df2 = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
            df.setTimeZone(timeZone);
            Calendar nowDate = Calendar.getInstance();
            nowDate.setTime(df2.parse(df.format(nowDate.getTime())));
            elemento.setFecha(nowDate.getTime());
            sessionBean.marketingFacade.guardarSolicitudentregabonos(elemento);
            sessionBean.registrarlog("Generada solicitud Usuario:" + sessionBean.getUsuario().getNombreUsuario());
            FacesUtil.addInfoMessage("Solicitud guardada con exito!", "Notificación enviada");
        } catch (ParseException ex) {
            Logger.getLogger(GeneradorSolicitudLotesBonos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Casinos getCasinoById(Integer idCasino) {
        int casinoIndex = casinos.indexOf(new Casinos(idCasino));
        if (casinoIndex != -1) {
            return casinos.get(casinoIndex);
        }
        return new Casinos();
    }
    public String getNombreDeUsuario(Integer id){
        return sessionBean.sessionFacade.getNombreDeUsuario(id);
    }
    
}
