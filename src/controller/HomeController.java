package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.HomeView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    public HomeController(HomeView homeView) {
    }

    public List<Campeonato> getCampeonatos() throws SQLException {
        List<Campeonato> listaCampeonatos = new ArrayList<>();
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();

        listaCampeonatos = campeonatoDAO.getAll();

        return listaCampeonatos;
    }
}