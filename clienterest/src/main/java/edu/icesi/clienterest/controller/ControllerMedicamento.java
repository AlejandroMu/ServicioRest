package edu.icesi.clienterest.controller;
import java.util.*;

import edu.icesi.clienterest.Delegado.InventoryDelegado;
import edu.icesi.clienterest.Delegado.MedicineDelegado;
import edu.icesi.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerMedicamento {
    @Autowired
    private MedicineDelegado medicineService;
    @Autowired
    private InventoryDelegado inventory;

    @GetMapping(value = "/crearMedicamento")
    public String crearMedicamento(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicamento", medicine);
        return "medicamentos/crear";
    }

    @PostMapping(value = "/crearMedicamento")
    public String crearMedicamento(@RequestParam(value = "action") String action,
            @Validated @ModelAttribute(value = "medicamento") Medicine medicine, BindingResult result) {
        if (action.equals("Cancelar")) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "medicamentos/crear";
        } else {
            medicineService.addMedicine(medicine);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/listarMedicamentos")
    public String listarMedicamentos(@ModelAttribute(value = "listaMedicina") ListaMedicine list, Model model) {
        model.addAttribute("listaMedicina", list);
        model.addAttribute("inventarios", inventory);
        return "medicamentos/listar";
    }

    @PostMapping(value = "/filtrarM")
    public String listarMedicamentos(@RequestParam(value = "action", required = true) String action,
            @ModelAttribute ListaMedicine list, RedirectAttributes red) {
        if (action.equals("Filtrar")) {
            List<Medicine> lista = medicineService.filtrar(list.getDate());
            list.setList(lista);
            red.addFlashAttribute("listaMedicina", list);
            return "redirect:/listarMedicamentos";
        } else if (action.equals("Agregar Medicina")) {
            return "redirect:/crearMedicamento";

        } else if (action.equals("Agregar Inventario")) {
            return "redirect:/crearInventario";
        }
        return "redirect:/";
    }

}