package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.icesi.model.Medicine;


@Repository
public interface MedicineRepository extends CrudRepository<Medicine,Integer>{
	

}
