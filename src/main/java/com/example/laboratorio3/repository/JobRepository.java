package com.example.laboratorio3.repository;

import com.example.laboratorio3.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
