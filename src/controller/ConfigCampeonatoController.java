package controller;

import dao.*;
import model.Campeonato;
import model.Time;
import view.CampeonatoView;
import view.ConfigCampeonatoView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfigCampeonatoController {
    private final ConfigCampeonatoView configCampeonatoView;
    private final CampeonatoView campeonatoView;

    public ConfigCampeonatoController(ConfigCampeonatoView configCampeonatoView, CampeonatoView campeonatoView) {
        this.configCampeonatoView = configCampeonatoView;
        this.campeonatoView = campeonatoView;
    }

    public void atualizarTimesParticipantes(int idCampeonato) throws SQLException {
        List<Time> times;
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getTimesByCampeonato(idCampeonato);
        configCampeonatoView.atualizarListaTimes((ArrayList<Time>) times);

    }

    public void addTimeToCampeonato(int idCampeonato, String nomeTime) throws SQLException {
        Time time;
        TimeDAO timeDAO = new TimeDAOImp();
        time = timeDAO.getNome(nomeTime);
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        campeonatoTimeDAO.addTimeToCampeonato(idCampeonato, time.getId());
        atualizarTimesParticipantes(idCampeonato);
    }

    public void removeTimeFromCampeonato(int idCampeonato, String nomeTime) throws SQLException {
        Time time;
        TimeDAO timeDAO = new TimeDAOImp();
        time = timeDAO.getNome(nomeTime);

        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        campeonatoTimeDAO.removeTimeFromCampeonato(idCampeonato, time.getId());
        atualizarTimesParticipantes(idCampeonato);
    }

    public ArrayList<String> getAllTimesNaoParticipantes(int idCampeonato) throws SQLException {
        ArrayList<Time> times;
        ArrayList<String> nomesTimes = new ArrayList<>(List.of());
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getAllTimesNaoParticipantes(idCampeonato);

        times.forEach(time -> nomesTimes.add(time.getNome()));
        return nomesTimes;
    }

    public boolean updateCampeonato(int idCampeonato, String nome, int ano) throws SQLException {
        Campeonato campeonato = new Campeonato(idCampeonato, nome, ano);
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        int resposta = campeonatoDAO.update(campeonato);
        campeonatoView.atualizarCampeonato(idCampeonato);
        return resposta != 0;
    }

    public Campeonato getCampeonatoByID(int id) throws SQLException {
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        return campeonatoDAO.get(id);
    }

}
