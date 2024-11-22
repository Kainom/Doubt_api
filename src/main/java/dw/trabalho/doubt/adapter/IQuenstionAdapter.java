package dw.trabalho.doubt.adapter;

import dw.trabalho.doubt.control.dto.QuestionDto;
import dw.trabalho.doubt.model.Question;

public interface IQuenstionAdapter {
    public QuestionDto tDto(Question question);
    public Question fromDto(QuestionDto questionDto);

}
