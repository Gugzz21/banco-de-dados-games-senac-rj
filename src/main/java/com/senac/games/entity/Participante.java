package com.senac.games.entity;

import jakarta.persistence.*;

import javax.swing.*;
import java.io.File;
import java.util.Set;

@Entity
@Table(name="participante")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participante_id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "participante_nome")
    private String nome;

    @Column(name = "participante_email")
    private String email;

    @Column(name = "participante_identificacao")
    private String identificacao;

    @Column(name = "participante_endereco")
    private String endereco;

    public File getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(File foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    @Column(name = "participante_foto_perfil")
    private File foto_perfil;
    @Column(name = "participante_status")
    private int status;

    public Set<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    @OneToMany(mappedBy = "participante")
    private Set<Inscricao> inscricoes;
}
