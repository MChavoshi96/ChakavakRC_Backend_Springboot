package com.repository;

import com.model.CommentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ICommentRepository extends MongoRepository<CommentModel, String>
{
    List<CommentModel> findAllByPostId(String postId);
    void deleteAllByPostId(String postId);
}

