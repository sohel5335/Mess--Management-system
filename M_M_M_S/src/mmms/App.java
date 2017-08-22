package mmms;



import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;


import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;

public class App {

	private JFrame frmMmms;
	private JPanel mdetails;
	private JPanel panel_1;
	private JPanel foodmenu;
	private JPanel bazarlist;
	private ManagerLogin managerLogin;
	private Dbsconnect dbsconnect;
	private JTable table_1;
	 private Adminlogin adminlogin;
	 Connection co;
	 Statement statement;
	 private JTable table;
	 private JTable table_2;
	 JPanel bill;
	 JLabel mealrate1;
	 double rate1,mealrate;
	 JLabel lblResult;
	 JLabel lblDeposite;
	 JLabel lblBazartk;
	 JLabel lblMeal;
	
	public static void main(String[] args) {
		
					App window = new App();
					window.frmMmms.setVisible(true);
		
	}

	  
	public App() {
		initialize();
	
	}
  
	public void show(String query){
   	 Connection connection=(Connection) dbsconnect.getconnection();
   	 Statement statement;
   	 try {
			statement=(Statement) connection.createStatement();
			
			
				table.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_1.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_2.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    }
	
	
	private void initialize() {
		frmMmms = new JFrame();
		frmMmms.setTitle("M_M_M_S");
		frmMmms.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java\\M_M_M_S\\icon\\food-profile_image-0a2c3ed2f8d119ea-300x300.png"));
		frmMmms.setBounds(100, 100, 600, 498);
		frmMmms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 128, 128));
		panel.setPreferredSize(new Dimension(200, 10));
		frmMmms.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JButton Admin = new JButton("Admin");
		Admin.setToolTipText("M_M_M_S");
		Admin.setBackground(new Color(173, 216, 230));
		Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminlogin = new Adminlogin();
				adminlogin.sohel();
			}
			
		});
		Admin.setBounds(22, 27, 168, 23);
		panel.add(Admin);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				managerLogin=new ManagerLogin();
				managerLogin.sohel();
			}
		});
		btnManager.setBackground(new Color(173, 216, 230));
		btnManager.setBounds(22, 61, 168, 23);
		panel.add(btnManager);
		
		JButton btnMemberDetails = new JButton("Member details");
		btnMemberDetails.setBackground(new Color(173, 216, 230));
		btnMemberDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(mdetails);
				panel_1.repaint();
				panel_1.revalidate();
				 
				 
				 try {
					co=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/m_m_m_s?autoReconnect=true&useSSL=false","root","root");
					statement=(Statement) co.createStatement();
					String query ="select * from member" ;
					ResultSet set = statement.executeQuery(query);
					table_1.setModel(DbUtils.resultSetToTableModel(set));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				 
				
			}
		});
		btnMemberDetails.setBounds(22, 95, 168, 23);
		panel.add(btnMemberDetails);
		
		JButton btnMeal = new JButton("MealChart");
		btnMeal.setBackground(new Color(173, 216, 230));
		btnMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(foodmenu);
				panel_1.repaint();
				panel_1.revalidate();
				 String query="SELECT * FROM m_m_m_s.mealchart";
				    show(query);
				
			}
		});
		btnMeal.setBounds(22, 129, 168, 23);
		panel.add(btnMeal);
		
		JButton btnBill = new JButton("MealRate");
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
				panel_1.removeAll();
				panel_1.add(bill);
				panel_1.repaint();
				panel_1.revalidate();	
				Connection connection=(Connection) dbsconnect.getconnection();
				double bazartk = 0;
				Double totalmeal = null;
				double totaldeposite = 0;
                try {
                	String sql="SELECT sum(aumount) from m_m_m_s.barartaka";
                  PreparedStatement	  st1=(PreparedStatement) connection.prepareStatement(sql);
                       ResultSet set=st1.executeQuery();
                       set.next();
                        bazartk=Double.parseDouble(set.getString(1));
                        
                        String sq="SELECT sum(amount) from m_m_m_s.deposite";
                        PreparedStatement	  s=(PreparedStatement) connection.prepareStatement(sq);
                       set=s.executeQuery();
                             set.next();
                             totaldeposite=Double.parseDouble(set.getString(1));
                        
                       
                       Statement st;
                       st= (Statement) connection.createStatement();
                	   String sql2="SELECT * FROM m_m_m_s.mealchart";
                	   ResultSet set2=st.executeQuery(sql2);
                	  ResultSetMetaData metaData=(ResultSetMetaData) set2.getMetaData();
                	  
                	  int count =metaData.getColumnCount();
                	   totalmeal=0.0;
                	  for(int i=2;i<=count;i++){
                		  String column=metaData.getColumnName(i);
                		  String query1="SELECT sum("+column+") from m_m_m_s.mealchart";
                		  st1=connection.prepareStatement(query1);
                		  ResultSet v =st1.executeQuery();
                		  v.next();
                		  totalmeal+=Double.parseDouble(v.getString(1));
                		 
                		  
                	  }
				} catch (Exception e2) {
					
				}
                 mealrate=bazartk/totalmeal;
                  rate1=(int)(mealrate*100)/100.0; 
                  lblDeposite.setText(Double.toString(totaldeposite));
                  lblBazartk.setText(Double.toString(bazartk));
                  lblResult.setText(Double.toString(totaldeposite-bazartk));
                  lblMeal.setText(Double.toString(totalmeal));
     		    mealrate1.setText(Double.toString(rate1));  
			}
		});
		btnBill.setBackground(new Color(173, 216, 230));
		btnBill.setBounds(22, 164, 168, 23);
		panel.add(btnBill);
		
		JButton btnFoodmenu = new JButton("FoodMenu");
		btnFoodmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add( foodmenu);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="SELECT * FROM m_m_m_s.foodmenu;";
				 show(query);
				
			}
			
		});
		btnFoodmenu.setBackground(new Color(173, 216, 230));
		btnFoodmenu.setBounds(22, 198, 168, 23);
		panel.add(btnFoodmenu);
		
		JButton btnBazarlist = new JButton("BazarList");
		btnBazarlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(bazarlist);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="SELECT * FROM m_m_m_s.bazarlist";
				 show(query);
				
			}
		});
		btnBazarlist.setBackground(new Color(173, 216, 230));
		btnBazarlist.setBounds(22, 232, 168, 23);
		panel.add(btnBazarlist);
		
		JButton btnDeposite = new JButton("deposite");
		btnDeposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.add( foodmenu);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="SELECT * FROM m_m_m_s.deposite";
				 show(query);
			}
		});
		btnDeposite.setBackground(new Color(173, 216, 230));
		btnDeposite.setBounds(22, 266, 168, 23);
		panel.add(btnDeposite);
		
		JButton btnBazartk = new JButton("BazarTaka");
		btnBazartk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.add( foodmenu);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="SELECT * FROM m_m_m_s.barartaka";
				 show(query);
			}
		});
		btnBazartk.setBackground(new Color(173, 216, 230));
		btnBazartk.setBounds(22, 300, 168, 23);
		panel.add(btnBazartk);
		
		 panel_1 = new JPanel();
		frmMmms.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new CardLayout(0, 0));
		
	
		
		 mdetails = new JPanel();
		mdetails.setBackground(new Color(210, 105, 30));
		panel_1.add(mdetails, "name_6191518542217");
		mdetails.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		mdetails.add(scrollPane, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(64, 224, 208));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "name", "occupation", "district", "ContactNumber"
			}
		));
		table_1.getColumnModel().getColumn(4).setPreferredWidth(96);
	  scrollPane.setViewportView(table_1);
		
		 foodmenu = new JPanel();
		 foodmenu.setBackground(new Color(119, 136, 153));
		panel_1.add(foodmenu, "name_7534627168652");
		foodmenu.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		foodmenu.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable();
		table_2.setBackground(new Color(64, 224, 208));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		 bazarlist = new JPanel();
		 bazarlist.setBackground(new Color(165, 42, 42));
		panel_1.add(bazarlist, "name_7549049286077");
		bazarlist.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		bazarlist.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(64, 224, 208));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table);
		
		 bill = new JPanel();
		 bill.setPreferredSize(new Dimension(10, 100));
		 bill.setBackground(new Color(32, 178, 170));
		panel_1.add(bill, "name_1302616445034");
		bill.setLayout(new BorderLayout(0, 0));
		
		JButton btnNext = new JButton("BILL");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mealcalculation calculation= new mealcalculation(rate1);
				calculation.sohel(mealrate);
			}
		});
		btnNext.setBackground(new Color(60, 179, 113));
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bill.add(btnNext, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(32, 178, 170));
		panel_2.setPreferredSize(new Dimension(150, 10));
		bill.add(panel_2, BorderLayout.WEST);
		
		 mealrate1 = new JLabel("MealRate");
		mealrate1.setBackground(new Color(0, 255, 255));
		mealrate1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		bill.add(mealrate1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(32, 178, 170));
		panel_3.setPreferredSize(new Dimension(10, 150));
		bill.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(null);
		
	  lblDeposite = new JLabel("deposite");
		lblDeposite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeposite.setBounds(10, 46, 80, 30);
		panel_3.add(lblDeposite);
		
		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(100, 46, 18, 30);
		panel_3.add(label);
		
		 lblBazartk = new JLabel("Bazartk");
		lblBazartk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBazartk.setBounds(128, 46, 80, 30);
		panel_3.add(lblBazartk);
		
		JLabel label_1 = new JLabel("=");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(218, 46, 18, 30);
		panel_3.add(label_1);
		
	    lblResult = new JLabel("result");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResult.setBounds(256, 46, 80, 30);
		panel_3.add(lblResult);
		
		JLabel lblTotalmeal = new JLabel("Totalmeal =");
		lblTotalmeal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalmeal.setBounds(0, 87, 118, 30);
		panel_3.add(lblTotalmeal);
		
	    lblMeal = new JLabel("meal");
		lblMeal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMeal.setBounds(118, 87, 64, 30);
		panel_3.add(lblMeal);
		dbsconnect =new Dbsconnect();
		show("SELECT * FROM m_m_m_s.member");
	}
}
