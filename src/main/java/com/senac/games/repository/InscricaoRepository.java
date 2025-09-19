package com.senac.games.repository;

import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Jogo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Inscricao i SET i.status = -1 WHERE i.id = :id")
    void apagadoLogicoInscricao(@Param("id") Integer inscricaoId);
    @Query("SELECT i FROM Inscricao i WHERE i.status >= 0")
    List<Inscricao> listarInscricoes();
    @Query("SELECT i FROM Inscricao i WHERE i.id = :id AND i.status >= 0")
    Inscricao obterInscricaoPeloId(@Param("id") Integer inscricaoId);

}
