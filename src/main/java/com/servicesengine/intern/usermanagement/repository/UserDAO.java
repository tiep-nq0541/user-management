package com.servicesengine.intern.usermanagement.repository;

import com.servicesengine.intern.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDAO extends PagingAndSortingRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);
}