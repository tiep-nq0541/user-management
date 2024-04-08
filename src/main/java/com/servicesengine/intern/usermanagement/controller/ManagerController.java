package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.service.LoginService;
import com.servicesengine.intern.usermanagement.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private LoginService loginService;


    //    User
    @GetMapping("/listUser")
    public String listUser(Model model) {
        managerService.checkManager();
        List<User> users = managerService.getUserRepo().findByRole(User.Role.USER);
        model.addAttribute("users", users);
        return "/manager/listUser";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        managerService.checkManager();
        User user = (User) managerService.getSession().getAttribute("user");
        if( user == null ) user = new User();
        model.addAttribute("user", user);
        return "/manager/addUser";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        managerService.checkManager();
        LocalDateTime current = LocalDateTime.now();
        if (user.getId() == null) {
            user.setTimeCreated(current);
            if (!managerService.checkAddUser(user, redirectAttributes)) {
                managerService.getSession().setAttribute("user", user);
                return "redirect:/manager/addUser";
            }
        } else {
            user.setTimeUpdated(current);
            if (!managerService.checkEditUser(user, redirectAttributes)) {
                managerService.getSession().setAttribute("user", user);
                return "redirect:/manager/addUser";
            }
        }
        user.setPassword(managerService.hashPassword(user.getPassword()));
        managerService.getUserRepo().save(user);
        managerService.getSession().removeAttribute("user");
        return "redirect:/manager/listUser";
    }


    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        managerService.checkManager();
        Optional<User> optionalUser =managerService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            managerService.getSession().setAttribute("user", optionalUser.get());
            return "/manager/addUser";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @GetMapping("/inforUser/{id}")
    public String inforUser(@PathVariable("id") Integer id, Model model) {
        managerService.checkManager();
        Optional<User> optionalUser = managerService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/manager/inforUser";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        managerService.checkManager();
        if (!managerService.checkCanDelete(id)){
            model.addAttribute("deleteError", true);
        }
        else managerService.getUserRepo().deleteById(id);
        return "redirect:/manager/listUser";
    }

    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable("id") Integer id, Model model) {
        managerService.checkManager();
        Optional<User> optionalUser = managerService.getUserRepo().findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            managerService.getSession().setAttribute("user", optionalUser.get());
            return "/manager/changePassword";
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
    @PostMapping("/savePassword")
    public String savePassword(@Validated @ModelAttribute User user, @RequestParam("newPassword") String newPassword, @RequestParam("rePassword") String rePassword){
        managerService.checkManager();
        if (loginService.checkLogin(user.getUserName(),user.getPassword())){
            if(newPassword.equals(rePassword)) {
                user.setPassword(managerService.hashPassword(rePassword));
                managerService.getUserRepo().save(user);
                return "redirect:/manager/listUser";
            }
        }
        return "/manager/changePassword";
    }


    @PostMapping("/fillter")
    public String filterUsers(Model model ,@RequestParam("searchInput") String keyword, @RequestParam("filterSelect") String filter) {
        managerService.checkManager();
        List<User> users = managerService.searchUsers(keyword, filter);
        model.addAttribute("users", users);
        return "/manager/listUser";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleForbidden(HttpClientErrorException ex) {
        return "redirect:/login";
    }
}


