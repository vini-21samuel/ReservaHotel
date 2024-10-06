package com.projeto.entities.hotel.ui;

import com.projeto.DataBase.reservarDAO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

        // Campo de destino
        JLabel lblDestino = new JLabel("Destino:");
        String[] destinos = {"NE (Nordeste)", "Bahia", "Ceará", "Maranhão", "Paraíba", "Pernambuco", "Piauí", "Rio Grande do Norte", "Sergipe", "Alagoas"};
        JComboBox<String> comboDestino = new JComboBox<>(destinos);
        comboDestino.setPreferredSize(new Dimension(200, 30));

        // Campos de data de check-in e check-out usando JDateChooser
        JLabel lblCheckIn = new JLabel("Check-In:");
        JDateChooser checkInChooser = new JDateChooser();
        checkInChooser.setPreferredSize(new Dimension(200, 30));

        JLabel lblCheckOut = new JLabel("Check-Out:");
        JDateChooser checkOutChooser = new JDateChooser();
        checkOutChooser.setPreferredSize(new Dimension(200, 30));

        // Campos de quantidade de adultos, crianças e quartos
        JLabel lblAdultos = new JLabel("Quantidade de Adultos:");
        SpinnerModel adultosModel = new SpinnerNumberModel(1, 1, 10, 1); // Inicial: 1, Mínimo: 1, Máximo: 10, Incremento: 1
        JSpinner spinnerAdultos = new JSpinner(adultosModel);
        spinnerAdultos.setPreferredSize(new Dimension(200, 30));

        JLabel lblCriancas = new JLabel("Quantidade de Crianças:");
        SpinnerModel criancasModel = new SpinnerNumberModel(0, 0, 10, 1); // Inicial: 0, Mínimo: 0, Máximo: 10, Incremento: 1
        JSpinner spinnerCriancas = new JSpinner(criancasModel);
        spinnerCriancas.setPreferredSize(new Dimension(200, 30));

        JLabel lblQuartos = new JLabel("Quantidade de Quartos:");
        SpinnerModel quartosModel = new SpinnerNumberModel(1, 1, 5, 1); // Inicial: 1, Mínimo: 1, Máximo: 5, Incremento: 1
        JSpinner spinnerQuartos = new JSpinner(quartosModel);
        spinnerQuartos.setPreferredSize(new Dimension(200, 30));

        // Botão de pesquisar
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setPreferredSize(new Dimension(100, 40));
        btnPesquisar.setBackground(new Color(65, 105, 225)); // Azul royal

        // Adicionando funcionalidade ao botão Pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados da tela
                String destino = (String) comboDestino.getSelectedItem();
                Date checkIn = checkInChooser.getDate();
                Date checkOut = checkOutChooser.getDate();
                int adultos = (int) spinnerAdultos.getValue();
                int criancas = (int) spinnerCriancas.getValue();

                // Cria uma instância do DAO e realiza a reserva
                reservarDAO dao = new reservarDAO();
                dao.reservar(destino, checkIn, checkOut, adultos, criancas);

                // Abrir a tela de listagem de hotéis
                TelaListarHoteis telaListarHoteis = new TelaListarHoteis();
                telaListarHoteis.setVisible(true);
                dispose(); // Fecha a tela de reserva
            }
        });


        // Adicionando componentes ao painel de campos de reserva na ordem correta
        gbc.gridx = 0;
        gbc.gridy = 0;
        fieldsPanel.add(lblDestino, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(comboDestino, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        fieldsPanel.add(lblCheckIn, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(checkInChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        fieldsPanel.add(lblCheckOut, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(checkOutChooser, gbc);

        // Adicionando os campos de quantidade de adultos, crianças e quartos
        gbc.gridx = 0;
        gbc.gridy = 3;
        fieldsPanel.add(lblAdultos, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(spinnerAdultos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        fieldsPanel.add(lblCriancas, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(spinnerCriancas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        fieldsPanel.add(lblQuartos, gbc);
        gbc.gridx = 1;
        fieldsPanel.add(spinnerQuartos, gbc);

        // Adiciona o botão Pesquisar
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Ocupa duas colunas
        fieldsPanel.add(btnPesquisar, gbc);

        // Adiciona o painel de campos de reserva ao painel principal
        reservaPanel.add(fieldsPanel);
        mainPanel.add(reservaPanel, BorderLayout.CENTER);

        // Configura a janela
        getContentPane().add(mainPanel);
        pack();
    }

    // Método para criar o menu de funcionalidades
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)); // Centraliza os botões
        menuPanel.setOpaque(false); // Deixa o painel transparente

        // Botões do menu com ícones e largura ajustada
        JButton btnVoltar = new JButton(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/voltar.png")));
        JButton btnReservar = new JButton("Reservar", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/reservar.png")));
        JButton btnGerenciarHospedes = new JButton("Gerenciar Hóspedes", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarHospedes.png")));
        JButton btnGerenciarQuartos = new JButton("Gerenciar Quartos", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarQuartos.png")));

        // Estilizando os botões com largura ajustada
        Dimension buttonSize = new Dimension(220, 40); // Ajustando largura
        btnReservar.setPreferredSize(buttonSize);
        btnGerenciarHospedes.setPreferredSize(buttonSize);
        btnGerenciarQuartos.setPreferredSize(buttonSize);

        // Destacar botão Reservar
        btnReservar.setBackground(new Color(49, 78, 187)); // Azul royal

        // Adiciona funcionalidades aos botões
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                dispose(); // Fecha a tela de reserva
            }
        });

        btnGerenciarHospedes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para gerenciar hóspedes
                JOptionPane.showMessageDialog(null, "Gerenciar Hóspedes");
            }
        });

        btnGerenciarQuartos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para gerenciar quartos
                JOptionPane.showMessageDialog(null, "Gerenciar Quartos");
            }
        });

        // Adiciona os botões ao painel de menu
        menuPanel.add(btnReservar);
        menuPanel.add(btnGerenciarHospedes);
        menuPanel.add(btnGerenciarQuartos);

        // Adiciona o botão "Voltar" isolado à direita
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false); // Deixa o painel transparente
        buttonPanel.add(btnVoltar);

        menuPanel.add(buttonPanel); // Adiciona o painel do botão "Voltar" ao menu

        return menuPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaReserva telaReserva = new TelaReserva();
            telaReserva.setVisible(true);
        });
    }
}
