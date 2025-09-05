package com.senac.games.dto;

import com.senac.games.entity.Jogo;
import jakarta.transaction.Transactional;

public class JogoDTO {
    private Integer id;
    private String nome;
    private int status;
    private int categoriaId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }


}
