package com.projeto.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.projeto.entities.hotel.ui.Usuario;

public class usuarioDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ReservaHotel";
    private static final String USER = "root";
    private static final String PASS = "212003vini";

    // Método para cadastrar um novo usuário no banco de dados
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, data_nascimento, telefone, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(4, usuario.getTelefone());
            pstmt.setString(5, usuario.getSenha());

            // Convertendo a data de nascimento para o formato correto
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = sdf.parse(usuario.getDataNascimento());
            SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataNascimentoMySQL = mysqlDateFormat.format(dataNascimento);

            pstmt.setString(3, dataNascimentoMySQL); // Corrigido o índice para o campo da data de nascimento

            pstmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Erro ao parsear a data: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Capture outros erros que possam ocorrer
        }
    }

    // Método para verificar as credenciais de login no banco de dados
    public boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, senha);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next(); // Retorna true se um usuário for encontrado
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            return false;
        }
    }
}
