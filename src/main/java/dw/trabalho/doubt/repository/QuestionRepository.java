package dw.trabalho.doubt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dw.trabalho.doubt.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT DISTINCT q FROM Question q " +
           "JOIN q.tags t " +
           "WHERE t.tag_name IN :tagNames")
    List<Question> findByTagNames(@Param("tagNames") List<String> tagNames);
}