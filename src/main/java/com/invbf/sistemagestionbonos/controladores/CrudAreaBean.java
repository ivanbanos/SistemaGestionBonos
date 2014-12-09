/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Areas;
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
public class CrudAreaBean {
    private List<Areas> lista;
    private Areas elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CrudAreaBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("atributosbonos");
        lista = sessionBean.adminFacade.findAllAreas();
        elemento = new Areas();
    }

    public List<Areas> getLista() {
        return lista;
    }

    public void setLista(List<Areas> lista) {
        this.lista = lista;
    }

    public Areas getElemento() {
        return elemento;
    }

    public void setElemento(Areas elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        if (elemento.getSolicitudentregaclientesList().isEmpty() ) {
            sessionBean.adminFacade.deleteAreas(elemento);
            lista = sessionBean.adminFacade.findAllAreas();
            FacesUtil.addInfoMessage("Area eliminada", elemento.getNombre()+ "");
            elemento = new Areas();
        } else {
            FacesUtil.addInfoMessage("Area no puede ser borrada", elemento.getNombre()+ "");
        }
    }

    public void guardar() {
        boolean opcion = sessionBean.adminFacade.guardarAreas(elemento);
        lista = sessionBean.adminFacade.findAllAreas();
        if (opcion) {
            FacesUtil.addInfoMessage("Area actualizada", elemento.getNombre()+ "");
        } else {
            FacesUtil.addInfoMessage("Area creada", elemento.getNombre()+ "");
        }
        elemento = new Areas();
    }
}
