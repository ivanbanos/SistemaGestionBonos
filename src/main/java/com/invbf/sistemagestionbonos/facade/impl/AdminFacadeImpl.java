/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.dao.CasinoDao;
import com.invbf.sistemagestionbonos.dao.DenominacionDao;
import com.invbf.sistemagestionbonos.dao.TipoBonoDao;
import com.invbf.sistemagestionbonos.entity.Denominaciones;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
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
    
}
