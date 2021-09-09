package com.nistagram.post.converter;

import com.nistagram.post.model.dto.PostDTO;
import com.nistagram.post.model.entity.Post;

public class PostConverter {
    public static Post toPost(PostDTO dto) {
        Post post = new Post();
        post.setDatePosted(dto.getDatePosted());
        post.setAuthorUsername(dto.getAuthorUsername());
        post.setId(dto.getId());
        post.setImagePath(dto.getImagePath());
        post.setTags(dto.getTags());
        post.setDislikedByUsers(dto.getDislikedByUsers());
        post.setLikedByUsers(dto.getDislikedByUsers());
        post.setFavouredByUsers(dto.getFavouredByUsers());
        return post;
    }

    public static PostDTO toDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setAuthorUsername(post.getAuthorUsername());
        dto.setDatePosted(post.getDatePosted());
        dto.setImagePath(post.getImagePath());
        dto.setId(post.getId());
        dto.setTags(post.getTags());
        dto.setDislikedByUsers(post.getDislikedByUsers());
        dto.setLikedByUsers(post.getLikedByUsers());
        dto.setFavouredByUsers(post.getFavouredByUsers());
        return dto;
    }
}
