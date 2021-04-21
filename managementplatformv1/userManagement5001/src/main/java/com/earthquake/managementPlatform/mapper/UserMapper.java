package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM earthquake.user where username=#{username};")
    @Results(id="disasterInfoMap", value={
            @Result(column="username", property="username", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column="password", property="password", jdbcType= JdbcType.VARCHAR),
            @Result(column="register_date", property="registerDate", jdbcType= JdbcType.VARCHAR),
            @Result(column="login_date", property="loginDate", jdbcType= JdbcType.VARCHAR),
            @Result(column="last_login_date", property="lastLoginDate", jdbcType= JdbcType.VARCHAR),
            @Result(column="login_count", property="loginCount", jdbcType= JdbcType.INTEGER),
            @Result(column="user_type", property="userType", jdbcType= JdbcType.CHAR),
    })
    User getUserByUsername(@Param("username") String username);

    @Select("SELECT * FROM earthquake.user where user_type=2")
    @ResultMap(value ="disasterInfoMap")
    List<User> getAdminUserInfo();

    @Select("SELECT * FROM earthquake.user where user_type=2 limit #{pageNum}, #{limit};")
    @ResultMap(value ="disasterInfoMap")
    List<User> getAdminUserInfoByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("SELECT * FROM earthquake.user where username=#{username} AND password=#{password}")
    @ResultMap(value = "disasterInfoMap")
    User getUserByUsernamePassword(@Param("username") String username,@Param("password") String password);

    /**
     * 用户注册
     */
    @Insert("INSERT INTO `earthquake`.`user` (`username`, `password`, `register_date`, `login_date`, `last_login_date`, `login_count`, `user_type`) VALUES (#{username}, #{password}, now(), null, null, 0, #{userType});")
    int save(User user);

    /**
     * 登录成功后修改相关登录时间
     * @param user
     * @return
     */
    @Update("UPDATE `earthquake`.`user` SET `last_login_date` = login_date, login_count =  login_count+1 ,login_date =now() WHERE (`username` = #{username});")
    int update(User user);

    /**
     * 用户注销
     * @param username
     * @return
     */
    @Delete("DELETE FROM `earthquake`.`user` WHERE `username` = #{username};")
    int deleteByUsername(@Param("username") String username);

    /**
     * 用户修改用户名
     * @param newUsername
     * @param oldUsername
     * @return
     */
    @Update("update earthquake.user set username = #{newUsername} where username = #{oldUsername} ")
    int updateUsernameInfo(@Param("newUsername") String newUsername,@Param("oldUsername") String oldUsername);

    @Update("update earthquake.user set password = #{password} where username = #{username} ")
    int updateUserPasswordInfo(@Param("password") String password,@Param("username") String username);


    @Update("update earthquake.user set password = #{password}, username= #{newUsername} where username = #{oldUsername} ")
    int adminUpdateUserPasswordInfo(@Param("password") String password,@Param("newUsername") String newUsername,@Param("oldUsername") String oldUsername);




}
