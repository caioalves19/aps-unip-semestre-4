package view;

import controller.CampeonatoController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class ConfigCampeonatoView extends JFrame {

    private JPanel infoPanel;
    private JTextField caixaTextoNomeCampeonato;
    private CampeonatoController campeonatoController;
    private JComboBox<String> anoComboBox;

    public ConfigCampeonatoView(int idCampeonato, CampeonatoView campeonato) throws SQLException {
        campeonatoController = new CampeonatoController(this);

        setTitle("Configurações");
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        JLabel tituloLabel = new JLabel("Configurações", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        JButton botaoRemoverTime = new JButton("-");
        botaoRemoverTime.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoRemoverTime.setBounds(450, 299, 90, 28);

        JButton botaoAdicionarTime = new JButton("+");
        botaoAdicionarTime.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoAdicionarTime.setBounds(450, 258, 90, 28);

        JButton botaoSalvarInfoCampeonato = new JButton("Salvar");
        botaoSalvarInfoCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSalvarInfoCampeonato.setBounds(450, 150, 90, 28);

        botaoSalvarInfoCampeonato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeCampeonato = caixaTextoNomeCampeonato.getText();
                String anoCampeonato =  anoComboBox.getItemAt(anoComboBox.getSelectedIndex());

                try {
                    campeonatoController.updateCampeonato(idCampeonato, nomeCampeonato, Integer.parseInt(anoCampeonato));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                campeonato.atualizarCampeonato(nomeCampeonato);
            }
        });

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoVoltar.setBounds(25, 20, 90, 28);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        layeredPane.add(botaoVoltar, Integer.valueOf(1));
        layeredPane.add(botaoSalvarInfoCampeonato, Integer.valueOf(1));
        layeredPane.add(botaoAdicionarTime, Integer.valueOf(1));
        layeredPane.add(botaoRemoverTime, Integer.valueOf(1));

        // Primeira caixa de texto
        caixaTextoNomeCampeonato = new JTextField("Digite o Campeonato...");
        caixaTextoNomeCampeonato.setFont(new Font("Arial", Font.PLAIN, 16));
        caixaTextoNomeCampeonato.setForeground(Color.GRAY);
        caixaTextoNomeCampeonato.setBackground(Color.WHITE); // Fundo branco
        caixaTextoNomeCampeonato.setBounds(130, 150, 300, 30);
        adicionarPlaceholder(caixaTextoNomeCampeonato, "Digite o Campeonato...");
        layeredPane.add(caixaTextoNomeCampeonato, Integer.valueOf(1));

        String[] anos = new String[201]; // Cria um array para os anos de 1900 a 2100
        for (int i = 0; i < anos.length; i++) {
            anos[i] = String.valueOf(1900 + i); // Preenche o array com os anos
        }
        anoComboBox = new JComboBox<>(anos);
        anoComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        anoComboBox.setBackground(Color.WHITE); // Fundo branco
        anoComboBox.setForeground(Color.BLACK); // Texto preto
        anoComboBox.setBounds(130, 200, 300, 30);

        layeredPane.add(anoComboBox, Integer.valueOf(1));

        // Painel para mostrar informações
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBounds(130, 250, 300, 100);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Times"));
        layeredPane.add(infoPanel, Integer.valueOf(1));

        // Adiciona DocumentListener à primeira caixa de texto
        caixaTextoNomeCampeonato.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateInfoPanel(caixaTextoNomeCampeonato.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateInfoPanel(caixaTextoNomeCampeonato.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Não usado para JTextField
            }
        });

        String nomeCampeonato = campeonatoController.getCampeonatoByID(idCampeonato).getNome();
        atualizarNomeCampeonato(nomeCampeonato);

        String anoCampeonato = String.valueOf(campeonatoController.getCampeonatoByID(idCampeonato).getAno());
        anoComboBox.setSelectedItem(anoCampeonato);

        add(layeredPane);
    }

    private void adicionarPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY); // Cor cinza para o placeholder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); // Limpa o placeholder
                    textField.setForeground(Color.BLACK); // Define a cor do texto como preto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // Retorna a cor do texto como cinza
                    textField.setText(placeholder); // Define o texto como o placeholder novamente
                }
            }
        });
    }

    private void updateInfoPanel(String text) {
        infoPanel.removeAll();

        if (!text.isEmpty() && !text.equals("Digite o Campeonato...")) {
            JLabel infoLabel = new JLabel("Você digitou: " + text);
            infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            infoPanel.add(infoLabel);
        }

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void atualizarNomeCampeonato(String nome) {
        this.caixaTextoNomeCampeonato.setText(nome);
        this.caixaTextoNomeCampeonato.setForeground(Color.BLACK);
    }
}
