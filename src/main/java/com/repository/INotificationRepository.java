package com.repository;

import com.model.NotificationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface INotificationRepository extends MongoRepository <NotificationModel, String>
{
    List<NotificationModel> findAllByReadAndFriendRequestAndReceiverIdOrderByHappeningMomentDesc(boolean isRead, boolean isFriendRequest, String receiverId);
    List<NotificationModel> findAllByFriendRequestAndReceiverIdOrderByHappeningMomentDesc(boolean isFriendRequest, String receiverId);
    void deleteAllByPostId(String postId);
    void deleteAllByCommentId(String commentId);
    void deleteBySenderIdAndFriendRequestAndReceiverId(String senderId, boolean isFriendRequest, String receiverId);

}
