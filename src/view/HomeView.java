package view;

import controller.CampeonatoController;
import model.Campeonato;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class HomeView extends JFrame {
    private CampeonatoController campeonatoController;

    public HomeView() {
        setTitle("Campeonatos");
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

        JLabel tituloLabel = new JLabel("Escolha o Campeonato", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        campeonatoController = new CampeonatoController(this);
        initCampeonatos(layeredPane);

        add(layeredPane);
    }

    private void initCampeonatos(JLayeredPane layeredPane) {
        try {
            // Acessa o banco e obtém todos os campeonatos
            List<Campeonato> campeonatos = campeonatoController.getCampeonatos();

            int yPosition = 150; // Posição inicial para os campeonatos

            for (Campeonato campeonato : campeonatos) {
                JLabel campeonatoLabel = new JLabel(campeonato.getNome());
                campeonatoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                campeonatoLabel.setForeground(Color.white);
                campeonatoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                campeonatoLabel.setBounds(170, yPosition, 170, 30);

                // Adiciona um listener para responder ao clique
                campeonatoLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CampeonatoView campeonatoView = null;
                        try {
                            campeonatoView = new CampeonatoView(campeonato.getId(), HomeView.this);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        campeonatoView.setVisible(true);
                        dispose();
                    }
                });

                layeredPane.add(campeonatoLabel, Integer.valueOf(1));
                yPosition += 50; // Ajusta a posição vertical para o próximo campeonato
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar campeonatos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
