package dw.trabalho.futebol.control;

import java.util.ArrayList;
import java.util.List;
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

import dw.trabalho.futebol.model.Jogador;
import dw.trabalho.futebol.model.Pagamento;
import dw.trabalho.futebol.repository.JogadorRepository;
import dw.trabalho.futebol.repository.PagamentoRepository;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    PagamentoRepository rep;

    @Autowired
    JogadorRepository jogadorRep;

    @GetMapping("/all")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        try {
            List<Pagamento> pagamentos = new ArrayList<Pagamento>();
            rep.findAll().forEach(pagamentos::add);

            if (pagamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pagamentos, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable("id") long id) {
        Optional<Pagamento> pagamento = rep.findById(id);

        try {
            if (pagamento.isPresent()) {
                return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        try {
            Optional<Jogador> jogador;

            if (pagamento.getJogador().getCod_jogador() == null) {

                if (pagamento.getJogador().getEmail() == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                List<Jogador> jogadorList = jogadorRep.findByEmail(pagamento.getJogador().getEmail());

                if (jogadorList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                jogador = Optional.of(jogadorList.get(0));
            } else {
                jogador = jogadorRep.findById(pagamento.getJogador().getCod_jogador());
            }

            if (jogador.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Pagamento novoPagamento = new Pagamento(
                    pagamento.getAno(),
                    pagamento.getMes(),
                    pagamento.getValor(),
                    jogador.get());

            return new ResponseEntity<>(rep.save(novoPagamento), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") long id, @RequestBody Pagamento pagamento) {
        Optional<Pagamento> pagamentoAntigo = rep.findById(id);

        try {
            if (pagamentoAntigo.isPresent()) {
                Pagamento novoPagamento = pagamentoAntigo.get();
                novoPagamento.setAno(pagamento.getAno());
                novoPagamento.setMes(pagamento.getMes());
                novoPagamento.setValor(pagamento.getValor());

                if (pagamento.getJogador() != null) {
                    Optional<Jogador> jogador = jogadorRep.findById(pagamento.getJogador().getCod_jogador());
                    if (jogador.isPresent()) {
                        novoPagamento.setJogador(jogador.get());
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
                return new ResponseEntity<>(rep.save(novoPagamento), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pagamento> patchPagamento(@PathVariable("id") long id, @RequestBody Pagamento pagamento) {
        Optional<Pagamento> pagamentoAntigo = rep.findById(id);

        try {
            if (pagamentoAntigo.isPresent()) {
                Pagamento novoPagamento = pagamentoAntigo.get();
                if (pagamento.getAno() != null) {
                    novoPagamento.setAno(pagamento.getAno());
                }
                if (pagamento.getMes() != null) {
                    novoPagamento.setMes(pagamento.getMes());
                }
                if (pagamento.getValor() != null) {
                    novoPagamento.setValor(pagamento.getValor());
                }
                if (pagamento.getJogador() != null) {
                    Optional<Jogador> jogador = jogadorRep.findById(pagamento.getJogador().getCod_jogador());
                    if (jogador.isPresent()) {
                        novoPagamento.setJogador(jogador.get());
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }

                return new ResponseEntity<>(rep.save(novoPagamento), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") long id) {
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
    public ResponseEntity<HttpStatus> deletePagamentos() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
