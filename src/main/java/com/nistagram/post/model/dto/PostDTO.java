package com.nistagram.post.model.dto;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.Set;

public class PostDTO {
    private Long id;
    private String authorUsername;
    private String imagePath;
    private Date datePosted;
    private Set<String> tags;
    private Set<String> likedByUsers;
    private Set<String> dislikedByUsers;
    private Set<String> favouredByUsers;
    private Set<CommentDTO> comments;
    public PostDTO() {
    }

    public PostDTO(Long id, String authorUsername, String imagePath, Date datePosted, Set<String> tags, Set<String> likedByUsers, Set<String> dislikedByUsers, Set<String> favouredByUsers) {
        this.id = id;
        this.authorUsername = authorUsername;
        this.imagePath = imagePath;
        this.datePosted = datePosted;
        this.tags = tags;
        this.likedByUsers = likedByUsers;
        this.dislikedByUsers = dislikedByUsers;
        this.favouredByUsers = favouredByUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<String> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public Set<String> getDislikedByUsers() {
        return dislikedByUsers;
    }

    public void setDislikedByUsers(Set<String> dislikedByUsers) {
        this.dislikedByUsers = dislikedByUsers;
    }

    public Set<String> getFavouredByUsers() {
        return favouredByUsers;
    }

    public void setFavouredByUsers(Set<String> favouredByUsers) {
        this.favouredByUsers = favouredByUsers;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }
}
