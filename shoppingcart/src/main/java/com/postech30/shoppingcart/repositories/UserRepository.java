package com.postech30.shoppingcart.repositories;

import com.postech30.shoppingcart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
