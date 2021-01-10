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

 

public class Purchase {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField Name;
	private JTextField Type;
	private JTextField Company_name;
	private JTextField Quantity;
	private JTextField Price;
	private JTextField Amount;
	private JButton btnDelete;
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
					Purchase window = new Purchase();
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
	public Purchase() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Got in class");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Purchases");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 20, 120, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 164, 568, 176);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		
		model = new DefaultTableModel();
		Object[] column = {"Id","Name", "Type", "Company","Quantity","Price","Amount"};
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
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(32, 112, 70, 15);
		frame.getContentPane().add(lblCompany);
		
		Company_name = new JTextField();
		Company_name.setBounds(135, 110, 150, 19);
		frame.getContentPane().add(Company_name);
		Company_name.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(330, 59, 91, 15);
		frame.getContentPane().add(lblQuantity);
		
		Quantity = new JTextField();
		Quantity.setBounds(433, 57, 150, 19);
		frame.getContentPane().add(Quantity);
		Quantity.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(330, 86, 91, 15);
		frame.getContentPane().add(lblPrice);
		
		Price = new JTextField();
		Price.setBounds(433, 84, 150, 19);
		frame.getContentPane().add(Price);
		Price.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(330, 112, 91, 15);
		frame.getContentPane().add(lblAmount);
		
		Amount = new JTextField();
		Amount.setBounds(433, 110, 150, 19);
		frame.getContentPane().add(Amount);
		Amount.setColumns(10);
		
		getData();
		
		//add
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Name.getText().equals("")||Type.getText().equals("")||Company_name.getText().equals("")||Quantity.getText().equals("")||Price.getText().equals("")||Amount.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",Name.getText(), Type.getText(), Company_name.getText(),Quantity.getText(),Price.getText(),Amount.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini", "root", "Manali@123");
						System.out.println("Connection established");
					
						PreparedStatement ps = con.prepareStatement("insert into Purchase(Name,Type,Company_name,Quantity,Price,Amount) values(?,?,?,?,?,?)");
						
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
					    ps.setString(5, data[5]);
					    ps.setString(6, data[6]);
						int count = ps.executeUpdate();
					
					Statement st = con.createStatement();
					String query = "select Id from Purchase where Name='"+data[1]+"'";
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
					Company_name.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
				}
			}
		});
		btnAdd.setBounds(330, 20, 70, 25);
		frame.getContentPane().add(btnAdd);
		
		//update
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String type = model.getValueAt(table.getSelectedRow(), 2).toString();
				String company_name = model.getValueAt(table.getSelectedRow(), 3).toString();
				String quantity = model.getValueAt(table.getSelectedRow(), 4).toString();
				String price = model.getValueAt(table.getSelectedRow(), 5).toString();
				String amount = model.getValueAt(table.getSelectedRow(), 6).toString();
				
				Name.setText(name);
				Type.setText(type);
				Company_name.setText(company_name);
				Quantity.setText(quantity);
				Price.setText(price);
				Amount.setText(amount);
			}
		});

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = Name.getText();
					String nType = Type.getText();
					String nCompany_name = Company_name.getText();
					String nQuantity = Quantity.getText();
					String nPrice= Price.getText();
					String nAmount = Amount.getText();

					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini", "root", "Manali@123");
						System.out.println("Connection established");
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Purchase set Name=?, Type=?, Company_name=?, Quantity=?, Price=?, Amount=? where Id=?");
					    ps.setString(1, nName);
					    ps.setString(2, nType);
					    ps.setString(3, nCompany_name);
					    ps.setString(4, nQuantity);
					    ps.setString(5, nPrice);
					    ps.setString(6, nAmount);
					    ps.setInt(7, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setValueAt(nName, table.getSelectedRow(), 1);
					model.setValueAt(nType, table.getSelectedRow(), 2);
					model.setValueAt(nCompany_name, table.getSelectedRow(), 3);
					model.setValueAt(nQuantity, table.getSelectedRow(), 4);
					model.setValueAt(nPrice, table.getSelectedRow(), 5);
					model.setValueAt(nAmount, table.getSelectedRow(), 6);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					Name.setText("");
					Type.setText("");
					Company_name.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
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
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini", "root", "Manali@123");
						System.out.println("Connection established");
						
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						System.out.println(x);
						PreparedStatement ps = con.prepareStatement("delete from Purchase where Id=?");
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
					Company_name.setText("");
					Quantity.setText("");
					Price.setText("");
					Amount.setText("");
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
		button.setBounds(470, 352, 140, 25);
		frame.getContentPane().add(button);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(27, 357, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(84, 352, 150, 25);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini", "root", "Manali@123");
			Statement st = con.createStatement();
			
			System.out.println("Connection established");
			ResultSet rs = st.executeQuery("select * from Purchase");
			
			String cols[] = new String[7];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Name");
				cols[2] = rs.getString("Type");
				cols[3] = rs.getString("Company_name");
				cols[4] = rs.getString("Quantity");
				cols[5] = rs.getString("Price");
				cols[6] = rs.getString("Amount");
//				 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
