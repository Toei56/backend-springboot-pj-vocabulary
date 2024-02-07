package com.tonson.eng.service;

import com.tonson.eng.Controller.request.VocabularyRequest;
import com.tonson.eng.exception.VocabularyNotFoundException;
import com.tonson.eng.model.Vocabulary;
import com.tonson.eng.repository.VocabularyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyServiceImp implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    public VocabularyServiceImp(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public List<Vocabulary> getAllVocabulary() {
        List<Vocabulary> vocabularies = vocabularyRepository.findAll(Sort.by(Sort.Direction.ASC, "eng"));
        if (vocabularies.size() == 0) {
            throw new VocabularyNotFoundException();
        }
        return vocabularies;
    }

    @Override
    public Vocabulary createVocabulary(VocabularyRequest vocabularyRequest) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary
                .setEng(vocabularyRequest.getEng())
                .setThai(vocabularyRequest.getThai())
                .setType(vocabularyRequest.getType())
                .setPronunciation(vocabularyRequest.getPronunciation());
        return vocabularyRepository.save(vocabulary);
    }

    @Override
//    @Transactional //หากมีข้อผิดพลาด (exception) ขึ้นขณะทำงานในกิจกรรมใด ๆ, การทำธุรกรรมนี้จะถูกยกเลิก (rollback) ทั้งหมด
    public Vocabulary updateVocabulary(VocabularyRequest vocabularyRequest, long id) {
        Optional<Vocabulary> vocabulary = vocabularyRepository.findById(id);
        if (vocabulary.isPresent()) {
            Vocabulary existingVocabulary = vocabulary.get();
            existingVocabulary
                    .setEng(vocabularyRequest.getEng())
                    .setThai(vocabularyRequest.getThai())
                    .setType(vocabularyRequest.getType())
                    .setPronunciation(vocabularyRequest.getPronunciation());
            return vocabularyRepository.save(existingVocabulary);
        }
        throw new VocabularyNotFoundException(id);
    }

    @Override
    public void deleteVocabulary(long id) {
        try {
            vocabularyRepository.deleteById(id);
        } catch (Exception ex) {
            throw new VocabularyNotFoundException(id);
        }
    }

    @Override //ใช้ทำ unit test
    public Optional<Vocabulary> getVocabularyTopByEng(String eng) {
        return vocabularyRepository.findTopByEng(eng);
    }

    @Override
    public List<Vocabulary> getVocabularyByEng(String eng) {
        List<Vocabulary> vocabularies = vocabularyRepository.findByEng(eng);
        if (vocabularies.size() == 0) {
            throw new VocabularyNotFoundException(eng);
        }
        return vocabularies;
    }

    @Override
    public List<Vocabulary> getVocabularyByThai(String thai) {
        List<Vocabulary> vocabularies = vocabularyRepository.findByThai(thai);
        if (vocabularies.size() == 0) {
            throw new VocabularyNotFoundException(thai);
        }
        return vocabularies;
    }
}
