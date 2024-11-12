package dw.trabalho.doubt.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.control.dto.QuestionAllDto;
import dw.trabalho.doubt.control.dto.QuestionDto;
import dw.trabalho.doubt.control.dto.TagAdditionRequestDto;
import dw.trabalho.doubt.model.Question;
import dw.trabalho.doubt.model.Tag;
import dw.trabalho.doubt.model.User;
import dw.trabalho.doubt.repository.QuestionRepository;
import dw.trabalho.doubt.repository.TagRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<?>> getAllQuestions(@PathVariable Long userId) {
        List<Question> questions = questionRepository.findQuestionsByUserId(userId);
        List<QuestionAllDto> questionDtos = new ArrayList<>();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        for (Question question : questions) {

            questionDtos.add(new QuestionAllDto(
                    question.getQuestionId(), question.getTitle(), question.getDescription(), question.getTags(),
                    question.getTimestamp(), question.isAnswered()));
        }

        return ResponseEntity.ok(questionDtos);
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<QuestionAllDto> getQuestionById(@PathVariable Long id) {
        try {
            Question question = questionRepository.findById(id).orElse(null);
            
            if (question == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            QuestionAllDto questionDto = new QuestionAllDto(
                question.getQuestionId(), question.getTitle(),
                question.getDescription(), question.getTags(),
                question.getTimestamp(), question.isAnswered()
            );

            return ResponseEntity.ok(questionDto);
        
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionAllDto>> questionByTitle(@RequestParam String search, @PathVariable Long id) {
        List<Question> questions = questionRepository.findQuestionsByTitleOrTag(search, id);
        System.out.println(search);
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<QuestionAllDto> questionDtos = questions.stream()
                .map(question -> new QuestionAllDto(
                        question.getQuestionId(),
                        question.getTitle(),
                        question.getDescription(),
                        question.getTags(),
                        question.getTimestamp(), question.isAnswered()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(questionDtos);

    }

    @PostMapping("/")
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        System.out.println(question);

        if (question.getTitle() == null || question.getDescription() == null)
            return ResponseEntity.badRequest().body("Title and description cannot be null or empty");

        if (question.getUser() == null)
            return ResponseEntity.badRequest().body("User cannot be null");

        System.out.println(question.getTags());
        Set<Tag> tags = new HashSet<>();

        if (question.getTags() != null) {
            for (Tag tagName : question.getTags()) {
                Tag tag = tagRepository.findByTagName(tagName.getTagName());
                if (tag == null)
                    return ResponseEntity.badRequest().body("Tag not found: " + tagName.getTagName());
                tags.add(tag);
            }
        }

        question.setTags(tags);
        question.setTimestamp(new Date());
        questionRepository.save(question);

        QuestionDto questionDto = new QuestionDto(
                question.isAnswered(),
                question.getTitle(),
                question.getDescription(),
                question.getTags(),
                question.getTimestamp(),
                question.getQuestionId());
        return ResponseEntity.ok(questionDto);
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

    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        Question existingQuestion = questionRepository.findById(question.getQuestionId()).orElse(null);

        if (question.getTitle() == null || question.getDescription() == null)
            return ResponseEntity.badRequest().body("Title and description cannot be null or empty");

        if (existingQuestion == null)
            return ResponseEntity.badRequest().body("User cannot be null");

        Set<Tag> tags = new HashSet<>();

        if (question.getTags() != null) {
            for (Tag tagName : question.getTags()) {
                Tag tag = tagRepository.findByTagName(tagName.getTagName());
                if (tag == null)
                    return ResponseEntity.badRequest().body("Tag not found: " + tagName.getTagName());
                tags.add(tag);
            }
        }

        if (!(existingQuestion.getTitle().equals(question.getTitle())))
            existingQuestion.setTitle(question.getTitle());

        if (!(existingQuestion.getTags().equals(tags)))
            existingQuestion.setTags(tags);

        if (!(existingQuestion.getDescription().equals(question.getDescription())))
            existingQuestion.setDescription(question.getDescription());

        existingQuestion.setTimestamp(new Date());
        questionRepository.save(existingQuestion);

        QuestionDto questionDto = new QuestionDto(
                existingQuestion.isAnswered(),
                existingQuestion.getTitle(),
                existingQuestion.getDescription(),
                existingQuestion.getTags(),
                existingQuestion.getTimestamp(),
                existingQuestion.getQuestionId());
        return ResponseEntity.ok(questionDto);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId).orElse(null);

        if (existingQuestion == null)
            return ResponseEntity.notFound().build();

        questionRepository.delete(existingQuestion);
        return ResponseEntity.ok().build();

    }

}
