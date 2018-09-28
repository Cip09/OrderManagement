package main.java.bll;

import java.util.NoSuchElementException;

import main.java.dao.AbstractDAO;
import main.java.dao.ClientDao;
import main.java.dao.OrderDAO;
import main.java.model.Client;
import main.java.model.Orders;

public class OrderBll {
	private OrderDAO orderDAO;

	public OrderBll() {
		orderDAO = new OrderDAO();
	}

	public Orders findOrderById(int id) {
		Orders st = orderDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The Orders with id =" + id + " was not found!");
		}
		return st;
	}

	public void insertOrder(Orders c) {
		orderDAO.insert(c);
	}

}
