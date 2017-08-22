package mmms;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.Timer;

import com.mysql.jdbc.Statement;



import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class ManagerLogin  {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel successornot;
	static ManagerLogin userlogin;
	private Manager manager;
	private  Bazarlist blist;

	
	public  void sohel() {
		
					 userlogin = new ManagerLogin();
					userlogin.frame.setVisible(true);
			
	}

	
    
	
	
	public ManagerLogin() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.setResizable(false);
		frame.setBounds(100, 100, 444, 317);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(60, 85, 64, 20);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(134, 86, 145, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(60, 127, 64, 20);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(134, 129, 145, 20);
		frame.getContentPane().add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else {
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBackground(new Color(0, 128, 128));
		chckbxNewCheckBox.setBounds(134, 156, 116, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton login = new JButton("Login");
		login.setBackground(new Color(255, 255, 255));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection=null;
				PreparedStatement ps;
				Statement statement;
				try {
					Connection myCon =DriverManager.getConnection("jdbc:mysql://localhost:3306/m_m_m_s?autoReconnect=true&useSSL=false", "root", "root");
						
						PreparedStatement mystm = myCon.prepareStatement("select * from managelogin where name=? and password=?");
						mystm.setString(1, textField.getText());
						mystm.setString(2, String.valueOf(passwordField.getPassword()));
						ResultSet result =mystm.executeQuery();
						
						if(result.next()){
							JOptionPane.showMessageDialog(null,"Login done ");
							manager = new Manager();
							blist = new Bazarlist();
     						frame.dispose();
     						manager.sohel();
     						
							
			            }
						else{
							JOptionPane.showMessageDialog(null,"Invalid user name or password");
						}
					} catch (Exception e) {
						
						System.err.println(e);
					}
				
				}
				
				
			
		});
		login.setBounds(134, 187, 69, 34);
		frame.getContentPane().add(login);
		
		 
	}
}
