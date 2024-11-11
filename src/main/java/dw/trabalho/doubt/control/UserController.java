package dw.trabalho.doubt.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.doubt.control.dto.UserDto;
import dw.trabalho.doubt.model.User;
import dw.trabalho.doubt.repository.UserRepository;
import dw.trabalho.doubt.service.AuthService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository rep;

    @Autowired
    AuthService auth;
    // http://localhost:8000/futebol/jogador

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = rep.findById(id);

        try {
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        try {

            System.out.println(user);
            auth.encoder(user);
            if (rep.findByEmail(user.getEmail()) != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            if (rep.findByUsername(user.getUsername()) != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            User newUser = rep.save(
                    new User(
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail()));

            UserDto userDto = new UserDto(
                    newUser.getUserId(),
                    newUser.getUsername(),
                    newUser.getEmail());

            return new ResponseEntity<>(userDto,
                    HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userOld = rep.findById(id);

        try {
            if (userOld.isPresent()) {
                User novoUser = userOld.get();
                novoUser.setUsername(user.getUsername());
                novoUser.setEmail(user.getEmail());
                novoUser.setPassword(user.getPassword());
                return new ResponseEntity<>(rep.save(novoUser), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> oldUser = rep.findById(id);
        try {
            if (oldUser.isPresent()) {
                User novoUser = oldUser.get();
                if (user.getUsername() != null) {
                    novoUser.setUsername(user.getUsername());
                }
                if (user.getEmail() != null) {
                    novoUser.setEmail(user.getEmail());
                    ;
                }
                return new ResponseEntity<>(rep.save(novoUser), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            if (rep.findById(id).isPresent()) {
                rep.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
