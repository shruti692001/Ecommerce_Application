package com.ecommerce.project.repository;

import com.ecommerce.project.module.AuthenticationToken;
import com.ecommerce.project.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);

}
