package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.icesi.model.User;
@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    Optional<User> findByUsername(String username);
}