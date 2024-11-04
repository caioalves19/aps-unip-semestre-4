package view;

import controller.CampeonatoController;
import controller.ConfigCampeonatoController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConfigCampeonatoView extends JFrame {

    private DefaultListModel<String> timesModel;
    private JList<String> timesList;
    private JTextField caixaTextoNomeCampeonato;
    private ConfigCampeonatoController campeonatoController;
    private JComboBox<String> anoComboBox;

    public ConfigCampeonatoView(int idCampeonato, CampeonatoView campeonato) throws SQLException {
        campeonatoController = new ConfigCampeonatoController(this);

        setTitle("Configurações");
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        JLabel tituloLabel = new JLabel("Configurações", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // Inicialização do modelo e JList para os times
        timesModel = new DefaultListModel<>();
        timesList = new JList<>(timesModel);
        timesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(timesList);
        scrollPane.setBounds(130, 250, 300, 150);
        layeredPane.add(scrollPane, Integer.valueOf(1));

        JButton botaoAdicionarTime = new JButton("+");
        botaoAdicionarTime.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoAdicionarTime.setBounds(450, 258, 90, 28);
        botaoAdicionarTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Carregar a lista de times do banco de dados
                    ArrayList<String> todosOsTimes = campeonatoController.getAllTimesNaoParticipantes(idCampeonato); // Método que você precisa implementar no controlador

                    // Criar uma lista para exibição
                    String[] timesArray = todosOsTimes.toArray(new String[0]);

                    // Mostrar a lista de times em um JOptionPane
                    String timeSelecionado = (String) JOptionPane.showInputDialog(
                            null,
                            "Selecione um time:",
                            "Adicionar Time",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            timesArray,
                            timesArray[0]
                    );

                    // Adicionar o time selecionado ao modelo se não for nulo
                    if (timeSelecionado != null && !timeSelecionado.trim().isEmpty()) {
                        campeonatoController.addTimeToCampeonato(idCampeonato, timeSelecionado);
                        atualizarListaTimes(idCampeonato);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar os times: " + ex.getMessage());
                }
            }
        });


        JButton botaoRemoverTime = new JButton("-");
        botaoRemoverTime.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoRemoverTime.setBounds(450, 299, 90, 28);
        botaoRemoverTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = timesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    try {
                        campeonatoController.removeTimeFromCampeonato(idCampeonato, timesList.getSelectedValue());
                        atualizarListaTimes(idCampeonato);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um time para remover.");
                }
            }
        });

        JButton botaoSalvarInfoCampeonato = new JButton("Salvar");
        botaoSalvarInfoCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSalvarInfoCampeonato.setBounds(450, 150, 90, 28);
        botaoSalvarInfoCampeonato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeCampeonato = caixaTextoNomeCampeonato.getText();
                String anoCampeonato = anoComboBox.getItemAt(anoComboBox.getSelectedIndex());

                try {
                    campeonatoController.updateCampeonato(idCampeonato, nomeCampeonato, Integer.parseInt(anoCampeonato));
                    // Aqui você pode salvar os times também, se necessário
                    // Exemplo: campeonatoController.saveTimes(idCampeonato, timesModel.toArray());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                campeonato.atualizarCampeonato(nomeCampeonato);
            }
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoVoltar.setBounds(25, 20, 90, 28);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        layeredPane.add(botaoVoltar, Integer.valueOf(1));
        layeredPane.add(botaoSalvarInfoCampeonato, Integer.valueOf(1));
        layeredPane.add(botaoAdicionarTime, Integer.valueOf(1));
        layeredPane.add(botaoRemoverTime, Integer.valueOf(1));

        // Caixa de texto para o nome do campeonato
        caixaTextoNomeCampeonato = new JTextField("Digite o Campeonato...");
        caixaTextoNomeCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        caixaTextoNomeCampeonato.setForeground(Color.GRAY);
        caixaTextoNomeCampeonato.setBounds(130, 150, 300, 30);
        adicionarPlaceholder(caixaTextoNomeCampeonato, "Digite o Campeonato...");
        layeredPane.add(caixaTextoNomeCampeonato, Integer.valueOf(1));

        // ComboBox para anos
        String[] anos = new String[201];
        for (int i = 0; i < anos.length; i++) {
            anos[i] = String.valueOf(1900 + i);
        }
        anoComboBox = new JComboBox<>(anos);
        anoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        anoComboBox.setBounds(130, 200, 300, 30);
        layeredPane.add(anoComboBox, Integer.valueOf(1));

        // Carregar dados do campeonato
        String nomeCampeonato = campeonatoController.getCampeonatoByID(idCampeonato).getNome();
        atualizarNomeCampeonato(nomeCampeonato);
        String anoCampeonato = String.valueOf(campeonatoController.getCampeonatoByID(idCampeonato).getAno());
        anoComboBox.setSelectedItem(anoCampeonato);

        // Carregar times do banco de dados
        atualizarListaTimes(idCampeonato);

        add(layeredPane);
    }

    private void atualizarListaTimes(int idCampeonato) throws SQLException {
        timesModel.clear();
        ArrayList<String> timesParticipantes = campeonatoController.getTimesByCampeonatoID(idCampeonato);

        for (String time : timesParticipantes) {
            timesModel.addElement(time);
        }
    }

    private void adicionarPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void atualizarNomeCampeonato(String nome) {
        this.caixaTextoNomeCampeonato.setText(nome);
        this.caixaTextoNomeCampeonato.setForeground(Color.BLACK);
    }
}
