/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.util;

import com.invbf.sistemagestionbonos.entity.Areas;
import com.invbf.sistemagestionbonos.entity.Clientessgb;

/**
 *
 * @author ivan
 */
public class ClienteSGBDTO {
    private Float valorTotal;
    private Clientessgb clientessgb;
    private Areas areaid;

    public ClienteSGBDTO() {
    }

    public ClienteSGBDTO(Float valorTotal, Clientessgb clientessgb, Areas areaid) {
        this.valorTotal = valorTotal;
        this.clientessgb = clientessgb;
        this.areaid = areaid;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Clientessgb getClientessgb() {
        return clientessgb;
    }

    public void setClientessgb(Clientessgb clientessgb) {
        this.clientessgb = clientessgb;
    }

    public Areas getAreaid() {
        return areaid;
    }

    public void setAreaid(Areas areaid) {
        this.areaid = areaid;
    }
    
    
}
