package com.example.demo.restController;

import java.util.List;

import com.example.demo.service.AtentionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.icesi.model.Atencion;

/**
 * AtencionControler
 */
@RestController
public class AtencionControler {
    @Autowired
    private AtentionService atenciones;

    @RequestMapping(name = "/atenciones", method = RequestMethod.GET)
    public List<Atencion> getAtenciones() {
        return atenciones.getAtencions();
    }

    @RequestMapping(name = "/atenciones", method = RequestMethod.POST)
    public void addAtencion(@RequestBody Atencion atencion) {
        try {
            atenciones.addAtention(atencion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}