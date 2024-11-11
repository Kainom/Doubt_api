package dw.trabalho.doubt.control.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AnswerDto {
    private Long answerId;


    private String text;

    private Date timestamp;


    public AnswerDto() {

    }

    public AnswerDto(Long answerId, String text, Date timestamp) {
        this.answerId = answerId;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Long getAnswerId() {
        return answerId;
    }
    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
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

    

        

}
