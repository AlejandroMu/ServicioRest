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
 * AtencionDelegado
 */
@Service
public class AtencionDelegado {
	private RestTemplate rest;

	public AtencionDelegado() {
		rest = new RestTemplate();
	}

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}

	public List<Atencion> getAtencions() {
		ResponseEntity<List<Atencion>> rEntity = rest.exchange(url() + "/atenciones", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Atencion>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Atencions is empty");

		return rEntity.getBody();
	}

	public List<Atencion> getAtencions(Date date) {
		if (date == null)
			throw new IllegalArgumentException("Date is null");
		ResponseEntity<List<Atencion>> rEntity = rest.exchange(url() + "/atenciones?date="+date, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Atencion>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Atencions is empty");

		return rEntity.getBody();
	}

	public void addAtention(Atencion a) {
		if(a==null) 
			throw new IllegalArgumentException("Atencion is empty");
		
		rest.put(url()+"/atenciones", a);
	}

}