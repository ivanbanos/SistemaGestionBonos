/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.util.Notificador;
import com.invbf.sistemagestionbonos.dao.LotebonoDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesmaestroDao;
import com.invbf.sistemagestionbonos.dao.TipoBonoDao;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import com.invbf.sistemagestionbonos.facade.MarketingFacade;
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
    
}
