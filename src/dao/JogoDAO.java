package dao;

import model.Jogo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface JogoDAO extends DAO<Jogo>{
    ArrayList<Jogo> getAllByCampeonato(int idCampeonato) throws SQLException;
	
}
