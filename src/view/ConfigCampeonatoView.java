package view;

import controller.CampeonatoController;
import model.Campeonato;

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

    private JPanel infoPanel;// Painel para mostrar informações
    private JTextField caixaTexto1;
    private CampeonatoController campeonatoController;

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
        
        JButton botaoMenos = new JButton("-");
        botaoMenos.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoMenos.setBounds(450, 299, 90, 28);
        
        JButton botaoMais = new JButton("+");
        botaoMais.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoMais.setBounds(450, 258, 90, 28);

        JButton botaoSave = new JButton("Salvar");
        botaoSave.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSave.setBounds(450, 150, 90, 28);

        JButton botaoSave2 = new JButton("Salvar");
        botaoSave2.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoSave2.setBounds(450, 200, 90, 28);

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
        layeredPane.add(botaoSave, Integer.valueOf(1));
        layeredPane.add(botaoSave2, Integer.valueOf(1));
        layeredPane.add(botaoMais, Integer.valueOf(1));
        layeredPane.add(botaoMenos, Integer.valueOf(1));

        // Primeira caixa de texto
        caixaTexto1 = new JTextField("Digite o Campeonato...");
        caixaTexto1.setFont(new Font("Arial", Font.PLAIN, 16));
        caixaTexto1.setForeground(Color.BLACK);
        caixaTexto1.setBackground(Color.WHITE); // Fundo branco
        caixaTexto1.setBounds(130, 150, 300, 30);
        adicionarPlaceholder(caixaTexto1, "Digite o Campeonato...");
        layeredPane.add(caixaTexto1, Integer.valueOf(1));

        // Segunda caixa de texto
        JTextField caixaTexto2 = new JTextField("Digite o Time...");
        caixaTexto2.setFont(new Font("Arial", Font.PLAIN, 16));
        caixaTexto2.setForeground(Color.GRAY);
        caixaTexto2.setBackground(Color.WHITE); // Fundo branco
        caixaTexto2.setBounds(130, 200, 300, 30);
        adicionarPlaceholder(caixaTexto2, "Digite o Time...");
        layeredPane.add(caixaTexto2, Integer.valueOf(1));

        // Painel para mostrar informações
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBounds(130, 250, 300, 100);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Times"));
        layeredPane.add(infoPanel, Integer.valueOf(1));

        // Adiciona DocumentListener à primeira caixa de texto
        caixaTexto1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateInfoPanel(caixaTexto1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateInfoPanel(caixaTexto1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Não usado para JTextField
            }
        });
        String nomeCampeonato = campeonatoController.getCampeonatoByID(idCampeonato).getNome();
        atualizarNomeCampeonato(nomeCampeonato);

        add(layeredPane);
    }

    private void adicionarPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
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

    private void atualizarNomeCampeonato(String nome){
        this.caixaTexto1.setText(nome);
    }
}