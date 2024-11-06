package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarCampeonatoView extends JFrame {
    public AdicionarCampeonatoView() {
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(Box.createVerticalStrut(50));


        // Título
        JLabel campeonatoLabel = new JLabel("Novo Campeonato", SwingConstants.CENTER);
        campeonatoLabel.setFont(new Font("Arial", Font.BOLD, 32));
        campeonatoLabel.setForeground(Color.black);
        campeonatoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(campeonatoLabel);

        panel.add(Box.createVerticalStrut(100));

        // Painel para o campo de Nome
        JPanel panelNome = new JPanel();
        panelNome.setLayout(new BoxLayout(panelNome, BoxLayout.X_AXIS));
        panelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nomeCampeonato  = new JLabel("Nome: ");
        nomeCampeonato.setHorizontalAlignment(SwingConstants.RIGHT);
        nomeCampeonato.setFont(new Font("Arial", Font.BOLD, 16));
        nomeCampeonato.setForeground(Color.black);
        nomeCampeonato.setPreferredSize(new Dimension(50, 40));
        nomeCampeonato.setMaximumSize(new Dimension(60, 40));
        nomeCampeonato.setMinimumSize(new Dimension(60, 40));
        JTextField nomeCampo = new JTextField();
        nomeCampo.setToolTipText("Digite o nome do campeonato");
        nomeCampo.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeCampo.setForeground(Color.black);
        nomeCampo.setPreferredSize(new Dimension(300, 40));
        nomeCampo.setMaximumSize(new Dimension(300, 40));
        nomeCampo.setMinimumSize(new Dimension(300, 40));
        panelNome.add(nomeCampeonato);
        panelNome.add(nomeCampo);
        panel.add(panelNome);

        panel.add(Box.createVerticalStrut(20));

        // Painel para o campo de Ano
        JPanel panelAno = new JPanel();
        panelAno.setLayout(new BoxLayout(panelAno, BoxLayout.X_AXIS));
        panelAno.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel anoCampeonato  = new JLabel("Ano: ");
        anoCampeonato.setHorizontalAlignment(SwingConstants.RIGHT);
        anoCampeonato.setFont(new Font("Arial", Font.BOLD, 16));
        anoCampeonato.setForeground(Color.black);
        anoCampeonato.setPreferredSize(new Dimension(60, 40));
        anoCampeonato.setMaximumSize(new Dimension(60, 40));
        anoCampeonato.setMinimumSize(new Dimension(60, 40));
        JComboBox<String> anoComboBox = new JComboBox<>();
        for (int i = 2000; i <= 2100; i++) {
            anoComboBox.addItem(String.valueOf(i));
        }
        anoComboBox.setPreferredSize(new Dimension(300, 40));
        anoComboBox.setMaximumSize(new Dimension(300, 40));
        anoComboBox.setMinimumSize(new Dimension(300, 40));
        anoComboBox.setSelectedItem(String.valueOf(2024));
        anoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        panelAno.add(anoCampeonato);
        panelAno.add(anoComboBox);
        panel.add(panelAno);

        panel.add(Box.createVerticalStrut(20));

        JButton salvarCampeonato = new JButton("Salvar");
        salvarCampeonato.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    }
}
