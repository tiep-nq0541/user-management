package com.servicesengine.intern.usermanagement.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ManagerService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private HttpSession session;
    @Autowired
    private LoginService loginService;
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean checkAddUser(User user, RedirectAttributes redirectAttributes){
        if (checkUserName(user.getUserName()) && checkPhone(user.getPhone()) && checkEmail(user.getEmail())) return true;
        if(!checkUserName(user.getUserName())){
            redirectAttributes.addFlashAttribute("errorUserName", "Username is exited");
        }
        if (!checkPhone(user.getPhone())){
            redirectAttributes.addFlashAttribute("errorPhone", "Phone is not correct");
        }
        if (!checkEmail(user.getEmail())){
            redirectAttributes.addFlashAttribute("errorEmail", "Email is not correct");
        }
        return false;
    }
    public boolean checkEditUser(User user, RedirectAttributes redirectAttributes){
        if ( checkPhone(user.getPhone()) && checkEmail(user.getEmail())) return true;
        if (!checkPhone(user.getPhone())){
            redirectAttributes.addFlashAttribute("errorPhone", "Phone is not correct");
        }
        if (!checkEmail(user.getEmail())){
            redirectAttributes.addFlashAttribute("errorEmail", "Email is not correct");
        }
        return false;
    }
    public boolean checkUserName(String username){
        if (validUserName(username) && !existsUserName(username)) return true;
        return false;
    }
    public boolean validUserName(String username){
        if (username == "") return false;
        return true;
    }
    public boolean existsUserName(String username){
        if (userRepo.existsByUserName(username)) return true;
        return false;
    }

    public boolean checkPhone(String phone){
        if (validPhone(phone) && !existsPhone(phone)) return true;
        return false;
    }
    public boolean existsPhone(String phone){
        User user = (User)session.getAttribute("user");
        if(phone == "" || user.getPhone().equals(phone)){
            phone = user.getPhone();
            return false;
        }
        if (userRepo.existsByPhone(phone)) return true;
        return false;
    }
    public boolean validPhone(String phone){
        if(phone.length() == 10 && isLong(phone)){
            return true;
        }
        return false;
    }
    public boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkEmail(String email){
        if (validEmail(email) && !existsEmail(email)) return true;
        return false;
    }
    public boolean existsEmail(String email){
        User user = (User)session.getAttribute("user");
        if(email == "" || user.getEmail().equals(email)){
            email = user.getEmail();
            return false;
        }
        if (userRepo.existsByEmail(email)) return true;
        return false;
    }
    public boolean validEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void checkManager() {
        User user = (User) loginService.getSession().getAttribute("user_login");
        if (user == null || user.getRole() != User.Role.MANAGER) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }
    public boolean checkCanDelete(Integer id){
        User user = (User) session.getAttribute("user_login");
        if (user.getId().equals(id)) return false;
        return true;
    }

    public List<User> searchUsers(String keyword, String filter1) {
        switch (filter1) {
            case "fillAll": {
                List<User> tmp = userRepo.search(keyword);
                List<User> users = new ArrayList<User>();
                for (User user : tmp) {
                    if (user.getRole().equals(User.Role.USER)) {
                        users.add(user);
                    }
                }
                return users;
            }
        }
        return userRepo.search(keyword);
    }
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
