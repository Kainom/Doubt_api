package dw.trabalho.doubt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dw.trabalho.doubt.model.Question;
import dw.trabalho.doubt.model.Tag;

public interface QuestionRepository extends JpaRepository<Question, Long> {
        public List<Question> findByTitle(String title);

        public Question findByDescription(String description);

        @Query("SELECT q FROM Question q WHERE q.user.id = :userId")
        List<Question> findQuestionsByUserId(@Param("userId") Long userId);

        @Query(value = "SELECT q FROM Question q JOIN q.tags t WHERE t IN :tags", nativeQuery = true)
        public List<Question> findByTagsIn(List<Tag> tags);

        @Query("SELECT DISTINCT q FROM Question q " +
                        "JOIN q.tags t " +
                        "WHERE LOWER(q.title) LIKE LOWER(CONCAT('%', :search, '%')) " +
                        "OR LOWER(t.tagName) LIKE LOWER(CONCAT('%', :search, '%'))")
        List<Question> findByTitleOrTagContaining(@Param("search") String search);

        @Query(value = "SELECT * FROM question q WHERE q.title LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
        List<Question> findQuestionsContainingText(@Param("searchText") String searchText);

        @Query(value = "SELECT * FROM question q WHERE q.title LIKE CONCAT('%', :searchText, '%') AND q.user_id = :userId", nativeQuery = true)
        List<Question> findQuestionsContainingTextByUser(@Param("searchText") String searchText,
                        @Param("userId") Long userId);

        @Query("SELECT DISTINCT q FROM Question q JOIN q.tags t " +
                        "WHERE (q.title LIKE CONCAT('%', :search, '%') " +
                        "OR t.tagName LIKE CONCAT('%', :search, '%')) " +
                        "AND q.user.id = :userId")
        List<Question> findQuestionsByTitleOrTag(@Param("search") String search, @Param("userId") Long userId);

        @Query("SELECT q FROM Question q JOIN q.tags t WHERE t.tagName = :name")
        List<Question> findByTagName(@Param("name") String name);

        @Query("SELECT q FROM Question q WHERE q.user.id = :userId ORDER BY q.timestamp DESC")
        List<Question> findByLastQuestions(@Param("userId") Long userId);

}
