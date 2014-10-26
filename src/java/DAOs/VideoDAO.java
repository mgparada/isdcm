package DAOs;

import Entities.Video;
import Exceptions.InternalErrorException;
import Exceptions.VideoExistsException;
import Exceptions.VideoNonExistsException;
import Utilities.DBConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author mauro.gomez
 */
public class VideoDAO {
    private static final String INSERT = "INSERT INTO videos "
            + "(TITLE, AUTHOR, CREATION_DATE, DURATION, VIEWS, DESCRIPTION, FORMAT, URL) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT title FROM videos WHERE upper(title) = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM videos WHERE id = ?";
    
    public boolean save ( Video video ) throws VideoExistsException, InternalErrorException  {
        if( !exists( video.getTitle() ) )
            return insert( video );
        else
            throw new VideoExistsException();
    }
    
    public Video getVideo ( int id ) throws VideoNonExistsException {
        if( exists( Integer.toString(id) ) ) {
            return find( Integer.toString(id) );
        }else
            throw new VideoNonExistsException();
    }
    
    public LinkedList<VideoDAO> getAll () {
        return null;
    }
    
    private boolean exists ( String discriminatorColum ) {
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT);
            
            preparedStatement.setString(1, discriminatorColum.toUpperCase() );
            
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
    
    
    private boolean insert ( Video video ) throws InternalErrorException { 
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(INSERT);

            preparedStatement.setString( 1, video.getTitle() );
            preparedStatement.setString( 2, video.getAuthor() );
            preparedStatement.setDate(3, (Date) video.getCreationDate());
            preparedStatement.setTime(4, video.getDuration());
            preparedStatement.setInt(5, 1 );
            preparedStatement.setString( 6, video.getDescription() );
            preparedStatement.setString( 7, video.getMime() );
            preparedStatement.setURL(8, video.getUrl() );
            
            
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
    
    private Video find ( String id ) throws VideoNonExistsException {
        Video foundVideo = null;
        
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT_BY_ID);

            preparedStatement.setString(1, id );
            
            
            ResultSet rs = preparedStatement.executeQuery();
            if( !rs.next() )
                throw new VideoNonExistsException();
            
            foundVideo = new Video ( 
                                rs.getInt(1), 
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDate(4),
                                rs.getDate(5),
                                rs.getTime(6),
                                rs.getInt(7),
                                rs.getString(8),
                                rs.getString(9),
                                rs.getURL(10)
                            );
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println( Arrays.toString(ex.getStackTrace()) );
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException ex) {
                System.err.println( Arrays.toString(ex.getStackTrace()) );
            }
        }
        
        return foundVideo;
    }
}