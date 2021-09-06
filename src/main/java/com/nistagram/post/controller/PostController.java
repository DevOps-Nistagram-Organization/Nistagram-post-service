package com.nistagram.post.controller;

import com.nistagram.post.converter.PostConverter;
import com.nistagram.post.model.dto.CreatePostDTO;
import com.nistagram.post.model.dto.PostDTO;
import com.nistagram.post.model.dto.PostIdWrapper;
import com.nistagram.post.model.entity.Post;
import com.nistagram.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO dto) {
        Post post = postService.createPost(dto);
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

}
