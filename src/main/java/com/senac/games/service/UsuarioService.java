package com.senac.games.service;

import com.senac.games.config.SecurityConfiguration;
import com.senac.games.dto.UsuarioDTO;
import com.senac.games.dto.request.UsuarioDTOLoginRequest;
import com.senac.games.dto.request.UsuarioDTORequest;
import com.senac.games.dto.response.UsuarioDTOLoginResponse;
import com.senac.games.entity.Role;
import com.senac.games.entity.RoleName;
import com.senac.games.entity.Usuario;
import com.senac.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;


    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }
    public UsuarioDTOLoginResponse login(UsuarioDTOLoginRequest usuarioDTOLoginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioDTOLoginRequest.getLogin(), usuarioDTOLoginRequest.getSenha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        UsuarioDTOLoginResponse usuarioDTOLoginResponse = new UsuarioDTOLoginResponse();
        usuarioDTOLoginResponse.setId(userDetails.getIdUsuario());
        usuarioDTOLoginResponse.setNome(userDetails.getNomeUsuario());
        usuarioDTOLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
        return usuarioDTOLoginResponse;

    }

    public UsuarioDTO criar(UsuarioDTORequest usuarioDTORequest){

        List<Role> roles = new ArrayList<Role>();
        for (int i=0; i<UsuarioDTORequest.getRoleList().size(); i++){
            Role role = new Role();
            role.setNome(RoleName.valueOf(UsuarioDTORequest.getRoleList().get(i).toString()));
            roles.add(role);

        }


        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTORequest.getNome());
        usuario.setCpf(usuarioDTORequest.getCpf());
        usuario.setDatanascimento(usuarioDTORequest.getDatanascimento());
        usuario.getLogin();
        usuario.getSenha(securityConfiguration.passwordEncoder().encode(usuarioDTORequest.getSenha()));
        usuario.setStatus(1);
        usuario.setRoles(usuarioDTORequest.getRoleList());
        Usuario usuariosave = usuarioRepository.save(usuario);


        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuariosave.getId());
        usuarioDTO.setNome(usuariosave.getNome());
        usuarioDTO.setDataNascimento(usuariosave.getDatanascimento());
        usuarioDTO.setCpf(usuariosave.getCpf());
        usuarioDTO.setLogin(usuariosave.getLogin());

        return usuarioDTO;

    }


}
