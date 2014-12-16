/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.util;

import com.invbf.sistemagestionbonos.entity.Bonosnoincluidos;
import com.invbf.sistemagestionbonos.entity.Lotesbonos;
import com.invbf.sistemagestionbonos.entity.Solicitudentregalotes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivan
 */
public class loteBonoSolicitud {
    private Integer id;
    private Integer cantidad;
    private Lotesbonos lotesBonosid;
    private List<Bonosnoincluidos> bonosnoincluidosList;

    public loteBonoSolicitud() {
    }

    public loteBonoSolicitud(Lotesbonos lote) {
        lotesBonosid = lote;
        cantidad = 0;
        bonosnoincluidosList = new ArrayList<Bonosnoincluidos>();
    }

    public loteBonoSolicitud(Integer id, Integer cantidad, Lotesbonos lotesBonosid, List<Bonosnoincluidos> bonosnoincluidosList) {
        this.id = id;
        this.cantidad = cantidad;
        this.lotesBonosid = lotesBonosid;
        this.bonosnoincluidosList = bonosnoincluidosList;
    }
    
    public Solicitudentregalotes getSolicitudEntregaLote(){
        Solicitudentregalotes sol = new Solicitudentregalotes(id);
        sol.setCantidad(cantidad);
        sol.setBonosnoincluidosList(bonosnoincluidosList);
        sol.setLotesBonosid(lotesBonosid);
        return sol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Lotesbonos getLotesBonosid() {
        return lotesBonosid;
    }

    public void setLotesBonosid(Lotesbonos lotesBonosid) {
        this.lotesBonosid = lotesBonosid;
    }

    public List<Bonosnoincluidos> getBonosnoincluidosList() {
        return bonosnoincluidosList;
    }

    public void setBonosnoincluidosList(List<Bonosnoincluidos> bonosnoincluidosList) {
        this.bonosnoincluidosList = bonosnoincluidosList;
    }
    
    
    
}
