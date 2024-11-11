package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.CampeonatoView;
import view.CriarCampeonatoView;
import view.CriarTimeView;
import view.HomeView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private HomeView homeView;

    public HomeController(HomeView homeView) {
        this.homeView = homeView;
    }

    public List<Campeonato> getCampeonatos() throws SQLException {
        List<Campeonato> listaCampeonatos = new ArrayList<>();
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();

        listaCampeonatos = campeonatoDAO.getAll();

        return listaCampeonatos;
    }

    public void exibirCampeonato(int campeonatoId) {
        try {
            CampeonatoView campeonatoView = new CampeonatoView(campeonatoId, homeView);
            campeonatoView.setVisible(true);
            homeView.dispose();  // Fechar a HomeView
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void exibirCriarCampeonatoView() {
        CriarCampeonatoView criarCampeonatoView = new CriarCampeonatoView(homeView);
        criarCampeonatoView.setVisible(true);
    }

    public void exibirCriarTimeView() {
        CriarTimeView criarTimeView = new CriarTimeView(homeView);
        criarTimeView.setVisible(true);
    }
}