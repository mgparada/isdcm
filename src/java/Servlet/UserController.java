/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.UserModel;
import java.io.IOException;
import java.util.LinkedList;
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
        
        LinkedList<String> data = new LinkedList<>();
        data.add(0, request.getParameter("name"));
        data.add(1, request.getParameter("surname"));
        data.add(2, request.getParameter("email"));
        data.add(3, request.getParameter("nickname"));
        data.add(4, request.getParameter("password"));
        
        if( new UserModel().createUser(data) )
            request.setAttribute("response", "Tu usuario ha sido creado correctamente.");
        else
            request.setAttribute("response", "Ya existe un usuario en el sistema con ese nick.");
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
