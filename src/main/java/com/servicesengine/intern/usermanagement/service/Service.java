package com.servicesengine.intern.usermanagement.service;

import com.servicesengine.intern.usermanagement.entity.User;

import java.sql.Date;

//@org.springframework.stereotype.Service
public interface Service {
    public User getUserInfor();
    public User changeName (String fullName);
    public  User changeUserName(String username);
    public  User changeDateOfBith(Date date);
    public User changeEmail(String emai);
    public  User changePhone(String phone);
}
