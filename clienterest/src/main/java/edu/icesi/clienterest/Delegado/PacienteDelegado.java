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

	public PacienteDelegado(){
		template=new RestTemplate();
	}

	public List<Pacient> getPacients() {
		return null;
	}

	public Pacient getPacient(String id) {

		Pacient resultado = template.getForObject("https://servicerestpacientes.herokuapp.com/pacientes?id="+id, Pacient.class);
		return resultado;
	}


    
}