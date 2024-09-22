package com.projeto.entities.hotel;

public class Quarto {
    private int numeroQuarto;
    private String tipoQuarto;
    private double precoDiaria;
    private String status;


    public Quarto(int numeroQuarto, String tipoQuarto, double precoDiaria, String status) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
        this.precoDiaria = precoDiaria;
        this.status = "Disponivel";
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }


    public void exibirInformacoesQuarto() {
        System.out.println("Número do Quarto: " + numeroQuarto);
        System.out.println("Tipo: " + tipoQuarto);
        System.out.println("Preço por Diária: " + precoDiaria);
        System.out.println("Status: " + status);
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
