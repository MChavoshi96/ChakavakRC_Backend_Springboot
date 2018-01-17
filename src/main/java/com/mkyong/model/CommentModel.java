package com.mkyong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "comment")
public class CommentModel
{
    @Id
    private String Id;
    private String postId;
    private String userId;
    private String text;
    private Date postDate;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
