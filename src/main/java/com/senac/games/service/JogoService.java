package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.JogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    private final ModelMapper modelmapper;

    @Autowired
    public JogoService(JogoRepository jogoRepository, ModelMapper modelmapper) {
        this.jogoRepository = jogoRepository;
        this.modelmapper = modelmapper;
    }

    public List<Jogo> listarJogos() {
        return this.jogoRepository.listarJogos();
    }

    public Jogo obterjogoPeloId(Integer jogoId) {
        return this.jogoRepository.obterJogoPeloId(jogoId);
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = modelmapper.map(jogoDTORequest, Jogo.class);
        Jogo jogoSave = this.jogoRepository.save(jogo);
        JogoDTOResponse jogoDTOResponse = modelmapper.map(jogoSave, JogoDTOResponse.class);
        return jogoDTOResponse;
    }

    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {
        Jogo jogo = this.obterjogoPeloId(jogoId);
        if (jogo != null) {
            modelmapper.map(jogoDTORequest, jogo);
            Jogo tempResponse = jogoRepository.save(jogo);
            return modelmapper.map(tempResponse, JogoDTOResponse.class);
        } else {
            return null;
        }
    }

    public JogoDTOUpdateResponse atualizarStatusJogo(Integer jogoId, JogoDTORequest jogoDTOUpdateRequest) {
        Jogo jogo = this.obterjogoPeloId(jogoId);
        if (jogo != null) {
            jogo.setStatus(jogoDTOUpdateRequest.getStatus());

            Jogo tempReponse = jogoRepository.save(jogo);

            return modelmapper.map(tempReponse, JogoDTOUpdateResponse.class);
        } else {
            return null;
        }
    }

    public void apagarJogo(Integer jogoId) {
        jogoRepository.apagadoLogicoJogo(jogoId);
    }
}