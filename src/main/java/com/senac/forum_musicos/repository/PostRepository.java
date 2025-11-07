package com.senac.forum_musicos.repository;

import com.senac.forum_musicos.entity.Post;
import com.senac.forum_musicos.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.status = -1 where p.id = :id")
    void apagarPost(@Param("id") Integer postId);

    @Query("SELECT p FROM Post p WHERE p.status >= 0")
    List<Post> listarPosts();
    @Query("SELECT p FROM Post p where p.status >= 0 AND p.id = :id")
    Post listarPostPorId(@Param("id") Integer postId);

    @Query("SELECT p FROM Post p where p.status >=0 and p.usuario.id = :usuarioId")
    List<Post> listarPostsUsuadio(@Param("usuarioId")Integer usuarioId);
    @Query("DELETE FROM Post p")
    void deletarTodosPosts();
}

////    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.topico WHERE p.status >= 0")
//    List<Post> listarPosts();