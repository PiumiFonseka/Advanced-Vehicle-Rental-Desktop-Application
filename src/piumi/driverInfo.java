package piumi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import Hiruni.Dashboard;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.Graphics2D;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class driverInfo extends JPanel{

	private JFrame frame;
	private JTextField txtSearchTheDriver;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private Vector<driver> drivers = new Vector<>();
	private JLabel lblNewLabel_2;
	private JComboBox<String> comboBox;
	private ChartPanel chartPanel;
	private JTextField textField;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driverInfo window = new driverInfo();
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
	public driverInfo() {
		initialize();
		displayTable();// display table data when running

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
	
		JLabel lblNewLabel = new JLabel("DRIVER INFORMATION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(434, 23, 550, 83);
		frame.getContentPane().add(lblNewLabel);

		txtSearchTheDriver = new JTextField();
		txtSearchTheDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve the search query entered by the user
				String searchText = txtSearchTheDriver.getText().trim();

				// Perform search operation in the database and update the table
				searchAndDisplayResults(searchText);
			}
		});
		txtSearchTheDriver.setForeground(new Color(0, 0, 0));
		txtSearchTheDriver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearchTheDriver.setBounds(1007, 112, 185, 38);
		frame.getContentPane().add(txtSearchTheDriver);
		txtSearchTheDriver.setColumns(10);
		

		JLabel lblNewLabel_3_1 = new JLabel("x");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshTable();
				txtSearchTheDriver.setText(""); // to clear the typed text

			}
		});
		lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(1202, 119, 29, 21);
		frame.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_5 = new JLabel(" Back\r\n\r\n\r\n");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {});
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_5.setBounds(10, 10, 255, 45);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_1 = new JLabel("Filter by vehicle : ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(24, 110, 162, 38);
		frame.getContentPane().add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.setBounds(182, 118, 198, 28);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("--Select vehicle type here--");
		comboBox.addItem("Car Drivers");
		comboBox.addItem("Van Drivers");
		comboBox.addItem("Lorry Drivers");
		comboBox.addItem("Bus Drivers");
		comboBox.addItem("Jeep Drivers");

		
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String selectedFilter = comboBox.getSelectedItem().toString();
				if (selectedFilter.equals("Car Drivers")) {
					displayFilteredDrivers("car");
				} else if (selectedFilter.equals("Van Drivers")) {
					displayFilteredDrivers("van");
				} else if (selectedFilter.equals("Lorry Drivers")) {
					displayFilteredDrivers("lorry");
				} else if (selectedFilter.equals("Bus Drivers")) {
					displayFilteredDrivers("bus");
				} else if (selectedFilter.equals("Jeep Drivers")) {
					displayFilteredDrivers("jeep");
				} 
				
			}
		});
		
		

		
		lblNewLabel_2 = new JLabel("Total number of drivers : " + getTotalNumberOfDrivers());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(393, 146, 328, 32);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 188, 1089, 168);
		frame.getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Driver ID", "Name", "NIC", "Phone", "Address", "Licence No", "Licence Expire Date",
						"Fee", "Note", "car", "van", "lorry", "bus", "jeep", "Licence Type", "Availability" }));

		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				driverForm.main(new String[] {});
			}
		});
		btnNewButton.setBackground(new Color(253, 240, 2));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnNewButton.setBounds(1109, 318, 124, 38);
		frame.getContentPane().add(btnNewButton);

		//Delete Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDriver();
			}
		});
		btnDelete.setBackground(new Color(253, 240, 2));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnDelete.setBounds(1107, 251, 124, 45);
		frame.getContentPane().add(btnDelete);
		
		//View button to view personal Profiles
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_1.getSelectedRow();
				if (selectedRowIndex != -1) { // Ensure a row is selected
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					Object[] driverData = new Object[model.getColumnCount()];
					for (int i = 0; i < model.getColumnCount(); i++) {
						driverData[i] = model.getValueAt(selectedRowIndex, i);
					}
					frame.dispose();
					driverProfile.showDriverDetails(driverData);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a driver.");
				}
			}
		});
		btnView.setBackground(new Color(253, 240, 2));
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnView.setBounds(1109, 188, 124, 45);
		frame.getContentPane().add(btnView);

		JButton btnView_1 = new JButton("Refresh");
		btnView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshPage();
			}
		});
		btnView_1.setBackground(new Color(253, 240, 2));
		btnView_1.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		btnView_1.setBounds(1109, 384, 124, 45);
		frame.getContentPane().add(btnView_1);

		//Add ne driver button
		JButton btnAddCus = new JButton("Add new driver");
		btnAddCus.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnAddCus.setBackground(new Color(253, 240, 2));
		btnAddCus.setBounds(978, 488, 255, 45);
		frame.getContentPane().add(btnAddCus);
		btnAddCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current window
				driverForm.main(new String[] {}); // Open the Customers section
			}
		});

		JLabel lblNewLabel_3 = new JLabel("search the driver detail here...");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_3.setBounds(742, 119, 255, 21);
		frame.getContentPane().add(lblNewLabel_3);

		
		// Adding chart panel for vehicle chart
		chartPanel = new ChartPanel(null);
		chartPanel.setBounds(289, 366, 675, 306);
		frame.getContentPane().add(chartPanel);
		
		// to display the bar chart on screen
		createBarChart();

	}

	// display data in table
	private void displayTable() {
		tableModel = (DefaultTableModel) table_1.getModel();
		tableModel.setRowCount(0); // Clear existing data

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM driver";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("d_id");
					String name = resultSet.getString("d_name");
					String nic = resultSet.getString("d_NIC");
					int phone = resultSet.getInt("d_phone");
					String address = resultSet.getString("d_address");
					String licNo = resultSet.getString("d_licNo");
					String licExp = resultSet.getString("d_LicExp");
					int regFee = resultSet.getInt("d_regFee");
					String note = resultSet.getString("d_note");
					String car = resultSet.getBoolean("car") ? "Yes" : "No";
					String van = resultSet.getBoolean("van") ? "Yes" : "No";
					String lorry = resultSet.getBoolean("lorry") ? "Yes" : "No";
					String bus = resultSet.getBoolean("bus") ? "Yes" : "No";
					String jeep = resultSet.getBoolean("jeep") ? "Yes" : "No";
					String licType = resultSet.getString("d_licType");
					String availability = resultSet.getString("d_Availability");

					// Add the row to the table model
					tableModel.addRow(new Object[] { id, name, nic, phone, address, licNo, licExp, regFee, note, car,
							van, lorry, bus, jeep, licType, availability });

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

	private void deleteDriver() {
		int selectedRowIndex = table_1.getSelectedRow();

		if (selectedRowIndex != -1) {
			int confirmDelete = JOptionPane.showConfirmDialog(frame, "Do you want to delete this driver record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);

			if (confirmDelete == JOptionPane.YES_OPTION) {
				int driverId = (int) table_1.getValueAt(selectedRowIndex, 0);

				try (Connection connection = DatabaseConnection.getConnection();
						Statement statement = connection.createStatement()) {
					String query = "DELETE FROM driver WHERE d_id = " + driverId;
					int rowsAffected = statement.executeUpdate(query);

					if (rowsAffected > 0) {
						DefaultTableModel model = (DefaultTableModel) table_1.getModel();
						model.removeRow(selectedRowIndex);
						updateTotalDriverCount();
						JOptionPane.showMessageDialog(frame, "Driver deleted successfully.");
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to driver customer. Driver may not exist.");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, "Error deleting driver: " + e.getMessage());
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a customer to delete.");
		}
	}

	private void updateTotalDriverCount() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM driver";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalDrivers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					lblNewLabel_2.setText("Total number of driver : " + totalDrivers);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error updating total driver count: " + e.getMessage());
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Database connection not available.");
		}
	}

	private int getTotalNumberOfDrivers() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM driver";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalDrivers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					return totalDrivers;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return 0; // Return 0 if there's an error or no driver found
	}

	
	
	
	
	// Method to perform search operation in the database and update the table
		private void searchAndDisplayResults(String searchText) {
			
			// Call your database query method here with the search text
			DefaultTableModel searchResultsModel = searchDriver(searchText);

			if (searchResultsModel.getRowCount() == 0) {
				// If no rows are returned, display a message
				JOptionPane.showMessageDialog(frame, "No search results found.");
			} else {
				// Update the table with the search results
				table_1.setModel(searchResultsModel);

			}
			comboBox.setSelectedIndex(0); // Set filter back to "--Select driver here--"

		}
		
		
	// Method to search for drivers in the database based on the search text
	public static DefaultTableModel searchDriver(String searchText) {
		Connection connection = null;
		DefaultTableModel model = new DefaultTableModel();

		try {
			connection = DatabaseConnection.getConnection();
			String query = "SELECT * FROM driver WHERE d_id LIKE ? OR d_name LIKE ? OR d_NIC LIKE ? OR d_phone LIKE ? OR d_address LIKE ? OR d_licNo LIKE ? OR d_LicExp LIKE ? OR d_regFee LIKE ? OR d_note LIKE ? OR car LIKE ? OR van LIKE ? OR lorry LIKE ? OR bus LIKE ? OR jeep LIKE ? OR d_licType LIKE ? OR d_Availability LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);

			for (int i = 1; i <= 16; i++) {
				statement.setString(i, "%" + searchText + "%"); // Use LIKE to search for partial matches
			}

			ResultSet resultSet = statement.executeQuery();

			// Populate the model with the search results
			model.setColumnIdentifiers(new String[] { "Driver ID", "Name", "NIC", "Phone", "Address", "Licence Number",
					"Licence Expire date", "Fee", "Note", "car", "van", "lorry", "bus", "jeep", "Licence Type",
					"Availability" });
			while (resultSet.next()) {
				// Convert boolean values to "Yes" or "No"
				String car = resultSet.getBoolean("car") ? "Yes" : "No";
				String van = resultSet.getBoolean("van") ? "Yes" : "No";
				String lorry = resultSet.getBoolean("lorry") ? "Yes" : "No";
				String bus = resultSet.getBoolean("bus") ? "Yes" : "No";
				String jeep = resultSet.getBoolean("jeep") ? "Yes" : "No";

				model.addRow(new Object[] { resultSet.getInt("d_id"), resultSet.getString("d_name"),
						resultSet.getString("d_NIC"), resultSet.getInt("d_phone"), resultSet.getString("d_address"),
						resultSet.getString("d_licNo"), resultSet.getString("d_LicExp"), resultSet.getInt("d_regFee"),
						resultSet.getString("d_note"), car, van, lorry, bus, jeep, resultSet.getString("d_licType"),
						resultSet.getString("d_Availability") });
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

	// Method to refresh the table with full data
	private void refreshTable() {
		DefaultTableModel fullTableModel = getCustomerDataFromDatabase();
		table_1.setModel(fullTableModel);
	}

	public static DefaultTableModel getCustomerDataFromDatabase() {
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Driver ID", "Name", "NIC", "Phone", "Address", "Licence Number", "Licence Expire",
						"Fee", "Note", "car", "van", "lorry", "bus", "jeep", "Licence Type", "Availability" });

		// Fetch driver data from the database and populate the tableModel
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM driver";
				ResultSet resultSet = statement.executeQuery(query);

				// Iterate through the result set and add each row to the tableModel
				while (resultSet.next()) {
					Object[] rowData = new Object[16]; // 16 columns in the table
					// Populate rowData with data from the result set

					rowData[0] = resultSet.getInt("d_id");
					rowData[1] = resultSet.getString("d_name");
					rowData[2] = resultSet.getString("d_NIC");
					rowData[3] = resultSet.getInt("d_phone");
					rowData[4] = resultSet.getString("d_address");
					rowData[5] = resultSet.getString("d_licNo");
					rowData[6] = resultSet.getString("d_LicExp");
					rowData[7] = resultSet.getInt("d_regFee");
					rowData[8] = resultSet.getString("d_note");
					// Convert boolean values to "Yes" or "No"
					rowData[9] = resultSet.getBoolean("car") ? "Yes" : "No";
					rowData[10] = resultSet.getBoolean("van") ? "Yes" : "No";
					rowData[11] = resultSet.getBoolean("lorry") ? "Yes" : "No";
					rowData[12] = resultSet.getBoolean("bus") ? "Yes" : "No";
					rowData[13] = resultSet.getBoolean("jeep") ? "Yes" : "No";
					rowData[14] = resultSet.getString("d_licType");
					rowData[15] = resultSet.getString("d_Availability");

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

	private void displayFilteredDrivers(String vehicleType) {
		tableModel.setRowCount(0); // Clear existing data

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM driver WHERE " + vehicleType + " = ? ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setBoolean(1, true); // Vehicle availability
				
				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {
					// Add driver data to the tableModel
					Object[] rowData = new Object[16]; // 16 columns in the table
					rowData[0] = resultSet.getInt("d_id");
					rowData[1] = resultSet.getString("d_name");
					rowData[2] = resultSet.getString("d_NIC");
					rowData[3] = resultSet.getInt("d_phone");
					rowData[4] = resultSet.getString("d_address");
					rowData[5] = resultSet.getString("d_licNo");
					rowData[6] = resultSet.getString("d_LicExp");
					rowData[7] = resultSet.getInt("d_regFee");
					rowData[8] = resultSet.getString("d_note");
					// Convert boolean values to "Yes" or "No"
					rowData[9] = resultSet.getBoolean("car") ? "Yes" : "No";
					rowData[10] = resultSet.getBoolean("van") ? "Yes" : "No";
					rowData[11] = resultSet.getBoolean("lorry") ? "Yes" : "No";
					rowData[12] = resultSet.getBoolean("bus") ? "Yes" : "No";
					rowData[13] = resultSet.getBoolean("jeep") ? "Yes" : "No";
					rowData[14] = resultSet.getString("d_licType");
					rowData[15] = resultSet.getString("d_Availability");

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
	}

	// Method to create and display bar chart
	private void createBarChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(getTotalNumberOfDriversByVehicleType("car"), "Total", "Car");
		dataset.addValue(getTotalNumberOfDriversByVehicleType("van"), "Total", "Van");
		dataset.addValue(getTotalNumberOfDriversByVehicleType("lorry"), "Total", "Lorry");
		dataset.addValue(getTotalNumberOfDriversByVehicleType("bus"), "Total", "Bus");
		dataset.addValue(getTotalNumberOfDriversByVehicleType("jeep"), "Total", "Jeep");

		JFreeChart chart = ChartFactory.createBarChart("Total Number of Drivers by Vehicle Type", "Vehicle Type",
				"Count", dataset, PlotOrientation.VERTICAL, true, true, false);

		// Customize colors for each series (bar)
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.YELLOW); // Car
		renderer.setSeriesPaint(1, Color.YELLOW); // Van
		renderer.setSeriesPaint(2, Color.YELLOW); // Lorry
		renderer.setSeriesPaint(3, Color.YELLOW); // Bus
		renderer.setSeriesPaint(4, Color.YELLOW); // Jeep

		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setPaint(Color.black);

		chartPanel.setChart(chart);
	}

	// Method to get total number of drivers by vehicle type from the database
	private int getTotalNumberOfDriversByVehicleType(String vehicleType) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT COUNT(*) as total FROM driver WHERE " + vehicleType + " = true";
				ResultSet resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int totalDrivers = resultSet.getInt("total");
					resultSet.close();
					statement.close();
					return totalDrivers;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}

		return 0; // Return 0 if there's an error or no driver found
	}
	

	// when click on Refresh button
	private void refreshPage() {
		// Dispose the current frame
		frame.dispose();

		// Reinitialize and display the window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driverInfo window = new driverInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
