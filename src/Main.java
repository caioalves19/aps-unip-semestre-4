import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import dao.TimeDAO;
import dao.TimeDAOImp;
import model.Campeonato;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		TimeDAO timeDAO = new TimeDAOImp();
		CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();

		Campeonato serieC = campeonatoDAO.get(3);
		campeonatoDAO.delete(serieC);

		System.out.println(campeonatoDAO.getAll());
	}

}
