package com.servicesengine.intern.usermanagement.entity;

public class Mapper {
    public static Manager toManager(User user){
        Manager tmp = new Manager();
        tmp.setFullName(user.getFullName());
        tmp.setUserName(user.getUserName());
        tmp.setPassword(user.getPassword());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setId(user.getId());
        tmp.setRole(user.getRole());
        return tmp;
    }
    public static Admin toAdmin(User user){
        Admin tmp = new Admin();
        tmp.setFullName(user.getFullName());
        tmp.setUserName(user.getUserName());
        tmp.setPassword(user.getPassword());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setId(user.getId());
        tmp.setRole(user.getRole());
        return tmp;
    }
}
