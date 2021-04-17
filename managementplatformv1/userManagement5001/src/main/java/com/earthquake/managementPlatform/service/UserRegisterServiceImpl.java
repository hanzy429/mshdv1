package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.UserMapper;
import com.earthquake.managementPlatform.entities.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Resource
    UserMapper userMapper;
    @Override
    public int signIn(User user) {
        int res = 0;
        if(userMapper.getUserByUsername(user.getUsername()) != null){
            res = -1;
        }
        else{
            int flag = userMapper.save(user);
            if(flag == 1){
                res = 0;
            }
            else{
                res = -1;
            }
        }
        return res;
    }
}
