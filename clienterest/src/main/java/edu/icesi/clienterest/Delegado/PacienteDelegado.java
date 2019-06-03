package edu.icesi.clienterest.Delegado;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
	public List<Pacient> getPacients() {
		ResponseEntity<List<Pacient>> rEntity = template.exchange(url() + "/pacientes", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Pacient>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Pacients is empty");

		return rEntity.getBody();
	}

	public Pacient getPacient(String id) {
		ResponseEntity<List<Pacient>> rEntity = template.exchange(url() + "/pacientes?id="+id, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Pacient>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Pacients is empty");

		return rEntity.getBody().get(0);
	}

	

    
}