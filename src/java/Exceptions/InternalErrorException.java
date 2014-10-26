/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author mauro
 */
public class InternalErrorException extends Exception {

    public InternalErrorException () {
        super("Se produjo un error interno, inténtalo de nuevo.");
    }
    
    public InternalErrorException ( String message ) {
        super(message);
    }
    
}
