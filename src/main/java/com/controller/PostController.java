package com.controller;

import com.model.PostModel;
import com.model.UserModel;
import com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/mongo/posts")
public class PostController
{
    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<PostModel>>  getAllPosts()
    {
        return new ResponseEntity<Collection<PostModel> >(postService.getAllPosts(), HttpStatus.OK);
    }

    // Example-URL = /posts/1234 (masalan id = 1234)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostModel> getPost(@PathVariable("id") String id)
    {
        PostModel post = postService.getPost(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(post, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addOrUpdate")
    public ResponseEntity<PostModel> addOrUpdatePost(@RequestBody PostModel post)
    {
        PostModel result = postService.createOrUpdatePost(post);
        if(result != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<> (HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/attachPhoto/{postId}")
    public ResponseEntity<PostModel> addAttachment(@PathVariable("postId") String postId, @RequestBody MultipartFile photo)
    {
        PostModel result = postService.addAttachment(postId, photo);
        if(result != null)
            return new ResponseEntity<PostModel>(result, HttpStatus.OK);
        else
            return new ResponseEntity<PostModel>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable("id") String id)
    {
        boolean result = postService.removePost(id);

        if(result)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/recent/{offset}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<Collection<PostModel>> getRecentPosts(@PathVariable("offset") Integer offset ,@PathVariable("pageSize") Integer pageSize, @RequestBody String userId)
    {
        List<PostModel> result = null;
        try
        {
            result = postService.getRecentPosts(offset, pageSize, userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/recent/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<PostModel>> getUserPosts(@PathVariable("userId") String userId)
    {
        List <PostModel> result = postService.getPostsOfUser(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/feed/{userId}")
    public ResponseEntity<Collection<PostModel>> getFeed(@PathVariable("userId") String userId)
    {
        List<PostModel> result = postService.loadFeed(userId);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }
}

