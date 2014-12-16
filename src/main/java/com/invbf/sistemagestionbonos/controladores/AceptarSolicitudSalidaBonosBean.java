/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entity.Controlsalidabonos;
import com.invbf.sistemagestionbonos.entity.ControlsalidabonosHasLotesbonos;
import com.invbf.sistemagestionbonos.entity.ControlsalidabonosHasLotesbonosPK;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.util.ClienteMonto;
import com.invbf.sistemagestionbonos.util.DenoinacionCant;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import com.invbf.sistemagestionbonos.util.LoteBonoCant;
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
public class AceptarSolicitudSalidaBonosBean {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;
    private Controlsalidabonos elemento;
    private List<LoteBonoCant> loteBonoCants;
    private float total;
    private Usuarios usuario;
    private Usuariosdetalles usuariosdetalles;
    private List<Casinos> casinos;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public AceptarSolicitudSalidaBonosBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("salidadebonos");
        if (!sessionBean.perfilViewMatch("AceptarSolicitudSalida")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }

        Integer id = (Integer) sessionBean.getAttributes().get("idsolicitudsalida");
        if (sessionBean.getAttributes().containsKey("idsolicitudsalida") && (Integer) sessionBean.getAttributes().get("idsolicitudsalida") != 0) {
            elemento = sessionBean.marketingFacade.getSolicitudSalida(id);
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
        if (elemento.getSolicitante() == null || elemento.getSolicitante() == 0) {
            try {
                elemento.setSolicitante(sessionBean.getUsuario().getIdUsuario());
                DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
                DateFormat df2 = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
                TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
                df.setTimeZone(timeZone);
                Calendar nowDate = Calendar.getInstance();
                nowDate.setTime(df2.parse(df.format(nowDate.getTime())));
                elemento.setFecha(nowDate.getTime());
            } catch (ParseException ex) {
                Logger.getLogger(GeneradorControlSalidaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        usuario = sessionBean.adminFacade.findUsuario(elemento.getSolicitante());
        usuariosdetalles = sessionBean.adminFacade.findUsuariosdetlles(elemento.getSolicitante());
        Solicitudentrega sol = elemento.getSolicitudEntregaid();
        loteBonoCants = new ArrayList<LoteBonoCant>();
        List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonoses = elemento.getControlsalidabonosHasLotesbonosList();
        for (ControlsalidabonosHasLotesbonos controlsalidabonosHasLotesbonos : controlsalidabonosHasLotesbonoses) {
            if (!loteBonoCants.contains(new LoteBonoCant(controlsalidabonosHasLotesbonos.getLotesbonos()))) {
                loteBonoCants.add(new LoteBonoCant(controlsalidabonosHasLotesbonos.getLotesbonos(), Long.parseLong(controlsalidabonosHasLotesbonos.getCantidad())));
            } else {
                loteBonoCants.get(loteBonoCants.indexOf(new LoteBonoCant(controlsalidabonosHasLotesbonos.getLotesbonos()))).sumCantidad(Long.parseLong(controlsalidabonosHasLotesbonos.getCantidad()));
            }
            total += (Integer.parseInt(controlsalidabonosHasLotesbonos.getCantidad()) * controlsalidabonosHasLotesbonos.getLotesbonos().getDenominacion().getValor());
        }

        casinos = sessionBean.adminFacade.findAllCasinos();
    }

    public Controlsalidabonos getElemento() {
        return elemento;
    }

    public void setElemento(Controlsalidabonos elemento) {
        this.elemento = elemento;
    }

    public void guardar() {
        elemento.setEstado("ACEPTADA");
        sessionBean.marketingFacade.guardarControlSalidaBonos(elemento);
        FacesUtil.addInfoMessage("Se aceptó la solicitud!", "Notificación enviada");

    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public Usuariosdetalles getUsuariosdetalles() {
        return usuariosdetalles;
    }

    public void setUsuariosdetalles(Usuariosdetalles usuariosdetalles) {
        this.usuariosdetalles = usuariosdetalles;
    }

    public Casinos getCasinoById(Integer idCasino) {
        int casinoIndex = casinos.indexOf(new Casinos(idCasino));
        if (casinoIndex != -1) {
            return casinos.get(casinoIndex);
        }
        return new Casinos();
    }

    public List<LoteBonoCant> getLoteBonoCants() {
        return loteBonoCants;
    }

    public void setLoteBonoCants(List<LoteBonoCant> loteBonoCants) {
        this.loteBonoCants = loteBonoCants;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
