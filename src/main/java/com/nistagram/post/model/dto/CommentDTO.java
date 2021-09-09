package com.nistagram.post.model.dto;

import javax.persistence.Column;
import java.util.Date;

public class CommentDTO {
    private Long id;
    private String text;
    private String username;
    private Date date;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String text, String username, Date date) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
