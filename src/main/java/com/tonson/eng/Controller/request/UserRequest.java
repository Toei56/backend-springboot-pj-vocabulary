package com.tonson.eng.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotEmpty
    @Size(min = 8, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    private String role;
}
