package edu.icesi.clienterest;

import static org.junit.Assert.assertTrue;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import edu.icesi.clienterest.Delegado.PacienteDelegado;
import edu.icesi.clienterest.Delegado.UserDelegado;
import edu.icesi.clienterest.model.Pacient;
import edu.icesi.clienterest.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDelegado {
	@Autowired
	private UserDelegado delegado;

	
	
	@Test
	public void testSaveAndLoan(){
		BCryptPasswordEncoder pas = new BCryptPasswordEncoder(11);
        Pacient p = new Pacient("Pedro", "Fercho", "Sistemas");
        User us = new User("test@gmail.com", p.getNames(), p.getLastNames(), pas.encode("password"), p);
        delegado.save(us);
        User d=delegado.getUser("test@gmail.com");
        assertTrue(d.getUsername().equals("test@gmail.com"));
        
	}

}
