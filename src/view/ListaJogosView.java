package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaJogosView extends JFrame {

    public ListaJogosView() {
        setTitle("Jogos Brasileirão");
        setSize(534, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Botão "Adicione um jogo"
        JButton botaoAdicionarJogo = new JButton("Adicione um jogo");
        botaoAdicionarJogo.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoAdicionarJogo.setBounds(165, 160, 180, 65); // Centraliza o botão na tela
        botaoAdicionarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela CriarJogo ao clicar no botão "Adicione um jogo"
                CriarJogoView criarJogoViewTela = new CriarJogoView();
                criarJogoViewTela.setVisible(true);
                dispose(); // Fecha a tela atual (JogosBrasileirao)
            }
        });
        layeredPane.add(botaoAdicionarJogo, Integer.valueOf(1));

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        voltarButton.setBounds(25, 20, 90, 28);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CampeonatoView campeonatoView = new CampeonatoView("Informações do Campeonato");
                campeonatoView.setVisible(true);
                dispose(); // Fecha a tela atual (JogosBrasileirao)
            }
        });
        layeredPane.add(voltarButton, Integer.valueOf(1));

        // Coloca o painel de fundo em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(layeredPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Adiciona o JScrollPane à janela
        add(scrollPane);
    }

    public static void main(String[] args) {
        ListaJogosView jogos = new ListaJogosView();
        jogos.setVisible(true);
    }
}