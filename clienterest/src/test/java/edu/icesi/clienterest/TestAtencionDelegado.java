package edu.icesi.clienterest;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.icesi.clienterest.Delegado.AtencionDelegado;
import edu.icesi.clienterest.Delegado.InventoryDelegado;
import edu.icesi.clienterest.model.Atencion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAtencionDelegado {

	@Autowired
	private AtencionDelegado delegado;
@Test
	public void testGetAtencionByDate() {
		Atencion d=delegado.getAtencions(new Date(3910,05,21)).get(0);
		assertTrue(d!=null);
	}
@Test
	public void testGetAtencion() {
		List<Atencion> d=delegado.getAtencions();
		assertTrue(d.size()>0);

	}
@Test
	public void testAddAtencion() {
		int aux0=delegado.getAtencions().size();
		Atencion d=new Atencion();
		delegado.addAtention(d);
		int aux1=delegado.getAtencions().size();
		assertTrue(aux0!=aux1);
		
	}
}
