package com.nistagram.post.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String authorUsername;
    @Column(nullable = false)
    private String imagePath;
    @Column
    private Date datePosted;

    @OneToMany
    private Set<Comment> comments;

    @ElementCollection
    private Set<String> tags;
    @ElementCollection
    private Set<String> likedByUsers;
    @ElementCollection
    private Set<String> dislikedByUsers;
    @ElementCollection
    private Set<String> favouredByUsers;

    public Post() {
    }

    public Post(String authorUsername, String imagePath, Date datePosted, Set<String> tags) {
        this.authorUsername = authorUsername;
        this.imagePath = imagePath;
        this.datePosted = datePosted;
        this.tags = tags;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
