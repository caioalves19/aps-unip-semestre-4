package dao;

import java.sql.SQLException;

import model.Time;

public interface TimeDAO extends DAO<Time>{
	Time getNome (String nome) throws SQLException;

}
