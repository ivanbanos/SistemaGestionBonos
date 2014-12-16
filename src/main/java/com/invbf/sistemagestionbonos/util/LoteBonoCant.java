/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.util;

import com.invbf.sistemagestionbonos.entity.Lotesbonos;

/**
 *
 * @author ivan
 */
public class LoteBonoCant {
    private Lotesbonos lote;
    private long cantidad;

    public LoteBonoCant() {
    }

    public LoteBonoCant(Lotesbonos lote) {
        this.lote = lote;
        cantidad = 0;
    }

    public LoteBonoCant(Lotesbonos lote, long cantidad) {
        this.lote = lote;
        this.cantidad = cantidad;
    }

    public Lotesbonos getLote() {
        return lote;
    }

    public void setLote(Lotesbonos lote) {
        this.lote = lote;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.lote != null ? this.lote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoteBonoCant other = (LoteBonoCant) obj;
        if (this.lote != other.lote && (this.lote == null || !this.lote.equals(other.lote))) {
            return false;
        }
        return true;
    }

    public void sumCantidad(long cantidad) {
        this.cantidad += cantidad;
    }
    
    public float getTotal(){
        return cantidad * lote.getDenominacion().getValor();
    }
    
}
