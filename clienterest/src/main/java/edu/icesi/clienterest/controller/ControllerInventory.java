package edu.icesi.clienterest.controller;

import edu.icesi.clienterest.Delegado.InventoryDelegado;
import edu.icesi.clienterest.Delegado.MedicineDelegado;
import edu.icesi.clienterest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerInventory {
    @Autowired
    private InventoryDelegado inventories;
    @Autowired
    private MedicineDelegado medicines;

    @GetMapping(value = "/crearInventario")
    public String crearInventario(Model model) {
        MedicineInventory inv = new MedicineInventory();
        addMedicines(model);
        model.addAttribute("inventario", inv);
        return "medicamentos/crearInventario";
    }

    private void addMedicines(Model model) {
        model.addAttribute("medicines", medicines.getMedicines());
    }

    @PostMapping(value = "/crearInventario")
    public String crearInventario(@RequestParam(value = "action", required = true) String action,
            @Validated @ModelAttribute MedicineInventory inventario, BindingResult res, Model model) {
        if (action.equals("Cancelar")) {
            return "redirect:/";
        }
        if (res.hasErrors()) {
            addMedicines(model);
            model.addAttribute("inventario", inventario);
            return "medicamentos/crearInventario";
        }
        inventories.addInventory(inventario);
        return "redirect:/";
    }

}