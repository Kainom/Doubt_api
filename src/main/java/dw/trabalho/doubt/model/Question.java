package dw.trabalho.doubt.model;

import java.util.Date;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private long question_id;

    @Column(nullable = false)
    private boolean answered;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToMany
    @JoinTable(
        name = "question_tag",
        joinColumns = @JoinColumn(name="question_id", referencedColumnName = "question_id"),
        inverseJoinColumns = @JoinColumn(name="tag_id", referencedColumnName = "tag_id")
    )
    private ArrayList<Tag> tags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private ArrayList<Answer> answers;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
