package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.User;

public interface UserLoginService {
    User userLogin(String username,String password);
}
