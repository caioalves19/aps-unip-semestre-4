package view;

import controller.ConfigCampeonatoController;
import model.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConfigCampeonatoView extends JDialog {

    private final DefaultListModel<String> timesModel;
    private final JList<String> timesList;
    private final JTextField caixaTextoNomeCampeonato;
    private final ConfigCampeonatoController configCampeonatoController;
    private final JComboBox<String> anoComboBox;

    public ConfigCampeonatoView(int idCampeonato, CampeonatoView campeonatoView) throws SQLException {
        super(campeonatoView, "Configurações", true);
        configCampeonatoController = new ConfigCampeonatoController(this, campeonatoView);
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

        JLabel tituloTimes = new JLabel("Times participantes");
        tituloTimes.setFont(new Font("Arial", Font.BOLD, 12));
        tituloTimes.setForeground(Color.BLACK);
        tituloTimes.setBounds(130, 215, getWidth(), 50);
        layeredPane.add(tituloTimes, Integer.valueOf(1));

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
        botaoAdicionarTime.addActionListener(e -> {
            try {
                // Carregar a lista de times do banco de dados
                ArrayList<String> todosOsTimes = configCampeonatoController.getAllTimesNaoParticipantes(idCampeonato);

                // Exibir o diálogo de seleção com pesquisa
                String timeSelecionado = ListaTimesView.showDialog(this, todosOsTimes);

                // Adicionar o time selecionado ao modelo se não for nulo
                if (timeSelecionado != null && !timeSelecionado.trim().isEmpty()) {
                    configCampeonatoController.addTimeToCampeonato(idCampeonato, timeSelecionado);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar os times: " + ex.getMessage());
            }
        });


        JButton botaoRemoverTime = new JButton("-");
        botaoRemoverTime.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoRemoverTime.setBounds(450, 299, 90, 28);
        botaoRemoverTime.addActionListener(e -> {
            int selectedIndex = timesList.getSelectedIndex();
            if (selectedIndex != -1) {
                try {
                    configCampeonatoController.removeTimeFromCampeonato(idCampeonato, timesList.getSelectedValue());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um time para remover.");
            }
        });

        JButton botaoSalvarInfoCampeonato = new JButton("Salvar");
        botaoSalvarInfoCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSalvarInfoCampeonato.setBounds(450, 125, 90, 28);
        botaoSalvarInfoCampeonato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeCampeonato = caixaTextoNomeCampeonato.getText();
                String anoCampeonato = anoComboBox.getItemAt(anoComboBox.getSelectedIndex());

                try {
                    boolean resposta = configCampeonatoController.updateCampeonato(idCampeonato, nomeCampeonato, Integer.parseInt(anoCampeonato));
                    if (resposta) {
                        JOptionPane.showMessageDialog(null, "Campeonato atualizado com sucesso!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar campeonato: " + ex);
                }
            }
        });

        layeredPane.add(botaoSalvarInfoCampeonato, Integer.valueOf(1));
        layeredPane.add(botaoAdicionarTime, Integer.valueOf(1));
        layeredPane.add(botaoRemoverTime, Integer.valueOf(1));

        // Caixa de texto para o nome do campeonato
        caixaTextoNomeCampeonato = new JTextField("Digite o Campeonato...");
        caixaTextoNomeCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        caixaTextoNomeCampeonato.setForeground(Color.GRAY);
        caixaTextoNomeCampeonato.setBounds(130, 125, 300, 30);
        adicionarPlaceholder(caixaTextoNomeCampeonato, "Digite o Campeonato...");
        layeredPane.add(caixaTextoNomeCampeonato, Integer.valueOf(1));

        // ComboBox para anos
        String[] anos = new String[201];
        for (int i = 0; i < anos.length; i++) {
            anos[i] = String.valueOf(1900 + i);
        }
        anoComboBox = new JComboBox<>(anos);
        anoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        anoComboBox.setBounds(130, 160, 300, 30);
        layeredPane.add(anoComboBox, Integer.valueOf(1));

        // Carregar dados do campeonato
        String nomeCampeonato = configCampeonatoController.getCampeonatoByID(idCampeonato).getNome();
        caixaTextoNomeCampeonato.setText(nomeCampeonato);
        caixaTextoNomeCampeonato.setForeground(Color.BLACK);
        String anoCampeonato = String.valueOf(configCampeonatoController.getCampeonatoByID(idCampeonato).getAno());
        anoComboBox.setSelectedItem(anoCampeonato);

        // Carregar times do banco de dados
        configCampeonatoController.atualizarTimesParticipantes(idCampeonato);

        add(layeredPane);
    }

    public void atualizarListaTimes(ArrayList<Time> timesParticipantes) throws SQLException {
        timesModel.clear();

        for (Time time : timesParticipantes) {
            timesModel.addElement(time.getNome());
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
}
