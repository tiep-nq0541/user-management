package com.servicesengine.intern.usermanagement.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class UserController {
    @GetMapping("/user-infor")
    public String getUserInfor(){
        return null;
    }
    @PutMapping("/change-user")
    public  String changeUserInfor(String fullname){
        return  null;
    }
}
