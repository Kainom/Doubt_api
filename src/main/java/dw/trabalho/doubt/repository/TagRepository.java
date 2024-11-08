package dw.trabalho.doubt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.trabalho.doubt.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findByTagName(String tagName);

    
}