package com.pwl.curhatin.Post;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
// import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.pwl.curhatin.Category.Category;
import com.pwl.curhatin.Comment.Comment;
import com.pwl.curhatin.User.User;

@Entity
@Table(name = "post")
public class Post implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Min(value = 1, message = "User ID is required")
    // private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // @Min(value = 1, message = "Category ID is required")
    // private int category_id;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Lob
    @NotEmpty(message = "Story is required")
    private String story;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date postDate;

    //Untuk fungsi delete
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @PrePersist
    private void onCreate(){
        postDate = new Date();
    }

    public Post() {
    }

    public Post(int id, User user, Category category, @NotEmpty(message = "Story is required") String story,
            Date postDate) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.story = story;
        this.postDate = postDate;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    
    
}
