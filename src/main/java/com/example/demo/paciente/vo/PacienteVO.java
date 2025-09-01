package com.example.demo.paciente.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PACIENTE")
public class PacienteVO implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pac_id")
    private Long id;

    @Column(name = "pac_nome")
    private String nome;

    @Column(name = "pac_email")
    private String email;

    @Column(name = "pac_senha")
    private String senha;

    @Column(name = "pac_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pac_cpf")
    private String cpf;

    @Column(name = "pac_sexo")
    private String sexo;

    public PacienteVO(Long id, String nome, String email, String senha, LocalDate dataNascimento, String cpf, String sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
    }

    public PacienteVO(){}

    @PrePersist
    @PreUpdate
    private void upperCase(){
        if (nome != null)
            nome = nome.toUpperCase();

        if (email != null)
            email = email.toUpperCase();

        if (cpf != null)
            cpf = cpf.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
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
        if (!(o instanceof PacienteVO that)) return false;

        return id.equals(that.id) && nome.equals(that.nome) && email.equals(that.email) && senha.equals(that.senha) &&
                dataNascimento.equals(that.dataNascimento) && cpf.equals(that.cpf) && sexo.equals(that.sexo);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + dataNascimento.hashCode();
        result = 31 * result + cpf.hashCode();
        result = 31 * result + sexo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PacienteVO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", sexo='" + sexo +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
