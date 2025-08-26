package com.example.demo.paciente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

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
    @NotNull(message = "Campo 'dataNascimento' não informado no body, verifique!")
    private Date dataNascimento;

    @JsonProperty("cpf")
    @NotNull(message = "Campo 'cpf' não informado no body, verifique!")
    private String cpf;

    public PacienteCadastroDTO(String cpf, Date dataNascimento, String senha, String email, String nome) {
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof PacienteCadastroDTO that)) return false;

        return nome.equals(that.nome) && email.equals(that.email) && senha.equals(that.senha) && dataNascimento.equals(that.dataNascimento) && cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + dataNascimento.hashCode();
        result = 31 * result + cpf.hashCode();
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
                '}';
    }
}
