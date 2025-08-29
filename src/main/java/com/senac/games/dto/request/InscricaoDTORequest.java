package com.senac.games.dto.request;

import java.time.LocalDateTime;

public class InscricaoDTORequest {
    private LocalDateTime data;

    private int status;

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
