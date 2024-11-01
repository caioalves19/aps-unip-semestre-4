import dao.TimeDAO;
import dao.TimeDAOImp;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		TimeDAO timeDAO = new TimeDAOImp();
		System.out.println(timeDAO.getNome("Vasco"));
	}

}
