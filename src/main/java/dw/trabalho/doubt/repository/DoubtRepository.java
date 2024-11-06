package dw.trabalho.doubt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.doubt.model.Doubt;

public interface DoubtRepository extends JpaRepository<Doubt,Long> {

    Doubt findByTitle(String title);
    List<Doubt> findByTag(String tag);
}
