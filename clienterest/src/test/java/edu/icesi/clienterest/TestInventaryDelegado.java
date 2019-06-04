package edu.icesi.clienterest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.icesi.clienterest.Delegado.InventoryDelegado;
import edu.icesi.clienterest.Delegado.MedicineDelegado;
import edu.icesi.clienterest.model.Atencion;
import edu.icesi.clienterest.model.Medicine;
import edu.icesi.clienterest.model.MedicineInventory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInventaryDelegado {

	@Autowired
	private InventoryDelegado delegado;
	
	@Test
	public void testFiltrar() {
		Medicine d=new Medicine();
		MedicineInventory f=new MedicineInventory();
		f.setId(2);
		ArrayList<MedicineInventory> ff=new ArrayList<>();
		ff.add(f);
		d.setInventarios(ff);
		List<MedicineInventory> ss=delegado.filtrar(d);
		assertTrue(ss.size()>0);
	}
	
}
