package mmms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Mealupdate {
	private static final ResultSetMetaData metaData = null;
	private Dbsconnect con;
	private JFrame frame;
	JComboBox comboBox;
	private JLabel lblDate;
	private JTextField textField;
	private JTextField textField_1;
	private String member;
	
	public  void sohel1() {
		
					Mealupdate window = new Mealupdate();
					window.frame.setVisible(true);
		
	}

	
	public Mealupdate() {
		
		initialize();
		combo();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.setBounds(100, 100, 450, 314);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(64, 58, 49, 14);
		frame.getContentPane().add(lblNewLabel);
		
	   comboBox = new JComboBox();
	   comboBox.addItemListener(new ItemListener() {
	   	public void itemStateChanged(ItemEvent arg0) {
	   		member=(String) comboBox.getSelectedItem();
	   		
	   	}
	   });
		comboBox.setBounds(123, 57, 125, 20);
		frame.getContentPane().add(comboBox);
		
		lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(72, 33, 41, 14);
		frame.getContentPane().add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(123, 32, 125, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMeal = new JLabel("Meal :");
		lblMeal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMeal.setBounds(74, 85, 49, 14);
		frame.getContentPane().add(lblMeal);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 84, 125, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Connection connection=con.getconnection();
	                Statement st;
	                try {
	                	  st=(Statement) connection.createStatement();
                          String query ="UPDATE `m_m_m_s`.`mealchart` SET `"+member+"`='"+Double.parseDouble(textField_1.getText())+"' WHERE `date`='"+textField.getText()+"'";  
                          int s=st.executeUpdate(query);
                          if(s==1){
                        	  JOptionPane.showMessageDialog(null, "update successfull");
                          }
					} catch (Exception e2) {
						
					}
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(183, 115, 65, 23);
		frame.getContentPane().add(btnNewButton);
		con= new Dbsconnect();
	}
	public void combo(){
		 Connection connection=con.getconnection();
         Statement st;
         try {
         	  st=(Statement) connection.createStatement();
         	  ResultSet set=st.executeQuery("select * from member");
         	while(set.next()){
         		String name=set.getString("name");
         		comboBox.addItem(name);
         	}
         	 
			} catch (Exception e2) {
				
			}
	}
}
