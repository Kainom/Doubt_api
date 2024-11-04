package dw.trabalho.doubt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.doubt.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
    User findByEmail(String email);

    
}
