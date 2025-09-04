package com.senac.games.controller;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Inscricao;
import com.senac.games.service.InscricaoService;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
public class InscricaoController {

    private InscricaoService inscricaoService;


    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar inscricao", description = "Endpoint para listar todos os inscricaos")
    public ResponseEntity<List<Inscricao>> listarInscricaos() {

        return ResponseEntity.ok(inscricaoService.listarInscricoes());
    }
    @GetMapping("/listarPorParticipantId/{inscricaoId}")
    @Operation(summary = "Listar inscricao pelo id de inscricao", description = "Endpoint para inscricao pela id de inscricao")
    public ResponseEntity<Inscricao> listarPorParticipantId(
            @PathVariable("inscricaoId") Integer inscricaoId) {
        Inscricao inscricao = inscricaoService.obterinscricaoPeloId(inscricaoId);
        if (inscricao == null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(inscricao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo inscricao", description = "Endpoint para criar um novo registro de inscricao")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(
            @Valid @RequestBody InscricaoDTORequest inscricao)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricao));
    }

    @PutMapping("/atualizarDados/{inscricaoId}")
    @Operation(summary = "Atualizar todos os dados do inscricao", description = "Endpoint para atualizar o registro do inscricao")
    public ResponseEntity<InscricaoDTOResponse> atualizarInscricao(
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoDTORequest inscricaoDTORequest
    ){
        return ResponseEntity.ok(inscricaoService.atualizarInscricao(inscricaoId, inscricaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{inscricaoId}")
    @Operation(summary = "Atualizar campo status do inscricao", description = "Endpoint para atualizar o status do inscricao")
    public ResponseEntity<InscricaoDTOUpdateResponse> atualizarStatusInscricao(
            @Valid
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoDTORequest inscricaoDTOUpdateRequest
    ){return ResponseEntity.ok(inscricaoService.atualizarStatusInscricao(inscricaoId, inscricaoDTOUpdateRequest));}


    @DeleteMapping("/apagar/{inscricaoId}")
    @Operation(summary = "Apagar registro de inscricao", description = "Endpoint para apagar um inscricao pelo Id")
    public ResponseEntity apagarInscricao(@PathVariable("inscricaoId") Integer inscricaoId){
        inscricaoService.apagarInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}
