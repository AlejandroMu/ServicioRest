package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.TallerDiseñoApplication;
import com.example.demo.model.*;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PacientRepository;
import com.example.demo.service.InventoryService;
import com.example.demo.service.SupplyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TallerDiseñoApplication.class)
public class supplyTest {

	@Autowired
	private SupplyService service;
	@Autowired
	private PacientRepository pacients;
	@Autowired
	private MedicineRepository medicines;
	@Autowired
	private InventoryService inventories;

	private Pacient pacient;
	private Supply supply;
	private Medicine medicine;

	public void context() {
		pacient = new Pacient();
		pacient.setDocument("101");
		pacient.setLastNames("wq");
		pacient.setNames("Juan");
		pacient.setState(true);

		supply = new Supply();
		supply.setAmount(19);
		supply.setPacient(pacient);
		medicine = new Medicine("Acetaminofén", "Ace", "Genfar", "Oral", "En la comida", "ninguna");

		medicine.setId(1);
		MedicineInventory inventory = new MedicineInventory();
		inventory.setAmountAvailable(11);
		inventory.setMedicine(medicine);
		inventory.setLocation("Cali");
		ArrayList<MedicineInventory> inv = new ArrayList<MedicineInventory>();
		inv.add(inventory);
		
		MedicineInventory inventory1 = new MedicineInventory();
		inventory1.setAmountAvailable(11);
		inventory1.setMedicine(medicine);
		inventory1.setLocation("Cartagena");
		
		inv.add(inventory1);
		
		supply.setMedicine(medicine);
		pacients.save(pacient);
		medicines.save(medicine);

	}

	@Test
	public void testP() {
		try {
			context();
			service.addSupply(supply);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Test(expected = Exception.class)
	public void addSupplyFail() throws Exception {
		supply.setAmount(15);
		service.addSupply(supply);

	}

	@Test(expected = Exception.class)
	public void addSupplyFailPacient() throws Exception {
		pacient.setState(false);
		service.addSupply(supply);
	}

	@Test
	public void testUpdateAviable() {
		try {
			context();
			service.addSupply(supply);
			List<MedicineInventory> in = inventories.filtrar(medicine);
			int amount = 0;
			for (MedicineInventory medicineInventory : in) {
				amount += medicineInventory.getAmountAvailable();
			}
			assertTrue(amount == 3);
		} catch (Exception e) {
			fail();
		}
	}

	public void updateSupplyFail() {
		supply.setAmount(15);
		try {
			service.addSupply(supply);
			fail();
		} catch (Exception e) {
			List<MedicineInventory> in = inventories.filtrar(medicine);
			int amount = 0;
			for (MedicineInventory medicineInventory : in) {
				amount += medicineInventory.getAmountAvailable();
			}
			assertTrue(amount == 22);
		}

	}

	public void updateSupplyFailPacient() {
		pacient.setState(false);
		try {
			service.addSupply(supply);
			fail();
		} catch (Exception e) {
			List<MedicineInventory> in = inventories.filtrar(medicine);
			int amount = 0;
			for (MedicineInventory medicineInventory : in) {
				amount += medicineInventory.getAmountAvailable();
			}
			assertTrue(amount == 22);
		}
	}
}
