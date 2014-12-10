/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
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
public class CrudClientesSGBBean {

    private List<Clientessgb> lista;
    private Clientessgb elemento;
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;
    private List<Casinos> casinos;

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public CrudClientesSGBBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("que lo redirecciona?");

        sessionBean.checkUsuarioConectado();

        System.out.println("será esto?");
        sessionBean.setActive("solicitudbonos");
        System.out.println("esto?");
        if (sessionBean.perfilViewMatch("verTodosClientes")) {
            lista = sessionBean.adminFacade.findAllClientessgb();
        } else {
            lista = sessionBean.adminFacade.findClientessgbByCasino(sessionBean.getUsuario().getIdCasino());
        }

        System.out.println("o esto?");
        elemento = new Clientessgb();

        casinos = sessionBean.adminFacade.findAllCasinos();
        System.out.println("o esto?");
    }

    public List<Clientessgb> getLista() {
        return lista;
    }

    public void setLista(List<Clientessgb> lista) {
        this.lista = lista;
    }

    public Clientessgb getElemento() {
        return elemento;
    }

    public void setElemento(Clientessgb elemento) {
        this.elemento = elemento;
    }

    public void delete() {
        if (elemento.getSolicitudentregaclientesList().isEmpty()) {
            sessionBean.adminFacade.deleteClientessgb(elemento);
            if (sessionBean.perfilViewMatch("verTodosClientes")) {
                lista = sessionBean.adminFacade.findAllClientessgb();
            } else {
                lista = sessionBean.adminFacade.findClientessgbByCasino(sessionBean.getUsuario().getIdCasino());
            }
            FacesUtil.addInfoMessage("Cliente eliminado", elemento.getNombres() + " " + elemento.getApellidos());
            elemento = new Clientessgb();
        } else {
            FacesUtil.addInfoMessage("Cleinte no puede ser borrado", elemento.getNombres() + " " + elemento.getApellidos());
        }
    }

    public void guardar() {
        if (!sessionBean.perfilViewMatch("verTodosClientes")) {
            elemento.setIdCasino(sessionBean.getUsuario().getIdCasino().getIdCasino());
        }
        boolean opcion = sessionBean.adminFacade.guardarClientessgb(elemento);
        if (sessionBean.perfilViewMatch("verTodosClientes")) {
            lista = sessionBean.adminFacade.findAllClientessgb();
        } else {
            lista = sessionBean.adminFacade.findClientessgbByCasino(sessionBean.getUsuario().getIdCasino());
        }
        if (opcion) {
            FacesUtil.addInfoMessage("Cliente actualizado", elemento.getNombres() + " " + elemento.getApellidos());
        } else {
            FacesUtil.addInfoMessage("Cliente creado", elemento.getNombres() + " " + elemento.getApellidos());
        }
        elemento = new Clientessgb();
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

}
