package com.earthquake.managementPlatform.service;

public interface UserInfoUpdateService {
    public int updateUsername(String newUsername,String oldUsername);
    public int updatePassword(String password,String username);
}
