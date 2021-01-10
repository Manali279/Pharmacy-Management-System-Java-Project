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
import com.placeholder.PlaceHolder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

 

public class Users {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField Name;
	private JTextField Dob;
	private JTextField Address;
	private JButton btnDelete;
	private JLabel lblPhone;
	private JLabel lblSalary;
	private JLabel lblPassword;
	private JTextField Phone;
	private JTextField Salary;
	private JTextField Password;
	private JButton button;
	private JLabel lblSearch;
	private JTextField searchfield;
	private TableRowSorter sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users window = new Users();
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
	public Users() throws Exception {
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
		frame.setBounds(100, 100, 622, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Users");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setBounds(40, 20, 150, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 166, 568, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();

		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		
		model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "DOB", "Address","Phone","Salary","Password"};
		model.setColumnIdentifiers(column);

		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(32, 59, 70, 15);
		frame.getContentPane().add(lblName);
		
		Name = new JTextField();
		Name.setBounds(135, 57, 150, 19);
		frame.getContentPane().add(Name);
		Name.setColumns(10);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(32, 86, 70, 15);
		frame.getContentPane().add(lblDob);
		
		Dob = new JTextField();
		Dob.setBounds(135, 84, 150, 19);
		PlaceHolder p=new PlaceHolder(Dob,"yyyy-mm-dd");
		frame.getContentPane().add(Dob);
		Dob.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(32, 112, 91, 15);
		frame.getContentPane().add(lblAddress);
		
		Address = new JTextField();
		Address.setBounds(135, 110, 150, 19);
		frame.getContentPane().add(Address);
		Address.setColumns(10);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(326, 56, 70, 20);
		frame.getContentPane().add(lblPhone);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(326, 86, 70, 15);
		frame.getContentPane().add(lblSalary);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(326, 112, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(418, 57, 150, 19);
		frame.getContentPane().add(Phone);
		
		Salary = new JTextField();
		Salary.setColumns(10);
		Salary.setBounds(418, 84, 150, 19);
		frame.getContentPane().add(Salary);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(418, 110, 150, 19);
		frame.getContentPane().add(Password);
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Name.getText().equals("")||Dob.getText().equals("")||Address.getText().equals("")||Phone.getText().equals("")||Salary.getText().equals("")||Password.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",Name.getText(), Dob.getText(), Address.getText(), Phone.getText(), Salary.getText(), Password.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
						PreparedStatement ps = con.prepareStatement("insert into Users(Name, DOB, Address, Phone, Salary, Password) values(?,?,?,?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
					    ps.setString(5, data[5]);
					    ps.setString(6, data[6]);
						int count = ps.executeUpdate();
						
						Statement st = con.createStatement();
						String query = "select Id from Users where Name='"+data[1]+"'";
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
					
					Name.setText("");
					Dob.setText("");
					Address.setText("");
					Phone.setText("");
					Salary.setText("");
					Password.setText("");

				}
			}
		});
		btnAdd.setBounds(326, 20, 70, 25);
		frame.getContentPane().add(btnAdd);
		
//		*************************************************UPDATE*************************************************
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String dob = model.getValueAt(table.getSelectedRow(), 2).toString();
				String address = model.getValueAt(table.getSelectedRow(), 3).toString();
				String phone = model.getValueAt(table.getSelectedRow(), 4).toString();
				String salary = model.getValueAt(table.getSelectedRow(), 5).toString();
				String password = model.getValueAt(table.getSelectedRow(), 6).toString();
				
				Name.setText(name);
				Dob.setText(dob);
				Address.setText(address);
				Phone.setText(phone);
				Salary.setText(salary);
				Password.setText(password);
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = Name.getText();
					String nDob = Dob.getText();
					String nAddress = Address.getText();
					String nPhone = Phone.getText();
					String nSalary = Salary.getText();
					String nPassword = Password.getText();
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Users set Name=?, DOB=?, Address=?, Phone=?, Salary=?, Password=? where Id=?");
					    ps.setString(1, nName);
					    ps.setString(2, nDob);
					    ps.setString(3, nAddress);
					    ps.setString(4, nPhone);
					    ps.setString(5, nSalary);
					    ps.setString(6, nPassword);
					    ps.setInt(7, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					model.setValueAt(nName, table.getSelectedRow(), 1);
					model.setValueAt(nDob, table.getSelectedRow(), 2);
					model.setValueAt(nAddress, table.getSelectedRow(), 3);
					model.setValueAt(nPhone, table.getSelectedRow(), 4);
					model.setValueAt(nSalary, table.getSelectedRow(), 5);
					model.setValueAt(nPassword, table.getSelectedRow(), 6);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					Name.setText("");
					Dob.setText("");
					Address.setText("");
					Phone.setText("");
					Salary.setText("");
					Password.setText("");
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
		btnUpdate.setBounds(408, 20, 88, 25);
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
						
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						System.out.println(x);
						PreparedStatement ps = con.prepareStatement("delete from Users where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					Name.setText("");
					Dob.setText("");
					Address.setText("");
					Phone.setText("");
					Salary.setText("");
					Password.setText("");
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
		btnDelete.setBounds(504, 20, 91, 25);
		frame.getContentPane().add(btnDelete);
		
		button = new JButton("<- Home Page");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard.u=1;
				Dashboard db = new Dashboard();
				db.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setBounds(470, 354, 140, 25);
		frame.getContentPane().add(button);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(27, 359, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(84, 354, 150, 25);
		frame.getContentPane().add(searchfield);
		
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
			ResultSet rs = st.executeQuery("select * from Users");
			
			String cols[] = new String[7];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Name");
				cols[2] = rs.getString("DOB");
				cols[3] = rs.getString("Address");
				cols[4] = rs.getString("Phone");
				cols[5] = rs.getString("Salary");
				cols[6] = rs.getString("Password");
		 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}