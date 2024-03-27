package com.servicesengine.intern.usermanagement.repository;

import com.servicesengine.intern.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
//    public User findByName(String name);
}
