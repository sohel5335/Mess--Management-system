package mmms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Admin {

	private JFrame frame;
	private JTextField id;
	private JTextField name;
	private JTextField occupation;
	private JTextField address;
	private JTextField number;
	private JPanel panel_1;
	private JPanel addMember;
	private JPanel remove;
	 private JPanel  selectM;
	 private JTextField textField;
	 private JTable table;
	 private JTable table_1;
	 private JTextField username;
	 private JTextField pass;
	           

	public static void m() {
		
					Admin window = new Admin();
					window.frame.setVisible(true);
			
	}


	public Admin() {
		initialize();
	}
     public Connection getconnection(){
    	 Connection connection;
    	 try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/m_m_m_s?autoReconnect=true&useSSL=false", "root", "root");
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
     }
     public void show(String query){
    	 Connection connection=getconnection();
    	 Statement statement;
    	 try {
			statement=(Statement) connection.createStatement();
			
			
				table.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
				table_1.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
     }
     
     public void executeSQLQuery(String query,String sql,String message)
     {
                 Connection con=getconnection();
                 Statement st;
         try {
                 st=(Statement) con.createStatement();
                
                    int set =st.executeUpdate(query);
                    st.executeUpdate(sql);
                  if(set==1)
                        {  
                	  JOptionPane.showMessageDialog(null,"Data "+message+" Successfully");
                	  String query1 ="select * from member";
                	      show(query1);                                
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
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 128));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 128, 128));
		panel.setPreferredSize(new Dimension(180, 10));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("AddMember");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(addMember);
				panel_1.repaint();
				panel_1.revalidate();
			}
		});
		btnNewButton.setBackground(new Color(173, 216, 230));
		btnNewButton.setBounds(24, 46, 134, 23);
		panel.add(btnNewButton);
		
		JButton btnRemovemember = new JButton("RemoveMember");
		btnRemovemember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(remove);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="select * from member" ;
				show(query);
				
			}
		});
		btnRemovemember.setBackground(new Color(173, 216, 230));
		btnRemovemember.setBounds(24, 90, 134, 23);
		panel.add(btnRemovemember);
		
		JButton btnSelectmanager = new JButton("SelectManager");
		btnSelectmanager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.add(selectM);
				panel_1.repaint();
				panel_1.revalidate();
				String query ="select * from member" ;
				show(query);
			}
		});
		btnSelectmanager.setBackground(new Color(173, 216, 230));
		btnSelectmanager.setBounds(24, 131, 134, 23);
		panel.add(btnSelectmanager);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   Connection connection =getconnection();
			   Statement  st;
			   try {
				   st=(Statement) connection.createStatement();
				   String sql="truncate m_m_m_s.deposite";
				   st.executeUpdate(sql);
				   String sql1="truncate m_m_m_s.barartaka";
				   st.executeUpdate(sql1);
				   String sql2="truncate m_m_m_s.bazarlist";
				   st.executeUpdate(sql2);
				   String sql3="truncate m_m_m_s.foodmenu";
				   st.executeUpdate(sql3);
				   String sql4="truncate m_m_m_s.mealchart";
				   int i=st.executeUpdate(sql4);
				   if(i==1){
					   JOptionPane.showMessageDialog(null, "not reset all data");
				   }
				   else{
					   JOptionPane.showMessageDialog(null, "reset all data");
				   }
			} catch (Exception e) {
				// TODO: handle exception
			}
			}
		});
		btnReset.setBackground(new Color(173, 216, 230));
		btnReset.setBounds(24, 179, 134, 23);
		panel.add(btnReset);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(173, 216, 230));
		btnNewButton_1.setBounds(85, 225, 73, 23);
		panel.add(btnNewButton_1);
		
	    panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new CardLayout(0, 0));
		
		 addMember = new JPanel();
		addMember.setBackground(new Color(60, 179, 113));
		panel_1.add(addMember, "name_4763842516740");
		addMember.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(80, 51, 24, 25);
		addMember.add(lblNewLabel);
		
		id = new JTextField();
		id.setBounds(114, 55, 199, 20);
		addMember.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(58, 87, 46, 14);
		addMember.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(114, 86, 199, 20);
		addMember.add(name);
		
		JLabel lblOccupation = new JLabel("Occupation:");
		lblOccupation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOccupation.setBounds(26, 112, 84, 25);
		addMember.add(lblOccupation);
		
		occupation = new JTextField();
		occupation.setColumns(10);
		occupation.setBounds(114, 117, 199, 20);
		addMember.add(occupation);
		
		JLabel lblAge = new JLabel("Address:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(47, 142, 57, 25);
		addMember.add(lblAge);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(114, 148, 199, 20);
		addMember.add(address);
		
		JLabel lblContactnum = new JLabel("Contact_num:");
		lblContactnum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContactnum.setBounds(10, 178, 94, 25);
		addMember.add(lblContactnum);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(114, 179, 199, 20);
		addMember.add(number);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			
				
				 String query="INSERT INTO `m_m_m_s`.`member` (`id`, `name`, `occupation`, `address`, `contact number`) VALUES ('"+Integer.parseInt(id.getText())+"', '"+name.getText()+"', '"+occupation.getText()+"','"+address.getText()+"', '"+number.getText()+"')";
				  String sql ="ALTER TABLE `m_m_m_s`.`mealchart` ADD COLUMN `"+name.getText()+"` double";
			        executeSQLQuery(query,sql,"insert");
                     
			}
		});
		btnNewButton_2.setBackground(new Color(173, 216, 230));
		btnNewButton_2.setBounds(113, 210, 62, 23);
		addMember.add(btnNewButton_2);
		
		
		 remove = new JPanel();
		 remove.setBackground(new Color(60, 179, 113));
		panel_1.add(remove, "name_5063524153251");
		remove.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(144, 238, 144));
		panel_2.setPreferredSize(new Dimension(10, 30));
		remove.add(panel_2, BorderLayout.NORTH);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel_2, 18, SpringLayout.WEST, panel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);
		
		textField = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.WEST, textField, 68, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textField, -179, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel_2, -1, SpringLayout.NORTH, textField);
		sl_panel_2.putConstraint(SpringLayout.EAST, lblNewLabel_2, -6, SpringLayout.WEST, textField);
		sl_panel_2.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.NORTH, panel_2);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton reok = new JButton("OK");
		reok.setBackground(new Color(173, 216, 230));
		sl_panel_2.putConstraint(SpringLayout.NORTH, reok, 2, SpringLayout.NORTH, lblNewLabel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, reok, 8, SpringLayout.EAST, textField);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, reok, 2, SpringLayout.SOUTH, lblNewLabel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, reok, 66, SpringLayout.EAST, textField);
		reok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query= "DELETE FROM `m_m_m_s`.`member` WHERE name='"+textField.getText()+"'";
				String sql ="ALTER TABLE `m_m_m_s`.`mealchart`DROP  COLUMN "+textField.getText();
				executeSQLQuery(query,sql,"delete");
			}
		});
		panel_2.add(reok);
		
		table = new JTable();
		table.setBackground(new Color(64, 224, 208));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		
	    
	    JScrollPane scrollPane = new JScrollPane();
	    remove.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		
	    selectM = new JPanel();
	    selectM.setBackground(new Color(60, 179, 113));
		panel_1.add(selectM, "name_5080991072811");
		selectM.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(152, 251, 152));
		panel_3.setPreferredSize(new Dimension(10, 70));
		selectM.add(panel_3, BorderLayout.NORTH);
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("username:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 10, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblNewLabel_3, 10, SpringLayout.WEST, panel_3);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblPassword = new JLabel("password:");
		sl_panel_3.putConstraint(SpringLayout.SOUTH, lblPassword, -10, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblNewLabel_3);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblPassword);
		
		username = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.NORTH, username, 10, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, username, 4, SpringLayout.EAST, lblNewLabel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, username, 189, SpringLayout.EAST, lblNewLabel_3);
		panel_3.add(username);
		username.setColumns(10);
		
		pass = new JTextField();
		sl_panel_3.putConstraint(SpringLayout.WEST, pass, 6, SpringLayout.EAST, lblPassword);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, pass, -9, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, pass, 0, SpringLayout.EAST, username);
		pass.setColumns(10);
		panel_3.add(pass);
		
		JButton btnNewButton_3 = new JButton("ok");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String query="UPDATE `m_m_m_s`.`managelogin` SET `name`='"+username.getText()+"', `password`='"+pass.getText()+"' WHERE `id`='1'";
                executeSQLQuery(query,"UPDATE `m_m_m_s`.`missing` SET `you`='"+username.getText()+"' WHERE `idmissing`='1'","select manager is");

			}
		});
		btnNewButton_3.setBackground(new Color(173, 216, 230));
		sl_panel_3.putConstraint(SpringLayout.WEST, btnNewButton_3, 11, SpringLayout.EAST, pass);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 0, SpringLayout.SOUTH, lblPassword);
		panel_3.add(btnNewButton_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		selectM.add(scrollPane_1, BorderLayout.CENTER);
		table_1 = new JTable();
		table_1.setBackground(new Color(64, 224, 208));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table_1);
		frame.setBounds(100, 100, 598, 494);
		
	}
}
