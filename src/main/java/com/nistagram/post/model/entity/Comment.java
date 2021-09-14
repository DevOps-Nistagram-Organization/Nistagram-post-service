package com.nistagram.post.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String text;

    @Column
    private String username;

    @Column
    private Date date;

    public Comment(Long id, String text, String username, Date date) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.date = date;
    }

    public Comment() {

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
