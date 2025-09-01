package com.example.demo.paciente.controller;

import jakarta.validation.Valid;
import com.example.demo.paciente.dto.PacienteCadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.paciente.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteRota {
    @Autowired
    private PacienteService fService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody PacienteCadastroDTO mDTO){
        return fService.cadastrar(mDTO);
    }
}
