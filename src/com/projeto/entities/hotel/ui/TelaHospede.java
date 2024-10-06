package com.projeto.entities.hotel.ui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaHospede extends JFrame {
    private ArrayList<Hospede> hospedes; // Lista para armazenar hóspedes
    private DefaultListModel<String> listaHospedesModel; // Modelo para a lista de hóspedes
    private JList<String> listaHospedes; // Lista visual para hóspedes

    public TelaHospede() {
        this.hospedes = new ArrayList<>(); // Inicializa a lista de hóspedes
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao clicar em "X"
    }

    private void initComponents() {
        // Painel principal com background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens/fundo.jpg"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Painel de ações no topo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinhamento à esquerda
        topPanel.setOpaque(false); // Transparente

        // Botão de voltar
        JButton btnVoltar = new JButton("Voltar", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/voltar.png")));
        btnVoltar.setPreferredSize(new Dimension(100, 50)); // Tamanho do botão

        // Botões de funcionalidades
        JButton btnAdicionarHospede = new JButton("Adicionar Hóspede", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/adicionarHospede.png")));
        JButton btnEditarHospede = new JButton("Editar Hóspede", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/editaHosp.png")));
        JButton btnRemoverHospede = new JButton("Remover Hóspede", new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/removerHospede.png")));

        // Adicionando os botões ao painel superior
        topPanel.add(btnVoltar);
        topPanel.add(Box.createHorizontalStrut(20)); // Espaçamento
        topPanel.add(btnAdicionarHospede);
        topPanel.add(Box.createHorizontalStrut(20)); // Espaçamento
        topPanel.add(btnEditarHospede);
        topPanel.add(Box.createHorizontalStrut(20)); // Espaçamento
        topPanel.add(btnRemoverHospede);

        // Painel de lista de hóspedes
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridBagLayout());
        actionPanel.setOpaque(false); // Transparente

        // Modelo e lista para hóspedes
        listaHospedesModel = new DefaultListModel<>();
        listaHospedes = new JList<>(listaHospedesModel);
        listaHospedes.setVisibleRowCount(10);
        listaHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaHospedes.addListSelectionListener(e -> mostrarInformacoesHospede());

        JScrollPane scrollPane = new JScrollPane(listaHospedes);
        scrollPane.setPreferredSize(new Dimension(300, 250)); // Aumenta o tamanho da lista
        actionPanel.add(scrollPane);

        // Adiciona painéis ao mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(actionPanel, BorderLayout.CENTER);

        // Adiciona funcionalidade para os botões
        btnAdicionarHospede.addActionListener(e -> adicionarHospede());
        btnEditarHospede.addActionListener(e -> editarHospede());
        btnRemoverHospede.addActionListener(e -> removerHospede());
        btnVoltar.addActionListener(e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose(); // Fecha a tela atual
        });

        // Adiciona funcionalidade final
        getContentPane().add(mainPanel);
        pack();
    }

    private void mostrarInformacoesHospede() {
        int selectedIndex = listaHospedes.getSelectedIndex();
        if (selectedIndex != -1) {
            Hospede hospede = hospedes.get(selectedIndex);
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(0, 1)); // Layout vertical

            infoPanel.add(new JLabel("Nome: " + hospede.getNome()));
            infoPanel.add(new JLabel("Endereço: " + hospede.getEndereco()));
            infoPanel.add(new JLabel("Telefone: " + hospede.getTelefone()));
            infoPanel.add(new JLabel("Data de Nascimento: " + hospede.getDataNascimento()));

            JOptionPane.showMessageDialog(this, infoPanel, "Informações do Hóspede", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void adicionarHospede() {
        JPanel panel = new JPanel();
        JTextField txtNome = new JTextField(15);
        JTextField txtEndereco = new JTextField(15);
        JTextField txtTelefone = new JTextField(15);
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);
        panel.add(new JLabel("Telefone:"));
        panel.add(txtTelefone);
        panel.add(new JLabel("Data de Nascimento:"));
        panel.add(dateChooser);

        int option = JOptionPane.showConfirmDialog(this, panel, "Adicionar Hóspede", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String nomeHospede = txtNome.getText();
            String endereco = txtEndereco.getText();
            String telefone = txtTelefone.getText();
            String dataNascimento = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

            if (nomeHospede != null && !nomeHospede.trim().isEmpty()) {
                // Cria um novo hóspede e adiciona à lista
                hospedes.add(new Hospede(nomeHospede, endereco, telefone, dataNascimento));
                listaHospedesModel.addElement(nomeHospede); // Adiciona o hóspede ao modelo da lista
            } else {
                JOptionPane.showMessageDialog(this, "Nome do hóspede não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarHospede() {
        int selectedIndex = listaHospedes.getSelectedIndex();
        if (selectedIndex != -1) {
            Hospede hospedeSelecionado = hospedes.get(selectedIndex);

            // Criar painel para editar as informações
            JPanel panel = new JPanel();
            JTextField txtNome = new JTextField(15);
            JTextField txtEndereco = new JTextField(15);
            JTextField txtTelefone = new JTextField(15);
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("dd/MM/yyyy");

            // Definir os valores atuais nos campos
            txtNome.setText(hospedeSelecionado.getNome());
            txtEndereco.setText(hospedeSelecionado.getEndereco());
            txtTelefone.setText(hospedeSelecionado.getTelefone());

            try {
                java.util.Date dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(hospedeSelecionado.getDataNascimento());
                dateChooser.setDate(dataAtual);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Adicionar os campos no painel
            panel.add(new JLabel("Nome:"));
            panel.add(txtNome);
            panel.add(new JLabel("Endereço:"));
            panel.add(txtEndereco);
            panel.add(new JLabel("Telefone:"));
            panel.add(txtTelefone);
            panel.add(new JLabel("Data de Nascimento:"));
            panel.add(dateChooser);

            // Mostrar o diálogo para edição
            int option = JOptionPane.showConfirmDialog(this, panel, "Editar Hóspede", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                // Atualizar os valores do hóspede
                String novoNome = txtNome.getText();
                String novoEndereco = txtEndereco.getText();
                String novoTelefone = txtTelefone.getText();
                String novaDataNascimento = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    // Atualiza as informações do hóspede
                    hospedeSelecionado.setNome(novoNome);
                    hospedeSelecionado.setEndereco(novoEndereco);
                    hospedeSelecionado.setTelefone(novoTelefone);
                    hospedeSelecionado.setDataNascimento(novaDataNascimento);

                    // Atualiza o modelo da lista de hóspedes
                    listaHospedesModel.set(selectedIndex, novoNome); // Atualiza o nome visível na lista
                } else {
                    JOptionPane.showMessageDialog(this, "Nome do hóspede não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um hóspede para editar!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerHospede() {
        int selectedIndex = listaHospedes.getSelectedIndex();
        if (selectedIndex != -1) {
            hospedes.remove(selectedIndex); // Remove o hóspede da lista
            listaHospedesModel.remove(selectedIndex); // Remove o hóspede do modelo da lista
            JOptionPane.showMessageDialog(this, "Hóspede removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um hóspede para remover!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaHospede tela = new TelaHospede();
            tela.setVisible(true);
        });
    }

    // Classe interna para representar um hóspede
    class Hospede {
        private String nome;
        private String endereco;
        private String telefone;
        private String dataNascimento;

        public Hospede(String nome, String endereco, String telefone, String dataNascimento) {
            this.nome = nome;
            this.endereco = endereco;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
    }
}
