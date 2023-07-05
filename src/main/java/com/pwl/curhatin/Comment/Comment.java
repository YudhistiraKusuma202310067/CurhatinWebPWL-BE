package com.pwl.curhatin.Comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.pwl.curhatin.Post.Post;
import com.pwl.curhatin.User.User;

@Entity
@Table(name = "comment")
public class Comment implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Min(value = 1, message = "User ID is required")
    // private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // @Min(value = 1, message = "Post ID is required")
    // private int post_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    @NotEmpty(message = "Comment is required")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date commentDate;

    @PrePersist
    private void onCreate(){
        commentDate = new Date();
    }

    public Comment() {
    }

    public Comment(int id, User user, Post post, @NotEmpty(message = "Comment is required") String comment,
            Date commentDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
