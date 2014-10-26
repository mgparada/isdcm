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
public class UserExistsException extends Exception {

    public UserExistsException () {
        super("Ya existe un usuario en el sistema con ese nick.");
    }
    
    public UserExistsException ( String message ) {
        super(message);
    }
    
}
