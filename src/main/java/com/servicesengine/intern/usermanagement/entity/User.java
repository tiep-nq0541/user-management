package com.servicesengine.intern.usermanagement.entity;

import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class User {
    private int Id;
    private String fullName;
    private String userName;
    private String passWord;
    private Date date;
    private String email;
    private String phone;
    private Timestamp time_created;
    private Timestamp time_update;
    private Timestamp time_delete;
    private String role;

    public User(String fullName, String userName, String passWord, Date date, String email, String phone, Timestamp time_created, Timestamp time_update, Timestamp time_delete, String role) {
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.time_created = time_created;
        this.time_update = time_update;
        this.time_delete = time_delete;
        this.role = role;
    }

    public User(int id, String fullName, String userName, String passWord, Date date, String email, String phone, Timestamp time_created, Timestamp time_update, Timestamp time_delete, String role) {
        Id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.time_created = time_created;
        this.time_update = time_update;
        this.time_delete = time_delete;
        this.role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public Timestamp getTime_created() {
        return time_created;
    }

    public void setTime_created(Timestamp time_created) {
        this.time_created = time_created;
    }

    public Timestamp getTime_update() {
        return time_update;
    }

    public void setTime_update(Timestamp time_update) {
        this.time_update = time_update;
    }

    public Timestamp getTime_delete() {
        return time_delete;
    }

    public void setTime_delete(Timestamp time_delete) {
        this.time_delete = time_delete;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
