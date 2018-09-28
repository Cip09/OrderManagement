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
//import main.java.model.Client;
import main.java.model.Product;

public class ProductDAO extends AbstractDAO<Product>{
	
	
	private String createInsertQuery(Product t) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append("Product");
		sb.append(" (");
		for (int i = 0; i < Product.class.getDeclaredFields().length - 1; i++) {
			if (!Product.class.getDeclaredFields()[i].getName().equals("id")) {
				sb.append(Product.class.getDeclaredFields()[i].getName() + ", ");
			}
		}
		sb.append(Product.class.getDeclaredFields()[Product.class.getDeclaredFields().length - 1].getName());
		sb.append(") values ( ");
		try {
			Object value;

			for (Field field : Product.class.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getName() != "id") {
					if (!field.equals(Product.class.getDeclaredFields()[Product.class.getDeclaredFields().length - 1])) {
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

	public List<Product> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "select * from " + "Product" + ";";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, Product.class.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public Product insert(Product t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, Product.class.getName() + "DAO:findById " + e.getMessage());
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
		String query = "delete from " + Product.class.getSimpleName() + " where id =" + id + ";";
		//String query = "SET FOREIGN_KEY_CHECKS=0; \ndelete from " + Product.class.getSimpleName() + " where id =" + id + ";\nSET FOREIGN_KEY_CHECKS=1;";
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
	}
	
}
