package piumi;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Hiruni.Dashboard;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import piumi.CustomerForm;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Hirushima.reservationForm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class customerInfo {

	private JFrame frame;
	private JTextField txtSearchTheCustomer;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerInfo window = new customerInfo();
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
	public customerInfo() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // This will center the frame on the screen
		
		JLabel lblNewLabel = new JLabel("CUSTOMER INFORMATION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(434, 23, 550, 83);
		frame.getContentPane().add(lblNewLabel);

		txtSearchTheCustomer = new JTextField();
		txtSearchTheCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve the search query entered by the user
				String searchText = txtSearchTheCustomer.getText().trim();

				// Perform search operation in the database and update the table
				searchAndDisplayResults(searchText);
			}
		});	
		txtSearchTheCustomer.setForeground(new Color(0, 0, 0));
		txtSearchTheCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearchTheCustomer.setBounds(993, 112, 199, 38);
		frame.getContentPane().add(txtSearchTheCustomer);
		txtSearchTheCustomer.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel(" Back\r\n");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {});
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_5.setBounds(10, 10, 212, 45);
		frame.getContentPane().add(lblNewLabel_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(32, 172, 1040, 281);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Customer ID", "Name", "NIC",
				"Contact Number", "Email", "Address", "Emgergency Name", "Emgergency Number" }));

		lblNewLabel_2 = new JLabel("Total number of customers : " + getTotalNumberOfCustomers());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(515, 490, 413, 32);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Loyalty customers");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_2_1.setBounds(42, 465, 413, 32);
		frame.getContentPane().add(lblNewLabel_2_1);

		//Edit Button
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerForm.main(new String[] {});
			}
		});
		btnEdit.setBackground(new Color(243, 219, 50));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnEdit.setBounds(1109, 301, 124, 38);
		frame.getContentPane().add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});
		btnDelete.setBackground(new Color(243, 219, 50));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		btnDelete.setBounds(1109, 246, 124, 45);
		frame.getContentPane().add(btnDelete);

		//View Button
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex != -1) { // Ensure a row is selected
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Object[] customerData = new Object[model.getColumnCount()];
					for (int i = 0; i < model.getColumnCount(); i++) {
						customerData[i] = model.getValueAt(selectedRowIndex, i);
					}
					frame.dispose();
					customerProfile.showCustomerDetails(customerData);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a customer.");
				}
			}
		});
		btnView.setBackground(new Color(243, 219, 50));
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnView.setBounds(1109, 191, 124, 45);
		frame.getContentPane().add(btnView);

		//Refresh Button
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// refreshTable();
				refreshPage();
			}
		});
		btnRefresh.setBackground(new Color(243, 219, 50));
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		btnRefresh.setBounds(1109, 349, 124, 45);
		frame.getContentPane().add(btnRefresh);

		JScrollPane loyalty_Table = new JScrollPane();
		loyalty_Table.setBounds(61, 497, 363, 138);
		frame.getContentPane().add(loyalty_Table);

		table_1 = new JTable();
		loyalty_Table.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Customer ID", "Customer Name", "Number of Reservations" }));

		displayLoyaltyCustomers();
		
		//Add new Customer Button
		JButton btnAddCus = new JButton("Add new Customer");
		btnAddCus.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		btnAddCus.setBackground(new Color(243, 219, 50));
		btnAddCus.setBounds(966, 525, 267, 45);
		frame.getContentPane().add(btnAddCus);

		//Full Payment Button
		JButton btnFullPayment = new JButton("Full Payment");
		btnFullPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); 
				bhagya.payment.main(new String[] {});
				
			}
		});
		btnFullPayment.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnFullPayment.setBackground(new Color(243, 219, 50));
		btnFullPayment.setBounds(966, 605, 267, 45);
		frame.getContentPane().add(btnFullPayment);
		btnAddCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				CustomerForm.main(new String[] {}); // Open the Customers section
			}
		});

		// display data from database in this table
		DefaultTableModel customerTableModel = getCustomerDataFromDatabase();
		table.setModel(customerTableModel);

		JLabel lblNewLabel_3 = new JLabel("search the customer detail here...");
		lblNewLabel_3.setForeground(new Color(192, 192, 192));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_3.setBounds(693, 119, 291, 21);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_2_1_1 = new JLabel("Loyalty customers will receive 15% discount for their reservation.");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBounds(61, 627, 452, 32);
		frame.getContentPane().add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("x");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshTable();
				txtSearchTheCustomer.setText(""); // to clear the typed text
			}
		});
		lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(1202, 119, 29, 21);
		frame.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Customers with the most number of reservation are considered loyal (minimum 3 reservations is required.)");
		lblNewLabel_2_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1_1_1.setBounds(61, 645, 618, 32);
		frame.getContentPane().add(lblNewLabel_2_1_1_1);
	

	}

	
	private int getTotalNumberOfCustomers() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM customer";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalCustomers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					return totalCustomers;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return 0; // Return 0 if there's an error or no customers found
	}

	private void updateTotalCustomerCount() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM customer";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalCustomers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					lblNewLabel_2.setText("Total number of customers : " + totalCustomers);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error updating total customer count: " + e.getMessage());
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Database connection not available.");
		}
	}

	private void deleteCustomer() {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex != -1) {
			int confirmDelete = JOptionPane.showConfirmDialog(frame, "Do you want to delete this customer record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);

			if (confirmDelete == JOptionPane.YES_OPTION) {
				int customerId = (int) table.getValueAt(selectedRowIndex, 0);

				try (Connection connection = DatabaseConnection.getConnection();
						Statement statement = connection.createStatement()) {
					String query = "DELETE FROM customer WHERE c_id = " + customerId;
					int rowsAffected = statement.executeUpdate(query);

					if (rowsAffected > 0) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(selectedRowIndex);
						updateTotalCustomerCount();
						JOptionPane.showMessageDialog(frame, "Customer deleted successfully.");
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete customer. Customer may not exist.");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, "Error deleting customer: " + e.getMessage());
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a customer to delete.");
		}
	}

	// Method to perform search operation in the database and update the table
	private void searchAndDisplayResults(String searchText) {
		DefaultTableModel searchResultsModel = new DefaultTableModel(new Object[][] {}, new String[] { "Customer ID",
				"Name", "NIC", "Contact Number", "Email", "Address", "Emergency Name", "Emergency Number" });

		// Fetch customer data from the database and populate the searchResultsModel
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM customer WHERE c_name LIKE ? OR c_NIC LIKE ? OR c_phone LIKE ? OR c_email LIKE ? OR c_address LIKE ? OR c_emgName LIKE ? OR c_emgNum LIKE ?");

				// Bind the search text to each parameter with wildcard % to match any characters
				String likePattern = "%" + searchText + "%";
				for (int i = 1; i <= 7; i++) {
					statement.setString(i, likePattern);
				}

				ResultSet resultSet = statement.executeQuery();

				// Check if there are any search results
				if (!resultSet.isBeforeFirst()) {
					// No search results found, display a message
					  JOptionPane.showMessageDialog(frame, "No search results found.", "Customer Search Error", JOptionPane.ERROR_MESSAGE);
				} else {
					// Iterate through the result set and add each row to the searchResultsModel
					while (resultSet.next()) {
						Object[] rowData = new Object[8]; // 8 columns in the table
						// Populate rowData with data from the result set
						rowData[0] = resultSet.getInt("c_id");
						rowData[1] = resultSet.getString("c_name");
						rowData[2] = resultSet.getString("c_NIC");
						rowData[3] = resultSet.getInt("c_phone");
						rowData[4] = resultSet.getString("c_email");
						rowData[5] = resultSet.getString("c_address");
						rowData[6] = resultSet.getString("c_emgName");
						rowData[7] = resultSet.getInt("c_emgNum");

						searchResultsModel.addRow(rowData);
					}

					resultSet.close();
					statement.close();

					// Update the table with the search results
					table.setModel(searchResultsModel);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Method to refresh the table with full data
	private void refreshTable() {
		DefaultTableModel fullTableModel = getCustomerDataFromDatabase();
		table.setModel(fullTableModel);
	}

	public static DefaultTableModel getCustomerDataFromDatabase() {
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Customer ID", "Name",
				"NIC", "Contact Number", "Email", "Address", "Emgergency Name", "Emgergency Number" });

		// Fetch customer data from the database and populate the tableModel
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM customer";
				ResultSet resultSet = statement.executeQuery(query);

				// Iterate through the result set and add each row to the tableModel
				while (resultSet.next()) {
					Object[] rowData = new Object[8]; // 8 columns in the table
					// Populate rowData with data from the result set
					rowData[0] = resultSet.getInt("c_id");
					rowData[1] = resultSet.getString("c_name");
					rowData[2] = resultSet.getString("c_NIC");
					rowData[3] = resultSet.getInt("c_phone");
					rowData[4] = resultSet.getString("c_email");
					rowData[5] = resultSet.getString("c_address");
					rowData[6] = resultSet.getString("c_emgName");
					rowData[7] = resultSet.getInt("c_emgNum");

					tableModel.addRow(rowData);
				}

				resultSet.close();
				statement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return tableModel;
	}

	// when click on refresh button
	private void refreshPage() {
		// Dispose the current frame
		frame.dispose();

		// Reinitialize and display the window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerInfo window = new customerInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//display Loyalty Customers
	 private void displayLoyaltyCustomers() {
	        DefaultTableModel loyaltyTableModel = new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "Customer ID", "Customer Name", "Number of Reservations" }
	        );
	        // Fetch customer data from the database and populate the tableModel
	        Connection connection = DatabaseConnection.getConnection();
	        if (connection != null) {
	            try {
	                // Query to get the number of reservations for each customer
	                String query = "SELECT c.c_id, c.c_name, COUNT(r.r_id) AS num_reservations " +
	                               "FROM customer c " +
	                               "LEFT JOIN reservations r ON c.c_id = r.cid " +
	                               "GROUP BY c.c_id, c.c_name " +
	                               "HAVING COUNT(r.r_id) >= 3 " +
	                               "ORDER BY COUNT(r.r_id) DESC";
	                PreparedStatement statement = connection.prepareStatement(query);
	                ResultSet resultSet = statement.executeQuery();

	                // Iterate through the result set and add each loyalty customer to the loyaltyTableModel
	                while (resultSet.next()) {
	                    Object[] rowData = new Object[3];
	                    rowData[0] = resultSet.getInt("c_id");
	                    rowData[1] = resultSet.getString("c_name");
	                    rowData[2] = resultSet.getInt("num_reservations");
	                    loyaltyTableModel.addRow(rowData);
	                }
	                resultSet.close();
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                DatabaseConnection.closeConnection(connection);
	            }
	        } else {
	            JOptionPane.showMessageDialog(frame, "Database connection not available.");
	        }
	        // Set the loyalty customer table model
	        table_1.setModel(loyaltyTableModel);
	    }
}
