/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ivan
 */
@ManagedBean
@ViewScoped
public class CrudTipoBonoBean {

    private List<Tiposbonos> lista;
    private Tiposbonos elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CrudTipoBonoBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("atributosbonos");
        lista = sessionBean.adminFacade.findAllTiposbonos();
        elemento = new Tiposbonos();
    }

    public List<Tiposbonos> getLista() {
        return lista;
    }

    public void setLista(List<Tiposbonos> lista) {
        this.lista = lista;
    }

    public Tiposbonos getElemento() {
        return elemento;
    }

    public void setElemento(Tiposbonos elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        if (elemento.getBonoList().isEmpty() && elemento.getLotesbonosList().isEmpty()) {
            sessionBean.adminFacade.deleteTiposbonos(elemento);
            lista = sessionBean.adminFacade.findAllTiposbonos();
            FacesUtil.addInfoMessage("Tipo bono eliminado", elemento.getNombre() + "");
            elemento = new Tiposbonos();
        } else {
            FacesUtil.addInfoMessage("Tipo bono no puede ser borrado", elemento.getNombre() + "");
        }
    }

    public void guardar() {
        boolean opcion = sessionBean.adminFacade.guardarTiposbonos(elemento);
        lista = sessionBean.adminFacade.findAllTiposbonos();
        if (opcion) {
            FacesUtil.addInfoMessage("Tipo bono actualizado", elemento.getNombre() + "");
        } else {
            FacesUtil.addInfoMessage("tipo bono creado", elemento.getNombre() + "");
        }
        elemento = new Tiposbonos();
    }
}
