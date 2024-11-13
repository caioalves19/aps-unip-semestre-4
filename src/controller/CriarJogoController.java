package controller;

import dao.*;
import model.Campeonato;
import model.Jogo;
import model.Time;
import view.ListaJogosView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CriarJogoController {
    private final ListaJogosView listaJogosView;

    public CriarJogoController(ListaJogosView listaJogosView) {
        this.listaJogosView = listaJogosView;
    }

    public List<String> getTimesParticipantes(int idCampeonato) throws SQLException {
        List<Time> times;
        CampeonatoTimeDAO campeonatoTimeDAO = new CampeonatoTimeDAOImp();
        times = campeonatoTimeDAO.getTimesByCampeonato(idCampeonato);
        List<String> nomesTimes = new ArrayList<>();

        for (Time time : times) {
            nomesTimes.add(time.getNome());
        }

        return nomesTimes;
    }

    public void criarJogo(int idCampeonato, String timeMandante, String timeVisitante, String estadio, Date data, Date hora) throws SQLException {
        LocalDateTime dataHoraJogo = dateToLocalDateTime(data, hora);

        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        Campeonato campeonato = campeonatoDAO.get(idCampeonato);

        TimeDAO timeDAO = new TimeDAOImp();
        Time mandante = timeDAO.getNome(timeMandante);
        Time visitante = timeDAO.getNome(timeVisitante);

        if(Objects.equals(estadio, "Digite o Estádio...")){
            estadio = "A definir";
        }

        Jogo jogo = new Jogo(campeonato, mandante, visitante, dataHoraJogo, estadio);
        JogoDAO jogoDAO = new JogoDAOImp();
        jogoDAO.insert(jogo);

        listaJogosView.carregarListaDeJogos(idCampeonato);
    }

    public void atualizarJogo(Jogo jogo, Date data, Date hora, String estadio) throws SQLException {
        LocalDateTime dataHoraJogo = dateToLocalDateTime(data, hora);

        jogo.setDataJogo(dataHoraJogo);
        jogo.setEstadio(estadio);

        JogoDAO jogoDAO = new JogoDAOImp();
        jogoDAO.update(jogo);

        listaJogosView.carregarListaDeJogos(jogo.getCampeonato().getId());
    }

    public List<Date> localDateTimeToDate(LocalDateTime localDateTime) {
        Date data = Date.from(localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hora = Date.from(localDateTime.toLocalTime().atDate(LocalDate.of(1970, 1, 1)).atZone(ZoneId.systemDefault()).toInstant());
        List<Date> dataHora = new ArrayList<>();
        dataHora.add(data);
        dataHora.add(hora);
        return dataHora;
    }

    private static LocalDateTime dateToLocalDateTime(Date data, Date hora) {
        LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = hora.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return LocalDateTime.of(localDate, localTime);
    }

    public void excluirJogo(Jogo jogo) throws SQLException {
        JogoDAO jogoDAO = new JogoDAOImp();
        jogoDAO.delete(jogo);
        listaJogosView.carregarListaDeJogos(jogo.getCampeonato().getId());
    }
}
