package com.servicesengine.intern.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public String getListUser(){
        return null;
    }
    @GetMapping("/user-infor")
    public String getUserInfor(){
        return null;
    }
    @PostMapping("/create-user")
    public String createUser(){
        return null;
    }
    @PutMapping("/change-user")
    public  String changeUserInfor(String fullname){
        return  null;
    }
    @DeleteMapping("/delete")
    public String deleteUser(){
        return null;
    }
}
