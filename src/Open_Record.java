import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Open_Record extends JFrame implements ActionListener, ItemListener 
{
	private JLabel IdNumberL, connectionL;
	private JComboBox IdNumberCB;
	private JButton enterB, cancelB;
	private JPanel itemP;
	private ButtonGroup itemBG;
	private JRadioButton orderRB, customerRB, gameRB;
	
	Connection connection = null;
	
	public Open_Record() 
	{
		connection = sqliteConnection.dbConnector();
		
		setTitle("Open Record");
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
					if (orderRB.isSelected()) 
					{
						String sql = "select * from New_Order where New_Order.Order_ID = ?";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, comboBoxText);
						ResultSet rst = pst.executeQuery();
						
						while (rst.next()) 
						{
							home.IdNumberTF.setText(rst.getString("Order_ID"));
							home.firstTF.setText(rst.getString("Order_Date"));
							home.secondTF.setText(rst.getString("Delivery_Date"));
							home.thirdTF.setText(rst.getString("Order_Price"));
							home.customerIdCB.setSelectedItem(rst.getString("Customer_ID"));
							home.gameIdCB.setSelectedItem(rst.getString("Game_ID"));
						}
						
						rst.close();
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.firstL.setText("Order Date");
						home.secondL.setText("Delivery Date");
						home.thirdL.setText("Price");
						
						home.fourthL.setEnabled(false);
						home.fourthTF.setEnabled(false);
						home.fifthL.setEnabled(false);
						home.fifthTF.setEnabled(false);
						home.sixthL.setEnabled(false);
						home.sixthTF.setEnabled(false);
						home.seventhL.setEnabled(false);
						home.seventhTF.setEnabled(false);
						home.eighthL.setEnabled(false);
						home.eighthTF.setEnabled(false);
					}
					
					else if (customerRB.isSelected()) 
					{
						String sql = "select * from Customer where Customer.Customer_ID = ?";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, comboBoxText);
						ResultSet rst = pst.executeQuery();
						
						while (rst.next()) 
						{
							home.IdNumberTF.setText(rst.getString("Customer_ID"));
							home.firstTF.setText(rst.getString("First_Name"));
							home.secondTF.setText(rst.getString("Last_Name"));
							home.thirdTF.setText(rst.getString("Address"));
							home.fourthTF.setText(rst.getString("City"));
							home.fifthTF.setText(rst.getString("State"));
							home.sixthTF.setText(rst.getString("Zip"));
							home.seventhTF.setText(rst.getString("Phone"));
							home.eighthTF.setText(rst.getString("Email"));
						}
						
						rst.close();
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.firstL.setText("First Name");
						home.secondL.setText("Last Name");
						home.thirdL.setText("Address");
						home.fourthL.setText("City");
						home.fifthL.setText("State");
						home.sixthL.setText("Zip");
						home.seventhL.setText("Phone");
						
						home.customerIdL.setEnabled(false);
						home.customerIdCB.setEnabled(false);
						home.gameIdL.setEnabled(false);
						home.gameIdCB.setEnabled(false);
					}
					
					else if (gameRB.isSelected()) 
					{
						String sql = "select * from Game where Game.Game_ID = ?";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, comboBoxText);
						ResultSet rst = pst.executeQuery();
						
						while (rst.next()) 
						{
							home.IdNumberTF.setText(rst.getString("Game_ID"));
							home.firstTF.setText(rst.getString("Game_Name"));
							home.secondTF.setText(rst.getString("Developer"));
							home.thirdTF.setText(rst.getString("Genre"));
							home.fourthTF.setText(rst.getString("Platform"));
							home.fifthTF.setText(rst.getString("Release_Date"));
							home.sixthTF.setText(rst.getString("Game_Price"));
							home.seventhTF.setText(rst.getString("Game_Quantity"));
						}
						
						rst.close();
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.firstL.setText("Title");
						home.secondL.setText("Developer");
						home.thirdL.setText("Genre");
						home.fourthL.setText("System");
						home.fifthL.setText("Release Date");
						home.sixthL.setText("Price");
						home.seventhL.setText("Quantity");
						
						home.eighthL.setEnabled(false);
						home.eighthTF.setEnabled(false);
						home.customerIdL.setEnabled(false);
						home.customerIdCB.setEnabled(false);
						home.gameIdL.setEnabled(false);
						home.gameIdCB.setEnabled(false);
					}
					
					dispose();
				}
				
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(this, ex);
					System.out.print(ex);
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
				
				String sql = "select New_Order.Order_ID from New_Order";
				
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
				
				String sql = "select Customer.Customer_ID from Customer";
				
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
				
				String sql = "select Game.Game_ID from Game";
				
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
