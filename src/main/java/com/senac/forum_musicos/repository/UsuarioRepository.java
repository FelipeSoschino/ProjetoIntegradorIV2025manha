package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Usuario p SET p.status = -1 where p.id = :id")
    void apagarUsuario(@Param("id") Integer id);

    @Query("SELECT p FROM Usuario p where p.status >= 0")
    List<Usuario> listarUsuarios();
    @Query("SELECT p FROM Usuario p where p.status >= 0 AND p.id = :id")
    Usuario listarUsuarioPorId(@Param("id") Integer id);
    @Query("DELETE FROM Usuario u")
    void deletarTodosUsuarios();

    Optional<Usuario> findByEmail(String email);

}
