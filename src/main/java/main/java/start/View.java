package main.java.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class View extends JFrame {
	
	private JPanel contentPane;
	ViewOrder viewOrd = new ViewOrder();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMeniuClienti = new JButton("Meniu Clienti");
		btnMeniuClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewClient MeniuClienti = new ViewClient();
				MeniuClienti.setVisible(true);
							}
		});
		btnMeniuClienti.setBounds(144, 13, 156, 49);
		contentPane.add(btnMeniuClienti);
		
		JButton btnMeniuProduse = new JButton("Meniu Produse");
		btnMeniuProduse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewProduct MeniuProd = new ViewProduct();
				MeniuProd.setVisible(true);
			}
		});
		btnMeniuProduse.setBounds(144, 83, 156, 49);
		contentPane.add(btnMeniuProduse);
		
		JButton btnNewButton = new JButton("Meniu Comenzi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewOrder MeniuProduse = new ViewOrder();
				MeniuProduse.setVisible(true);
				//viewOrd.fillCbOrders();
			}
		});
		btnNewButton.setBounds(150, 157, 150, 49);
		contentPane.add(btnNewButton);
	}
}
