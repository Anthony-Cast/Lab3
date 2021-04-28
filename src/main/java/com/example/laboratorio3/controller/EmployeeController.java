package com.example.laboratorio3.controller;

import com.example.laboratorio3.entity.Department;
import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.repository.DepartmentRepository;
import com.example.laboratorio3.repository.EmployeesRepository;
import com.example.laboratorio3.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value = {"", "/", "listar"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmpleados", employeesRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoEmployeeForm(Model model) {

       model.addAttribute("listaDepartamentos", departmentRepository.findAll());
       List<Department> departmentOpt = departmentRepository.findAll();
       List<Department> departamentosFinales = new ArrayList<Department>();
       for (Department i : departmentOpt){
           if(i.getEmployees() != null){
               departamentosFinales.add(i);
           }
       }
       model.addAttribute("listaTrabajos", jobRepository.findAll());
       model.addAttribute("listaDepartamentosconJefes", departamentosFinales);

       Employees empleadoUtil = new Employees();
       empleadoUtil.setHire_date(LocalDateTime.now());

        model.addAttribute("empleadoUtil", empleadoUtil);

       return "employee/newFrm";
    }

    @PostMapping("/guardar")
    public String guardarEmployee(Employees empleado, RedirectAttributes redirectAttributes) {
        System.out.println("Employee ID del empleado nuevo:");
        System.out.println(empleado.getEmployee_id());
        System.out.println("Employee ID del jefe del empleado nuevo:");
        Employees nuevo = empleado.getEmployees();
        System.out.println(nuevo.getEmployee_id());

        empleado.setEmployee_id(0);
        System.out.println("Employee ID del empleado nuevo:");
        System.out.println(empleado.getEmployee_id());
        System.out.println("Employee ID del jefe del empleado nuevo:");
        System.out.println(nuevo.getEmployee_id());

        if (empleado.getEmployee_id() == 0) {
            redirectAttributes.addFlashAttribute("msg", "Empleado creado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Empleado actualizado exitosamente");
        }
        System.out.println("SEGUNDO TRACE");
        //employeesRepository.save(empleado);
        return "redirect:/employees";
    }

    @GetMapping("/editar")
    public String editarEmployee(Model model, @RequestParam("id") int id) {
        Optional<Employees> employeesOptional = employeesRepository.findById(id);
        if (employeesOptional.isPresent()) {
            Employees employees = employeesOptional.get();
            model.addAttribute("employee", employees);
            model.addAttribute("listaDepartamentos", departmentRepository.findAll());
            List<Department> departmentOpt = departmentRepository.findAll();
            List<Department> departamentosFinales = new ArrayList<Department>();
            for (Department i : departmentOpt){
                if(i.getEmployees() != null){
                    departamentosFinales.add(i);
                }
            }
            model.addAttribute("listaTrabajos", jobRepository.findAll());
            model.addAttribute("listaDepartamentosconJefes", departamentosFinales);
            return "employee/editFrm";
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping("/buscar")
    public String searchEmpleado(@RequestParam("name") String name, Model model) {

        List<Employees> employeesOpt = employeesRepository.listarEmpleadosPorNombreApellidoDepartamentoPuestoCiudad(name);
        model.addAttribute("listaEmpleados",employeesOpt);
        return "employee/lista";
    }

    @GetMapping("/borrar")
    public String borrarEmpleado(Model model, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        Optional<Employees> employeesOptional = employeesRepository.findById(id);
        if (employeesOptional.isPresent()) {
            employeesRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("msg", "Empleado borrado exitosamente");
        }
            return "redirect:/employees";
    }

}
