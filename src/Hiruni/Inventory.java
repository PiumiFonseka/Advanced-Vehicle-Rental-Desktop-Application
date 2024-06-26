package Hiruni;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Hirushima.adminForm;
import Hirushima.employeeForm;
import Hirushima.reservationForm;
import bhagya.supinformation;
import piumi.CustomerForm;
import piumi.customerInfo;
import piumi.driverForm;
import piumi.driverInfo;

public class Inventory {

	private JFrame frame;
	private JTable table;

	private DefaultTableModel tableModel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
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
	public Inventory() {
		initialize();
		populateTable();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null); // This will center the frame on the screen

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		panel.setBounds(0, 0, 1266, 80);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		// Add a click listener to the 'lblNewLabel' for exiting the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JLabel lblVehicleInventory = new JLabel("Vehicle Inventory");
		lblVehicleInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleInventory.setForeground(new Color(243, 219, 50));
		lblVehicleInventory.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleInventory.setBackground(new Color(114, 224, 239));
		lblVehicleInventory.setBounds(225, 10, 813, 61);
		panel.add(lblVehicleInventory);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 218, 1266, 465);
		frame.getContentPane().add(panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(243, 219, 50));
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(243, 219, 50));
		panel_3.setBounds(0, 0, 675, 465);
		panel_1.add(panel_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 113, 655, 323);
		panel_3.add(scrollPane_1);
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Reg Number", "Type", "Fuel Type",
				"Brand", "Colour", "No of Passengers", "Gearbox Type", "Price Per Day" });
		table = new JTable(tableModel);

		// Set the table model
		table.setModel(tableModel);

		// Add the table to the scroll pane
		scrollPane_1.setViewportView(table);

		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setColumns(10);
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(156, 38, 160, 40);
		panel_3.add(textField);

		JLabel lblNewLabel_1_1_1 = new JLabel("Vehicle Id");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(26, 35, 120, 44);
		panel_3.add(lblNewLabel_1_1_1);

		JButton btnCustomer_1_1_1 = new JButton("Search");
		btnCustomer_1_1_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnCustomer_1_1_1.setBackground(Color.WHITE);
		btnCustomer_1_1_1.setBounds(335, 36, 130, 40);
		panel_3.add(btnCustomer_1_1_1);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(new Color(243, 219, 50));
		panel_3_1.setBackground(new Color(0, 0, 0));
		panel_3_1.setBounds(673, 0, 593, 465);
		panel_1.add(panel_3_1);

		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setBounds(27, 20, 541, 420);
		panel_3_1.add(chartPanel);
		updateChartPanel(chartPanel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.GRAY);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 77, 1266, 142);
		frame.getContentPane().add(panel_2);

		JButton btnDrivers = new JButton("Drivers");
		btnDrivers.setForeground(new Color(6, 6, 6));
		btnDrivers.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDrivers.setBackground(new Color(255, 255, 255));
		btnDrivers.setBounds(835, 73, 183, 57);
		panel_2.add(btnDrivers);

		JButton btnReservations = new JButton("Reservations");
		btnReservations.setForeground(new Color(6, 6, 6));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(255, 255, 255));
		btnReservations.setBounds(719, 10, 275, 57);
		panel_2.add(btnReservations);

		JButton btnVehicleMaintenance = new JButton("Vehicle Maintenance");
		btnVehicleMaintenance.setForeground(new Color(6, 6, 6));
		btnVehicleMaintenance.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleMaintenance.setBackground(new Color(255, 255, 255));
		btnVehicleMaintenance.setBounds(457, 73, 368, 57);
		panel_2.add(btnVehicleMaintenance);

		JButton btnCustomer_1_1 = new JButton("Suppliers");
		btnCustomer_1_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1_1.setBounds(240, 73, 205, 57);
		panel_2.add(btnCustomer_1_1);

		JButton btnVehicleSpecifications = new JButton("Vehicle Specifications");
		btnVehicleSpecifications.setForeground(new Color(6, 6, 6));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(255, 255, 255));
		btnVehicleSpecifications.setBounds(22, 10, 410, 57);
		panel_2.add(btnVehicleSpecifications);

		JButton btnCustomer_1 = new JButton("Customers");
		btnCustomer_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1.setBounds(22, 73, 208, 57);
		panel_2.add(btnCustomer_1);

		JButton btnCustomer_1_2 = new JButton("Employees");
		btnCustomer_1_2.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2.setBounds(1004, 10, 244, 57);
		panel_2.add(btnCustomer_1_2);

		JButton btnCustomer_1_2_1 = new JButton("Admins");
		btnCustomer_1_2_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2_1.setBounds(1028, 73, 220, 57);
		panel_2.add(btnCustomer_1_2_1);

		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.setBounds(442, 10, 269, 57);
		panel_2.add(btnDashboard);
		btnDashboard.setForeground(new Color(6, 6, 6));
		btnDashboard.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDashboard.setBackground(new Color(255, 255, 255));

		// Button for 'Dashboard' with an action
		btnDashboard.addMouseListener(new MouseAdapter() {
		});
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {}); // Open the 'Dashboard' window
			}
		});
		btnCustomer_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = textField.getText().trim();
				if (!searchText.isEmpty()) {
					searchVehicleById(searchText);
				} else {
					populateTable(); // If search text is empty, populate the table with all data
				}
			}
		});

		// Button for 'Vehicle Maintenance' with an action
		btnVehicleMaintenance.addMouseListener(new MouseAdapter() {
		});
		btnVehicleMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VehicleMaintenance.main(new String[] {}); // Open the 'Vehicle Maintenance' window
			}
		});

		// Button for 'Admins' with an action
		btnCustomer_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				adminForm.main(new String[] {}); // Open the 'Admins' window
			}
		});

		// Button for 'Reservations' with an action
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				reservationForm.main(new String[] {}); // Open the 'Reservations' window
			}
		});

		// Button for 'Customers' with an action
		btnCustomer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				customerInfo.main(new String[] {}); // Open the 'Customers' window
			}
		});

		// Button for 'Drivers' with an action
		btnDrivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				driverInfo.main(new String[] {}); // Open the 'Drivers' window
			}
		});

		// Button for 'Employees' with an action
		btnCustomer_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				employeeForm.main(new String[] {}); // Open the 'Employees' window
			}
		});

		// Button for 'Suppliers' with an action
		btnCustomer_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				supinformation.main(new String[] {}); // Open the 'Suppliers' window
			}
		});

		// Button for 'Vehicle Specifications' with an action
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VehicleSpecifications.main(new String[] {}); // Open the 'VehicleSpecifications' window
			}
		});
	}

	private void updateChartPanel(ChartPanel chartPanel) {
		 int cars = Integer.parseInt(getCarCount());
		 int jeeps = Integer.parseInt(getJeepCount());
		 int vans = Integer.parseInt(getVanCount());
		 int bikes = Integer.parseInt(getBikeCount());
		 int lorries = Integer.parseInt(getLorriesCount());
		 int busses = Integer.parseInt(getBussesCount());

	        JFreeChart chart = createChart(cars, jeeps, vans, bikes, lorries,busses);
	        chartPanel.setChart(chart);
	}

	private JFreeChart createChart(int cars, int jeeps, int vans, int bikes, int lorries, int busses) {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    
	    if (cars > 0) {
	        dataset.setValue("Cars", cars);
	    }
	    if (jeeps > 0) {
	        dataset.setValue("Jeeps", jeeps);
	    }
	    if (vans > 0) {
	        dataset.setValue("Vans", vans);
	    }
	    if (bikes > 0) {
	        dataset.setValue("Bikes", bikes);
	    }
	    if (lorries > 0) {
	        dataset.setValue("Lorries", lorries);
	    }
	    if (busses > 0) {
	        dataset.setValue("Busses", busses);
	    }

	    JFreeChart chart = ChartFactory.createPieChart(
	        "Summary",
	        dataset,
	        true,
	        true,
	        false
	    );

	    return chart;
	}


	private void populateTable() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM vehicle_specifications";
				ResultSet resultSet = statement.executeQuery(query);

				// Clear existing rows from the table model
				tableModel.setRowCount(0);

				while (resultSet.next()) {
					// Retrieve data from the result set
					Object[] rowData = new Object[] { resultSet.getInt("vehi_id"), resultSet.getString("vehi_reg_no"),
							resultSet.getString("vehi_type"), resultSet.getString("vehi_fuel"),
							resultSet.getString("vehi_brand"), resultSet.getString("vehi_colour"),
							resultSet.getInt("vehi_passengers"), resultSet.getString("vehi_gearbox"),
							resultSet.getDouble("vehi_price") };
					// Add a row to the table model
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

	private void searchVehicleById(String vehicleId) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM vehicle_specifications WHERE vehi_id = " + vehicleId;
				ResultSet resultSet = statement.executeQuery(query);

				// Clear existing rows from the table model
				tableModel.setRowCount(0);

				// Check if the result set is empty
				if (!resultSet.isBeforeFirst()) {
					// No rows found for the specified ID
					// You can show a message or perform any action as needed
				} else {
					// Populate the table with the retrieved row
					while (resultSet.next()) {
						Object[] rowData = new Object[] { resultSet.getInt("vehi_id"),
								resultSet.getString("vehi_reg_no"), resultSet.getString("vehi_type"),
								resultSet.getString("vehi_fuel"), resultSet.getString("vehi_brand"),
								resultSet.getString("vehi_colour"), resultSet.getInt("vehi_passengers"),
								resultSet.getString("vehi_gearbox"), resultSet.getDouble("vehi_price") };
						tableModel.addRow(rowData);
					}
				}
				resultSet.close();
				statement.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	private String getCarCount() {
		String vehicleCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS cars_count FROM vehicle_specifications WHERE vehi_type = 'Cars'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				vehicleCount = resultSet.getString("cars_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicleCount;
	}

	private String getJeepCount() {
		String customerCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS Jeeps_count FROM vehicle_specifications WHERE vehi_type = 'Jeeps'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				customerCount = resultSet.getString("Jeeps_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer count: " + customerCount); // Add this line for debugging
		return customerCount;
	}

	private String getVanCount() {
		String driverCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS van_count FROM vehicle_specifications WHERE vehi_type = 'Vans'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				driverCount = resultSet.getString("van_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Driver count: " + driverCount); // Add this line for debugging
		return driverCount;
	}

	private String getBikeCount() {
		String employeeCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS Bikes_count FROM vehicle_specifications WHERE vehi_type = 'Bikes'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				employeeCount = resultSet.getString("Bikes_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeCount;
	}

	private String getLorriesCount() {
		String lorryCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS lorry_count FROM vehicle_specifications WHERE vehi_type = 'Lorries'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				lorryCount = resultSet.getString("lorry_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lorryCount;
	}

	private String getBussesCount() {
		String busCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT COUNT(*) AS bus_count FROM vehicle_specifications WHERE vehi_type = 'Busses'");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				busCount = resultSet.getString("bus_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return busCount;

	}
}
