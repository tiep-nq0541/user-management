package com.servicesengine.intern.usermanagement.controller;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import com.servicesengine.intern.usermanagement.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.aspectj.util.LangUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/checklogin")
    public String checkLogin(@ModelAttribute User userIP, Model model, RedirectAttributes redirectAttributes) {
        httpSession.setAttribute("lastUserName", userIP.getUserName());
        if (loginService.checkLogin(userIP.getUserName(), userIP.getPassword())) {
            User user = loginService.getUserRepo().findByUserName(userIP.getUserName());
            loginService.getSession().setAttribute("user_login", user);
            var role = user.getRole();
            switch (role) {
                case ADMIN -> {
                    return "redirect:/admin/list";
                }
                case MANAGER -> {
                    return "redirect:/manager/listUser";
                }
                case USER -> {
                    return "redirect:/user/information";
                }
            }
        }
        redirectAttributes.addFlashAttribute("errorMessage","User or password is not exit");
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        httpSession.removeAttribute("user_login");
        httpSession.removeAttribute("lastUserName");
        return "redirect:/login";
    }
}
