/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAOs.UserDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mauro
 */

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        try {
            if( isStaticContent(httpRequest) ) {
                chain.doFilter(request, response);
            }else if( isLogoutPath(httpRequest) ) {
                removeTokenCookie(httpResponse);
                redirectToIndex(httpRequest, httpResponse);
            }else if( isIndexPath(httpRequest) || isRegisterPath(httpRequest) 
                    || checkToken(httpRequest) ) {
                chain.doFilter(request, response);
            }else if( checkLogin(httpRequest, httpResponse) ) {
                continueWithRedirect(httpRequest, httpResponse);
            }else
                redirectToIndex(httpRequest, httpResponse);
            
        } catch( IOException | ServletException ex ) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        } 
        
        
    }
    
    private boolean isStaticContent(HttpServletRequest httpRequest) {
        return httpRequest.getServletPath().matches("^/js/.*$") || 
                httpRequest.getServletPath().matches("^/css/.*$") ||
                httpRequest.getServletPath().matches("^/sectionPages/.*$");
    }
    
    private boolean isLogoutPath(HttpServletRequest request) {
        return request.getServletPath().equals("/logout");
    }

    private void removeTokenCookie(HttpServletResponse httpResponse) {
        final Cookie tokenCookie = new Cookie("token", "");
        tokenCookie.setMaxAge(0);
        httpResponse.addCookie(tokenCookie);
    }
    
    private void redirectToIndex(HttpServletRequest httpRequest, 
            HttpServletResponse httpResponse 
    ) throws IOException {
        httpResponse.sendRedirect(httpRequest.getContextPath());
    }
    
    private boolean isIndexPath(HttpServletRequest httpRequest) {
        return httpRequest.getServletPath().equals("/index.jsp");
    }
    
    private boolean isRegisterPath(HttpServletRequest httpRequest) {
        return httpRequest.getServletPath().equals("/registerUser");
    }

    private boolean checkToken(HttpServletRequest httpRequest) {
        final Cookie[] cookies = httpRequest.getCookies();
        if( cookies != null ) {
            for( Cookie cookie: cookies ) {
                if( cookie.getName().equals("token") )
                    return UserDAO.checkToken(cookie.getValue()) != null;
            }
        }
        
        return false;
    }
    
    private boolean checkLogin(HttpServletRequest httpRequest, 
            HttpServletResponse httpResponse) {
        
        if( !httpRequest.getServletPath().equals("/login.jsp") )
            return false;
        
        final String nickname = httpRequest.getParameter("nickname");
        final String password = httpRequest.getParameter("password");
        
        if( nickname != null || password != null ) {
            final String token = UserDAO.checkLogin(nickname, password);
            if( token != null ) {
                httpResponse.addCookie(new Cookie("token", token));
                return true;
            }
        }
        
        return false;
    }
    
    private void continueWithRedirect(HttpServletRequest httpRequest, 
            HttpServletResponse httpResponse
    ) throws IOException {
        String redirectPath = httpRequest.getRequestURI();
        if( httpRequest.getQueryString() != null )
            redirectPath += httpRequest.getQueryString();
        
        httpResponse.sendRedirect(redirectPath);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
