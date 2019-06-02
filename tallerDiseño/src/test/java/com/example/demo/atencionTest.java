package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.*;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PacientRepository;
import com.example.demo.service.AtentionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TallerDiseñoApplication.class)
public class atencionTest {
	@Autowired
	private AtentionService service;
	@Autowired
	private PacientRepository pacients;
	@Autowired
	private MedicineRepository medicines;

	private Atencion urgency;
	private Pacient pacient;
	private List<Supply> supplys;

	@Before
	public void before() {
		pacient = new Pacient();
		pacient.setDocument("101");
		pacient.setLastNames("Torres");
		pacient.setNames("Juan");
		pacient.setState(true);

		urgency = new Atencion();
		urgency.setPacient(pacient);
		urgency.setTransfer(true);

		supplys = new ArrayList<Supply>();
		Supply sup = new Supply();
		sup.setAmount(10);
		sup.setAtencion(urgency);
		sup.setPacient(pacient);

		Medicine medicine = new Medicine();
		medicine.setName("Acetaminofen");
		ArrayList<MedicineInventory> in = new ArrayList<MedicineInventory>();
		MedicineInventory in1 = new MedicineInventory();
		in1.setAmountAvailable(15);
		in1.setMedicine(medicine);
		in.add(in1);
		sup.setMedicine(medicine);
		supplys.add(sup);
		urgency.setSupplys(supplys);
		pacients.save(pacient);
		medicines.save(medicine);
	}

	@Test
	public void registrarUrgencia() throws Exception {
		try {
			assertTrue(service.addAtention(urgency));
		} catch (Exception e) {
			fail();
		}

	}

	@Test(expected=Exception.class)
	public void registrarFailPacient() throws Exception {
		pacient.setState(false);
		service.addAtention(urgency);

	}
	@Test
	public void registrarFailSupply() throws Exception {
		for (Supply suppl : supplys) {
			suppl.setAmount(20);
		}
		try {
			service.addAtention(urgency);
			fail();
		}catch (Exception e) {
			List<Atencion> atentions=service.getAtencions();
			assertTrue(atentions.isEmpty());
		}
		
	}

}
