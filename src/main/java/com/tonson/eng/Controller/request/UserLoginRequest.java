package com.tonson.eng.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {

    @NotEmpty(message = "must not be empty")
    @Size(min = 8, max = 20)
    private String email;

    @Size(min = 8)
    @NotEmpty(message = "must not be empty")
    private String password;
}
