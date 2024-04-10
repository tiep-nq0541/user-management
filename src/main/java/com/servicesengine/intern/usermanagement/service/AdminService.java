package com.servicesengine.intern.usermanagement.service;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {
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
        User tmp = (User) session.getAttribute("user");
        if ( checkPhone(user.getPhone()) && checkEmail(user.getEmail())) return true;
        if (!checkPhone(user.getPhone())){
            if (user.getPhone().isEmpty()){
                user.setPhone(tmp.getPhone());
            }else redirectAttributes.addFlashAttribute("errorPhone", "Phone is not correct");
        }
        if (!checkEmail(user.getEmail())){
            if (user.getEmail().isEmpty()){
                user.setEmail(tmp.getEmail());
            }else redirectAttributes.addFlashAttribute("errorEmail", "Email is not correct");
        }
        return false;
    }
    public boolean checkUserName(String username){
        return validUserName(username) && !existsUserName(username);
    }
    public boolean validUserName(String username){
        return !username.isEmpty();
    }
    public boolean existsUserName(String username){
        return userRepo.existsByUserName(username);
    }

    public boolean checkPhone(String phone){
        return validPhone(phone) && !existsPhone(phone);
    }
    public boolean existsPhone(String phone){
        User user = (User)session.getAttribute("user");
        if (user != null){
            if(user.getPhone().equals(phone)){
                return false;
            }
        }
        return userRepo.existsByPhone(phone);
    }
    public boolean validPhone(String phone){
        return phone.length() == 10 && isLong(phone);
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
        return validEmail(email) && !existsEmail(email);
    }
    public boolean existsEmail(String email){
        User user = (User)session.getAttribute("user");
        if (user != null){
            if(user.getEmail().equals(email)){
                return false;
            }
        }
        return userRepo.existsByEmail(email);
    }
    public boolean validEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void checkAdmin() {
        User user = (User) loginService.getSession().getAttribute("user_login");
        if (user == null || user.getRole() != User.Role.ADMIN) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }
    public boolean checkCanDelete(Integer id){
        User user = (User) session.getAttribute("user_login");
        return !user.getId().equals(id);
    }

    public List<User> searchUsers(String keyword, String filter1, String filter2) {
        switch (filter2){
            case "fillAll" :
                switch (filter1){
                case "fillAll" : return userRepo.search(keyword);
                case "fillUserName" : return userRepo.searchUserName(keyword);
                case "fillFullName" : return userRepo.searchFullName(keyword);
                case "fillEmail" : return userRepo.searchEmail(keyword);
            }
            case "fillUser" :
                switch (filter1){
                    case "fillAll" : {
                        List<User> tmp = userRepo.search(keyword);
                        List<User> users = new ArrayList<>();
                        for (User user : tmp){
                            if (user.getRole().equals(User.Role.USER)){
                                System.out.println("hello");
                                users.add(user);
                            }
                        }
                        return users;
                    }
                    case "fillUserName" : {
                        List<User> tmp = userRepo.searchUserName(keyword);
                        List<User> users = new ArrayList<>();
                        for (User user : tmp){
                            if (user.getRole().equals(User.Role.USER)){
                                users.add(user);
                            }
                        }
                        return users;
                    }
                    case "fillFullName" :{
                        List<User> tmp = userRepo.searchFullName(keyword);
                        List<User> users = new ArrayList<>();
                        for (User user : tmp){
                            if (user.getRole().equals(User.Role.USER)){
                                users.add(user);
                            }
                        }
                        return users;
                    }
                    case "fillEmail" :{
                        List<User> tmp = userRepo.searchEmail(keyword);
                        List<User> users = new ArrayList<>();
                        for (User user : tmp){
                            if (user.getRole().equals(User.Role.USER)){
                                users.add(user);
                            }
                        }
                        return users;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + filter1);
                }
            case "fillManager" :switch (filter1){
                case "fillAll" : {
                    List<User> tmp = userRepo.search(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.MANAGER)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillUserName" : {
                    List<User> tmp = userRepo.searchUserName(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.MANAGER)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillFullName" :{
                    List<User> tmp = userRepo.searchFullName(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.MANAGER)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillEmail" :{
                    List<User> tmp = userRepo.searchEmail(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.MANAGER)){
                            users.add(user);
                        }
                    }
                    return users;
                }
            }
            case "fillAdmin" :switch (filter1){
                case "fillAll" : {
                    List<User> tmp = userRepo.search(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.ADMIN)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillUserName" : {
                    List<User> tmp = userRepo.searchUserName(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.ADMIN)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillFullName" :{
                    List<User> tmp = userRepo.searchFullName(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.ADMIN)){
                            users.add(user);
                        }
                    }
                    return users;
                }
                case "fillEmail" :{
                    List<User> tmp = userRepo.searchEmail(keyword);
                    List<User> users = new ArrayList<>();
                    for (User user : tmp){
                        if (user.getRole().equals(User.Role.ADMIN)){
                            users.add(user);
                        }
                    }
                    return users;
                }
            }
        }
        return userRepo.search(keyword);
    }
    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
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

}
