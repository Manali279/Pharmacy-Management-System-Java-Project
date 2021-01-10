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

 

public class History {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField Name;
	private JTextField Type;
	private JTextField Dose;
	private JTextField Quantity;
	private JTextField Price;
	private JTextField Amount;
	private JButton btnDelete;
	private JLabel lblUser;
	private JTextField User;
	private JTextField Date;
	private JTextField Time;
	private JLabel lblTime;
	private JLabel lblDate;
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
					History window = new History();
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
	public History() throws Exception {
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
		frame.setBounds(100, 100, 622, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("History");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 20, 120, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 235, 568, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);

		
		
		model = new DefaultTableModel();
		Object[] column = {"Id","User","Name", "Type", "Dose","Quantity","Price","Amount","Date","Time"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(32, 115, 70, 15);
		frame.getContentPane().add(lblName);
		
		Name = new JTextField();
		Name.setBounds(135, 113, 150, 19);
		frame.getContentPane().add(Name);
		Name.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(32, 146, 70, 15);
		frame.getContentPane().add(lblType);
		
		Type = new JTextField();
		Type.setBounds(135, 144, 150, 19);
		frame.getContentPane().add(Type);
		Type.setColumns(10);
		
		JLabel lblDose = new JLabel("Dose");
		lblDose.setBounds(32, 177, 70, 15);
		frame.getContentPane().add(lblDose);
		
		Dose = new JTextField();
		Dose.setBounds(135, 175, 150, 19);
		frame.getContentPane().add(Dose);
		Dose.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(330, 77, 91, 15);
		frame.getContentPane().add(lblQuantity);
		
		Quantity = new JTextField();
		Quantity.setBounds(433, 75, 150, 19);
		frame.getContentPane().add(Quantity);
		Quantity.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(330, 104, 91, 15);
		frame.getContentPane().add(lblPrice);
		
		Price = new JTextField();
		Price.setBounds(433, 102, 150, 19);
		frame.getContentPane().add(Price);
		Price.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(330, 131, 91, 15);
		frame.getContentPane().add(lblAmount);
		
		Amount = new JTextField();
		Amount.setBounds(433, 129, 150, 19);
		frame.getContentPane().add(Amount);
		Amount.setColumns(10);
		
		lblUser = new JLabel("User");
		lblUser.setBounds(32, 86, 70, 15);
		frame.getContentPane().add(lblUser);
		
		User = new JTextField();
		User.setColumns(10);
		User.setBounds(135, 84, 150, 19);
		frame.getContentPane().add(User);
		
		Date = new JTextField();
		Date.setColumns(10);
		Date.setBounds(433, 160, 150, 19);
		PlaceHolder p=new PlaceHolder(Date,"yyyy-mm-dd");
		frame.getContentPane().add(Date);
		
		Time = new JTextField();
		Time.setColumns(10);
		Time.setBounds(433, 191, 150, 19);
		frame.getContentPane().add(Time);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(330, 193, 91, 15);
		frame.getContentPane().add(lblTime);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(330, 162, 91, 15);
		frame.getContentPane().add(lblDate);
		
		getData();
		
		//add
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(User.getText().equals("")||Name.getText().equals("")||Type.getText().equals("")||Dose.getText().equals("")||Quantity.getText().equals("")||Price.getText().equals("")||Amount.getText().equals("")||Date.getText().equals("")||Time.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",User.getText(), Name.getText(), Type.getText(), Dose.getText(),Quantity.getText(),Price.getText(),Amount.getText(), Date.getText(), Time.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
					
						PreparedStatement ps = con.prepareStatement("insert into History(User_name,Name,Type,Dose,Quantity,Price,Amount,Date,Time) values(?,?,?,?,?,?,?,?,?)");
						
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
					    ps.setString(5, data[5]);
					    ps.setString(6, data[6]);
					    ps.setString(7, data[7]);
					    ps.setString(8, data[8]);
					    ps.setString(9, data[9]);
						int count = ps.executeUpdate();
					
					Statement st = con.createStatement();
					String query = "select Id from History where User_name='"+data[1]+"'";
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
					
					User.setText("");
					Name.setText("");
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");
					Time.setText("");
					
				}
			}
		});
		btnAdd.setBounds(330, 20, 70, 25);
		frame.getContentPane().add(btnAdd);

		//update
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String user = model.getValueAt(table.getSelectedRow(), 1).toString();
				String name = model.getValueAt(table.getSelectedRow(), 2).toString();
				String type = model.getValueAt(table.getSelectedRow(), 3).toString();
				String dose = model.getValueAt(table.getSelectedRow(), 4).toString();
				String quantity = model.getValueAt(table.getSelectedRow(), 5).toString();
				String price = model.getValueAt(table.getSelectedRow(), 6).toString();
				String amount = model.getValueAt(table.getSelectedRow(), 7).toString();
				String date = model.getValueAt(table.getSelectedRow(), 8).toString();
				String time = model.getValueAt(table.getSelectedRow(), 9).toString();
				
				User.setText(user);
				Name.setText(name);
				Type.setText(type);
				Dose.setText(dose);
				Quantity.setText(quantity);
				Price.setText(price);
				Amount.setText(amount);
				Date.setText(date);
				Time.setText(time);
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nUser=User.getText();
					String nName = Name.getText();
					String nType = Type.getText();
					String nDose = Dose.getText();
					String nQuantity = Quantity.getText();
					String nPrice= Price.getText();
					String nAmount = Amount.getText();
					String nDate = Date.getText();
					String nTime = Time.getText();

					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update History set User_name=?, Name=?, Type=?, Dose=?, Quantity=?, Price=?, Amount=?, Date=?, Time=? where Id=?");
						ps.setString(1, nUser);
						ps.setString(2, nName);
					    ps.setString(3, nType);
					    ps.setString(4, nDose);
					    ps.setString(5, nQuantity);
					    ps.setString(6, nPrice);
					    ps.setString(7, nAmount);
					    ps.setString(8, nDate);
					    ps.setString(9, nTime);
					    ps.setInt(10, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setValueAt(nUser, table.getSelectedRow(), 1);
					model.setValueAt(nName, table.getSelectedRow(), 2);
					model.setValueAt(nType, table.getSelectedRow(), 3);
					model.setValueAt(nDose, table.getSelectedRow(), 4);
					model.setValueAt(nQuantity, table.getSelectedRow(), 5);
					model.setValueAt(nPrice, table.getSelectedRow(), 6);
					model.setValueAt(nAmount, table.getSelectedRow(), 7);
					model.setValueAt(nDate, table.getSelectedRow(), 8);
					model.setValueAt(nTime, table.getSelectedRow(), 9);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					User.setText("");
					Name.setText("");
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");
					Time.setText("");
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
		btnUpdate.setBounds(412, 20, 88, 25);
		frame.getContentPane().add(btnUpdate);
		
		//delete
		
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
						PreparedStatement ps = con.prepareStatement("delete from History where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					User.setText("");
					Name.setText("");
					Type.setText("");
					Dose.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
					Date.setText("");
					Time.setText("");
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
		btnDelete.setBounds(512,20, 91, 25);
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
		button.setBounds(470, 427, 140, 25);
		frame.getContentPane().add(button);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(27, 428, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(84, 423, 150, 25);
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
	
	//get
	
	public void getData() {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
			Statement st = con.createStatement();
			
			System.out.println("Connection established");
			ResultSet rs = st.executeQuery("select * from History");
			
			String cols[] = new String[10];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("User_name");
				cols[2] = rs.getString("Name");
				cols[3] = rs.getString("Type");
				cols[4] = rs.getString("Dose");
				cols[5] = rs.getString("Quantity");
				cols[6] = rs.getString("Price");
				cols[7] = rs.getString("Amount");
				cols[8] = rs.getString("Date");
				cols[9] = rs.getString("Time");
//				 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}