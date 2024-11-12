package controller;

import dao.JogoDAO;
import dao.JogoDAOImp;
import model.Jogo;
import view.CriarJogoView;
import view.ListaJogosView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaJogosController {
    private final ListaJogosView listaJogosView;

    public ListaJogosController(ListaJogosView listaJogosView) {
        this.listaJogosView = listaJogosView;
    }

    public List<Jogo> getJogosByCampeonato(int idCampeonato) throws SQLException {
        ArrayList<Jogo> listaJogos;
        JogoDAO jogoDAO = new JogoDAOImp();
        listaJogos = jogoDAO.getAllByCampeonato(idCampeonato);
        return listaJogos;
    }

    public Jogo getJogoById(int jogoId) throws SQLException {
        JogoDAO jogoDAO = new JogoDAOImp();
        return jogoDAO.get(jogoId);
    }

    public void exibirTelaJogo(Jogo jogo) {
        CriarJogoView editarJogoView = new CriarJogoView(jogo, listaJogosView);
        editarJogoView.setVisible(true);
    }

    public void exibirTelaJogo() {
        CriarJogoView editarJogoView = new CriarJogoView(listaJogosView);
        editarJogoView.setVisible(true);
    }
}
