package pages;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Invoice {

	public JFrame frame;
	private JTextField txtCustomer;
	private JTextField txtMedicines;
	private JTable table;
	DefaultTableModel model;
	private JTextField txtAmt;
	private JTextField txtBill;
	private JTextField searchfield;
	private TableRowSorter sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice window = new Invoice();
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
	public Invoice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 564, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInvoice = new JLabel("Invoice");
		 lblInvoice.setFont(new Font("Arial", Font.BOLD, 20));
			lblInvoice.setBounds(39, 12, 120, 20);
		frame.getContentPane().add(lblInvoice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 193, 540, 129);
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
		Object[] column = {"ID", "Customer Name", "Medicines", "Total Amt", "Bill Id"};
//		Object[][] row ={{"1", "David Louis", "ABC", "10000"}, {"2", "Sam Celvin", "XYZ", "50000"}};
		model.setColumnIdentifiers(column);
//		for(int i=0; i<2; i++) {
//			model.addRow(row[i]);
//		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(25, 57, 112, 15);
		frame.getContentPane().add(lblCustomerName);
		
		txtCustomer = new JTextField();
		txtCustomer.setBounds(155, 52, 123, 26);
		frame.getContentPane().add(txtCustomer);
		txtCustomer.setColumns(10);
		
		JLabel lblMedicines = new JLabel("Medication");
		lblMedicines.setBounds(25, 95, 99, 15);
		frame.getContentPane().add(lblMedicines);
		
		txtMedicines = new JTextField();
		txtMedicines.setBounds(155, 93, 123, 26);
		frame.getContentPane().add(txtMedicines);
		txtMedicines.setColumns(10);
		
		JLabel lblAmt = new JLabel("Amount");
		lblAmt.setBounds(337, 50, 70, 15);
		frame.getContentPane().add(lblAmt);
		
		txtAmt = new JTextField();
		txtAmt.setBounds(410, 48, 123, 26);
		frame.getContentPane().add(txtAmt);
		txtAmt.setColumns(10);
		
		JLabel lblBillId = new JLabel("Bill Id");
		lblBillId.setBounds(337, 95, 70, 15);
		frame.getContentPane().add(lblBillId);
		
		txtBill = new JTextField();
		txtBill.setBounds(410, 93, 123, 26);
		frame.getContentPane().add(txtBill);
		txtBill.setColumns(10);
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCustomer.getText().equals("")||txtMedicines.getText().equals("")||txtAmt.getText().equals("")||txtBill.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",txtCustomer.getText(), txtMedicines.getText(), txtAmt.getText(), txtBill.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						
						PreparedStatement ps = con.prepareStatement("insert into Invoice(Customer_name, Medication, Total_amount, Bill_copy) values(?,?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setDouble(3, Double.parseDouble(data[3]));
					    ps.setString(4, data[4]);
						int count = ps.executeUpdate();
						System.out.println(data[1]);
						Statement st = con.createStatement();
						String query = "select Id from Invoice where Customer_name='"+data[1]+"'";
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
					
					txtCustomer.setText("");
					txtMedicines.setText("");
					txtAmt.setText("");
					txtBill.setText("");
//					getData();
				}
			}
		});
		btnAdd.setBounds(115, 133, 117, 25);
		frame.getContentPane().add(btnAdd);
		
//		*************************************************UPDATE*************************************************
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String customer = model.getValueAt(table.getSelectedRow(), 1).toString();
				String medication = model.getValueAt(table.getSelectedRow(), 2).toString();
				String amt = model.getValueAt(table.getSelectedRow(), 3).toString();
				String bill = model.getValueAt(table.getSelectedRow(), 4).toString();
				
				txtCustomer.setText(customer);
				txtMedicines.setText(medication);
				txtAmt.setText(amt);
				txtBill.setText(bill);
			}
		});
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nCustomer = txtCustomer.getText();
					String nMedicines = txtMedicines.getText();
					String nAmt = txtAmt.getText();
					String nBill = txtBill.getText();
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Invoice set Customer_name=?, Medication=?, Total_amount=?, Bill_copy=? where Id=?");
					    ps.setString(1, nCustomer);
					    ps.setString(2, nMedicines);
					    ps.setDouble(3, Double.parseDouble(nAmt));
					    ps.setString(4, nBill);
					    ps.setInt(5, x);
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
					model.setValueAt(nCustomer, table.getSelectedRow(), 1);
					model.setValueAt(nMedicines, table.getSelectedRow(), 2);
					model.setValueAt(nAmt, table.getSelectedRow(), 3);
					model.setValueAt(nBill, table.getSelectedRow(), 4);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					txtCustomer.setText("");
					txtMedicines.setText("");
					txtAmt.setText("");
					txtBill.setText("");
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
		btnUpdate.setBounds(244, 133, 117, 25);
		frame.getContentPane().add(btnUpdate);
		
//		*************************************************DELETE*************************************************
		JButton btnDelete = new JButton("DELETE");
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
						PreparedStatement ps = con.prepareStatement("delete from Invoice where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					txtCustomer.setText("");
					txtMedicines.setText("");
					txtAmt.setText("");
					txtBill.setText("");
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
		btnDelete.setBounds(373, 133, 117, 25);
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
		button.setBounds(412, 330, 140, 25);
		frame.getContentPane().add(button);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(22, 335, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(79, 330, 150, 25);
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
	private void getData() {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
			Statement st = con.createStatement();
			
			System.out.println("Connection established");
			ResultSet rs = st.executeQuery("select * from Invoice");
//			ResultSet rs = st.executeQuery("select * from Departments");
//			int result = rs.executeUpdate();
			
			
			String cols[] = new String[5];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Customer_name");
				cols[2] = rs.getString("Medication");
				cols[3] = Double.toString(rs.getDouble("Total_amount"));
				cols[4] = rs.getString("Bill_copy");
//				 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
