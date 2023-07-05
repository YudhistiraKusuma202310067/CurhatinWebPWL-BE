package com.pwl.curhatin.Category;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category){
        return categoryRepo.save(category);
    }

    public Category findOne(int id){
        return categoryRepo.findById(id).get();
    }    

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public void removeOne(int id){
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findCategoryName(String categoryName) {
        return categoryRepo.findCategoryName(categoryName);
    }
}
