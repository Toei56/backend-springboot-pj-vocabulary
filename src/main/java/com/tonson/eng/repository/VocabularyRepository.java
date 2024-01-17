package com.tonson.eng.repository;

import com.tonson.eng.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    List<Vocabulary> findByEng(String eng);

    List<Vocabulary> findByThai(String thai);
}
