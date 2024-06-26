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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Hirushima.adminForm;
import Hirushima.employeeForm;
import Hirushima.reservationForm;
import bhagya.supinformation;
import piumi.customerInfo;
import piumi.driverInfo;

import javax.swing.JComboBox;

//Main class representing the GUI application for managing vehicle specifications
public class VehicleSpecifications extends JFrame {

	private JFrame frame; // JFrame object for the application window
	// JTextFields for input fields
	private JTextField text_ID;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private DefaultTableModel tableModel;
	private ButtonGroup fuelTypeGroup;
	private ButtonGroup gearboxGroup;
	private JRadioButton rdbtnManual;
	private JRadioButton rdbtnAuto;
	private JRadioButton rdbtnNewRadioButtonPetrol;
	private JRadioButton rdbtnNewRadioButtonDiesel;
	private JScrollPane scrollPane; // ScrollPane for the table
	private List<Cars> Cars = new ArrayList<>(); // List to store Car objects
	private JTextField textField;
	private JComboBox<String> comboBox;

	// Main method to launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create an instance of VehicleSpecifications window and make it visible
					VehicleSpecifications window = new VehicleSpecifications();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor to initialize the application
	public VehicleSpecifications() {
		// Call the initialization method to set up the GUI components
		initialize();
		// Populate the table with data
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize the main JFrame
		frame = new JFrame();
		fuelTypeGroup = new ButtonGroup();
		gearboxGroup = new ButtonGroup();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Center the frame on the screen
		frame.setLocationRelativeTo(null);

		// Panel for header
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		panel.setBounds(0, 0, 1266, 84);
		frame.getContentPane().add(panel);

		// Close button
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Exit the application when close button is clicked
				System.exit(0);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		// Label for title
		JLabel lblVehicleSpecifications = new JLabel("VEHICLE SPECIFICATIONS");
		lblVehicleSpecifications.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleSpecifications.setForeground(new Color(243, 219, 50));
		lblVehicleSpecifications.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleSpecifications.setBackground(new Color(243, 219, 50));
		lblVehicleSpecifications.setBounds(228, 10, 835, 61);
		panel.add(lblVehicleSpecifications);
		
		JButton btnDemo = new JButton("Demo");
		btnDemo.setForeground(new Color(6, 6, 6));
		btnDemo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDemo.setBackground(Color.WHITE);
		btnDemo.setBounds(27, 21, 126, 39);
		panel.add(btnDemo);
		// Add action listener to the Demo button
		btnDemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        fillDemoData(); // Call method to fill text fields with example data
		    }
		});

		// Label for ID
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(10, 234, 73, 44);
		frame.getContentPane().add(lblNewLabel_1);

		// TextField for ID
		text_ID = new JTextField();
		text_ID.setText("1"); // Default ID
		text_ID.setHorizontalAlignment(SwingConstants.CENTER);
		text_ID.setForeground(new Color(6, 6, 6));
		text_ID.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text_ID.setEditable(true);
		text_ID.setColumns(10);
		text_ID.setBackground(new Color(243, 219, 50));
		text_ID.setBounds(167, 237, 245, 40);
		frame.getContentPane().add(text_ID);

		// Retrieve next available car ID and set it in the ID field
		int nextCarID = getNextCarID();
		text_ID.setText(Integer.toString(nextCarID));

		// Panel for additional vehicle specifications
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 224, 1266, 459);
		frame.getContentPane().add(panel_1);

		// TextField for price
		textField_4 = new JTextField();
		textField_4.setBounds(167, 403, 245, 40);
		panel_1.add(textField_4);
		textField_4.setForeground(new Color(6, 6, 6));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(243, 219, 50));

		// Label for price
		JLabel lblNewLabel_1_1_5 = new JLabel("Price / Day");
		lblNewLabel_1_1_5.setBounds(10, 400, 174, 44);
		panel_1.add(lblNewLabel_1_1_5);
		lblNewLabel_1_1_5.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Label for brand
		JLabel lblNewLabel_1_1 = new JLabel("Brand");
		lblNewLabel_1_1.setBounds(10, 157, 73, 44);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// TextField for brand
		textField_1 = new JTextField();
		textField_1.setBounds(167, 160, 245, 40);
		panel_1.add(textField_1);
		textField_1.setForeground(new Color(6, 6, 6));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(243, 219, 50));

		// Label for fuel type
		JLabel lblNewLabel_1_1_1 = new JLabel("Fuel");
		lblNewLabel_1_1_1.setBounds(10, 205, 73, 44);
		panel_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Radio buttons for fuel type
		rdbtnNewRadioButtonDiesel = new JRadioButton("Diesel");
		rdbtnNewRadioButtonDiesel.setBounds(167, 207, 112, 40);
		panel_1.add(rdbtnNewRadioButtonDiesel);
		rdbtnNewRadioButtonDiesel.setForeground(new Color(6, 6, 6));
		rdbtnNewRadioButtonDiesel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnNewRadioButtonDiesel.setBackground(new Color(243, 219, 50));
		fuelTypeGroup.add(rdbtnNewRadioButtonDiesel);

		rdbtnNewRadioButtonPetrol = new JRadioButton("Petrol");
		rdbtnNewRadioButtonPetrol.setBounds(305, 207, 107, 40);
		panel_1.add(rdbtnNewRadioButtonPetrol);
		rdbtnNewRadioButtonPetrol.setForeground(new Color(6, 6, 6));
		rdbtnNewRadioButtonPetrol.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnNewRadioButtonPetrol.setBackground(new Color(243, 219, 50));
		fuelTypeGroup.add(rdbtnNewRadioButtonPetrol);

		// Label for gearbox
		JLabel lblNewLabel_1_1_4 = new JLabel("Gearbox");
		lblNewLabel_1_1_4.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_4.setBounds(10, 558, 144, 44);
		frame.getContentPane().add(lblNewLabel_1_1_4);

		// Radio buttons for gearbox
		rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(167, 353, 107, 40);
		panel_1.add(rdbtnManual);
		rdbtnManual.setForeground(new Color(6, 6, 6));
		rdbtnManual.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnManual.setBackground(new Color(243, 219, 50));
		gearboxGroup.add(rdbtnManual);

		// Radio button for automatic gearbox
		rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setBounds(305, 353, 107, 40);
		panel_1.add(rdbtnAuto);
		rdbtnAuto.setForeground(new Color(6, 6, 6));
		rdbtnAuto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnAuto.setBackground(new Color(243, 219, 50));
		gearboxGroup.add(rdbtnAuto);

		// Panel for additional functionality (e.g., add, update, delete, clear buttons)
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(6, 6, 6));
		panel_3.setBounds(449, 0, 817, 459);
		panel_1.add(panel_3);

		// Close button in the additional functionality panel
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_2.setBounds(1204, 10, 33, 36);
		panel_3.add(lblNewLabel_2);

		// Button for adding a new car
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(34, 391, 132, 44);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCar();// add function
			}
		});
		panel_3.add(btnNewButton);
		btnNewButton.setForeground(new Color(6, 6, 6));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBackground(new Color(255, 255, 255));

		// Button for updating car details
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(176, 391, 132, 44);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCar();// update method
			}
		});
		panel_3.add(btnUpdate);
		btnUpdate.setForeground(new Color(6, 6, 6));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnUpdate.setBackground(new Color(255, 255, 255));

		// Button for deleting a car
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCar();
			}
		});
		btnDelete.setBounds(318, 391, 132, 44);
		panel_3.add(btnDelete);
		btnDelete.setForeground(new Color(6, 6, 6));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.setBackground(new Color(255, 255, 255));

		// Button for clearing input fields
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(460, 391, 125, 44);
		panel_3.add(btnClear);
		btnClear.setForeground(new Color(6, 6, 6));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnClear.setBackground(new Color(255, 255, 255));

		// Scroll pane for displaying car data in a table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 797, 341);
		panel_3.add(scrollPane);

		// Table for displaying car data
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Reg Number", "Type", "Fuel Type",
				"Brand", "Colour", "No of Passengers", "Gearbox Type", "Price Per Day" }));

		table.getColumnModel().getColumn(4).setPreferredWidth(97);
		table.getColumnModel().getColumn(6).setPreferredWidth(99);
		scrollPane.setViewportView(table);

		// Create a JButton for adding images
		JButton btnAddImages = new JButton("IMAGES");
		btnAddImages.setForeground(new Color(6, 6, 6));
		btnAddImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected row index
				int selectedRow = table.getSelectedRow();
				// Check if a row is selected
				if (selectedRow >= 0) {
					// Get the vehicle ID from the selected row
					int vehicleID = (int) tableModel.getValueAt(selectedRow, 0);
					// Open the VehicleImageGUI passing the vehicle ID
					frame.dispose();
					
					VehicleImages.main(new String[] { Integer.toString(vehicleID) });
				} else {
					// If no row is selected, show a message to the user
					JOptionPane.showMessageDialog(frame, "Please select a vehicle from the table first.");
				}
			}
		});
		btnAddImages.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAddImages.setBackground(Color.WHITE);
		btnAddImages.setBounds(595, 391, 193, 44);
		panel_3.add(btnAddImages);

		// Label for gearbox in the additional functionality panel
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Gear Box");
		lblNewLabel_1_1_3_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_3_1.setBounds(10, 351, 144, 44);
		panel_1.add(lblNewLabel_1_1_3_1);

		// Label for color
		JLabel lblNewLabel_1_1_2 = new JLabel("Colour");
		lblNewLabel_1_1_2.setBounds(10, 254, 73, 44);
		panel_1.add(lblNewLabel_1_1_2);
		lblNewLabel_1_1_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// TextField for other information
		textField_2 = new JTextField();
		textField_2.setBounds(167, 257, 245, 40);
		panel_1.add(textField_2);
		textField_2.setForeground(new Color(6, 6, 6));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(243, 219, 50));

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Reg Number");
		lblNewLabel_1_1_2_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_2_1.setBounds(10, 60, 157, 44);
		panel_1.add(lblNewLabel_1_1_2_1);

		textField = new JTextField();
		textField.setForeground(new Color(6, 6, 6));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setColumns(10);
		textField.setBackground(new Color(243, 219, 50));
		textField.setBounds(167, 63, 245, 40);
		panel_1.add(textField);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Vehicle Type");
		lblNewLabel_1_1_2_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_2_1_1.setBounds(10, 113, 174, 40);
		panel_1.add(lblNewLabel_1_1_2_1_1);

		// Label for passengers
		JLabel lblNewLabel_1_1_3 = new JLabel("Passengers");
		lblNewLabel_1_1_3.setBounds(10, 302, 144, 44);
		panel_1.add(lblNewLabel_1_1_3);
		lblNewLabel_1_1_3.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// TextField for passengers
		textField_3 = new JTextField();
		textField_3.setBounds(167, 305, 245, 40);
		panel_1.add(textField_3);
		textField_3.setForeground(new Color(6, 6, 6));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(243, 219, 50));

		comboBox = new JComboBox<>();
		comboBox.setBounds(166, 114, 246, 39);
		panel_1.add(comboBox);
		comboBox.setMaximumRowCount(100);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox.setBackground(new Color(243, 219, 50));
		// Populate the combo box with vehicle types
		String[] vehicleTypes = { "Cars", "Vans", "Jeeps", "Bikes", "Lorries", "Busses" };
		for (String type : vehicleTypes) {
			comboBox.addItem(type);
		}

		// Panel for separating header and main content
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.GRAY);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(0, 82, 1266, 142);
		frame.getContentPane().add(panel_2);

		// Button for "Drivers"
		JButton btnDrivers = new JButton("Drivers");
		btnDrivers.setForeground(new Color(6, 6, 6));
		btnDrivers.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDrivers.setBackground(new Color(255, 255, 255));
		btnDrivers.setBounds(835, 73, 183, 57);
		panel_2.add(btnDrivers);

		// Button for "Reservations"
		JButton btnReservations = new JButton("Reservations");
		btnReservations.setForeground(new Color(6, 6, 6));
		btnReservations.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnReservations.setBackground(new Color(255, 255, 255));
		btnReservations.setBounds(687, 10, 283, 57);
		panel_2.add(btnReservations);

		// Button for "Vehicle Inventory"
		JButton btnVehicleInventory = new JButton("Vehicle Inventory");
		btnVehicleInventory.setForeground(new Color(6, 6, 6));
		btnVehicleInventory.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleInventory.setBackground(new Color(255, 255, 255));
		btnVehicleInventory.setBounds(317, 10, 360, 57);
		panel_2.add(btnVehicleInventory);

		// Button for "Vehicle Maintenance"
		JButton btnVehicleMaintenance = new JButton("Vehicle Maintenance");
		btnVehicleMaintenance.setForeground(new Color(6, 6, 6));
		btnVehicleMaintenance.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVehicleMaintenance.setBackground(new Color(255, 255, 255));
		btnVehicleMaintenance.setBounds(457, 73, 368, 57);
		panel_2.add(btnVehicleMaintenance);

		// Button for "Suppliers"
		JButton btnCustomer_1_1 = new JButton("Suppliers");
		btnCustomer_1_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1_1.setBounds(240, 73, 205, 57);
		panel_2.add(btnCustomer_1_1);

		// Button for "Dashboard"
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.setForeground(new Color(6, 6, 6));
		btnDashboard.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnDashboard.setBackground(new Color(255, 255, 255));
		btnDashboard.setBounds(24, 10, 283, 57);
		panel_2.add(btnDashboard);

		// Button for "Customers"
		JButton btnCustomer_1 = new JButton("Customers");
		btnCustomer_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1.setBounds(25, 73, 205, 57);
		panel_2.add(btnCustomer_1);

		// Button for "Employees"
		JButton btnCustomer_1_2 = new JButton("Employees");
		btnCustomer_1_2.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2.setBounds(980, 10, 262, 57);
		panel_2.add(btnCustomer_1_2);

		// Button for "Admins"
		JButton btnCustomer_1_2_1 = new JButton("Admins");
		btnCustomer_1_2_1.setForeground(new Color(6, 6, 6));
		btnCustomer_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCustomer_1_2_1.setBackground(new Color(255, 255, 255));
		btnCustomer_1_2_1.setBounds(1028, 73, 214, 57);
		panel_2.add(btnCustomer_1_2_1);

		// Button for 'Dashboard' with an action
		btnDashboard.addMouseListener(new MouseAdapter() {
		});
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {}); // Open the 'Dashboard' window
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

		// Button for 'Vehicle Inventory' with an action
		btnVehicleInventory.addMouseListener(new MouseAdapter() {
		});
		btnVehicleInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Inventory.main(new String[] {}); // Open the 'Vehicle Inventory' window
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

		// Add a MouseListener to the JTable
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get the selected row index
				int selectedRow = table.getSelectedRow();
				// Check if a row is selected
				if (selectedRow >= 0) {
					// Get data from the selected row
					int id = (int) tableModel.getValueAt(selectedRow, 0);
					String regNumber = (String) tableModel.getValueAt(selectedRow, 1); // Retrieve registration number
					String vehicleType = (String) tableModel.getValueAt(selectedRow, 2); // Retrieve vehicle type
					String brand = (String) tableModel.getValueAt(selectedRow, 4);
					String fuelType = (String) tableModel.getValueAt(selectedRow, 3);
					String color = (String) tableModel.getValueAt(selectedRow, 5);
					int passengers = (int) tableModel.getValueAt(selectedRow, 6);
					String gearbox = (String) tableModel.getValueAt(selectedRow, 7);
					double price = (double) tableModel.getValueAt(selectedRow, 8);
					// Populate the text fields with the retrieved data
					text_ID.setText(Integer.toString(id));
					textField_1.setText(brand);
					// Select the appropriate fuel type radio button
					if (fuelType.equals("Diesel")) {
						rdbtnNewRadioButtonDiesel.setSelected(true);
					} else if (fuelType.equals("Petrol")) {
						rdbtnNewRadioButtonPetrol.setSelected(true);
					}
					textField_2.setText(color);
					textField_3.setText(Integer.toString(passengers));
					// Select the appropriate gearbox radio button
					if (gearbox.equals("Manual")) {
						rdbtnManual.setSelected(true);
					} else if (gearbox.equals("Auto")) {
						rdbtnAuto.setSelected(true);
					}
					textField_4.setText(Double.toString(price));
					// Populate the registration number field
					textField.setText(regNumber);
					// Populate the vehicle type field
					comboBox.setSelectedItem(vehicleType);
				}
			}
		});

	}

	// Method to clear input fields
	private void clearFields() {
		int nextCarID = getNextCarID();
		text_ID.setText(Integer.toString(nextCarID));
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField.setText("");
		comboBox.setSelectedIndex(0); //

		fuelTypeGroup.clearSelection();
		gearboxGroup.clearSelection();
	}

	private String selectGearbox() {
		if (rdbtnManual.isSelected()) {
			return "Manual";
		} else if (rdbtnAuto.isSelected()) {
			return "Auto";
		}
		return "";
	}

	// Method to select fuel type based on user input
	private String selectfueltype() {
		if (rdbtnNewRadioButtonPetrol.isSelected()) {
			return "Petrol";
		} else if (rdbtnNewRadioButtonDiesel.isSelected()) {
			return "Diesel";
		}
		return "";
	}

	private boolean checkRegistrationNumberExists(String regNumber) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT COUNT(*) FROM vehicle_specifications WHERE vehi_reg_no = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, regNumber);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0; // If count > 0, registration number exists
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return false;
	}

	// Method to populate the JTable with data from the 'vehicle_specifications'
	// table
	private void populateTable() {
		// Get the table model and clear any existing data
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // Clear existing data

		// Establish a database connection
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Create a SQL statement and query the database
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM vehicle_specifications";
				ResultSet resultSet = statement.executeQuery(query);

				// Iterate through the result set and populate the JTable
				while (resultSet.next()) {
					// Retrieve data from the result set
					int id = resultSet.getInt("vehi_id");
					String brand = resultSet.getString("vehi_brand");
					String fuelType = resultSet.getString("vehi_fuel");
					String color = resultSet.getString("vehi_colour");
					int passengers = resultSet.getInt("vehi_passengers");
					String gearbox = resultSet.getString("vehi_gearbox");
					double price = resultSet.getDouble("vehi_price");
					String regNumber = resultSet.getString("vehi_reg_no");
					String vehicleType = resultSet.getString("vehi_type");

					// Create a 'Cars' object with retrieved data
					Cars car = new Cars(id, brand, fuelType, color, passengers, gearbox, price, regNumber, vehicleType);
					Cars.add(car);

					// Create a vector to add data to the table model
					Vector<Object> row = new Vector<>();
					row.add(car.getId());
					row.add(car.getRegNumber()); // Add registration number
					row.add(car.getVehicleType()); // Add vehicle type
					row.add(car.getFuelType());
					row.add(car.getBrand());
					row.add(car.getColor());
					row.add(car.getPassengers());
					row.add(car.getGearbox());
					row.add(car.getPrice());

					// Add the row to the table model
					tableModel.addRow(row);
				}

				// Close the result set and statement
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Close the database connection
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Method to retrieve the next available car ID from the
	// 'vehicle_specifications' table
	private int getNextCarID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Query to get the maximum value of 'vehi_id'
				String query = "SELECT MAX(vehi_id) FROM vehicle_specifications";
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
				// Close the database connection
				DatabaseConnection.closeConnection(connection);
			}
		}
		return nextID;
	}

	private void addCar() {
		// Get data from input fields
		String brand = textField_1.getText();
		String fuelType = selectfueltype();
		String color = textField_2.getText();
		String passengersText = textField_3.getText();
		String gearbox = selectGearbox();
		String priceText = textField_4.getText();
		String regNumber = textField.getText(); // Get registration number from the text field
		String vehicleType = comboBox.getSelectedItem().toString(); // Get selected vehicle type from the combo box

		int passengers;
		double price;

		try {
			if (brand.isEmpty() || fuelType.isEmpty() || color.isEmpty() || passengersText.isEmpty()
					|| gearbox.isEmpty() || priceText.isEmpty() || regNumber.isEmpty() || vehicleType.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
				return;
			} else {
				passengers = Integer.parseInt(passengersText);
				price = Double.parseDouble(priceText);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Passengers and price should be numbers.", "Input Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (passengers == 0 || price == 0.0) {
			JOptionPane.showMessageDialog(frame, "Price and passengers cannot be zero.");
			return;
		}
		if (!isValidRegistrationNumber(regNumber)) {
			JOptionPane.showMessageDialog(frame,
					"Invalid registration number format. Please enter a valid format (e.g., ABC-8888 or AB-8888 or 88-8888 or 888-8888).");
			return;
		}
		if (checkRegistrationNumberExists(regNumber)) {
			JOptionPane.showMessageDialog(frame, "Registration number already exists. Please enter a different one.");
			return;
		}

		// Create a 'Cars' object with the entered data
		Cars car = new Cars(0, brand, fuelType, color, passengers, gearbox, price, regNumber, vehicleType);
		Cars.add(car);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Insert the car data into the database
				String query = "INSERT INTO vehicle_specifications (vehi_reg_no,vehi_type,vehi_brand, vehi_fuel, vehi_colour, vehi_passengers, vehi_gearbox, vehi_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, car.getRegNumber());
				preparedStatement.setString(2, car.getVehicleType());
				preparedStatement.setString(3, car.getBrand());
				preparedStatement.setString(4, car.getFuelType());
				preparedStatement.setString(5, car.getColor());
				preparedStatement.setInt(6, car.getPassengers());
				preparedStatement.setString(7, car.getGearbox());
				preparedStatement.setDouble(8, car.getPrice());

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(frame, "Vehicle added successfully!");
					clearFields();
					populateTable();
				} else {
					JOptionPane.showMessageDialog(frame, "Failed to add the vehicle.");
				}

				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	private void updateCar() {
		// Get the selected row from the table
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {
			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);
			if (showConfirmationMessage("Are you sure you want to update this vehicle?")) {

				// Get the updated data from input fields
				String brand = textField_1.getText();
				String fuelType = selectfueltype();
				String color = textField_2.getText();
				String passengersText = textField_3.getText();
				String gearbox = selectGearbox();
				String priceText = textField_4.getText();
				String regNumber = textField.getText(); //
				String vehicleType = comboBox.getSelectedItem().toString();

				// Check if any of the fields are empty
				if (brand.isEmpty() || fuelType.isEmpty() || color.isEmpty() || passengersText.isEmpty()
						|| gearbox.isEmpty() || priceText.isEmpty() || regNumber.isEmpty() || vehicleType.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
					return;
				}

				int passengers = Integer.parseInt(passengersText);
				double price = Double.parseDouble(priceText);

				// Update the car in the 'Cars' list
				for (Cars car : Cars) {
					if (car.getId() == id) {
						car.setBrand(brand);
						car.setFuelType(fuelType);
						car.setColor(color);
						car.setPassengers(passengers);
						car.setGearbox(gearbox);
						car.setPrice(price);
						car.setRegNumber(regNumber);
						car.setVehicleType(vehicleType);
						break;
					}
				}

				Connection connection = DatabaseConnection.getConnection();
				if (connection != null) {
					try {
						// Update the car data in the database
						String query = "UPDATE vehicle_specifications SET vehi_brand = ?, vehi_fuel = ?, vehi_colour = ?, vehi_passengers = ?, vehi_gearbox = ?, vehi_price = ?, vehi_reg_no = ?, vehi_type = ? WHERE vehi_id = ?";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, brand);
						preparedStatement.setString(2, fuelType);
						preparedStatement.setString(3, color);
						preparedStatement.setInt(4, passengers);
						preparedStatement.setString(5, gearbox);
						preparedStatement.setDouble(6, price);
						preparedStatement.setString(7, regNumber);
						preparedStatement.setString(8, vehicleType);
						preparedStatement.setInt(9, id);

						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(frame, "Vehicle updated successfully!");
							clearFields();
							populateTable();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to update the vehicle.");
						}

						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DatabaseConnection.closeConnection(connection);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a vehicle to update.");
		}
	}

	private void deleteCar() {
		// Get the selected row from the table
		int selectedRow = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRow >= 0) {

			// Get the car ID from the selected row
			int id = (int) tableModel.getValueAt(selectedRow, 0);

			if (showConfirmationMessage("Are you sure you want to delete this vehicle?")) {

				Connection connection = DatabaseConnection.getConnection();
				if (connection != null) {
					try {
						// Delete the car from the database based on its ID
						String query = "DELETE FROM vehicle_specifications WHERE vehi_id = ?";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setInt(1, id);

						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							// Remove the car from the 'Cars' list
							Cars.removeIf(car -> car.getId() == id);

							JOptionPane.showMessageDialog(frame, "Vehicle deleted successfully!");
							clearFields();
							populateTable();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to delete the vehicle.");
						}

						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DatabaseConnection.closeConnection(connection);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a vehicle to delete.");
		}
	}
	private void fillDemoData() {
	    // Example data
	    String vehicleType = "Bikes";
	    String brand = "Honda 450";
	    String color = "Green";
	    String fuelType = "Petrol";
	    String gearbox = "Auto";

	    // Populate text fields with example data
	    comboBox.setSelectedItem(vehicleType);
	    textField_1.setText(brand);
	    textField_2.setText(color);
	    if (fuelType.equals("Diesel")) {
	        rdbtnNewRadioButtonDiesel.setSelected(true);
	    } else {
	        rdbtnNewRadioButtonPetrol.setSelected(true);
	    }
	    if (gearbox.equals("Manual")) {
	        rdbtnManual.setSelected(true);
	    } else {
	        rdbtnAuto.setSelected(true);
	    }
	}

	private boolean isValidRegistrationNumber(String regNumber) {
		// Validate registration number format
		return regNumber.matches("[A-Z]{2,3}-\\d{4}") || // ABD-4444
				regNumber.matches("[A-Z]{2}-\\d{4}") || // AB-4444
				regNumber.matches("\\d{2}-\\d{4}") || regNumber.matches("\\d{3}-\\d{4}"); // 44-4444
	}

	private boolean showConfirmationMessage(String message) {
		int choice = JOptionPane.showConfirmDialog(frame, message, "Confirmation", JOptionPane.YES_NO_OPTION);
		return choice == JOptionPane.YES_OPTION;
	}
}
