package view;

import controller.CampeonatoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CampeonatoView extends JFrame {
        private HomeView homeView;
        private CampeonatoController campeonatoController;
        private JLabel tituloLabel;

    public CampeonatoView(int idCampeonato, HomeView view) throws SQLException {
        this.homeView = homeView;
        campeonatoController = new CampeonatoController(this);
        String titulo = campeonatoController.getCampeonatoByID(idCampeonato).getNome();
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

        tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
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
                ConfigCampeonatoView configCampeonatoView = null;
                try {
                    configCampeonatoView = new ConfigCampeonatoView(idCampeonato, CampeonatoView.this);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                configCampeonatoView.setVisible(true);
            }
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoVoltar.setBounds(25, 20, 90, 28);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeView homeView = new HomeView();
                homeView.setVisible(true); // Exibe a HomeView
                dispose(); // Fecha a CampeonatoView
            }
        });


        JButton botaoJogos = new JButton("Jogos");
        botaoJogos.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoJogos.setBounds(480, 350, 150, 70);

        botaoJogos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaJogosView listaJogosView = new ListaJogosView();
                listaJogosView.setVisible(true);
            }
        });

        layeredPane.add(botaoConfig, Integer.valueOf(1));
        layeredPane.add(botaoJogos, Integer.valueOf(1));
        layeredPane.add(botaoVoltar, Integer.valueOf(1));

        add(layeredPane);
    }

    public void atualizarCampeonato(String nome) {
        this.tituloLabel.setText(nome);
        }
}