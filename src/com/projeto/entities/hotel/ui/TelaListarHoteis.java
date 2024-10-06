package com.projeto.entities.hotel.ui;

import com.projeto.DataBase.HoteisDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TelaListarHoteis extends JFrame {

    public TelaListarHoteis() {
        // Configurações da janela
        setTitle("Lista de Hotéis");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre em tela cheia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com BoxLayout para dispor os cards verticalmente
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adiciona os itens de hotel (exemplos personalizados)
        painelPrincipal.add(criarPainelHotel("Castelo dos Sonhos", "Um lugar mágico onde todos os sonhos se realizam", "R$ 599,00", "/com/projeto/entities/hotel/ui/imagens/hotel13.jpg", true, "20%"));
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre os cards
        painelPrincipal.add(criarPainelHotel("Paraíso Tropical", "Um paraíso cercado por natureza exuberante e paz", "R$ 750,00", "/com/projeto/entities/hotel/ui/imagens/hotel23.jpg", false, ""));
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre os cards
        painelPrincipal.add(criarPainelHotel("Hotel Montanha Encantada", "Perfeito para aventuras e contato com a natureza", "R$ 459,00", "/com/projeto/entities/hotel/ui/imagens/hotel33.jpg", true, "15%"));
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço entre os cards
        painelPrincipal.add(criarPainelHotel("Refúgio da Paz", "Relaxe e encontre tranquilidade neste refúgio de paz", "R$ 680,00", "/com/projeto/entities/hotel/ui/imagens/hotel4.PNG", false, ""));

        // Adiciona o painel principal à janela
        JScrollPane scrollPane = new JScrollPane(painelPrincipal); // Para rolar caso tenha muitos hotéis
        scrollPane.setPreferredSize(new Dimension(800, 600)); // Diminui o tamanho do painel
        add(scrollPane, BorderLayout.CENTER);

        // Adiciona o botão de voltar no canto esquerdo
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volta à tela anterior (substitua pela sua tela de origem)
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                dispose(); // Fecha a tela atual
            }
        });

        JPanel panelVoltar = new JPanel(); // Painel para o botão de voltar
        panelVoltar.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelVoltar.add(btnVoltar);
        add(panelVoltar, BorderLayout.NORTH); // Adiciona o botão ao topo da janela
    }

    // Método para criar cada painel de hotel
    private JPanel criarPainelHotel(String nome, String descricao, String preco, String imagemCaminho, boolean desconto, String descontoPercentual) {
        // Painel principal para o hotel
        JPanel painelHotel = new JPanel(new BorderLayout());
        painelHotel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        painelHotel.setPreferredSize(new Dimension(800, 150));  // Diminuir o tamanho preferencial do card

        // Painel para a imagem
        JLabel imagemLabel = new JLabel();
        imagemLabel.setPreferredSize(new Dimension(250, 150));  // Ajuste o tamanho da imagem

        // Verifica se a imagem existe
        URL imageURL = getClass().getResource(imagemCaminho);
        if (imageURL == null) {
            System.err.println("Imagem não encontrada: " + imagemCaminho);
        } else {
            ImageIcon hotelImage = new ImageIcon(imageURL);
            imagemLabel.setIcon(hotelImage);
        }

        // Painel esquerdo (dados do hotel)
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.add(new JLabel(nome));
        painelEsquerdo.add(Box.createRigidArea(new Dimension(0, 5)));
        painelEsquerdo.add(new JLabel(descricao));
        painelEsquerdo.add(Box.createRigidArea(new Dimension(0, 5)));
        JLabel ratingLabel = new JLabel("★ ★ ★ ★ ☆"); // Exemplo de rating
        ratingLabel.setForeground(new Color(255, 204, 0));  // Cor dourada para as estrelas
        painelEsquerdo.add(ratingLabel);

        // Painel direito (preço e botão)
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        JLabel precoLabel = new JLabel(preco);
        precoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        precoLabel.setForeground(Color.RED);
        painelDireito.add(precoLabel);

        if (desconto) {
            JLabel descontoLabel = new JLabel("-" + descontoPercentual);
            descontoLabel.setForeground(Color.GREEN);
            painelDireito.add(descontoLabel);
        }

        JButton reservarButton = new JButton("Reservar");
        reservarButton.setBackground(new Color(30, 144, 255));  // Azul para o botão de Reservar
        reservarButton.setForeground(Color.WHITE);
        reservarButton.setFocusPainted(false);
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaConfirmacao(nome, descricao, preco); // Passa descrição também
            }
        });

        painelDireito.add(Box.createRigidArea(new Dimension(0, 10)));  // Espaço entre preço e botão
        painelDireito.add(reservarButton);

        // Montar a estrutura final
        painelHotel.add(imagemLabel, BorderLayout.WEST);
        painelHotel.add(painelEsquerdo, BorderLayout.CENTER);
        painelHotel.add(painelDireito, BorderLayout.EAST);

        return painelHotel;
    }

    private void mostrarTelaConfirmacao(String nomeHotel, String descricaoHotel, String precoHotel) {
        // Nova tela de confirmação
        JFrame telaConfirmacao = new JFrame("Confirmação de Reserva");
        telaConfirmacao.setSize(300, 200);
        telaConfirmacao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaConfirmacao.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Você está prestes a reservar: " + nomeHotel));
        panel.add(new JLabel("Descrição: " + descricaoHotel)); // Adicione essa linha para mostrar a descrição
        panel.add(new JLabel("Preço: " + precoHotel));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Formata o preço removendo R$ e substituindo vírgula por ponto
                String precoFormatado = precoHotel.replace("R$ ", "").replace(",", ".");
                // Aqui você pode implementar a lógica para salvar a reserva no banco de dados
                HoteisDAO hoteisDAO = new HoteisDAO();
                hoteisDAO.inserirHotel(nomeHotel, descricaoHotel, precoFormatado);
                JOptionPane.showMessageDialog(null, "Reserva confirmada para: " + nomeHotel);
                telaConfirmacao.dispose(); // Fecha a tela de confirmação
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaConfirmacao.dispose(); // Fecha a tela de confirmação
            }
        });

        panel.add(btnConfirmar);
        panel.add(btnCancelar);

        telaConfirmacao.add(panel);
        telaConfirmacao.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaListarHoteis tela = new TelaListarHoteis();
            tela.setVisible(true);
        });
    }
}
