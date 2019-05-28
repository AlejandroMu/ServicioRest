package com.example.demo.service;

import java.util.*;

import javax.annotation.PostConstruct;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.icesi.model.*;
@Service
public class MedicineService{
    @Autowired
    private MedicineRepository medicines;
    @Autowired
    private InventoryService inventoryService;
    

    @PostConstruct
    public void post(){
        Medicine m1=new Medicine();
        m1.setName("Acetaminof");
        m1.setAdministationType("via oral");
        m1.setIndications("ninguna");
        m1.setContraIndications("ninguna");
        m1.setGenericName("Acetaminofén");
        m1.setLaboratory("genfar");
        medicines.save(m1); 

    }

	public List<Medicine> getMedicines() {
        List<Medicine> ret=new ArrayList<Medicine>();
        Iterable<Medicine> it=medicines.findAll();
        for (Medicine var : it) {
            ret.add(var);
        }
		return ret;
    }
    
    public Medicine addMedicine(Medicine med){
        return medicines.save(med);
    }

	public List<Medicine> filtrar(Date date) {
        List<MedicineInventory> inventories=inventoryService.filtrar(date);
        HashSet<Medicine> medicines=new HashSet<>();
        for (MedicineInventory inv : inventories) {
            medicines.add(inv.getMedicine());
        }
        List<Medicine> med=new ArrayList<>();
        medicines.forEach(x->med.add(x));
		return med;
	}
}