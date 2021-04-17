package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.UserMapper;
import com.earthquake.managementPlatform.entities.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Resource
    UserMapper userMapper;

    @Override
    public User userLogin(String username,String password) {
        User user = userMapper.getUserByUsernamePassword(username,password);
        if(user != null){
            userMapper.update(user);
            return user;
        }
        else{
            return null;
        }

    }
}
