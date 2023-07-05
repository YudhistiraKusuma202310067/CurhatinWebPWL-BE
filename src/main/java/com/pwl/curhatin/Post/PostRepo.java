package com.pwl.curhatin.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepo extends CrudRepository<Post, Integer>{
    
    @Query(value = "SELECT a.* FROM post a WHERE a.category_id = :category_id", nativeQuery = true)
    public Iterable<Post> findPostByCategory(@Param("category_id") int category_id);
}
