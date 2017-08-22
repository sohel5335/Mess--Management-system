package mmms;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ItemEvent;

public class Manager {

	private JFrame frmMmms;
	private JPanel panel_1 ;
	private JTextField date;
	private JTextField name;
	private JTable table;
	private Dbsconnect con;
	JPanel blist;
	JPanel panel_2;
	JPanel foodmenu;
	private JTextField fdate;
	private JTextField Menu;
	private JTable table_1;
	private JTextField tdate;
	private JTextField btaka;
	private JTable table_2;
	JPanel bazartk;
	JPanel mealchart;
	private JTable table_3;
	private JTextField mdte;
	JButton click;
	private JTextField mMeal;
	JButton done;
	String date1;
	private JTable table_4;
	private JTextField amount;
	JComboBox comboBox;
	private String name1;
	JPanel deposite;
	public void sohel() {
		
					Manager window = new Manager();
					window.frmMmms.setVisible(true);
		
	}
	public void executeSQLQuery(String query,String message)
    {
   	          Connection connection=con.getconnection();
                Statement st;
        try {
                st=(Statement) connection.createStatement();
                
                   int set =st.executeUpdate(query);
                 if(set==1)
                       {  
               	  JOptionPane.showMessageDialog(null,"Data "+message+" Successfully");
                                               
                    } 
                    else {
                            JOptionPane.showMessageDialog(null,"unsuccessful"); 
                    }
            }
        catch(Exception ex)
            {
                   ex.printStackTrace();
            }
    }
	 public void show(String query){
    	 Connection connection=con.getconnection();
    	
    	 Statement statement;
    	 try {
			statement=(Statement) connection.createStatement();
				table.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_1.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_2.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_3.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_4.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
     }
	public Manager() {
		initialize();
		combo();
	}


	private void initialize() {
		frmMmms = new JFrame();
		frmMmms.setTitle("M_M_M_S");
		frmMmms.getContentPane().setBackground(new Color(0, 128, 128));
		frmMmms.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Manager", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 128, 128));
		panel.setPreferredSize(new Dimension(180, 10));
		frmMmms.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Deposite");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(deposite);
				panel_1.repaint();
				panel_1.revalidate();
				String query="SELECT * FROM m_m_m_s.deposite";
				show(query);
			}
		});
		btnNewButton.setBackground(new Color(173, 216, 230));
		btnNewButton.setBounds(21, 36, 132, 23);
		panel.add(btnNewButton);
		
		JButton btnCreatebazarlist = new JButton("CreateBazarlist");
		btnCreatebazarlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.add(blist);
				panel_1.repaint();
				panel_1.revalidate();
				 String query="SELECT * FROM m_m_m_s.bazarlist";
			    show(query);
			}
		});
		btnCreatebazarlist.setBackground(new Color(173, 216, 230));
		btnCreatebazarlist.setBounds(21, 78, 132, 23);
		panel.add(btnCreatebazarlist);
		
		JButton btnMealchart = new JButton("Mealchart");
		btnMealchart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.add(mealchart);
				panel_1.repaint();
				panel_1.revalidate();
				 String query="SELECT * FROM m_m_m_s.mealchart";
				show(query);
			}
		});
		btnMealchart.setBackground(new Color(173, 216, 230));
		btnMealchart.setBounds(21, 122, 132, 23);
		panel.add(btnMealchart);
		
		JButton btnCreatefoodmenu = new JButton("CreateFoodMenu");
		btnCreatefoodmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(foodmenu);
				panel_1.repaint();
				panel_1.revalidate();
				 String query="SELECT * FROM m_m_m_s.foodmenu";
				show(query);
			}
		});
		btnCreatefoodmenu.setBackground(new Color(173, 216, 230));
		btnCreatefoodmenu.setBounds(21, 165, 132, 23);
		panel.add(btnCreatefoodmenu);
		
		JButton btnBazartk = new JButton("BazarTaka");
		btnBazartk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(bazartk);
				panel_1.repaint();
				panel_1.revalidate();
				 String query="SELECT * FROM m_m_m_s.barartaka;";
				show(query);
			}
		});
		btnBazartk.setBackground(new Color(173, 216, 230));
		btnBazartk.setBounds(21, 204, 132, 23);
		panel.add(btnBazartk);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMmms.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(173, 216, 230));
		btnNewButton_1.setBounds(76, 238, 77, 23);
		panel.add(btnNewButton_1);
		
		 panel_1 = new JPanel();
		frmMmms.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new CardLayout(0, 0));
		
		 foodmenu = new JPanel();
		panel_1.add(foodmenu, "name_20490983807724");
		foodmenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(102, 205, 170));
		panel_4.setPreferredSize(new Dimension(10, 100));
		foodmenu.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(null);
		
		JLabel fd = new JLabel("Date :");
		fd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fd.setBounds(32, 11, 48, 14);
		panel_4.add(fd);
		
		JLabel lblMenu = new JLabel("Menu :");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMenu.setBounds(26, 37, 54, 14);
		panel_4.add(lblMenu);
		
		fdate = new JTextField();
		fdate.setBounds(83, 10, 187, 20);
		panel_4.add(fdate);
		fdate.setColumns(10);
		
		Menu = new JTextField();
		Menu.setColumns(10);
		Menu.setBounds(83, 36, 187, 20);
		panel_4.add(Menu);
		
		JButton insert = new JButton("insert");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="INSERT INTO `m_m_m_s`.`foodmenu` (`date`, `menu`) VALUES ('"+fdate.getText()+"', '"+Menu.getText()+"')";
				 executeSQLQuery(query,"insert");  
			}
		});
		insert.setBackground(new Color(0, 139, 139));
		insert.setBounds(188, 66, 82, 23);
		panel_4.add(insert);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		foodmenu.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(72, 209, 204));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
	    blist = new JPanel();
		panel_1.add(blist, "name_20549254873094");
		blist.setLayout(new BorderLayout(0, 0));
		
		 panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setPreferredSize(new Dimension(10, 100));
		blist.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 11, 50, 14);
		panel_2.add(lblNewLabel);
		
		date = new JTextField();
		date.setBounds(70, 10, 183, 20);
		panel_2.add(date);
		date.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 10, 10);
		panel_2.add(panel_3);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 36, 50, 14);
		panel_2.add(lblName);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(70, 35, 183, 20);
		panel_2.add(name);
		
		JButton btnNewButton_2 = new JButton("ok");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query ="INSERT INTO `m_m_m_s`.`bazarlist` (`Date`, `Name`) VALUES ('"+date.getText()+"', '"+name.getText()+"')";
		        executeSQLQuery(query,"insert");
			}
		});
		btnNewButton_2.setBackground(new Color(72, 209, 204));
		btnNewButton_2.setBounds(203, 66, 50, 23);
		panel_2.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		blist.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(72, 209, 204));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		 bazartk = new JPanel();
		panel_1.add(bazartk, "name_91832009625734");
		bazartk.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(102, 205, 170));
		panel_5.setPreferredSize(new Dimension(10, 100));
		bazartk.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(null);
		
		tdate = new JTextField();
		tdate.setBounds(93, 11, 166, 20);
		panel_5.add(tdate);
		tdate.setColumns(10);
		
		btaka = new JTextField();
		btaka.setColumns(10);
		btaka.setBounds(93, 42, 166, 20);
		panel_5.add(btaka);
		
		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(42, 12, 41, 14);
		panel_5.add(lblNewLabel_1);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(22, 43, 61, 14);
		panel_5.add(lblAmount);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  Uppdate_bazarTK dBazarTK= new Uppdate_bazarTK();
			  dBazarTK.sohel();
			}
		});
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBounds(93, 73, 77, 23);
		panel_5.add(btnUpdate);
		
		JButton btnInsert = new JButton("insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string="INSERT INTO `m_m_m_s`.`barartaka` (`date`, `aumount`) VALUES ('"+tdate.getText()+"', '"+Integer.parseInt(btaka.getText())+"')";
				executeSQLQuery(string,"insert");
			}
		});
		btnInsert.setBackground(new Color(0, 139, 139));
		btnInsert.setBounds(180, 73, 79, 23);
		panel_5.add(btnInsert);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		bazartk.add(scrollPane_2, BorderLayout.CENTER);
		
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
		
		mealchart = new JPanel();
		panel_1.add(mealchart, "name_902771031136");
		mealchart.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(102, 205, 170));
		panel_6.setPreferredSize(new Dimension(10, 100));
		mealchart.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(null);
		
		JCheckBox chckbxDate = new JCheckBox("Date");
		
		
		chckbxDate.setBackground(new Color(102, 205, 170));
		
		chckbxDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean a = chckbxDate.isSelected();
				 mdte.setEnabled(a);
				 click.setEnabled(a);
				
				 
			}
		});
		chckbxDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDate.setBounds(6, 6, 67, 27);
		panel_6.add(chckbxDate);
		
		mdte = new JTextField();
		mdte.setBounds(79, 11, 141, 20);
		panel_6.add(mdte);
		mdte.setColumns(10);
		
		
		 click = new JButton("click");
		click.setBackground(new Color(0, 128, 128));
		click.setEnabled(false);
		click.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="insert into m_m_m_s.mealchart (date)values('"+mdte.getText()+"')";
                executeSQLQuery(sql,"input");
			}
		});
		click.setBounds(232, 10, 64, 20);
		panel_6.add(click);
		
		mMeal = new JTextField();
		mMeal.setColumns(10);
		mMeal.setBounds(79, 42, 141, 20);
		panel_6.add(mMeal);
		
		JCheckBox cmeal = new JCheckBox("Meal");
		cmeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = cmeal.isSelected();
				 mMeal.setEditable(b);
				 done.setEnabled(b);
				 date1=mdte.getText();
				 	
			}
		});
		
		
		cmeal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmeal.setBackground(new Color(102, 205, 170));
		cmeal.setBounds(6, 36, 55, 27);
		panel_6.add(cmeal);
		
		
		
		done = new JButton("click");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection connection=con.getconnection();
	                Statement st;
	                try {
	                	  st=(Statement) connection.createStatement();
	                	  ResultSet set=st.executeQuery("SELECT * FROM m_m_m_s.mealchart");
	                	  ResultSetMetaData metaData=(ResultSetMetaData) set.getMetaData();
	                	  int count =metaData.getColumnCount();
	                	  for(int i=2;i<=count;i++){
	                		  String column=metaData.getColumnName(i);
	                		  st.executeUpdate("UPDATE `m_m_m_s`.`mealchart` SET `"+column+"`='"+Double.parseDouble(mMeal.getText())+"' WHERE `date`='"+date1+"'");
	                	  }
					} catch (Exception e2) {
						
					}
			}
		});
		done.setEnabled(false);
		done.setBackground(new Color(0, 139, 139));
		done.setBounds(232, 44, 64, 20);
		panel_6.add(done);
		
		JButton btnNewButton_3 = new JButton("update");
		btnNewButton_3.setBackground(new Color(0, 139, 139));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mealupdate mealupdate=new Mealupdate();
				mealupdate.sohel1();
			}
		});
		btnNewButton_3.setBounds(79, 66, 141, 23);
		panel_6.add(btnNewButton_3);
		
		
		
		JScrollPane scrollPane_3 = new JScrollPane();
		mealchart.add(scrollPane_3, BorderLayout.CENTER);
		
		table_3 = new JTable();
		table_3.setBackground(new Color(64, 224, 208));
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		deposite = new JPanel();
		panel_1.add(deposite, "name_758897398568");
		deposite.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(102, 205, 170));
		panel_7.setPreferredSize(new Dimension(10, 100));
		deposite.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(null);
		
		 comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				name1=(String) comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(110, 11, 134, 20);
		panel_7.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(47, 10, 54, 24);
		panel_7.add(lblNewLabel_2);
		
		JLabel lblDate = new JLabel("amount :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(33, 38, 72, 24);
		panel_7.add(lblDate);
		
		amount = new JTextField();
		amount.setBounds(110, 42, 134, 20);
		panel_7.add(amount);
		amount.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("update");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  depositeupdate d = new depositeupdate();
			    d.sohel();
			}
		});
		btnNewButton_4.setBackground(new Color(0, 139, 139));
		btnNewButton_4.setBounds(108, 66, 83, 23);
		panel_7.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("ok");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String query="INSERT INTO `m_m_m_s`.`deposite` (`name`, `amount`) VALUES ('"+name1+"', '"+Double.parseDouble(amount.getText())+"')";
             executeSQLQuery(query, "input");
			}
		});
		btnNewButton_5.setBackground(new Color(0, 139, 139));
		btnNewButton_5.setBounds(195, 66, 49, 23);
		panel_7.add(btnNewButton_5);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		deposite.add(scrollPane_4, BorderLayout.CENTER);
		
		table_4 = new JTable();
		table_4.setBackground(new Color(64, 224, 208));
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane_4.setViewportView(table_4);
		frmMmms.setBounds(100, 100, 576, 485);
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
