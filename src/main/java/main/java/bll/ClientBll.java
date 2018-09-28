package main.java.bll;

import java.util.List;
import java.util.NoSuchElementException;


import main.java.dao.ClientDao;
import main.java.model.Client;


public class ClientBll {

	private ClientDao clientDao;

	public ClientBll() {
		clientDao = new ClientDao();
	}

	public Client findClientById(int id) {
		Client st = clientDao.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}
	
	public List<Client> findAllClients(){
		List<Client> st = clientDao.findAll();
		if (st == null) {
			throw new NoSuchElementException("There are not clients into database");
		}
		return st;
		
	}
	
	public void insertClient(Client c) {
		clientDao.insert(c);
	}
	

	public int getIdFromName(String name) {
		return clientDao.getIdFromName(name);
	}
	
	public void deleteClient(int id) {
		//int id = clientDao.getIdFromName(name);
		clientDao.delete(id);
	}

}
