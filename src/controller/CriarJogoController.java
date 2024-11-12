package controller;

import dao.CampeonatoTimeDAO;
import dao.CampeonatoTimeDAOImp;
import model.Time;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriarJogoController {
    public List<String> getTimesParticipantes(int idCampeonato) throws SQLException {
        List<Time> times;
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getTimesByCampeonato(idCampeonato);
        List<String> nomesTimes = new ArrayList<>();

        for (Time time : times) {
            nomesTimes.add(time.getNome());
        }

        return nomesTimes;
    }
}
