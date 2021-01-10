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

 

public class Sale {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField Name;
	private JTextField Type;
	private JTextField Dose;
	private JButton btnDelete;
	private JLabel lblQuantity;
	private JTextField Quantity;
	private JLabel lblPrice;
	private JTextField Price;
	private JLabel lblAmount;
	private JLabel lblDate;
	private JTextField Amount;
	private JTextField Date;
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
					Sale window = new Sale();
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
	public Sale() throws Exception {
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
		frame.setBounds(100, 100, 622, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Sales");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setBounds(40, 20, 150, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 189, 568, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
	
		model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "Type", "Dose", "Quantity", "Price", "Amount", "Date"};
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
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(32, 86, 70, 15);
		frame.getContentPane().add(lblType);
		
		Type = new JTextField();
		Type.setBounds(135, 84, 150, 19);
		frame.getContentPane().add(Type);
		Type.setColumns(10);
		
		JLabel lblDose = new JLabel("Dose");
		lblDose.setBounds(32, 112, 91, 15);
		frame.getContentPane().add(lblDose);
		
		Dose = new JTextField();
		Dose.setBounds(135, 110, 150, 19);
		frame.getContentPane().add(Dose);
		Dose.setColumns(10);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(32, 139, 91, 15);
		frame.getContentPane().add(lblQuantity);
		
		Quantity = new JTextField();
		Quantity.setColumns(10);
		Quantity.setBounds(135, 141, 150, 19);
		frame.getContentPane().add(Quantity);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(341, 71, 70, 15);
		frame.getContentPane().add(lblPrice);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(403, 69, 150, 19);
		frame.getContentPane().add(Price);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setBounds(341, 98, 70, 15);
		frame.getContentPane().add(lblAmount);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(341, 125, 70, 15);
		frame.getContentPane().add(lblDate);
		
		Amount = new JTextField();
		Amount.setColumns(10);
		Amount.setBounds(403, 98, 150, 19);
		frame.getContentPane().add(Amount);
		
		Date = new JTextField();
		Date.setColumns(10);
		Date.setBounds(403, 125, 150, 19);
		PlaceHolder p=new PlaceHolder(Date,"yyyy-mm-dd");
		frame.getContentPane().add(Date);
		
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Name.getText().equals("")||Type.getText().equals("")||Dose.getText().equals("")||Quantity.getText().equals("")||Price.getText().equals("")||Amount.getText().equals("")||Date.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",Name.getText(), Type.getText(), Dose.getText(),Quantity.getText(),Price.getText(),Amount.getText(),Date.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");

						PreparedStatement ps = con.prepareStatement("insert into Sale(Name, Type, Dose, Quantity, Price, Amount, Date) values(?,?,?,?,?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
					    ps.setString(5, data[5]);
					    ps.setString(6, data[6]);
					    ps.setString(7, data[7]);
						int count = ps.executeUpdate();
						
						Statement st = con.createStatement();
						String query = "select Id from Sale where Name='"+data[1]+"'";
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
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");

				}
			}
		});
		btnAdd.setBounds(321, 20, 70, 25);
		frame.getContentPane().add(btnAdd);
		
		//update
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String type = model.getValueAt(table.getSelectedRow(), 2).toString();
				String dose = model.getValueAt(table.getSelectedRow(), 3).toString();
				String quantity = model.getValueAt(table.getSelectedRow(), 4).toString();
				String price = model.getValueAt(table.getSelectedRow(), 5).toString();
				String amount = model.getValueAt(table.getSelectedRow(), 6).toString();
				String date = model.getValueAt(table.getSelectedRow(), 7).toString();
				
				Name.setText(name);
				Type.setText(type);
				Dose.setText(dose);
				Quantity.setText(quantity);
				Price.setText(price);
				Amount.setText(amount);
				Date.setText(date);
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = Name.getText();
					String nType = Type.getText();
					String nDose = Dose.getText();
					String nQuantity = Quantity.getText();
					String nPrice = Price.getText();
					String nAmount = Amount.getText();
					String nDate = Date.getText();
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Sale set Name=?, Type=?, Dose=?, Quantity=?, Price=?, Amount=?, Date=? where Id=?");
					    ps.setString(1, nName);
					    ps.setString(2, nType);
					    ps.setString(3, nDose);
					    ps.setString(4, nQuantity);
					    ps.setString(5, nPrice);
					    ps.setString(6,nAmount);
					    ps.setString(7, nDate);
					    ps.setInt(8, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					model.setValueAt(nName, table.getSelectedRow(), 1);
					model.setValueAt(nType, table.getSelectedRow(), 2);
					model.setValueAt(nDose, table.getSelectedRow(), 3);
					model.setValueAt(nQuantity, table.getSelectedRow(), 4);
					model.setValueAt(nPrice, table.getSelectedRow(), 5);
					model.setValueAt(nAmount, table.getSelectedRow(), 6);
					model.setValueAt(nDate, table.getSelectedRow(), 7);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					Name.setText("");
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");

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
		btnUpdate.setBounds(403, 20, 88, 25);
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
						PreparedStatement ps = con.prepareStatement("delete from Sale where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					Name.setText("");
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");

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
		button.setBounds(470, 377, 140, 25);
		frame.getContentPane().add(button);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(27, 379, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(84, 374, 150, 25);
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
			ResultSet rs = st.executeQuery("select * from Sale");

		String cols[] = new String[8];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Name");
				cols[2] = rs.getString("Type");
				cols[3] = rs.getString("Dose");
				cols[4] = rs.getString("Quantity");
				cols[5] = rs.getString("Price");
				cols[6] = rs.getString("Amount");
				cols[7] = rs.getString("Date");
			 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}