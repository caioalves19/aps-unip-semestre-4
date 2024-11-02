import controller.CampeonatoController;
import dao.*;
import model.Campeonato;
import model.Jogo;
import model.Time;
import view.CampeonatoView;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) throws SQLException {
			CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
			Campeonato campeonato = campeonatoDAO.get(2);

			CampeonatoView view = new CampeonatoView(campeonato.getNome());

			CampeonatoController campeonatoController = new CampeonatoController(campeonato, view);

			view.setVisible(true);

	}
}

