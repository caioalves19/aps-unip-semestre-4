package view;

import controller.HomeController;
import model.Campeonato;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class HomeView extends JFrame {
    private final HomeController homeController;
    private final JPanel panelCampeonatosContainer;

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

        homeController = new HomeController(this);

        // Criação do container para campeonatos
        panelCampeonatosContainer = new JPanel();
        panelCampeonatosContainer.setOpaque(false); // Transparente
        panelCampeonatosContainer.setBounds(0, 150, getWidth()-15, 300);
        layeredPane.add(panelCampeonatosContainer, Integer.valueOf(1));

        atualizarCampeonatos();

        JButton botaoAdicionarCampeonato = new JButton("Criar Campeonato");
        botaoAdicionarCampeonato.setBackground(Color.lightGray);
        botaoAdicionarCampeonato.setForeground(Color.black);
        botaoAdicionarCampeonato.setBounds(312, 500, 300, 40);
        botaoAdicionarCampeonato.setFont(new Font("Arial", Font.PLAIN, 15));
        botaoAdicionarCampeonato.setFocusPainted(false);
        layeredPane.add(botaoAdicionarCampeonato, Integer.valueOf(1));

        botaoAdicionarCampeonato.addActionListener(e -> homeController.exibirCriarCampeonatoView());

        add(layeredPane);
    }

    public void atualizarCampeonatos() {
        try {
            // Acessa o banco e obtém todos os campeonatos
            List<Campeonato> campeonatos = homeController.getCampeonatos();

            // Limpa o container de campeonatos antes de adicionar novos componentes
            panelCampeonatosContainer.removeAll();

            // Cria um JPanel com BoxLayout para centralizar os JPanels dos campeonatos
            JPanel panelCampeonatos = new JPanel();
            panelCampeonatos.setLayout(new BoxLayout(panelCampeonatos, BoxLayout.Y_AXIS));
            panelCampeonatos.setOpaque(false);

            for (Campeonato campeonato : campeonatos) {
                JLabel campeonatoLabel = new JLabel(campeonato.getNome(), SwingConstants.CENTER);
                campeonatoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                campeonatoLabel.setForeground(Color.white);
                campeonatoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                campeonatoLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        homeController.exibirCampeonato(campeonato.getId());
                    }
                });

                JPanel campeonatoPanel = new JPanel();
                campeonatoPanel.setLayout(new BorderLayout());
                campeonatoPanel.setOpaque(false);
                campeonatoPanel.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(Color.WHITE, 2, true),
                        new EmptyBorder(10, 10, 10, 10)
                ));
                campeonatoPanel.setMaximumSize(new Dimension(300, 40));
                campeonatoPanel.add(campeonatoLabel, BorderLayout.CENTER);
                campeonatoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                panelCampeonatos.add(campeonatoPanel);
                panelCampeonatos.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            JScrollPane scrollPane = new JScrollPane(panelCampeonatos);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(getWidth()-15, 300));

            panelCampeonatosContainer.add(scrollPane);

            panelCampeonatosContainer.revalidate();
            panelCampeonatosContainer.repaint();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar campeonatos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}