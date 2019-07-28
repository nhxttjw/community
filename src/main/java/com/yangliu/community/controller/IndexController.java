package com.yangliu.community.controller;

import com.yangliu.community.mapper.UserMapper;
import com.yangliu.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //获得所有cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            //找到有token的cookie,有则展示登录
            if(cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
