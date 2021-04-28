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
    public String guardarEmployee(@RequestParam("manager_id") int manager,Employees employee, RedirectAttributes redirectAttributes) {
        Optional<Employees> emplo= employeesRepository.findById(manager);
        employee.setEmployees(emplo.get());
        if (employee.getEmployee_id() == 0) {
            redirectAttributes.addFlashAttribute("msg1", "Empleado creado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("msg2", "Empleado actualizado exitosamente");
        }
        employeesRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/editar")
    public String editarEmployee(Model model, @RequestParam("id") int id, RedirectAttributes redirectAttributes ) {
        Optional<Employees> employeesOptional = employeesRepository.findById(id);
        if (employeesOptional.isPresent()) {
            Employees employees = employeesOptional.get();
            Employees emplo = new Employees();
            if((emplo = employees.getEmployees()) == null){
                redirectAttributes.addFlashAttribute("nohayjefe", "No puedes editar a este usuario. MANAGER_ID = NULL");
                return "redirect:/employees";
            }
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
            Employees employees = employeesOptional.get();
            Employees emplo = new Employees();
            if((emplo = employees.getEmployees()) == null){
                redirectAttributes.addFlashAttribute("nohayjefe", "No puedes borrar al jefe");
                return "redirect:/employees";
            }
            employeesRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("msg3", "Empleado borrado exitosamente");
        }
            return "redirect:/employees";
    }

}
