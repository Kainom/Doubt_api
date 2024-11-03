package dw.trabalho.futebol.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.futebol.model.Adm;

public interface AdmRepository extends JpaRepository<Adm,Long> {
    Adm findByEmail(String email);
    Adm findByNome(String nome);
    
}
