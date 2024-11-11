package controller;

import dao.TimeDAO;
import dao.TimeDAOImp;
import model.Time;
import view.HomeView;

import java.sql.SQLException;

public class CriarTimeController {
    public CriarTimeController(HomeView homeView) {
    }

    public boolean adicionarTime(String nome) throws SQLException {
        Time time = new Time(nome);
        TimeDAO timeDAO = new TimeDAOImp();

        int resultado = timeDAO.insert(time);
        return resultado != 0;
    }
}