package dw.trabalho.doubt.control;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.model.Question;
import dw.trabalho.doubt.model.Tag;
import dw.trabalho.doubt.repository.QuestionRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionRepository rep;

    @GetMapping("/")
    public ResponseEntity<HttpStatus> getQuestionsByTagName(@RequestParam(required=true) String tagsString){
        List<String> tags = Arrays.asList(tagsString.split(","));
        List<Question> q = rep.findByTagNames(tags);
        System.out.println(tags);
        if(q.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
