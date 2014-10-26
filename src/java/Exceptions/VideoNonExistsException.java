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
public class VideoNonExistsException extends Exception {

    public VideoNonExistsException () {
        super("El video con ese id no existe.");
    }
    
    public VideoNonExistsException ( String message ) {
        super(message);
    }
    
}
