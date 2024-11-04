package dao;

import model.Time;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CampeonatoTimeDAO {

    void addTimeToCampeonato(int idCampeonato, int idTime) throws SQLException;

    void removeTimeFromCampeonato(int idCampeonato, int idTime) throws SQLException;

    List<Time> getTimesByCampeonato(int idCampeonato) throws SQLException;

    ArrayList<Time> getAllTimesNaoParticipantes(int idCampeonato) throws SQLException;
}
