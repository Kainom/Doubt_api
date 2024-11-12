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

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Tag>> getAllTags(@PathVariable Long id) {
        List<Tag> tags = tagRepository.findAllByUserId(id);
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{tagName}/{id}")
    public ResponseEntity<Tag> getTagByTagName(@PathVariable String tagName,
    @PathVariable Long id){
        Tag tag = tagRepository.findByUserAndTagName(id,tagName);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/")
    public ResponseEntity<? extends Object> createTag(@RequestBody Tag tag) {

        if (tag.getTagName() == null)
            return ResponseEntity.badRequest().body("Tag name cannot be null or empty");
        if (tag.getUser() == null)
            return ResponseEntity.badRequest().body("User cannot be null");

        System.out.println(tag.getUser());

        // Tag existingTag = tagRepository.findByTagName(tag.getTagName());
         Tag existingTag = tagRepository.findByUserAndTagName(tag.getUser().getUserId(),tag.getTagName());
        // List<Tag> existingTag = tagRepository.findByUser(tag.getUser());
        // Boolean exists = existingTag.stream().anyMatch(t -> t.getTagName().equals(tag.getTagName()));


        if (existingTag != null)
            return ResponseEntity.badRequest().body("Tag already exists");

        return ResponseEntity.ok(tagRepository.save(tag));
    }

    @PutMapping("/{tagName}/{id}")
    public ResponseEntity<? extends Object> updateTag(@PathVariable String tagName, 
    @PathVariable Long id,
    @RequestBody Tag tag) {
        Tag existingTag = tagRepository.findByUserAndTagName(id,tagName);

        if (tag.getTagName() == null)
            return ResponseEntity.badRequest().body("Tag name cannot be null or empty");

        if (tagRepository.findByUserAndTagName(id,tag.getTagName()) != null)
            return ResponseEntity.badRequest().body("Tag already exists");

        if (existingTag == null)
            return ResponseEntity.notFound().build();

        existingTag.setTagName(tag.getTagName());
        return ResponseEntity.ok(tagRepository.save(existingTag));
    }

}
