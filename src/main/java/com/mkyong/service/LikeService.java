package com.mkyong.service;

import com.mkyong.model.LikeModel;
import com.mkyong.model.NotificationModel;
import com.mkyong.model.PostModel;
import com.mkyong.repository.ILikeRepository;
import com.mkyong.repository.INotificationRepository;
import com.mkyong.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService
{
    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    public LikeModel sendLike(LikeModel model)
    {
        try
        {
            model = likeRepository.save(model);
            NotificationModel notification = new NotificationModel();
            notification.setRead(false);
            notification.setSenderId(model.getUserId());
            notification.setPostId(model.getPostId());
            PostModel post = postRepository.findOne(model.getPostId());
            notification.setReceiverId(post.getUserId());
            notification.setCommentId(model.getCommentId());
            notification.setFriendRequest(false);
            notification.setHappeningMoment(LocalDateTime.now());
            notificationRepository.save(notification);
            return model;
        }
        catch (Exception ex)
        {
            model = null;
            return model;
        }
    }

    public LikeModel getLikeData(String likeId)
    {
        LikeModel result = null;
        try {
            result = likeRepository.findOne(likeId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public boolean unLike(String likeId)
    {
        try {
            likeRepository.delete(likeId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean removePostLikes(String postId)
    {
        try {
            likeRepository.deleteAllByPostId(postId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public LikeModel findLike(String userId, String postId, String commentId)
    {
        LikeModel result = null;
        try{
            result = likeRepository.findByUserIdAndCommentIdAndPostId(userId, commentId, postId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public int countPostLikes(String postId)
    {
        try {
            return likeRepository.findAllByPostId(postId).size();
        }
        catch (Exception ex)
        {
            return -1;
        }
    }
}
