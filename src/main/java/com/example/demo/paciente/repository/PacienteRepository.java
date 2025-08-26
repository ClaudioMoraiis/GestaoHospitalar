package com.example.demo.paciente.repository;

import com.example.demo.paciente.vo.PacienteVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteVO, Long> {
    PacienteVO findByEmail(String mEmail);
}
