package com.mkyong.controller;

import com.mkyong.model.UserFriendModel;
import com.mkyong.model.UserModel;
import com.mkyong.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/mongo/userFriend")
public class UserFriendController
{
    @Autowired
    private UserFriendService userFriendService;

    @RequestMapping(value = "/sendRequest", method = RequestMethod.POST)
    public ResponseEntity<UserFriendModel> sendFriendRequest(@RequestBody String userId, @RequestBody String friendId)
    {
        UserFriendModel result = userFriendService.sendRequest(userId, friendId);
        if (result != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/friendList/{userId}", method = RequestMethod.GET)
    public ResponseEntity< Collection<UserModel> > getAllFriends(String userId)
    {
        List <UserModel> result = userFriendService.getUsersAllFriends(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public ResponseEntity<?> acceptRequest(@RequestBody String requestId)
    {
        boolean result = userFriendService.acceptRequest(requestId);
        if(result)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/friendshipCheck/{userId}/{friendId}", method = RequestMethod.GET)
    public ResponseEntity<?> areTheyFriends(@PathVariable String userId,@PathVariable String friendId)
    {
        boolean result = userFriendService.areTheyFriends(userId, friendId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestCheck/{senderId}/{receiverId}", method = RequestMethod.GET)
    public ResponseEntity<?> isRequestSent(@PathVariable String senderId,@PathVariable String receiverId)
    {
        boolean result = userFriendService.isFriendRequestSent(senderId, receiverId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
