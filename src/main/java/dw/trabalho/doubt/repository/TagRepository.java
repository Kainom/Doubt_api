package dw.trabalho.doubt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dw.trabalho.doubt.model.Tag;
import dw.trabalho.doubt.model.User;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findByTagName(String tagName);
    public List<Tag> findByUser(User user);
    @Query(
        "SELECT t " +
        "FROM Tag t " +
        "JOIN t.user u " +
        "WHERE u.userId = :userId"
    )
    public List<Tag> findAllByUserId(@Param("userId") Long id);

    @Query(
        "SELECT t " +
        "FROM Tag t " +
        "JOIN t.user u " +
        "WHERE u.userId = :userId " +
        "AND t.tagName = :tagName"  
    )
    public Tag findByUserAndTagName(@Param("userId") Long id,@Param("tagName") String tagName);


    // public List<Tag> findByUse();

    // SELECT t.tag_name  from question q INNER JOIN question_tag qt ON q.question_id = qt.question_id INNER JOIN tag t ON t.tag_id = qt.tag_id WHERE t.user_id =1  GROUP BY t.tag_name ORDER BY (COUNT(qt.tag_id))  DESC LIMIT 5;


    
}