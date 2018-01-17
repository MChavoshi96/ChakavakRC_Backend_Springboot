package com.service;

import com.model.UserFriendModel;
import com.model.UserModel;
import com.repository.IUserFriendRepository;
import com.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserFriendRepository userFriendRepository;

    public List<UserModel> getAllUsers()
    {
        List<UserModel> result = userRepository.findAll();
        return result;
    }

    public UserModel getUser(String id)
    {
        UserModel model = null;
        try{
            model = userRepository.findById(id);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }

    public UserModel addOrUpdateUser(UserModel model)
    {
        try
        {
            boolean added = false;
            if(model.getId() == null)
                added = true;
            else
                added = userRepository.exists(model.getId()) == false;
            model = userRepository.save(model);
            if(added)
            {
                UserFriendModel selfFriendship = new UserFriendModel();
                selfFriendship.setConfirmed(true);
                selfFriendship.setUserId(model.getId());
                selfFriendship.setFriendId(model.getId());
                userFriendRepository.save(selfFriendship);
            }
            return model;
        }
        catch (Exception ex)
        {
            model = null;
            return model;
        }
    }

    public UserModel addProfilePicture(String userId, MultipartFile profilePic)
    {
        UserModel model = null;
        try
        {
            model = userRepository.findById(userId);
            model.setProfilePicture(profilePic);
            model = userRepository.save(model);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }

    public UserModel addCoverImage(String userId, MultipartFile coverImage)
    {
        UserModel model = null;
        try {
            model = userRepository.findById(userId);
            model.setCoverImage(coverImage);
            model = userRepository.save(model);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }

    public boolean deleteUser(String id)
    {
        try
        {
            userRepository.delete(id);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public UserModel getUserByUsername(String username)
    {
        UserModel model = null;
        try
        {
            model = userRepository.findByUsername(username);
            return model;
        }
        catch (Exception ex)
        {
            return model;
        }
    }

    public List<UserModel> searchAllUsers(String username)
    {
        List <UserModel> result = null;
        try
        {
            result = userRepository.findByUsernameContaining(username);
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }

    }
}
