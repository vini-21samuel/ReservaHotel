package com.projeto.entities.hotel.ui;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private String dataNascimento;
    private String senha;

    public Usuario(String nome, String email, String telefone, String dataNascimento, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSenha() {
        return senha;
    }
}
