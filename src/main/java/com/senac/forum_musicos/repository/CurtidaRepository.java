package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Comentario;
import com.senac.forum_musicos.entity.Curtida;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Curtida p SET p.status = -1 where p.id = :id")
    void apagarCurtida(@Param(("id")) Integer curtidaId);

    @Query("SELECT p FROM Curtida p where p.status >= 0")
    List<Curtida> listarCurtidas();
    @Query("SELECT p FROM Curtida p where p.status >= 0 AND p.id = :id")
    Curtida listarCurtidaPorId(@Param("id") Integer curtidaId);

    @Query("DELETE FROM Curtida c")
        void deletarTodasCurtidas();
}
