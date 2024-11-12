package view;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import controller.ListaJogosController;
import model.Jogo;

public class ListaJogosView extends JDialog {

    private final JList<Jogo> jogosList;
    private final DefaultListModel<Jogo> listModel;
    private final ListaJogosController listaJogosController;

    public ListaJogosView(int idCampeonato, CampeonatoView campeonatoView) throws SQLException {
        super(campeonatoView, "Lista de Jogos", true);
        this.listaJogosController = new ListaJogosController(this); // Utilizar o campo de instância
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        // Imagem de fundo
        ImageIcon backgroundImage = new ImageIcon("src/img/background.png");
        JLabel imageLabel = new JLabel(backgroundImage);
        imageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        // Título
        JLabel tituloLabel = new JLabel("Lista de Jogos", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setBounds(-15, 38, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // Lista de jogos
        listModel = new DefaultListModel<>();
        jogosList = new JList<>(listModel);
        jogosList.setFont(new Font("Arial", Font.PLAIN, 16));
        jogosList.setBounds(50, 100, this.getWidth()-100, 250);
        jogosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jogosList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Verifica se foi um clique duplo
                if (e.getClickCount() == 2 && jogosList.getSelectedIndex() != -1) {
                    Jogo selectedGame = jogosList.getSelectedValue();
                    Jogo jogo; // Método no controller para buscar jogo
                    try {
                        jogo = listaJogosController.getJogoById(selectedGame.getId());
                        listaJogosController.exibirTelaJogo(jogo, idCampeonato);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
    });
        layeredPane.add(jogosList, Integer.valueOf(1));

        // Botão "Adicione um jogo"
        JButton botaoAdicionarJogo = new JButton("Adicione um jogo");
        botaoAdicionarJogo.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoAdicionarJogo.setBounds((this.getWidth()-180)/2, this.getHeight()-100, 180, 30);
        botaoAdicionarJogo.addActionListener(e -> {
            try {
                listaJogosController.exibirTelaJogo(idCampeonato);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        layeredPane.add(botaoAdicionarJogo, Integer.valueOf(1));

        add(layeredPane);
        carregarListaDeJogos(idCampeonato);
    }

    // Carregar a lista de jogos a partir do banco de dados
    private void carregarListaDeJogos(int idCampeonato) throws SQLException {
        List<Jogo> jogos = listaJogosController.getJogosByCampeonato(idCampeonato);
        listModel.clear();
        for (Jogo jogo : jogos) {
            listModel.addElement(jogo);
        }
    }
}
