package pages;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JTextField txtuser;
	private JLabel lblWelcome;
	private JPasswordField txtpassword;
	java.util.Date date;
	private final JLabel lblNewLabel = new JLabel("");
	public String loggedIn_user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setBounds(89, 97, 93, 20);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(89, 141, 93, 18);
		frame.getContentPane().add(lblPassword);
		
		txtuser = new JTextField();
		txtuser.setBounds(243, 92, 157, 31);
		frame.getContentPane().add(txtuser);
		txtuser.setColumns(10);
		
		lblWelcome = new JLabel("Pharmacy System");
		lblWelcome.setForeground(Color.BLACK);
		lblWelcome.setFont(new Font("FreeMono", Font.BOLD, 24));
		lblWelcome.setBounds(122, 38, 250, 31);
		frame.getContentPane().add(lblWelcome);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(243, 141, 157, 31);
		frame.getContentPane().add(txtpassword);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtuser.getText().equals("")||txtpassword.getPassword().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter all details");
				}
				else {
					String data[] = {"0",txtuser.getText(), String.valueOf(txtpassword.getPassword())};
					
					loggedIn_user = data[1];
					String type;
					if(data[1].equals("admin") || data[1].equals("Admin"))
					{
						type="Admin";
						Dashboard.f=1;
					}
					else
						type="Employee";
					
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsmini?autoReconnect=false&useSSL=false", "root", "Manali@123");
						System.out.println("Connection established");
						
//						ResultSet rs = st.executeQuery("select * from Departments");
//						int result = rs.executeUpdate();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
					    Date date = new Date();
					    
					    Statement st = con.createStatement();
						System.out.println("Connection established");
						ResultSet rs = st.executeQuery("select Name, Password from Users");
						
						
						ArrayList<ArrayList<String> > x = new ArrayList<ArrayList<String>>(); 
						while(rs.next())
						{
							 x.add(new ArrayList<String>(Arrays.asList(rs.getString("Name"),rs.getString("Password")))); 
						}
						
						int flag=0;
						for(int i=0; i<x.size(); i++) {
							for(int j=0; j<2; j++) {
								if((x.get(i).get(0).contentEquals(data[1])) && (x.get(i).get(1).contentEquals(data[2]))) 
									flag=1;
							}
						}
						System.out.println(flag);
						if(flag==1) {
							
							PreparedStatement ps = con.prepareStatement("insert into Login(Name, Type) values(?,?)");
						    ps.setString(1, data[1]);
						    ps.setString(2, type);
//						    ps.setDate(3, new java.sql.Date(java.time.LocalDate.now()));
//						    ps.setString(4, arg1);
							int count = ps.executeUpdate();
							
							System.out.println(count + " row updated");
							JOptionPane.showMessageDialog(frame, "User is verified!!!");
							
							Dashboard db = new Dashboard(data[1]);
							db.getname(data[1]);
							db.frame.setVisible(true);
							frame.setVisible(false);
						}
						else
							JOptionPane.showMessageDialog(frame, "User doesn't exist!!");
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					model.addRow(data);
					
					
					txtuser.setText("");
					txtpassword.setText("");
//					getData();
				}
				
			}
		});
		btnLogin.setBounds(151, 200, 117, 25);
		frame.getContentPane().add(btnLogin);
		lblNewLabel.setIcon(new ImageIcon("/home/manali/Downloads/icon.jpg"));
		lblNewLabel.setBounds(391, 12, 47, 44);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
