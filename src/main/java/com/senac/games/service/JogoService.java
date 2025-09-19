package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private final ModelMapper modelmapper;

    @Autowired
    public JogoService(JogoRepository jogoRepository, ModelMapper modelmapper, CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.jogoRepository = jogoRepository;
        this.modelmapper = modelmapper;
    }

    public List<Jogo> listarJogos() {
        return this.jogoRepository.listarJogos();
    }

    public Jogo obterjogoPeloId(Integer jogoId) {
        return this.jogoRepository.obterJogoPeloId(jogoId);
    }

    @Transactional
    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = modelmapper.map(jogoDTORequest, Jogo.class);
        jogo.setId(null);

        Categoria categoria = categoriaRepository.findById(jogoDTORequest.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + jogoDTORequest.getIdCategoria()));

        jogo.setCategoria(categoria);

        Jogo jogoSalvo = this.jogoRepository.save(jogo);

        return modelmapper.map(jogoSalvo, JogoDTOResponse.class);
    }

    @Transactional
    public JogoDTOResponse atualizarJogo(Integer id, JogoDTORequest jogoDTORequest) {

        Jogo jogoExistente = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo com id: " + id + " não encontrado."));


        jogoExistente.setNome(jogoDTORequest.getNome());
        jogoExistente.setStatus(jogoDTORequest.getStatus());


        if (jogoDTORequest.getIdCategoria() != null && jogoDTORequest.getIdCategoria() != 0) {
            Categoria categoria = categoriaRepository.findById(jogoDTORequest.getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + jogoDTORequest.getIdCategoria()));
            jogoExistente.setCategoria(categoria);
        }


        Jogo jogoAtualizado = jogoRepository.save(jogoExistente);


        return modelmapper.map(jogoAtualizado, JogoDTOResponse.class);
    }
    public JogoDTOUpdateResponse atualizarStatusJogo(Integer jogoId, JogoDTORequest jogoDTOUpdateRequest) {
        Jogo jogo = this.obterjogoPeloId(jogoId);
        if (jogo != null) {
            jogo.setStatus(jogoDTOUpdateRequest.getStatus());
            Jogo tempResponse = jogoRepository.save(jogo);
            return modelmapper.map(tempResponse, JogoDTOUpdateResponse.class);
        }
        return null;
    }

    public void apagarJogo(Integer jogoId) {
        jogoRepository.apagadoLogicoJogo(jogoId);
    }
}