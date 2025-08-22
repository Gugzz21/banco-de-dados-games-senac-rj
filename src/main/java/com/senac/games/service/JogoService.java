package com.senac.games.service;

import com.senac.games.entity.Jogo;
import com.senac.games.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;

    }

    public List<Jogo> listarJogos() {
        return this.jogoRepository.findAll();
    }
}
