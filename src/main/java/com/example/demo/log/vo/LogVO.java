package com.example.demo.log.vo;

import jakarta.persistence.*;
import com.example.demo.paciente.vo.PacienteVO;

@Entity
@Table(name = "LOG")
public class LogVO {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_processo")
    private String processo;

    @ManyToOne
    @JoinColumn(name = "log_paciente_id", referencedColumnName = "pac_id", nullable = false)
    private PacienteVO paciente;

    public LogVO(Long id, String processo, PacienteVO paciente) {
        this.id = id;
        this.processo = processo;
        this.paciente = paciente;
    }

    public LogVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public PacienteVO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteVO paciente) {
        this.paciente = paciente;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof LogVO logVO)) return false;

        return id.equals(logVO.id) && processo.equals(logVO.processo) && paciente.equals(logVO.paciente);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + processo.hashCode();
        result = 31 * result + paciente.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "id=" + id +
                ", processo='" + processo + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}
