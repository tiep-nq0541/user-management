package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import com.servicesengine.intern.usermanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepo userRepo;
    @GetMapping("")
    public String homePage(){
        return "/user/information";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute User user){
        LocalDateTime current = LocalDateTime.now();
        if(user.getId() == null){
            user.setTimeCreated(current);
        }
        else {
            user.setTimeUpdated(current);
        }
        userRepo.save(user);
        return "redirect:/user/information";
    }
    @GetMapping("/changePassword")
    public String changePassword( Model model) {
        User user = (User) loginService.getSession().getAttribute("user_login");
        model.addAttribute("user", user);
        return "user/changePassword";
    }
    @GetMapping("/information")
    public String information(Model model) {
        User user = (User) loginService.getSession().getAttribute("user_login");
        model.addAttribute("user", user);
        return "user/information";
    }
}
