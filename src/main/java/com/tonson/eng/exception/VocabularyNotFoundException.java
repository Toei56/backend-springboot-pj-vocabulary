package com.tonson.eng.exception;

public class VocabularyNotFoundException extends RuntimeException {

    public VocabularyNotFoundException() {
        super("Could not find Vocabulary");
    }

    public VocabularyNotFoundException(String message) {
        super("Could not find Vocabulary : " + message);
    }

    public VocabularyNotFoundException(long id) {
        super("Could not find Vocabulary : " + id);
    }
}
