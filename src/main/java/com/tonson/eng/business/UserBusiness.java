package com.tonson.eng.business;

import com.tonson.eng.Controller.request.UserLoginRequest;
import com.tonson.eng.Controller.request.UserRegisterRequest;
import com.tonson.eng.Controller.request.UserRegisterResponse;
import com.tonson.eng.exception.BaseException;
import com.tonson.eng.exception.UserException;
import com.tonson.eng.mapper.UserMapper;
import com.tonson.eng.model.User;
import com.tonson.eng.service.TokenService;
import com.tonson.eng.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;

    private final TokenService tokenService;
    private final UserMapper userMapper;


    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public UserRegisterResponse register(UserRegisterRequest registerRequest) throws BaseException {
        User user = userService.createUser(registerRequest);

        // mapper
        return userMapper.USER_REGISTER_RESPONSE(user);
    }

    public String login(UserLoginRequest loginRequest) throws BaseException {
        //verify
        Optional<User> opt = userService.findByEmail(loginRequest.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if (!userService.matchPassword(loginRequest.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.Tokenize(user);
    }
}
