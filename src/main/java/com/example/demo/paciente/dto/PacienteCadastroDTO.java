package com.example.demo.paciente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class PacienteCadastroDTO {
    @JsonProperty("nome")
    @NotNull(message = "Campo 'nome' não informado no body, verifique!")
    private String nome;

    @JsonProperty("email")
    @Email
    @NotNull(message = "Campo 'email' não informado no body, verifique!")
    private String email;

    @JsonProperty("senha")
    @NotNull(message = "Campo 'senha' não informado no body, verifique!")
    private String senha;

    @JsonProperty("dataNascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Campo 'dataNascimento' não informado no body, verifique!")
    private String dataNascimento;

    @JsonProperty("cpf")
    @NotNull(message = "Campo 'cpf' não informado no body, verifique!")
    private String cpf;

    @JsonProperty("sexo")
    @NotNull(message = "Campo 'sexo' não informado no body, verifique!")
    private String sexo;

    public PacienteCadastroDTO(String cpf, String dataNascimento, String senha, String email, String nome, String sexo) {
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.sexo = sexo;
    }

    public PacienteCadastroDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof PacienteCadastroDTO that)) return false;

        return nome.equals(that.nome) && email.equals(that.email) && senha.equals(that.senha) &&
                dataNascimento.equals(that.dataNascimento) && cpf.equals(that.cpf) && sexo.equals(that.sexo);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + dataNascimento.hashCode();
        result = 31 * result + cpf.hashCode();
        result = 31 * result + sexo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PacienteCadastroDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
