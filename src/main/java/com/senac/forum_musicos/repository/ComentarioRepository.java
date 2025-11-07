package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Comentario;
import com.senac.forum_musicos.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Comentario p SET p.status = -1 where p.id = :id")
    void apagarComentario(@Param(("id")) Integer comentarioId);

    @Query("SELECT p FROM Comentario p where p.status >= 0")
    List<Comentario> listarComentarios();
    @Query("SELECT p FROM Comentario p where p.status >= 0 AND p.id = :id")
    Comentario listarComentarioPorId(@Param("id") Integer comentarioId);

    @Query("DELETE FROM Comentario c")
    void deletarTodosComentarios();

}
