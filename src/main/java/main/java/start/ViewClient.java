package main.java.start;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.bll.ClientBll;
import main.java.connection.ConnectionFactory;
import main.java.dao.ClientDao;
import main.java.model.Client;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ViewClient extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textMail;
	private JTextField textAge;
	private ClientBll clientBll;
	private JTable table;
	private ClientDao clientDAO;
	private List <Client> listaClienti;

	Connection conn = null;
	private JComboBox comboBox_ClientiS;
	public ViewClient() {
		conn = ConnectionFactory.getConnection();
		clientBll = new ClientBll();
		setBounds(100, 100, 741, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdaugareClient = new JLabel("           Adaugare client");
		lblAdaugareClient.setBounds(37, 13, 163, 35);
		contentPane.add(lblAdaugareClient);
		
		JLabel lblNume = new JLabel("nume");
		lblNume.setBounds(25, 82, 56, 16);
		contentPane.add(lblNume);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(25, 128, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblVarsta = new JLabel("varsta");
		lblVarsta.setBounds(25, 182, 56, 16);
		contentPane.add(lblVarsta);
		
		
		JButton btnRegister = new JButton("Inregistrare");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nume = textName.getText().toString();
				int age = Integer.parseInt(textAge.getText().toString());
				Client client = new Client(nume,age);
				clientBll.insertClient(client);
				fillCBClientS();
			}
			
		});
		btnRegister.setBounds(51, 270, 134, 41);
		contentPane.add(btnRegister);
		
		textName = new JTextField();
		textName.setBounds(84, 79, 116, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textMail = new JTextField();
		textMail.setText("");
		textMail.setBounds(84, 125, 116, 22);
		contentPane.add(textMail);
		textMail.setColumns(10);
		
		textAge = new JTextField();
		textAge.setBounds(84, 179, 116, 22);
		contentPane.add(textAge);
		textAge.setColumns(10);
		
		
		
		JButton btnAfisareClienti = new JButton("Afisare clienti");
		btnAfisareClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from Client";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					fillCBClientS();
				}catch(Exception e) {
					e.printStackTrace();
					
				}
								
			}
			
		});
		btnAfisareClienti.setBounds(451, 18, 140, 22);
		contentPane.add(btnAfisareClienti);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(376, 106, 305, 171);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox_ClientiS = new JComboBox();
		comboBox_ClientiS.setBounds(377, 315, 116, 27);
		contentPane.add(comboBox_ClientiS);
		
		fillCBClientS();
		
		JButton btnDelete = new JButton("Sterge");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String client = getComboBox_ClientiS().getSelectedItem().toString();
				int idClient = (Integer) clientBll.getIdFromName(client);
				clientBll.deleteClient(idClient);
				fillCBClientS();
			}
		});
		btnDelete.setBounds(389, 372, 97, 25);
		contentPane.add(btnDelete);
	}
	
	public void fillCBClientS() {
		this.getComboBox_ClientiS().removeAllItems();
		List<Client> clienti = clientBll.findAllClients();
		for (int i = 0; i < clienti.size(); i++) {
			this.getComboBox_ClientiS().addItem((clienti.get(i).getName()));
		}
		
	}
	public JComboBox getComboBox_ClientiS() {
		return comboBox_ClientiS;
	}
	public void setComboBox_ClientiS(JComboBox comboBox_ClientiS) {
		this.comboBox_ClientiS = comboBox_ClientiS;
	}
	
	void populare(ConnectionFactory c) {
		try {
			java.sql.Statement st = c.getConnection().createStatement();
			String query = "Select * from client";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("ID"));
				client.setName(rs.getString("nume"));
				client.setAge(rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void creareClient() {
		Object[] columName = new Object[3];
		columName[0] = "id";
		columName[1] = "nume";
		columName[2] = "age";
		Object[][] rowData = new Object[listaClienti.size()][3];
		Object[][] columnData = new Object[3][listaClienti.size()];
	
	for(int i = 0; i < listaClienti.size(); i++) {
		int aux = 0;
		for(Field field : listaClienti.get(i).getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(listaClienti.get(i));
				rowData[i][aux] = value;
				aux ++;
				
			}catch(IllegalArgumentException e){
					e.printStackTrace();
	
			}catch(IllegalAccessException l) {
				l.printStackTrace();
			}
		}
	}
	JTable table = new JTable(rowData, columnData);
	JScrollPane pane = new JScrollPane(table);
	JScrollPane panel = new JScrollPane(table);
	panel.setLayout(new BorderLayout());
	panel.add(pane, new BorderLayout().CENTER);
	this.setContentPane(panel);
	}
	
}
