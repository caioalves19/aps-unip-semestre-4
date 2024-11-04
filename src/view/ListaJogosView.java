package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controller.JogoController;
import model.Jogo;

public class ListaJogosView extends JFrame {

    private JList<String> jogosList;
    private DefaultListModel<String> listModel;
    private JogoController jogoController;

    public ListaJogosView(int idCampeonato) throws SQLException {
        this.jogoController = new JogoController(this); // Utilizar o campo de instância
        setTitle("Jogos Brasileirão");
        setSize(534, 390);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        // Imagem de fundo
        ImageIcon backgroundImage = new ImageIcon("src/img/CampoFutebol.png");
        JLabel imageLabel = new JLabel(backgroundImage);
        imageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        // Título
        JLabel tituloLabel = new JLabel("Jogos Brasileirão", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setBounds(-15, 38, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // Lista de jogos
        listModel = new DefaultListModel<>();
        jogosList = new JList<>(listModel);
        jogosList.setFont(new Font("Arial", Font.PLAIN, 16));
        jogosList.setBounds(50, 100, 400, 150);
        jogosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jogosList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Verifica se foi um clique duplo
                if (e.getClickCount() == 2 && jogosList.getSelectedIndex() != -1) {
                    String selectedGame = jogosList.getSelectedValue();
                    String[] parts = selectedGame.split(" - ");
                    int jogoId = Integer.parseInt(parts[0]);
                    Jogo jogo = null; // Método no controller para buscar jogo
                    try {
                        jogo = jogoController.getJogoById(jogoId);
                        System.out.println(jogo.getTimeMandante().getNome());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    CriarJogoView editarJogoView = new CriarJogoView(jogo);
                    editarJogoView.setVisible(true);
                }
            }
    });
        layeredPane.add(jogosList, Integer.valueOf(1));

        // Botão "Adicione um jogo"
        JButton botaoAdicionarJogo = new JButton("Adicione um jogo");
        botaoAdicionarJogo.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoAdicionarJogo.setBounds(165, 260, 180, 30);
        botaoAdicionarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CriarJogoView criarJogoView = new CriarJogoView();
                criarJogoView.setVisible(true);
            }
        });
        layeredPane.add(botaoAdicionarJogo, Integer.valueOf(1));

        add(layeredPane);
        carregarListaDeJogos(idCampeonato);
    }

    // Carregar a lista de jogos a partir do banco de dados
    private void carregarListaDeJogos(int idCampeonato) throws SQLException {
        List<Jogo> jogos = jogoController.getJogosByCampeonato(idCampeonato);
        listModel.clear();
        for (Jogo jogo : jogos) {
            String jogoInfo = String.format("%s - %s x %s", jogo.getId(), jogo.getTimeMandante().getNome(), jogo.getTimeVisitante().getNome());
            listModel.addElement(jogoInfo);
        }
    }
}
