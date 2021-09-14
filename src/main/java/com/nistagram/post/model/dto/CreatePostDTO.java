package com.nistagram.post.model.dto;

import java.util.List;

public class CreatePostDTO {
    private String imagePath;
    private List<String> tags;

    public CreatePostDTO() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
