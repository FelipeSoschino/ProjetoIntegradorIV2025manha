package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Topico;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Topico p SET p.status = -1 where p.id = :id")
    void apagarTopico(@Param("id") Integer topicoId);

    @Query("SELECT p FROM Topico p WHERE p.status >= 0")  // Adicionado LEFT JOIN FETCH para carregar posts
    List<Topico> listarTopicos();
    @Query("SELECT p FROM Topico p where p.status >= 0 AND p.id = :id")
    Topico listarTopicoPorId(@Param("id") Integer topicoId);

    @Query("SELECT p FROM Topico p where p.status >=0 and p.usuario.id = :usuarioId")
    List<Topico> listarTopicosUsuario(@Param("usuarioId")Integer usuarioId);
    @Query("DELETE FROM Topico t")
    void deletarTodosTopicos();
}
//    @Query("SELECT p FROM Topico p LEFT JOIN FETCH p.posts WHERE p.status >= 0")  // Adicionado LEFT JOIN FETCH para carregar posts
//    List<Topico> listarTopicos();