package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }
//    @RequestMapping("/user_main")
//    public String userHome(){
//        return "display";
//    }
    @PostMapping("/checklogin")
    public String checkLogin(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(loginService.isValidUser(username,password) != null){
            return "display";
        }else return "login";
    }

}
