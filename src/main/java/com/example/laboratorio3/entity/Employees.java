package com.example.laboratorio3.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="employees")
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

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private BigDecimal salary;
    private  BigDecimal commission_pct;

    private Integer manager_id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private Integer enabled;

}
