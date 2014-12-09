/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Propositosentrega;
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
public class CrudPropositos {
    private List<Propositosentrega> lista;
    private Propositosentrega elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CrudPropositos() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("atributosbonos");
        lista = sessionBean.adminFacade.findAllPropositosentrega();
        elemento = new Propositosentrega();
    }

    public List<Propositosentrega> getLista() {
        return lista;
    }

    public void setLista(List<Propositosentrega> lista) {
        this.lista = lista;
    }

    public Propositosentrega getElemento() {
        return elemento;
    }

    public void setElemento(Propositosentrega elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        if (elemento.getSolicitudentregaList().isEmpty() ) {
            sessionBean.adminFacade.deletePropositosentrega(elemento);
            lista = sessionBean.adminFacade.findAllPropositosentrega();
            FacesUtil.addInfoMessage("Proposito de entrega eliminado", elemento.getNombre()+ "");
            elemento = new Propositosentrega();
        } else {
            FacesUtil.addInfoMessage("Proposito de entrega no puede ser borrado", elemento.getNombre()+ "");
        }
    }

    public void guardar() {
        boolean opcion = sessionBean.adminFacade.guardarPropositosentrega(elemento);
        lista = sessionBean.adminFacade.findAllPropositosentrega();
        if (opcion) {
            FacesUtil.addInfoMessage("Proposito de entrega actualizado", elemento.getNombre()+ "");
        } else {
            FacesUtil.addInfoMessage("Proposito de entrega creado", elemento.getNombre()+ "");
        }
        elemento = new Propositosentrega();
    }
}
