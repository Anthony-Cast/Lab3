package com.example.laboratorio3.dto;

import java.time.LocalDateTime;

public interface DetalleEmpleados {
    String getNombre();
    String getApellido();
    LocalDateTime getFechainicio();
    LocalDateTime getFechafin();
    String getPuesto();
}
