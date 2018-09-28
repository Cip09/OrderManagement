package main.java.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.bll.ClientBll;
import main.java.bll.OrderBll;
import main.java.bll.ProductBll;
import main.java.connection.ConnectionFactory;
import main.java.model.Client;
import main.java.model.Orders;
import main.java.model.Product;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.sql.Connection;
import javax.swing.JScrollPane;

public class ViewOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textCantitate;
	private JComboBox comboBoxProdus;
	private JComboBox comboBoxClient;

	private OrderBll orderBll = new OrderBll();
	private ClientBll clientBll = new ClientBll();
	private ProductBll productBll = new ProductBll();
	private JTable table;

	Connection conn = null;
	public ViewOrder() {
		conn = ConnectionFactory.getConnection();
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdaugareComanda = new JLabel("Adaugare comanda");
		lblAdaugareComanda.setBounds(120, 13, 133, 16);
		contentPane.add(lblAdaugareComanda);

		JLabel lblNumeClient = new JLabel("Nume client");
		lblNumeClient.setBounds(12, 63, 88, 16);
		contentPane.add(lblNumeClient);

		comboBoxClient = new JComboBox();
		comboBoxClient.setBounds(137, 60, 88, 19);
		contentPane.add(comboBoxClient);

		JLabel lblNumeProdus = new JLabel("Nume produs");
		lblNumeProdus.setBounds(12, 118, 88, 16);
		contentPane.add(lblNumeProdus);

		comboBoxProdus = new JComboBox();
		comboBoxProdus.setBounds(137, 115, 88, 19);
		contentPane.add(comboBoxProdus);

		JLabel lblCantitate = new JLabel("Cantitate");
		lblCantitate.setBounds(12, 189, 88, 16);
		contentPane.add(lblCantitate);

		textCantitate = new JTextField();
		textCantitate.setBounds(137, 186, 88, 22);
		contentPane.add(textCantitate);
		textCantitate.setColumns(10);

		fillCbOrders();

		JButton btnAdaugareComanda = new JButton("Adaugare Comanda");
		btnAdaugareComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fillCbOrders();
				System.out.println("lalala");

				String client = getComboBoxClient().getSelectedItem().toString(); // int client =
																					// getComboBoxClient().getItemCount();
				int idClient = (Integer) clientBll.getIdFromName(client);
				String produs = getComboBoxProdus().getSelectedItem().toString(); //
				// int produs = getComboBoxProdus().getItemCount();

				int idProdus = (Integer) productBll.getIdFromName(produs);
				int cantitate = Integer.parseInt(textCantitate.getText().toString());
				Orders comanda = new Orders(1, idClient, idProdus, cantitate);
				orderBll.insertOrder(comanda);
			}
		});
		btnAdaugareComanda.setBounds(71, 253, 182, 44);
		contentPane.add(btnAdaugareComanda);
		
		JButton btnAfisareComenzi = new JButton("Afisare Comenzi");
		btnAfisareComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						String query = "select * from Orders";
						PreparedStatement pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch(Exception l) {
						l.printStackTrace();
					}
									
				}
				
			
		});
		btnAfisareComenzi.setBounds(436, 13, 191, 34);
		contentPane.add(btnAfisareComenzi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 119, 319, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JComboBox getComboBoxProdus() {
		return comboBoxProdus;
	}

	public void setComboBoxProdus(JComboBox comboBoxProdus) {
		this.comboBoxProdus = comboBoxProdus;
	}

	public JComboBox getComboBoxClient() {
		return comboBoxClient;
	}

	public void setComboBoxClient(JComboBox comboBoxClient) {
		this.comboBoxClient = comboBoxClient;
	}

	public void fillCbOrders() {
		this.getComboBoxClient().removeAllItems();
		this.getComboBoxProdus().removeAllItems();
		List<Client> clienti = clientBll.findAllClients();
		List<Product> products = productBll.findAllProduct();

		for (int i = 0; i < clienti.size(); i++) {
			this.getComboBoxClient().addItem((clienti.get(i).getName()));
		}

		for (int i = 0; i < products.size(); i++) {
			this.getComboBoxProdus().addItem((products.get(i).getName()));
		}
	}
}
