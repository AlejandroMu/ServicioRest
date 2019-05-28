package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private AtentionService atentionService;
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @GetMapping(value="/logout")
    public String logOut() {
        return "logout";
    }
    
    @GetMapping("/")
    public String opciones() {
        return "home";
    }

    @PostMapping(value = "/evaluar")
    public String evaluar(@RequestParam(value = "action", required = true) String action, RedirectAttributes red) {
        if (action.equals("Realizar Atención")) {
            UrgencyAtention atention = new UrgencyAtention();
            red.addFlashAttribute("atencion", atention);
            return "redirect:/atencion";
        } else if (action.equals("Medicamentos")) {
            Medicine medicine = new Medicine();
            red.addFlashAttribute("medicine", medicine);
            return "redirect:/crearMedicamento";
        } else if (action.equals("Listar Atenciones")) {
            ListUrgency lis = new ListUrgency();
            lis.setList(atentionService.getAtencions());
            red.addFlashAttribute("lis", lis);
            return "redirect:/listarAtenciones";

        } else if(action.equals("Listar Medicamentos")){
            ListaMedicine list = new ListaMedicine();
            list.setList(medicineService.getMedicines());
            red.addFlashAttribute("listaMedicina", list);
            return "redirect:/listarMedicamentos";
        }else if(action.equals("Cerrar sesion")){
            return "redirect:/logout";
        }      
        else{
            return "redirect:/crearInventario";
        }
    }

}
