package com.nistagram.post.converter;

import com.nistagram.post.model.dto.CommentDTO;
import com.nistagram.post.model.entity.Comment;

public class CommentConverter {

    public static CommentDTO toDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setDate(comment.getDate());
        dto.setUsername(comment.getUsername());
        return dto;
    }
}
