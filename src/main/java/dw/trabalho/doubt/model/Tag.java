package dw.trabalho.doubt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name="tag_name",nullable = false, length = 30,unique = true)
    private String tagName;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags",
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH,
    }
    )
    private Set<Question> questions = null;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "tag-user")
    private User user;



    public Tag() {
    }


    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getTagName() {
        return tagName;
    }

    public Long getId() {
        return tagId;
    }
    public void setId(Long id) {
        this.tagId = id;
    }

    public Set<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }



    public void add(Question question) {
        if (questions == null) {
            questions = new HashSet<>();
        }
        questions.add(question);
    }




}   
