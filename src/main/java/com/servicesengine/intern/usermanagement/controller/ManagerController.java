package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private UserRepo userRepo;

}
