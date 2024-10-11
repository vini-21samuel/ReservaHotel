package app;

import com.projeto.entities.hotel.Classes.Hospede;
import com.projeto.entities.hotel.Classes.Quarto;
import com.projeto.entities.hotel.Classes.Reserva;

import java.util.Date;


public class Main {
    public static void main(String[] args) {
        // Criando um hóspede
        Hospede hospede = new Hospede("Vinícius", 21, "123456789", "vinicius23@gmail.com", "987654321   ", new Date(), 1);
        // Criando um quarto
        Quarto quarto = new Quarto(7, "Standard", 150.0, "Disponível"); // Quarto 101 com diária de R$ 150

        // Definindo as datas de entrada e saída
        Date dataEntrada = new Date();
        Date dataSaida = new Date(dataEntrada.getTime() + (2 * 24 * 60 * 60 * 1000));

        // Criando uma reserva
        Reserva reserva = new Reserva(1, hospede, quarto, dataEntrada, dataSaida);

        // Exibindo informações da reserva
        reserva.exibirInformacoes();
    }
}
