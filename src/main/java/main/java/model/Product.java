package main.java.model;

public class Product {
	public int id;
	public String  name;
	public int quantity;
	public int pricePerUnit;

	public Product() {
		
	}
	
	public Product(int id, String name, int quantity, int pricePerUnit) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}
	
	public Product(String name, int quantity, int pricePerUnit) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	
}
