/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.UserDAO;
import Entities.Video;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mauro.gomez
 */
public class VideoController extends HttpServlet {
    
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Video videoToSave = new Video( 
                                    request.getParameter("title"),
                                    request.getParameter("author"),
                                    createDateFromString(request.getParameter("creation_date")),
                                    createTimeFromString(request.getParameter("duration")), 
                                    request.getParameter("description"), 
                                    request.getParameter("format"), 
                                    new URL(request.getParameter("url"))
                                );
//        
//        if( new UserDAO().createUser(data) )
//            request.setAttribute("response", "El video se ha añadido correctamente.");
//        else
//            request.setAttribute("response", "Ya existe un video con ese nombre.");
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
     
        
        
    }
    
    private Date createDateFromString ( String date ) {
        Date newDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            newDate = formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newDate;
    }
    
    private Time createTimeFromString ( String time ) {
        Time newTime = null;
        try {
            DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            newTime = new Time ( formatter.parse(time).getTime() );
        } catch (ParseException ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newTime;
    }
    
    
}
