package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participante")
@Tag(name = "participante", description = "API para o gerenciamento de participante")
public class ParticipanteController {

    private ParticipanteService participanteService;


    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar participante", description = "Endpoint para listar todos os participantes")
    public ResponseEntity<List<Participante>> listarParticipantes() {

        return ResponseEntity.ok(participanteService.listarParticipantes());
    }
    @GetMapping("/listarPorParticipantId/{participanteId}")
    @Operation(summary = "Listar participante pelo id de participante", description = "Endpoint para participante pela id de participante")
    public ResponseEntity<Participante> listarPorParticipantId(@PathVariable("participanteId") Integer participanteId) {
        Participante participante = participanteService.listarPorParticipantId(participanteId);
        if (participante == null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(participante);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo participante", description = "Endpoint para criar um novo registro de participante")
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(@Valid @RequestBody ParticipanteDTORequest participante){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participante));
    }
}
