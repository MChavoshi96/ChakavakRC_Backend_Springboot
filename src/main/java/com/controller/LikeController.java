package com.controller;

import com.model.LikeModel;
import com.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mongo/likes")
public class LikeController
{
    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "/sendLike", method = RequestMethod.POST)
    public ResponseEntity<LikeModel> likePost(@RequestBody LikeModel model)
    {
        if(likeService.findLike(model.getUserId(), model.getPostId(), model.getCommentId()) != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        LikeModel result = likeService.sendLike(model);
        if(result != null)
            return new ResponseEntity<> (result, HttpStatus.OK);
        else
            return new ResponseEntity<> (result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/unlike/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unlike(@PathVariable("id") String id)
    {
        boolean result = likeService.unLike(id);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> isLikeGiven(@RequestBody LikeModel model)
    {
        boolean result = (likeService.findLike(model.getUserId(), model.getPostId(), model.getCommentId()) != null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/countPostLikes", method = RequestMethod.GET)
    public ResponseEntity<?> countLikes(@RequestBody String postId)
    {
        int result = likeService.countPostLikes(postId);
        if(result != -1)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }
}
