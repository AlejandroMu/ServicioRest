package edu.icesi.clienterest.Delegado;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.icesi.clienterest.model.*;

/**
 * PacienteDelegado
 */
@Service
public class PacienteDelegado {

	private RestTemplate template;

	public List<Pacient> getPacients() {
		return null;
	}

	public Pacient getPacient(String string) {

		Pacient resultado = template.getForObject("https://serverrestpacientes.herokuapp.com/pacientes?", Pacient.class);
		return null;
	}


    
}