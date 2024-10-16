package com.projeto.entities.hotel.Classes;

public class Servico {
    private String nomeServico;
    private String descricao;
    private double precoServico;

    public Servico(){
    }
    public Servico(String nomeServico, String descricao, double precoServico) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.precoServico = precoServico;
    }

    public void exibirInformacoesServico() {
        System.out.println("Nome doServiço: " + nomeServico);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço: " + precoServico);
    }

    public double aplicarDesconto(double percentualDesconto) {
        return precoServico - (precoServico * percentualDesconto /100);
    }

}
