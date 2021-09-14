package com.nistagram.post.repository;

import com.nistagram.post.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthorUsernameOrderByDatePostedAsc(String authorUsername);
    @Query("select p from Post p where ?1 member of p.likedByUsers order by p.datePosted asc ")
    List<Post> findByLikedByUsersContaining(String username);
    @Query("select p from Post p where ?1 member of p.dislikedByUsers order by p.datePosted asc ")
    List<Post> findAllByDislikedByUsersContainingOrderByDatePostedAsc(String username);
    @Query("select p from Post p where ?1 member of p.favouredByUsers order by p.datePosted asc ")
    List<Post> findAllByFavouredByUsersContainingOrderByDatePostedAsc(String username);
    Page<Post> findAllByAuthorUsernameInOrderByDatePostedAsc(Collection<String> authorUsername, Pageable pageable);
}
