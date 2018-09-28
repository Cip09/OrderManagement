package main.java.model;

public class Orders {
	public int id;
	public int idClient;
	public int idProduct;
	public int cantitate;
	
	public Orders() {
		
	}
	
	public Orders(int idOrder, int idClient, int idProduct, int cantitate) {
		super();
		//this.idOrder = idOrder;
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.cantitate = cantitate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	
	
}
