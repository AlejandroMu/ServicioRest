package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.icesi.model.Supply;


@Repository
public interface SupplyRepository extends CrudRepository<Supply,Integer> {
	
}
