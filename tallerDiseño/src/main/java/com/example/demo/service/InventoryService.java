package com.example.demo.service;

import java.util.*;

import javax.annotation.PostConstruct;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventories;
    @Autowired
    private MedicineService medicineService;

    @PostConstruct
    public void post() {
        MedicineInventory in1 = new MedicineInventory();
        in1.setAmountAvailable(20);
        in1.setDateExpiration(new Date());
        in1.setLocation("location");
        Medicine medicin=medicineService.getMedicines().get(0);
       
        in1.setMedicine(medicin);
        inventories.save(in1);
    }
    public List<MedicineInventory> filtrar(Medicine med){
        return inventories.findByMedicine(med);
    }

    public List<MedicineInventory> filtrar(Date date){
        return inventories.findByDateExpiration(date);
    }

    public List<MedicineInventory> getAll() {
        Iterator<MedicineInventory> i = inventories.findAll().iterator();
        List<MedicineInventory> ret = new ArrayList<MedicineInventory>();
        while (i.hasNext()) {
            ret.add(i.next());
        }
        return ret;
    }

    public void saveAll(List<MedicineInventory> l) {
        for (MedicineInventory var : l) {
            inventories.save(var);

        }
    }

    public MedicineInventory addInventory(MedicineInventory inv) {
        return inventories.save(inv);
    }
	public void update(MedicineInventory medicineInventory) {
        inventories.save(medicineInventory);
	}
}