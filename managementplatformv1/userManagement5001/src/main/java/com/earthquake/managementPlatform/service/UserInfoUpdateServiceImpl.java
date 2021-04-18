package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.User;
import com.earthquake.managementPlatform.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoUpdateServiceImpl implements UserInfoUpdateService{

    @Resource
    UserMapper userMapper;

    @Override
    public int updateUsername(String newUsername, String oldUsername) {
        User user = userMapper.getUserByUsername(newUsername);
        int res = 0;
        if(user == null) {
            userMapper.updateUsernameInfo(newUsername,oldUsername);
        }
        else{
            res = -1;
        }
        return res;
    }

    @Override
    public int updatePassword(String password, String username) {
        int flag = userMapper.updateUserPasswordInfo(password,username);
        if(flag == 1){
            return 0;
        }
        else{
            return -1;
        }
    }
}
