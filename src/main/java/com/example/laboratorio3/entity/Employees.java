package com.example.laboratorio3.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Employees {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    private String phone_number;
    @Column(nullable = false)
    private LocalDateTime hire_date;
    @Column(nullable = false)
    private String job_id;
    private BigDecimal salary;
    private  BigDecimal commission_pct;
    private Integer manager_id;
    private  Integer department_id;
    private Integer enabled;



}
