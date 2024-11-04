package dao;

import model.Campeonato;
import model.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampeonatoTimeDAOImp implements CampeonatoTimeDAO{

    @Override
    public void addTimeToCampeonato(int idCampeonato, int idTime) throws SQLException {
        String sql = "INSERT INTO campeonato_time (id_campeonato, id_time) VALUES (?, ?)";
        Connection connection = Database.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCampeonato);
            ps.setInt(2, idTime);
            ps.executeUpdate();
        }
    }

    @Override
    public void removeTimeFromCampeonato(int idCampeonato, int idTime) throws SQLException {
        String sql = "DELETE FROM campeonato_time WHERE id_campeonato = ? AND id_time = ?";
        Connection connection = Database.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCampeonato);
            ps.setInt(2, idTime);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Time> getTimesByCampeonato(int idCampeonato) throws SQLException {
        List<Time> times = new ArrayList<>();
        String sql = "SELECT t.* FROM time t JOIN campeonato_time ct ON t.id = ct.id_time WHERE ct.id_campeonato = ? order by t.nome";
        Connection connection = Database.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCampeonato);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crie um objeto Time e adicione à lista
                Time time = new Time(rs.getInt("id"), rs.getString("nome")); // Exemplo de construtor
                times.add(time);
            }
        }
        return times;
    }

    @Override
    public ArrayList<Time> getAllTimesNaoParticipantes (int idCampeonato) throws SQLException {
        ArrayList<Time> times = new ArrayList<>();
        Connection connection = Database.getConnection();
        String sql = "SELECT t.* FROM time t LEFT JOIN campeonato_time ct ON t.id = ct.id_time AND ct.id_campeonato = ? WHERE ct.id_time IS NULL;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idCampeonato);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Time time = new Time(rs.getInt("id"), rs.getString("nome"));
            times.add(time);
        }
        return times;
    }
}
