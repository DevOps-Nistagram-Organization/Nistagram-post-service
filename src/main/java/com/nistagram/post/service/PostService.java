package com.nistagram.post.service;

import com.nistagram.post.client.UserClient;
import com.nistagram.post.model.dto.AddCommentDTO;
import com.nistagram.post.model.dto.CreatePostDTO;
import com.nistagram.post.model.dto.PostIdWrapper;
import com.nistagram.post.model.dto.UserInfoDTO;
import com.nistagram.post.model.entity.Comment;
import com.nistagram.post.model.entity.Post;
import com.nistagram.post.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, UserService userService, UserClient userClient, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userClient = userClient;
        this.commentRepository = commentRepository;
    }

    public Post createPost(CreatePostDTO dto) {
        Date date = new Date();
        String authorUsername = userService.getUsername();
        Set<String> tags = new HashSet<>(dto.getTags());
        Post post = new Post(authorUsername, dto.getImagePath(), date, tags);
        return postRepository.save(post);
    }

    public Post getPost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        if (hasAccess(post)) {
            return post;
        }
        throw new Exception("No access to post");
    }
    public List<Post> getFavouritePosts() throws Exception {
        String username = userService.getUsername();
        // TODO: Double check query
        return postRepository.findAllByFavouredByUsersContainingOrderByDatePostedAsc(username);
    }

    public List<Post> getLikedPosts() throws Exception {
        String username = userService.getUsername();
        // TODO: Double check query
        return postRepository.findByLikedByUsersContaining(username);
    }

    public List<Post> getMyPosts() throws Exception {
        String username = userService.getUsername();
        return getUsersPost(username);
    }

    public List<Post> getUsersPost(String username) throws Exception {
        if (hasAccessToPosts(username)) {
            return postRepository.findAllByAuthorUsernameOrderByDatePostedAsc(username);
        }
        throw new Exception("No access to post");
    }

    public List<Post> getFeed() {
        String username = userService.getUsername();
        UserInfoDTO userInfo = userClient.getUser(username);
        List<String> following = userInfo.getFollowing().stream().map(UserInfoDTO::getUsername).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> posts = postRepository.findAllByAuthorUsernameInOrderByDatePostedAsc(following, pageable);
        return posts.toList();
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

    public Post likePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getDislikedByUsers().remove(username);
            post.getLikedByUsers().add(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post unLikePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getLikedByUsers().remove(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post dislikePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getLikedByUsers().remove(username);
            post.getDislikedByUsers().add(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post unDislikePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getDislikedByUsers().remove(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post favouritePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getFavouredByUsers().add(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post unFavouritePost(Long id) throws Exception {
        Post post = postRepository.getById(id);
        String username = userService.getUsername();
        if (hasAccess(post)) {
            post.getFavouredByUsers().remove(username);
            return postRepository.save(post);
        }
        throw new Exception("No access to post");
    }

    public Post addComment(AddCommentDTO dto) throws Exception {
        Post post = postRepository.getById(dto.getPostId());
        String username = userService.getUsername();
        if (!hasAccess(post)) {
            throw new Exception("No access to post");
        }
        Comment comment = new Comment(null, dto.getCommentText(), username, new Date());
        comment = commentRepository.save(comment);
        post.getComments().add(comment);
        return postRepository.save(post);
    }

    public Boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    public List<Post> searchByTags(String tag) {
        return postRepository.findAllByTags(tag);
    }
}
