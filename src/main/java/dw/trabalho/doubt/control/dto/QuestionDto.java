package dw.trabalho.doubt.control.dto;

import java.util.Date;
import java.util.Set;

import dw.trabalho.doubt.model.Answer;
import dw.trabalho.doubt.model.Tag;

public class    QuestionDto {
    private Long questionId;
    private boolean answered;
    private String title;
    private String description;
    private Set<Tag> tags; // list of tag names
    private Set<Answer> answers; // list of answer DTOs
    private Date timestamp;

    public QuestionDto() {

    }

    

    public QuestionDto(boolean answered, String title, String description, Set<Tag> tags) {
        this.answered = answered;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public QuestionDto(Long questionId,String title,String description,Set<Tag> tags, Date timestamp) {
        this.questionId = questionId;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.timestamp = timestamp;
    }




    public QuestionDto(boolean answered, String title, String description, Set<Tag> tags,Date timestamp) {
        this.answered = answered;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.timestamp = timestamp;
    }

    public QuestionDto(boolean answered, String title, String description, Set<Tag> tags,Date timestamp,Long questionId) {
        this.answered = answered;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.timestamp = timestamp;
        this.questionId = questionId;
    }

    public QuestionDto(boolean answered, String title, String description, Set<Tag> tags,Date timestamp,Long questionId, Set<Answer> answers) {
        this.answered = answered;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.timestamp = timestamp;
        this.questionId = questionId;
        this.answers = answers;
    }









  

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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


}
