package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Instrumento;
import com.senac.forum_musicos.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Instrumento p SET p.status = -1 where p.id = :id")
    void apagarInstrumento(@Param(("id")) Integer instrumentoId);

    @Query("SELECT p FROM Instrumento p where p.status >= 0")
    List<Instrumento> listarInstrumentos();
    @Query("SELECT p FROM Instrumento p where p.status >= 0 AND p.id = :id")
    Instrumento listarInstrumentoPorId(@Param("id") Integer instrumentoId);

    @Query("SELECT p FROM Instrumento p where p.status >=0 AND p.usuario.id = :usuarioId")
    List<Instrumento> listarInstrumentosUsuario(@Param("usuarioId") Integer usuarioId);
    @Query("DELETE FROM Instrumento i")
    void deletarTodosInstrumentos();

}
