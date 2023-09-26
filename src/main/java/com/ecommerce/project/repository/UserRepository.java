package com.ecommerce.project.repository;

import com.ecommerce.project.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
}
