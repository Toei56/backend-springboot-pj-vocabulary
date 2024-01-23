package com.tonson.eng;

import com.tonson.eng.Controller.request.VocabularyRequest;
import com.tonson.eng.model.Vocabulary;
import com.tonson.eng.service.VocabularyService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestVocabularyService {

    @Autowired
    private VocabularyService vocabularyService;

    @Order(1)
    @Test
    void testCreate() {
        VocabularyRequest vocabularyRequest = new VocabularyRequest();
        vocabularyRequest.setEng(CreateDataTest.eng);
        vocabularyRequest.setThai(CreateDataTest.thai);
        vocabularyRequest.setType(CreateDataTest.type);
        vocabularyRequest.setPronunciation(CreateDataTest.pronunciation);
        Vocabulary vocabulary = vocabularyService.createVocabulary(vocabularyRequest);

        Assertions.assertNotNull(vocabulary);
        Assertions.assertNotNull(vocabulary.getId());
        Assertions.assertEquals(CreateDataTest.eng, vocabulary.getEng());
    }

    @Order(2)
    @Test
    void testUpdate() {
        List<Vocabulary> lVocabulary = vocabularyService.getVocabularyByEng(CreateDataTest.eng);
        Assertions.assertNotNull(lVocabulary);

        VocabularyRequest vocabularyRequest = new VocabularyRequest();
        vocabularyRequest.setEng(UpdateDataTest.eng);
        vocabularyRequest.setThai(UpdateDataTest.thai);
        vocabularyRequest.setType(UpdateDataTest.type);
        vocabularyRequest.setPronunciation(UpdateDataTest.pronunciation);

        Vocabulary upDateVocabulary = vocabularyService.updateVocabulary(vocabularyRequest, 1);

        Assertions.assertNotNull(upDateVocabulary);
        Assertions.assertNotNull(upDateVocabulary.getEng());
        Assertions.assertEquals(UpdateDataTest.eng, upDateVocabulary.getEng());
    }

    @Order(3)
    @Test
    void testDelete() {
        Optional<Vocabulary> opt = vocabularyService.getVocabularyTopByEng(UpdateDataTest.eng);
        Assertions.assertTrue(opt.isPresent());

        Vocabulary vocabulary = opt.get();
        vocabularyService.deleteVocabulary(vocabulary.getId());

        Optional<Vocabulary> checkDelete = vocabularyService.getVocabularyTopByEng(UpdateDataTest.eng);
        Assertions.assertTrue(checkDelete.isEmpty());

    }

    interface CreateDataTest {
        String eng = "run";
        String thai = "วิ่ง";
        String type = "verb";
        String pronunciation = "รัน";
    }

    interface UpdateDataTest {
        String eng = "have";
        String thai = "มี";
        String type = "verb";
        String pronunciation = "แฮฟว";
    }
}
