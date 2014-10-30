package DAOs;

import Entities.Video;
import Exceptions.InternalErrorException;
import Exceptions.VideoExistsException;
import Exceptions.VideoNonExistsException;
import Utilities.DBConnection;
import com.sun.xml.internal.ws.util.StreamUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro.gomez
 */
public final class VideoDAO {
    private static final String INSERT = "INSERT INTO VIDEOS "
            + "(TITLE, AUTHOR, CREATION_DATE, DURATION, VIEWS, DESCRIPTION, FORMAT, URL, IMAGE, LANGUAGE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT TITLE FROM VIDEOS WHERE upper(TITLE) = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM VIDEOS";
    
    public static boolean save ( Video video ) throws VideoExistsException, InternalErrorException  {
        if( !exists( video.getTitle() ) )
            return insert( video );
        else
            throw new VideoExistsException();
    }
    
    public static Video getVideo ( int id ) throws VideoNonExistsException {
        if( exists( Integer.toString(id) ) ) {
            return find( Integer.toString(id) );
        }else
            throw new VideoNonExistsException();
    }
    
    public static LinkedList<Video> getAll () {
        return findAll();
    }
    
    private static boolean exists ( String discriminatorColum ) {
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
    
    
    private static boolean insert ( Video video ) throws InternalErrorException {
        System.out.println("title " + video.getTitle());
        System.out.println("author " + video.getAuthor());
        System.out.println("creation " + video.getCreationDate().toString());
        System.out.println("duration " + video.getDuration().toString());
        System.out.println("descript. " + video.getDescription());
        System.out.println("mime " + video.getMime());
        System.out.println("videourl " + video.getVideoUrl().toString());
        System.out.println("imageurl " + video.getImageUrl().toString());
        System.out.println("language " + video.getLanguage());
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(INSERT);

            preparedStatement.setString(1, video.getTitle() );
            preparedStatement.setString(2, video.getAuthor() );
            preparedStatement.setDate(3, (Date) video.getCreationDate());
            preparedStatement.setString(4, video.getDuration().toString() );
            preparedStatement.setInt(5, 1 );
            preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );
            preparedStatement.setString(7, video.getMime() );
            preparedStatement.setString(8, video.getVideoUrl().toString() );
            preparedStatement.setString(9, video.getImageUrl().toString());
            preparedStatement.setString(10, video.getLanguage());
            
            if( preparedStatement.executeUpdate() != 0 )
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
    
    private static Video find ( String id ) throws VideoNonExistsException {
        Video foundVideo = null;
        
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT_BY_ID);

            preparedStatement.setString(1, id );
            
            
            ResultSet rs = preparedStatement.executeQuery();
            if( !rs.next() )
                throw new VideoNonExistsException();
            
            foundVideo = new Video ( 
                                rs.getInt("ID"), 
                                rs.getString("TITLE"),
                                rs.getString("AUTHOR"),
                                rs.getDate("CREATION_DATE"),
                                rs.getDate("UPLOAD_DATE"),
                                rs.getTime("DURATION"),
                                rs.getInt("VIEWS"),
                                rs.getBlob("DESCRIPTION").toString(),
                                rs.getString("FORMAT"),
                                rs.getURL("URL"),
                                rs.getURL("IMAGE"),
                                rs.getString("LANGUAGE")
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
    
    private static LinkedList<Video> findAll () {
        LinkedList<Video> linkedVideos = new LinkedList<>();
        
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT_ALL);

            ResultSet rs = preparedStatement.executeQuery();
            if( rs.next() )
                do {
                    linkedVideos.add(new Video ( 
                                rs.getInt("ID"), 
                                rs.getString("TITLE"),
                                rs.getString("AUTHOR"),
                                rs.getDate("CREATION_DATE"),
                                rs.getDate("UPLOAD_DATE"),
                                rs.getTime("DURATION"),
                                rs.getInt("VIEWS"),
                                getStringFromBlob(rs.getBlob("DESCRIPTION")),
                                rs.getString(10),
                                new URL(rs.getString(8)),
                                rs.getString(11).length() == 2 ? null: new URL(rs.getString(11)),
                                rs.getString(12)
                            )
                    );
                } while(rs.next());
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println( Arrays.toString(ex.getStackTrace()) );
        } catch (MalformedURLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException ex) {
                System.err.println( Arrays.toString(ex.getStackTrace()) );
            }
        }
        
        return linkedVideos;
    }
    
    private static String getStringFromBlob( java.sql.Blob blob ) {
        try {
            byte[] data = blob.getBytes(1, (int) blob.length() );
            return new String(data);
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}