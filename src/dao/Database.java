package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DATABASE = "apsunip";

    private Database() {
		
	}

	public static Connection getConnection() throws SQLException {
		Connection connection;
        final String USER = "root";
        final String PASS = "admin";
        final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
        connection = DriverManager.getConnection(URL, USER, PASS);
		return connection;
	}
	
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

	public static void closeStatement(Statement statement) throws SQLException {
		statement.close();
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.close();
	}

	public static void closeResultSet(ResultSet resultSet) throws SQLException {
		resultSet.close();
	}
}
