/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Lotesbonos;
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
    
}