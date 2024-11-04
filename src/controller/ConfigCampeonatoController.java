package controller;

import dao.CampeonatoTimeDAO;
import dao.CampeonatoTimeDAOImp;
import dao.TimeDAO;
import dao.TimeDAOImp;
import model.Time;
import view.ConfigCampeonatoView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfigCampeonatoController extends CampeonatoController{
    public ConfigCampeonatoController(ConfigCampeonatoView view) {
        super(view);
    }

    public ArrayList<String> getTimesByCampeonatoID(int idCampeonato) throws SQLException {
        List<Time> times;
        ArrayList<String> nomesTimes = new ArrayList<>(List.of());
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getTimesByCampeonato(idCampeonato);

        times.forEach(time -> nomesTimes.add(time.getNome()));

        return nomesTimes;
    }

    public void addTimeToCampeonato(int idCampeonato, String nomeTime) throws SQLException {
        Time time;
        TimeDAO timeDAO = new TimeDAOImp();
        time = timeDAO.getNome(nomeTime);

        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        campeonatoTimeDAO.addTimeToCampeonato(idCampeonato, time.getId());
    }

    public void removeTimeFromCampeonato(int idCampeonato, String nomeTime) throws SQLException {
            Time time;
            TimeDAO timeDAO = new TimeDAOImp();
            time = timeDAO.getNome(nomeTime);

            CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
            campeonatoTimeDAO.removeTimeFromCampeonato(idCampeonato, time.getId());
    }

    public ArrayList<String> getAllTimesNaoParticipantes(int idCampeonato) throws SQLException {
        ArrayList<Time> times;
        ArrayList<String> nomesTimes = new ArrayList<>(List.of());
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getAllTimesNaoParticipantes(idCampeonato);

        times.forEach(time -> nomesTimes.add(time.getNome()));
        return nomesTimes;
    }
}
