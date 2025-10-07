package com.senac.forum_musicos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name ="topico")
public class Topico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topico_id")
    private int id;

    @Column(name="topico_titulo")
    private String titulo;

    @Column(name = "topico_descricao")
    private String descricao;

    @Column(name="topico_data")
    private LocalDateTime data;

    @Column(name="topico_status")
    private int status;

    @Column(name="topico_categoria")
    private int categoria;

    public int getIdUsuario() {
        return idUsuario = this.usuario.getId();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Transient
    @JsonProperty("idUsuairo")
    private int idUsuario;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "topico")
    public Set<Post> posts;

    @OneToMany(mappedBy = "topico")
    public Set<Comentario> comentarios;

    @OneToMany(mappedBy = "topico")
    public Set<Participa> participa;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Participa> getParticipa() {
        return participa;
    }

    public void setParticipa(Set<Participa> participa) {
        this.participa = participa;
    }


}
