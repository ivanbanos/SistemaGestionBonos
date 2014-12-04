/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.dao.AccesoDao;
import com.invbf.sistemagestionbonos.dao.ConfiguracionDao;
import com.invbf.sistemagestionbonos.dao.LogDao;
import com.invbf.sistemagestionbonos.dao.UsuarioDao;
import com.invbf.sistemagestionbonos.dao.UsuarioDetalleDao;
import com.invbf.sistemagestionbonos.entity.Accesos;
import com.invbf.sistemagestionbonos.entity.Configuraciones;
import com.invbf.sistemagestionbonos.entity.Logs;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.exceptions.ClavesNoConcuerdanException;
import com.invbf.sistemagestionbonos.exceptions.NoCambioContrasenaException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioInactivoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoConectadoException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioNoExisteException;
import com.invbf.sistemagestionbonos.exceptions.UsuarioSinAccesoalSistemaException;
import com.invbf.sistemagestionbonos.facade.SystemFacade;
import com.invbf.sistemagestionbonos.util.EncryptUtil;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ideacentre
 */
public class SystemFacadeImpl implements SystemFacade {

    @Override
    public Usuarios iniciarSession(Usuarios usuario) throws ClavesNoConcuerdanException, UsuarioNoExisteException, UsuarioNoConectadoException, UsuarioInactivoException, UsuarioSinAccesoalSistemaException{
        try {
            Usuarios usuarios = UsuarioDao.findByNombreUsuario(usuario.getNombreUsuario());
            if (usuarios != null) {
                Usuarios usuarioConectado = usuarios;
                if (usuarioConectado.getEstado()==null||usuarioConectado.getEstado().equals("INACTIVO")) {
                    throw new UsuarioInactivoException();
                }
                Usuariosdetalles du = UsuarioDetalleDao.find(usuarioConectado.getIdUsuario());
                if(du==null){
                    UsuarioDetalleDao.create(du);
                }
                Accesos a = AccesoDao.findByNombreAcceso("SGB");
                if(du.getAccesosList()==null||du.getAccesosList().isEmpty()||!du.getAccesosList().contains(a)){
                    throw new UsuarioSinAccesoalSistemaException();
                }
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
        try {
            Logs log = new Logs();
            log.setMensaje(mensaje);
            DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            DateFormat df2 = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
            df.setTimeZone(timeZone);
            Calendar nowDate = Calendar.getInstance();
            nowDate.setTime(df2.parse(df.format(nowDate.getTime())));
            log.setFecha(nowDate.getTime());
            LogDao.create(log);
        } catch (ParseException ex) {
            Logger.getLogger(SystemFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuarios getUsuario(Integer idUsuario) {
        return UsuarioDao.find(idUsuario);
    }

    @Override
    public String getNombreDeUsuario(Integer id) {
        Usuarios u = UsuarioDao.find(id);
        if(u==null){
            return "No registrado";
        } else {
            return u.getNombreUsuario();
        }
    }

}
