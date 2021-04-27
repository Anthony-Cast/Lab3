package com.example.laboratorio3.entity;

import javax.persistence.*;

@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String job_id;

    @Column(nullable = false)
    private String job_name;
    private int min_salary;
    private int max_salary;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public int getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    public int getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }
}