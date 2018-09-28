package main.java.bll;

import java.util.List;
import java.util.NoSuchElementException;

import main.java.dao.ProductDAO;
import main.java.model.Client;
import main.java.model.Product;

public class ProductBll {
	private ProductDAO productDao;

	public ProductBll() {
		this.productDao = new ProductDAO();
}
	public Product findById(int id) {
		Product product = null;
		product = productDao.findById(id);
		if (product == null) {
			throw new NoSuchElementException("The element with the id " + id + " doesnt exist!");
		}
		return product;
	}
	
	public List<Product> findAllProduct(){
		List<Product> st = productDao.findAll();
		if (st == null) {
			throw new NoSuchElementException("There are not clients into database");
		}
		return st;
	}
	
	public void insertProdus(Product p) {
		productDao.insert(p);
	}
	
	public int getIdFromName(String name) {
		return productDao.getIdFromName(name);
	}
	
	public void deleteProduct(int id) {
		
		productDao.delete(id);
	}
}
