package main.java.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.bll.ProductBll;
import main.java.connection.ConnectionFactory;
import main.java.dao.ProductDAO;
import main.java.model.Client;
import main.java.model.Product;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ViewProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textNameProd;
	private JTextField textQuantityProd;
	private JTextField textPrice;
	private JTable table;
	private ProductBll productBll;
	private ProductDAO productDAO;

	Connection conn = null;
	private JComboBox comboBox_ProdusS;
	
		public ViewProduct() {
		conn = ConnectionFactory.getConnection();

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		productBll = new ProductBll();
		setBounds(100, 100, 627, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdaugareProdus = new JLabel("Adaugare Produs");
		lblAdaugareProdus.setBounds(65, 23, 121, 16);
		contentPane.add(lblAdaugareProdus);
		
		JLabel lblNume = new JLabel("nume");
		lblNume.setBounds(26, 84, 56, 16);
		contentPane.add(lblNume);
		
		JLabel lblNewLabel = new JLabel("cantitate");
		lblNewLabel.setBounds(26, 143, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblPretbuc = new JLabel("Pret/buc");
		lblPretbuc.setBounds(26, 194, 56, 16);
		contentPane.add(lblPretbuc);
		
		textNameProd = new JTextField();
		textNameProd.setBounds(89, 81, 116, 22);
		contentPane.add(textNameProd);
		textNameProd.setColumns(10);
		
		textQuantityProd = new JTextField();
		textQuantityProd.setBounds(89, 140, 116, 22);
		contentPane.add(textQuantityProd);
		textQuantityProd.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(89, 191, 116, 22);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JButton btnAdaugareProdus = new JButton("Adauga");
		btnAdaugareProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nume = textNameProd.getText().toString();
				int cantitate = Integer.parseInt(textQuantityProd.getText().toString());
				int price = Integer.parseInt(textPrice.getText().toString());
				Product produs = new Product(nume,cantitate,price);
				productBll.insertProdus(produs);
				fillCBProdusS();
			}
		});
		btnAdaugareProdus.setBounds(50, 264, 143, 25);
		contentPane.add(btnAdaugareProdus);
		
		JButton btnNewButton = new JButton("Afisare produse existente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from Product";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception h) {
					h.printStackTrace();
				}
								
			}
		});
			
		btnNewButton.setBounds(370, 11, 194, 41);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 81, 224, 155);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox_ProdusS = new JComboBox();
		comboBox_ProdusS.setBounds(361, 278, 132, 25);
		contentPane.add(comboBox_ProdusS);
		fillCBProdusS();
		
		JButton btnStergereProdus = new JButton("Stergere");
		btnStergereProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String produs = getComboBox_ProdusS().getSelectedItem().toString();
				int idProdus = (Integer) productBll.getIdFromName(produs);
				productBll.deleteProduct(idProdus);
				fillCBProdusS();
			}
		});
		btnStergereProdus.setBounds(382, 328, 97, 25);
		contentPane.add(btnStergereProdus);
	}

		public void fillCBProdusS() {
			this.getComboBox_ProdusS().removeAllItems();
			List<Product> products = productBll.findAllProduct();
			for(int i = 0; i < products.size(); i++) {
				this.getComboBox_ProdusS().addItem((products.get(i).getName()));
			}
		}
		
		public JComboBox getComboBox_ProdusS() {
			return comboBox_ProdusS;
		}

		public void setComboBox_ProdusS(JComboBox comboBox_ProdusS) {
			this.comboBox_ProdusS = comboBox_ProdusS;
		}

}