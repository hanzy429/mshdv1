package com.earthquake.managementPlatform.controller;


import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.User;
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









}
