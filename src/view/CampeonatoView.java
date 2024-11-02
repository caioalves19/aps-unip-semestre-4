package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampeonatoView extends JFrame {

    public CampeonatoView(String titulo) {
        setTitle(titulo);
        setSize(925, 592);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        ImageIcon icon = new ImageIcon("src/img/background.png");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));


        JButton botaoConfig = new JButton("Configurações");
        botaoConfig.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoConfig.setBounds(280, 350, 150, 70);

        botaoConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo configurações...");
            }
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoVoltar.setBounds(25, 20, 90, 28);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Voltando...");
            }
        });


        JButton botaoJogos = new JButton("Jogos");
        botaoJogos.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoJogos.setBounds(480, 350, 150, 70);

        botaoJogos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo jogos...");
            }
        });

        layeredPane.add(botaoConfig, Integer.valueOf(1));
        layeredPane.add(botaoJogos, Integer.valueOf(1));
        layeredPane.add(botaoVoltar, Integer.valueOf(1));

        add(layeredPane);
    }
}