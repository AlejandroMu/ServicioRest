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

<<<<<<< HEAD
	public AtencionDelegado() {
		rest = new RestTemplate();
	}
=======
	private RestTemplate rest;
>>>>>>> d285b0857a946ab2e1aee1ec2756716fda6d924e

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}

	public List<Atencion> getAtencions() {
		ResponseEntity<List<Atencion>> rEntity = rest.exchange(url() + "/atenciones", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Atencion>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Atencions it's empty");

		return rEntity.getBody();
	}

	public List<Atencion> getAtencions(Date date) {
		if (date == null)
			throw new IllegalArgumentException("Date is null");
		ResponseEntity<List<Atencion>> rEntity = rest.exchange(url() + "/atenciones", HttpMethod.GET, date,
				new ParameterizedTypeReference<List<Atencion>>() {
				});
		return null;
	}

	public void addAtention(Atencion a) {
		if(a==null) {
			
		}
	}

}