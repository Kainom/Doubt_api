package dw.trabalho.doubt.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.model.Doubt;
import dw.trabalho.doubt.repository.DoubtRepository;

@RestController()
@RequestMapping("/doubts") // Endpoint for the DoubtController
public class DoubtController {

    @Autowired
    private DoubtRepository doubRep;

    @GetMapping("/")

    public ResponseEntity<List<Doubt>> getALL(@RequestParam(required = false) String tag) {
        List<Doubt> allDoubts;

        if (tag != null)
            allDoubts = doubRep.findByTag(tag);
        else
            allDoubts = doubRep.findAll();

        if (!allDoubts.isEmpty())
            return ResponseEntity.ok(allDoubts);
        else
            return ResponseEntity.noContent().build();

    }

    @GetMapping({ "/{id}" })
    public ResponseEntity<Doubt> getDoubt(@PathVariable Long id) {
        Optional<Doubt> doubt = doubRep.findById(id);

        if (doubt.isPresent())
            return ResponseEntity.ok(doubt.get());
        else
            return ResponseEntity.notFound().build();

    }

    @PostMapping("/")
    public ResponseEntity<Doubt> createDoubt(@RequestBody Doubt doubt) {
        if (doubt.getTitle() == null || doubt.getDescription() == null)
            return ResponseEntity.badRequest().build();
        else if (doubRep.findByTitle(doubt.getTitle()) != null)
            return ResponseEntity.status(409).build();
        else
            return ResponseEntity.ok(doubRep.save(doubt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doubt> updateDoubt(@PathVariable Long id, @RequestBody Doubt newDoubt) {
        Optional<Doubt> oldDoubt = doubRep.findById(id);
        if (oldDoubt.isPresent()) {
            Doubt updatedDoubt = oldDoubt.get();
            updatedDoubt.setTitle(newDoubt.getTitle());
            updatedDoubt.setDescription(newDoubt.getDescription());
            updatedDoubt.setTag(newDoubt.getTag());
            return ResponseEntity.ok(doubRep.save(updatedDoubt));
        } else 
            return ResponseEntity.notFound().build();
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoubt(@PathVariable Long id) {
        Optional<Doubt> doubt = doubRep.findById(id);
        if (doubt.isPresent()) {
            doubRep.deleteById(id);
            return ResponseEntity.noContent().build();
        } else 
        return ResponseEntity.notFound().build();
    }

}
