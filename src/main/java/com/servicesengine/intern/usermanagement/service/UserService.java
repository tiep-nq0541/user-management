package com.servicesengine.intern.usermanagement.service;

import com.servicesengine.intern.usermanagement.entity.User;
import com.servicesengine.intern.usermanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ListUI;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User findById(Integer id){
        Optional<User> tmp = userRepo.findById(id);
        if(tmp.isPresent()){
            return tmp.get();
        }
        else return null;
    }

    public List<User> findAllUserId(Integer id){
        List<User> tmp = userRepo.findAll();
        return tmp;
    }

}
