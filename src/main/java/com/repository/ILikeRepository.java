package com.repository;

import com.model.LikeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ILikeRepository extends MongoRepository<LikeModel, String>
{
    List<LikeModel> findAllByPostId(String postId);
    void deleteAllByPostId(String postId);
    void deleteAllByCommentId(String commentId);
    LikeModel findByUserIdAndCommentIdAndPostId(String userId, String commentId, String postId);
}

