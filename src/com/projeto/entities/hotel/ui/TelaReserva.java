package com.projeto.entities.hotel.ui;

import javax.swing.*;
import java.awt.*;

public class TelaReserva extends JFrame {

    public TelaReserva() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH); // Maximiza a tela
    }

    // Inicialização dos componentes
    private void initComponents() {
        // Painel principal com background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem de fundo
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo reserva.jpg"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Menu de funcionalidades da tela principal
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.NORTH);

        // Painel de reserva
        JPanel reservaPanel = new JPanel();
        reservaPanel.setLayout(new GridBagLayout());
        reservaPanel.setOpaque(false); // Deixa o painel transparente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Painel para os campos de reserva com fundo
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridBagLayout());
        fieldsPanel.setBackground(new Color(255, 255, 255, 200)); // Fundo branco com transparência
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem interna

        // Campos de data de check-in e check-out
        JLabel lblCheckIn = new JLabel("Check-In:");
        JTextField txtCheckIn = new JTextField("__/__/__", 20); // Aumentando o tamanho
        txtCheckIn.setBackground(Color.WHITE); // Fundo normal
        txtCheckIn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Bordas arredondadas

        JLabel lblCheckOut = new JLabel("Check-Out:");
        JTextField txtCheckOut = new JTextField("__/__/__", 20); // Aumentando o tamanho
        txtCheckOut.setBackground(Color.WHITE); // Fundo normal
        txtCheckOut.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Bordas arredondadas

        // Campos para quantidade de adultos e crianças
        JLabel lblAdultos = new JLabel("Adultos:");
        JTextField txtAdultos = new JTextField("0", 5);
        txtAdultos.setBackground(Color.WHITE); // Fundo normal
        txtAdultos.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Bordas arredondadas

        JLabel lblCriancas = new JLabel("Crianças:");
        JTextField txtCriancas = new JTextField("0", 5);
        txtCriancas.setBackground(Color.WHITE); // Fundo normal
        txtCriancas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true)); // Bordas arredondadas

        // Botão de Reservar
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setPreferredSize(new Dimension(100, 40));

        // Adicionando componentes ao painel de campos de reserva
        gbc.gridx = 0;
        gbc.gridy = 0;
        fieldsPanel.add(lblCheckIn, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(txtCheckIn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        fieldsPanel.add(lblCheckOut, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(txtCheckOut, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        fieldsPanel.add(lblAdultos, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(txtAdultos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        fieldsPanel.add(lblCriancas, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(txtCriancas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Ocupa duas colunas
        fieldsPanel.add(btnReservar, gbc);

        // Adiciona o painel de campos de reserva ao painel principal
        reservaPanel.add(fieldsPanel);
        mainPanel.add(reservaPanel, BorderLayout.CENTER);

        // Configura a janela
        getContentPane().add(mainPanel);
        pack();
    }

    // Método para criar o menu de funcionalidades
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)); // Ajustado para FlowLayout
        menuPanel.setOpaque(false); // Deixa o painel transparente

        // Botões do menu com ícones e largura aumentada
        JButton btnReservar = new JButton("Reservar", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/reservar.png")));
        JButton btnGerenciarHospedes = new JButton("Gerenciar Hóspedes", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarHospedes.png")));
        JButton btnGerenciarQuartos = new JButton("Gerenciar Quartos", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarQuartos.png")));
        JButton btnServicos = new JButton("Serviços", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/servicos.png")));

        // Estilizando os botões com largura ajustada
        Dimension buttonSize = new Dimension(220, 40); // Ajustando largura
        btnReservar.setPreferredSize(buttonSize);
        btnGerenciarHospedes.setPreferredSize(buttonSize);
        btnGerenciarQuartos.setPreferredSize(buttonSize);
        btnServicos.setPreferredSize(buttonSize);

        // Adiciona os botões ao painel de menu
        menuPanel.add(btnReservar);
        menuPanel.add(btnGerenciarHospedes);
        menuPanel.add(btnGerenciarQuartos);
        menuPanel.add(btnServicos);

        return menuPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaReserva tela = new TelaReserva();
            tela.setVisible(true);
        });
    }
}
