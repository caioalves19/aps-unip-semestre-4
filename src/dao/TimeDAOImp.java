package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Time;

public class TimeDAOImp implements TimeDAO{

	@Override
	public Time get(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = Database.getConnection();
		Time time = null;
		
		String sql = "SELECT id, nome FROM time WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int idTime = rs.getInt("id");
			String nome = rs.getString("nome");
			time = new Time(idTime, nome);
		}
		
		Database.closeResultSet(rs);
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return time;
	}

	@Override
	public List<Time> getAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = Database.getConnection();
		List<Time> listaTimes = new ArrayList<>();
		
		String sql = "SELECT id, nome FROM time";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int idTime = rs.getInt("id");
			String nome = rs.getString("nome");
			Time time = new Time(idTime, nome);
			listaTimes.add(time);
		}
		
		Database.closeResultSet(rs);
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return listaTimes;
	}

	@Override
	public int insert(Time time) throws SQLException {
		Connection con = Database.getConnection();
		
		String sql = "INSERT INTO time VALUES (default, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, time.getNome());
		
		int result = ps.executeUpdate();
		
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return result;
	}

	@Override
	public int update(Time time) throws SQLException {
		Connection con = Database.getConnection();
		
		String sql = "UPDATE time SET nome=? WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, time.getNome());
		ps.setInt(2, time.getId());
		
		int result = ps.executeUpdate();
		
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return result;
	}

	@Override
	public int delete(Time time) throws SQLException {
		Connection con = Database.getConnection();
		
		String sql = "DELETE FROM time WHERE id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, time.getId());
		
		int result = ps.executeUpdate();
		
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return result;
	}

	@Override
	public Time getNome(String nome) throws SQLException {
		Time time = null;
		
		Connection con = Database.getConnection();
		
		String sql = "SELECT id, nome FROM time WHERE nome = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nome);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int idTime = rs.getInt("id");
			String nomeTime = rs.getString("nome");
			time = new Time(idTime, nomeTime);
		}
		
		Database.closeResultSet(rs);
		Database.closePreparedStatement(ps);
		Database.closeConnection(con);
		
		return time;
	}

}
