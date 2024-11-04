package dw.trabalho.futebol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.futebol.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{
    List<Jogador> findByNomeContaining(String nome);
    List<Jogador> findByNome(String nome);
    List<Jogador> findByEmail(String email);
}
