package mmms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;

import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mealcalculation {

	private JFrame frame;
	private Dbsconnect con;
	private JComboBox comboBox;
	private String name;
	public  double mrate=0.0;
	public  void sohel(double rate) {
		    
		 
		     
			mealcalculation window = new mealcalculation(rate);
			window.frame.setVisible(true);
				
	}

	
	public mealcalculation(double rate) {
		initialize(rate);
		combo();
	}

	
	private void initialize(double mealrate) {
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(74, 74, 57, 14);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				name=(String) comboBox.getSelectedItem();
				
			}
		});
		comboBox.setBounds(133, 73, 130, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblDeposite = new JLabel("Deposite :");
		lblDeposite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDeposite.setBounds(54, 113, 79, 14);
		frame.getContentPane().add(lblDeposite);
		
		JLabel Desposite = new JLabel("0.0");
		Desposite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Desposite.setBounds(133, 113, 79, 14);
		frame.getContentPane().add(Desposite);
		
		JLabel lblCost = new JLabel("Cost :");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCost.setBounds(85, 140, 44, 14);
		frame.getContentPane().add(lblCost);
		
		JLabel cost = new JLabel("0.0");
		cost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cost.setBounds(133, 138, 79, 14);
		frame.getContentPane().add(cost);
		
		JLabel lblTotalmeal = new JLabel("TotalMeal :");
		lblTotalmeal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalmeal.setBounds(48, 163, 88, 14);
		frame.getContentPane().add(lblTotalmeal);
		
		JLabel totalmeal = new JLabel("0.0");
		totalmeal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totalmeal.setBounds(133, 163, 79, 14);
		frame.getContentPane().add(totalmeal);
		
		JLabel lblPay = new JLabel("PayMoney :");
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPay.setBounds(48, 188, 83, 26);
		frame.getContentPane().add(lblPay);
		
		JLabel pay = new JLabel("0.0");
		pay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pay.setBounds(133, 196, 79, 14);
		frame.getContentPane().add(pay);
		
		JLabel lblGetmoney = new JLabel("Getmoney :");
		lblGetmoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGetmoney.setBounds(45, 221, 88, 26);
		frame.getContentPane().add(lblGetmoney);

		JLabel getmoney = new JLabel("0.0");
		getmoney.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getmoney.setBounds(133, 225, 79, 14);
		frame.getContentPane().add(getmoney);
		
		JButton btnNewButton = new JButton("Press");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection=con.getconnection();
				PreparedStatement statement;
				ResultSet set;
				
				try {
					  
					String query ="SELECT amount FROM `m_m_m_s`.`deposite` where name=?";
					statement=connection.prepareStatement(query);
					statement.setString(1, name);
					 set=statement.executeQuery();
					    set.next();
					  double aumont=Double.parseDouble(set.getString(1));
					  Desposite.setText(Double.toString(aumont));
					
					String query1="SELECT sum("+name+") from m_m_m_s.mealchart";
					statement=connection.prepareStatement(query1);
					set=statement.executeQuery();
					set.next();
				    double meal=Double.parseDouble(set.getString(1));
				    
				    double totalcost=mealrate*meal;
				    totalmeal.setText(Double.toString(meal));
				    
				    cost.setText(String.format("%.2f", totalcost));
				   
				    double result=aumont-totalcost;
				    result=(int)(result*100)/100.0;
				      
				    if(result>0.0){
				    	
				    	getmoney.setText(Double.toString(result));
				    	pay.setText(Double.toString(0.0));
				    	
				    }
				    else {
						pay.setText(Double.toString(result));
						getmoney.setText(Double.toString(0.0));
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(133, 270, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 500, 444);
		
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
