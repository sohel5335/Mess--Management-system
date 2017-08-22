package mmms;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Foodmenu extends JPanel {
	private JTextField textField;
	private JTextField Menu;
	private JTable table;
	private Dbsconnect con;
	 public void show(){
    	 Connection connection=con.getconnection();
    	 String query="SELECT * FROM m_m_m_s.foodmenu";
    	 Statement statement;
    	 try {
			statement=(Statement) connection.createStatement();
				table.setModel(DbUtils.resultSetToTableModel(statement.executeQuery(query)));
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
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
	    
	                	      show();                                
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
	public Foodmenu() {
		setBackground(new Color(0, 139, 139));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 205, 50));
		panel.setPreferredSize(new Dimension(10, 100));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel date = new JLabel("Date :");
		date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		date.setBounds(30, 24, 53, 14);
		panel.add(date);
		
		textField = new JTextField();
		textField.setBounds(82, 23, 279, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMenu = new JLabel("Menu :");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMenu.setBounds(30, 49, 53, 14);
		panel.add(lblMenu);
		
		Menu = new JTextField();
		Menu.setColumns(10);
		Menu.setBounds(82, 48, 279, 20);
		panel.add(Menu);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String query=	"INSERT INTO `m_m_m_s`.`foodmenu` (`date`, `menu`) VALUES ('"+date.getText()+"', '"+Menu.getText()+"')";
			 executeSQLQuery(query,"insert");  
			}
		});
		btnNewButton.setBounds(371, 47, 53, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		con= new Dbsconnect();

	}
}
