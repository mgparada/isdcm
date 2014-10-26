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
public class VideoExistsException extends Exception {

    public VideoExistsException () {
        super("Ya existe un video en el sistema con ese titulo.");
    }
    
    public VideoExistsException ( String message ) {
        super(message);
    }
    
}
