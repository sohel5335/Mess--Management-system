package mmms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Uppdate_bazarTK {

	private JFrame frame;
	private JTextField textField;
    private Dbsconnect con;
    JComboBox comboBox;
    String date;
	public  void sohel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uppdate_bazarTK window = new Uppdate_bazarTK();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Uppdate_bazarTK() {
		initialize();
		combo();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel lblNewLabel = new JLabel("Date :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(40, 50, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		 comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				date=(String) comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(96, 49, 107, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(25, 86, 61, 14);
		frame.getContentPane().add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(96, 80, 107, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection=con.getconnection();
                Statement st;
                try {
                	  st=(Statement) connection.createStatement();
                      String query ="UPDATE `m_m_m_s`.`barartaka` SET `aumount`='"+Integer.parseInt(textField.getText())+"' WHERE `date`='"+date+"'";
  
                      int s=st.executeUpdate(query);
                      if(s==1){
                    	  JOptionPane.showMessageDialog(null, "update successfull");
                      }
				} catch (Exception e2) {
					
				}
		}
		
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(147, 123, 56, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setBackground(new Color(0, 139, 139));
		frame.setBounds(100, 100, 414, 307);
		
		con=new Dbsconnect();
	}
	public void combo(){
		 Connection connection=con.getconnection();
       Statement st;
       try {
       	  st=(Statement) connection.createStatement();
       	  ResultSet set=st.executeQuery("SELECT * FROM m_m_m_s.barartaka");
       	while(set.next()){
       		String name=set.getString("date");
       		comboBox.addItem(name);
       	}
       	 
			} catch (Exception e2) {
				
			}
	}
}
