package pages;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

 

public class Customers {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JLabel lblPersonalCare;
	private JTextField compName;
	private JTextField compAddress;
	private JTextField compContact;
	private JButton btnDelete;
	private JTextField searchfield;
	private JLabel lblSearch;
	private TableRowSorter sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customers window = new Customers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Customers() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Got in class");
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Customers");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setBounds(40, 20, 150, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 166, 568, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
//		frame.getContentPane().add(table);
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
//		table.setBounds(22, 53, 406, 152);
		
		
//		frame.getContentPane().add(table);
		model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "Address", "Contact"};
//		Object[][] row ={{"1", "David Louis", "ABC", "10000"}, {"2", "Sam Celvin", "XYZ", "50000"}};
		model.setColumnIdentifiers(column);
//		for(int i=0; i<2; i++) {
//			model.addRow(row[i]);
//		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(32, 59, 70, 15);
		frame.getContentPane().add(lblName);
		
		compName = new JTextField();
		compName.setBounds(135, 57, 150, 19);
		frame.getContentPane().add(compName);
		compName.setColumns(10);
		
		JLabel lblCompAddress = new JLabel("Address");
		lblCompAddress.setBounds(32, 86, 70, 15);
		frame.getContentPane().add(lblCompAddress);
		
		compAddress = new JTextField();
		compAddress.setBounds(135, 84, 150, 19);
		frame.getContentPane().add(compAddress);
		compAddress.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact No.");
		lblContact.setBounds(32, 112, 91, 15);
		frame.getContentPane().add(lblContact);
		
		compContact = new JTextField();
		compContact.setBounds(135, 110, 150, 19);
		frame.getContentPane().add(compContact);
		compContact.setColumns(10);
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(compName.getText().equals("")||compAddress.getText().equals("")||compContact.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",compName.getText(), compAddress.getText(), compContact.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						
						PreparedStatement ps = con.prepareStatement("insert into Company(Name, Address, Phone) values(?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
						int count = ps.executeUpdate();
						
						Statement st = con.createStatement();
						String query = "select Id from Company where Name='"+data[1]+"'";
						ResultSet rs = st.executeQuery(query);
						int x=0 ;
						while(rs.next())
						{
							 x = rs.getInt("Id");
						}
						
						data[0] = Integer.toString(x);
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.addRow(data);
					JOptionPane.showMessageDialog(frame, "Data added successfully!!");
					
					compName.setText("");
					compAddress.setText("");
					compContact.setText("");
//					getData();
				}
			}
		});
		btnAdd.setBounds(330, 81, 70, 25);
		frame.getContentPane().add(btnAdd);
		
//		*************************************************UPDATE*************************************************
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String comp_name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String comp_address = model.getValueAt(table.getSelectedRow(), 2).toString();
				String comp_phone = model.getValueAt(table.getSelectedRow(), 3).toString();
				
				compName.setText(comp_name);
				compAddress.setText(comp_address);
				compContact.setText(comp_phone);
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = compName.getText();
					String nAddress = compAddress.getText();
					String nContact = compContact.getText();
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Company set Name=?, Address=?, Phone=? where Id=?");
					    ps.setString(1, nName);
					    ps.setString(2, nAddress);
					    ps.setString(3, nContact);
					    ps.setInt(4, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					model.addRow(new Object[] {nName, nAddress, nContact});
//					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
//					
//					compName.setText("");
//					compAddress.setText("");
//					compContact.setText("");
//					getData();
					model.setValueAt(nName, table.getSelectedRow(), 1);
					model.setValueAt(nAddress, table.getSelectedRow(), 2);
					model.setValueAt(nContact, table.getSelectedRow(), 3);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					compName.setText("");
					compAddress.setText("");
					compContact.setText("");
				}
				else {
					if(table.getSelectedRowCount()==0) {
						JOptionPane.showMessageDialog(frame, "Table is empty");
					}
					else
						JOptionPane.showMessageDialog(frame, "Select a row to update");
				}
			}
		});
		btnUpdate.setBounds(412, 81, 88, 25);
		frame.getContentPane().add(btnUpdate);
		
//		*************************************************DELETE*************************************************
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRowCount()==1) {
					
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						System.out.println(x);
						PreparedStatement ps = con.prepareStatement("delete from Company where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					compName.setText("");
					compAddress.setText("");
					compContact.setText("");
				}
					
				else {
					if(table.getSelectedRowCount()==0) {
						JOptionPane.showMessageDialog(frame, "Table is empty");
					}
					else
						JOptionPane.showMessageDialog(frame, "Select a row to delete");
				}
			}
		});
		btnDelete.setBounds(512, 81, 91, 25);
		frame.getContentPane().add(btnDelete);
		
		JButton button = new JButton("<- Home Page");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard.u=1;
				Dashboard db = new Dashboard();
				db.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setBounds(455, 354, 140, 25);
		frame.getContentPane().add(button);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(97, 354, 150, 25);
		frame.getContentPane().add(searchfield);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(40, 359, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield.getDocument().addDocumentListener(new DocumentListener() {
	         @Override
	         public void insertUpdate(DocumentEvent e) {
	            search(searchfield.getText());
	         }
	         @Override
	         public void removeUpdate(DocumentEvent e) {
	            search(searchfield.getText());
	         }
	         @Override
	         public void changedUpdate(DocumentEvent e) {
	            search(searchfield.getText());
	         }
	         public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
		
	}

//	*************************************************DISPLAY*************************************************
	public void getData() {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
			Statement st = con.createStatement();
			
			System.out.println("Connection established");
			ResultSet rs = st.executeQuery("select * from Company");
//			ResultSet rs = st.executeQuery("select * from Departments");
//			int result = rs.executeUpdate();
			
			
			String cols[] = new String[4];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Name");
				cols[2] = rs.getString("Address");
				cols[3] = rs.getString("Phone");
//				 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
