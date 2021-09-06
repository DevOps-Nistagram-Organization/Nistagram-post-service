package com.nistagram.post.repository;

import com.nistagram.post.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthorUsernameOrderByDatePostedAsc(String authorUsername);
    List<Post> findAllByLikedByUsersContainingOrderByDatePostedAsc(String username);
    List<Post> findAllByDislikedByUsersContainingOrderByDatePostedAsc(String username);
    List<Post> findAllByFavouredByUsersContainingOrderByDatePostedAsc(String username);
    Page<Post> findAllByAuthorUsernameInOrderByDatePostedAsc(Collection<String> authorUsername, Pageable pageable);
}
