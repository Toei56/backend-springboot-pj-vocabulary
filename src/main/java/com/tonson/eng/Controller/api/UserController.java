package com.tonson.eng.Controller.api;

import com.tonson.eng.Controller.request.UserLoginRequest;
import com.tonson.eng.Controller.request.UserRegisterRequest;
import com.tonson.eng.Controller.request.UserRegisterResponse;
import com.tonson.eng.business.UserBusiness;
import com.tonson.eng.exception.BaseException;
import com.tonson.eng.exception.ValidationException;
import com.tonson.eng.model.User;
import com.tonson.eng.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class UserController {

    private final UserService userService;
    private final UserBusiness userBusiness;

    public UserController(UserService userService, UserBusiness userBusiness) {
        this.userService = userService;
        this.userBusiness = userBusiness;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest registerRequest, BindingResult bindingResult) throws BaseException {
        //validate
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> {
                throw new ValidationException(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });
        }
        UserRegisterResponse register = userBusiness.register(registerRequest);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest loginRequest, BindingResult bindingResult) throws BaseException {
        //validate
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> {
                throw new ValidationException(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });
        }
        String login = userBusiness.login(loginRequest);
        return ResponseEntity.ok(login);
    }
}
