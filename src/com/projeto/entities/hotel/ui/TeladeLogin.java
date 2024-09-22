package com.projeto.entities.hotel.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeladeLogin extends JFrame {
    private JLabel labelLogo;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoEnviar;

    public TeladeLogin() {
        // Configura a janela principal
        setTitle("Tela de Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel com fundo
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo.jpg"));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        painelFundo.setLayout(new GridBagLayout());
        setContentPane(painelFundo); // Define o painel como conteúdo da janela

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margem entre os componentes

        // Título
        JLabel titulo = new JLabel("Login", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Título ocupa duas colunas
        painelFundo.add(titulo, gbc);

        // Criação do JLabel para o logotipo e configuração da imagem
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/logoHotel2.png"));
        labelLogo = new JLabel(logoIcon);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiciona a logo ao layout
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Faz com que a logo ocupe duas colunas
        painelFundo.add(labelLogo, gbc);

        // Criação dos campos de e-mail e senha
        campoEmail = new JTextField(10); // Tamanho menor
        campoSenha = new JPasswordField(10); // Tamanho menor

        // Adiciona o campo de e-mail
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Campo de e-mail ocupa uma coluna
        painelFundo.add(new JLabel("E-mail:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFundo.add(campoEmail, gbc);

        // Adiciona o campo de senha
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFundo.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        painelFundo.add(campoSenha, gbc);

        // Criação do botão Enviar
        botaoEnviar = new JButton("Enviar");

        // Adiciona ação ao botão Enviar
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());
                JOptionPane.showMessageDialog(null, "E-mail: " + email + "\nSenha: " + senha, "Informações de Login", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Adiciona o botão Enviar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Botão ocupa duas colunas
        painelFundo.add(botaoEnviar, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TeladeLogin tela = new TeladeLogin();
            tela.setVisible(true);
        });
    }
}
