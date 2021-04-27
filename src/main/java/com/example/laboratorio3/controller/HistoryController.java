package com.example.laboratorio3.controller;

import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.entity.Job_History;
import com.example.laboratorio3.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistoryController {

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping(value = {"", "/", "historial"})
    public String verHistorial(Model model) {
        model.addAttribute("listaHistorial", historyRepository.findAll());
        return "history/historial";
    }

    @PostMapping("/buscar")
    public String searchEmpleado(@RequestParam("name") String name, Model model) {

        List<Job_History> job_historiesOpt = historyRepository.listarHistorialPorNombreApellidoPuestoDepartamentoFecha(name);
        model.addAttribute("listaHistorial",job_historiesOpt);
        return "history/historial";
    }
}
