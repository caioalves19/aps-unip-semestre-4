package view;

import controller.CampeonatoController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CampeonatoView extends JFrame {
    private final JLabel tituloLabel;
    CampeonatoController campeonatoController;

    public CampeonatoView(int idCampeonato, HomeView homeView) throws SQLException {
        campeonatoController = new CampeonatoController(this, homeView);
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

        tituloLabel = new JLabel("Teste", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));
        atualizarCampeonato(idCampeonato);


        JButton botaoConfig = new JButton("Configurações");
        botaoConfig.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoConfig.setBounds(280, 350, 150, 70);

        botaoConfig.addActionListener(e -> {
            try {
                campeonatoController.exibirConfigCampeonato(idCampeonato);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoVoltar.setBounds(25, 20, 90, 28);

        botaoVoltar.addActionListener(e -> campeonatoController.voltarHome());


        JButton botaoJogos = new JButton("Jogos");
        botaoJogos.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoJogos.setBounds(480, 350, 150, 70);

        botaoJogos.addActionListener(e -> {
            try {
                campeonatoController.exibirListaJogos(idCampeonato);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        layeredPane.add(botaoConfig, Integer.valueOf(1));
        layeredPane.add(botaoJogos, Integer.valueOf(1));
        layeredPane.add(botaoVoltar, Integer.valueOf(1));

        add(layeredPane);
    }

    public void atualizarCampeonato(int id) throws SQLException {
        String titulo = campeonatoController.getCampeonatoByID(id).getNome();
        this.tituloLabel.setText(titulo);
        }
}