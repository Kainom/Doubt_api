package dw.trabalho.doubt.adapter;

import dw.trabalho.doubt.control.dto.QuestionDto;
import dw.trabalho.doubt.model.Question;

public class QuestionAdapter implements IQuenstionAdapter {
    @Override
    public QuestionDto tDto(Question question) {
        return new QuestionDto().builder()
                .answered(question.isAnswered())
                .title(question.getTitle())
                .description(question.getDescription())
                .tags(question.getTags())
                .timestamp(question.getTimestamp())
                .questionId(question.getQuestionId())
                .build();

    }

    @Override
    public Question fromDto(QuestionDto questionDto) {
        return new Question(
                questionDto.isAnswered(),
                questionDto.getTitle(),
                questionDto.getDescription(),
                questionDto.getTags(),
                null,
                questionDto.getTimestamp(),
                null);
    }
}