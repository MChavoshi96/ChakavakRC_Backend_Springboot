package com.mkyong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "like")
public class LikeModel
{
    @Id
    private String id;
    private String userId;
    private String commentId;
    private String postId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentid(String commentId) {
        this.commentId = commentId;
    }

}

