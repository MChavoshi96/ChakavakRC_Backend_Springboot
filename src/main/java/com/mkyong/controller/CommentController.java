package com.mkyong.controller;

import com.mkyong.model.CommentModel;
import com.mkyong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/mongo/comments")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public ResponseEntity< Collection<CommentModel> > getPostComments(@PathVariable("postId") String postId)
    {
        List <CommentModel> result = commentService.getPostComments(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public ResponseEntity<CommentModel> addOrEditComment(@RequestBody CommentModel model)
    {
        CommentModel result = commentService.addOrEditComment(model);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/remove/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeComment(@PathVariable("commentId") String commentId)
    {
        boolean result = commentService.removeComment(commentId);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public ResponseEntity<CommentModel> getComment(@PathVariable("commentId") String commentId)
    {
        CommentModel result = commentService.getComment(commentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/postCount/{id}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getNumberOfComments(@PathVariable("id") String postId)
    {
        int result = commentService.countPostComments(postId);
        if(result != -1)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }
}