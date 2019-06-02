package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;


@Repository
public interface PacientRepository extends CrudRepository<Pacient,String>{
    Pacient findByDocument(String d);
	
}
