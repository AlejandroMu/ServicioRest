package com.example.demo.restController;

import java.util.Date;
import java.util.List;

import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;

/**
 * AtencionControler
 */
@RestController
public class AtencionControler {

    @Autowired
    private AtentionService atenciones;
    @Autowired
    private InventoryService inventarios;
    @Autowired
    private MedicineService medicinas;
    @Autowired
    private PacientService pacientes;
    @Autowired
    private SupplyService suministros;
    @Autowired
    private UserRepository usuarios;

    @RequestMapping(value = "/suministros", method = RequestMethod.GET)
    public List<Supply> getSupply() {
        return suministros.getSupplys();
    }

    @RequestMapping(value = "/suministros", method = RequestMethod.POST)
    public void addSupply(@RequestBody Supply sup) {
        try {
            suministros.addSupply(sup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    public List<Pacient> getPacientes(@RequestParam(value = "id",required = false) String id) {
        if (id != null) {

            pacientes.getPacient(id);
        }
        return pacientes.getPacients();
    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.POST)
    public void addPaciente(@RequestBody Pacient pacient) {
        pacientes.addPaciente(pacient);
    }

    @RequestMapping(value = "/atenciones", method = RequestMethod.POST)
    public void addAtencion(@RequestBody Atencion atencion) {
        try {
            atenciones.addAtention(atencion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/atenciones", method = RequestMethod.GET)
    public List<Atencion> getAtenciones(@RequestParam(value = "date", required = false) Date date) {
        if (date != null) {
            return atenciones.getAtencions(date);
        } else {
            return atenciones.getAtencions();
        }

    }

    @RequestMapping(value = "/inventarios", method = RequestMethod.POST)
    public void addInventarion(@RequestBody MedicineInventory inventario) {
        inventarios.addInventory(inventario);
    }

    @RequestMapping(value = "/inventarios", method = RequestMethod.GET)
    public List<MedicineInventory> getInventarios(@RequestParam(value = "medicina", required = false) Medicine medicina,
            @RequestParam(value = "fecha", required = false) Date fecha) {
        if (medicina != null) {
            return inventarios.filtrar(medicina);
        } else if (fecha != null) {
            return inventarios.filtrar(fecha);
        } else {
            return inventarios.getAll();
        }
    }

    @RequestMapping(value = "/medicinas", method = RequestMethod.GET)
    public List<Medicine> getMedicines(@RequestParam(value = "fecha", required = false) Date fecha) {
        if (fecha != null) {
            return medicinas.filtrar(fecha);
        } else {
            return medicinas.getMedicines();
        }
    }

    @RequestMapping(value = "/medicinas", method = RequestMethod.POST)
    public void addMedicine(@RequestBody Medicine med) {
        medicinas.addMedicine(med);
    }
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public User getUser(@RequestParam String name){
        return usuarios.findByUsername(name).get();
    }
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        usuarios.save(user);

    }
}