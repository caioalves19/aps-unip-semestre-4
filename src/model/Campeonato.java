package model;

import java.util.HashSet;
import java.util.Set;

public class Campeonato {
    private int id;
    private String nome;
    private int ano;
    private Set<Time> times;
    private Set<Jogo> jogos;

    public Campeonato(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
        this.times = new HashSet<>();
        this.jogos = new HashSet<>();
    }

    public Campeonato(int id, String nome, int ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.times = new HashSet<>();
        this.jogos = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Set<Time> getTimes() {
        return times;
    }

    public boolean adicionarTime(Time time) {
        return times.add(time);
    }

    public boolean removerTime(Time time) {
        return times.remove(time);
    }

    public Set<Jogo> getJogos() {
        return jogos;
    }

    public boolean adicionarJogo(Jogo jogo) {
        if (times.contains(jogo.getTimeMandante()) && times.contains(jogo.getTimeVisitante())) {
            return jogos.add(jogo);
        }
        return false;
    }

    public boolean removerJogo(Jogo jogo) {
        return jogos.remove(jogo);
    }

    public String listarTimes() {
        StringBuilder sb = new StringBuilder("Times Participantes:\n");
        for (Time time : times) {
            sb.append(" - ").append(time.getNome()).append("\n");
        }
        return sb.toString();
    }

    public String listarJogos() {
        StringBuilder sb = new StringBuilder("Jogos:\n");
        for (Jogo jogo : jogos) {
            sb.append(" - ").append(jogo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Campeonato{" + "id=" + id + ", nome='" + nome + '\'' + ", ano=" + ano + '}';
    }
}
