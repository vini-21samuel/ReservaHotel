package com.projeto.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reservarDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ReservaHotel";
    private static final String USER = "root";
    private static final String PASS = "212003vini";

    public void reservar(String destino, Date checkIn, Date checkOut, int adultos, int criancas) {
        String sql = "INSERT INTO reservas (destino, check_in, check_out, adultos, criancas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, destino);

            // Formatando as datas para o formato padrão do MySQL (yyyy-MM-dd)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(2, sdf.format(checkIn));
            pstmt.setString(3, sdf.format(checkOut));

            pstmt.setInt(4, adultos);
            pstmt.setInt(5, criancas);

            pstmt.executeUpdate();
            System.out.println("Reserva realizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
