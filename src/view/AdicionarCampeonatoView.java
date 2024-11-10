package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AdicionarCampeonatoView extends JDialog {
    public AdicionarCampeonatoView(HomeView view) {
        super(view, "Adicionar Campeonato", true);
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(50));

        // Título
        JLabel campeonatoLabel = new JLabel("Novo Campeonato");
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

        // Painel para o campo de Ano
        JComboBox<String> anoComboBox = new JComboBox<>();
        for (int i = 2000; i <= 2100; i++) {
            anoComboBox.addItem(String.valueOf(i));
        }
        anoComboBox.setPreferredSize(new Dimension(300, 40));
        anoComboBox.setMaximumSize(new Dimension(300, 40));
        anoComboBox.setMinimumSize(new Dimension(300, 40));
        anoComboBox.setSelectedItem(String.valueOf(2024));
        anoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(anoComboBox);

        panel.add(Box.createVerticalStrut(20));

        JButton salvarCampeonato = new JButton("Salvar");
        salvarCampeonato.setFont(new Font("Arial", Font.BOLD, 16));
        salvarCampeonato.setForeground(Color.black);
        salvarCampeonato.setPreferredSize(new Dimension(150, 40));
        salvarCampeonato.setMaximumSize(new Dimension(150, 40));
        salvarCampeonato.setMinimumSize(new Dimension(150, 40));
        salvarCampeonato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(salvarCampeonato);

        add(panel);

        SwingUtilities.invokeLater(anoComboBox::requestFocusInWindow);
    }

    private void adicionarPlaceholder(JTextField textField) {
        textField.setText("Digite o nome do campeonato...");
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Digite o nome do campeonato...")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText("Digite o nome do campeonato...");
                }
            }
        });
    }
}
