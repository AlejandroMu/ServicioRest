package com.example.demo.service;

import java.util.*;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.icesi.model.*;

import com.example.demo.repository.AtentionRepository;
import com.example.demo.repository.PacientRepository;

@Service
public class AtentionService {
	@Autowired
	private AtentionRepository atentions;
	@Autowired
	private PacientRepository pacients;
	@Autowired
	private SupplyService supplyService;

	public boolean addAtention(UrgencyAtention atention) throws Exception {
		Pacient pacient = atention.getPacient();
		List<Supply> supplys = atention.getSupplys();
		Pacient p1 = pacients.findByDocument(pacient.getDocument());
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

	public List<UrgencyAtention> getAtencions() {
		List<UrgencyAtention> atention = new ArrayList<UrgencyAtention>();
		Iterator<UrgencyAtention> it = atentions.findAll().iterator();
		while (it.hasNext()) {
			atention.add(it.next());
		}

		return atention;
	}

	public List<UrgencyAtention> getAtencions(Date date) {
		return atentions.findByDateHour(date);
	}

}
