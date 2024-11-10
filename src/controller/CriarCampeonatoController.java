package controller;

import dao.CampeonatoDAO;
import dao.CampeonatoDAOImp;
import model.Campeonato;
import view.CriarCampeonatoView;

import java.sql.SQLException;

public class CriarCampeonatoController {
    public CriarCampeonatoController(CriarCampeonatoView view){}

    public boolean adicionarCampeonato(String nome, int ano) throws SQLException {
        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        Campeonato campeonato = new Campeonato(nome, ano);
        int insert = campeonatoDAO.insert(campeonato);
        return insert != 0;
    }
}
