package com.senac.games.controller;
import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.service.PremioService;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name = "premio", description = "API para o gerenciamento de premio")
public class PremioController {
    private PremioService premioService;


    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar premio", description = "Endpoint para listar todos os premios")
    public ResponseEntity<List<Premio>> listarPremios() {

        return ResponseEntity.ok(premioService.listarPremios());
    }
    @GetMapping("/listarPorPremioId/{premioId}")
    @Operation(summary = "Listar participante pelo id de premio", description = "Endpoint para participante pela id de premio")
    public ResponseEntity<Premio> listarPorPremioId(
            @PathVariable("premioId") Integer premioId) {
        Premio premio = premioService.obterpremioPeloId(premioId);
        if (premio == null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(premio);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo premio", description = "Endpoint para criar um novo registro de premio")
    public ResponseEntity<PremioDTOResponse> criarPremio(
            @Valid @RequestBody PremioDTORequest premio)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }

    @PutMapping("/atualizarDados/{premioId}")
    @Operation(summary = "Atualizar todos os dados do premio", description = "Endpoint para atualizar o registro do premio")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(
            @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTORequest
    ){
        return ResponseEntity.ok(premioService.atualizarPremio(premioId, premioDTORequest));
    }
    @PatchMapping("/atualizarStatus/{premioId}")
    @Operation(summary = "Atualizar campo status do premio", description = "Endpoint para atualizar o status do premio")
    public ResponseEntity<PremioDTOUpdateResponse> atualizarStatusPremio(
            @Valid
            @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTOUpdateRequest
    ){return ResponseEntity.ok(premioService.atualizarStatusPremio(premioId, premioDTOUpdateRequest));}

    @DeleteMapping("/apagar/{premioId}")
    @Operation(summary = "Apagar registro de participante", description = "Endpoint para apagar um participante pelo Id")
    public ResponseEntity apagarPremio(@PathVariable("premioId") Integer premioId){
        premioService.apagarPremio(premioId);
        return ResponseEntity.noContent().build();
    }
}
