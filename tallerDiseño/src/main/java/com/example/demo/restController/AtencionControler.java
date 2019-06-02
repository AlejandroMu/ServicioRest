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


    @RequestMapping(name = "/atenciones", method = RequestMethod.POST)
    public void addAtencion(@RequestBody Atencion atencion) {
        try {
            atenciones.addAtention(atencion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(name = "/atenciones", method = RequestMethod.GET)
    public List<Atencion> getAtenciones(@RequestParam(name = "date",required = false) Date date) {
        if(date!=null){
            return atenciones.getAtencions(date);
        }else{
            return atenciones.getAtencions();
        }

    }

    @RequestMapping(name = "/inventarios", method = RequestMethod.POST)
    public void addInventarion(@RequestBody MedicineInventory inventario) {
        inventarios.addInventory(inventario);
    }

    @RequestMapping(name = "/inventarios", method = RequestMethod.GET)
    public List<MedicineInventory> getInventarios(@RequestParam(name = "medicina", required = false) Medicine medicina,
            @RequestParam(name = "fecha", required = false) Date fecha) {
        if (medicina != null) {
            return inventarios.filtrar(medicina);
        } else if (fecha != null) {
            return inventarios.filtrar(fecha);
        } else {
            return inventarios.getAll();
        }
    }

    @RequestMapping(name = "/medicinas", method = RequestMethod.GET)
    public List<Medicine> getMedicines(@RequestParam(name="fecha", required=false) Date fecha) {
        if(fecha!=null){
            return medicinas.filtrar(fecha);
        }else{
            return medicinas.getMedicines();
        }
    }
    @RequestMapping(name = "/medicinas", method = RequestMethod.POST)
    public void addMedicine(@RequestBody Medicine med){
        medicinas.addMedicine(med);
    }

}