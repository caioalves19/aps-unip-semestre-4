package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ListaTimesView extends JDialog {
    private final JTextField searchField;
    private final JList<String> timesList;
    private final List<String> allTimes;
    private String selectedTime;

    public ListaTimesView(ConfigCampeonatoView parent, List<String> times) {
        super(parent, "Selecione um Time", true);
        this.allTimes = new ArrayList<>(times);

        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Campo de pesquisa
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 30));
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterList(searchField.getText());
            }
        });
        add(searchField, BorderLayout.NORTH);

        // Lista de times
        timesList = new JList<>(new DefaultListModel<>());
        timesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(timesList);
        add(scrollPane, BorderLayout.CENTER);

        // Botão de seleção
        JButton selectButton = new JButton("Selecionar");
        selectButton.addActionListener(e -> {
            selectedTime = timesList.getSelectedValue();
            dispose();
        });
        add(selectButton, BorderLayout.SOUTH);

        // Inicializar a lista com todos os times
        filterList("");
    }

    private void filterList(String filter) {
        DefaultListModel<String> model = (DefaultListModel<String>) timesList.getModel();
        model.clear();
        for (String time : allTimes) {
            if (time.toLowerCase().contains(filter.toLowerCase())) {
                model.addElement(time);
            }
        }
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public static String showDialog(ConfigCampeonatoView parent, List<String> times) {
        ListaTimesView dialog = new ListaTimesView(parent, times);
        dialog.setVisible(true);
        return dialog.getSelectedTime();
    }
}
