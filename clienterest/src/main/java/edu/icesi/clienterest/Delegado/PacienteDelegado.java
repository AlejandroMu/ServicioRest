package edu.icesi.clienterest.Delegado;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.icesi.clienterest.model.*;

/**
 * PacienteDelegado
 */
@Service
public class PacienteDelegado {
	public static final String URL="https://servicerestpacientes.herokuapp.com/pacientes";

	private RestTemplate template;

	public PacienteDelegado(){
		template=new RestTemplate();
	}

	public List<Pacient> getPacients() {
		return null;
	}

	public Pacient getPacient(String id) {
		try{
		String query=URL + "?id="+id;
		ResponseEntity<Pacient> response= template.getForEntity(query, Pacient.class);
		return response.getBody();
		}catch(Exception e){
			System.out.println("---error--");
			System.out.println(e.getMessage());
			System.out.println("---error--");
			return null;
		}
	}


    
}