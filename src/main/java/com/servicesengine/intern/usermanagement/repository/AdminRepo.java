package com.servicesengine.intern.usermanagement.repository;

import com.servicesengine.intern.usermanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
//    public List<User> findByName(String name);
}
