package com.tonson.eng.service;

import com.tonson.eng.Controller.request.UserRequest;
import com.tonson.eng.model.User;

public interface UserService {

    User register(UserRequest userRequest);
}
