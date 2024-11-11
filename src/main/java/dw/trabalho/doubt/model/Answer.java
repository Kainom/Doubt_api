package dw.trabalho.doubt.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false, length = 50)
    private String text;

    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Answer() {

    }

    public Answer(Question question, String text, Date timestamp, User user) {
        this.question = question;
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;

    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answer_id) {
        this.answerId = answer_id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer_id=" + answerId +
                ", question=" + question +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
