package com.senac.forum_musicos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

import java.io.File;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="post")
public class Post {
    /*id, texto, arquivo, data, status*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer id;

    @Column(name="post_texto")
    private String texto;

    //    @Convert(converter = FileAttributeConverter.class)
    @Column(name="post_arquivo", nullable = true)
    private File arquivo;

    @Column(name="post_data_criacao")
    private LocalDateTime data;

    @Column(name="post_status")
    private int status;

    public int getIdUsuario() {
        return idUsuario = this.usuario.getId();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTopico() {
        if(this.topico == null){
        return null;}
        return idTopico = this.topico.getId();
    }

    public void setIdTopico(Integer idTopico) {
        this.idTopico = idTopico;
    }

    @Transient
    @JsonProperty("idUsuario")
    private int idUsuario;

    @Transient
    @JsonProperty("idTopico")
    private Integer idTopico;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "topico_id",nullable = true)
    private Topico topico;

    @OneToMany(mappedBy = "post")
    public Set<Curtida> curtidas;

    @OneToMany(mappedBy = "post")
    public Set<Comentario> comentarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Topico getTopico() {
        if(this.topico != null){
        return topico;}
        return null;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Set<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Set<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
