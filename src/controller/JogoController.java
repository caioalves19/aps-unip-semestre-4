package controller;

import dao.JogoDAO;
import dao.JogoDAOImp;
import dao.TimeDAO;
import dao.TimeDAOImp;
import model.Jogo;
import view.CriarJogoView;
import view.ListaJogosView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoController {
    public JogoController(ListaJogosView listaJogosView) {
    }

    public JogoController(CriarJogoView view) {
    }

    public List<Jogo> getJogosByCampeonato(int idCampeonato) throws SQLException {
        ArrayList<Jogo> listaJogos = new ArrayList<>();
        JogoDAO jogoDAO = new JogoDAOImp();
        listaJogos = jogoDAO.getAllByCampeonato(idCampeonato);

        return listaJogos;
    }

    public void criarJogo(String timeMandante, String timeVisitante, String data, String hora) {
    }

    public void atualizarJogo(int jogoId, String timeMandante, String timeVisitante, String data, String hora) {
    }

    public Jogo getJogoById(int jogoId) throws SQLException {
        JogoDAO jogoDAO = new JogoDAOImp();
        return jogoDAO.get(jogoId);
    }
}
