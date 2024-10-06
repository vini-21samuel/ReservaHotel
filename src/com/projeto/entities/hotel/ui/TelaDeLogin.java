package com.projeto.entities.hotel.ui;

import com.projeto.DataBase.usuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private usuarioDAO usuarioDAO;

    public TelaDeLogin() {
        usuarioDAO = new usuarioDAO();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar a janela para abrir em tela cheia
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Adicionando o background
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo.jpg")));
        background.setLayout(new GridBagLayout());  // Usar GridBagLayout para centralizar o painel
        add(background);

        // Criando o contêiner branco
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setPreferredSize(new Dimension(350, 200));  // Definindo o tamanho do contêiner
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Adicionando espaçamento interno

        // Adicionando campos e botão ao contêiner
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));  // Definindo o tamanho do campo de email
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(300, 30));  // Definindo o tamanho do campo de senha
        senhaField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Entrar");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });

        // Adicionando componentes ao contêiner
        container.add(emailLabel);
        container.add(emailField);
        container.add(Box.createVerticalStrut(10));  // Espaço entre campos
        container.add(senhaLabel);
        container.add(senhaField);
        container.add(Box.createVerticalStrut(20));  // Espaço antes do botão
        container.add(loginButton);

        // Adicionando o contêiner ao background, centralizado
        background.add(container, new GridBagConstraints());
    }

    private void verificarLogin() {
        String email = emailField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();

        // Verificando as credenciais no banco de dados
        if (usuarioDAO.verificarLogin(email, senha)) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fechar a tela de login e abrir a tela principal
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            this.dispose();  // Fechar a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaDeLogin login = new TelaDeLogin();
            login.setVisible(true);
        });
    }
}
