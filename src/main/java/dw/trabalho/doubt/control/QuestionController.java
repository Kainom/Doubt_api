package dw.trabalho.doubt.control;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.control.dto.TagAdditionRequestDto;
import dw.trabalho.doubt.model.Question;
import dw.trabalho.doubt.model.Tag;
import dw.trabalho.doubt.repository.QuestionRepository;
import dw.trabalho.doubt.repository.TagRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> questionByTitle(@RequestParam String search) {
        List<Question> questions;
        questions = questionRepository.findQuestionsContainingText(search);

    
        if(questions.isEmpty()) {
            questions = questionRepository.findByTagName(search);
            if(questions.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(questions);
    }

    @PostMapping("/")
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        System.out.println(question);
        if (question.getTitle() == null || question.getDescription() == null)
            return ResponseEntity.badRequest().body("Title and description cannot be null or empty");

        return ResponseEntity.ok(questionRepository.save(question));
    }

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<?> answerQuestion(@PathVariable Long questionId, Question question) {
        Question existingQuestion = questionRepository.findById(questionId).orElse(null);
        if (existingQuestion == null)
            return ResponseEntity.notFound().build();

        existingQuestion.setAnswered(true);
        // existingQuestion.getAnswers().add(question) ;
        return ResponseEntity.ok(questionRepository.save(existingQuestion));
    }

    @PostMapping("/tag")

    public ResponseEntity<?> addTagToQuestion(@RequestBody TagAdditionRequestDto requestDto) {
        System.out.println(requestDto.getTagName());
        Question existingQuestion = questionRepository.findById(requestDto.getQuestionId()).orElse(null);
        if (existingQuestion == null)
            return ResponseEntity.notFound().build();

        Tag existingTag = tagRepository.findByTagName(requestDto.getTagName());
        if (existingTag == null)
            return ResponseEntity.notFound().build();

        if (existingQuestion.getTags().contains(existingTag))
            return ResponseEntity.ok("Tag already exists in the question");

        existingQuestion.getTags().add(tagRepository.findByTagName(requestDto.getTagName()));
        return ResponseEntity.ok(questionRepository.save(existingQuestion));
    }

}
