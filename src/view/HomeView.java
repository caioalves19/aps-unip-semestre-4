package view;

import controller.HomeController;
import model.Campeonato;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class HomeView extends JFrame {
    private HomeController homeController;

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
        initCampeonatos(layeredPane);

        add(layeredPane);
    }


    private void initCampeonatos(JLayeredPane layeredPane) {
        try {
            // Acessa o banco e obtém todos os campeonatos
            List<Campeonato> campeonatos = homeController.getCampeonatos();

            // Cria um JPanel com BoxLayout para centralizar os JPanels dos campeonatos
            JPanel panelCampeonatos = new JPanel();
            panelCampeonatos.setLayout(new BoxLayout(panelCampeonatos, BoxLayout.Y_AXIS));
            panelCampeonatos.setOpaque(false); // Torna o painel transparente

            // Adiciona cada campeonato dentro de um JPanel com margem e centralizado
            for (Campeonato campeonato : campeonatos) {
                // Cria um JLabel para o nome do campeonato
                JLabel campeonatoLabel = new JLabel(campeonato.getNome(), SwingConstants.CENTER);
                campeonatoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                campeonatoLabel.setForeground(Color.white);
                campeonatoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

                // Cria um JPanel para envolver o JLabel e aplica uma margem interna e externa
                JPanel campeonatoPanel = new JPanel();
                campeonatoPanel.setLayout(new BorderLayout());
                campeonatoPanel.setOpaque(false);// Torna o painel transparente
                campeonatoPanel.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(Color.WHITE, 2, true), // Borda branca com cantos arredondados
                        new EmptyBorder(10, 10, 10, 10) // Margem interna de 10 pixels
                ));
                campeonatoPanel.setMaximumSize(new Dimension(300, 40)); // Define um tamanho fixo para centralizar
                campeonatoPanel.add(campeonatoLabel, BorderLayout.CENTER);

                // Centraliza o painel no BoxLayout
                campeonatoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Adiciona o JPanel do campeonato ao painel principal
                panelCampeonatos.add(campeonatoPanel);
                panelCampeonatos.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os JPanels
            }

            // Envolve o panelCampeonatos em um JScrollPane
            JScrollPane scrollPane = new JScrollPane(panelCampeonatos);
            scrollPane.setBounds(0, 150, getWidth()-15, 300); // Define a posição e o tamanho do JScrollPane
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false); // Torna o fundo transparente
            scrollPane.setBorder(null); // Remove a borda do JScrollPane
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Desabilita o scroll horizontal
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setBackground(Color.lightGray); // Cor de fundo
            verticalScrollBar.setForeground(Color.black); // Cor da barra

            // Adiciona o JScrollPane ao layeredPane
            layeredPane.add(scrollPane, Integer.valueOf(1));

            JButton botaoAdicionarCampeonato = new JButton("Adicionar Campeonato");
            botaoAdicionarCampeonato.setBackground(Color.lightGray);
            botaoAdicionarCampeonato.setForeground(Color.black);
            botaoAdicionarCampeonato.setBounds(312, 500, 300, 40);
            botaoAdicionarCampeonato.setFont(new Font("Arial", Font.PLAIN, 15));
            botaoAdicionarCampeonato.setFocusPainted(false);

            layeredPane.add(botaoAdicionarCampeonato, Integer.valueOf(1));

            botaoAdicionarCampeonato.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    AdicionarCampeonatoView adicionarCampeonatoView = new AdicionarCampeonatoView(HomeView.this);
                    adicionarCampeonatoView.setVisible(true);
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar campeonatos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}