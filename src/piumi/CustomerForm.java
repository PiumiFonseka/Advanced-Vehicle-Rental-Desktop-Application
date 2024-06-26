package piumi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Hiruni.Cars;

import javax.swing.JScrollPane;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerForm {

	private JFrame frame;
	private JTextField cus_id;
	private JTextField cus_name;
	private JTextField cus_email;
	private JTextField cus_NIC;
	private JTextField cus_num;
	private JTextField cus_address;
	private JTextField cus_emgName;
	private JTextField cus_emgNum;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<customer> customers = new ArrayList<>();  // List to store customer objects

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerForm window = new CustomerForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerForm() {
		initialize();
		displayTable();// display table data when running
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // This will center the frame on the screen

		JLabel lblNewLabel = new JLabel("ADD NEW CUSTOMER FORM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(414, 26, 574, 83);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Personal Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(41, 127, 294, 30);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Emergency Contacts");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(33, 291, 238, 30);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel cidLabel = new JLabel("Customer ID :");
		cidLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cidLabel.setBounds(57, 178, 119, 30);
		frame.getContentPane().add(cidLabel);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(438, 178, 119, 30);
		frame.getContentPane().add(lblName);

		JLabel lblNic = new JLabel("NIC :");
		lblNic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNic.setBounds(869, 178, 59, 30);
		frame.getContentPane().add(lblNic);

		JLabel lblContactNumber = new JLabel("Contact Number :");
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContactNumber.setBounds(57, 235, 155, 30);
		frame.getContentPane().add(lblContactNumber);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(438, 235, 119, 30);
		frame.getContentPane().add(lblEmail);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(837, 235, 119, 30);
		frame.getContentPane().add(lblAddress);

		cus_num = new JTextField();
		cus_num.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_num.setBounds(222, 235, 184, 37);
		frame.getContentPane().add(cus_num);
		cus_num.setColumns(10);

		cus_id = new JTextField();
		cus_id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_id.setColumns(10);
		cus_id.setBounds(222, 178, 184, 37);
		frame.getContentPane().add(cus_id);

		// Get the next available customer ID and set it in the text field
		int nextCusID = getNextCusID();
		cus_id.setText(Integer.toString(nextCusID));

		cus_name = new JTextField();
		cus_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_name.setColumns(10);
		cus_name.setBounds(518, 171, 280, 37);
		frame.getContentPane().add(cus_name);

		cus_email = new JTextField();
		cus_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_email.setColumns(10);
		cus_email.setBounds(518, 228, 280, 37);
		frame.getContentPane().add(cus_email);

		cus_NIC = new JTextField();
		cus_NIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_NIC.setColumns(10);
		cus_NIC.setBounds(993, 171, 231, 37);
		frame.getContentPane().add(cus_NIC);

		cus_address = new JTextField();
		cus_address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_address.setColumns(10);
		cus_address.setBounds(993, 233, 231, 37);
		frame.getContentPane().add(cus_address);

		JLabel lblNewLabel_2 = new JLabel("The emergency contact's name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(237, 318, 280, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		cus_emgName = new JTextField();
		cus_emgName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_emgName.setColumns(10);
		cus_emgName.setBounds(526, 317, 272, 37);
		frame.getContentPane().add(cus_emgName);
		
		cus_emgNum = new JTextField();
		cus_emgNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cus_emgNum.setColumns(10);
		cus_emgNum.setBounds(993, 317, 231, 37);
		cus_emgNum.setToolTipText("Phone number to contact at emergency"); // Adding a tooltip
		frame.getContentPane().add(cus_emgNum);
		

		JLabel lblNewLabel_4 = new JLabel("Contact Number:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(828, 321, 155, 33);
		lblNewLabel_4.setToolTipText("Phone number to contact at emergency"); // Adding a tooltip
		frame.getContentPane().add(lblNewLabel_4);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 0));
		separator.setBackground(new Color(255, 255, 0));
		separator.setBounds(41, 363, 1183, 17);
		frame.getContentPane().add(separator);

		//Submit Button
		JButton SubmitCustomer = new JButton("Submit");
		SubmitCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		SubmitCustomer.setBackground(new Color(243, 219, 50));
		SubmitCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		SubmitCustomer.setBounds(1124, 460, 132, 45);
		frame.getContentPane().add(SubmitCustomer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(20, 379, 1084, 283);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Customer ID", "Name", "Email", "NIC",
				"Contact Number", "Address", "Emgergency Name", "Emgergency Number" }));
		addMouseListenerToTable();

		//Clear Button
		JButton clearCustomer = new JButton("Clear");
		clearCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all fields?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        if (dialogResult == JOptionPane.YES_OPTION) {
		            clearbtn();
		        }
		    }
		});
		clearCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		clearCustomer.setBackground(new Color(243, 219, 50));
		clearCustomer.setBounds(1124, 531, 132, 45);
		frame.getContentPane().add(clearCustomer);

		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				customerInfo.main(new String[] {});
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_3.setBounds(29, 10, 77, 54);
		frame.getContentPane().add(lblNewLabel_3);

		//Edit Button
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCustomer();
			}

		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnEdit.setBackground(new Color(243, 219, 50));
		btnEdit.setBounds(1124, 597, 132, 45);
		frame.getContentPane().add(btnEdit);
		
		
		//Demo Button for Demonstration Purpose
		JButton btnNewButton = new JButton("Demo Button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dri_id.setText("1234");
				cus_name.setText("Piumi Fonseka");
				cus_email.setText("piu@gmail.com");
				cus_NIC.setText("655131655V");
				cus_num.setText("0778944562");
				cus_address.setText("Kurana, Negombo");
				cus_emgName.setText("Harshi Sliva");
				cus_emgNum.setText("0775844569");
		     
		  
			}
		});
		btnNewButton.setBackground(new Color(240, 210, 2));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(1108, 36, 126, 30);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	

	
	// Method to search for customers in the database based on the search text
	public static DefaultTableModel searchCustomers(String searchText) {
		Connection connection = null;
		DefaultTableModel model = new DefaultTableModel();

		try {
			connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM customer WHERE c_name LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + searchText + "%"); // Use LIKE to search for partial matches

			ResultSet resultSet = statement.executeQuery();

			// Populate the model with the search results
			model.setColumnIdentifiers(new String[] { "Customer ID", "Name", "Email", "NIC", "Contact Number",
					"Address", "Emgergency Name", "Emgergency Number" });
			while (resultSet.next()) {
				model.addRow(new Object[] { resultSet.getInt("c_id"), resultSet.getString("c_name"),
						resultSet.getString("c_phone"), resultSet.getString("c_phone"), resultSet.getString("c_email"),
						resultSet.getString("c_address"), resultSet.getString("c_emgName"),
						resultSet.getString("c_emgNum") });
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(connection);
		}

		return model;
	}

	// clearbtn() method is called in CLEAR Button
	private void clearbtn() {

		cus_name.setText("");
		cus_email.setText("");
		cus_NIC.setText("");
		cus_num.setText("");
		cus_address.setText("");
		cus_emgName.setText("");
		cus_emgNum.setText("");
	}

	// addCustomer() method is called in Submit Button
	private void addCustomer() {
		// Get data from user input
		String name = cus_name.getText();
		String email = cus_email.getText();
		String nic = cus_NIC.getText();
		// int phone = Integer.parseInt(cus_num.getText());
		String phoneText = cus_num.getText(); // Get phone number as string
		String address = cus_address.getText();
		String emgName = cus_emgName.getText();
		// int emgphone = Integer.parseInt(cus_emgNum.getText());
		String emgphoneText = cus_emgNum.getText(); // Get emg phone number as string
		
		// Validate inputs
	    boolean isValidEmail = validateEmail(email);
	    boolean isValidNIC = validateNIC(nic);
	    boolean isValidPhoneNumber = validatePhoneNumber(phoneText);
	    boolean isValidEmergencyContactNumber = validateEmergencyContactNumber(emgphoneText);

	    // Set background color based on validation result
	    cus_email.setBackground(isValidEmail ? Color.WHITE : Color.PINK);
	    cus_NIC.setBackground(isValidNIC ? Color.WHITE : Color.PINK);
	    cus_num.setBackground(isValidPhoneNumber ? Color.WHITE : Color.PINK);
	    cus_emgNum.setBackground(isValidEmergencyContactNumber ? Color.WHITE : Color.PINK);

	    
	    

		// Validate inputs
		if (!validateEmail(email)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid email address.");
			return;
		}

		if (!validateNIC(nic)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid NIC.");
			return;
		}

		if (!validatePhoneNumber(phoneText)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit phone number.");
			return;
		}
	

		if (!validateEmergencyContactNumber(emgphoneText)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit emergency contact number.");
			return;
		}
		
		

	    
		// Convert validated phone numbers from string to integer
		int phone = Integer.parseInt(phoneText); // Convert validated phone number from string to integer
		int emgphone = Integer.parseInt(emgphoneText); // Convert validated emg phone number from string to integer
		

		customer cus = new customer(0, name, email, nic, phone, address, emgName, emgphone);
		customers.add(cus);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Enter data into Database
				String query = "INSERT INTO customer (c_name,c_email,c_NIC,c_phone,c_address,c_emgName,c_emgNum) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, cus.getName());
				preparedStatement.setString(2, cus.getEmail());
				preparedStatement.setString(3, cus.getNic());
				preparedStatement.setInt(4, cus.getPhone());
				preparedStatement.setString(5, cus.getAddress());
				preparedStatement.setString(6, cus.getEmgName());
				preparedStatement.setInt(7, cus.getEmgphone());

				int rowsAffected = preparedStatement.executeUpdate();

				// Check if a row is selected
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated id

						// Update the ID field with the generated ID
						cus_id.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Customer added successfully!");
						clearbtn();
						displayTable(); // display table with added customer details
						// updateTotalCustomerCount(); // Update the count
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to add Customer.");
					}
					preparedStatement.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

	}

	// Get the next available car ID by finding the maximum existing ID and adding 1
	private int getNextCusID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(c_id) FROM customer";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					nextID = resultSet.getInt(1) + 1;
				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return nextID;
	}

	// display data in table
	private void displayTable() {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // Clear existing data

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM customer";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("c_id");
					String name = resultSet.getString("c_name");
					String email = resultSet.getString("c_email");
					String nic = resultSet.getString("c_NIC");
					int phone = resultSet.getInt("c_phone");
					String address = resultSet.getString("c_address");
					String emgName = resultSet.getString("c_emgName");
					int emgPhone = resultSet.getInt("c_emgNum");

					customer cus = new customer(id, name, email, nic, phone, address, emgName, emgPhone);
					customers.add(cus);

					Vector<Object> row = new Vector<>();
					row.add(cus.getId());
					row.add(cus.getName());
					row.add(cus.getEmail());
					row.add(cus.getNic());
					row.add(cus.getPhone());
					row.add(cus.getAddress());
					row.add(cus.getEmgName());
					row.add(cus.getEmgphone());

					tableModel.addRow(row);
				}

				resultSet.close();
				statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	

	}
	
			

	// Add a MouseListener method to the JTable (mostly use for edit method)
		private void addMouseListenerToTable() {
	table.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            // Get data from the selected row
	            String id = tableModel.getValueAt(selectedRow, 0).toString();
	            String name = tableModel.getValueAt(selectedRow, 1).toString();
	            String email = tableModel.getValueAt(selectedRow, 2).toString();
	            String nic = tableModel.getValueAt(selectedRow, 3).toString();
	            String phone = tableModel.getValueAt(selectedRow, 4).toString();
	            String address = tableModel.getValueAt(selectedRow, 5).toString();
	            String emgName = tableModel.getValueAt(selectedRow, 6).toString();
	            String emgNum = tableModel.getValueAt(selectedRow, 7).toString();

	            // Set the retrieved data to text fields
	            cus_id.setText(id);
	            cus_name.setText(name);
	            cus_email.setText(email);
	            cus_NIC.setText(nic);
	            cus_num.setText(phone);
	            cus_address.setText(address);
	            cus_emgName.setText(emgName);
	            cus_emgNum.setText(emgNum);
	            
	            // Retrieve the phone numbers
                String phonecus =tableModel.getValueAt(selectedRow, 4).toString();
                String phoneemgNum = tableModel.getValueAt(selectedRow, 7).toString();


                // Add a leading zero if it's missing
                if (phone.length() == 9) { // phone number has 9 digits (in database)
                	phonecus = "0" + phone;
                }

                // Add a leading zero if it's missing
                if (phone.length() == 9) { // phone number has 9 digits (in database)
                	phoneemgNum = "0" + phone;
                }
                
                // Set the phone number to text field
                cus_num.setText(phonecus);
                cus_emgNum.setText(phoneemgNum);

                
	        }
	    }
	});
	
}
	
	
						
	// editCustomer() method is called in EDIT Button
		private void editCustomer() {
			int selectedRowIndex = table.getSelectedRow();

			if (selectedRowIndex >= 0) {
				int id = (int) tableModel.getValueAt(selectedRowIndex, 0);
				String name = cus_name.getText();
				String email = cus_email.getText();
				String nic = cus_NIC.getText();
				String phoneText = cus_num.getText();
				String emgPhoneText = cus_emgNum.getText();

				// Validate inputs
			    boolean isValidEmail = validateEmail(email);
			    boolean isValidNIC = validateNIC(nic);
			    boolean isValidPhoneNumber = validatePhoneNumber(phoneText);
			    boolean isValidEmergencyContactNumber = validateEmergencyContactNumber(emgPhoneText);

			    // Set background color to light red based on validation result
			    cus_email.setBackground(isValidEmail ? Color.WHITE : Color.PINK);
			    cus_NIC.setBackground(isValidNIC ? Color.WHITE : Color.PINK);
			    cus_num.setBackground(isValidPhoneNumber ? Color.WHITE : Color.PINK);
			    cus_emgNum.setBackground(isValidEmergencyContactNumber ? Color.WHITE : Color.PINK);

			    
				// Check for empty strings before parsing
				if (name.isEmpty() || email.isEmpty() || nic.isEmpty() || phoneText.isEmpty() || emgPhoneText.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
					return;
				}

				int phone = 0;
				int emgPhone = 0;

				 try {
			            phone = Integer.parseInt(phoneText);
			            emgPhone = Integer.parseInt(emgPhoneText);
			        } catch (NumberFormatException e) {
			            JOptionPane.showMessageDialog(frame, "Invalid phone number.");
			            return;
			        }

			        // Validate inputs
			        if (!validateEmail(email)) {
			            JOptionPane.showMessageDialog(frame, "Please enter a valid email address.");
			            return;
			        }

			        if (!validateNIC(nic)) {
			            JOptionPane.showMessageDialog(frame, "Please enter a valid NIC.");
			            return;
			        }

			        if (!validatePhoneNumber(phoneText)) {
			            JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit phone number.");
			            return;
			        }

			        if (!validateEmergencyContactNumber(emgPhoneText)) {
			            JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit emergency contact number.");
			            return;
			        }

				
		       	        
				String address = cus_address.getText();
				String emgName = cus_emgName.getText();

				// Update customer object in the list
				for (customer cus : customers) {
					if (cus.getId() == id) {
						cus.setName(name);
						cus.setEmail(email);
						cus.setNic(nic);
						cus.setPhone(phone);
						cus.setAddress(address);
						cus.setEmgName(emgName);
						cus.setEmgphone(emgPhone);
						break;
					}
				}

				Connection connection = DatabaseConnection.getConnection();
				if (connection != null) {
					try {
						String query = "UPDATE customer SET c_name=?, c_email=?, c_NIC=?, c_phone=?, c_address=?, c_emgName=?, c_emgNum=? WHERE c_id=?";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, email);
						preparedStatement.setString(3, nic);
						preparedStatement.setInt(4, phone);
						preparedStatement.setString(5, address);
						preparedStatement.setString(6, emgName);
						preparedStatement.setInt(7, emgPhone);
						preparedStatement.setInt(8, id);

						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(frame, "Customer updated successfully!");
							clearbtn();
							displayTable();
						} else {
							JOptionPane.showMessageDialog(frame, "Unable to update Customer.");
						}

						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DatabaseConnection.closeConnection(connection);
					}
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a customer to edit.");
			}
		}



	// Validation for user inputs for email, NIC and phone numbers
		
	// Validation method for email
	private boolean validateEmail(String email) {
		String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return Pattern.matches(emailPattern, email);
	}

	// Validation method for NIC
	private boolean validateNIC(String nic) {
		// Pattern for old NIC (9 letters and one letter)
		String oldNICPattern = "^[0-9]{9}[Vv]$";

		// Pattern for new NIC (12 numbers)
		String newNICPattern = "^[0-9]{12}$";

		// Check if NIC matches old or new NIC pattern
		return Pattern.matches(oldNICPattern, nic) || Pattern.matches(newNICPattern, nic);
	}

	// Validation method for phone number
	private boolean validatePhoneNumber(String phoneNumber) {
		// Check if the phone number contains exactly 10 digits
		return phoneNumber.matches("\\d{10}");
	}

	// Validation method for phone number
	private boolean validateEmergencyContactNumber(String emgphoneNumber) {
		// Check if the phone number contains exactly 10 digits
		return emgphoneNumber.matches("\\d{10}");
	}

}
