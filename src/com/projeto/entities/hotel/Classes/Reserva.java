package com.projeto.entities.hotel.Classes;

import com.projeto.entities.hotel.Classes.Hospede;

import java.util.Date;

public class Reserva {
    private int codigoReserva;
    private Hospede hospede;
    private Quarto quarto;
    private Date dataEntrada;
    private Date dataSaida;
    private double valorTotal;

    public Reserva() {
    }

    public Reserva(int codigoReserva, Hospede hospede, Quarto quarto, Date dataEntrada, Date dataSaida) {
        this.codigoReserva = codigoReserva;
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        if (dataEntrada != null && dataSaida != null && quarto != null) {
            long dias = (dataSaida.getTime() - dataEntrada.getTime()) / (1000 * 60 * 60 * 24);
            this.valorTotal = dias * quarto.getPrecoDiaria();
        }
    }

    public void exibirInformacoes() {
        System.out.println("Código da Reserva: " + codigoReserva);
        hospede.exibirInformacoes();
        quarto.exibirInformacoesQuarto();
        System.out.println("Data de Entrada: " + dataEntrada);
        System.out.println("Data de Saída: " + dataSaida);
        System.out.println("Valor Total: " + valorTotal);
    }
}
