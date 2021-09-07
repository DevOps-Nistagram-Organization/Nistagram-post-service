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

    @GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") Long id) throws Exception {
        Post post = postService.getPost(id);
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @GetMapping(value = "getFeed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getFeed() {
        List<Post> feed = postService.getFeed();
        List<PostDTO> dto = feed.stream().map(PostConverter::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "getMyPosts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getMyPosts() throws Exception {
        List<Post> feed = postService.getMyPosts();
        List<PostDTO> dto = feed.stream().map(PostConverter::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "getLiked", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getLikedPosts() throws Exception {
        List<Post> feed = postService.getLikedPosts();
        List<PostDTO> dto = feed.stream().map(PostConverter::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "getSaved", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getSaved() throws Exception {
        List<Post> feed = postService.getFavouritePosts();
        List<PostDTO> dto = feed.stream().map(PostConverter::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "like", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> likePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.likePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @PostMapping(value = "unlike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> unLikePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.unLikePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @PostMapping(value = "dislike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> dislikePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.dislikePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @PostMapping(value = "undislike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> unDislikePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.unDislikePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @PostMapping(value = "favourite", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> savePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.favouritePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

    @PostMapping(value = "unfavourite", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> unSavePost(@RequestBody PostIdWrapper postIdWrapper) throws Exception {
        Post post = postService.unFavouritePost(postIdWrapper.getId());
        return new ResponseEntity<>(PostConverter.toDTO(post), HttpStatus.OK);
    }

}
