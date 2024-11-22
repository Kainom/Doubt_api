package dw.trabalho.doubt.control.dto;

import java.util.Date;
import java.util.Set;

import dw.trabalho.doubt.model.Answer;
import dw.trabalho.doubt.model.Tag;

public class QuestionDto {
    private Long questionId;
    private boolean answered;
    private String title;
    private String description;
    private Set<Tag> tags; // list of tag names
    private Set<Answer> answers; // list of answer DTOs
    private Date timestamp;

    public QuestionDto() {

    }

    public QuestionDto(Builder builder) {
        this.answered = builder.answered;
        this.title = builder.title;
        this.description = builder.description;
        this.tags = builder.tags;
        this.answers = builder.answers;
        this.timestamp = builder.timestamp;
        this.questionId = builder.questionId;
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

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long questionId;
        private boolean answered;
        private String title;
        private String description;
        private Set<Tag> tags; // list of tag names
        private Set<Answer> answers; // list of answer DTOs
        private Date timestamp;

        public Builder questionId(Long questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder answered(boolean answered) {
            this.answered = answered;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public QuestionDto build() {
            return new QuestionDto(this);
        }

    }

}
