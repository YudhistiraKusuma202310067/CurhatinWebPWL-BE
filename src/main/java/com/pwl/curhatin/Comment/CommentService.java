package com.pwl.curhatin.Comment;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    public Comment save(Comment comment){
        return commentRepo.save(comment);
    }

    public Comment findOne(int id){
        return commentRepo.findById(id).get();
    }    

    public Iterable<Comment> findAll(){
        return commentRepo.findAll();
    }

    public void removeOne(int id){
        commentRepo.deleteById(id);
    }

    public Iterable<Comment> findCommentPost(int post_id) {
        return commentRepo.findCommentPost(post_id);
    }
}
