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

        // Primeira caixa de texto
        JTextField campo1 = new JTextField("Digite o Time 1...");
        campo1.setFont(new Font("Arial", Font.PLAIN, 16));
        campo1.setForeground(Color.GRAY);
        campo1.setBounds(130, 150, 200, 30);
        adicionarPlaceholder(campo1, "Digite o Time 1...");
        layeredPane.add(campo1, Integer.valueOf(1));

        // Segunda caixa de texto
        JTextField campo2 = new JTextField("Digite o Time 2...");
        campo2.setFont(new Font("Arial", Font.PLAIN, 16));
        campo2.setForeground(Color.GRAY);
        campo2.setBounds(340, 150, 200, 30);
        adicionarPlaceholder(campo2, "Digite o Time 2...");
        layeredPane.add(campo2, Integer.valueOf(1));
        
        JTextField campo3 = new JTextField("Digite o Estadio...");
        campo3.setFont(new Font("Arial", Font.PLAIN, 16));
        campo3.setForeground(Color.GRAY);
        campo3.setBounds(130, 200, 410, 30);
        adicionarPlaceholder(campo3, "Digite o Estadio...");
        layeredPane.add(campo3, Integer.valueOf(1));

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
                mostrarDataHora(campo1.getText(), campo2.getText());
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

    private void mostrarDataHora(String time1, String time2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((java.util.Date) dataSpinner.getValue());
        int hora = (Integer) horaSpinner.getValue();
        calendar.set(Calendar.HOUR_OF_DAY, hora);

        JOptionPane.showMessageDialog(this, String.format("Jogo agendado:\nTime 1: %s\nTime 2: %s\nData: %1$td/%1$tm/%1$tY %1$tH:%1$tM", time1, time2, calendar));
    }

    public static void main(String[] args) {
        CriarJogoView criarJogoView = new CriarJogoView();
        criarJogoView.setVisible(true);
    }
}