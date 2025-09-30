package com.senac.games.service;

import com.senac.games.config.SecurityConfiguration;
import com.senac.games.dto.request.UsuarioDTORequest;
import com.senac.games.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioDetailsImpl implements UserDetails {

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private UsuarioDTORequest usuarioDTORequest;
    private Usuario usuario;

    public UsuarioDetailsImpl(Usuario usuario){
        this.usuario = usuario;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.
                        getNome().
                        name())).
                        collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return usuario.getSenha(securityConfiguration.passwordEncoder().encode(usuarioDTORequest.getSenha()));
    }

    @Override
    public String getUsername() {
        return usuario.getLogin();
    }


    public Integer getIdUsuario(){
        return usuario.getId();
    }

    public String getNomeUsuario(){
        return getNomeUsuario();
    }


}
