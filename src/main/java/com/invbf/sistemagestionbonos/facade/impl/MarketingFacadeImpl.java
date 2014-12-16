/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade.impl;

import com.invbf.sistemagestionbonos.dao.AreaDao;
import com.invbf.sistemagestionbonos.dao.BonosnofisicosDao;
import com.invbf.sistemagestionbonos.dao.BonosnoincluidosDao;
import com.invbf.sistemagestionbonos.dao.ControlsalidabonosDao;
import com.invbf.sistemagestionbonos.util.Notificador;
import com.invbf.sistemagestionbonos.dao.LotebonoDao;
import com.invbf.sistemagestionbonos.dao.SolicitudEntregaClientesDao;
import com.invbf.sistemagestionbonos.dao.SolicitudEntregaDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesDao;
import com.invbf.sistemagestionbonos.dao.SolicitudentregalotesmaestroDao;
import com.invbf.sistemagestionbonos.entity.Areas;
import com.invbf.sistemagestionbonos.entity.Bonosnofisicos;
import com.invbf.sistemagestionbonos.entity.Bonosnoincluidos;
import com.invbf.sistemagestionbonos.entity.Controlsalidabonos;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.exceptions.ExistenBonosFisicosException;
import com.invbf.sistemagestionbonos.exceptions.LoteBonosExistenteException;
import com.invbf.sistemagestionbonos.facade.MarketingFacade;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan
 */
public class MarketingFacadeImpl implements MarketingFacade {

    @Override
    public Solicitudentregalotesmaestro getSolicitudentregalotesbono(Integer id) {
        return SolicitudentregalotesmaestroDao.find(id);
    }

    @Override
    public List<Lotesbonos> getAllLotesBonos() {
        return LotebonoDao.findAll();
    }

    @Override
    public List<Solicitudentregalotesmaestro> getAllSolicitudentregalotesmaestro() {
        return SolicitudentregalotesmaestroDao.findAll();
    }

    @Override
    public void deleteSolicitudentregalotesmaestro(Solicitudentregalotesmaestro elemento) {
        for (Solicitudentregalotes sel : elemento.getSolicitudentregalotesList()) {
            SolicitudentregalotesDao.remove(sel);
        }
        SolicitudentregalotesmaestroDao.remove(elemento);
    }

    @Override
    public List<Solicitudentregalotesmaestro> getSolicitudentregalotesmaestroNoAceptadas() {
        return SolicitudentregalotesmaestroDao.findNoAceptadas();
    }

    @Override
    public void borrarLote(Lotesbonos elemento) throws ExistenBonosFisicosException {
        if (elemento.getDesde().equals(elemento.getHasta())) {
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
        if (!LotebonoDao.existeLote(elemento)) {
            elemento.setSolicitudentregalotesList(new ArrayList<Solicitudentregalotes>());
            LotebonoDao.create(elemento);

        } else {
            throw new LoteBonosExistenteException();
        }
    }

    @Override
    public void deleteSolicitudentrega(Solicitudentrega elemento) {
        if (elemento.getSolicitudentregaclientesList() != null) {
            for (Solicitudentregaclientes col : elemento.getSolicitudentregaclientesList()) {
                SolicitudEntregaClientesDao.remove(col);
            }
        }
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
    public Solicitudentrega guardarSolicitudentrega(Solicitudentrega elemento) {

        System.out.println("entra aqui");
        Areas a = AreaDao.findAll().get(0);
        if (elemento.getId() != null) {
            if (elemento.getSolicitudentregaclientesList() != null) {
                for (Solicitudentregaclientes col : elemento.getSolicitudentregaclientesList()) {
                    if (col.getAreaid() == null) {
                        col.setAreaid(a);
                    }
                    SolicitudEntregaClientesDao.edit(col);
                }
            }
            SolicitudEntregaDao.edit(elemento);
            return elemento;
        } else {
            if (elemento.getSolicitudentregaclientesList() != null) {
                for (Solicitudentregaclientes col : elemento.getSolicitudentregaclientesList()) {
                    if (col.getAreaid() == null) {
                        col.setAreaid(a);
                    }
                    SolicitudEntregaClientesDao.edit(col);
                }
            }
            SolicitudEntregaDao.create(elemento);
            return elemento;
        }
    }

    @Override
    public void borrarSolicitudCliente(Solicitudentregaclientes sec) {
        SolicitudEntregaClientesDao.remove(sec);
    }

    @Override
    public void guardarBononoincluido(Bonosnoincluidos bni) {
        BonosnoincluidosDao.edit(bni);
    }

    @Override
    public void borrarBononoIncluido(Bonosnoincluidos next) {
        BonosnoincluidosDao.remove(next);
    }

    @Override
    public void borrarSolicitudLote(Solicitudentregalotes next2) {
        SolicitudentregalotesDao.remove(next2);
    }

    @Override
    public void convertBonosNoIncluidosToBonosNoFisicos(List<Solicitudentregalotes> solicitudentregalotesList) {
        for (Solicitudentregalotes solicitudentregalotesList1 : solicitudentregalotesList) {
            List<Bonosnoincluidos> bonosNoIncluidos = solicitudentregalotesList1.getBonosnoincluidosList();
            for (Bonosnoincluidos next : bonosNoIncluidos) {
                Bonosnofisicos bnf = new Bonosnofisicos();
                bnf.setConsecutivo(next.getConsecutivo());
                bnf.setLotesBonosid(solicitudentregalotesList1.getLotesBonosid());
                BonosnofisicosDao.create(bnf);
                solicitudentregalotesList1.getLotesBonosid().getBonosnofisicosList().add(bnf);
            }
            LotebonoDao.edit(solicitudentregalotesList1.getLotesBonosid());
        }
    }

    @Override
    public void editLoteBono(Lotesbonos lotesBonosid, List<Bonosnoincluidos> bonosnoincluidos) {
        if (lotesBonosid.getBonosnofisicosList() == null) {
            lotesBonosid.setBonosnofisicosList(new ArrayList<Bonosnofisicos>());
        }
        for (Bonosnoincluidos next : bonosnoincluidos) {
            if (next.getConsecutivo() != null || !next.getConsecutivo().equals("")) {
                Bonosnofisicos bnf = new Bonosnofisicos();
                bnf.setConsecutivo(next.getConsecutivo());
                bnf.setLotesBonosid(lotesBonosid);
                BonosnofisicosDao.create(bnf);
                lotesBonosid.getBonosnofisicosList().add(bnf);
            }
        }
        LotebonoDao.edit(lotesBonosid);
    }

    @Override
    public boolean guardarSolicitudentregabonos(Solicitudentregalotesmaestro elemento) {
        if (elemento.getId() == null) {
            List<Solicitudentregalotes> solicitudentregaloteses = elemento.getSolicitudentregalotesList();
            elemento.setSolicitudentregalotesList(null);
            SolicitudentregalotesmaestroDao.create(elemento);
            for (Solicitudentregalotes solicitudentregalotese : solicitudentregaloteses) {
                solicitudentregalotese.setSolicitudEntregaLotesMaestro(elemento);
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
    public void crearSolicitudSalidaBonos(Solicitudentrega s) {
        Controlsalidabonos csb = new Controlsalidabonos();
        csb.setSolicitudEntregaid(s);
        csb.setEstado("CREADA");
        try {
            DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            DateFormat df2 = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
            TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
            df.setTimeZone(timeZone);
            Calendar nowDate = Calendar.getInstance();
            nowDate.setTime(df2.parse(df.format(nowDate.getTime())));
            csb.setFecha(nowDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(MarketingFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ControlsalidabonosDao.create(csb);

    }

    @Override
    public List<Controlsalidabonos> getAllControlsalidabonos() {
        return ControlsalidabonosDao.findAll();
    }

    @Override
    public Controlsalidabonos getSolicitudSalida(Integer id) {
        return ControlsalidabonosDao.find(id);
    }

    @Override
    public List<Lotesbonos> getLotesBonosCasinoTipoBono(Integer idCasino, Tiposbonos tipoBono) {
        return LotebonoDao.getByCasinoTipoBono(idCasino, tipoBono);
    }

    @Override
    public void guardarControlSalidaBonos(Controlsalidabonos elemento) {
        ControlsalidabonosDao.edit(elemento);
    }
}
