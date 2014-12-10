/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.dao.AreaDao;
import com.invbf.sistemagestionbonos.dao.CasinoDao;
import com.invbf.sistemagestionbonos.dao.ClienteDao;
import com.invbf.sistemagestionbonos.dao.DenominacionDao;
import com.invbf.sistemagestionbonos.dao.PropositosentregaDao;
import com.invbf.sistemagestionbonos.dao.TipoBonoDao;
import com.invbf.sistemagestionbonos.dao.UsuarioDao;
import com.invbf.sistemagestionbonos.dao.UsuarioDetalleDao;
import com.invbf.sistemagestionbonos.entity.Areas;
import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entity.Denominaciones;
import com.invbf.sistemagestionbonos.entity.Propositosentrega;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entity.Usuariosdetalles;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
import com.invbf.sistemagestionbonos.facade.AdminFacade;
import java.util.List;

/**
 *
 * @author ivan
 */
public class AdminFacadeImpl implements AdminFacade{

    @Override
    public List<Denominaciones> findAllDenominaciones() {
        return DenominacionDao.findAll();
    }

    @Override
    public void deleteDenominacion(Denominaciones elemento) {
        DenominacionDao.remove(elemento);
    }

    @Override
    public boolean guardarDenominacion(Denominaciones elemento) {
        if (elemento.getId()== null) {

            DenominacionDao.create(elemento);
            return false;
        } else {
            DenominacionDao.edit(elemento);
            return true;
        }
    }

    @Override
    public void deleteTiposbonos(Tiposbonos elemento) {
        TipoBonoDao.remove(elemento);
    }

    @Override
    public List<Tiposbonos> findAllTiposbonos() {
        return TipoBonoDao.findAll();
    }

    @Override
    public boolean guardarTiposbonos(Tiposbonos elemento) {
        if (elemento.getId()== null) {

            TipoBonoDao.create(elemento);
            return false;
        } else {
            TipoBonoDao.edit(elemento);
            return true;
        }
    }

    @Override
    public List<Casinos> findAllCasinos() {
        return CasinoDao.findAll();
    }

    @Override
    public List<Propositosentrega> findAllPropositosentrega() {
        return PropositosentregaDao.findAll();
    }

    @Override
    public boolean guardarPropositosentrega(Propositosentrega elemento) { 
        if (elemento.getId()== null) {

            PropositosentregaDao.create(elemento);
            return false;
        } else {
            PropositosentregaDao.edit(elemento);
            return true;
        }
    }

    @Override
    public void deletePropositosentrega(Propositosentrega elemento) {
        PropositosentregaDao.remove(elemento);
    }

    @Override
    public List<Areas> findAllAreas() {
        return AreaDao.findAll();
    }

    @Override
    public boolean guardarAreas(Areas elemento) {
        if (elemento.getId()== null) {

            AreaDao.create(elemento);
            return false;
        } else {
            AreaDao.edit(elemento);
            return true;
        }
    }

    @Override
    public void deleteAreas(Areas elemento) {
        AreaDao.remove(elemento);
    }

    @Override
    public void deleteClientessgb(Clientessgb elemento) {
        ClienteDao.remove(elemento);
    }

    @Override
    public List<Clientessgb> findAllClientessgb() {
        return ClienteDao.findAll();
    }

    @Override
    public boolean guardarClientessgb(Clientessgb elemento) {
        if (elemento.getId()== null) {

            ClienteDao.create(elemento);
            return false;
        } else {
            ClienteDao.edit(elemento);
            return true;
        }
    }

    @Override
    public List<Clientessgb> findClientessgbByCasino(Casinos idCasino) {
        return ClienteDao.findByIdCasino(idCasino.getIdCasino());
    }

    @Override
    public List<Usuarios> findAllUsuarios() {
        return UsuarioDao.findAll();
    }

    @Override
    public List<Usuariosdetalles> findAllUsuariosdetlles() {
        return UsuarioDetalleDao.findAll();
    }
    
}
