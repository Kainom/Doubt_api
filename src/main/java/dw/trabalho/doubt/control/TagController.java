package dw.trabalho.doubt.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.model.Tag;
import dw.trabalho.doubt.repository.TagRepository;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{tagName}")
    public ResponseEntity<Tag> getTagByTagName(@PathVariable String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/")
    public ResponseEntity<? extends Object> createTag(@RequestBody Tag tag) {

        if (tag.getTagName() == null)
            return ResponseEntity.badRequest().body("Tag name cannot be null or empty");

        Tag existingTag = tagRepository.findByTagName(tag.getTagName());
        if (existingTag != null)
            return ResponseEntity.badRequest().body("Tag already exists");

        return ResponseEntity.ok(tagRepository.save(tag));
    }

    @PutMapping("/{tagName}")
    public ResponseEntity<? extends Object> updateTag(@PathVariable String tagName, @RequestBody Tag tag) {
        Tag existingTag = tagRepository.findByTagName(tagName);

        if (tag.getTagName() == null)
            return ResponseEntity.badRequest().body("Tag name cannot be null or empty");

        if (tagRepository.findByTagName(tag.getTagName()) != null)
        return ResponseEntity.badRequest().body("Tag already exists");


        if (existingTag == null)
            return ResponseEntity.notFound().build();

        existingTag.setTagName(tag.getTagName());
        return ResponseEntity.ok(tagRepository.save(existingTag));
    }

}
