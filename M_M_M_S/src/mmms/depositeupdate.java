package mmms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class depositeupdate {

	private JFrame frame;
	private JTextField textField;
    Dbsconnect con;
    JComboBox comboBox;
    String member;
	public  void sohel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					depositeupdate window = new depositeupdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public depositeupdate() {
		initialize();
		combo();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(51, 47, 49, 14);
		frame.getContentPane().add(lblName);
		
		 comboBox = new JComboBox();
		 comboBox.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		member=(String) comboBox.getSelectedItem();
		 	}
		 });
		comboBox.setBounds(110, 46, 101, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(34, 84, 66, 14);
		frame.getContentPane().add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(110, 83, 101, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ok");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection=con.getconnection();
                Statement st;
                try {
                	  st=(Statement) connection.createStatement();
                  String query =  "UPDATE `m_m_m_s`.`deposite` SET `amount`='"+Double.parseDouble(textField.getText())+"' WHERE `name`='"+member+"'";

                      int s=st.executeUpdate(query);
                      if(s==1){
                    	  JOptionPane.showMessageDialog(null, "update successfull");
                      }
				} catch (Exception e2) {
					
				}
		
			}
		});
		btnNewButton.setBounds(162, 114, 49, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 407, 308);
		
		con=new Dbsconnect();
	   
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
