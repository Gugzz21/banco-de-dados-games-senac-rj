package com.senac.games.controller;

import com.senac.games.dto.UsuarioDTO;
import com.senac.games.dto.request.UsuarioDTOLoginRequest;
import com.senac.games.dto.request.UsuarioDTORequest;
import com.senac.games.dto.response.UsuarioDTOLoginResponse;
import com.senac.games.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/usuario")
@Tag(name="Usuario", description = "API para gerenciamento de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOLoginResponse> login(@RequestBody UsuarioDTOLoginRequest usuarioDTOLoginRequest){
        return ResponseEntity.ok(usuarioService.login(usuarioDTOLoginRequest));
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioService.criar(usuarioDTORequest));
    }

}
