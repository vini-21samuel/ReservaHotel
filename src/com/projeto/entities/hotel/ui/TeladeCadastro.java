package com.projeto.entities.hotel.ui;

import com.projeto.DataBase.usuarioDAO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeladeCadastro extends JFrame {
    public static List<Usuario> usuarios = new ArrayList<>();
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JPasswordField confirmarSenhaField;
    private JTextField telefoneField;
    private JDateChooser dataNascimentoChooser;

    // DAO para gerenciar o cadastro no banco de dados
    private usuarioDAO usuarioDAO;

    public TeladeCadastro() {
        // Inicializando o DAO
        usuarioDAO = new usuarioDAO();

        setTitle("Cadastro de Usuário");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo.jpg")));
        background.setLayout(new BorderLayout());
        add(background);

        JPanel caixaPanel = new JPanel();
        caixaPanel.setLayout(new BoxLayout(caixaPanel, BoxLayout.Y_AXIS));
        caixaPanel.setOpaque(true);
        caixaPanel.setBackground(new Color(255, 255, 255, 200));
        caixaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        background.add(caixaPanel, BorderLayout.EAST);

        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/logoHotel2.png")));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        caixaPanel.add(logoLabel);

        JLabel tituloLabel = new JLabel("Cadastro de Usuário");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        caixaPanel.add(tituloLabel);

        nomeField = criarCampo("Nome Completo", caixaPanel);
        emailField = criarCampo("Email", caixaPanel);
        telefoneField = criarCampo("Telefone", caixaPanel);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento");
        dataNascimentoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dataNascimentoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        caixaPanel.add(dataNascimentoLabel);

        dataNascimentoChooser = new JDateChooser();
        dataNascimentoChooser.setDateFormatString("dd/MM/yyyy");
        dataNascimentoChooser.setMaximumSize(new Dimension(300, 30));
        dataNascimentoChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        dataNascimentoChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
        caixaPanel.add(dataNascimentoChooser);

        senhaField = criarCampoSenha("Senha", caixaPanel);
        confirmarSenhaField = criarCampoSenha("Confirmar Senha", caixaPanel);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCadastro();
            }
        });
        caixaPanel.add(Box.createVerticalStrut(10));
        caixaPanel.add(cadastrarButton);
    }

    private JTextField criarCampo(String label, JPanel panel) {
        JLabel campoLabel = new JLabel(label);
        campoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        campoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(campoLabel);

        JTextField textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(textField);

        return textField;
    }

    private JPasswordField criarCampoSenha(String label, JPanel panel) {
        JLabel campoLabel = new JLabel(label);
        campoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        campoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(campoLabel);

        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passwordField);

        return passwordField;
    }

    private void validarCadastro() {
        String nome = nomeField.getText().trim();
        String email = emailField.getText().trim();
        String telefone = telefoneField.getText().trim();
        Date dataNascimento = dataNascimentoChooser.getDate();
        String senha = new String(senhaField.getPassword()).trim();
        String confirmarSenha = new String(confirmarSenhaField.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || dataNascimento == null || senha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(dataNascimento);

        Usuario novoUsuario = new Usuario(nome, email, telefone, dataFormatada, senha);

        // Utilizando o DAO para cadastrar o usuário no banco de dados
        usuarioDAO.cadastrarUsuario(novoUsuario);

        // Adicionando o novo usuário à lista
        usuarios.add(novoUsuario);

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TeladeCadastro cadastro = new TeladeCadastro();
            cadastro.setVisible(true);
        });
    }
}
