package com.service;

import com.model.CommentModel;
import com.model.NotificationModel;
import com.model.PostModel;
import com.repository.ICommentRepository;
import com.repository.ILikeRepository;
import com.repository.INotificationRepository;
import com.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService
{
    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    public List<CommentModel> getPostComments(String postId)
    {
        List<CommentModel> result = null;
        try
        {
            result = commentRepository.findAllByPostId(postId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public CommentModel addOrEditComment(CommentModel model)
    {
        try
        {
            boolean added = (model.getId() == null);
            model = commentRepository.save(model);
            if(added)
            {
                NotificationModel notification = new NotificationModel();
                notification.setRead(false);
                notification.setPostId(model.getPostId());
                notification.setCommentId(model.getId());
                notification.setFriendRequest(false);
                notification.setSenderId(model.getUserId());
                PostModel post = postRepository.findOne(model.getPostId());
                notification.setReceiverId(post.getUserId());
                if(notification.getSenderId() == notification.getReceiverId())
                    return model;
                notification.setHappeningMoment(LocalDateTime.now());
                notificationRepository.save(notification);
            }
            return model;
        }
        catch (Exception ex)
        {
            model = null;
            return model;
        }
    }

    public boolean removeComment(String commentId)
    {
        try {

            commentRepository.delete(commentId);
            likeRepository.deleteAllByCommentId(commentId);
            notificationRepository.deleteAllByCommentId(commentId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public CommentModel getComment(String commentId)
    {
        CommentModel result = null;
        try {
            result = commentRepository.findOne(commentId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public boolean removePostComments(String postId)
    {
        try {
            commentRepository.deleteAllByPostId(postId);
            likeRepository.deleteAllByPostId(postId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public int countPostComments(String postId)
    {
        try {
            return getPostComments(postId).size();
        }
        catch (Exception ex)
        {
            return -1;
        }
    }
}
