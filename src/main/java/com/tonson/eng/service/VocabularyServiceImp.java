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
        List<Vocabulary> vocabulary = vocabularyRepository.findAll();
        if (vocabulary.size() == 0) {
            throw new VocabularyNotFoundException();
        }
        return vocabularyRepository.findAll(Sort.by(Sort.Direction.ASC, "eng"));
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

    @Override
    public Optional<Vocabulary> getVocabularyTopByEng(String eng) {
        return vocabularyRepository.findTopByEng(eng);
    }

    @Override
    public List<Vocabulary> getVocabularyByEng(String eng) {
        List<Vocabulary> vocabulary = vocabularyRepository.findByEng(eng);
        if (vocabulary.size() == 0) {
            throw new VocabularyNotFoundException(eng);
        }
        return vocabularyRepository.findByEng(eng);
    }

    @Override
    public List<Vocabulary> getVocabularyByThai(String thai) {
        List<Vocabulary> vocabulary = vocabularyRepository.findByThai(thai);
        if (vocabulary.size() == 0) {
            throw new VocabularyNotFoundException(thai);
        }
        return vocabularyRepository.findByThai(thai);
    }
}
