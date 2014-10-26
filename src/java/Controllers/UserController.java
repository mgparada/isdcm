/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import DAOs.UserDAO;
import Exceptions.InternalErrorException;
import Exceptions.UserExistsException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mauro.gomez
 */
public class UserController extends HttpServlet {
    
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
        
        User registeredUser = new User( 
                                    request.getParameter("name"),
                                    request.getParameter("surname"),
                                    request.getParameter("email"),
                                    request.getParameter("nickname"),
                                    request.getParameter("password")
                                );
        
        try {
            if( UserDAO.save(registeredUser) ) {
                request.setAttribute("response", "Tu usuario ha sido creado correctamente.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else
                request.setAttribute("response", "Extraño. Por aquí no debería pasar nunca...");
        } catch (UserExistsException ex) {
            request.setAttribute("response", "Ya existe un usuario en el sistema con ese nick.");
        } catch (InternalErrorException ex) {
            request.setAttribute("response", "Ha ocurrido un error interno, inténtalo de nuevo");
        }
        
        request.getRequestDispatcher("errors.jsp").forward(request, response);
    }
}
