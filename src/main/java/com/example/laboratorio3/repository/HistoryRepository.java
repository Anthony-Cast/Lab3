package com.example.laboratorio3.repository;

import com.example.laboratorio3.entity.Job_History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<Job_History, Integer> {
}
