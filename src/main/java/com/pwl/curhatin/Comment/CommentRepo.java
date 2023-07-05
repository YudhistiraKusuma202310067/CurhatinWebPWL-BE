package com.pwl.curhatin.Comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepo extends CrudRepository<Comment, Integer>{
    
    @Query(value = "SELECT a.* FROM comment a WHERE a.post_id = :post_id", nativeQuery = true)
    public Iterable<Comment> findCommentPost(@Param("post_id") int post_id);
}
