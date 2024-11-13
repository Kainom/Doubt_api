package dw.trabalho.doubt.control.dto;

public class  TagAdditionRequestDto {
    private String tagName;
    private Long questionId;

    public TagAdditionRequestDto() {
    }
    public TagAdditionRequestDto(String tagName, Long questionId) {
        this.tagName = tagName;
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagAdditionRequestDto{" +
                "tagName='" + tagName + '\'' +
                ", questionId=" + questionId +
                '}';
    }




    
}