package com.example.laboratorio3.repository;


import com.example.laboratorio3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query(value="select * from employees e inner join jobs j on j.job_id = e.job_id\n" +
            "inner join departments d on d.department_id = e.department_id\n" +
            "inner join locations l on l.location_id = d.location_id\n" +
            "where d.department_name like %?1% or e.first_name like %?1% or e.last_name like %?1%\n" +
            "or j.job_title like %?1% or l.city like %?1%",nativeQuery = true)
    List<Employees> listarEmpleadosPorNombreApellidoDepartamentoPuestoCiudad(String name);

}
