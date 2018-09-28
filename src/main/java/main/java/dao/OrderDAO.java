package main.java.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import main.java.bll.ClientBll;
import main.java.connection.ConnectionFactory;
import main.java.model.Client;
//import main.java.model.Client;
import main.java.model.Orders;
import main.java.start.ViewOrder;

public class OrderDAO extends AbstractDAO<Orders> {

	private ClientDao clientDAO = new ClientDao();
	private ClientBll clientBll = new ClientBll();

	/*
	 * private String createInsertQuery(Orders t) { StringBuilder sb = new
	 * StringBuilder(); sb.append("insert into "); sb.append("Orders");
	 * sb.append(" ("); for (int i = 0; i < Orders.class.getDeclaredFields().length -
	 * 1; i++) { if (!Orders.class.getDeclaredFields()[i].getName().equals("id")) {
	 * sb.append(Orders.class.getDeclaredFields()[i].getName() + ", "); } }
	 * sb.append(Orders.class.getDeclaredFields()[Orders.class.getDeclaredFields().
	 * length - 1].getName()); sb.append(") values ( "); try { Object value;
	 * 
	 * for (Field field : Orders.class.getDeclaredFields()) {
	 * field.setAccessible(true); if (field.getName() != "id") { if
	 * (!field.equals(Orders.class.getDeclaredFields()[Orders.class.getDeclaredFields(
	 * ).length - 1])) { value = field.get(t); sb.append("'" + value + "', "); }
	 * else { value = field.get(t); sb.append("'" + value + "'); "); } } }
	 * 
	 * } catch (IllegalArgumentException e) { e.printStackTrace(); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } return sb.toString(); }
	 */

	public List<Orders> findAll() {
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
			LOGGER.log(Level.WARNING, Orders.class.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/*
	 * public Orders insert(Orders t) { Connection connection = null;
	 * PreparedStatement statement = null; ResultSet resultSet = null; String query
	 * = createInsertQuery(t);
	 * 
	 * try { connection = ConnectionFactory.getConnection(); statement =
	 * connection.prepareStatement(query); statement.execute(); } catch
	 * (SQLException e) { LOGGER.log(Level.WARNING, Orders.class.getName() +
	 * "DAO:findById " + e.getMessage()); } finally {
	 * ConnectionFactory.close(resultSet); ConnectionFactory.close(statement);
	 * ConnectionFactory.close(connection); } return t; / }
	 */

}
