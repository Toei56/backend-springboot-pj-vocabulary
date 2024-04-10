package com.tonson.eng.service;

import com.tonson.eng.Controller.request.VocabularyRequest;
import com.tonson.eng.model.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {

    List<Vocabulary> getAllVocabulary();

    Optional<Vocabulary> getVocabularyById(Long id);

    Vocabulary createVocabulary(VocabularyRequest vocabularyRequest);

    Vocabulary updateVocabulary(VocabularyRequest vocabularyRequest, long id);

    void deleteVocabulary(long id);

    Optional<Vocabulary> getVocabularyTopByEng(String eng);

    List<Vocabulary> getVocabularyByEng(String eng);

    List<Vocabulary> getVocabularyByThai(String thai);

}
