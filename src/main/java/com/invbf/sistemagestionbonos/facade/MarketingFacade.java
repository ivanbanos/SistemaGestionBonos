/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotesmaestro;
import java.util.List;

/**
 *
 * @author ivan
 */
public interface MarketingFacade {

    public Solicitudentregalotesmaestro getSolicitudentregalotesbono(Integer id);

    public List<Lotesbonos> getAllLotesBonos();

    public boolean guardarSolicitudentregabonos(Solicitudentregalotesmaestro elemento);
    
}
