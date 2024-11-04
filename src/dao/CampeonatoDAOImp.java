package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Campeonato;

public class CampeonatoDAOImp implements CampeonatoDAO{

	@Override
	public Campeonato get(int id) throws SQLException {
		Campeonato campeonato = null;
		Connection con = Database.getConnection();

		String sql = "SELECT * FROM campeonato WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int idCampeonato = rs.getInt("id");
			String nome = rs.getString("nome");
			int ano = rs.getInt("ano");
			campeonato = new Campeonato(idCampeonato, nome, ano);
		}

		return campeonato;
	}

	@Override
	public List<Campeonato> getAll() throws SQLException {
		List<Campeonato> campeonatos = new ArrayList<Campeonato>();
		Connection con = Database.getConnection();
		String sql = "SELECT * FROM campeonato";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int idCampeonato = rs.getInt("id");
			String nome = rs.getString("nome");
			int ano = rs.getInt("ano");
			campeonatos.add(new Campeonato(idCampeonato, nome, ano));
		}
		return campeonatos;
	}

	@Override
	public int insert(Campeonato campeonato) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO campeonato (nome, ano) VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, campeonato.getNome());
		ps.setInt(2, campeonato.getAno());

		return ps.executeUpdate();
	}

	@Override
	public int update(Campeonato campeonato) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "UPDATE campeonato SET nome = ?, ano = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, campeonato.getNome());
		ps.setInt(2, campeonato.getAno());
		ps.setInt(3, campeonato.getId());
		return ps.executeUpdate();
	}

	@Override
	public int delete(Campeonato campeonato) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "DELETE FROM campeonato WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, campeonato.getId());

		return ps.executeUpdate();
	}

}
