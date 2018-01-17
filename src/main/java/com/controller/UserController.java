package com.controller;

import com.model.UserModel;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/api/mongo/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity< Collection<UserModel> > getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") String id)
    {
        UserModel user = userService.getUser(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public ResponseEntity<UserModel> addOrUpdateUser(@RequestBody UserModel user) {
        UserModel result = userService.addOrUpdateUser(user);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value = "/addProfilePicture/{userId}", method = RequestMethod.POST)
    public ResponseEntity<UserModel> addProfilePicture(@PathVariable("userId") String userId, @RequestBody MultipartFile photo)
    {
        UserModel result = userService.addProfilePicture(userId, photo);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/addCoverImage/{userId}", method = RequestMethod.POST)
    public ResponseEntity<UserModel> addCoverImage(@PathVariable("userId") String userId, @RequestBody MultipartFile photo)
    {
        UserModel result = userService.addCoverImage(userId, photo);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id)
    {
        boolean result = userService.deleteUser(id);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "getUserByUsername/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getUserByUsername(@PathVariable("username") String username)
    {
        UserModel model = userService.getUserByUsername(username);
        if(model != null)
            return new ResponseEntity<>(model, HttpStatus.OK);
        else
            return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "searchUsername/{searchedPhrase}", method = RequestMethod.GET)
    public ResponseEntity< Collection<UserModel> > searchUsername(@PathVariable("searchedPhrase") String searchedPhrase)
    {
        return new ResponseEntity<>(userService.searchAllUsers(searchedPhrase), HttpStatus.OK);
    }
}

