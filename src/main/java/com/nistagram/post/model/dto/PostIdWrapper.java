package com.nistagram.post.model.dto;

public class PostIdWrapper {
    private Long id;

    public PostIdWrapper() {
    }

    public PostIdWrapper(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
