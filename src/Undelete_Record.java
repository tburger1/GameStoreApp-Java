import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Undelete_Record extends JFrame implements ActionListener, ItemListener 
{
	private JLabel IdNumberL, connectionL;
	private JComboBox IdNumberCB;
	private JButton enterB, cancelB;
	private JPanel itemP;
	private ButtonGroup itemBG;
	private JRadioButton orderRB, customerRB, gameRB;
	
	Connection connection = null;
	
	public Undelete_Record() 
	{
		connection = sqliteConnection.dbConnector();
		
		setTitle("Undelete Record");
		setLayout(null);
		setSize(525, 295);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IdNumberL = new JLabel("Customer Number");
		connectionL = new JLabel("");
		IdNumberCB = new JComboBox();
		enterB = new JButton("OK");
		cancelB = new JButton("Cancel");
		itemP = new JPanel();
		itemBG = new ButtonGroup();
		orderRB = new JRadioButton("Order");
		customerRB = new JRadioButton("Customer");
		gameRB = new JRadioButton("Game");
		
		if (connection != null) 
		{
			connectionL.setText("Connection Successful");
			connectionL.setForeground(Color.blue);
		}
		
		else 
		{
			connectionL.setText("Connection Failed");
			connectionL.setForeground(Color.red);
		}
		
		enterB.addActionListener(this);
		cancelB.addActionListener(this);
		orderRB.addItemListener(this);
		customerRB.addItemListener(this);
		gameRB.addItemListener(this);
		
		itemP.setBorder(BorderFactory.createTitledBorder("Select Item To Open"));
		itemP.setBounds(30, 30, 270, 55);
		orderRB.setBounds(40, 50, 75, 25);
		customerRB.setBounds(120, 50, 100, 25);
		gameRB.setBounds(220, 50, 75, 25);
		IdNumberL.setBounds(30, 165, 150, 25);
		IdNumberCB.setBounds(150, 165, 330, 25);
		enterB.setBounds(330, 30, 150, 50);
		cancelB.setBounds(330, 90, 150, 50);
		connectionL.setBounds(30, 215, 200, 25);
		
		itemP.add(orderRB);
		itemP.add(customerRB);
		itemP.add(gameRB);
		itemBG.add(orderRB);
		itemBG.add(customerRB);
		itemBG.add(gameRB);
		customerRB.setSelected(true);
		
		add(IdNumberL);
		add(connectionL);
		add(IdNumberCB);
		add(enterB);
		add(cancelB);
		add(itemP);
		add(orderRB);
		add(customerRB);
		add(gameRB);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == enterB) 
		{
			if (IdNumberCB.getSelectedIndex() == -1 || IdNumberCB.getSelectedItem() == "") 
			{
				JOptionPane.showMessageDialog(this, "Record Not Found!", "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			
			else 
			{
				String labelText = IdNumberL.getText();
				String comboBoxText = (String)IdNumberCB.getSelectedItem();
				
				Home_Page home = new Home_Page();
				
				try 
				{
					String IdNumberText = (String)IdNumberCB.getSelectedItem();
					
					if (orderRB.isSelected()) 
					{
						String sqlInsert = "insert into New_Order select * From Temp_Order where Temp_Order.Order_ID =" + IdNumberText;
						String sqlDelete = "delete from Temp_Order where Temp_Order.Order_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
					}
					
					else if (customerRB.isSelected()) 
					{
						String sqlInsert = "insert into Customer select * From Temp_Customer where Temp_Customer.Customer_ID =" + IdNumberText;
						String sqlDelete = "delete from Temp_Customer where Temp_Customer.Order_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
					}
					
					else if (gameRB.isSelected()) 
					{
						String sqlInsert = "insert into Game select * From Temp_Game where Temp_Game.Game_ID =" + IdNumberText;
						String sqlDelete = "delete from Temp_Game where Temp_Game.Game_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
					}
					
					JOptionPane.showMessageDialog(this, "Record Undelete!", "Undelete Successful", 
							JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
				}
				
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(this, ex);
				}
			}
		}
		
		else if (e.getSource() == cancelB) 
		{
			Home_Page home = new Home_Page();
			
			dispose();
		}
	}

	public void itemStateChanged(ItemEvent e) 
	{
		try 
		{
			if (e.getSource() == orderRB) 
			{
				IdNumberL.setText("Order Number");
				
				IdNumberCB.removeAllItems();
				IdNumberCB.insertItemAt("", 0);
				
				String sql = "select Temp_Order.Order_ID from Temp_Order";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String orderID = rst.getString("Order_ID");
					IdNumberCB.addItem(orderID);
				}
				
				rst.close();
				pst.close();
			}
			
			else if (e.getSource() == customerRB) 
			{
				IdNumberL.setText("Customer Number");
				
				IdNumberCB.removeAllItems();
				IdNumberCB.insertItemAt("", 0);
				
				String sql = "select Temp_Customer.Customer_ID from Temp_Customer";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String customerId = rst.getString("Customer_ID");
					IdNumberCB.addItem(customerId);
				}
				
				rst.close();
				pst.close();
			}
			
			else if (e.getSource() == gameRB) 
			{
				IdNumberL.setText("Game Number");
				
				IdNumberCB.removeAllItems();
				IdNumberCB.insertItemAt("", 0);
				
				String sql = "select Temp_Game.Game_ID from Temp_Game";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String gameId = rst.getString("Game_ID");
					IdNumberCB.addItem(gameId);
				}
				
				rst.close();
				pst.close();
			}	
		}
		
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(this, ex);
		}
	}
}
