package com.mkyong.service;

import com.mkyong.model.PostModel;
import com.mkyong.model.UserFriendModel;
import com.mkyong.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService
{
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    private IUserFriendRepository userFriendRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    public List<PostModel> getAllPosts()
    {
        List<PostModel> result = null;
        try
        {
            result = postRepository.findAll();
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<PostModel> getPostsOfUser(String userId)
    {
        List<PostModel> result = null;
        try
        {
            result = postRepository.findAllByUserId(userId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public boolean removePost(String postId)
    {
        try
        {
            postRepository.delete(postId);
            commentRepository.deleteAllByPostId(postId);
            likeRepository.deleteAllByPostId(postId);
            notificationRepository.deleteAllByPostId(postId);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public PostModel getPost(String postId)
    {
        PostModel result = null;
        try {
            result = postRepository.findOne(postId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public PostModel createOrUpdatePost(PostModel model)
    {
        try
        {
            model = postRepository.save(model);
            return model;
        }
        catch (Exception ex)
        {
            model = null;
            return model;
        }
    }

    public List<PostModel> getRecentPosts(int offset, int pageSize, String userId)
    {
        List<PostModel> result = null;
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "publicationTime"));
        Pageable pageable = new PageRequest(offset, pageSize, sort);
        try {
            result = postRepository.findAllByUserId(userId, pageable).getContent();
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<PostModel> loadFeed (String userId)
    {
        List <PostModel> result = null;
        try {
            result = new ArrayList<>();
            List <UserFriendModel> friends = userFriendRepository.findAllByUserId(userId);
            for (UserFriendModel friend: friends)
            {
                result.addAll(postRepository.findAllByUserId(friend.getId()));
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public PostModel addAttachment(String postId, MultipartFile photo)
    {
        PostModel model = null;
        try {
            model = postRepository.findOne(postId);
            model.setImage(photo);
            model = postRepository.save(model);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }
}
