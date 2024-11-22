package dw.trabalho.doubt.diretor;

import dw.trabalho.doubt.control.dto.QuestionDto;
import dw.trabalho.doubt.model.Question;

public class QuestionDtoDIrector {

    public QuestionDto getDefaultQuestionDto(Question question) {
        return new QuestionDto().builder()
                .answered(question.isAnswered())
                .title(question.getTitle())
                .description(question.getDescription())
                .tags(question.getTags())
                .timestamp(question.getTimestamp())
                .questionId(question.getQuestionId())
                .build();
    }


}
