package com.mkyong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Document(collection = "user")
public class UserModel
{
    @Id
    private String id;
    private String name;
    private String quote;
    private String username;
    private Boolean isActive;
    private List<String> tags;
    private MultipartFile profilePicture;
    private MultipartFile coverImage;

    public UserModel()
    {
        id = null;
        name = null;
        quote = null;
        username = null;
        isActive = false;
    }

    public UserModel(String id)
    {
        this();
        this.id = id;
    }

    public UserModel(UserModel model)
    {
        id = model.id;
        name = model.name;
        quote = model.quote;
        username = model.username;
        isActive = model.isActive;
        tags = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActivationStatus(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuote() {
        return quote;
    }

    public String getUsername() {
        return username;
    }

    public boolean getActivationStatus() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag)
    {
        tags.add(tag);
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public MultipartFile getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(MultipartFile coverImage) {
        this.coverImage = coverImage;
    }
}
