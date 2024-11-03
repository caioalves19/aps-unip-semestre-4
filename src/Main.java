import controller.CampeonatoController;
import dao.*;
import model.Campeonato;
import model.Jogo;
import model.Time;
import view.CampeonatoView;
import view.HomeView;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) throws SQLException {


		SwingUtilities.invokeLater(() -> {
			HomeView homeView = new HomeView();
			homeView.setVisible(true);
		});
	}
}

