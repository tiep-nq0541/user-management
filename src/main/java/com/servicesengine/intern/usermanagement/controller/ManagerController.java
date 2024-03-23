package com.servicesengine.intern.usermanagement.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class ManagerController {
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
