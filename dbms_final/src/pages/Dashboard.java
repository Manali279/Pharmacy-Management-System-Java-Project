package pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;
import pages.*;
//import sun.java2d.Disposer;

public class Dashboard {

	public JFrame frame;
	String name;
public static int f=0,u=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard(String x) {
		name=x;
		initialize();
	}
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Got in class");
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 567, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPharmacySystem = new JLabel("PHARMACY SYSTEM");
		lblPharmacySystem.setForeground(Color.WHITE);
		lblPharmacySystem.setFont(new Font("Dialog", Font.BOLD, 24));
		lblPharmacySystem.setBackground(Color.WHITE);
		lblPharmacySystem.setBounds(145, 35, 287, 21);
		frame.getContentPane().add(lblPharmacySystem);
		
		JButton btnNewButton = new JButton("Customers");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customers customer;
				try {
					customer = new Customers();
					customer.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(413, 124, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDrugs = new JButton("Medicines");
		btnDrugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Medicines drug = new Medicines();
				drug.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnDrugs.setBounds(413, 161, 117, 25);
		frame.getContentPane().add(btnDrugs);
		
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				History history;
				try {
					history = new History();
					history.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnHistory.setBounds(413, 198, 117, 25);
		frame.getContentPane().add(btnHistory);
		
		System.out.println(name);
		JLabel lblHello = new JLabel("Hello, "+ name);
		lblHello.setForeground(Color.WHITE);
		lblHello.setBounds(30, 91, 152, 15);
		frame.getContentPane().add(lblHello);
		
		
		
		JButton btnNewButton_1 = new JButton("Expiry");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Expiry exp;
				try {
					exp = new Expiry();
					exp.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(413, 235, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Purchase purchase;
				try {
					purchase = new Purchase();
					purchase.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnPurchase.setBounds(413, 272, 117, 25);
		frame.getContentPane().add(btnPurchase);
		
		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sale sale;
				try {
					sale = new Sale();
					sale.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSale.setBounds(413, 309, 117, 25);
		frame.getContentPane().add(btnSale);
		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Invoice invoice = new Invoice();
				invoice.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnInvoice.setBounds(413, 346, 117, 25);
		frame.getContentPane().add(btnInvoice);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users user;
				try {
					user = new Users();
					user.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(413, 88, 117, 25);
		if(f==1) {
			frame.getContentPane().add(btnAddUser);
		}
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/manali/Downloads/pharmfinal.png"));
		lblNewLabel.setBounds(0, -28, 567, 508);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Customers");
		btnNewButton_2.setBounds(187, 395, 117, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		if(f==1||u==1)
		{
			btnAddUser.setVisible(true);
		}
		else
		{
			btnAddUser.setVisible(false);
		}
		if(u==1)
			lblHello.setVisible(false);

	}
	public void getname(String x) {
		name=x;
	}
}
