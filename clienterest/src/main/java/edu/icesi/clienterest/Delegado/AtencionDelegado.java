package edu.icesi.clienterest.Delegado;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import edu.icesi.clienterest.model.*;

/**
 * AtencionDelegado
 */
@Service
public class AtencionDelegado {

	private RestTemplate rest;

	public AtencionDelegado(){
		rest=new RestTemplate();
	}
	
	public List<Atencion> getAtencions() {
		return null;
	}

	public List<Atencion> getAtencions(Date date) {
		return null;
	}

	public void addAtention(Atencion a) {
	}

    
}