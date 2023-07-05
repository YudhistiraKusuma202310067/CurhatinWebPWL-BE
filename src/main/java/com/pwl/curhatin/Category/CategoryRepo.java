package com.pwl.curhatin.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends CrudRepository<Category, Integer>{
    
    @Query(value = "SELECT a.* FROM category a WHERE a.category_name = :categoryName", nativeQuery = true)
    public Iterable<Category> findCategoryName(@Param("categoryName") String categoryName);

}
