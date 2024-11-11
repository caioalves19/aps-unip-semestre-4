package view;

import controller.CriarCampeonatoController;
import controller.CriarTimeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.Objects;

public class CriarTimeView extends JDialog{
    private final CriarTimeController controller;
    private final String placeholderText = "Digite o nome do time...";

    public CriarTimeView(HomeView homeView) {
        super(homeView, "Criar time", true);
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        controller = new CriarTimeController(homeView);

        // Painel principal com BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(50));

        // Título
        JLabel campeonatoLabel = new JLabel("Novo Time");
        campeonatoLabel.setFont(new Font("Arial", Font.BOLD, 32));
        campeonatoLabel.setForeground(Color.black);
        campeonatoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(campeonatoLabel);

        panel.add(Box.createVerticalStrut(100));

        JTextField nomeCampeonato = new JTextField();
        nomeCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeCampeonato.setForeground(Color.black);
        nomeCampeonato.setPreferredSize(new Dimension(300, 40));
        nomeCampeonato.setMaximumSize(new Dimension(300, 40));
        nomeCampeonato.setMinimumSize(new Dimension(300, 40));
        adicionarPlaceholder(nomeCampeonato);
        panel.add(nomeCampeonato);

        panel.add(Box.createVerticalStrut(20));

        JButton salvarTime = new JButton("Salvar");
        salvarTime.setFont(new Font("Arial", Font.BOLD, 16));
        salvarTime.setForeground(Color.black);
        salvarTime.setPreferredSize(new Dimension(150, 40));
        salvarTime.setMaximumSize(new Dimension(150, 40));
        salvarTime.setMinimumSize(new Dimension(150, 40));
        salvarTime.addActionListener(e -> {
            String nome = nomeCampeonato.getText();
            boolean campeonatoSalvo = false;

            if(!nome.isEmpty() && !nome.equals(placeholderText)) {
                try {
                    campeonatoSalvo = controller.adicionarTime(nome);
                } catch (SQLException ex) {
                    if (ex.getErrorCode() == 1062) {
                        // Tratamento específico para erro de duplicidade
                        JOptionPane.showMessageDialog(null, "Este time já existe no banco de dados");
                    } else {
                        // Tratamento para outros erros de SQL
                        JOptionPane.showMessageDialog(null, "Erro SQL: " + ex.getMessage());
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Informe o nome do time!");
            }

            if (campeonatoSalvo){
                JOptionPane.showMessageDialog(null, "Time cadastrado com sucesso!");
                dispose();
            }

        });
        panel.add(salvarTime);

        add(panel);
    }

    private void adicionarPlaceholder(JTextField textField) {
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
