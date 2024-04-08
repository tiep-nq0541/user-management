package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.service.AdminService;
import com.servicesengine.intern.usermanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginService loginService;

    @GetMapping("/list")
    public String list(Model model) {
        adminService.checkAdmin();
        List<User> users = adminService.getUserRepo().findAll();
        model.addAttribute("users", users);
        return "/admin/list";
    }

    //    User
    @GetMapping("/listUser")
    public String listUser(Model model) {
        adminService.checkAdmin();
//        adminService.getSession().removeAttribute("user");
        List<User> users = adminService.getUserRepo().findByRole(User.Role.USER);
        model.addAttribute("users", users);
        return "/admin/listUser";
    }

    @GetMapping("/addMember")
    public String addUser(Model model) {
        adminService.checkAdmin();
        User user = (User) adminService.getSession().getAttribute("user");
        if( user == null ) user = new User();
        model.addAttribute("user", user);
        return "/admin/addMember";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        adminService.checkAdmin();
        LocalDateTime current = LocalDateTime.now();
        if (user.getId() == null) {
            user.setTimeCreated(current);
            if (!adminService.checkAddUser(user, redirectAttributes)) {
                adminService.getSession().setAttribute("user", user);
                return "redirect:/admin/addMember";
            }
        } else {
            user.setTimeUpdated(current);
            if (!adminService.checkEditUser(user, redirectAttributes)) {
                adminService.getSession().setAttribute("user", user);
                return "redirect:/admin/addMember";
            }
        }
        user.setPassword(adminService.hashPassword(user.getPassword()));
        adminService.getUserRepo().save(user);
        adminService.getSession().removeAttribute("user");
        return "redirect:/admin/list";
    }


    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        adminService.checkAdmin();
        Optional<User> optionalUser = adminService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            adminService.getSession().setAttribute("user", optionalUser.get());
            return "/admin/addMember";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @GetMapping("/inforUser/{id}")
    public String inforUser(@PathVariable("id") Integer id, Model model) {
        adminService.checkAdmin();
        Optional<User> optionalUser = adminService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/admin/inforMember";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        adminService.checkAdmin();
        if (!adminService.checkCanDelete(id)){
            model.addAttribute("deleteError", true);
        }
        else adminService.getUserRepo().deleteById(id);
        return "redirect:/admin/list";
    }

    //    Admin
    @GetMapping("/listAdmin")
    public String listAdmin(Model model) {
        adminService.checkAdmin();
        List<User> admins = adminService.getUserRepo().findByRole(User.Role.ADMIN);
        model.addAttribute("admins", admins);
        return "/admin/listAdmin";
    }
    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable("id") Integer id, Model model) {
        adminService.checkAdmin();
        Optional<User> optionalUser = adminService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            adminService.getSession().setAttribute("user", optionalUser.get());
            return "/admin/changePassword";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
    @PostMapping("/savePassword")
    public String savePassword(@Validated @ModelAttribute User user, @RequestParam("newPassword") String newPassword, @RequestParam("rePassword") String rePassword){
        adminService.checkAdmin();
        if (loginService.checkLogin(user.getUserName(),user.getPassword())){
            if(newPassword.equals(rePassword)) {
                user.setPassword(adminService.hashPassword(rePassword));
                adminService.getUserRepo().save(user);
                return "redirect:/admin/list";
            }
        }
        return "/admin/changePassword";
    }

    //Manager
    @GetMapping("/listManager")
    public String listManager(Model model) {
        adminService.checkAdmin();
        List<User> managers = adminService.getUserRepo().findByRole(User.Role.MANAGER);
        model.addAttribute("users", managers);
        return "/admin/listManager";
    }

    @PostMapping("/fillter")
    public String filterUsers(Model model ,@RequestParam("searchInput") String keyword, @RequestParam("filterSelect1") String filter1, @RequestParam("filterSelect2") String filter2) {
        adminService.checkAdmin();
        List<User> users = adminService.searchUsers(keyword, filter1, filter2);
        model.addAttribute("users", users);
        return "/admin/list";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleForbidden(HttpClientErrorException ex) {
        return "redirect:/login";
    }
}
