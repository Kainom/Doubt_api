package dw.trabalho.doubt.control.dto;

import java.util.Date;
import java.util.Set;

import dw.trabalho.doubt.model.Tag;

public class QuestionAllDto {
    private Long questionId;
    private String title;
    private String description;
    private Set<Tag> tags;
    private Date timestamp;
    private boolean answered;

    public QuestionAllDto(Long questionId, String title, String description, Set<Tag> tags, Date timestamp, boolean answered) {
        this.questionId = questionId;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.timestamp = timestamp;
        this.answered = answered; // Assuming all questions are not answered by default
    }

    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public boolean isAnswered() {
        return answered;
    }
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

}
