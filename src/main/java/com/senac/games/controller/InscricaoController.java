package com.senac.games.controller;

import com.senac.games.entity.Inscricao;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
public class InscricaoController {

    private InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar inscricoes", description =  "Endpoint para listar todas as inscricoes")
    public ResponseEntity<List<Inscricao>> listarInscricoes(){
        return ResponseEntity.ok(inscricaoService.listarInscricoes());
    }
}
