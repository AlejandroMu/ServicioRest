package edu.icesi.clienterest.controller;
import java.util.*;

import edu.icesi.clienterest.Delegado.AtencionDelegado;
import edu.icesi.clienterest.Delegado.PacienteDelegado;
import edu.icesi.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerAtencion {
    @Autowired
    private AtencionDelegado atentionService;
    @Autowired
    private PacienteDelegado pacientService;

    @GetMapping("/listarAtenciones")
    public String listar(@ModelAttribute(name = "lis") ListUrgency lis, Model model) {
        model.addAttribute("lis", lis);
        return "atencion/listar";
    }

    @PostMapping(value = "/listarAtenciones")
    public String listar(@RequestParam(value = "action", required = true) String action,
            @ModelAttribute(name = "lis") ListUrgency lis, RedirectAttributes red) {
        if (action.equals("Filtrar")) {

            List<Atencion> filtro = atentionService.getAtencions(lis.getDate());
            lis.setList(filtro);
            red.addFlashAttribute("lis", lis);
            return "redirect:/listarAtenciones";
        }else if(action.equals("Agregar Atencion")){
            Atencion atention = new Atencion();
            red.addFlashAttribute("atencion", atention);
            return "redirect:/atencion";

        }
        return "redirect:/";
    }

    @GetMapping(value = "/atencion")
    public String crearAtencion(@ModelAttribute(value = "atencion") Atencion atencion, Model model) {
        if (atencion.getDateHour() == null) {
            atencion.setDateHour(new Date());
        }
        model.addAttribute("atencion", atencion);
        addPacients(model);
        return "atencion/crearAtencion";
    }

    @PostMapping("/atencion")
    public String saveAtencion(@RequestParam(value = "action", required = true) String action,
           @Validated @ModelAttribute(value = "atencion") Atencion a, BindingResult result, Model model,
            RedirectAttributes redirect) {

        if (action.equals("Guardar")) {
            if(!result.hasErrors()){
                try {
                    atentionService.addAtention(a);
                } catch (Exception e) {
                    model.addAttribute("error", e.getMessage());
                    addPacients(model);
                    return "atencion/crearAtencion";
    
                }
            }
            else{
                addPacients(model);
                return "atencion/crearAtencion";
            }
        } else if (action.equals("Añadir Suministro")) {
            redirect.addFlashAttribute("atencion", a);
            return "redirect:/suministro";
        }else if(action.equals("Cancelar")){
            return "redirect:/";
        }
         else {
            int index = Integer.parseInt(action);
            List<Supply> supplys = a.getSupplys();
            supplys.remove(index);
            redirect.addFlashAttribute("atencion", a);
            return "redirect:/atencion";
        }
        return "redirect:/";
    }

    public void addPacients(Model m) {
        m.addAttribute("pacients", pacientService.getPacients());
    }

}