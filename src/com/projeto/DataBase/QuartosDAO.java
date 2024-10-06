package com.projeto.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuartosDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ReservaHotel";
    private static final String USER = "root";
    private static final String PASSWORD = "212003vini";

    // Método para buscar quartos
    public void buscarQuartos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM quartos")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String numero = rs.getString("numero");
                String tipo = rs.getString("tipo");
                String status = rs.getString("status");
                String preco = rs.getString("preco");
                System.out.println("Número: " + numero + ", Tipo: " + tipo + ", Status: " + status + ", Preço: " + preco);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um quarto
    public void inserirQuarto(String numero, String tipo, String status, String preco) {
        String sql = "INSERT INTO quartos (numero, tipo, status, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numero);
            pstmt.setString(2, tipo);
            pstmt.setString(3, status);
            pstmt.setString(4, preco);

            pstmt.executeUpdate();
            System.out.println("Quarto inserido com sucesso: " + numero);

        } catch (Exception e) {
            System.err.println("Erro ao inserir o quarto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        QuartosDAO quartosDAO = new QuartosDAO();
        quartosDAO.buscarQuartos();
    }

    public void atualizarStatusQuarto(String numero, String novoStatus) {
    }
}
