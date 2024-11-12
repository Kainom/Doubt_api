package dw.trabalho.doubt.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.control.dto.AnswerDto;
import dw.trabalho.doubt.model.Answer;
import dw.trabalho.doubt.model.Question;
import dw.trabalho.doubt.repository.AnswerRepository;
import dw.trabalho.doubt.repository.QuestionRepository;
import dw.trabalho.doubt.repository.UserRepository;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<AnswerDto>> getAnswerByQuestion(@PathVariable Long id) {
        List<Answer> answers = answerRepository.findByQuestionId(id);

        if (answers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<AnswerDto> answerDtos = new ArrayList<>();
        for (Answer answer : answers) {
            answerDtos.add(new AnswerDto(answer.getAnswerId(), answer.getText(), answer.getTimestamp()));
        }
        return ResponseEntity.ok(answerDtos);

    }

    @PostMapping("/")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        System.out.println(answer);
        Question question = questionRepository.findById(answer.getQuestion().getQuestionId()).orElse(null);
        if (question == null) {
            return ResponseEntity.badRequest().build();
        }

        if(question.getUser().getUserId()!= answer.getUser().getUserId())
        return ResponseEntity.status(403).build();


        answer.setTimestamp(new Date());
        question.setAnswered(true);
        questionRepository.save(question);
        return ResponseEntity.ok(answerRepository.save(answer));

    }

    @PutMapping("/")
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) {
        Answer existingAnswer = answerRepository.findById(answer.getAnswerId()).orElse(null);

        if (existingAnswer == null)
            return ResponseEntity.notFound().build();

        if (!(existingAnswer.getText().equals(answer.getText())))
            existingAnswer.setText(answer.getText());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElse(null);
       
        if (answer == null)
            return ResponseEntity.notFound().build();

        answerRepository.delete(answer);
        return ResponseEntity.noContent().build();

    }
}
