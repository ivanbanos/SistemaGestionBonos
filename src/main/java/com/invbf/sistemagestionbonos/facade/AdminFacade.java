/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Denominaciones;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import java.util.List;

/**
 *
 * @author ivan
 */
public interface AdminFacade {

    public List<Denominaciones> findAllDenominaciones();

    public void deleteDenominacion(Denominaciones elemento);

    public boolean guardarDenominacion(Denominaciones elemento);

    public void deleteTiposbonos(Tiposbonos elemento);

    public List<Tiposbonos> findAllTiposbonos();

    public boolean guardarTiposbonos(Tiposbonos elemento);

    public List<Casinos> findAllCasinos();
    
}
