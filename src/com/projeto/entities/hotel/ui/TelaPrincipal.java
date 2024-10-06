package com.projeto.entities.hotel.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH); // Maximiza a tela
    }

    // Inicialização dos componentes
    private void initComponents() {
        // Painel principal com um background personalizado
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo.jpg"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Adiciona a logo e a mensagem de boas-vindas dentro de um contêiner
        JPanel centralPanel = createCentralPanel();
        mainPanel.add(centralPanel, BorderLayout.CENTER);

        // Menu de funcionalidades na parte superior
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.NORTH);

        // Adiciona o painel principal à janela
        getContentPane().add(mainPanel);
        pack();
    }

    private JPanel createCentralPanel() {
        // Criando um painel central com fundo semi-transparente
        JPanel centralPanel = new JPanel();
        centralPanel.setOpaque(false); // Deixa o painel transparente
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

        // Criando um contêiner branco e semi-transparente
        JPanel container = new JPanel();
        container.setBackground(new Color(255, 255, 255, 200)); // Cor branca com 200 de opacidade
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento interno
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Adiciona a logo do hotel
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/logoHotel2.png"))); // Substitua pelo caminho da sua logo
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(logoLabel);

        // Espaço entre logo e mensagem
        container.add(Box.createRigidArea(new Dimension(0, 30))); // Aumentei o espaço para descer a logo

        // Adiciona a mensagem de boas-vindas
        JLabel welcomeLabel = new JLabel("Bem-vindo ao Reserva de Hotéis Byteforest!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLACK); // Mudança de cor para contrastar com o fundo
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(welcomeLabel);

        // Adiciona um espaçamento superior para afastar do menu
        centralPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Aumente esse valor para mais espaço

        // Adiciona o contêiner ao painel central
        centralPanel.add(container);

        return centralPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        menuPanel.setOpaque(false); // Deixa o painel transparente

        // Botões do menu com ícones e largura ajustada
        JButton btnReservar = new JButton("Reservar", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/reservar.png")));
        JButton btnGerenciarHospedes = new JButton("Gerenciar Hóspedes", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarHospedes.png")));
        JButton btnGerenciarQuartos = new JButton("Gerenciar Quartos", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/gerenciarQuartos.png")));

        // Estilizando os botões com largura ajustada
        Dimension buttonSize = new Dimension(220, 40); // Ajustando largura
        btnReservar.setPreferredSize(buttonSize);
        btnGerenciarHospedes.setPreferredSize(buttonSize);
        btnGerenciarQuartos.setPreferredSize(buttonSize);

        // Adiciona funcionalidade ao botão "Reservar"
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de reserva ao clicar no botão
                TelaReserva telaReserva = new TelaReserva();
                telaReserva.setVisible(true);
                dispose(); // Fecha a tela principal
            }
        });

        // Adiciona funcionalidade ao botão "Gerenciar Hóspedes"
        btnGerenciarHospedes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de gerenciamento de hóspedes ao clicar no botão
                TelaHospede telaHospede = new TelaHospede();
                telaHospede.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a tela
                telaHospede.setVisible(true);
                dispose(); // Fecha a tela principal
            }
        });

        // Adiciona funcionalidade ao botão "Gerenciar Quartos"
        btnGerenciarQuartos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de gerenciamento de quartos ao clicar no botão
                TelaGerenciarQuartos telaGerenciarQuartos = new TelaGerenciarQuartos();
                telaGerenciarQuartos.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a tela
                telaGerenciarQuartos.setVisible(true);
                dispose(); // Fecha a tela principal
            }
        });

        // Adiciona os botões ao painel de menu
        menuPanel.add(btnReservar);
        menuPanel.add(btnGerenciarHospedes);
        menuPanel.add(btnGerenciarQuartos);

        return menuPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
