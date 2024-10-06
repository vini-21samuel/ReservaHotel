package com.projeto.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoteisDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ReservaHotel";
    private static final String USER = "root";
    private static final String PASSWORD = "212003vini";

    // Método para buscar hotéis
    public void buscarHoteis() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM hoteis")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String preco = rs.getString("preco");
                System.out.println("Nome: " + nome + ", Descrição: " + descricao + ", Preço: " + preco);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um hotel
    public void inserirHotel(String nome, String descricao, String preco) {
        String sql = "INSERT INTO hoteis (nome, descricao, preco) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, descricao);
            pstmt.setString(3, preco);

            pstmt.executeUpdate();
            System.out.println("Hotel inserido com sucesso: " + nome);

        } catch (Exception e) {
            System.err.println("Erro ao inserir o hotel: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HoteisDAO hoteisDAO = new HoteisDAO();
        hoteisDAO.buscarHoteis();
    }
}
