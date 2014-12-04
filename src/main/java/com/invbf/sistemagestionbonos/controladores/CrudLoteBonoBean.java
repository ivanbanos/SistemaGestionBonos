/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Denominaciones;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.exceptions.ExistenBonosFisicosException;
import com.invbf.sistemagestionbonos.exceptions.LoteBonosExistenteException;
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
public class CrudLoteBonoBean {

    private List<Lotesbonos> lista;
    private Lotesbonos elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    private List<Casinos> casinos;
    private List<Denominaciones> denominaciones;
    private List<Tiposbonos> tiposbonos;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CrudLoteBonoBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("lotesdebonos");
        if (!sessionBean.perfilViewMatch("LoteBono")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
        lista = sessionBean.marketingFacade.getAllLotesBonos();
        elemento = new Lotesbonos();
        elemento.setDenominacion(new Denominaciones());
        elemento.setTipoBono(new Tiposbonos());
        casinos = sessionBean.adminFacade.findAllCasinos();
        denominaciones = sessionBean.adminFacade.findAllDenominaciones();
        tiposbonos = sessionBean.adminFacade.findAllTiposbonos();
    }

    public List<Lotesbonos> getLista() {
        return lista;
    }

    public void setLista(List<Lotesbonos> lista) {
        this.lista = lista;
    }

    public Lotesbonos getElemento() {
        return elemento;
    }

    public void setElemento(Lotesbonos elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        try {
            sessionBean.marketingFacade.borrarLote(elemento);
            FacesUtil.addInfoMessage("Lote eliminado con exito!", "");

            lista = sessionBean.marketingFacade.getAllLotesBonos();
            elemento = new Lotesbonos();
            elemento.setDenominacion(new Denominaciones());
            elemento.setTipoBono(new Tiposbonos());
        } catch (ExistenBonosFisicosException ex) {
            FacesUtil.addErrorMessage("No se eliminó el lote de bonos", "Existen bonos fisicos");
        }
    }

    public void guardar() {
        try {
            elemento.setDesde("0000-A");
            elemento.setHasta("0000-A");
            sessionBean.marketingFacade.guardarLote(elemento);
            FacesUtil.addInfoMessage("Lote creado con exito!", "");

            lista = sessionBean.marketingFacade.getAllLotesBonos();
            elemento = new Lotesbonos();
            elemento.setDenominacion(new Denominaciones());
            elemento.setTipoBono(new Tiposbonos());
        } catch (LoteBonosExistenteException ex) {
            FacesUtil.addErrorMessage("No se pudo crear el lote de bonos", "Existe un lote de bonos con la misma combinacion de casino, denominación y tipo de bono");
        }
    }

    public Casinos getCasinoById(Integer idCasino) {
        int casinoIndex = casinos.indexOf(new Casinos(idCasino));
        if (casinoIndex != -1) {
            return casinos.get(casinoIndex);
        }
        return new Casinos();
    }

    public List<Casinos> getCasinos() {
        return casinos;
    }

    public void setCasinos(List<Casinos> casinos) {
        this.casinos = casinos;
    }

    public List<Denominaciones> getDenominaciones() {
        return denominaciones;
    }

    public void setDenominaciones(List<Denominaciones> denominaciones) {
        this.denominaciones = denominaciones;
    }

    public List<Tiposbonos> getTiposbonos() {
        return tiposbonos;
    }

    public void setTiposbonos(List<Tiposbonos> tiposbonos) {
        this.tiposbonos = tiposbonos;
    }

}
