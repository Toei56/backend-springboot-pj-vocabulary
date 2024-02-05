package com.tonson.eng.repository;

import com.tonson.eng.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    Optional<Vocabulary> findTopByEng(String eng);

    List<Vocabulary> findByEng(String eng);

    List<Vocabulary> findByThai(String thai);
}
