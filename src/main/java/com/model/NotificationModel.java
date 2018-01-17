package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notification")
public class NotificationModel
{
    @Id
    private String id;
    private String receiverId;
    private boolean isFriendRequest;
    private String senderId;
    private String commentId;
    private String postId;
    private LocalDateTime happeningMoment;
    private boolean isRead;

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isFriendRequest() {
        return isFriendRequest;
    }

    public String getId() {
        return id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setFriendRequest(boolean friendRequest) {
        isFriendRequest = friendRequest;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public LocalDateTime getHappeningMoment() {
        return happeningMoment;
    }

    public void setHappeningMoment(LocalDateTime happeningMoment) {
        this.happeningMoment = happeningMoment;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
