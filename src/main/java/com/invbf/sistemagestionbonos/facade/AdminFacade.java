/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.facade;

import com.invbf.sistemagestionbonos.entity.Areas;
import com.invbf.sistemagestionbonos.entity.Clientessgb;
import com.invbf.sistemagestionbonos.entity.Denominaciones;
import com.invbf.sistemagestionbonos.entity.Propositosentrega;
import com.invbf.sistemagestionbonos.entity.Tiposbonos;
import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import com.invbf.sistemagestionbonos.entitySGC.Usuarios;
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

    public List<Propositosentrega> findAllPropositosentrega();

    public boolean guardarPropositosentrega(Propositosentrega elemento);

    public void deletePropositosentrega(Propositosentrega elemento);

    public List<Areas> findAllAreas();

    public boolean guardarAreas(Areas elemento);

    public void deleteAreas(Areas elemento);

    public void deleteClientessgb(Clientessgb elemento);

    public List<Clientessgb> findAllClientessgb();

    public boolean guardarClientessgb(Clientessgb elemento);

    public List<Clientessgb> findClientessgbByCasino(Casinos idCasino);

    public List<Usuarios> findAllUsuarios();

}
