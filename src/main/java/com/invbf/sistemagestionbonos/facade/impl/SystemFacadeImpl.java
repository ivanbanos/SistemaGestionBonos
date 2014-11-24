/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.dao.ConfiguracionDao;
import com.invbf.sistemagestionbonos.dao.LogDao;
import com.invbf.sistemagestionbonos.dao.UsuarioDao;
import com.invbf.sistemagestionbonos.entity.Configuraciones;
import com.invbf.sistemagestionbonos.entity.Logs;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.exceptions.ClavesNoConcuerdanException;
import com.invbf.sistemagestionbonos.exceptions.NoCambioContrasenaException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoConectadoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoExisteException;
import com.invbf.sistemagestionbonos.facade.SystemFacade;
import com.invbf.sistemagestionbonos.util.EncryptUtil;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ideacentre
 */
public class SystemFacadeImpl implements SystemFacade {

    @Override
    public Usuarios iniciarSession(Usuarios usuario) throws ClavesNoConcuerdanException, UsuarioNoExisteException, UsuarioNoConectadoException {
        try {
            Usuarios usuarios = UsuarioDao.findByNombreUsuario(usuario.getNombreUsuario());
            if (usuarios != null) {
                Usuarios usuarioConectado = usuarios;
                if (!EncryptUtil.comparePassword(usuario.getContrasena(), usuarioConectado.getContrasena())) {
                    throw new ClavesNoConcuerdanException();
                }
                return usuarioConectado;
            } else {
                throw new UsuarioNoExisteException();
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new UsuarioNoConectadoException();
        }
    }

    @Override
    public Usuarios actualizarUsuario(Usuarios usuario) {
        return UsuarioDao.find(usuario.getIdUsuario());
    }

    @Override
    public Usuarios cambiarContrasena(String contrasena, String nueva, Usuarios usuario) throws ClavesNoConcuerdanException, NoCambioContrasenaException {
        try {
            if (!EncryptUtil.comparePassword(contrasena, usuario.getContrasena())) {
                throw new ClavesNoConcuerdanException();
            } else {
                usuario.setContrasena(EncryptUtil.encryptPassword(nueva));
                UsuarioDao.edit(usuario);
                return usuario;
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new NoCambioContrasenaException();
        }
    }

    @Override
    public Configuraciones getConfiguracionByName(String nombre) {

        return ConfiguracionDao.findByNombre(nombre);
    }

    @Override
    public void registrarlog(String mensaje) {
        Logs log = new Logs();
        log.setMensaje(mensaje);
        LogDao.create(log);
    }

    @Override
    public Usuarios getUsuario(Integer idUsuario) {
        return UsuarioDao.find(idUsuario);
    }

}
