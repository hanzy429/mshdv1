package com.earthquake.managementPlatform.controller;


import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.User;
import com.earthquake.managementPlatform.mapper.UserMapper;
import com.earthquake.managementPlatform.service.UserInfoUpdateServiceImpl;
import com.earthquake.managementPlatform.service.UserLoginServiceImpl;
import com.earthquake.managementPlatform.service.UserRegisterServiceImpl;
import com.earthquake.managementPlatform.service.UserRemoveServiceImpl;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserResource {
    @Resource
    UserLoginServiceImpl userLoginService;

    @Resource
    UserRegisterServiceImpl userRegisterService;

    @Resource
    UserRemoveServiceImpl userRemoveService;

    @Resource
    UserInfoUpdateServiceImpl userInfoUpdateService;

    @Resource
    UserMapper userMapper;
    @PostMapping("/v1/user")
    public PostVo userSignIn(HttpServletRequest request){
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setUserType(request.getParameter("userType"));
        int res = userRegisterService.signIn(user);
        if(res == 0)
        {
            return new PostVo(res,"注册成功！",null);
        }
        else {
            return new PostVo(res,"注册失败，用户名已存在！",null);
        }

    }

    @GetMapping("/v1/user")
    public GetVo userLogin(HttpServletRequest request){
        User user = userLoginService.userLogin(request.getParameter("username"),request.getParameter("password"));
        if(user == null){
            return new GetVo(-1,"登录错误！用户名或者密码不正确！",0,null);
        }
        else{
            HttpSession session = request.getSession();
            session.setAttribute("USER",user);
            List list = new ArrayList();
            list.add(user);
            return new GetVo(Integer.valueOf(user.getUserType()),"登录成功！",0,list);
        }

    }

    @GetMapping("/v1/user/permission")
    public GetVo isPermitted(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("USER");
        if(Objects.isNull(user)){
            return new GetVo(-1,"未登录！请登录！",0,null);
        }
        else{
            return new GetVo(0,"已登录！允许继续浏览！",0,null);
        }
    }

    @PutMapping("/v1/user")
    public PostVo loginOut(HttpServletRequest request){
        request.getSession().setAttribute("USER",null);

        return new PostVo(0,"登出成功！",null);
    }


    @DeleteMapping("/v1/user")
    public  PostVo deleteUser(HttpServletRequest request){
        int res = userRemoveService.userRemove(request.getParameter("username"));
        request.getSession().setAttribute("USER",null);
        if(res == 0 ){
            return new PostVo(res,"注销成功!",null);
        }
        else
            return new PostVo(res,"注销失败",null);
    }

    @PutMapping("/v1/username/{username}")
    public PostVo updateUsername(HttpServletRequest request,@PathVariable("username") String username){
        int res = userInfoUpdateService.updateUsername(request.getParameter("username"),username);
        if(res == 0 ){
            return new PostVo(res,"更改用户信息成功!",null);
        }
        else
            return new PostVo(res,"更改用户名失败！原因：用户名重名！",null);
    }

    @PutMapping("/v1/userpassword/{username}")
    public PostVo updateUserpassword(HttpServletRequest request,@PathVariable("username") String username){
        int res = userInfoUpdateService.updatePassword(request.getParameter("password"),username);

        if(res == 0 ){
            return new PostVo(res,"更改密码成功!",null);
        }
        else
            return new PostVo(res,"更改用户密码失败！请重试！",null);
    }

    @PutMapping("/v1/adminuserpassword/")
    public PostVo adminUpdateUserpassword(HttpServletRequest request){
        int res = userInfoUpdateService.updatePassword(request.getParameter("password"),request.getParameter("username"));

        if(res == 0 ){
            return new PostVo(res,"更改密码成功!",null);
        }
        else
            return new PostVo(res,"更改用户密码失败！请重试！",null);
    }

//    @GetMapping("/v1/brickwoodStructure")
//    public GetVo brickwoodStructureAll(HttpServletRequest request){
//        int limit = Integer.valueOf(request.getParameter("limit"));
//        int page = Integer.valueOf(request.getParameter("page"));
//        int size = brickwoodStructureMapper.getAllBrickwoodStructure().size();
//        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getBrickwoodStructureByPage((page-1)*limit,limit);
//        GetVo<BrickwoodStructure> getVo = new GetVo<>(0,"获取数据成功！",size,brickwoodStructure);
//        return getVo;
//    }


    @GetMapping("/v1/adminalluser")
    public GetVo selectUserAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = userMapper.getAdminUserInfo().size();
        List<User> user = userMapper.getAdminUserInfoByPage((page-1)*limit,limit);
        GetVo<User> getVo = new GetVo<>(0,"获取数据成功！",size,user);
        return getVo;
    }







}
