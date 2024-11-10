package dw.trabalho.doubt.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.model.Answer;
import dw.trabalho.doubt.repository.AnswerRepository;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    
    @Autowired
    private AnswerRepository answerRepository;



    @GetMapping("/{id}")
    public ResponseEntity<List<Answer>> getAnswerByQuestion(@PathVariable Long id) {
        List<Answer> answers = answerRepository.findByQuestionId(id);
        if(answers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(answers);


    }

    @PostMapping("/{questionId}")
    public Answer createAnswer(@PathVariable Long questionId, @RequestBody Answer answer) {
        // answer.setQuestion(questionRepository.findById(questionId).orElse(null));
        // return answerRepository.save(answer);
        return null; // replace with actual implementation
    }
}
