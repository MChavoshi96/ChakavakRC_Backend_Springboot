package com.mkyong.service;

import com.mkyong.model.NotificationModel;
import com.mkyong.model.UserFriendModel;
import com.mkyong.model.UserModel;
import com.mkyong.repository.INotificationRepository;
import com.mkyong.repository.IUserFriendRepository;
import com.mkyong.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserFriendService
{
    @Autowired
    private IUserFriendRepository userFriendRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IUserRepository userRepository;

    private List<UserFriendModel> getUsersAllSentRequests(String userId)
    {
        List<UserFriendModel> result = null;
        try
        {
            result = userFriendRepository.findAllByUserId(userId);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<UserModel> getUsersAllFriends(String userId)
    {
        List<UserModel> result = new LinkedList<UserModel>();
        try
        {
            List <UserFriendModel> sentRequests = getUsersAllSentRequests(userId);
            for (UserFriendModel friendship : sentRequests) {
                if (friendship.isConfirmed()){
                    UserModel user = userRepository.findById(friendship.getFriendId());
                    result.add(user);
                }
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public boolean areTheyFriends(String userId, String friendId)
    {
        try
        {
            UserFriendModel firstDirection = userFriendRepository.findByUserIdAndFriendId(userId, friendId);
            UserFriendModel secondDirection = userFriendRepository.findByUserIdAndFriendId(friendId, userId);
            if(firstDirection == null || secondDirection == null)
                return false;
            if(!firstDirection.isConfirmed() || !secondDirection.isConfirmed())
                return false;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean acceptRequest(String requestId)
    {
        try
        {
            UserFriendModel model = userFriendRepository.findOne(requestId);
            model.setConfirmed(true);
            userFriendRepository.save(model);
            UserFriendModel otherDirection = new UserFriendModel();
            otherDirection.setConfirmed(true);
            otherDirection.setFriendId(model.getUserId());
            otherDirection.setUserId(model.getFriendId());
            otherDirection = userFriendRepository.save(otherDirection);
            notificationRepository.deleteBySenderIdAndFriendRequestAndReceiverId(model.getUserId(), true, model.getFriendId());
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public UserFriendModel sendRequest(String senderId, String receiverId)
    {
        UserFriendModel model = null;
        try
        {
            UserFriendModel request = new UserFriendModel();
            request.setUserId(senderId);
            request.setFriendId(receiverId);
            request.setConfirmed(false);
            model = userFriendRepository.save(request);
            NotificationModel notification = new NotificationModel();
            notification.setFriendRequest(true);
            notification.setRead(false);
            notification.setSenderId(senderId);
            notification.setReceiverId(receiverId);
            notification.setHappeningMoment(LocalDateTime.now());
            notificationRepository.save(notification);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }

    public boolean isFriendRequestSent(String sender, String receiver)
    {
        try {
            UserFriendModel model = userFriendRepository.findByUserIdAndFriendId(sender, receiver);
            if(model == null)
                return false;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
