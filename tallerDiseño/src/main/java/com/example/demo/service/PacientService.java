package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;

@Service
public class PacientService {
    @Autowired
    private PacientRepository pacients;

    @PostConstruct
    public void post() {
        Pacient pacien = new Pacient("Juan", "Dias", "Sistemas");
        pacien.setDocument("1234");
        pacien.setState(true);
        pacients.save(pacien);

    }

    public Pacient getPacient(String id) {
        return pacients.findById(id).get();
    }

    public List<Pacient> getPacients() {
        List<Pacient> ret = new ArrayList<>();
        Iterable<Pacient> it = pacients.findAll();
        for (Pacient var : it) {
            ret.add(var);
        }
        return ret;
    }

    public void addPaciente(Pacient pas) {
        pacients.save(pas);

    }
}