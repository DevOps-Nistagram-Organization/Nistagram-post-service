package com.nistagram.post.service;

import com.nistagram.post.client.UserClient;
import com.nistagram.post.model.dto.CreatePostDTO;
import com.nistagram.post.model.dto.UserInfoDTO;
import com.nistagram.post.model.entity.Post;
import com.nistagram.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserClient userClient;

    public PostService(PostRepository postRepository, UserService userService, UserClient userClient) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userClient = userClient;
    }

    public Post createPost(CreatePostDTO dto) {
        Date date = new Date();
        String authorUsername = userService.getUsername();
        Set<String> tags = new HashSet<>(dto.getTags());
        Post post = new Post(authorUsername, dto.getImagePath(), date, tags);
        return postRepository.save(post);
    }
    
    private boolean hasAccessToPosts(String author) {
        String username = userService.getUsername();
        if (author.equals(username)) {
            return true;
        }
        UserInfoDTO userInfo = userClient.getUser(author);
        return userInfo.getPublicProfile() ||
                userInfo.getFollowers().stream().anyMatch(userInfoDTO -> userInfoDTO.getUsername().equals(username));
    }


    private boolean hasAccess(Post post) {
        UserInfoDTO userInfo = userClient.getUser(post.getAuthorUsername());
        String username = userService.getUsername();
        if (username.equals(userInfo.getUsername())) {
            return true;
        }
        return userInfo.getPublicProfile() ||
                userInfo.getFollowers().stream().anyMatch(userInfoDTO -> userInfoDTO.getUsername().equals(username));
    }

}
