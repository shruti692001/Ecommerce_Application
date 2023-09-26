package com.ecommerce.project.repository;

import com.ecommerce.project.module.Cart;
import com.ecommerce.project.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository  extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
