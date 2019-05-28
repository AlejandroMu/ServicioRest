package com.example.demo.unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import edu.icesi.model.*;
import com.example.demo.repository.AtentionRepository;
import com.example.demo.repository.PacientRepository;
import com.example.demo.service.AtentionService;
import com.example.demo.service.SupplyService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class atencionTest {
	@InjectMocks
	private AtentionService service;
	@Mock
	private AtentionRepository atention;
	@Mock
	private SupplyService supply;
	@Mock
	private PacientRepository pacients;

	private UrgencyAtention urgency;
	private Pacient pacient;
	private List<Supply> supplys;
	private Optional<Pacient> op;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		pacient = new Pacient();
		pacient.setDocument("101");
		pacient.setLastNames("Torres");
		pacient.setNames("Juan");
		pacient.setState(true);

		urgency = new UrgencyAtention();
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
		op=Optional.of(pacient);

	}

	@Test
	public void registrarUrgencia() throws Exception {
		when(pacients.findById("101")).thenReturn(op);
		for (Supply suppl : supplys) {
			when(supply.addSupply(suppl)).thenReturn(suppl);
		}
		try {
			assertTrue(service.addAtention(urgency));
		} catch (Exception e) {
			fail();
		}

	}

	@Test(expected=Exception.class)
	public void registrarFailPacient() throws Exception {
		pacient.setState(false);
		when(pacients.findById("101")).thenReturn(op);
		service.addAtention(urgency);

	}
	@Test
	public void registrarFailSupply() throws Exception {
		when(pacients.findById("101")).thenReturn(op);
		for (Supply suppl : supplys) {
			suppl.setAmount(20);
			when(supply.addSupply(suppl)).thenThrow(Exception.class);
		}
		try {
			service.addAtention(urgency);
			fail();
		}catch (Exception e) {
			List<UrgencyAtention> atentions=service.getAtencions();
			assertTrue(atentions.isEmpty());
		}
		
	}

}
