package com.repository;

import com.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface IUserRepository extends MongoRepository<UserModel, String>
{
    UserModel findByUsername(String username);
    List<UserModel> findByUsernameContaining(String searchedPhrase);
    UserModel findById (String id);
}

