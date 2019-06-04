package edu.icesi.clienterest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.icesi.clienterest.Delegado.PacienteDelegado;
import edu.icesi.clienterest.model.Pacient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPacienteDelegado {
	@Autowired
	private PacienteDelegado delegado;

	

	@Test
	public void testGetPacients() {
		List<Pacient> list = delegado.getPacients();

		assertTrue(list.size() == 1);

	}
	@Test
	public void testGetPacient() {
		Pacient pacient = delegado.getPacient("1234");

		assertTrue(pacient.getNames().equals("Juan"));

	}
}
