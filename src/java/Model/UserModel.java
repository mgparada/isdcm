package Model;

import Utilities.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author mauro.gomez
 */
public class UserModel {
    private static final String insert = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
    private static final String select = "SELECT name FROM users WHERE upper(nickname) = ?";
    
    public boolean createUser ( LinkedList<String> data )  {
        return !exists( data.get(3) ) && save( data );
    }
    
    private boolean exists ( String nickname ) {  
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(select);
            
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
    
    private boolean save ( LinkedList data ) { 
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(insert);

            preparedStatement.setString( 1, data.get(0).toString() );
            preparedStatement.setString( 2, data.get(1).toString() );
            preparedStatement.setString( 3, data.get(2).toString() );
            preparedStatement.setString( 4, data.get(3).toString() );
            preparedStatement.setString( 5, data.get(4).toString() ); 
            
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
        
        return false;
    }
}