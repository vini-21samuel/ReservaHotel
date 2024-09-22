package com.projeto.entities.hotel;

public class servico {
    private String nomeServico;
    private String descricao;
    private double precoServico;

    public servico(String nomeServico, String descricao, double precoServico) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.precoServico = precoServico;
    }

    public void exibirInformacoesServico() {
        System.out.println("Nome do Serviço: " + nomeServico);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço: " + precoServico);
    }

    public double aplicarDesconto(double percentualDesconto) {
        return precoServico - (precoServico * percentualDesconto / 100);
    }

}
