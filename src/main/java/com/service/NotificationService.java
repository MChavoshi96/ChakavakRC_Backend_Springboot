package com.service;

import com.model.NotificationModel;
import com.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService
{
    @Autowired
    private INotificationRepository notificationRepository;

    public List<NotificationModel> getUnreadNotifications(String userId)
    {
        List <NotificationModel> result = null;
        try {
            result = notificationRepository.findAllByReadAndFriendRequestAndReceiverIdOrderByHappeningMomentDesc(false, false, userId);
            for (NotificationModel model: result)
            {
                model.setRead(true);
                notificationRepository.save(model);
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<NotificationModel> getUnseenFriendRequests(String userId)
    {
        List <NotificationModel> result = null;
        try {
            result = notificationRepository.findAllByReadAndFriendRequestAndReceiverIdOrderByHappeningMomentDesc(false, true, userId);
            for (NotificationModel model: result)
            {
                model.setRead(true);
                notificationRepository.save(model);
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<NotificationModel> getAllFriendRequests(String userId)
    {
        List <NotificationModel> result = null;
        try {
            result = notificationRepository.findAllByFriendRequestAndReceiverIdOrderByHappeningMomentDesc(true, userId);
            for (NotificationModel model: result)
            {
                if(model.isRead())
                    continue;
                model.setRead(true);
                notificationRepository.save(model);
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }

    public List<NotificationModel> getAllNotifications(String userId)
    {
        List <NotificationModel> result = null;
        try {
            result = notificationRepository.findAllByFriendRequestAndReceiverIdOrderByHappeningMomentDesc(false, userId);
            for (NotificationModel model: result)
            {
                if(model.isRead())
                    continue;
                model.setRead(true);
                notificationRepository.save(model);
            }
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }
}
