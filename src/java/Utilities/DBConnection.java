package Utilities;

import java.sql.*;
/**
 *
 * @author mauro.gomez
 */
public final class DBConnection {
    private static Connection con;
    private static final String connectionUrl = "jdbc:derby://localhost:1527/isdcm;user=isdcm;password=isdcm";
    
    
    public static Connection getConnection () throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        con = DriverManager.getConnection(connectionUrl);
        return con;
    }
    
    public static void closeConnection () throws SQLException {
        if( con != null )
            con.close();
    }
    
    public static PreparedStatement getPreparedStatement ( String query ) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        preparedStatement.setQueryTimeout(30);
        
        return preparedStatement;
    }
}
