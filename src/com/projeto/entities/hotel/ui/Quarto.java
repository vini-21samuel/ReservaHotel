package com.projeto.entities.hotel.ui;

import com.projeto.entities.hotel.ui.TelaGerenciarQuartos;

public class Quarto {
    private String numero;
    private TelaGerenciarQuartos.TipoQuarto tipo;
    private String preco;
    private TelaGerenciarQuartos.StatusQuarto status;

    public Quarto(String numero, TelaGerenciarQuartos.TipoQuarto tipo, String preco, TelaGerenciarQuartos.StatusQuarto status) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.status = status;
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TelaGerenciarQuartos.TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TelaGerenciarQuartos.TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public TelaGerenciarQuartos.StatusQuarto getStatus() {
        return status;
    }

    public void setStatus(TelaGerenciarQuartos.StatusQuarto status) {
        this.status = status;
    }
}
