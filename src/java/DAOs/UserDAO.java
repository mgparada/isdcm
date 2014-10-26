package DAOs;

import Entities.User;
import Exceptions.InternalErrorException;
import Exceptions.UserExistsException;
import Utilities.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro.gomez
 */
public final class UserDAO {
    private static final String INSERT = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT name FROM users WHERE upper(nickname) = ?";
    private static final String CHECK_LOGIN = "SELECT PASSWORD FROM USERS WHERE NICKNAME = ?";    
    
    public static boolean save ( User user ) throws UserExistsException, InternalErrorException {
        if( !exists(user.getNickname()) )
            return insert( user );
        else
            throw new UserExistsException();
    }
    
    public static String checkLogin ( String nickname, String password ) {
        try {            
            final PreparedStatement preparedStatement = DBConnection.getPreparedStatement(CHECK_LOGIN);
            preparedStatement.setString(1, nickname);
            
            final ResultSet result = preparedStatement.executeQuery();
            if( result.next() ) {
                if( result.getString("PASSWORD").equals(password) )
                    return nickname + ":" + password;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static String checkToken ( String token ) {
        if( token.length() <= 0 )
            return null;
        
        final String nickname = token.substring(0, token.indexOf(":"));
        final String password = token.substring(token.indexOf(":") + 1);
        
        try {            
            final PreparedStatement preparedStatement = DBConnection.getPreparedStatement(CHECK_LOGIN);
            preparedStatement.setString(1, nickname);
            
            final ResultSet result = preparedStatement.executeQuery();
            if( result.next() ) {
                if( result.getString("PASSWORD").equals(password) )
                    return nickname;
                    
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private static boolean exists ( String nickname ) {  
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT);
            
            preparedStatement.setString(1, nickname.toUpperCase() );
            
            if( preparedStatement.executeQuery().next() )
                return true;

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println( Arrays.toString(ex.getStackTrace()) );
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException ex) {
                System.err.println( Arrays.toString(ex.getStackTrace()) );
            }
        }
        
        return false;
    }
    
    private static boolean insert ( User user ) throws InternalErrorException { 
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(INSERT);

            preparedStatement.setString( 1, user.getName() );
            preparedStatement.setString( 2, user.getSurname() );
            preparedStatement.setString( 3, user.getEmail() );
            preparedStatement.setString( 4, user.getNickname() );
            preparedStatement.setString( 5, user.getPassword() ); 
            
            if( preparedStatement.executeUpdate() == 1 )
                return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println( Arrays.toString(ex.getStackTrace()) );
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException ex) {
                System.err.println( Arrays.toString(ex.getStackTrace()) );
            }
        }
        
        throw new InternalErrorException();
    }
}