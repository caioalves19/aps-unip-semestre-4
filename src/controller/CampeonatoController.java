package controller;

import dao.CampeonatoDAO;
import model.Campeonato;
import view.CampeonatoView;

import java.sql.SQLException;

public class CampeonatoController {
    private Campeonato campeonato;
    private CampeonatoView view;

    public CampeonatoController(Campeonato campeonato, CampeonatoView view) {
        this.campeonato = campeonato;
        this.view = view;
    }

    public void setView(CampeonatoView view) {
    }

    public void carregarCampeonato(int id) throws SQLException {
    }

    public void abrirConfiguracoes() {
        // Lógica para abrir a tela de configurações
    }

    public void voltarTelaInicial() {
        // Lógica para voltar à tela inicial
    }

    public void abrirJogos() {
        // Lógica para abrir a lista de jogos
    }
}

