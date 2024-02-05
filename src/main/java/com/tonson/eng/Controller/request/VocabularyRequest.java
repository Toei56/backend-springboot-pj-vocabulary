package com.tonson.eng.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VocabularyRequest {

    @NotEmpty(message = "must not be empty")
    private String eng;

    @NotEmpty(message = "must not be empty")
    private String thai;

    @NotEmpty(message = "must not be empty")
    private String type;

    @NotEmpty(message = "must not be empty")
    private String pronunciation;
}
