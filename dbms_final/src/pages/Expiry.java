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

 

public class Expiry {

	public JFrame frame;
	private JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField Name;
	private JTextField Code;
	private JTextField Date;
	private JButton btnDelete;
	private JLabel lblQuantityRemain;
	private JTextField Quantity;
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
					Expiry window = new Expiry();
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
	public Expiry() throws Exception {
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
		frame.setBounds(100, 100, 630, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Expiry");
		 lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setBounds(40, 20, 150, 20);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 182, 568, 176);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);


		model = new DefaultTableModel();
		Object[] column = {"ID", "Name", "Code", "Date","Quantity"};
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		sorter=new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setBounds(32, 59, 100, 15);
		frame.getContentPane().add(lblName);
		
		Name = new JTextField();
		Name.setBounds(168, 57, 150, 19);
		frame.getContentPane().add(Name);
		Name.setColumns(10);
		
		JLabel lblCode = new JLabel("Product Code");
		lblCode.setBounds(32, 86, 100, 15);
		frame.getContentPane().add(lblCode);
		
		Code = new JTextField();
		Code.setBounds(168, 84, 150, 19);
		frame.getContentPane().add(Code);
		Code.setColumns(10);
		
		JLabel lblDate = new JLabel("Date of Expiry");
		lblDate.setBounds(32, 113, 100, 15);
		frame.getContentPane().add(lblDate);
		
		Date = new JTextField();
		Date.setBounds(168, 111, 150, 19);
	PlaceHolder p=new PlaceHolder(Date,"yyyy-mm-dd");
		frame.getContentPane().add(Date);
		Date.setColumns(10);
		
		lblQuantityRemain = new JLabel("Quantity remain");
		lblQuantityRemain.setBounds(32, 140, 150, 15);
		frame.getContentPane().add(lblQuantityRemain);
		
		Quantity = new JTextField();
		Quantity.setColumns(10);
		Quantity.setBounds(168, 142, 150, 19);
		frame.getContentPane().add(Quantity);
		
		
		getData();
		
//		*************************************************INSERT*************************************************
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Name.getText().equals("")||Code.getText().equals("")||Date.getText().equals("")||Quantity.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",Name.getText(), Code.getText(), Date.getText(),Quantity.getText()};
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");

						PreparedStatement ps = con.prepareStatement("insert into Expiry(Product_name, Product_code, Date_of_expiry, Quantity_remain) values(?,?,?,?)");
					    ps.setString(1, data[1]);
					    ps.setString(2, data[2]);
					    ps.setString(3, data[3]);
					    ps.setString(4, data[4]);
						int count = ps.executeUpdate();
						
						Statement st = con.createStatement();
						String query = "select Id from Expiry where Product_name='"+data[1]+"'";
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
					Code.setText("");
					Date.setText("");
					Quantity.setText("");
				}
			}
		});
		btnAdd.setBounds(341, 81, 70, 25);
		frame.getContentPane().add(btnAdd);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = model.getValueAt(table.getSelectedRow(), 1).toString();
				String code = model.getValueAt(table.getSelectedRow(), 2).toString();
				String date = model.getValueAt(table.getSelectedRow(), 3).toString();
				String quantity = model.getValueAt(table.getSelectedRow(), 4).toString();
				
				Name.setText(name);
				Code.setText(code);
				Date.setText(date);
				Quantity.setText(quantity);
			}
		});
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount()==1) {
					String nName = Name.getText();
					String nCode = Code.getText();
					String nDate = Date.getText();
					String nQuantity = Quantity.getText();
					
					Connection con;
					
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
						String id = model.getValueAt(table.getSelectedRow(), 0).toString();
						int x = Integer.parseInt(id);
						
						PreparedStatement ps = con.prepareStatement("update Expiry set Product_name=?, Product_code=?, Date_of_expiry=?, Quantity_remain=? where Id=?");
					    ps.setString(1, nName);
					    ps.setString(2, nCode);
					    ps.setString(3, nDate);
					    ps.setString(4, nQuantity);
					    ps.setInt(5, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					model.setValueAt(nName, table.getSelectedRow(), 1);
					model.setValueAt(nCode, table.getSelectedRow(), 2);
					model.setValueAt(nDate, table.getSelectedRow(), 3);
					model.setValueAt(nQuantity, table.getSelectedRow(), 4);
					
					JOptionPane.showMessageDialog(frame, "Data updated successfully!!");
					
					Name.setText("");
					Code.setText("");
					Date.setText("");
					Quantity.setText("");
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
		btnUpdate.setBounds(423, 81, 88, 25);
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
						PreparedStatement ps = con.prepareStatement("delete from Expiry where Id=?");
					    ps.setInt(1, x);
						int count = ps.executeUpdate();
						System.out.println(count + " row updated");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeRow(table.getSelectedRow());
					Name.setText("");
					Code.setText("");
					Date.setText("");
					Quantity.setText("");
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
		btnDelete.setBounds(523, 81, 91, 25);
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
button.setBounds(474, 383, 140, 25);
frame.getContentPane().add(button);
lblSearch = new JLabel("Search");
lblSearch.setBounds(25, 375, 70, 15);
frame.getContentPane().add(lblSearch);
searchfield = new JTextField();
searchfield.setColumns(10);
searchfield.setBounds(82, 370, 150, 25);
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
			ResultSet rs = st.executeQuery("select * from Expiry");
			
			
			String cols[] = new String[5];
			while(rs.next())
			{
				int x = rs.getInt("Id");
				System.out.println(x);
				cols[0] = Integer.toString(x);
				cols[1] = rs.getString("Product_name");
				cols[2] = rs.getString("Product_code");
				cols[3] = rs.getString("Date_of_expiry");
				cols[4] = rs.getString("Quantity_remain");			 
				model.addRow(cols);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}