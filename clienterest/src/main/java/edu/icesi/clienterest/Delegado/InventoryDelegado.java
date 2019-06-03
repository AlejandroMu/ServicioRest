package edu.icesi.clienterest.Delegado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.icesi.clienterest.model.*;
/**
 * InventoryDelegado
 */
@Service
public class InventoryDelegado {
	private RestTemplate rest;
	
	public InventoryDelegado() {
		rest=new RestTemplate();
	}

	public String url() {
		return "https://servicerestpacientes.herokuapp.com";
	}
	public void addInventory(MedicineInventory inventario) {
		if(inventario==null)
			throw new IllegalArgumentException("Inventary is null");
		
		
		rest.postForObject(url()+"/inventarios", inventario, MedicineInventory.class);
	}
	public List<MedicineInventory> filtrar(Medicine med){
		ResponseEntity<List<MedicineInventory>> rEntity = rest.exchange(url() + "/inventarios", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MedicineInventory>>() {
				});
		if (rEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED)
			throw new IllegalArgumentException("Inventary is empty");

		return fixList(rEntity.getBody(), med);
	}
	public List<MedicineInventory> fixList(List<MedicineInventory> list,Medicine med){
		List<MedicineInventory> retur=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(med.getId()==list.get(i).getId()) {
				retur.add(list.get(i));
			}
		}
		return retur;
	}
    
}