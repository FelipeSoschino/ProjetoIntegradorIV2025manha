package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Participa;
import com.senac.forum_musicos.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipaRepository extends JpaRepository<Participa, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Participa p SET p.status = -1 where p.id = :id")
    void apagarParticipa(@Param("id") Integer participaId);

    @Query("SELECT p FROM Participa p where p.status >= 0")
    List<Participa> listarParticipas();
    @Query("SELECT p FROM Participa p where p.status >= 0 AND p.id = :id")
    Participa listarParticipaPorId(@Param("id") Integer participaId);

    @Query("DELETE FROM Participa p")
    void deletarTodosParticipa();
}
