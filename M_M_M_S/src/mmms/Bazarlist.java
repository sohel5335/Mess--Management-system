package mmms;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bazarlist extends JPanel {
	private JTextField date;
	private JTextField name;
	private JTable table;
	private Dbsconnect con;

	 public void show(){
    	 Connection connection=con.getconnection();
    	 String query="SELECT * FROM m_m_m_s.bazarlist";
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
     
	public Bazarlist() {
		setBackground(new Color(0, 128, 128));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(127, 255, 0));
		panel.setPreferredSize(new Dimension(10, 100));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(31, 11, 46, 19);
		panel.add(lblNewLabel);
		
		date = new JTextField();
		date.setBounds(87, 12, 146, 20);
		panel.add(date);
		date.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(24, 41, 61, 19);
		panel.add(lblName);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(87, 43, 146, 20);
		panel.add(name);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		String query ="INSERT INTO `m_m_m_s`.`bazarlist` (`Date`, `Name`) VALUES ('"+date.getText()+"', '"+name.getText()+"')";
        executeSQLQuery(query,"insert");
				
			}
		});
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setBounds(243, 44, 61, 19);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		con= new Dbsconnect();

	}
}
