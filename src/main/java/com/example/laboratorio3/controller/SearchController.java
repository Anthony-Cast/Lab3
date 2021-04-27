package com.example.laboratorio3.controller;

import com.example.laboratorio3.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/busqueda")
public class SearchController {
    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping("")
    public String busquedas(){
        return "Search/indice";
    }
    @GetMapping("/detalle")
    public String detalleEmpleados(Model model){
        model.addAttribute("detalleEmpleados",employeesRepository.obtenerDetalle());
        return "Search/detalle";
    }
    @GetMapping("/detalle2")
    public String detalleEmpleados2(Model model){
        model.addAttribute("detalleEmpleados",employeesRepository.obtenerDetalle2());
        return "Search/detalle2";
    }
    @GetMapping("/detalle3")
    public String detalleEmpleados3(Model model){
        model.addAttribute("detalleEmpleados",employeesRepository.obtenerDetalle3());
        return "Search/detalle3";
    }
    @GetMapping("/detalle4")
    public String detalleEmpleados4(Model model){
        model.addAttribute("detalleEmpleados",employeesRepository.obtenerDetalle3());
        return "Search/detalle4";
    }
}
