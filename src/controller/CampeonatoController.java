package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.CampeonatoView;
import view.HomeView;

import java.sql.SQLException;
import java.util.List;

public class CampeonatoController {
    private CampeonatoView view;
    private HomeView homeView;
    private CampeonatoDAO campeonatoDAO;

    public CampeonatoController(CampeonatoView view) {
        this.view = view;
        this.campeonatoDAO = new CampeonatoDAOImp();
    }

    public CampeonatoController(HomeView view) {
        this.homeView = view;
        this.campeonatoDAO = new CampeonatoDAOImp();
    }

    public List<Campeonato> getCampeonatos() throws SQLException {
        return campeonatoDAO.getAll();
    }
}

