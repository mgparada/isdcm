/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.net.URL;
import java.sql.Time;

/**
 *
 * @author mauro
 */
public class Video {
    
    private int id;
    private String title;
    private String author;
    private Date creationDate;
    private Date uploadDate;
    private Time duration;
    private long views;
    private String description;
    private String mime;
    private URL url;

    public Video(int id, String title, String author, Date creationDate, 
            Date uploadDate, Time duration, long views, String description, 
            String mime, URL url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.uploadDate = uploadDate;
        this.duration = duration;
        this.views = views;
        this.description = description;
        this.mime = mime;
        this.url = url;
    }
    
    public Video(String title, String author, Date creationDate, Time duration, 
                String description, String mime, URL url) {
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.duration = duration;
        this.description = description;
        this.mime = mime;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Time getDuration() {        
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
    
    
    
}
