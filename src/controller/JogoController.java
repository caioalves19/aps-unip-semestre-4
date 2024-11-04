package controller;

import model.Jogo;
import view.CriarJogoView;
import view.ListaJogosView;

import java.util.List;

public class JogoController {
    public JogoController(ListaJogosView listaJogosView) {
    }

    public JogoController(CriarJogoView view) {
    }

    public List<Jogo> getJogosByCampeonato(int campeonatoId) {
        return null;
    }

    public void criarJogo(String timeMandante, String timeVisitante, String data, String hora) {
    }

    public void atualizarJogo(int jogoId, String timeMandante, String timeVisitante, String data, String hora) {
    }

    public Jogo getJogoById(int jogoId) {
        return null;
    }
}
