/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentrega;
import com.invbf.sistemagestionbonos.entity.Solicitudentregaclientes;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import com.invbf.sistemagestionbonos.exceptions.ExistenBonosFisicosException;
import com.invbf.sistemagestionbonos.exceptions.LoteBonosExistenteException;
import java.util.List;

/**
 *
 * @author ivan
 */
public interface MarketingFacade {

    public Solicitudentregalotesmaestro getSolicitudentregalotesbono(Integer id);

    public List<Lotesbonos> getAllLotesBonos();

    public boolean guardarSolicitudentregabonos(Solicitudentregalotesmaestro elemento);

    public List<Solicitudentregalotesmaestro> getAllSolicitudentregalotesmaestro();

    public void deleteSolicitudentregalotesmaestro(Solicitudentregalotesmaestro elemento);

    public void editLoteBono(Lotesbonos lotesBonosid);

    public List<Solicitudentregalotesmaestro> getSolicitudentregalotesmaestroNoAceptadas();

    public void borrarLote(Lotesbonos elemento)throws ExistenBonosFisicosException;

    public void guardarLote(Lotesbonos elemento)throws LoteBonosExistenteException;

    public void deleteSolicitudentrega(Solicitudentrega elemento);

    public List<Solicitudentrega> getAllSolicitudentrega();

    public List<Solicitudentrega> getAllSolicitudentregaSolicitante(Integer idUsuario);

    public Solicitudentrega getSolicitudbono(Integer id);

    public Solicitudentrega guardarSolicitudentrega(Solicitudentrega elemento);

    public void borrarSolicitudCliente(Solicitudentregaclientes sec);
    
}
