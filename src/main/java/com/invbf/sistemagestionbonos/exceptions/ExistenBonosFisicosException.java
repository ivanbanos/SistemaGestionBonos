/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.exceptions;

/**
 *
 * @author ivan
 */
public class ExistenBonosFisicosException extends Exception {

    /**
     * Creates a new instance of <code>ExistenBonosFisicosException</code>
     * without detail message.
     */
    public ExistenBonosFisicosException() {
    }

    /**
     * Constructs an instance of <code>ExistenBonosFisicosException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExistenBonosFisicosException(String msg) {
        super(msg);
    }
}
