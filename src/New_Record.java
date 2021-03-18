import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class New_Record extends JFrame implements ActionListener, ItemListener
{
	private JLabel IdNumberL, connectionL;
	private JTextField IdNumberTF;
	private JButton enterB, cancelB;
	private JTextArea recordDisplayTA;
	private JPanel itemP;
	private ButtonGroup itemBG;
	private JRadioButton orderRB, customerRB, gameRB;
	
	Connection connection = null;
	
	public New_Record() 
	{
		connection = sqliteConnection.dbConnector();
		
		setTitle("New Record");
		setLayout(null);
		setSize(525, 615);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IdNumberL = new JLabel("Customer Number");
		connectionL = new JLabel("");
		IdNumberTF = new JTextField();
		enterB = new JButton("OK");
		cancelB = new JButton("Cancel");
		recordDisplayTA = new JTextArea();
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
		
		itemP.setBorder(BorderFactory.createTitledBorder("Select Item To Add"));
		itemP.setBounds(30, 30, 270, 55);
		orderRB.setBounds(40, 50, 75, 25);
		customerRB.setBounds(120, 50, 100, 25);
		gameRB.setBounds(220, 50, 75, 25);
		IdNumberL.setBounds(30, 115, 150, 25);
		IdNumberTF.setBounds(150, 115, 150, 25);
		recordDisplayTA.setBounds(30, 165, 450, 350);
		enterB.setBounds(330, 30, 150, 50);
		cancelB.setBounds(330, 90, 150, 50);
		connectionL.setBounds(30, 535, 200, 25);
		
		itemP.add(orderRB);
		itemP.add(customerRB);
		itemP.add(gameRB);
		itemBG.add(orderRB);
		itemBG.add(customerRB);
		itemBG.add(gameRB);
		customerRB.setSelected(true);
		
		add(IdNumberL);
		add(connectionL);
		add(IdNumberTF);
		add(enterB);
		add(cancelB);
		add(recordDisplayTA);
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
			if (IdNumberTF.getText() == null || IdNumberTF.getText().trim().isEmpty()) 
			{
				JOptionPane.showMessageDialog(this, "Record Not Found!", "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			
			else 
			{
				String labelText = IdNumberL.getText();
				String textFieldText = IdNumberTF.getText();
				
				Home_Page home = new Home_Page();
				
				try 
				{
					if (orderRB.isSelected()) 
					{
						String sql = "insert into New_Order (Order_ID, Customer_ID, Game_ID) values (?,?,?)";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, textFieldText);
						pst.setString(2, "");
						pst.setString(3, "");
						pst.execute();					
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.IdNumberTF.setText(textFieldText);
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
						String sql = "insert into Customer (Customer_ID) values (?)";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, textFieldText);
						pst.execute();				
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.IdNumberTF.setText(textFieldText);
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
						String sql = "insert into Game (Game_ID) values (?)";
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, textFieldText);
						pst.execute();				
						pst.close();
						
						home.IdNumberL.setText(labelText);
						home.IdNumberTF.setText(textFieldText);
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
				recordDisplayTA.setText("");
				
				recordDisplayTA.setText("OrderID\tCustomerID\tGameID\n");
				
				String sql = "select New_Order.Order_ID, New_Order.Customer_ID, New_Order.Game_ID from New_Order";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String orderID = rst.getString("Order_ID");
					String customerID = rst.getString("Customer_ID");
					String gameID = rst.getString("Game_ID");
					recordDisplayTA.append(orderID + "\t" + customerID + "\t" + gameID + "\n");
				}
				
				rst.close();
				pst.close();
				
			}
			
			else if (e.getSource() == customerRB) 
			{
				IdNumberL.setText("Customer Number");
				recordDisplayTA.setText("");
				
				recordDisplayTA.setText("CustomerID\tName\n");
				
				String sql = "select Customer.Customer_ID, Customer.First_Name, Customer.Last_Name from Customer";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String customerID = rst.getString("Customer_ID");
					String firstName = rst.getString("First_Name");
					String lastName = rst.getString("Last_Name");
					recordDisplayTA.append(customerID + "\t" + firstName + " " + lastName + "\n");
				}
				
				rst.close();
				pst.close();
			}
			
			else if (e.getSource() == gameRB) 
			{
				IdNumberL.setText("Game Number");
				recordDisplayTA.setText("");
				
				recordDisplayTA.setText("GameID\tSystem\tTitle\n");
				
				String sql = "select Game.Game_ID, Game.Game_Name, Game.Platform from Game";
				
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rst = pst.executeQuery();
				
				while (rst.next()) 
				{
					String gameID = rst.getString("Game_ID");
					String title = rst.getString("Game_Name");
					String platform = rst.getString("Platform");
					recordDisplayTA.append(gameID + "\t" + platform + "\t" + title + "\n");
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
