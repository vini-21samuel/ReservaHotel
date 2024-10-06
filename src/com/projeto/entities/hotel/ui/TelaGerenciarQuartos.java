package com.projeto.entities.hotel.ui;

import com.projeto.DataBase.QuartosDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciarQuartos extends JFrame {

    private JTable tabelaQuartos;
    private DefaultTableModel modeloTabela;
    private QuartosDAO quartosDAO;

    // Enum para o status do quarto
    public enum StatusQuarto {
        LIVRE, OCUPADO
    }

    // Enum para os tipos de quarto
    public enum TipoQuarto {
        STANDARD, PREMIUM, SUITE
    }

    public TelaGerenciarQuartos() {
        // Instancia o QuartosDAO
        quartosDAO = new QuartosDAO();

        // Configurações da janela
        setTitle("Gerenciar Quartos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Criar o painel de cima com filtros, botões e o botão "Voltar"
        JPanel painelTop = new JPanel();
        painelTop.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Filtro de status
        JLabel labelFiltro = new JLabel("Filtrar por Status:");
        painelTop.add(labelFiltro);

        JComboBox<StatusQuarto> filtroStatus = new JComboBox<>(StatusQuarto.values());
        painelTop.add(filtroStatus);

        // Botão para filtrar
        JButton botaoFiltrar = new JButton("Filtrar");
        painelTop.add(botaoFiltrar);

        // Botão para atualizar status
        JButton botaoAtualizar = new JButton("Atualizar Status");
        painelTop.add(botaoAtualizar);

        // **Botão Voltar** no lado direito
        JButton botaoVoltar = new JButton(new ImageIcon(getClass().getResource("/com/projeto/entities/hotel/ui/imagens2/voltar.png")));
        painelTop.add(botaoVoltar);

        // Adicionar funcionalidade ao botão "Voltar"
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retorna para a Tela Principal
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                dispose(); // Fecha a tela atual
            }
        });

        add(painelTop, BorderLayout.NORTH);

        // Painel central com a tabela de quartos
        String[] colunas = {"Número", "Tipo", "Status", "Preço"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaQuartos = new JTable(modeloTabela);

        // Renderizador para colorir as células com base no status
        tabelaQuartos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 2).toString();
                if (status.equals("LIVRE")) {
                    cell.setBackground(Color.GREEN);
                } else if (status.equals("OCUPADO")) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(Color.WHITE);
                }
                return cell;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabelaQuartos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de baixo para adicionar/editar quartos
        JPanel painelBottom = new JPanel();
        painelBottom.setLayout(new GridLayout(5, 2, 10, 10));

        painelBottom.add(new JLabel("Número do Quarto:"));
        JTextField campoNumero = new JTextField();
        painelBottom.add(campoNumero);

        painelBottom.add(new JLabel("Tipo do Quarto:"));
        JComboBox<TipoQuarto> campoTipo = new JComboBox<>(TipoQuarto.values());
        painelBottom.add(campoTipo);

        painelBottom.add(new JLabel("Preço:"));
        JTextField campoPreco = new JTextField();
        painelBottom.add(campoPreco);

        painelBottom.add(new JLabel("Status:"));
        JComboBox<StatusQuarto> campoStatus = new JComboBox<>(StatusQuarto.values());
        painelBottom.add(campoStatus);

        JButton botaoAdicionar = new JButton("Adicionar/Atualizar Quarto");
        painelBottom.add(botaoAdicionar);

        add(painelBottom, BorderLayout.SOUTH);

        // Evento do botão de filtrar
        botaoFiltrar.addActionListener(e -> filtrarQuartos(filtroStatus.getSelectedItem().toString()));

        // Evento do botão de adicionar
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = campoNumero.getText();
                TipoQuarto tipo = (TipoQuarto) campoTipo.getSelectedItem();
                String preco = campoPreco.getText();
                StatusQuarto status = (StatusQuarto) campoStatus.getSelectedItem();

                adicionarOuAtualizarQuarto(numero, tipo, status, preco);
                limparCampos(campoNumero, campoPreco, campoStatus);
            }
        });

        // Evento do botão de atualizar status
        botaoAtualizar.addActionListener(e -> atualizarStatusQuartos());
    }

    // Método para adicionar ou atualizar quarto na tabela e no banco de dados
    private void adicionarOuAtualizarQuarto(String numero, TipoQuarto tipo, StatusQuarto status, String preco) {
        boolean quartoExistente = false;

        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            if (modeloTabela.getValueAt(i, 0).equals(numero)) {
                modeloTabela.setValueAt(tipo, i, 1);
                modeloTabela.setValueAt(status, i, 2);
                modeloTabela.setValueAt(preco, i, 3);
                quartoExistente = true;
                break;
            }
        }

        if (!quartoExistente) {
            modeloTabela.addRow(new Object[]{numero, tipo, status, preco});
            quartosDAO.inserirQuarto(numero, tipo.name(), status.name(), preco); // Inserir no banco
        }
    }

    private void filtrarQuartos(String statusFiltrar) {
        DefaultTableModel modeloFiltrado = new DefaultTableModel(new String[]{"Número", "Tipo", "Status", "Preço"}, 0);

        // Adiciona as linhas que correspondem ao status filtrado
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            if (modeloTabela.getValueAt(i, 2).toString().equals(statusFiltrar)) {
                modeloFiltrado.addRow(new Object[]{
                        modeloTabela.getValueAt(i, 0),
                        modeloTabela.getValueAt(i, 1),
                        modeloTabela.getValueAt(i, 2),
                        modeloTabela.getValueAt(i, 3)
                });
            }
        }

        // Atualiza a tabela com o modelo filtrado

    }



    // Método para limpar campos
    private void limparCampos(JTextField campoNumero, JTextField campoPreco, JComboBox<StatusQuarto> campoStatus) {
        campoNumero.setText("");
        campoPreco.setText("");
        campoStatus.setSelectedIndex(0);
    }

    // Método para atualizar status dos quartos (a ser implementado)
    private void atualizarStatusQuartos() {
        int linhaSelecionada = tabelaQuartos.getSelectedRow();
        if (linhaSelecionada != -1) {
            String numero = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
            String novoStatus = JOptionPane.showInputDialog(this, "Novo Status (LIVRE/OCUPADO):");

            if (novoStatus != null && (novoStatus.equals("LIVRE") || novoStatus.equals("OCUPADO"))) {
                modeloTabela.setValueAt(novoStatus, linhaSelecionada, 2); // Atualiza o status na tabela
                quartosDAO.atualizarStatusQuarto(numero, novoStatus); // Chama o método no DAO
            } else {
                JOptionPane.showMessageDialog(this, "Status inválido. Por favor, insira LIVRE ou OCUPADO.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um quarto na tabela.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaGerenciarQuartos tela = new TelaGerenciarQuartos();
            tela.setVisible(true);
        });
    }
}
