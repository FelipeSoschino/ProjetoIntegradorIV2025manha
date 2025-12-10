package com.senac.forum_musicos.DTO.request;

import java.util.List;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private String bio;
    private List<String> roleList;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
