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
import javax.servlet.http.Cookie;
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
            if( UserDAO.save(registeredUser) && checkLogin(response, registeredUser) ) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (UserExistsException ex) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (InternalErrorException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private boolean checkLogin(HttpServletResponse httpResponse, 
            User registeredUser ) {
        
        final String nickname = registeredUser.getName();
        final String password = registeredUser.getPassword();
        
        System.out.println("nick " + nickname );
        System.out.println("pass " + password );
        
        if( nickname != null || password != null ) {
            final String token = UserDAO.checkLogin(nickname, password);
            System.out.println("token " + token );
            if( token != null ) {
                httpResponse.addCookie(new Cookie("token", token));
                return true;
            }
        }
        
        
        return false;
    }
}
