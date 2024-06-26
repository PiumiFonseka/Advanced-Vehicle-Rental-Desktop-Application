package piumi;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;


import Hirushima.reservationForm;

public class driverProfile {

	private JFrame frame;
	private JTable table_1;
	private JTable table;
	private JLabel lblDID;
	private JLabel lblName;
	private JLabel lblNIC;
	private JLabel lblContactNumber;
	private JLabel lblAddress;
	private JLabel lblLicNo;
	private JLabel lblLicExp;
	private JLabel lblFee;
	private JLabel lblNote;
	private JLabel lblLicType;
	private JLabel lblavailable;
	private JLabel lblVehicleType;
	private JLabel lblExDate;
	private Object[] driverData;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblNewLabel_1_5;
	private JLabel driGender;
	private JLabel driAge;
	private JSeparator separator_3;
	private JLabel lblTotalReservations;
	private JLabel futureRes;
	// private JTextArea futureRes;
	 private String pickDate;
	    private String returnDate;
	    private long durationDays;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driverProfile window = new driverProfile();
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
	public driverProfile() {
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

		JLabel lblNewLabel_5 = new JLabel(" Back");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				driverInfo.main(new String[] {});
			}
		});

		lblNote = new JLabel("(Notes/Comments)");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNote.setBounds(401, 454, 402, 33);
		frame.getContentPane().add(lblNote);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_5.setBounds(37, 26, 177, 45);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("DRIVER PERSONAL PROFILE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(329, 23, 710, 83);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 240, 2));
		panel.setBounds(-25, 0, 1291, 118);
		frame.getContentPane().add(panel);

		//download report button
		JButton btnNewButton = new JButton("Download Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePDFReport();
			}
		});
		btnNewButton.setBackground(new Color(253, 240, 2));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnNewButton.setBounds(959, 547, 270, 45);
		frame.getContentPane().add(btnNewButton);

		//Print Contact Details  button
		JButton btnContactDetails = new JButton("Print Contact Details");
		btnContactDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printContactDetails();
			}
		});
		btnContactDetails.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnContactDetails.setBackground(new Color(253, 240, 2));
		btnContactDetails.setBounds(959, 609, 270, 45);
		frame.getContentPane().add(btnContactDetails);
		
		
		JLabel lblNewLabel_1_6 = new JLabel("Vehicle that have drive");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1_6.setBounds(939, 141, 327, 39);
		frame.getContentPane().add(lblNewLabel_1_6);

		JLabel lblNewLabel_1 = new JLabel("Personal Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(10, 129, 249, 39);
		frame.getContentPane().add(lblNewLabel_1);

		lblDID = new JLabel("Driver ID :");
		lblDID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDID.setBounds(35, 178, 233, 33);
		frame.getContentPane().add(lblDID);

		lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(35, 212, 222, 33);
		frame.getContentPane().add(lblName);

		lblNIC = new JLabel("NIC :");
		lblNIC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNIC.setBounds(35, 252, 240, 33);
		frame.getContentPane().add(lblNIC);

		lblContactNumber = new JLabel("Contact Number :");
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContactNumber.setBounds(35, 284, 316, 33);
		frame.getContentPane().add(lblContactNumber);

		lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(35, 316, 233, 33);
		frame.getContentPane().add(lblAddress);

		JLabel lblNewLabel_1_1 = new JLabel("Licence details");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(367, 285, 249, 39);
		frame.getContentPane().add(lblNewLabel_1_1);

		lblLicNo = new JLabel("Driving license No:");
		lblLicNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLicNo.setBounds(401, 334, 331, 33);
		frame.getContentPane().add(lblLicNo);

		lblLicExp = new JLabel("Expiredate of licence:");
		lblLicExp.setHorizontalAlignment(SwingConstants.LEFT);
		lblLicExp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLicExp.setBounds(401, 366, 321, 33);
		frame.getContentPane().add(lblLicExp);

		lblFee = new JLabel("Registration Fee :");
		lblFee.setHorizontalAlignment(SwingConstants.LEFT);
		lblFee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFee.setBounds(37, 454, 292, 33);
		frame.getContentPane().add(lblFee);

		lblLicType = new JLabel("Licence type :");
		lblLicType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLicType.setBounds(401, 398, 249, 33);
		frame.getContentPane().add(lblLicType);

		// Initialize lblVehicleType
		lblVehicleType = new JLabel("Vehicle type :");
		lblVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVehicleType.setBounds(401, 427, 450, 33);
		frame.getContentPane().add(lblVehicleType);

		lblavailable = new JLabel("Availability :");
		lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblavailable.setBounds(633, 640, 292, 33);
		lblavailable.setToolTipText("Get's to know about the driver is currently in hire or not."); // Adding a tooltip
		frame.getContentPane().add(lblavailable);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(913, 209, 318, 328);
		frame.getContentPane().add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Vehicle ID", "Vehicle Model", "Reservation ID" }));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Vehicle ID", "Vehicle Model", "Reservation ID" }));
		table.setBounds(949, 190, 280, 368);
		frame.getContentPane().add(table);

		lblExDate = new JLabel("Number of dates to expire licence :");
		lblExDate.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblExDate.setBounds(37, 541, 579, 39);
		frame.getContentPane().add(lblExDate);

		JButton btnSelectRes = new JButton("Select for Reservation");
		btnSelectRes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String driverID = driverData[0].toString(); // Get the driver ID
		        reservationForm.setDriverID(driverID); // Pass the driver ID to reservationForm
		        
		        reservationForm newReservationForm = new reservationForm();
		        newReservationForm.setVisible(true); // Set the new reservationForm to be visible
		        
		        frame.dispose(); 
		        
		    }
		});
		
		btnSelectRes.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnSelectRes.setBackground(new Color(253, 240, 2));
		btnSelectRes.setBounds(601, 486, 280, 45);
		frame.getContentPane().add(btnSelectRes);
			
		

		JButton btnLeaveForHire = new JButton("Leave for hire");
		btnLeaveForHire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure driver leave for the hire?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					updateAvailabilityToNo(); // Proceed with updating availability to No
				}
			}
		});
		btnLeaveForHire.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnLeaveForHire.setBackground(new Color(253, 240, 2));
		btnLeaveForHire.setBounds(601, 599, 280, 45);
		btnLeaveForHire.setToolTipText("Availability : No"); // Adding a tooltip
		frame.getContentPane().add(btnLeaveForHire);
		
		
		JButton btnReturnAfterHire = new JButton("Return after hire");
		btnReturnAfterHire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure the driver return after the hire?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {					
					updateAvailabilityToYes(); // Proceed with updating availability to Yes
				}
			}
		});
		btnReturnAfterHire.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnReturnAfterHire.setBackground(new Color(253, 240, 2));
		btnReturnAfterHire.setBounds(601, 544, 280, 45);
		btnReturnAfterHire.setToolTipText("Availability : Yes"); // Adding a tooltip
		frame.getContentPane().add(btnReturnAfterHire);

		separator = new JSeparator();
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(10, 160, 187, 17);
		frame.getContentPane().add(separator);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.YELLOW);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(377, 317, 169, 17);
		frame.getContentPane().add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setForeground(Color.YELLOW);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(939, 178, 273, 17);
		frame.getContentPane().add(separator_2);

		lblNewLabel_1_5 = new JLabel("Additional Details");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1_5.setBounds(10, 359, 208, 39);
		frame.getContentPane().add(lblNewLabel_1_5);

		separator_3 = new JSeparator();
		separator_3.setForeground(Color.YELLOW);
		separator_3.setBackground(Color.LIGHT_GRAY);
		separator_3.setBounds(10, 391, 220, 17);
		frame.getContentPane().add(separator_3);

		driAge = new JLabel("Age:");
		driAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		driAge.setBounds(37, 427, 197, 33);
		frame.getContentPane().add(driAge);

		// Get NIC number from the driver form and trim any leading or trailing spaces
		String nic = lblNIC.getText().trim();
		// Calculate age from NIC number
		int age = calculateAgeFromNIC(nic);
		// Display age in driver profile
		driAge.setText("Age: " + age);

		driGender = new JLabel("Gender :");
		driGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		driGender.setBounds(37, 398, 197, 33);
		frame.getContentPane().add(driGender);

		futureRes = new JLabel("Future reservations:");
		futureRes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		futureRes.setBounds(277, 128, 594, 117);
		futureRes.setToolTipText("Check with future reservation before allocate for a reservation."); // Adding a tooltip
		frame.getContentPane().add(futureRes);

		lblTotalReservations = new JLabel("Number of hires with us: ");
		lblTotalReservations.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblTotalReservations.setBounds(34, 580, 390, 39);
		frame.getContentPane().add(lblTotalReservations);
		
		
	


	}

	private void updateAvailabilityToYes() {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getConnection();
			if (connection != null) {
				String updateQuery = "UPDATE driver SET d_Availability = 'Yes' WHERE d_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setString(1, driverData[0].toString());
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					// Update the availability in the driverData array
					driverData[15] = "Yes";
					// Update  label to display the updated availability
					lblavailable.setText("Currently not in a hire.");
					JOptionPane.showMessageDialog(frame, "Driver is Currently Availability.");
				} else {
					JOptionPane.showMessageDialog(frame, "No rows updated.");
				}
				preparedStatement.close();
			} else {
				JOptionPane.showMessageDialog(frame, "Failed to connect to the database.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(connection);
		}
	}
	
	private void updateAvailabilityToNo() {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getConnection();
			if (connection != null) {
				String updateQuery = "UPDATE driver SET d_Availability = 'No' WHERE d_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setString(1, driverData[0].toString());
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					// Update the availability in the driverData array
					driverData[15] = "No";
					// Update  label to display the updated availability
					lblavailable.setText("Driver is on a hire now.");
					JOptionPane.showMessageDialog(frame, "Driver is not currently Availability.");
				} else {
					JOptionPane.showMessageDialog(frame, "No rows updated.");
				}
				preparedStatement.close();
			} else {
				JOptionPane.showMessageDialog(frame, "Failed to connect to the database.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(connection);
		}
	}

	public static void showDriverDetails(Object[] driverData) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driverProfile window = new driverProfile();
					window.frame.setVisible(true);
					window.displayDriverDetails(driverData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void displayDriverDetails(Object[] driverData) {
		// Display driver details in the GUI components of the driverProfile window
		lblDID.setText("Driver ID: " + driverData[0]);
		lblName.setText("Name: " + driverData[1]);
		lblNIC.setText("NIC: " + driverData[2]);
		lblContactNumber.setText("Contact Number: " + driverData[3]);
		lblAddress.setText("Address: " + driverData[4]);
		lblLicNo.setText("Driving License No: " + driverData[5]);
		lblLicExp.setText("Expire date of license: " + driverData[6]);
		lblFee.setText("Registration Fee: " + driverData[7]);
		lblNote.setText("Notes/Comments : " + driverData[8]);

		lblLicType.setText("Licence type: " + driverData[14]);


		// Check the value of driverData[15] 
	    if (driverData[15].equals("No")) {
	        lblavailable.setText("Driver is on a hire now.");
	    } else if (driverData[15].equals("Yes")) {
	        lblavailable.setText("Currently not in a hire.");
	    } else {
	        // Handle unexpected values here
	        lblavailable.setText("Unknown hire status.");
	    }
		
		// Get NIC number from the driverData array
		String nic = driverData[2].toString(); // stored at index 2
		// Calculate age from NIC number
		int age = calculateAgeFromNIC(nic);
		// Display age in the GUI
		driAge.setText("Age: " + age);

		// Determine gender
		char gender = getGenderFromNIC(nic);
		// display gender
		driGender.setText("Gender:  " + (gender == 'M' ? "Male" : "Female"));

		// Get the vehicle types associated with the driver from the database
		List<String> vehicleTypes = getVehicleTypesForDriver(driverData[0].toString()); // driverData[0] contains d_id

		// Display the vehicle types in the GUI
		if (vehicleTypes != null && !vehicleTypes.isEmpty()) {
			StringBuilder vehicleTypesStr = new StringBuilder("Vehicle Type(s): ");
			for (String type : vehicleTypes) {
				vehicleTypesStr.append(type).append(", ");
			}
			// Remove the trailing comma and space
			vehicleTypesStr.setLength(vehicleTypesStr.length() - 2);
			// vehicleTypesStr.append("</html>");
			lblVehicleType.setText(vehicleTypesStr.toString());
		} else {
			lblVehicleType.setText("<html><b>Vehicle Type(s): </b>No vehicles</html>");
		}

		// Set driverData
		this.driverData = driverData;

		// Get the expiration date of the license from driverData
		String expireDateStr = (String) driverData[6];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date expireDate;
		try {
			expireDate = sdf.parse(expireDateStr);
			// Calculate the number of days until expiration
			long diff = expireDate.getTime() - System.currentTimeMillis();
			long daysUntilExpiration = diff / (1000 * 60 * 60 * 24);

			// Display the number of days until expiration
			lblExDate.setText("Number of days until license expiration: " + daysUntilExpiration);

			// If the license is expired, set text color to red
			if (daysUntilExpiration < 0) {
				lblExDate.setForeground(Color.RED);
			}
		} catch (ParseException e) {
			e.printStackTrace(); // Handle parsing exception
		}

		// to display number of hires
		int totalReservations = getTotalReservationsForDriver(driverData[0].toString());
		lblTotalReservations.setText("Number of hires with us:  " + totalReservations);

		int driverID = Integer.parseInt(driverData[0].toString());
		// Populate the vehicle with reservation table with data for the specific driver
		populateVehicleReservationTable(driverID);		
		
		
		 // Extract driver ID from driverData array
	    int driverId = Integer.parseInt(driverData[0].toString());
	    
	    // Call displayFutureReservations method to update futureRes label
	    displayFutureReservations(driverId);

	}

	
	

	// Method to get vehicle types for a specific driver ID
	public List<String> getVehicleTypesForDriver(String driverID) {
		List<String> vehicleTypes = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT car, van, lorry, bus, jeep FROM driver WHERE d_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(driverID)); // Parse the String to an int
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					// Check each vehicle type column and add it to the list if it's true
					if (resultSet.getBoolean("car")) {
						vehicleTypes.add("Car");
					}
					if (resultSet.getBoolean("van")) {
						vehicleTypes.add("Van");
					}
					if (resultSet.getBoolean("lorry")) {
						vehicleTypes.add("Lorry");
					}
					if (resultSet.getBoolean("bus")) {
						vehicleTypes.add("Bus");
					}
					if (resultSet.getBoolean("jeep")) {
						vehicleTypes.add("Jeep");
					}
				}

				resultSet.close();
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return vehicleTypes;
	}

	public int calculateAgeFromNIC(String nic) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int age;

		if (nic.endsWith("V")) { // For old NIC format
			int birthYear = Integer.parseInt(nic.substring(0, 2)); // Extract the first 2 digits
			age = currentYear - (1900 + birthYear);
		} else if (nic.length() == 12) { // For new NIC format
			int birthYear = Integer.parseInt(nic.substring(0, 4)); // Extract the first 4 digits
			age = currentYear - birthYear;
		} else {
			// Handle invalid NIC format
			age = 0; // Indicate an error or invalid format
		}

		return age;
	}

	// get total number of hires from database
	public int getTotalReservationsForDriver(String driverID) {
		int totalReservations = 0;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT COUNT(*) AS totalReservations FROM reservations WHERE driver_needed = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, driverID);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					totalReservations = resultSet.getInt("totalReservations");
				}

				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return totalReservations;
	}

	// method to identify the gender
	private char getGenderFromNIC(String nic) {
		char gender = ' '; // Default value

		if (nic.length() == 10) { // Old NIC format
			int digit = Integer.parseInt(nic.substring(2, 5));
			if (digit >= 500) {
				gender = 'F'; // Female
			} else {
				gender = 'M'; // Male
			}
		} else if (nic.length() == 12) { // New NIC format
			int digit = Integer.parseInt(nic.substring(4, 7));
			if (digit >= 501) {
				gender = 'F'; // Female
			} else {
				gender = 'M'; // Male
			}
		}

		return gender;
	}

	//for table
	private void populateVehicleReservationTable(int driverId) {

		DefaultTableModel vehicleReservationTableModel = getDriverVehicleDataFromDatabase(driverId);
		table_1.setModel(vehicleReservationTableModel);
	}

	private DefaultTableModel getDriverVehicleDataFromDatabase(int driverId) {

		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Vehicle ID", "Model", "Reservation ID" });

		// Fetch data from the database using JOIN operation to get vehicle information
		// and reservation ID
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT vs.vehi_id, vs.vehi_brand, r.r_id " + "FROM vehicle_specifications vs "
						+ "INNER JOIN reservations r ON vs.vehi_id = r.vehi_id " + "WHERE r.driver_needed = ?";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, driverId); // Bind driver ID to the query

				ResultSet resultSet = statement.executeQuery();

				// Populate the table model with retrieved data
				while (resultSet.next()) {
					Object[] rowData = new Object[3];
					rowData[0] = resultSet.getString("vehi_id");
					rowData[1] = resultSet.getString("vehi_brand");
					rowData[2] = resultSet.getString("r_id");

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
	

	
	
	
	// Update getFutureReservations method to properly execute the SQL query and return the ResultSet
	public ResultSet getFutureReservations(int driverId) {
	    Connection connection = null;
	    ResultSet resultSet = null;
	    try {
	        connection = DatabaseConnection.getConnection();
	        String query = "SELECT pick_date, return_date FROM reservations WHERE driver_needed = ? AND STR_TO_DATE(pick_date, '%d/%m/%Y') > CURDATE()";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, driverId);
	        resultSet = statement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return resultSet;
	}

	// Update displayFutureReservations to set the text of futureRes label
	private void displayFutureReservations(int driverId) {
	    ResultSet futureReservations = getFutureReservations(driverId);

	    
	    try {
	    	
	    	
	        
	        StringBuilder reservationsText = new StringBuilder("<html><b>Future Reservations:</b><br>");
	        while (futureReservations.next()) {
	            String pickDateStr = futureReservations.getString("pick_date");
	            String returnDateStr = futureReservations.getString("return_date");
	            LocalDate pickDate = LocalDate.parse(pickDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	            LocalDate returnDate = LocalDate.parse(returnDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	            long durationDays = pickDate.until(returnDate).getDays();
	            reservationsText.append("Pick Date: ").append(pickDateStr)
	                            .append(", Return Date: ").append(returnDateStr)
	                            .append(", Duration: ").append(durationDays).append(" days<br>");
	       
	        }
	        reservationsText.append("</html>");
	        
	        // Set reservationsText if there are future reservations
	        futureRes.setText(reservationsText.toString());
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	// report generation
	private void printContactDetails() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showSaveDialog(frame);
		if (result != JFileChooser.APPROVE_OPTION) {
			return; // Exit if the user cancels the operation
		}

		File desktopDirectory = fileChooser.getSelectedFile();
		String desktopPath = desktopDirectory.getAbsolutePath();
		Document document = new Document();
		try {
		
			Rectangle pageSize = new Rectangle(400, 600);

			// Create a document with the custom page size
			Document doc = new Document(pageSize, 50, 50, 50, 50); // Add margins of 50 units

			// Get the driver ID from the driverData array
			String driverID = driverData[0].toString();

			// Construct the filename with the driver ID
			String filename = "driver Contact id" + driverID + ".pdf";

			// Provide the path where you want to save the PDF report with the constructed
			// filename
			String filePath = desktopPath + File.separator + filename;

			// Save the PDF with the constructed filename
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			
			// Create PdfWriter instance to write to the document
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

			
			document.open();

			// Add content to the PDF report
			document.add(new Paragraph("Vass Enterprises",FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.DARK_GRAY)));
			document.add(new Paragraph("No. 31, KadolUyana, Negombo", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph("+94766150388", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph("vassEnterprises@gmail.com", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Driver Contact Details", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));

			document.add(new Paragraph(" ")); // to keep a line space after topic
			document.add(new Paragraph(" ")); // to keep a line space after topic

			// Add driver details to the PDF report
			addContentToDocumentInfo(document, "Driver ID", lblDID);
			addContentToDocumentInfo(document, "Name", lblName);
			addContentToDocumentInfo(document, "Contact Number", lblContactNumber);
			addContentToDocumentInfo(document, "Address", lblAddress);
			addContentToDocumentInfo(document, "Vehicle Type(s)", lblVehicleType);
			
			document.add(new Paragraph(" ")); // to keep a line space after topic
			document.add(new Paragraph(" ")); // to keep a line space after topic
			
			document.add(new Paragraph("Thank you for choosing our vehicle rental service! For any inquiries, assistance, "
					+ "or emergencies, please don't hesitate to contact us.Your safety and "
					+ "satisfaction are our top priorities.",FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			
			;

	        
			JOptionPane.showMessageDialog(frame, "Contact Details generated successfully!");
		
			// Close the document after writing
			document.close();

			// Open the generated PDF file after creation
			File pdfFile = new File(filePath);
			if (pdfFile.exists()) {
				Desktop.getDesktop().open(pdfFile);
			}
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	// Helper method to add content to the PDF document
	private void addContentToDocumentInfo(Document document, String label, JLabel component)
			throws DocumentException, IOException {
		// Extract plain text from HTML string
		String labelText = component.getText().replaceAll("<[^>]*>", "").trim();

		// Add label and corresponding text from JLabel to the PDF document
		Chunk chunkText = new Chunk(labelText, FontFactory.getFont(FontFactory.HELVETICA));
		Paragraph paragraph = new Paragraph();

		paragraph.add(chunkText);
		document.add(paragraph);
		document.add(Chunk.NEWLINE); // Add newline after each entry
	}
	
	
	
	
	
	
	
	
	
	
	// report generation
		private void generatePDFReport() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fileChooser.showSaveDialog(frame);
			if (result != JFileChooser.APPROVE_OPTION) {
				return; // Exit if the user cancels the operation
			}

			File desktopDirectory = fileChooser.getSelectedFile();
			String desktopPath = desktopDirectory.getAbsolutePath();
			String watermarkText = "Confidential"; // Watermark text
			Document document = new Document();
			try {
				// Create a document with specified page size
				Document doc = new Document(PageSize.A4, 50, 50, 50, 50); // Add margins of 50 units

				// Get the driver ID from the driverData array
				String driverID = driverData[0].toString();

				// Construct the filename with the driver ID
				String filename = "Driver report id" + driverID + ".pdf";

				// Provide the path where you want to save the PDF report with the constructed
				// filename
				String filePath = desktopPath + File.separator + filename;

				// Save the PDF with the constructed filename
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				
				// Create PdfWriter instance to write to the document
		        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

				
				document.open();

				// Add content to the PDF report
				document.add(new Paragraph("Vass Enterprises",FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.DARK_GRAY)));
				document.add(new Paragraph("No. 31, KadolUyana, Negombo", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
				document.add(new Paragraph("+94766150388", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
				document.add(new Paragraph("vassEnterprises@gmail.com", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
				document.add(new Paragraph(" "));
				document.add(new Paragraph("Driver Report", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));
				
				document.add(new Paragraph(
						"Confidential: The following report contains personal profiles. Access to this information is"
						+ " restricted to authorized personnel only. Any unauthorized distribution or use of"
						+ " this information is strictly prohibited.",FontFactory.getFont(FontFactory.HELVETICA, 10)));

				document.add(new Paragraph(" ")); // to keep a line space after topic
				document.add(new Paragraph(" ")); // to keep a line space after topic

				// Add driver details to the PDF report
				addContentToDocumentInfo(document, "Driver ID", lblDID);
				addContentToDocumentInfo(document, "Name", lblName);
				addContentToDocumentInfo(document, "NIC", lblNIC);
				addContentToDocumentInfo(document, "Contact Number", lblContactNumber);
				addContentToDocumentInfo(document, "Address", lblAddress);
				addContentToDocumentInfo(document, "Driving License No", lblLicNo);
				addContentToDocumentInfo(document, "Expire date of license", lblLicExp);
				addContentToDocumentInfo(document, "Registration Fee", lblFee);
				addContentToDocumentInfo(document, "Notes/Comments", lblNote);
				addContentToDocumentInfo(document, "License Type", lblLicType);
				addContentToDocumentInfo(document, "Vehicle Type(s)", lblVehicleType);
				addContentToDocumentInfo(document, "Number of days until license expiration", lblExDate);
				addContentToDocumentInfo(document, "Number of hires with us", lblTotalReservations);

				;

				

		        // Add watermark to the single page
		        PdfContentByte contentByte = writer.getDirectContentUnder();
		        PdfGState gs = new PdfGState();
		        gs.setFillOpacity(0.3f); // Set transparency level (0.0 to 1.0)
		        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		        contentByte.beginText();
		        contentByte.setFontAndSize(baseFont, 100);
		        contentByte.setColorFill(BaseColor.LIGHT_GRAY);
		        contentByte.setTextMatrix(30, 30);
		        contentByte.showTextAligned(Element.ALIGN_LEFT, watermarkText, 150, 300, 45);
		        contentByte.endText();
		        contentByte.setGState(gs);

		        
				JOptionPane.showMessageDialog(frame, "PDF Report generated successfully!");
			
				// Close the document after writing
				document.close();

				// Open the generated PDF file after creation
				File pdfFile = new File(filePath);
				if (pdfFile.exists()) {
					Desktop.getDesktop().open(pdfFile);
				}
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				document.close();
			}
		}

	
	
	
	
	
	
	
}
