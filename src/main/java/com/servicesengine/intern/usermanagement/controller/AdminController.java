package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.entity.Admin;
import com.servicesengine.intern.usermanagement.entity.Manager;
import com.servicesengine.intern.usermanagement.entity.Mapper;
import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.AdminRepo;
import com.servicesengine.intern.usermanagement.repository.ManagerRepo;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ManagerRepo managerRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/home")
    public String home(){
        return "/admin/home";
    }

//    Admin
    @GetMapping("/listAdmin")
    public String listAdmin(Model model){
        List<Admin> admins = adminRepo.findAll();
        model.addAttribute("admins", admins);
        return "/admin/listAdmin";
    }

    @GetMapping("/editAdmin")
    public String editAdmin(){
        return "/admin/editAdmin";
    }

    @PostMapping("/saveOrUpdateAdmin")
    public String saveOrUpdateAdmin(@ModelAttribute Admin admin){
        adminRepo.save(admin);
        return "redirect:/admin/listAdmin";
    }
    @GetMapping("/editAdmin/{id}")
    public String editadmin(@PathVariable("id") Integer id, Model model) {
        Optional<Admin> optionalAdmin = adminRepo.findById(id);
        if (optionalAdmin.isPresent()) {
            model.addAttribute("admin", optionalAdmin.get());
            return "/admin/editAdmin";
        } else {
            throw new IllegalArgumentException("Invalid manager ID");
        }
    }
    @GetMapping("/inforAdmin/{id}")
    public String inforAdmin(@PathVariable("id") Integer id, Model model) {
        Optional<Admin> optionalAdmin = adminRepo.findById(id);
        if (optionalAdmin.isPresent()) {
            model.addAttribute("user", optionalAdmin.get());
            return "/admin/editAdmin";
        } else {
            throw new IllegalArgumentException("Invalid manager ID");
        }
    }
    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id) {
        adminRepo.deleteById(id);
        return "redirect:/admin/listAdmin";
    }



    //Manager
    @GetMapping("/listManager")
    public String listManager(Model model){
        List<Manager> managers = managerRepo.findAll();
        model.addAttribute("managers", managers);
        return "/admin/listManager";
    }

    @GetMapping("/editManager")
    public String editManager(){
        return "/admin/editManger";
    }

    @PostMapping("/saveOrUpdateManager")
    public String saveOrUpdateManager(@ModelAttribute Manager manager){
        managerRepo.save(manager);
        return "redirect:/admin/listManager";
    }
    @GetMapping("/editManager/{id}")
    public String editmanager(@PathVariable("id") Integer id, Model model) {
        Optional<Manager> optionalManager = managerRepo.findById(id);
        if (optionalManager.isPresent()) {
            model.addAttribute("user", optionalManager.get());
            return "/admin/editManager";
        } else {
            throw new IllegalArgumentException("Invalid manager ID");
        }
    }
    @GetMapping("/inforManager/{id}")
    public String inforManager(@PathVariable("id") Integer id, Model model) {
        Optional<Manager> optionalManager = managerRepo.findById(id);
        if (optionalManager.isPresent()) {
            model.addAttribute("user", optionalManager.get());
            return "/admin/editManager";
        } else {
            throw new IllegalArgumentException("Invalid manager ID");
        }
    }
    @GetMapping("/deleteManager/{id}")
    public String deleteManager(@PathVariable("id") Integer id) {
        userRepo.deleteById(id);
        return "redirect:/admin/listManager";
    }


//    User
    @GetMapping("/listUser")
    public String listUser(Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "/admin/listUser";
    }
    @GetMapping("/addMember")
    public String addUser(){
        return "/admin/addMember";
    }

    @GetMapping("/editUser")
    public String editUser(){
        return "/admin/editUser";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute User user){
        if(user.getRole().equals("admin")){
            adminRepo.save(Mapper.toAdmin(user));
            return "redirect:/admin/listAdmin";
        } else if (user.getRole().equals("manager")) {
            managerRepo.save(Mapper.toManager(user));
            return "redirect:/admin/listManager";
        } else if (user.getRole().equals("user")) {
            userRepo.save(user);
            return "redirect:/admin/listUser";
        }
        else return null;
    }
//    @PostMapping("/saveOrUpdate")
//    public String saveOrUpdate(@ModelAttribute User user){
//        userRepo.save(user);
//        return "redirect:/admin/listUser";
//    }
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/admin/editUser";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
    @GetMapping("/inforUser/{id}")
    public String inforUser(@PathVariable("id") Integer id, Model model) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/admin/editUser";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userRepo.deleteById(id);
        return "redirect:/admin/listUser";
    }
}
