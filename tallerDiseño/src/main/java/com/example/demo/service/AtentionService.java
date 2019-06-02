package com.example.demo.service;

import java.util.*;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;

import com.example.demo.repository.AtentionRepository;
import com.example.demo.repository.PacientRepository;

@Service
public class AtentionService {
	@Autowired
	private AtentionRepository atentions;
	@Autowired
	private PacientService pacients;
	@Autowired
	private SupplyService supplyService;
	@Autowired
	private MedicineService medicines;

	@PostConstruct
	public void postcons() {
		List<Supply> supa = new ArrayList<>();
		Supply sup = new Supply(3, new Date(2019, 06, 8), "observaciones", "patologia");
		Pacient p = pacients.getPacient("1234");
		sup.setPacient(p);
		Medicine med=medicines.getMedicines().get(0);
		sup.setMedicine(med);
		supa.add(sup);
		Atencion nueva = new Atencion(new Date(2010, 04, 21), "generalDescription", "procedureDone", "observations");
		sup.setAtencion(nueva);
		nueva.setSupplys(supa);
		nueva.setPacient(p);
		try {
			addAtention(nueva);
			System.out.println("x");
		} catch (Exception e) {
			System.out.println("--- fallo ---");
			System.out.println(e.getMessage());
			System.out.println("--- fallo ---");
		}
	}
	public boolean addAtention(Atencion atention) throws Exception {
		Pacient pacient = atention.getPacient();
		List<Supply> supplys = atention.getSupplys();
		Pacient p1 = pacients.getPacient(pacient.getDocument());
		if (pacient.isState() && p1 != null) {
			if (supplys != null) {
				for (Supply supply : supplys) {
					if (!supply.getPacient().getDocument().equals(pacient.getDocument())) {
						throw new Exception("El paciente no coincide con el suministro");
					}
				}
				List<Supply> tmp = new ArrayList<Supply>();
				boolean fail = false;
				Exception e1 = new Exception();

				for (Supply supply : supplys) {
					try {
						Supply n = supplyService.addSupply(supply);
						tmp.add(n);
					} catch (Exception e) {
						fail = true;
						e1 = e;
						break;
					}
				}
				if (fail) {
					for (Supply supply : tmp) {
						supplyService.remove(supply);
					}
					throw e1;
				} else {
					atention.setSupplys(tmp);
				}
			}
			// atention.setDateHour(new Date());
			atentions.save(atention);

		} else {
			throw new Exception("El paciente no esta activo");
		}
		return true;
	}

	public List<Atencion> getAtencions() {
		List<Atencion> atention = new ArrayList<Atencion>();
		Iterator<Atencion> it = atentions.findAll().iterator();
		while (it.hasNext()) {
			atention.add(it.next());
		}

		return atention;
	}

	public List<Atencion> getAtencions(Date date) {
		return atentions.findByDateHour(date);
	}

}
