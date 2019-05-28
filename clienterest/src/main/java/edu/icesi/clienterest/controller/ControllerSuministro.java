package com.example.demo.controller;

import java.util.*;

import edu.icesi.model.*;
import com.example.demo.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControllerSuministro{

    @Autowired
    private MedicineService medicineService;
    
    @GetMapping(value = "/suministro")
    public String agregarSuministro(@ModelAttribute(value = "atencion") UrgencyAtention atencion,Model m) {
         Supply supl = new Supply();
       m.addAttribute("supply", supl);
        addMedicine(m);
        m.addAttribute("atencion", atencion);
        
        return "Suministro/suministro.html";
    }

    @PostMapping(value = "/suministro")
    public String saveSupply(@RequestParam(value = "action",required = true) String action, RedirectAttributes redirect,@ModelAttribute(value = "atencion") UrgencyAtention atencion,
    @Validated @ModelAttribute(name = "supply") Supply s,BindingResult result) {
        if(action.equals("Cancelar")){
            redirect.addFlashAttribute("atencion",atencion);
            return "redirect:/atencion";
        }
        if(result.hasErrors()){
            return "Suministro/suministro.html";
        }
        List<Supply> supplies=atencion.getSupplys();
        if(supplies==null){
            supplies=new ArrayList<Supply>();
        }
        supplies.add(s);
        atencion.setSupplys(supplies);
        redirect.addFlashAttribute("atencion",atencion);
        return "redirect:/atencion";
    }

    @GetMapping(value="/d")
    public String getMethodName(@RequestParam(name = "supply") Supply med) {

        return new String();
    }
    

    public void addMedicine(Model m){
    	List<Medicine> medi=medicineService.getMedicines();
        m.addAttribute("medicines", medi);
    }

}