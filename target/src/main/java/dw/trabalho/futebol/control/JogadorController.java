package dw.trabalho.futebol.control;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.futebol.model.Jogador;
import dw.trabalho.futebol.repository.JogadorRepository;
import dw.trabalho.futebol.service.AuthService;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
    @Autowired
    JogadorRepository rep;


    @Autowired
    AuthService auth;
    // http://localhost:8000/futebol/jogador
    @GetMapping("/all")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {
        try {
            List<Jogador> lj = new ArrayList<Jogador>();
            if (nome == null) {
                rep.findAll().forEach(lj::add);
            } else {
                rep.findByNome(nome).forEach(lj::add);
            }

            if (lj.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") long id) {
        Optional<Jogador> jogador = rep.findById(id);

        try {
            if (jogador.isPresent()) {
                return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {
        try {
            auth.registerJogador(jogador);
            return new ResponseEntity<>(rep.save(
                    new Jogador(
                            jogador.getNome(),
                            jogador.getEmail(),
                            jogador.getDatanasc(),
                            jogador.getPassword())),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") long id, @RequestBody Jogador jogador) {
        Optional<Jogador> jogadorAntigo = rep.findById(id);

        try {
            if (jogadorAntigo.isPresent()) {
                Jogador novoJogador = jogadorAntigo.get();
                novoJogador.setNome(jogador.getNome());
                novoJogador.setEmail(jogador.getEmail());
                novoJogador.setDatanasc(jogador.getDatanasc());
                return new ResponseEntity<>(rep.save(novoJogador), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Jogador> patchJogador(@PathVariable("id") long id, @RequestBody Jogador jogador) {
        Optional<Jogador> jogadorAntigo = rep.findById(id);
        try {
            if (jogadorAntigo.isPresent()) {
                Jogador novoJogador = jogadorAntigo.get();
                if(jogador.getDatanasc() != null){
                    novoJogador.setDatanasc(jogador.getDatanasc());
                }
                if(jogador.getNome()!= null){
                    novoJogador.setNome(jogador.getNome());
                }
                if(jogador.getEmail()!= null){
                    novoJogador.setEmail(jogador.getEmail());
                }
                return new ResponseEntity<>(rep.save(novoJogador), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") long id) {
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

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteJogador() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
