package com.servicesengine.intern.usermanagement.service;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.model.Infor.User_Infor;
import com.servicesengine.intern.usermanagement.model.mapper.Mapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class LoginService {
    private static ArrayList<User> users = new ArrayList<User>();
    static {
        users.add(new User("A", "A","pass", null, "a@gmail.com", "0123456789", null, null, null,"user"));
        users.add(new User("B", "A","pass", null, "a@gmail.com", "0123456789", null, null, null,"user"));
        users.add(new User("C", "A","pass", null, "a@gmail.com", "0123456789", null, null, null,"user"));
        users.add(new User("D", "A","pass", null, "a@gmail.com", "0123456789", null, null, null,"user"));
        users.add(new User("E", "A","pass", null, "a@gmail.com", "0123456789", null, null, null,"user"));

    }

    public LoginService() {
    }

    public User isValidUser(String username, String password) {
        for(User user: users){
            if(user.getUserName().equals(username) && user.getPassWord().equals(password)){
                return user;
            }
        }
        return null;
    }


//    public User_Infor getUserInfor() {
//        return Mapper.toUser_Infor(user);
//    }
//
//
//    public User_Infor changeUserInfor(String fullName) {
//        return null;
//    }
//
//    public User_Infor changePassword() {
//        return null;
//    }
//
}
