package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.CampeonatoView;
import view.ConfigCampeonatoView;
import view.HomeView;
import view.ListaJogosView;

import java.sql.SQLException;

public class CampeonatoController {
    private final HomeView homeView;
    private final CampeonatoView campeonatoView;
    private final CampeonatoDAO campeonatoDAO;

    public CampeonatoController(CampeonatoView campeonatoView, HomeView homeView) {
        this.campeonatoView = campeonatoView;
        this.homeView = homeView;
        campeonatoDAO = new CampeonatoDAOImp();
    }

    public Campeonato getCampeonatoByID(int id) throws SQLException {
        return campeonatoDAO.get(id);
    }

    public void voltarHome() {
        homeView.atualizarCampeonatos();
        homeView.setVisible(true);
        campeonatoView.dispose();
    }

    public void exibirConfigCampeonato(int idCampeonato) throws SQLException {
        ConfigCampeonatoView configCampeonatoView = new ConfigCampeonatoView(idCampeonato, campeonatoView);
        configCampeonatoView.setVisible(true);
    }

    public void exibirListaJogos(int idCampeonato) throws SQLException {
        ListaJogosView listaJogosView = new ListaJogosView(idCampeonato, campeonatoView);
        listaJogosView.setVisible(true);
    }
}

