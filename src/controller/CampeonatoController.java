package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.CampeonatoView;
import view.ConfigCampeonatoView;
import view.HomeView;

import java.sql.SQLException;
import java.util.List;

public class CampeonatoController {
    private CampeonatoView campoenatoView;
    private HomeView homeView;
    private ConfigCampeonatoView configCampeonatoView;
    private CampeonatoDAO campeonatoDAO;

    public CampeonatoController(CampeonatoView view) {
        this.campoenatoView = view;
        this.campeonatoDAO = new CampeonatoDAOImp();
    }

    public CampeonatoController(HomeView view) {
        this.homeView = view;
        this.campeonatoDAO = new CampeonatoDAOImp();
    }

    public CampeonatoController(ConfigCampeonatoView view) {
        this.configCampeonatoView = view;
        this.campeonatoDAO = new CampeonatoDAOImp();
    }

    public List<Campeonato> getCampeonatos() throws SQLException {
        return campeonatoDAO.getAll();
    }

    public Campeonato getCampeonatoByID(int id) throws SQLException {
        return campeonatoDAO.get(id);
    }
}

