package com.mkyong.repository;

import com.mkyong.model.UserFriendModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IUserFriendRepository extends MongoRepository<UserFriendModel, String>
{
    List<UserFriendModel> findAllByUserId(String userId);
    UserFriendModel findByUserIdAndFriendId(String userId, String friendId);
}
