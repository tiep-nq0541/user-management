package com.servicesengine.intern.usermanagement.model.Infor;

import java.sql.Date;

public class Manager_Infor {

    private String fullName;
    private String userName;
    private Date date;
    private String email;
    private String phone;

    public Manager_Infor(String fullName, String userName, Date date, String email, String phone) {
        this.fullName = fullName;
        this.userName = userName;
        this.date = date;
        this.email = email;
        this.phone = phone;
    }

    public Manager_Infor() {
        this.fullName = null;
        this.userName = null;
        this.date = null;
        this.email = null;
        this.phone = null;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


