/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Configuraciones;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.exceptions.ClavesNoConcuerdanException;
import com.invbf.sistemagestionbonos.exceptions.NoCambioContrasenaException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioInactivoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoConectadoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoExisteException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioSinAccesoalSistemaException;


/**
 *
 * @author ideacentre
 */
public interface SystemFacade {

    public Usuarios iniciarSession(Usuarios usuario)throws ClavesNoConcuerdanException, UsuarioNoExisteException, UsuarioNoConectadoException,UsuarioInactivoException, UsuarioSinAccesoalSistemaException ;

    public Usuarios actualizarUsuario(Usuarios usuario);

    public Usuarios cambiarContrasena(String contrasena, String nueva, Usuarios usuario)throws ClavesNoConcuerdanException, NoCambioContrasenaException;

    public Configuraciones getConfiguracionByName(String paginacion);

    public void registrarlog(String string);

    public Usuarios getUsuario(Integer idUsuario);

    public String getNombreDeUsuario(Integer id);

    public Usuariosdetalles getDetalleUsuario(Integer idUsuario);
}
