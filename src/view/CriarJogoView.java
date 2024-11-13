package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import controller.CriarJogoController;
import model.Jogo;

public class CriarJogoView extends JDialog {

    private JSpinner horaSpinner;
    private JSpinner dataSpinner;
    private JTextField estadioTextField;
    private JComboBox<String> timeMandanteComboBox;
    private JComboBox<String> timeVisitanteComboBox;
    private Jogo jogo;
    private final CriarJogoController criarJogoController;

    // Construtor para criar um novo jogo
    public CriarJogoView(ListaJogosView listaJogosView, int idCampeonato) throws SQLException {
        this(null, listaJogosView, idCampeonato);
    }

    // Construtor para editar um jogo existente
    public CriarJogoView(Jogo jogo, ListaJogosView listaJogosView, int idCampeonato) throws SQLException {
        super(listaJogosView, "Jogo", true);
        criarJogoController = new CriarJogoController(listaJogosView);
        this.jogo = jogo;
        setTitle(jogo == null ? "Criar Jogo" : "Editar Jogo");
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(705, 482));

        JLabel tituloLabel = new JLabel(jogo == null ? "Criar Novo Jogo" : "Editar Jogo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // ComboBox para Time 1
        List<String> timesParticipantes = criarJogoController.getTimesParticipantes(idCampeonato);
        timeMandanteComboBox = new JComboBox<>();
        for (String time : timesParticipantes) {
            timeMandanteComboBox.addItem(time);
        }
        timeMandanteComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        timeMandanteComboBox.setBounds(130, 150, 200, 30);
        layeredPane.add(timeMandanteComboBox, Integer.valueOf(1));

        // ComboBox para Time 2
        timeVisitanteComboBox = new JComboBox<>();
        for (String time : timesParticipantes) {
            timeVisitanteComboBox.addItem(time);
        }
        timeVisitanteComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        timeVisitanteComboBox.setBounds(340, 150, 200, 30);
        layeredPane.add(timeVisitanteComboBox, Integer.valueOf(1));

        if (jogo != null) {
            timeMandanteComboBox.setEnabled(false);
            timeVisitanteComboBox.setEnabled(false);
        }

        // Campo de texto para o Estádio com placeholder
        estadioTextField = new JTextField();
        estadioTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        estadioTextField.setBounds(130, 200, 410, 30);
        adicionarPlaceholder(estadioTextField, "Digite o Estádio...");
        layeredPane.add(estadioTextField, Integer.valueOf(1));

        // Spinner para a data
        SpinnerDateModel dataModel = new SpinnerDateModel();
        dataSpinner = new JSpinner(dataModel);
        JSpinner.DateEditor dataEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(dataEditor);
        dataSpinner.setBounds(130, 250, 200, 30);
        layeredPane.add(dataSpinner, Integer.valueOf(1));

        // Spinner para a hora
        horaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor horaEditor = new JSpinner.DateEditor(horaSpinner, "HH:mm"); // Define o editor para exibir horas e minutos
        horaSpinner.setEditor(horaEditor);
        horaSpinner.setBounds(340, 250, 200, 30);
        layeredPane.add(horaSpinner, Integer.valueOf(1));

        if (jogo != null) {
            JButton excluirButton = new JButton("Excluir");
            excluirButton.setFont(new Font("Arial", Font.PLAIN, 16));
            excluirButton.setBounds(350, 290, 90, 28);
            excluirButton.setBackground(Color.RED);
            excluirButton.setForeground(Color.WHITE);
            layeredPane.add(excluirButton, Integer.valueOf(1));

            excluirButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Tem certeza que deseja excluir o jogo?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            criarJogoController.excluirJogo(jogo);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                }
            });
            carregarDadosDoJogo();
        }

        // Botão para salvar o jogo
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        salvarButton.setBounds(450, 290, 90, 28);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeMandante = (String) timeMandanteComboBox.getSelectedItem();
                String timeVisitante = (String) timeVisitanteComboBox.getSelectedItem();
                String estadio = estadioTextField.getText();
                Date data = (Date) dataSpinner.getValue();
                Date hora = (Date) horaSpinner.getValue();

                if (jogo == null) {
                    if (!Objects.equals(timeMandante, timeVisitante)) {
                        try {
                            criarJogoController.criarJogo(idCampeonato, timeMandante, timeVisitante, estadio, data, hora);
                            JOptionPane.showMessageDialog(layeredPane, "Jogo criado com sucesso!");
                            dispose();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(layeredPane, "Erro: " + ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(layeredPane, "Os times não podem ser iguais");
                    }
                } else {
                    try {
                        criarJogoController.atualizarJogo(jogo, data, hora, estadio);
                        JOptionPane.showMessageDialog(layeredPane, "Jogo atualizado com sucesso!");
                        dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(layeredPane, "Erro: " + ex);
                    }
                }
            }
        });
        layeredPane.add(salvarButton, Integer.valueOf(1));

        add(layeredPane);
    }

    private void carregarDadosDoJogo() {
        List<Date> dataHora = criarJogoController.localDateTimeToDate(jogo.getDataJogo());
        timeMandanteComboBox.setSelectedItem(jogo.getTimeMandante().getNome());
        timeVisitanteComboBox.setSelectedItem(jogo.getTimeVisitante().getNome());
        estadioTextField.setText(jogo.getEstadio());
        estadioTextField.setForeground(Color.BLACK);
        dataSpinner.setValue(dataHora.getFirst());
        horaSpinner.setValue(dataHora.getLast());
    }

    // Método para adicionar placeholder
    private void adicionarPlaceholder(JTextField textField, String placeholderText) {
        textField.setText(placeholderText);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
    }
}
