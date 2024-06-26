package Hiruni;

//Import necessary Java libraries
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Hirushima.*;
import piumi.*;
import bhagya.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Dashboard {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("X");
	JLabel lblNewLabel_4_1;
	JLabel lblNewLabel_1_1_2_7;
	JLabel lblNewLabel_1_1_2_6;
	JLabel lblNewLabel_1_1_2_5;
	JLabel lblNewLabel_1_1_2_4;
	JLabel lblNewLabel_1_1_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard(args); // Pass the admin's username
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Dashboard(String[] args) {
		initialize();
		if (args.length > 0) {
			String adminUsername = args[0];
			lblNewLabel_4_1.setText(adminUsername); // Set the admin's username
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null); // This will center the frame on the screen

		// Add a click listener to the 'lblNewLabel' for exiting the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblNewLabel_1_1_2_7 = new JLabel("0");
		lblNewLabel_1_1_2_7.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_7.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_7.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1_2_7.setBounds(1148, 313, 67, 55);
		frame.getContentPane().add(lblNewLabel_1_1_2_7);
		lblNewLabel_1_1_2_7.setText(getSupplierCount());

		lblNewLabel_1_1_2_6 = new JLabel("0");
		lblNewLabel_1_1_2_6.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_6.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_6.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1_2_6.setBounds(869, 313, 59, 55);
		frame.getContentPane().add(lblNewLabel_1_1_2_6);
		lblNewLabel_1_1_2_6.setText(getEmployeeCount());

		lblNewLabel_1_1_2_5 = new JLabel("0");
		lblNewLabel_1_1_2_5.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_5.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_5.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1_2_5.setBounds(609, 313, 67, 55);
		frame.getContentPane().add(lblNewLabel_1_1_2_5);
		lblNewLabel_1_1_2_5.setText(getDriverCount());

		lblNewLabel_1_1_2_4 = new JLabel("0");
		lblNewLabel_1_1_2_4.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_4.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1_2_4.setBounds(343, 313, 77, 55);
		frame.getContentPane().add(lblNewLabel_1_1_2_4);
		lblNewLabel_1_1_2_4.setText(getCustomerCount());

		lblNewLabel_1_1_2 = new JLabel("0");
		lblNewLabel_1_1_2.setBounds(80, 313, 77, 55);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		lblNewLabel_1_1_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1_2.setText(getVehicleCount());

		// Panel for title
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(128, 128, 128));
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(128, 128, 128)); // Set background color
		panel_2.setBounds(0, 79, 1266, 142);
		frame.getContentPane().add(panel_2);

		JButton btnDrivers = new JButton("Drivers");
		btnDrivers.setBounds(835, 73, 183, 57);
		panel_2.add(btnDrivers);
		btnDrivers.setForeground(new Color(6, 6, 6));
		btnDrivers.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDrivers.setBackground(new Color(255, 255, 255));

		JButton btnReservations = new JButton("Reservations");
		btnReservations.setBounds(768, 10, 250, 57);
		panel_2.add(btnReservations);
		btnReservations.setForeground(new Color(6, 6, 6));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(255, 255, 255));

		JButton btnVehicleMaintenance = new JButton("Vehicle Maintenance");
		btnVehicleMaintenance.setBounds(457, 73, 368, 57);
		panel_2.add(btnVehicleMaintenance);
		btnVehicleMaintenance.setForeground(new Color(6, 6, 6));
		btnVehicleMaintenance.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleMaintenance.setBackground(new Color(255, 255, 255));

		JButton btnCustomer_1_1 = new JButton("Suppliers");
		btnCustomer_1_1.setBounds(240, 73, 205, 57);
		panel_2.add(btnCustomer_1_1);
		btnCustomer_1_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_1.setBackground(new Color(255, 255, 255));

		JButton btnVehicleSpecifications = new JButton("Vehicle Specifications");
		btnVehicleSpecifications.setBounds(21, 10, 390, 57);
		panel_2.add(btnVehicleSpecifications);
		btnVehicleSpecifications.setForeground(new Color(6, 6, 6));
		btnVehicleSpecifications.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSpecifications.setBackground(new Color(255, 255, 255));

		JButton btnCustomer_1 = new JButton("Customers");
		btnCustomer_1.setBounds(21, 73, 209, 57);
		panel_2.add(btnCustomer_1);
		btnCustomer_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1.setBackground(new Color(255, 255, 255));

		JButton btnCustomer_1_2 = new JButton("Employees");
		btnCustomer_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCustomer_1_2.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2.setBounds(1028, 10, 220, 57);
		panel_2.add(btnCustomer_1_2);

		JButton btnCustomer_1_2_1 = new JButton("Admins");
		btnCustomer_1_2_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2_1.setBounds(1028, 73, 220, 57);
		panel_2.add(btnCustomer_1_2_1);

		JButton btnVehicleInventory = new JButton("Vehicle Inventory");
		btnVehicleInventory.setBounds(421, 10, 337, 57);
		panel_2.add(btnVehicleInventory);
		btnVehicleInventory.setForeground(new Color(6, 6, 6));
		btnVehicleInventory.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleInventory.setBackground(new Color(255, 255, 255));

		// Button for 'Vehicle Inventory' with an action
		btnVehicleInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Inventory.main(new String[] {}); // Open the 'Vehicle Inventory' window
			}
		});

		// Create a panel for displaying customer information
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setForeground(new Color(2, 18, 138));
		panel_1_1.setBackground(new Color(243, 219, 50));
		panel_1_1.setBounds(524, 234, 218, 151);
		frame.getContentPane().add(panel_1_1);

		// Label for 'Customers'
		JLabel lblNewLabel_1_1 = new JLabel("Drivers");
		lblNewLabel_1_1.setBackground(new Color(2, 18, 138));
		lblNewLabel_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_1_1.add(lblNewLabel_1_1);

		// Create a panel for displaying booked items
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(243, 219, 50));
		panel_1_1_1.setBounds(788, 234, 218, 151);
		frame.getContentPane().add(panel_1_1_1);

		// Label for 'Booked'
		JLabel lblNewLabel_1_1_1 = new JLabel("Employees");
		lblNewLabel_1_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_1_1_1.add(lblNewLabel_1_1_1);

		// Create a panel for the main navigation buttons
		JPanel panel = new JPanel();
		panel.setBackground(new Color(6, 6, 6));
		panel.setBounds(0, 0, 1266, 80);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		lblNewLabel.setForeground(new Color(255, 255, 255));

		// Add a click listener to the 'lblNewLabel' for exiting the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		JLabel lblDp = new JLabel("DASHBOARD");
		lblDp.setBounds(479, 10, 370, 61);
		panel.add(lblDp);
		lblDp.setForeground(new Color(243, 219, 50)); // Set text color
		lblDp.setBackground(new Color(114, 224, 239)); // Set background color
		lblDp.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblDp.setFont(new Font("Tahoma", Font.BOLD, 50)); // Set font

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(21, 40, 125, 31);
		panel.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Hirushima.login.main(new String[] {});

			}
		});
		btnLogOut.setForeground(new Color(6, 6, 6));
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLogOut.setBackground(new Color(255, 255, 255));

		lblNewLabel_4_1 = new JLabel("Profile Name");
		lblNewLabel_4_1.setBounds(125, 0, 212, 43);
		panel.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setForeground(new Color(243, 219, 50));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT); // Center the text
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 28)); // Set font

		JLabel lblNewLabel_4 = new JLabel("Profile :");
		lblNewLabel_4.setBounds(0, 0, 138, 43);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(243, 219, 50));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 28)); // Set font

		// Panel for the welcome message
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(6, 6, 6)); // Set background color
		panel_3.setBounds(0, 621, 1266, 62);
		frame.getContentPane().add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("WELCOME TO VASS ENTERPRISES VEHICLE RENTAL MANAGEMENT SYSTEM!!!");
		lblNewLabel_3.setBounds(0, 0, 1266, 62);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(243, 219, 50)); // Set text color
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 36)); // Set font

		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setForeground(new Color(2, 18, 138));
		panel_1_1_2.setBackground(new Color(243, 219, 50));
		panel_1_1_2.setBounds(0, 234, 218, 151);
		frame.getContentPane().add(panel_1_1_2);

		JLabel lblNewLabel_1_1_2_3 = new JLabel("Vehicles");
		lblNewLabel_1_1_2_3.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_3.setBackground(new Color(2, 18, 138));
		panel_1_1_2.add(lblNewLabel_1_1_2_3);

		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setForeground(new Color(2, 18, 138));
		panel_1_1_2_1.setBackground(new Color(243, 219, 50));
		panel_1_1_2_1.setBounds(260, 234, 218, 151);
		frame.getContentPane().add(panel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Customers");
		lblNewLabel_1_1_2_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_1.setBackground(new Color(2, 18, 138));
		panel_1_1_2_1.add(lblNewLabel_1_1_2_1);

		JPanel panel_1_1_2_2 = new JPanel();
		panel_1_1_2_2.setForeground(new Color(2, 18, 138));
		panel_1_1_2_2.setBackground(new Color(243, 219, 50));
		panel_1_1_2_2.setBounds(1048, 234, 218, 151);
		frame.getContentPane().add(panel_1_1_2_2);

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Suppliers");
		lblNewLabel_1_1_2_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1_1_2_2.setBackground(new Color(2, 18, 138));
		panel_1_1_2_2.add(lblNewLabel_1_1_2_2);

		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setBounds(116, 388, 597, 233);
		frame.getContentPane().add(chartPanel);

		updateChartPanel(chartPanel);

		JButton btnVehicleSelection = new JButton("Vehicle Selection");
		btnVehicleSelection.setBounds(919, 554, 337, 57);
		frame.getContentPane().add(btnVehicleSelection);
		btnVehicleSelection.setForeground(new Color(6, 6, 6));
		btnVehicleSelection.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleSelection.setBackground(new Color(255, 255, 255));

		// Button for 'Vehicle Selection' with an action
		btnVehicleSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VehicleSelection.main(new String[] {}); // Open the 'Vehicle Selection' window
			}
		});

		// Button for 'Vehicle Specifications' with an action
		btnVehicleSpecifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VehicleSpecifications.main(new String[] {}); // Open the 'VehicleSpecifications' window
			}
		});

		// Button for 'Vehicle Maintenance' with an action
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

	}

	private void updateChartPanel(ChartPanel chartPanel) {
		int drivers = Integer.parseInt(getDriverCount());
		int customers = Integer.parseInt(getCustomerCount());
		int vehicles = Integer.parseInt(getVehicleCount());
		int employees = Integer.parseInt(getEmployeeCount());
		int suppliers = Integer.parseInt(getSupplierCount());

		JFreeChart chart = createChart(drivers, customers, vehicles, employees, suppliers);
		chartPanel.setChart(chart);
	}

	private JFreeChart createChart(int drivers, int customers, int vehicles, int employees, int suppliers) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(vehicles, "Count", "Vehicles");
		dataset.addValue(customers, "Count", "Customers");
		dataset.addValue(drivers, "Count", "Drivers");
		dataset.addValue(employees, "Count", "Employees");
		dataset.addValue(suppliers, "Count", "Suppliers");

		JFreeChart chart = ChartFactory.createBarChart("Summary", "Category", "Count", dataset);
		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.BLACK);

		return chart;
	}

	private String getVehicleCount() {
		String vehicleCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS vehicle_count FROM vehicle_specifications");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				vehicleCount = resultSet.getString("vehicle_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicleCount;
	}

	private String getCustomerCount() {
		String customerCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS customer_count FROM customer");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				customerCount = resultSet.getString("customer_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer count: " + customerCount); // Add this line for debugging
		return customerCount;
	}

	private String getDriverCount() {
		String driverCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS driver_count FROM driver");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				driverCount = resultSet.getString("driver_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Driver count: " + driverCount); // Add this line for debugging
		return driverCount;
	}

	private String getEmployeeCount() {
		String employeeCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS employee_count FROM employees");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				employeeCount = resultSet.getString("employee_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeCount;
	}

	private String getSupplierCount() {
		String supplierCount = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT COUNT(*) AS supplier_count FROM suppi");
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				supplierCount = resultSet.getString("supplier_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplierCount;
	}
}
