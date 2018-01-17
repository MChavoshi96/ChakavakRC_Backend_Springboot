package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Document(collection = "post")
public class PostModel
{
    @Id
    private String id;
    private String username;
    private String description;
    private String userId;
    private MultipartFile image;
    private Date publicationTime;
    private GeoLocationModel location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) { this.id = id; }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public MultipartFile getImage() { return image; }

    public void setImage(MultipartFile image) { this.image = image; }

    public GeoLocationModel getLocation() {
        return location;
    }

    public void setLocation(GeoLocationModel location) {
        this.location = location;
    }
}