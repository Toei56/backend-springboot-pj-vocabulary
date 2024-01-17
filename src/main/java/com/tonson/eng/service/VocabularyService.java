package com.tonson.eng.service;

import com.tonson.eng.Controller.request.VocabularyRequest;
import com.tonson.eng.model.Vocabulary;

import java.util.List;

public interface VocabularyService {

    List<Vocabulary> getAllVocabulary();

    Vocabulary createVocabulary(VocabularyRequest vocabularyRequest);

    Vocabulary updateVocabulary(VocabularyRequest vocabularyRequest, long id);

    void deleteVocabulary(long id);

    List<Vocabulary> getVocabularyByEng(String eng);

    List<Vocabulary> getVocabularyByThai(String thai);

}
