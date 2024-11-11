package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.HomeView;

import java.sql.SQLException;

public class CriarCampeonatoController {
    private final HomeView homeView; // Adicione a referência para HomeView

    public CriarCampeonatoController(HomeView homeView) {
        this.homeView = homeView;
    }

    public boolean adicionarCampeonato(String nome, int ano) throws SQLException {
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        Campeonato campeonato = new Campeonato(nome, ano);
        int resposta = campeonatoDAO.insert(campeonato);
        homeView.atualizarCampeonatos();
        return resposta != 0;
    }
}
