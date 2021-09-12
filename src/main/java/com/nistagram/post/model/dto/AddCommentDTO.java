package com.nistagram.post.model.dto;

public class AddCommentDTO {
    private Long postId;
    private String commentText;

    public AddCommentDTO() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
