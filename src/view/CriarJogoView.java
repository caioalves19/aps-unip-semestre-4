package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import model.Jogo;

public class CriarJogoView extends JDialog {

    private final JSpinner horaSpinner;
    private JSpinner dataSpinner;
    private JTextField estadioTextField;
    private JComboBox<String> time1ComboBox;
    private JComboBox<String> time2ComboBox;
    private Jogo jogo;

    // Construtor para criar um novo jogo
    public CriarJogoView(ListaJogosView listaJogosView) {
        this(null, listaJogosView);
    }

    // Construtor para editar um jogo existente
    public CriarJogoView(Jogo jogo, ListaJogosView listaJogosView) {
        super(listaJogosView, "Jogo", true);
        this.jogo = jogo;
        setTitle(jogo == null ? "Criar Jogo" : "Editar Jogo");
        setSize(705, 482);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(705, 482));

        JLabel tituloLabel = new JLabel(jogo == null ? "Criar Novo Jogo" : "Editar Jogo", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.BLACK);
        tituloLabel.setBounds(0, 20, getWidth(), 50);
        layeredPane.add(tituloLabel, Integer.valueOf(1));

        // ComboBox para Time 1

        time1ComboBox = new JComboBox<>(new String[]{"Selecione o Time 1", "Time A", "Time B", "Time C"});
        time1ComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        time1ComboBox.setBounds(130, 150, 200, 30);
        layeredPane.add(time1ComboBox, Integer.valueOf(1));

        // ComboBox para Time 2
        time2ComboBox = new JComboBox<>(new String[]{"Selecione o Time 2", "Time X", "Time Y", "Time Z"});
        time2ComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        time2ComboBox.setBounds(340, 150, 200, 30);
        layeredPane.add(time2ComboBox, Integer.valueOf(1));

        // Campo de texto para o Estádio com placeholder
        estadioTextField = new JTextField();
        estadioTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        estadioTextField.setBounds(130, 200, 410, 30);
        adicionarPlaceholder(estadioTextField, "Digite o Estádio...");
        layeredPane.add(estadioTextField, Integer.valueOf(1));

        // Spinner para a data
        SpinnerDateModel dataModel = new SpinnerDateModel();
        dataSpinner = new JSpinner(dataModel);
        JSpinner.DateEditor dataEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(dataEditor);
        dataSpinner.setBounds(130, 250, 200, 30);
        layeredPane.add(dataSpinner, Integer.valueOf(1));

        // Spinner para a hora
        SpinnerNumberModel horaModel = new SpinnerNumberModel(0, 0, 23, 1); // horas de 0 a 23
        horaSpinner = new JSpinner(horaModel);
        horaSpinner.setBounds(340, 250, 200, 30);
        layeredPane.add(horaSpinner, Integer.valueOf(1));

        if (jogo != null) {
            carregarDadosDoJogo();
        }

        // Botão para salvar o jogo
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        salvarButton.setBounds(450, 290, 90, 28);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jogo == null) {
                    // Código para salvar novo jogo
                } else {
                    // Código para atualizar jogo existente
                }
                dispose();
            }
        });
        layeredPane.add(salvarButton, Integer.valueOf(1));

        add(layeredPane);
    }

    private void carregarDadosDoJogo() {
        time1ComboBox.setSelectedItem(jogo.getTimeMandante());
        time2ComboBox.setSelectedItem(jogo.getTimeVisitante());
        estadioTextField.setText(jogo.getEstadio());
        //dataSpinner.setValue(jogo.getDataJogo());
        //horaSpinner.setValue(jogo.getDataJogo());
    }

    // Método para adicionar placeholder
    private void adicionarPlaceholder(JTextField textField, String placeholderText) {
        Color placeholderColor = Color.GRAY;
        textField.setForeground(placeholderColor);
        textField.setText(placeholderText);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Cor do texto normal
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(placeholderColor); // Cor do placeholder
                    textField.setText(placeholderText);
                }
            }
        });
    }
}
