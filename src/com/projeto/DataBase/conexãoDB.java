package com.projeto.hotel.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexãodb {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ReservaHotel";
        String user = "root";
        String password = "212003vini";

        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Conectado ao banco de dados com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
