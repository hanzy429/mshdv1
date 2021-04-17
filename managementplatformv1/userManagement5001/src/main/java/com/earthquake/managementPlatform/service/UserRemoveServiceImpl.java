package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRemoveServiceImpl implements  UserRemoveService{
    @Resource
    UserMapper userMapper;

    @Override
    public int userRemove(String username) {
        int flag = userMapper.deleteByUsername(username);
        if(flag == 1)
            return 0;
        else{
            return -1;
        }
    }
}
