package edu.icesi.clienterest;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.intercept.aspectj.MethodInvocationAdapter;
import org.springframework.test.context.junit4.SpringRunner;

import edu.icesi.clienterest.Delegado.MedicineDelegado;
import edu.icesi.clienterest.Delegado.UserDelegado;
import edu.icesi.clienterest.model.Medicine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMedicineDelegado {

	@Autowired
	private MedicineDelegado delegado;
	
	
	@Test
	public void testGetMedicine() {
		assertTrue(delegado.getMedicines().size()>0);
	}
	@Test
	public void testAddMedicine() {
		Medicine dd=new Medicine();
		dd.setGenericName("test");
		delegado.addMedicine(dd);
		List<Medicine> d=delegado.getMedicines();
		for (int i = 0; i < d.size(); i++) {
			if(d.get(i).getGenericName().equals("test"))
				assertTrue(true);
		}
	}
	@Test
	public void testFiltar() {
		Date d=new Date(2019,03,06);
		assertTrue(delegado.filtrar(d)!=null);
		
	}
}
