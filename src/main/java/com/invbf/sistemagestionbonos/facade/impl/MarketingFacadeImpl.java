/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.util.Notificador;
import com.invbf.sistemagestionbonos.dao.LotebonoDao;
import com.invbf.sistemagestionbonos.dao.SolicitudEntregaDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesmaestroDao;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import com.invbf.sistemagestionbonos.exceptions.ExistenBonosFisicosException;
import com.invbf.sistemagestionbonos.exceptions.LoteBonosExistenteException;
import com.invbf.sistemagestionbonos.facade.MarketingFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ivan
 */
public class MarketingFacadeImpl implements MarketingFacade{

    @Override
    public Solicitudentregalotesmaestro getSolicitudentregalotesbono(Integer id) {
        return SolicitudentregalotesmaestroDao.find(id);
    }

    @Override
    public List<Lotesbonos> getAllLotesBonos() {
        return LotebonoDao.findAll();
    }

    @Override
    public boolean guardarSolicitudentregabonos(Solicitudentregalotesmaestro elemento) {
        if (elemento.getId()== null) {
            List<Solicitudentregalotes> solicitudentregaloteses = elemento.getSolicitudentregalotesList();
            elemento.setSolicitudentregalotesList(null);
            SolicitudentregalotesmaestroDao.create(elemento);
            for (Solicitudentregalotes solicitudentregalotese : solicitudentregaloteses) {
                solicitudentregalotese.setSolicitudEntregaLotesMaestro(elemento);
                SolicitudentregalotesDao.create(solicitudentregalotese);
            }
            elemento.setSolicitudentregalotesList(solicitudentregaloteses);
            SolicitudentregalotesmaestroDao.edit(elemento);
            Notificador.notificar(Notificador.SOLICITUD_ENTREGA_LOTES_GENERADA);
            return false;
        } else {
            SolicitudentregalotesmaestroDao.edit(elemento);
            return true;
        }
    }

    @Override
    public List<Solicitudentregalotesmaestro> getAllSolicitudentregalotesmaestro() {
        return SolicitudentregalotesmaestroDao.findAll();
    }

    @Override
    public void deleteSolicitudentregalotesmaestro(Solicitudentregalotesmaestro elemento) {
        for(Solicitudentregalotes sel: elemento.getSolicitudentregalotesList()){
            SolicitudentregalotesDao.remove(sel);
        }
        SolicitudentregalotesmaestroDao.remove(elemento);
    }

    @Override
    public void editLoteBono(Lotesbonos lotesBonosid) {
        LotebonoDao.edit(lotesBonosid);
    }

    @Override
    public List<Solicitudentregalotesmaestro> getSolicitudentregalotesmaestroNoAceptadas() {
        return SolicitudentregalotesmaestroDao.findNoAceptadas();
    }

    @Override
    public void borrarLote(Lotesbonos elemento) throws ExistenBonosFisicosException {
        if(elemento.getDesde().equals(elemento.getHasta())){
            for (Iterator<Solicitudentregalotes> iterator = elemento.getSolicitudentregalotesList().iterator(); iterator.hasNext();) {
                Solicitudentregalotes next = iterator.next();
                SolicitudentregalotesDao.remove(next);
                
            }
            elemento.getSolicitudentregalotesList().clear();
            LotebonoDao.remove(elemento);
        } else {
            throw new ExistenBonosFisicosException();
        }
    }

    @Override
    public void guardarLote(Lotesbonos elemento) throws LoteBonosExistenteException {
        if(!LotebonoDao.existeLote(elemento)){
            elemento.setSolicitudentregalotesList(new ArrayList<Solicitudentregalotes>());
            LotebonoDao.create(elemento);
            
        } else {
            throw new LoteBonosExistenteException();
        }
    }

    @Override
    public void deleteSolicitudentrega(Solicitudentrega elemento) {
        SolicitudEntregaDao.remove(elemento);
    }

    @Override
    public List<Solicitudentrega> getAllSolicitudentrega() {
        return SolicitudEntregaDao.findAll();
    }

    @Override
    public List<Solicitudentrega> getAllSolicitudentregaSolicitante(Integer idUsuario) {
        return SolicitudEntregaDao.findByIdCreador(idUsuario);
    }

    @Override
    public Solicitudentrega getSolicitudbono(Integer id) {
        return SolicitudEntregaDao.find(id);
    }

    @Override
    public void guardarSolicitudentrega(Solicitudentrega elemento) {
        if(elemento.getId()!=null){
            SolicitudEntregaDao.edit(elemento);
            
        } else {
            SolicitudEntregaDao.create(elemento);
        }
    }

    
}
