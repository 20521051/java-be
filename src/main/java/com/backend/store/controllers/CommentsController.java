package com.backend.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.store.models.User;
import com.backend.store.services.CommentService;
import com.backend.store.services.UserService;
import com.backend.store.models.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentsController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public Comment createComment(@RequestBody CreateCommentDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        return commentService.createComment(dto, user.getUserId());
    }
}
