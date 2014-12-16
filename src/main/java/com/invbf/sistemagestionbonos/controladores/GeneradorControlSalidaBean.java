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
public class GeneradorControlSalidaBean {

    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;
    private Controlsalidabonos elemento;
    private List<ClienteMonto> clientesMontos;
    private List<Lotesbonos> lotesSol;
    private Usuarios usuario;
    private Usuariosdetalles usuariosdetalles;
    private List<Casinos> casinos;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public GeneradorControlSalidaBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("salidadebonos");
        if (!sessionBean.perfilViewMatch("ActSolicitudSalida")) {
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
        List<Solicitudentregaclientes> solec = sol.getSolicitudentregaclientesList();
        lotesSol = sessionBean.marketingFacade.getLotesBonosCasinoTipoBono(sol.getIdCasino(), sol.getTipoBono());
        clientesMontos = new ArrayList<ClienteMonto>();
        for (Solicitudentregaclientes solec1 : solec) {
            clientesMontos.add(new ClienteMonto(solec1.getClientessgb().getId(), solec1.getClientessgb().getNombres() + " " + solec1.getClientessgb().getApellidos(), solec1.getValorTotal(), lotesSol));
        }

        casinos = sessionBean.adminFacade.findAllCasinos();
    }

    public Controlsalidabonos getElemento() {
        return elemento;
    }

    public void setElemento(Controlsalidabonos elemento) {
        this.elemento = elemento;
    }

    public List<ClienteMonto> getClientesMontos() {
        return clientesMontos;
    }

    public void setClientesMontos(List<ClienteMonto> clientesMontos) {
        this.clientesMontos = clientesMontos;
    }

    public void guardar() {
        boolean isNotOk = false;
        List<ControlsalidabonosHasLotesbonos> controlsalidabonosHasLotesbonoses = new ArrayList<ControlsalidabonosHasLotesbonos>();

        for (ClienteMonto cm : clientesMontos) {
            if (!cm.getIsOk()) {
                isNotOk = true;
                break;
            }
            for (Lotesbonos lb : lotesSol) {
                ControlsalidabonosHasLotesbonos cslb = new ControlsalidabonosHasLotesbonos();
                cslb.setCantidad((cm.getDenominacionCant().get(cm.getDenominacionCant().indexOf(new DenoinacionCant(lb.getDenominacion())))).getCantidad() + "");
                cslb.setClientessgb(new Clientessgb(cm.getId()));
                cslb.setControlsalidabonos(elemento);
                cslb.setLotesbonos(lb);
                cslb.setControlsalidabonosHasLotesbonosPK(new ControlsalidabonosHasLotesbonosPK(elemento.getId(), lb.getId(), cm.getId()));
                controlsalidabonosHasLotesbonoses.add(cslb);
            }
        }
        if (!isNotOk) {
            elemento.setControlsalidabonosHasLotesbonosList(controlsalidabonosHasLotesbonoses);
            elemento.setEstado("SOLICITADA");
            sessionBean.marketingFacade.guardarControlSalidaBonos(elemento);
            FacesUtil.addInfoMessage("Se generó la solicitud con exito!", "Notificación enviada");
        } else {
            FacesUtil.addErrorMessage("No se puede guardar la solicitud!","Revise que los bonos asignados a los clientes concuerden con el monto");
        }
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Lotesbonos> getLotesSol() {
        return lotesSol;
    }

    public void setLotesSol(List<Lotesbonos> lotesSol) {
        this.lotesSol = lotesSol;
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
}
