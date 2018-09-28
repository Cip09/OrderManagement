package main.java.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import main.java.connection.ConnectionFactory;
import main.java.model.Client;

public class ClientDao extends AbstractDAO<Client> {

	
	private String createInsertQuery(Client t) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append("Client");
		sb.append(" (");
		for (int i = 0; i < Client.class.getDeclaredFields().length - 1; i++) {
			if (!Client.class.getDeclaredFields()[i].getName().equals("id")) {
				sb.append(Client.class.getDeclaredFields()[i].getName() + ", ");
			}
		}
		sb.append(Client.class.getDeclaredFields()[Client.class.getDeclaredFields().length - 1].getName());
		sb.append(") values ( ");
		try {
			Object value;

			for (Field field : Client.class.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getName() != "id") {
					if (!field.equals(Client.class.getDeclaredFields()[Client.class.getDeclaredFields().length - 1])) {
						value = field.get(t);
						sb.append("'" + value + "', ");
					} else {
						value = field.get(t);
						sb.append("'" + value + "'); ");
					}
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public List<Client> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "select * from " + "Client" + ";";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, Client.class.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public Client insert(Client t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, Client.class.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	
	public void delete(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "delete from " + Client.class.getSimpleName() + " where id =" + id + ";";
		//String query = "SET FOREIGN_KEY_CHECKS=0; \ndelete from " + Client.class.getSimpleName() + " where id =" + id + ";\nSET FOREIGN_KEY_CHECKS=1;";
		System.out.println(query);
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement(query);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(stm);
			ConnectionFactory.close(con);
		}
		//return 0;
	}
	
	
}

