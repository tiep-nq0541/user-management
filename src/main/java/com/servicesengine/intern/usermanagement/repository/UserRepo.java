package com.servicesengine.intern.usermanagement.repository;

import com.servicesengine.intern.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    List<User> findByRole(User.Role role);
    User findByPhone(String phone);
    User findByEmail(String email);
    boolean existsByUserName(String username);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.userName LIKE %:keyword% OR u.fullName LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> search(@Param("keyword") String keyword);
    @Query("SELECT u FROM User u WHERE u.userName LIKE %:keyword%")
    List<User> searchUserName(@Param("keyword") String keyword);
    @Query("SELECT u FROM User u WHERE u.fullName LIKE %:keyword% ")
    List<User> searchFullName(@Param("keyword") String keyword);
    @Query("SELECT u FROM User u WHERE u.email LIKE %:keyword%")
    List<User> searchEmail(@Param("keyword") String keyword);

}
