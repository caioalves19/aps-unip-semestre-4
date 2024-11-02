package dao;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Campeonato;
import model.Jogo;
import model.Time;

public class JogoDAOImp implements JogoDAO {

    @Override
    public Jogo get(int id) throws SQLException {
        Jogo jogo = null;

        Connection con = Database.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM jogo WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            jogo = criarJogo(rs);
        }

        return jogo;
    }

    @Override
    public List<Jogo> getAll() throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM jogo");
        ResultSet rs = ps.executeQuery();

        List<Jogo> jogos = new ArrayList<Jogo>();
        while (rs.next()) {
            Jogo jogo = criarJogo(rs);
            jogos.add(jogo);
        }
        return jogos;
    }

    @Override
    public int insert(Jogo jogo) throws SQLException {
        Connection con = Database.getConnection();
        String insert = "INSERT INTO jogo (campeonato, time_mandante, time_visitante, data_hora, estadio) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, jogo.getCampeonato().getId());
        ps.setInt(2, jogo.getTimeMandante().getId());
        ps.setInt(3, jogo.getTimeVisitante().getId());
        ps.setTimestamp(4, Timestamp.valueOf(jogo.getDataJogo()));
        ps.setString(5, jogo.getEstadio());
        return ps.executeUpdate();
    }

    @Override
    public int update(Jogo jogo) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE jogo SET campeonato = ?, time_mandante = ?, time_visitante = ?, data_hora = ?, estadio = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, jogo.getCampeonato().getId());
        ps.setInt(2, jogo.getTimeMandante().getId());
        ps.setInt(3, jogo.getTimeVisitante().getId());
        ps.setTimestamp(4, Timestamp.valueOf(jogo.getDataJogo()));
        ps.setString(5, jogo.getEstadio());
        ps.setInt(6, jogo.getId());

        return ps.executeUpdate();
    }

    @Override
    public int delete(Jogo jogo) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "DELETE FROM jogo WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, jogo.getId());
        return ps.executeUpdate();
    }

    private static Jogo criarJogo(ResultSet rs) throws SQLException {
        Jogo jogo;
        int idJogo = rs.getInt("id");
        int idCampeonato = rs.getInt("campeonato");
        int time_mandante = rs.getInt("time_mandante");
        int time_visitante = rs.getInt("time_visitante");
        LocalDateTime data_hora = rs.getTimestamp("data_hora").toLocalDateTime();
        String estadio = rs.getString("estadio");

        CampeonatoDAO campeonatoDAO = new CampeonatoDAOImp();
        Campeonato campeonato = campeonatoDAO.get(idCampeonato);

        TimeDAO timeDAO = new TimeDAOImp();
        Time timeMandante = timeDAO.get(time_mandante);
        Time timeVisitante = timeDAO.get(time_visitante);

        jogo = new Jogo(idJogo, campeonato, timeMandante, timeVisitante, data_hora, estadio);
        return jogo;
    }

}
