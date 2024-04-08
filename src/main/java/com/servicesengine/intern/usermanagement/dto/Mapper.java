package com.servicesengine.intern.usermanagement.dto;

import com.servicesengine.intern.usermanagement.entity.User;

public class Mapper {
    public static UserDTO toUserDto(User user){
        UserDTO tmp = new UserDTO();
        tmp.setId(user.getId());
        tmp.setUserName(user.getUserName());
        tmp.setFullName(user.getFullName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setDateOfBirth(user.getDateOfBirth());
        tmp.setRole(user.getRole());
        return tmp;
    }
}
