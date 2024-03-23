package com.servicesengine.intern.usermanagement.model.mapper;

import com.servicesengine.intern.usermanagement.model.Infor.Manager_Infor;
import com.servicesengine.intern.usermanagement.model.Infor.User_Infor;
import com.servicesengine.intern.usermanagement.entity.User;

public class Mapper {
    public static User_Infor toUser_Infor(User user){
        User_Infor tmp = new User_Infor();
        tmp.setFullName(user.getFullName());
        tmp.setDate(user.getDate());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        return tmp;
    }
    public static Manager_Infor toUser_Manager(User user){
        Manager_Infor tmp = new Manager_Infor();
        tmp.setFullName(user.getFullName());
        tmp.setDate(user.getDate());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        return tmp;
    }

}
