package pages;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Medicines {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	DefaultTableModel model_1;
	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtDose;
	private JLabel lblCode;
	private JTextField txtCode;
	private JLabel lblCP;
	private JTextField txtCP;
	private JLabel lblSellingPrice;
	private JTextField txtSP;
	private JLabel lblExpiredQty;
	private JTextField txtExpiredQty;
	private JLabel lblCompanyName;
	private JTextField txtCompany;
	private JLabel lblProductDate;
	private JLabel lblExpiry;
	private JLabel lblPlace;
	private JTextField txtPlace;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JLabel lblType;
	java.util.Date date, date1, DATE;
	java.sql.Date sqldate, sqldate1;
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
					Medicines window = new Medicines();
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
	public Medicines(){
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
		frame.setBounds(100, 100, 961, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MEDICINES");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setBounds(39, 12, 170, 20);
		frame.getContentPane().add(lblNewLabel);
		
//		JLabel lblPersonalCare = new JLabel("Personal Care");
//		lblPersonalCare.setBounds(30, 56, 116, 15);
//		frame.getContentPane().add(lblPersonalCare);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 259, 916, 154);
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
		Object[] column = {"Id", "Name", "Type", "Dose", "Code", "Cost Price", "Selling Price", "Expiry", "Company", "Product Date", "Expiry Date", "Place", "Quantity"};
		Object[][] row = {{"1", "Cough Syrup", "200l", "12/21/2023"}, {"2", "Paracetamol", "500l", "5/2/2025"}};
		model.setColumnIdentifiers(column);

		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(98, 51, 70, 15);
		frame.getContentPane().add(lblName);
		
		JLabel lblType;
		lblType = new JLabel("Type");
		lblType.setBounds(98, 91, 70, 15);
		frame.getContentPane().add(lblType);
		
		txtName = new JTextField();
		txtName.setBounds(186, 45, 114, 27);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtType = new JTextField();
		txtType.setBounds(186, 85, 114, 27);
		frame.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		JLabel lblDose = new JLabel("Dose");
		lblDose.setBounds(98, 132, 70, 15);
		frame.getContentPane().add(lblDose);
		
		txtDose = new JTextField();
		txtDose.setBounds(186, 126, 114, 27);
		frame.getContentPane().add(txtDose);
		txtDose.setColumns(10);
		
		lblCode = new JLabel("Code");
		lblCode.setBounds(98, 174, 70, 15);
		frame.getContentPane().add(lblCode);
		
		txtCode = new JTextField();
		txtCode.setBounds(186, 168, 114, 27);
		frame.getContentPane().add(txtCode);
		txtCode.setColumns(10);
		
		lblCP = new JLabel("Cost Price");
		lblCP.setBounds(347, 51, 87, 15);
		frame.getContentPane().add(lblCP);
		
		txtCP = new JTextField();
		txtCP.setBounds(452, 45, 114, 27);
		frame.getContentPane().add(txtCP);
		txtCP.setColumns(10);
		
		lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setBounds(347, 91, 87, 15);
		frame.getContentPane().add(lblSellingPrice);
		
		txtSP = new JTextField();
		txtSP.setBounds(452, 85, 114, 27);
		frame.getContentPane().add(txtSP);
		txtSP.setColumns(10);
		
		lblExpiredQty = new JLabel("Expired Qty");
		lblExpiredQty.setBounds(347, 132, 87, 15);
		frame.getContentPane().add(lblExpiredQty);
		
		txtExpiredQty = new JTextField();
		txtExpiredQty.setBounds(452, 126, 114, 27);
		frame.getContentPane().add(txtExpiredQty);
		txtExpiredQty.setColumns(10);
		
		lblCompanyName = new JLabel("Company ");
		lblCompanyName.setBounds(347, 174, 87, 15);
		frame.getContentPane().add(lblCompanyName);
		
		txtCompany = new JTextField();
		txtCompany.setBounds(452, 168, 114, 27);
		frame.getContentPane().add(txtCompany);
		txtCompany.setColumns(10);
		
		lblProductDate = new JLabel("Product Date");
		lblProductDate.setBounds(627, 51, 104, 15);
		frame.getContentPane().add(lblProductDate);
		
		final JDateChooser dateProduct = new JDateChooser();
		dateProduct.setBounds(749, 47, 114, 27);
		frame.getContentPane().add(dateProduct);
		
		lblExpiry = new JLabel("Expiry Date");
		lblExpiry.setBounds(627, 91, 104, 15);
		frame.getContentPane().add(lblExpiry);
		
		final JDateChooser dateExpiry = new JDateChooser();
		dateExpiry.setBounds(749, 87, 114, 27);
		frame.getContentPane().add(dateExpiry);
		
		lblPlace = new JLabel("Place");
		lblPlace.setBounds(627, 132, 70, 15);
		frame.getContentPane().add(lblPlace);
		
		txtPlace = new JTextField();
		txtPlace.setBounds(749, 126, 114, 27);
		frame.getContentPane().add(txtPlace);
		txtPlace.setColumns(10);
		
		lblQuantity = new JLabel("Quatity");
		lblQuantity.setBounds(627, 174, 70, 15);
		frame.getContentPane().add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(749, 168, 114, 27);
		frame.getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtName.getText().equals("")||txtCode.getText().equals("")||txtCompany.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String pattern = "yyyy-MM-dd";
			        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
					String data[] = {"0",txtName.getText(), txtType.getText(), txtDose.getText(), txtCode.getText(), txtCP.getText(), txtSP.getText(), txtExpiredQty.getText(), txtCompany.getText(),  formatter.format(dateProduct.getDate()),  formatter.format(dateExpiry.getDate()), txtPlace.getText(), txtQuantity.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						
						date = dateProduct.getDate();
					    sqldate = new java.sql.Date(date.getTime());
					    date1 = dateExpiry.getDate();
					    sqldate1 = new java.sql.Date(date1.getTime());
//					    System.out.println(data);
//						System.out.println(sqldate);
					    
						PreparedStatement ps = con.prepareStatement("insert into Drugs(Name, Type, Dose, Code, Cost_price, Selling_price, Expiry, Company_name, Product_date, Expiration_date, Place, Quantity) values(?,?,?,?,?,?,?,?,?,?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
					    ps.setDouble(5, Double.parseDouble(data[5]));
					    ps.setDouble(6, Double.parseDouble(data[6]));
					    ps.setString(7, data[7]);
					    ps.setString(8, data[8]);
					    ps.setDate(9, sqldate);
					    ps.setDate(10, sqldate1);
					    ps.setString(11, data[11]);
					    ps.setInt(12, Integer.parseInt(data[12]));
					    
					    
					    
						int count = ps.executeUpdate();
						
						Statement st = con.createStatement();
						String query = "select Id from Drugs where Name='"+data[1]+"'";
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
					
					txtName.setText("");txtType.setText("");txtDose.setText("");txtCode.setText("");
					txtCP.setText("");txtSP.setText("");txtExpiredQty.setText("");txtCompany.setText("");
					txtPlace.setText("");txtQuantity.setText("");dateProduct.setDate(null);dateExpiry.setDate(null);
					
					
//					getData();
				}
			}
		});
		btnAdd.setBounds(237, 211, 117, 25);
		frame.getContentPane().add(btnAdd);
		
//		*************************************************UPDATE*************************************************
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String type = model.getValueAt(table.getSelectedRow(), 2).toString();
				String dose = model.getValueAt(table.getSelectedRow(), 3).toString();
				String code = model.getValueAt(table.getSelectedRow(), 4).toString();
				String cp = model.getValueAt(table.getSelectedRow(), 5).toString();
				String sp = model.getValueAt(table.getSelectedRow(), 6).toString();
				String expqty = model.getValueAt(table.getSelectedRow(), 7).toString();
       			String company = model.getValueAt(table.getSelectedRow(), 8).toString();
				String place = model.getValueAt(table.getSelectedRow(), 11).toString();
				String qty = model.getValueAt(table.getSelectedRow(), 12).toString();
				
				txtName.setText(name);txtType.setText(type);txtDose.setText(dose);txtCode.setText(code);txtPlace.setText(place);
				txtCP.setText(cp);txtSP.setText(sp);txtExpiredQty.setText(expqty);txtCompany.setText(company);txtQuantity.setText(qty);
				java.util.Date showDate1,showDate2;
				try {
					showDate1 = new SimpleDateFormat("dd-MMM-yyyy").parse((String)model.getValueAt(table.getSelectedRow(), 9));
					dateProduct.setDate(showDate1);
					showDate2 = new SimpleDateFormat("dd-MMM-yyyy").parse((String)model.getValueAt(table.getSelectedRow(), 10));
					dateExpiry.setDate(showDate2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = txtName.getText();String nType = txtType.getText();String nDose = txtDose.getText();String nCode= txtCode.getText();
					String nCp = txtCP.getText();String nSp = txtSP.getText();String nExpQty = txtExpiredQty.getText();String nCompany = txtCompany.getText();
					java.util.Date nProdDate = dateProduct.getDate();java.util.Date nExpDate = dateExpiry.getDate();String nPlace = txtPlace.getText();String nQty = txtQuantity.getText();
					
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						System.out.println(nProdDate);
						System.out.println(nExpDate);
						
						PreparedStatement ps = con.prepareStatement("update Drugs set Name=?, Type=?, Dose=?, Code=?, Cost_price=?, Selling_price=?, Expiry=?, Company_name=?, Product_date=?, Expiration_date=?, Place=?, Quantity=? where Id=?");
						ps.setString(1, nName);
					    ps.setString(2, nType);
					    ps.setString(3, nDose);
					    ps.setString(4, nCode);
					    ps.setDouble(5, Double.parseDouble(nCp));
					    ps.setDouble(6, Double.parseDouble(nSp));
					    ps.setString(7, nExpQty);
					    ps.setString(8, nCompany);
					    ps.setDate(9, new java.sql.Date(nProdDate.getTime()));
					    ps.setDate(10, new java.sql.Date(nExpDate.getTime()));
					    ps.setString(11, nPlace);
					    ps.setInt(12, Integer.parseInt(nQty));
					    ps.setInt(13, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String pattern = "dd-MMM-yyyy";
			        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
					model.setValueAt(nName, table.getSelectedRow(), 1);model.setValueAt(nType, table.getSelectedRow(), 2);model.setValueAt(nDose, table.getSelectedRow(), 3);
					model.setValueAt(nCode, table.getSelectedRow(), 4);model.setValueAt(nCp, table.getSelectedRow(), 5);model.setValueAt(nSp, table.getSelectedRow(), 6);
					model.setValueAt(nExpQty, table.getSelectedRow(), 7);model.setValueAt(nCompany, table.getSelectedRow(), 8);model.setValueAt(formatter.format(dateProduct.getDate()), table.getSelectedRow(), 9);
					model.setValueAt(formatter.format(dateExpiry.getDate()), table.getSelectedRow(), 10);model.setValueAt(nPlace, table.getSelectedRow(), 11);model.setValueAt(nQty, table.getSelectedRow(), 12);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					txtName.setText("");txtType.setText("");txtDose.setText("");txtCode.setText("");
					txtCP.setText("");txtSP.setText("");txtExpiredQty.setText("");txtCompany.setText("");
					txtPlace.setText("");txtQuantity.setText("");dateProduct.setDate(null);dateExpiry.setDate(null);
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
		btnUpdate.setBounds(413, 211, 117, 25);
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
						PreparedStatement ps = con.prepareStatement("delete from Drugs where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					txtName.setText("");txtType.setText("");txtDose.setText("");txtCode.setText("");
					txtCP.setText("");txtSP.setText("");txtExpiredQty.setText("");txtCompany.setText("");
					txtPlace.setText("");txtQuantity.setText("");dateProduct.setDate(null);dateExpiry.setDate(null);
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
		btnDelete.setBounds(580, 211, 117, 25);
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
		button.setBounds(809, 425, 140, 25);
		frame.getContentPane().add(button);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(22, 430, 70, 15);
		frame.getContentPane().add(lblSearch);
		
		searchfield = new JTextField();
		searchfield.setColumns(10);
		searchfield.setBounds(79, 425, 150, 25);
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
		
		
		
		
		
//		table.setBounds(22, 53, 406, 152);
//		frame.getContentPane().add(table);
		model_1 = new DefaultTableModel();
		Object[] column1 = {"Med_ID", "Medicine", "Quantity", "Expiry Date"};
		Object[][] row1 = {{"1", "Cough Syrup", "200l", "12/21/2023"}, {"2", "Paracetamol", "500l", "5/2/2025"}};
		model_1.setColumnIdentifiers(column1);
		for(int i=0; i<2; i++) {
			model_1.addRow(row1[i]);
		}
	}

//	*************************************************DISPLAY*************************************************
	private void getData() {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
			Statement st = con.createStatement();
			
			System.out.println("Connection established from getData");
			ResultSet rs = st.executeQuery("select * from Drugs");
//			ResultSet rs = st.executeQuery("select * from Departments");
//			int result = rs.executeUpdate();
			
			
			String cols[] = new String[13];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Name");
				cols[2] = rs.getString("Type");
				cols[3] = rs.getString("Dose");
				cols[4] = rs.getString("Code");
				cols[5] = Double.toString(rs.getDouble("Cost_price"));
				cols[6] = Double.toString(rs.getDouble("Selling_price"));
				cols[7] = rs.getString("Expiry");
				cols[8] = rs.getString("Company_name");
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				cols[9] = dateFormat.format(rs.getDate("Product_date"));
				cols[10] = dateFormat.format(rs.getDate("Expiration_date"));
				cols[11] = rs.getString("Place");
				cols[12] = Integer.toString(rs.getInt("Quantity"));
//				 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
