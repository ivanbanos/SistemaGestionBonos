/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.controladores;

import com.invbf.sistemagestionbonos.entity.Configuraciones;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Formularios;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.entitySGC.Vistas;
import com.invbf.sistemagestionbonos.exceptions.ClavesNoConcuerdanException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioInactivoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoConectadoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoExisteException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioSinAccesoalSistemaException;
import com.invbf.sistemagestionbonos.facade.AdminFacade;
import com.invbf.sistemagestionbonos.facade.MarketingFacade;
import com.invbf.sistemagestionbonos.facade.SystemFacade;
import com.invbf.sistemagestionbonos.facade.impl.AdminFacadeImpl;
import com.invbf.sistemagestionbonos.facade.impl.MarketingFacadeImpl;
import com.invbf.sistemagestionbonos.facade.impl.SystemFacadeImpl;
import com.invbf.sistemagestionbonos.observer.Observer;
import com.invbf.sistemagestionbonos.observer.Subject;
import com.invbf.sistemagestionbonos.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ideacentre
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable, Subject {

    SystemFacade sessionFacade;
    AdminFacade adminFacade;
    MarketingFacade marketingFacade;
    private Usuarios usuario;//Almacena el objeto usuario de la session
    private Usuariosdetalles usuarioDetalle;//Almacena el objeto usuario de la session
    private HashMap<String, Object> Attributes;
    private List<Observer> observers;
    private int paginacion;
    private String active;

    /**
     * Creates a new instance of SessionFlowumiUtil
     */
    public SessionBean() {
    }

    @PostConstruct
    public void init() {
        sessionFacade = new SystemFacadeImpl();
        adminFacade = new AdminFacadeImpl();
        marketingFacade = new MarketingFacadeImpl();
        usuario = new Usuarios();
        Attributes = new HashMap<String, Object>();
        observers = new ArrayList<Observer>();
        Configuraciones configuracion = sessionFacade.getConfiguracionByName("paginacion");
        if (configuracion == null) {
            paginacion = 10;
        } else {
            paginacion = Integer.parseInt(configuracion.getValorString());
        }
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String Conectar() {
        try {
            usuario = sessionFacade.iniciarSession(usuario);
            usuarioDetalle = sessionFacade.getDetalleUsuario(usuario.getIdUsuario());
            sessionFacade.registrarlog("Inicio de sesion del usuario " + usuario.getNombreUsuario());
            active = "inicio";
            return "/pages/index.xhtml";
        } catch (ClavesNoConcuerdanException ex) {
            FacesUtil.addErrorMessage("Usuario no conectado", ex.getMessage());
            usuario = new Usuarios();
        } catch (UsuarioNoExisteException ex) {
            FacesUtil.addErrorMessage("Usuario no conectado", ex.getMessage());
            usuario = new Usuarios();
        } catch (UsuarioNoConectadoException ex) {
            FacesUtil.addErrorMessage("Usuario no conectado", ex.getMessage());
            usuario = new Usuarios();
        }catch (UsuarioInactivoException ex) {
            FacesUtil.addErrorMessage("Usuario inactivo", ex.getMessage());
            usuario = new Usuarios();
        } catch (UsuarioSinAccesoalSistemaException ex) {
            FacesUtil.addErrorMessage("Usuario sin acceso", ex.getMessage());
            usuario = new Usuarios();
        }
        return "";
    }

    public String Desconectar() {
        usuario = new Usuarios();
        return "/pages/InicioSession.xhtml";
    }

    public boolean perfilViewMatch(String vista) {
        if (usuario == null || usuario.getIdPerfil() == null || usuario.getIdPerfil().getVistasList() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        } else {
            List<Vistas> vistasUsuario = usuario.getIdPerfil().getVistasList();
            for (Vistas v : vistasUsuario) {
                if (v.getNombreVista().equals(vista)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean perfilFormMatch(String tabla, String accion) {
        if (usuario == null || usuario.getIdPerfil() == null || usuario.getIdPerfil().getFormulariosList() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        } else {
            for (Formularios f : usuario.getIdPerfil().getFormulariosList()) {
                if (f.es(tabla + accion)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void actualizarUsuario() {
        usuario = sessionFacade.actualizarUsuario(usuario);
    }

    public HashMap<String, Object> getAttributes() {
        return Attributes;
    }

    public void setAttributes(HashMap<String, Object> Attributes) {
        this.Attributes = Attributes;
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObserver(String tabla) {
        Iterator<Observer> i = observers.iterator();
        while (i.hasNext()) {
            Observer o = i.next();
            if (o == null) {
                i.remove();
            } else {
                o.update();
            }

        }
    }

    public void registrarlog(String mensaje) {
        sessionFacade.registrarlog(mensaje);
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    public void checkUsuarioConectado() {
        if (usuario == null
                || usuario.getIdUsuario() == null
                || usuario.getIdUsuario() <= 0) {
            try {
                System.out.println("No lo coje");
                Desconectar();
                FacesUtil.addErrorMessage("Session finalizada", "No existe usuario conectado");
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSession.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public boolean isActive(String pestaña) {
        if (active == null) {
            return false;
        }
        return active.equals(pestaña);
    }

    public boolean isNotActive(String pestaña) {
        if (active == null) {
            return true;
        }
        return !active.equals(pestaña);
    }

    public String go(String page) {
        System.out.println(page);
        
        if (page.equals("ClientesSGB")) {
            active = "solicitudbonos";
            return "/pages/ClientesSGB.xhtml";
        }else if (page.equals("inicio")) {
            active = "inicio";
            return "/pages/index.xhtml";
        } else if (page.equals("cuenta")) {
            active = "cuenta";
            return "/pages/CuentaUsuarios.xhtml";
        } else if (page.equals("atributosbonos")) {
            active = "atributosbonos";
            return "/pages/AtributosBonos.xhtml";
        } else if (page.equals("GenerarSolicitudLotesBonos")) {
            active = "lotesdebonos";
            return "/pages/GeneradorSolicitudLoteBono.xhtml";
        }else if (page.equals("listasolicitudlotes")) {
            active = "lotesdebonos";
            return "/pages/ListaSolicitudLotesBonosView.xhtml";
        }else if (page.equals("LoteBono")) {
            active = "lotesdebonos";
            return "/pages/AdminLotesBonos.xhtml";
        }else if (page.equals("solicitudesbonos")) {
            active = "solicitudbonos";
            return "/pages/ListaSolicitudBono.xhtml";
        }else if (page.equals("ControlSalidaBono")) {
            active = "salidadebonos";
            return "/pages/ListaSolicitudSalidaBonos.xhtml";
        }
        return "/pages/InicioSession.xhtml";
    }

    void obtenerUsuario(Integer idUsuario) {
        usuario = sessionFacade.getUsuario(idUsuario);
    }

    public SystemFacade getSessionFacade() {
        return sessionFacade;
    }

    public void setSessionFacade(SystemFacade sessionFacade) {
        this.sessionFacade = sessionFacade;
    }

    public AdminFacade getAdminFacade() {
        return adminFacade;
    }

    public void setAdminFacade(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    public MarketingFacade getMarketingFacade() {
        return marketingFacade;
    }

    public void setMarketingFacade(MarketingFacade marketingFacade) {
        this.marketingFacade = marketingFacade;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public Usuariosdetalles getUsuarioDetalle() {
        return usuarioDetalle;
    }

    public void setUsuarioDetalle(Usuariosdetalles usuarioDetalle) {
        this.usuarioDetalle = usuarioDetalle;
    }

}
