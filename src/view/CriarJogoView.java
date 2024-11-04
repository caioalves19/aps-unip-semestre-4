package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CriarJogoView extends JFrame {

    private JSpinner horaSpinner;
    private JSpinner dataSpinner;

    public CriarJogoView() {
        setTitle("Criar Jogo");
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(705, 482));

        JLabel tituloLabel = new JLabel("Criar Novo Jogo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // ComboBox para Time 1
        JComboBox<String> time1ComboBox = new JComboBox<>(new String[]{"Selecione o Time 1", "Time A", "Time B", "Time C"});
        time1ComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        time1ComboBox.setBounds(130, 150, 200, 30);
        layeredPane.add(time1ComboBox, Integer.valueOf(1));

        // ComboBox para Time 2
        JComboBox<String> time2ComboBox = new JComboBox<>(new String[]{"Selecione o Time 2", "Time X", "Time Y", "Time Z"});
        time2ComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        time2ComboBox.setBounds(340, 150, 200, 30);
        layeredPane.add(time2ComboBox, Integer.valueOf(1));

        // Campo de texto para o Estádio
        JTextField estadioTextField = new JTextField("Digite o Estádio...");
        estadioTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        estadioTextField.setForeground(Color.GRAY);
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
        SpinnerNumberModel horaModel = new SpinnerNumberModel(0, 0, 23, 1); // horas de 0 a 23
        horaSpinner = new JSpinner(horaModel);
        horaSpinner.setBounds(340, 250, 200, 30);
        layeredPane.add(horaSpinner, Integer.valueOf(1));

        // Botão para agendar o jogo
        JButton agendarButton = new JButton("Salvar");
        agendarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        agendarButton.setBounds(450, 290, 90, 28);
        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time1 = (String) time1ComboBox.getSelectedItem();
                String time2 = (String) time2ComboBox.getSelectedItem();
                String estadio = estadioTextField.getText();
                mostrarDataHora(time1, time2, estadio);
            }
        });
        layeredPane.add(agendarButton, Integer.valueOf(1));

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        voltarButton.setBounds(25, 20, 90, 28);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaJogosView listaJogosView = new ListaJogosView();
                listaJogosView.setVisible(true);
                dispose(); // Fecha a janela atual
            }
        });
        layeredPane.add(voltarButton, Integer.valueOf(1));

        add(layeredPane);
    }

    private void adicionarPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void mostrarDataHora(String time1, String time2, String estadio) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((java.util.Date) dataSpinner.getValue());
        int hora = (Integer) horaSpinner.getValue();
        calendar.set(Calendar.HOUR_OF_DAY, hora);

        JOptionPane.showMessageDialog(this, String.format("Jogo agendado:\nTime 1: %s\nTime 2: %s\nEstádio: %s\nData: %1$td/%1$tm/%1$tY %1$tH:%1$tM", time1, time2, estadio, calendar));
    }

    public static void main(String[] args) {
        CriarJogoView criarJogoView = new CriarJogoView();
        criarJogoView.setVisible(true);
    }}