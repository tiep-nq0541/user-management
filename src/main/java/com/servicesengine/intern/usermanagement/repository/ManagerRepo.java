package com.servicesengine.intern.usermanagement.repository;

import com.servicesengine.intern.usermanagement.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager,Integer> {

}
