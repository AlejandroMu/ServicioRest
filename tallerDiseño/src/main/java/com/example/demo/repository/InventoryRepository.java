package com.example.demo.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;

@Repository
public interface InventoryRepository extends CrudRepository<MedicineInventory, Integer> {
    List<MedicineInventory> findByMedicine(Medicine med);
    List<MedicineInventory> findByDateExpiration(Date date);
}