package com.example.demo.unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Medicine;
import com.example.demo.model.MedicineInventory;
import com.example.demo.model.Pacient;
import com.example.demo.model.Supply;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PacientRepository;
import com.example.demo.repository.SupplyRepository;
import com.example.demo.service.SupplyService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestSupply {
	@Autowired
	@InjectMocks
	private SupplyService service;
	@Mock
	private SupplyRepository repository;
	@Mock
	private PacientRepository pacients;
	@Mock
	private MedicineRepository medicines;
	
	private MedicineInventory inventory;
	private Pacient pacient;
	private Supply supply;
	private Medicine medicine;
	private Optional<Pacient> op;
	private Optional<Medicine> opM;
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		pacient = new Pacient();
		pacient.setDocument("101");
		pacient.setLastNames("wq");
		pacient.setNames("Juan");
		pacient.setState(true);
		op=Optional.of(pacient);

		supply = new Supply();
		supply.setAmount(10);
		supply.setPacient(pacient);
		medicine = new Medicine("Acetaminofén", "Ace", "Genfar", "Oral", "En la comida", "ninguna");

		medicine.setId(1);
		inventory = new MedicineInventory();
		inventory.setAmountAvailable(11);
		inventory.setMedicine(medicine);
		inventory.setLocation("Cali");
		ArrayList<MedicineInventory> inve=new ArrayList<MedicineInventory>();
		inve.add(inventory);
		supply.setMedicine(medicine);
		opM=Optional.of(medicine);

	}

	@Test
	public void addSupply() {
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);
		try {
			service.addSupply(supply);
			assertTrue(true);
		} catch (Exception e) {
			
			fail();
		}

	}

	@Test(expected = Exception.class)
	public void addSupplyFail() throws Exception {
		supply.setAmount(15);
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);

		service.addSupply(supply);

	}

	@Test(expected = Exception.class)
	public void addSupplyFailPacient() throws Exception {
		pacient.setState(false);
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);
		service.addSupply(supply);
	}

	@Test
	public void testUpdateAviable() {
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);
		try {
			service.addSupply(supply);
			assertTrue(inventory.getAmountAvailable()==1);
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void updateSupplyFail()  {
		supply.setAmount(15);
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);
		try {
			service.addSupply(supply);
			fail();
		} catch (Exception e) {
			assertTrue(inventory.getAmountAvailable()==11);
		}

	}

	public void updateSupplyFailPacient()  {
		pacient.setState(false);
		when(pacients.findById("101")).thenReturn(op);
		when(medicines.findById(1)).thenReturn(opM);

		try {
			service.addSupply(supply);
			fail();
		} catch (Exception e) {
			assertTrue(inventory.getAmountAvailable()==11);
		}
	}

}
