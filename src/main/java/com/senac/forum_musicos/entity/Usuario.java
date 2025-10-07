package com.senac.forum_musicos.entity;

import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="usuario")
public class Usuario {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public Set<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(Set<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public Set<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(Set<Topico> topicos) {
        this.topicos = topicos;
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

    public Set<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Set<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public Set<Participa> getParticipa() {
        return participa;
    }

    public void setParticipa(Set<Participa> participa) {
        this.participa = participa;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuario_id")
    private int id;

    @Column(name = "usuario_nome")
    private String nome;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "usuario_email")
    private String email;
    @Column(name = "usuario_senha_hash")
    private String senha;
    @Column(name= "usuario_bio")
    private String bio;

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    @Column(name = "usuario_foto")
    private File foto;

    @Column(name= "usuario_datacriacao")
    private LocalDateTime data;
    @Column(name="usuario_status")
    private int status;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;


    @OneToMany(mappedBy = "usuario")
    public Set<Instrumento> instrumentos;

    @OneToMany(mappedBy = "usuario")
    public Set<Topico> topicos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    public Set<Post> posts;

    @OneToMany(mappedBy = "usuario")
    public Set<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    public Set<Curtida> curtidas;

    @OneToMany(mappedBy = "usuario")
    public Set<Participa> participa;




}
