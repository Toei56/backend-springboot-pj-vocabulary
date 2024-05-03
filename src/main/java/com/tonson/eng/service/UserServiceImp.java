package com.tonson.eng.service;

import com.tonson.eng.Controller.request.UserRegisterRequest;
import com.tonson.eng.exception.BaseException;
import com.tonson.eng.exception.UserException;
import com.tonson.eng.model.User;
import com.tonson.eng.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(UserRegisterRequest request) throws BaseException {
        //verify
        if (userRepository.existsByEmail(request.getEmail())) {
            throw UserException.createEmailDuplicate();
        }

        //save
        User user = new User()
                .setEmail(request.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(request.getPassword()))
                .setRole(request.getRole())
                .setPhone_number(request.getPhone_number());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean matchPassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
