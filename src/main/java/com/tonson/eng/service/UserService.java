package com.tonson.eng.service;

import com.tonson.eng.Controller.request.UserRegisterRequest;
import com.tonson.eng.exception.BaseException;
import com.tonson.eng.exception.UserException;
import com.tonson.eng.model.User;

import java.util.Optional;

public interface UserService {

    User createUser(UserRegisterRequest userRegisterRequest) throws BaseException;

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Boolean matchPassword(String rawPassword, String encodedPassword);
}
