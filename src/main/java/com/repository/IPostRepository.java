package com.repository;

import com.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPostRepository extends MongoRepository<PostModel, String>
{
    List<PostModel> findAllByUserId(String userId);
    Page<PostModel> findAll(Pageable pageable);
    Page<PostModel> findAllByUserId(String userId, Pageable pageable);
}

