package com.pwl.curhatin.Post;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService {
    
    @Autowired
    private PostRepo postRepo;

    public Post save(Post post){
        return postRepo.save(post);
    }

    public Post findOne(int id){
        return postRepo.findById(id).get();
    }    

    public Iterable<Post> findAll(){
        return postRepo.findAll();
    }

    public void removeOne(int id){
        postRepo.deleteById(id);
    }

    public Iterable<Post> findPostByCategory(int category_id) {
        return postRepo.findPostByCategory(category_id);
    }

    // public Iterable<Post> deletePostComment(int id) {
    //     return postRepo.deletePostComment(id);
    // }
}
