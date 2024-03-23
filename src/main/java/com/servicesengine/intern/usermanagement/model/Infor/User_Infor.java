package com.servicesengine.intern.usermanagement.model.Infor;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;

public class User_Infor {
    private String fullName;
    private Date date;
    private String email;
    private String phone;

    public User_Infor() {
        this.fullName = null;
        this.date = null;
        this.email = null;
        this.phone = null;
    }

    public User_Infor(String fullName, String userName, String passWord, Date date, String email, String phone, Timestamp time_created, Timestamp time_update, Timestamp time_delete, String role) {
        this.fullName = fullName;
        this.date = date;
        this.email = email;
        this.phone = phone;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
