package com.example.laboratorio3.repository;

import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.entity.Job_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<Job_History,Integer> {

    @Query(value="select * from job_history j inner join employees e on e.employee_id = j.employee_id\n" +
            "inner join jobs o on o.job_id = j.job_id\n" +
            "inner join departments d on d.department_id = e.department_id\n" +
            "where d.department_name like %?1% or e.first_name like %?1% or e.last_name like %?1%\n" +
            "or o.job_title like %?1% or j.start_date like %?1%",nativeQuery = true)
    List<Job_History> listarHistorialPorNombreApellidoPuestoDepartamentoFecha(String name);
}
