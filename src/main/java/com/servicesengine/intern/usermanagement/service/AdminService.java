//package com.servicesengine.intern.usermanagement.service;
//
//import com.servicesengine.intern.usermanagement.entity.User;
//import com.servicesengine.intern.usermanagement.repository.UserRepo;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AdminService {
//    private UserRepo userRepo;
//    public User findById(Integer id){
//        Optional<User> tmp = userRepo.findById(id);
//        if(tmp.isPresent()){
//            return tmp.get();
//        }
//        else return null;
//    }
//
//    public List<User> findByName(String name){
//        return null;
//    }
//}
