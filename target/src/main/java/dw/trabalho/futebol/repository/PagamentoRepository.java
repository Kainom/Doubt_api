package dw.trabalho.futebol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.futebol.model.Jogador;
import dw.trabalho.futebol.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByJogador(Jogador jogador);
}
