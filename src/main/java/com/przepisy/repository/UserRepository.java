package com.przepisy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.przepisy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}