package edu.icesi.clienterest.Delegado;

import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.icesi.clienterest.model.*;

/**
 * MedicineDelegado
 */
@Service
public class MedicineDelegado {
	private RestTemplate rest;

	public MedicineDelegado() {
		rest = new RestTemplate();
	}

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
	
	
	public List<Medicine> getMedicines() {
		ResponseEntity<List<Medicine>> rEntity = rest.exchange(url() + "/medicinas", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Medicine>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Medicinas is empty");

		return rEntity.getBody();
	}

	public void addMedicine(Medicine medicine) {
		if(medicine==null) 
			throw new IllegalArgumentException("Atencion is empty");
		
		
		rest.postForObject(url()+"/medicinas", medicine, Medicine.class);
	}

	public List<Medicine> filtrar(Date date) {
		if (date == null)
			throw new IllegalArgumentException("Date is null");
		ResponseEntity<List<Medicine>> rEntity = rest.exchange(url() + "/medicinas?date="+date, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Medicine>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Medicinas is empty");

		return rEntity.getBody();
	}

    
}