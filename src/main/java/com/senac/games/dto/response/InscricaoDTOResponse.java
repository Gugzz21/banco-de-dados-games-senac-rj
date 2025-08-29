package com.senac.games.dto.response;

import java.time.LocalDateTime;

public class InscricaoDTOResponse {
    private int id;

    private LocalDateTime data;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
