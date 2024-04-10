package com.tonson.eng.service;

import com.tonson.eng.Controller.request.UserRequest;
import com.tonson.eng.exception.UserDuplicateException;
import com.tonson.eng.model.User;
import com.tonson.eng.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            user = new User()
                    .setUsername(userRequest.getUsername())
                    .setPassword(userRequest.getPassword())
                    .setRole(userRequest.getRole());
            return userRepository.save(user);
        }
        throw new UserDuplicateException(userRequest.getUsername());
    }
}
