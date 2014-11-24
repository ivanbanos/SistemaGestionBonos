/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.exceptions.ClavesNoConcuerdanException;
import com.invbf.sistemagestionbonos.exceptions.NoCambioContrasenaException;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ideacentre
 */
@ManagedBean
@ViewScoped
public class CuentaUsuarioBean {
    
    @ManagedProperty("#{sessionBean}")
    private SessionBean sessionBean;
    
    private String contrasena;
    private String nueva;
    
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
    public CuentaUsuarioBean() {
    }

    @PostConstruct
    public void init() {
        sessionBean.checkUsuarioConectado();
        sessionBean.setActive("cuenta");
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }
    
    public void cambioContrasena(){
        try {
            sessionBean.sessionFacade.cambiarContrasena(contrasena, nueva, sessionBean.getUsuario());
            FacesUtil.addInfoMessage("Contraseña cambiada con exito!");
        } catch (ClavesNoConcuerdanException ex) {
            FacesUtil.addErrorMessage("Claves no concuerdan");
        } catch (NoCambioContrasenaException ex) {
            FacesUtil.addErrorMessage("No se pudo cambiar la contraseña");
        } finally {
            contrasena = "";
            nueva = "";
        }
    }
}
