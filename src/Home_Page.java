import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Home_Page extends JFrame implements ActionListener
{
	public JLabel IdNumberL, firstL, secondL, thirdL, fourthL, fifthL, sixthL, seventhL, eighthL, customerIdL, gameIdL, connectionL;
	public JTextField IdNumberTF, firstTF, secondTF, thirdTF, fourthTF, fifthTF, sixthTF, seventhTF, eighthTF;
	public JComboBox customerIdCB, gameIdCB;
	
	private JMenuBar menuMB = new JMenuBar();
	private JMenu fileM, helpM;
	private JMenuItem newMI, openMI, saveMI, deleteMI, undeleteMI, purgeMI, exitMI, aboutMI;
	
	Connection connection = null;
	
	public Home_Page() 
	{
		connection = sqliteConnection.dbConnector();
		
		setTitle("Game Order Data");
		setLayout(null);
		setSize(640, 350);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuMB);
		setFileMenu();
		setHelpMenu();
		
		IdNumberL = new JLabel("Customer Number");
		firstL = new JLabel("First Name");
		secondL = new JLabel("Last Name");
		thirdL = new JLabel("Address");
		fourthL = new JLabel("City");
		fifthL = new JLabel("State");
		sixthL = new JLabel("Zip");
		seventhL = new JLabel("Phone");
		eighthL = new JLabel("Email");
		customerIdL = new JLabel("CustomerID");
		gameIdL = new JLabel("GameID");
		connectionL = new JLabel("");
		
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
		
		IdNumberTF = new JTextField("");
		IdNumberTF.setEditable(false);
		firstTF = new JTextField();
		secondTF = new JTextField();
		thirdTF = new JTextField();
		fourthTF = new JTextField();
		fifthTF = new JTextField();
		sixthTF = new JTextField();
		seventhTF = new JTextField();
		eighthTF = new JTextField();
		
		customerIdCB = new JComboBox();
		gameIdCB = new JComboBox();
		customerIdCB.insertItemAt("", 0);
		gameIdCB.insertItemAt("", 0);
		customerIdFillCombo();
		gameIdFillCombo();
		
		IdNumberL.setBounds(20, 30, 150, 25);
		IdNumberTF.setBounds(140, 30, 200, 25);
		firstL.setBounds(20, 80, 100, 25);
		firstTF.setBounds(100, 80, 200, 25);
		secondL.setBounds(20, 110, 100, 25);
		secondTF.setBounds(100, 110, 200, 25);
		thirdL.setBounds(20, 140, 100, 25);
		thirdTF.setBounds(100, 140, 200, 25);
		fourthL.setBounds(20, 170, 100, 25);
		fourthTF.setBounds(100, 170, 200, 25);
		fifthL.setBounds(20, 200, 100, 25);
		fifthTF.setBounds(100, 200, 200, 25);
		sixthL.setBounds(320, 80, 100, 25);
		sixthTF.setBounds(400, 80, 200, 25);
		seventhL.setBounds(320, 110, 100, 25);
		seventhTF.setBounds(400, 110, 200, 25);
		eighthL.setBounds(320, 140, 100, 25);
		eighthTF.setBounds(400, 140, 200, 25);
		customerIdL.setBounds(320, 170, 100, 25);
		customerIdCB.setBounds(400, 170, 200, 25);
		gameIdL.setBounds(320, 200, 100, 25);
		gameIdCB.setBounds(400, 200, 200, 25);
		connectionL.setBounds(20, 250, 200, 25);
		
		add(IdNumberL);
		add(IdNumberTF);
		add(firstL);
		add(firstTF);
		add(secondL);
		add(secondTF);
		add(thirdL);
		add(thirdTF);
		add(fourthL);
		add(fourthTF);
		add(fifthL);
		add(fifthTF);
		add(sixthL);
		add(sixthTF);
		add(seventhL);
		add(seventhTF);
		add(eighthL);
		add(eighthTF);
		add(customerIdL);
		add(customerIdCB);
		add(gameIdL);
		add(gameIdCB);
		add(connectionL);

		setVisible(true);
	}
	
	private void setFileMenu() 
	{
		fileM = new JMenu("File");
		menuMB.add(fileM);
		
		newMI = new JMenuItem("New");
		fileM.add(newMI);
		newMI.addActionListener(this);
		
		openMI = new JMenuItem("Open");
		fileM.add(openMI);
		openMI.addActionListener(this);
		
		saveMI = new JMenuItem("Save");
		fileM.add(saveMI);
		saveMI.addActionListener(this);
		
		deleteMI = new JMenuItem("Delete");
		fileM.add(deleteMI);
		deleteMI.addActionListener(this);
		
		undeleteMI = new JMenuItem("Undelete");
		fileM.add(undeleteMI);
		undeleteMI.addActionListener(this);
		
		purgeMI = new JMenuItem("Purge");
		fileM.add(purgeMI);
		purgeMI.addActionListener(this);
		
		exitMI = new JMenuItem("Exit");
		fileM.add(exitMI);
		exitMI.addActionListener(this);
	}
	
	private void setHelpMenu() 
	{
		helpM = new JMenu("Help");
		menuMB.add(helpM);
		
		aboutMI = new JMenuItem("About");
		helpM.add(aboutMI);
		aboutMI.addActionListener(this);
	}
	
	private void customerIdFillCombo() 
	{
		try 
		{
			String sql = "select Customer.Customer_ID from Customer";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			
			while (rst.next()) 
			{
				String customerId = rst.getString("Customer_ID");
				customerIdCB.addItem(customerId);
			}
			
			rst.close();
			pst.close();
		}
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	private void gameIdFillCombo() 
	{
		try 
		{
			String sql = "select Game.Game_ID from Game";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			
			while (rst.next()) 
			{
				String gameId = rst.getString("Game_ID");
				gameIdCB.addItem(gameId);
			}
			
			rst.close();
			pst.close();
		}
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String text = "";
		
		if (e.getSource() == newMI) 
		{
			New_Record add = new New_Record();
			
			dispose();
		}
		
		else if (e.getSource() == openMI) 
		{
			Open_Record open = new Open_Record();
			
			dispose();
		}
		
		else if (e.getSource() == saveMI) 
		{		
			if (IdNumberTF.getText() == null || IdNumberTF.getText().trim().isEmpty()) 
			{
				JOptionPane.showMessageDialog(this, "Record Not Found!", "Save Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			
			else 
			{
				try 
				{
					String IdNumberText = IdNumberTF.getText();
					
					if (IdNumberL.getText() == "Order Number") 
					{
						String sql = "update New_Order set Order_Date = ?, Delivery_Date = ?, Order_Price = ?, Customer_ID = ?, Game_ID = ? where Order_ID =" + IdNumberText;
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, firstTF.getText());
						pst.setString(2, secondTF.getText());
						pst.setString(3, thirdTF.getText());
						pst.setString(4, (String)customerIdCB.getSelectedItem());
						pst.setString(5, (String)gameIdCB.getSelectedItem());
						pst.execute();					
						pst.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						customerIdCB.setSelectedItem("");
						gameIdCB.setSelectedItem("");
					}
					
					else if (IdNumberL.getText() == "Customer Number") 
					{
						String sql = "update Customer set First_Name = ?, Last_Name = ?, Address = ?, City = ?, State = ?, Zip = ?, Phone = ?, Email = ? where Customer.Customer_ID =" + IdNumberText;
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, firstTF.getText());
						pst.setString(2, secondTF.getText());
						pst.setString(3, thirdTF.getText());
						pst.setString(4, fourthTF.getText());
						pst.setString(5, fifthTF.getText());
						pst.setString(6, sixthTF.getText());
						pst.setString(7, seventhTF.getText());
						pst.setString(8, eighthTF.getText());
						pst.execute();					
						pst.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						fourthTF.setText("");
						fifthTF.setText("");
						sixthTF.setText("");
						seventhTF.setText("");
						eighthTF.setText("");
					}
					
					else if (IdNumberL.getText() == "Game Number") 
					{
						String sql = "update Game set Game_Name = ?, Developer = ?, Genre = ?, Platform = ?, Release_Date = ?, Game_Price = ?, Game_Quantity = ? where Game.Game_ID =" + IdNumberText;
						
						PreparedStatement pst = connection.prepareStatement(sql);
						pst.setString(1, firstTF.getText());
						pst.setString(2, secondTF.getText());
						pst.setString(3, thirdTF.getText());
						pst.setString(4, fourthTF.getText());
						pst.setString(5, fifthTF.getText());
						pst.setString(6, sixthTF.getText());
						pst.setString(7, seventhTF.getText());
						pst.execute();					
						pst.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						fourthTF.setText("");
						fifthTF.setText("");
						sixthTF.setText("");
						seventhTF.setText("");
					}
					
					JOptionPane.showMessageDialog(this, "Record Saved!", "Save Successful!", 
							JOptionPane.INFORMATION_MESSAGE);	
				}
				
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(this, ex);
				}
			}
		}
		
		else if (e.getSource() == deleteMI) 
		{
			if (IdNumberTF.getText() == null || IdNumberTF.getText().trim().isEmpty()) 
			{
				JOptionPane.showMessageDialog(this, "Record Not Found!", "Delete Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			
			else 
			{
				try 
				{
					String IdNumberText = IdNumberTF.getText();
					
					if (IdNumberL.getText() == "Order Number") 
					{
						String sqlInsert = "insert into Temp_Order (Order_ID, Order_Date, Delivery_Date, Order_Price, Customer_ID, Game_ID) values (?,?,?,?,?,?)";
						String sqlDelete = "delete from New_Order where New_Order.Order_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.setString(1, IdNumberTF.getText());
						pstInsert.setString(2, firstTF.getText());
						pstInsert.setString(3, secondTF.getText());
						pstInsert.setString(4, thirdTF.getText());
						pstInsert.setString(5, (String)customerIdCB.getSelectedItem());
						pstInsert.setString(6, (String)gameIdCB.getSelectedItem());
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						customerIdCB.setSelectedItem("");
						gameIdCB.setSelectedItem("");
					}
					
					else if (IdNumberL.getText() == "Customer Number") 
					{
						String sqlInsert = "insert into Temp_Customer (Customer_ID, First_Name, Last_Name, Address, City, State, Zip, Phone, Email) values (?,?,?,?,?,?,?,?,?)";
						String sqlDelete = "delete from Customer where Customer.Customer_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.setString(1, IdNumberTF.getText());
						pstInsert.setString(2, firstTF.getText());
						pstInsert.setString(3, secondTF.getText());
						pstInsert.setString(4, thirdTF.getText());
						pstInsert.setString(5, fourthTF.getText());
						pstInsert.setString(6, fifthTF.getText());
						pstInsert.setString(7, sixthTF.getText());
						pstInsert.setString(8, seventhTF.getText());
						pstInsert.setString(9, eighthTF.getText());
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						fourthTF.setText("");
						fifthTF.setText("");
						sixthTF.setText("");
						seventhTF.setText("");
						eighthTF.setText("");
					}
					
					else if (IdNumberL.getText() == "Game Number") 
					{
						String sqlInsert = "insert into Temp_Game (Game_ID, Game_Name, Developer, Genre, Platform, Release_Date, Game_Price, Game_Quantity) values (?,?,?,?,?,?,?,?)";
						String sqlDelete = "delete from Game where Game.Game_ID =" + IdNumberText;
						
						PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
						pstInsert.setString(1, IdNumberTF.getText());
						pstInsert.setString(2, firstTF.getText());
						pstInsert.setString(3, secondTF.getText());
						pstInsert.setString(4, thirdTF.getText());
						pstInsert.setString(5, fourthTF.getText());
						pstInsert.setString(6, fifthTF.getText());
						pstInsert.setString(7, sixthTF.getText());
						pstInsert.setString(8, seventhTF.getText());
						pstInsert.execute();					
						pstInsert.close();
						
						PreparedStatement pstDelete = connection.prepareStatement(sqlDelete);
						pstDelete.execute();
						pstDelete.close();
						
						IdNumberTF.setText("");
						firstTF.setText("");
						secondTF.setText("");
						thirdTF.setText("");
						fourthTF.setText("");
						fifthTF.setText("");
						sixthTF.setText("");
						seventhTF.setText("");
					}
					
					JOptionPane.showMessageDialog(this, "Record Deleted!", "Delete Successful!", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(this, ex);
				}
			}
		}
		
		else if (e.getSource() == undeleteMI) 
		{
			Undelete_Record undelete = new Undelete_Record();
			
			dispose();
		}
		
		else if (e.getSource() == purgeMI) 
		{			
			int purge = JOptionPane.showConfirmDialog(this, "Are you sure you want to permanently purge all deleted records?", 
					"Purge Deleted Records", JOptionPane.YES_NO_OPTION);
			
			if (purge == JOptionPane.YES_OPTION) 
			{
				try 
				{
					String sqlOrder = "delete from Temp_Order";
					PreparedStatement pstOrder = connection.prepareStatement(sqlOrder);
					pstOrder.execute();
					pstOrder.close();
					
					String sqlCustomer = "delete from Temp_Customer";
					PreparedStatement pstCustomer = connection.prepareStatement(sqlCustomer);
					pstCustomer.execute();
					pstCustomer.close();
					
					String sqlGame = "delete from Temp_Game";
					PreparedStatement pstGame = connection.prepareStatement(sqlGame);
					pstGame.execute();
					pstGame.close();
				}
				
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(this, ex);
				}
			}
		}
		
		else if (e.getSource() == exitMI) 
		{
			System.exit(0);
		}
		
		else if (e.getSource() == aboutMI) 
		{
			JOptionPane.showMessageDialog(this, "Game Store Project GUI\n" + "Author: Tyler Burger\n" 
					+ "CSCI239 2020", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) 
	{
		Home_Page home = new Home_Page();
	}
}
