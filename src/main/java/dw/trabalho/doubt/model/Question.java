package dw.trabalho.doubt.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long questionId;

    @Column(nullable = false)
    private boolean answered;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "question_tag", joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "question_id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id"))
    private Set<Tag> tags;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    public Question() {

    }

    public Question(boolean answered, String title, String description, Set<Tag> tags, Set<Answer> answers,
            Date timestamp, User user) {
        this.answered = answered;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.answers = answers;
        this.timestamp = timestamp;
        this.user = user;

    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long question_id) {
        this.questionId = question_id;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
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

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getQuestions().add(this);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", answered=" + answered +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", answers=" + answers +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }

}
