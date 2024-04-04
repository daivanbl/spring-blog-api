package com.example.springcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcrud.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
}
