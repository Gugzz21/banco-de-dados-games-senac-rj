package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name = "patrocinador", description = "API para o gerenciamento de patrocinador")
public class PatrocinadorController {

    private PatrocinadorService patrocinadorService;


    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar patrocinador", description = "Endpoint para listar todos os patrocinadores")
    public ResponseEntity<List<Patrocinador>> listarPatrocinadores() {

        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }

    @GetMapping("/listarPorPatrocinadorId/{patrocinadorId}")
    @Operation(summary = "Listar patrocinador pelo id de patrocinador", description = "Endpoint para patrocinador pela id de patrocinador")
    public ResponseEntity<Patrocinador> listarPorPatrocinadorId(
            @PathVariable("patrocinadorId") Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorService.obterPatrocinadorPeloId(patrocinadorId);
        if (patrocinador == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(patrocinador);
        }
    }
    @PostMapping("/criar")
    @Operation(summary = "Criar novo patrocinador", description = "Endpoint para criar um novo registro de patrocinador")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinador){
            return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorService.criarPatrocinador(patrocinador));
    }

    @PutMapping("/atualizarDados/{patrocinadorId}")
    @Operation(summary = "Atualizar todos os dados do patrocinador", description = "Endpoint para atualizar o registro do patrocinador")
    public ResponseEntity<PatrocinadorDTOResponse> atualizarPatrocinador(@PathVariable("patrocinadorId") Integer patrocinadorId,
                                                                         @RequestBody PatrocinadorDTORequest patrocinadorDTORequest){
        return ResponseEntity.ok(patrocinadorService.atualizarPatrocinador(patrocinadorId, patrocinadorDTORequest));
    }

    @PatchMapping("/atualizarStatus/{patrocinadorId}")
    @Operation(summary = "Atualizar campo status do patrocinador", description = "Endpoint para atualizar o status do patrocinador")
    public ResponseEntity<PatrocinadorDTOUpdateResponse> atualizarStatusPatrocinador(
            @Valid
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @RequestBody PatrocinadorDTORequest patrocinadorDTOUpdateRequest
    ){return ResponseEntity.ok(patrocinadorService.atualizarStatusPartrocinador(patrocinadorId, patrocinadorDTOUpdateRequest));
    }
    @DeleteMapping("/apagar/{patrocinadorId}")
    @Operation(summary = "Apagar registro de patrocinador", description = "Endpoint para apagar um patrocinador pelo Id")
    public ResponseEntity apagarPatrocinador(@PathVariable("patrocinadorId") Integer patrocinadorId){
        patrocinadorService.apagarPatrocinador(patrocinadorId);
        return ResponseEntity.noContent().build();
    }



}
