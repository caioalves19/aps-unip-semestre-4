import dao.*;
import model.Campeonato;
import model.Jogo;
import model.Time;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) throws SQLException {

		TimeDAO timeDAO = new TimeDAOImp();
		CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
		JogoDAO jogoDAO = new JogoDAOImp();

		Time vasco = timeDAO.get(1);
		Time bahia = timeDAO.get(9);

		Campeonato campeonato = campeonatoDAO.get(1);

		Jogo jogo = new Jogo(campeonato, vasco, bahia, LocalDateTime.of(2024, 11, 1, 16, 0), "São Januário");


		System.out.println(jogoDAO.getAll());
	}

}
