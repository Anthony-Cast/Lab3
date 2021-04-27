package com.example.laboratorio3.controller;


import com.example.laboratorio3.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/historial")
public class HistoryController {
    @Autowired
    HistoryRepository historyRepository;
    @GetMapping(value = {"", "/"})
    public String listaHistorial(Model model){
        model.addAttribute("listaHistorial", historyRepository.findAll());
        return "employee/historial";
    }

}
