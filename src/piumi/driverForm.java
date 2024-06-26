package piumi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import java.awt.TextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.Checkbox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.swingx.JXDatePicker;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class driverForm {

	private JFrame frame;
	private JTextField dri_phone;
	private JTextField dri_id;
	private JTextField dri_name;
	private JTextField dri_NIC;
	private JTextField dri_address;
	private JTextField dri_licNo;
	private JTextField dri_regFee;
	private JTextField dri_note;
//	private JTextField dri_LicExp;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<driver> drivers = new ArrayList<>();
	private ButtonGroup availabilityGroup;
	private JRadioButton dri_AvailableYes;
	private JRadioButton dri_AvailableNo;

	private ButtonGroup licenseTypeGroup;
	private JRadioButton dri_mannual;
	private JRadioButton dri_auto;

	// Declare checkboxes at the class level
	private JCheckBox dri_bus;
	private JCheckBox dri_jeep;
	private JCheckBox dri_lorry;
	private JCheckBox dri_car;
	private JCheckBox dri_van;

	// date picker for date
	private JXDatePicker datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driverForm window = new driverForm();
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
	public driverForm() {
		initialize();
		displayTable();// display table data when running
		
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		availabilityGroup = new ButtonGroup(); // Initialize availabilityGroup
		licenseTypeGroup = new ButtonGroup(); // Initialize licenseTypeGroup
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null); // This will center the frame on the screen
		
		JLabel lblNewLabel = new JLabel("ADD NEW DRIVER FORM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(438, 26, 550, 83);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Personal Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(41, 127, 294, 30);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Licence details");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(27, 298, 238, 30);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel cidLabel = new JLabel("Driver ID :");
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

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(427, 237, 119, 30);
		frame.getContentPane().add(lblAddress);

		dri_phone = new JTextField();
		dri_phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_phone.setBounds(222, 235, 184, 37);
		frame.getContentPane().add(dri_phone);
		dri_phone.setColumns(10);

		dri_id = new JTextField();
		dri_id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_id.setColumns(10);
		dri_id.setBounds(222, 178, 184, 37);
		frame.getContentPane().add(dri_id);

		// Get the next available driver ID and set it in the text field
		int nextDriID = getNextDriID();
		dri_id.setText(Integer.toString(nextDriID));

		dri_name = new JTextField();
		dri_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_name.setColumns(10);
		dri_name.setBounds(518, 171, 280, 37);
		frame.getContentPane().add(dri_name);

		dri_NIC = new JTextField();
		dri_NIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_NIC.setColumns(10);
		dri_NIC.setBounds(978, 176, 231, 37);
		frame.getContentPane().add(dri_NIC);

		dri_address = new JTextField();
		dri_address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_address.setColumns(10);
		dri_address.setBounds(518, 233, 280, 37);
		frame.getContentPane().add(dri_address);

		JLabel lblNewLabel_2 = new JLabel("Driving license No:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(27, 338, 166, 33);
		frame.getContentPane().add(lblNewLabel_2);

		
		dri_licNo = new JTextField();
		dri_licNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_licNo.setColumns(10);
		dri_licNo.setBounds(187, 338, 148, 37);
		frame.getContentPane().add(dri_licNo);

		dri_regFee = new JTextField();
		dri_regFee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_regFee.setColumns(10);
		dri_regFee.setBounds(427, 382, 231, 37);
		frame.getContentPane().add(dri_regFee);

		JLabel exp = new JLabel("Expiredate of licence:");
		exp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exp.setBounds(10, 389, 219, 33);
		frame.getContentPane().add(exp);

		JLabel lblNewLabel_5 = new JLabel(" Back");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				driverInfo.main(new String[] {});
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_5.setBounds(37, 26, 177, 45);
		frame.getContentPane().add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 0));
		separator.setBackground(new Color(255, 255, 0));
		separator.setBounds(41, 432, 1183, 17);
		frame.getContentPane().add(separator);
		
		//Submit Button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					addDriver();		
			}
		});
		btnSubmit.setBackground(new Color(243, 219, 50));
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnSubmit.setBounds(1133, 499, 114, 37);
		frame.getContentPane().add(btnSubmit);

		// Create and set up a Radio Button for driver License type
		JLabel lblNewLabel_4_1 = new JLabel("License Type :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(978, 318, 126, 33);
		frame.getContentPane().add(lblNewLabel_4_1);

		dri_auto = new JRadioButton("Auto");
		dri_auto.setBackground(new Color(192, 192, 192));
		dri_auto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_auto.setBounds(1001, 357, 111, 27);
		frame.getContentPane().add(dri_auto);
		licenseTypeGroup.add(dri_auto);

		dri_mannual = new JRadioButton("Mannual");
		dri_mannual.setBackground(new Color(192, 192, 192));
		dri_mannual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_mannual.setBounds(1001, 392, 111, 27);
		frame.getContentPane().add(dri_mannual);
		licenseTypeGroup.add(dri_mannual);

		// Create and set up a Radio Button for dri_Available type
		dri_AvailableYes = new JRadioButton("Yes");
		dri_AvailableYes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_AvailableYes.setBackground(Color.LIGHT_GRAY);
		dri_AvailableYes.setBounds(1149, 363, 100, 27);
		frame.getContentPane().add(dri_AvailableYes);
		availabilityGroup.add(dri_AvailableYes);

		dri_AvailableNo = new JRadioButton("No");
		dri_AvailableNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_AvailableNo.setBackground(Color.LIGHT_GRAY);
		dri_AvailableNo.setBounds(1148, 391, 111, 27);
		frame.getContentPane().add(dri_AvailableNo);
		availabilityGroup.add(dri_AvailableNo);

		dri_car = new JCheckBox("Car");
		dri_car.setBackground(new Color(192, 192, 192));
		dri_car.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_car.setBounds(808, 324, 73, 21);
		frame.getContentPane().add(dri_car);

		dri_van = new JCheckBox("Van");
		dri_van.setBackground(new Color(192, 192, 192));
		dri_van.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_van.setBounds(808, 347, 73, 48);
		frame.getContentPane().add(dri_van);

		dri_lorry = new JCheckBox("Lorry");
		dri_lorry.setBackground(new Color(192, 192, 192));
		dri_lorry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_lorry.setBounds(808, 387, 85, 37);
		frame.getContentPane().add(dri_lorry);

		dri_bus = new JCheckBox("Bus");
		dri_bus.setBackground(new Color(192, 192, 192));
		dri_bus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_bus.setBounds(883, 324, 73, 21);
		frame.getContentPane().add(dri_bus);

		dri_jeep = new JCheckBox("Jeep");
		dri_jeep.setBackground(new Color(192, 192, 192));
		dri_jeep.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dri_jeep.setBounds(883, 354, 64, 45);
		frame.getContentPane().add(dri_jeep);

		JLabel lblNewLabel_4_1_1 = new JLabel("Vehicle Type :");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1_1.setBounds(682, 318, 139, 33);
		frame.getContentPane().add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Availability :");
		lblNewLabel_4_1_1_1.setToolTipText("Availability will be 'Yes' in driver registration."); // Adding a tooltip
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1_1_1.setBounds(1152, 318, 126, 33);
		frame.getContentPane().add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_2 = new JLabel("Notes/Comments :");
		lblNewLabel_4_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1_1_2.setBounds(808, 234, 160, 33);
		lblNewLabel_4_1_1_2.setToolTipText("Add any additional notes or comments about driver."); // Adding a tooltip
		frame.getContentPane().add(lblNewLabel_4_1_1_2);

		dri_note = new JTextField();
		dri_note.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dri_note.setColumns(10);
		dri_note.setBounds(978, 233, 231, 55);
		dri_note.setToolTipText("Add any additional notes or comments about driver."); // Adding a tooltip
		frame.getContentPane().add(dri_note);
			
		datePicker = new JXDatePicker();
		datePicker.setBounds(187, 381, 148, 37);
		frame.getContentPane().add(datePicker);

		JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Registration Fee :");
		lblNewLabel_4_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1_1_2_1.setBounds(426, 338, 160, 33);
		frame.getContentPane().add(lblNewLabel_4_1_1_2_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 450, 1113, 212);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Driver ID", "Name", "NIC", "Phone", "Address", "Licence Number", "Licence Expire",
						"Fee", "Note", "car", "van", "lorry", "bus", "jeep", "Licence Type", "Availability" }));
		addMouseListenerToTable();

		
		//Clear button
		JButton clearDriver = new JButton("Clear");
		clearDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all fields?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        if (dialogResult == JOptionPane.YES_OPTION) {
		            clearbtn();
		        }
		    }
		});
		clearDriver.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		clearDriver.setBackground(new Color(243, 219, 50));
		clearDriver.setBounds(1133, 556, 114, 37);
		frame.getContentPane().add(clearDriver);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.YELLOW);
		separator_1.setBackground(Color.YELLOW);
		separator_1.setBounds(962, 298, 2, 128);
		frame.getContentPane().add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(Color.YELLOW);
		separator_1_1.setBackground(Color.YELLOW);
		separator_1_1.setBounds(1121, 298, 2, 128);
		frame.getContentPane().add(separator_1_1);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editDriver();
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnEdit.setBackground(new Color(243, 219, 50));
		btnEdit.setBounds(1133, 612, 114, 37);
		frame.getContentPane().add(btnEdit);
		
		JLabel lblNewLabel_3 = new JLabel("(Optional)\r\n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(878, 259, 78, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		//Demo button is used for Demonstration Purpose
		JButton btnNewButton = new JButton("Demo Button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dri_id.setText("1234");
		        dri_name.setText("Piumi Fonseka");
		        dri_NIC.setText("200047589645");
		        dri_phone.setText("0766458957");
		        dri_address.setText("123 Main St, Negombo");
		        dri_licNo.setText("11123456");
		        dri_regFee.setText("5000");
		        dri_note.setText("Only on weekends");
		        dri_auto.setSelected(true);
		        dri_AvailableYes.setSelected(true);
		        dri_car.setSelected(true);
		        dri_van.setSelected(true);
		        dri_lorry.setSelected(true);
		        dri_bus.setSelected(true);
		        dri_jeep.setSelected(true);
		  
			}
		});
		btnNewButton.setBackground(new Color(240, 210, 2));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(1108, 36, 126, 30);
		frame.getContentPane().add(btnNewButton);
		
	
		
		
		

		
	}

	// addDriver() method is called in ADD Button
	private void addDriver() {
		// Get data from user input
		String name = dri_name.getText();
		String nic = dri_NIC.getText();
	//	int phone = Integer.parseInt(dri_phone.getText());
		 String phoneText = dri_phone.getText(); // Get phone number as string
		String address = dri_address.getText();
		String licNo = dri_licNo.getText();
		String licExpire = formatDate(datePicker.getDate());
		int regFee = Integer.parseInt(dri_regFee.getText());
		String note = dri_note.getText();
		
		
		 // Check for empty strings before parsing
	    if (name.isEmpty() || nic.isEmpty() || address.isEmpty() || phoneText.isEmpty() || licNo.isEmpty() || licExpire.isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
	        return;
	    }

		// Get checkbox states
		boolean car = dri_car.isSelected();
		boolean van = dri_van.isSelected();
		boolean lorry = dri_lorry.isSelected();
		boolean bus = dri_bus.isSelected();
		boolean jeep = dri_jeep.isSelected();

		// Get the selected license type and availability
		String licType = getSelectedLicenseTpye();
		String availability = getSelectedAvailabilityTpye();
		

		// Validate inputs
	    boolean isValidPhoneNumber = validatePhoneNumber(phoneText);
	    boolean isValidNIC = validateNIC(nic);
	    boolean isValidLicence = validateDrivinglicense(licNo);
	  
	    // Set background color based on validation result
	    dri_phone.setBackground(isValidPhoneNumber ? Color.WHITE : Color.PINK);
	    dri_NIC.setBackground(isValidNIC ? Color.WHITE : Color.PINK);
	    dri_licNo.setBackground(isValidLicence ? Color.WHITE : Color.PINK);
	    
	

		
		
		// Validate inputs
		if (!validatePhoneNumber(phoneText)) {
	        JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit phone number.");
	        return;
	    }	
		

		if (!validateNIC(nic)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid NIC.");
			return;
		}
		if (!validateDrivinglicense(licNo)) {
			JOptionPane.showMessageDialog(frame, "Please enter a valid Driving license Number.");
			return;
		}
		
		
		 // Convert validated phone number and registration fee from string to integer/double
        int phone = Integer.parseInt(phoneText);
    
        
		driver dri = new driver(0, name, nic, phone, address, licNo, licExpire, regFee, note, car, van, lorry, bus,
				jeep, licType, availability);
		drivers.add(dri);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Enter data into Database

				String query = "INSERT INTO driver (d_name,d_NIC,d_phone,d_address,d_licNo,"
						+ "d_LicExp,d_regFee,d_note,car,van,lorry,bus, jeep, d_licType,d_Availability ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, dri.getName());
				preparedStatement.setString(2, dri.getNic());
				preparedStatement.setInt(3, dri.getPhone());
				preparedStatement.setString(4, dri.getAddress());
				preparedStatement.setString(5, dri.getLicNum());
				preparedStatement.setString(6, dri.getLicExpDate());
				preparedStatement.setDouble(7, dri.getRegFee());
				preparedStatement.setString(8, dri.getNote());
				// VehType
				preparedStatement.setBoolean(9, dri.getCar());
				preparedStatement.setBoolean(10, dri.getVan());
				preparedStatement.setBoolean(11, dri.getLorry());
				preparedStatement.setBoolean(12, dri.getBus());
				preparedStatement.setBoolean(13, dri.getJeep());
				preparedStatement.setString(14, dri.getLicType());
				preparedStatement.setString(15, dri.getAvailability());

				int rowsAffected = preparedStatement.executeUpdate();

				// Check if a row is selected
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated id

						// Update the ID field with the generated ID
						dri_id.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Driver added successfully!");
						clearbtn();
						displayTable(); // display table with added customer details
						// updateTotalCustomerCount(); // Update the count
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to add Driver.");
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

	// method to change date format 
	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	// Get the selected License type (Manual or Auto) based on radio button
	// selection
	private String getSelectedLicenseTpye() {
		if (dri_mannual != null && dri_mannual.isSelected()) {
			return "Manual";
		} else if (dri_auto != null && dri_auto.isSelected()) {
			return "Auto";
		} else {
			return "";
		}
	}

	// Get the selected Availability status based on radio button
	// selection
	private String getSelectedAvailabilityTpye() {

		if (dri_AvailableYes != null && dri_AvailableYes.isSelected()) {
			return "Yes";
		} else if (dri_AvailableNo != null && dri_AvailableNo.isSelected()) {
			return "No";
		} else {
			return "";
		}
	}

	// clearbtn() method is called in CLEAR Button
	private void clearbtn() {
		dri_id.setText("");
		dri_name.setText("");
		dri_NIC.setText("");
		dri_phone.setText("");
		dri_address.setText("");
		dri_licNo.setText("");
		datePicker.setDate(null);
		dri_regFee.setText("");
		dri_note.setText("");

		// clear radio buttons
		availabilityGroup.clearSelection();
		licenseTypeGroup.clearSelection();

		// Uncheck all checkboxes
		dri_car.setSelected(false);
		dri_van.setSelected(false);
		dri_lorry.setSelected(false);
		dri_bus.setSelected(false);
		dri_jeep.setSelected(false);
	}



	// Get the next available driver ID by finding the maximum existing ID and
	// adding 1
	private int getNextDriID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(d_id) FROM driver";
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
				String query = "SELECT * FROM driver";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					// Fetch data from the result set
					int id = resultSet.getInt("d_id");
					String name = resultSet.getString("d_name");
					String nic = resultSet.getString("d_NIC");
					int phone = resultSet.getInt("d_phone");
					String address = resultSet.getString("d_address");
					String licNo = resultSet.getString("d_licNo");
					String licExp = resultSet.getString("d_LicExp");
					int regFee = resultSet.getInt("d_regFee");
					String note = resultSet.getString("d_note");
					boolean car = resultSet.getBoolean("car");
					boolean van = resultSet.getBoolean("van");
					boolean lorry = resultSet.getBoolean("lorry");
					boolean bus = resultSet.getBoolean("bus");
					boolean jeep = resultSet.getBoolean("jeep");
					String licType = resultSet.getString("d_licType");
					String availability = resultSet.getString("d_Availability");

					// Convert boolean values(true/false) to "Yes" or "No"
					String carStr = car ? "Yes" : "No";
					String vanStr = van ? "Yes" : "No";
					String lorryStr = lorry ? "Yes" : "No";
					String busStr = bus ? "Yes" : "No";
					String jeepStr = jeep ? "Yes" : "No";

					// Create a vector to store row data
					Vector<Object> row = new Vector<>();
					row.add(id);
					row.add(name);
					row.add(nic);
					row.add(phone);
					row.add(address);
					row.add(licNo);
					row.add(licExp);
					row.add(regFee);
					row.add(note);
					row.add(carStr);
					row.add(vanStr);
					row.add(lorryStr);
					row.add(busStr);
					row.add(jeepStr);
					row.add(licType);
					row.add(availability);

					// Add the row to the table model
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
	
	
	
	
	// Add a MouseListener method to the JTable  (mostly use for edit method)
	private void addMouseListenerToTable() {
	    table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow >= 0) {
	                String id = table.getValueAt(selectedRow, 0).toString();
	                String name = table.getValueAt(selectedRow, 1).toString();
	                String nic = table.getValueAt(selectedRow, 2).toString();
	                String phone = table.getValueAt(selectedRow, 3).toString();
	                String address = table.getValueAt(selectedRow, 4).toString();
	                String licNo = table.getValueAt(selectedRow, 5).toString();
	                String licExp = table.getValueAt(selectedRow, 6).toString();
	                String regFee = table.getValueAt(selectedRow, 7).toString();
	                String note = table.getValueAt(selectedRow, 8).toString();
	                String car = table.getValueAt(selectedRow, 9).toString();
	                String van = table.getValueAt(selectedRow, 10).toString();
	                String lorry = table.getValueAt(selectedRow, 11).toString();
	                String bus = table.getValueAt(selectedRow, 12).toString();
	                String jeep = table.getValueAt(selectedRow, 13).toString();
	                String licType = table.getValueAt(selectedRow, 14).toString();
	                String availability = table.getValueAt(selectedRow, 15).toString();

	                // Set the retrieved data to text fields
	                dri_id.setText(id);
	                dri_name.setText(name);
	                dri_NIC.setText(nic);
	                dri_phone.setText(phone);
	                dri_address.setText(address);
	                dri_licNo.setText(licNo);
	         //       datePicker.setDate(licExp);
	                dri_regFee.setText(regFee);
	                dri_note.setText(note);
	                
	             // Retrieve the phone number
	                String phonetoedit = table.getValueAt(selectedRow, 3).toString();

	                // Add a leading zero if it's missing
	                if (phone.length() == 9) { //b the phone number has 9 digits (in database)
	                	phonetoedit = "0" + phone;
	                }

	                // Set the phone number to the appropriate text field
	                dri_phone.setText(phonetoedit);

	                
	             // Parse the string to a Date object for date picker
	                Date expDate = null;
	                try {
	                    expDate = new SimpleDateFormat("dd/MM/yyyy").parse(licExp);
	                } catch (ParseException ex) {
	                    ex.printStackTrace();
	                }

	                // Set the parsed Date object to the datePicker
	                datePicker.setDate(expDate);

	                // Handle radio buttons and check boxes
	                if (car.equals("Yes")) {
	                    dri_car.setSelected(true);
	                } else {
	                    dri_car.setSelected(false);
	                }
	                
	                if (van.equals("Yes")) {
	                    dri_van.setSelected(true);
	                } else {
	                    dri_van.setSelected(false);
	                }
	                
	                if (lorry.equals("Yes")) {
	                    dri_lorry.setSelected(true);
	                } else {
	                    dri_lorry.setSelected(false);
	                }
	                
	                if (bus.equals("Yes")) {
	                    dri_bus.setSelected(true);
	                } else {
	                    dri_bus.setSelected(false);
	                }
	                
	                if (jeep.equals("Yes")) {
	                    dri_jeep.setSelected(true);
	                } else {
	                    dri_jeep.setSelected(false);
	                }
	                
	                // Handle license type radio buttons
	                if (licType.equals("Auto")) {
	                    dri_auto.setSelected(true);
	                } else if (licType.equals("Manual")) {
	                    dri_mannual.setSelected(true);
	                }

	                // Handle availability radio buttons
	                if (availability.equals("Yes")) {
	                    dri_AvailableYes.setSelected(true);
	                } else if (availability.equals("No")) {
	                    dri_AvailableNo.setSelected(true);
	                }
	            }
	        }
	    });
	}



	//Edit Driver Method
	private void editDriver() {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {
			
			
	
	 int id = (int) tableModel.getValueAt(selectedRowIndex, 0);
			String name = dri_name.getText();
			String nic = dri_NIC.getText();
			String phoneStr = dri_phone.getText();
			String address = dri_address.getText();
			String licNo = dri_licNo.getText();
			// String licExp = datePicker.getText();
			String feeStr = dri_regFee.getText();
			String note = dri_note.getText();
			boolean car = dri_car.isSelected();
			boolean van = dri_van.isSelected();
			boolean lorry = dri_lorry.isSelected();
			boolean bus = dri_bus.isSelected();
			boolean jeep = dri_jeep.isSelected();
			String type = getSelectedLicenseType();
			String availability = getSelectedAvailability();
	 
	

			// Validate inputs
		    boolean isValidPhoneNumber = validatePhoneNumber(phoneStr);
		    boolean isValidNIC = validateNIC(nic);
		    boolean isValidLicence = validateDrivinglicense(licNo);
		  
		    // Set background color based on validation result
		    dri_phone.setBackground(isValidPhoneNumber ? Color.WHITE : Color.PINK);
		    dri_NIC.setBackground(isValidNIC ? Color.WHITE : Color.PINK);
		    dri_licNo.setBackground(isValidLicence ? Color.WHITE : Color.PINK);
		 
		    
			// Check for empty strings before parsing
			if (name.isEmpty() || nic.isEmpty() || address.isEmpty() || phoneStr.isEmpty() || feeStr.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
				return;
			}

			int phone;
			double fee;

			try {
				phone = Integer.parseInt(phoneStr);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "Invalid phone number.");
				return;
			}

			try {
				fee = Double.parseDouble(feeStr);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "Invalid registration fee.");
				return;
			}

			
			  // Validate inputs
	        
	        if (!validateNIC(nic)) {
	            JOptionPane.showMessageDialog(frame, "Please enter a valid NIC.");
	            return;
	        }

	        if (!validatePhoneNumber(phoneStr)) {
	            JOptionPane.showMessageDialog(frame, "Please enter a valid 10 digit phone number.");
	            return;
	        }

	        if (!validateNIC(nic)) {
	            JOptionPane.showMessageDialog(frame, "Please enter a valid NIC.");
	            return;
	        }
	        
	        if (!validateDrivinglicense(licNo)) {
				JOptionPane.showMessageDialog(frame, "Please enter a valid Driving license Number.");
				return;
			}
	        
			// Handling license expiration date from datePicker
			String licExp;
		    Date selectedDate = datePicker.getDate();
		    if (selectedDate != null) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        licExp = dateFormat.format(selectedDate);
		    } else {
		        JOptionPane.showMessageDialog(frame, "Please select license expiration date.");
		        return;
		    }

			// Update driver object in the list
			for (driver dri : drivers) {
				if (dri.getId() == id) {
					dri.setName(name);
					dri.setNic(nic);
					dri.setPhone(phone);
					dri.setAddress(address);
					dri.setLicNum(licNo);
					dri.setLicExpDate(licExp);
					dri.setRegFee(fee);
					dri.setNote(note);
					dri.setCar(car);
					dri.setVan(van);
					dri.setLorry(lorry);
					dri.setBus(bus);
					dri.setJeep(jeep);
					dri.setLicType(type);
					dri.setAvailability(availability);
					break;
				}
			}

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {

					String query = "UPDATE driver SET d_name=?, d_NIC=?, d_phone=?, d_address=?, d_licNo=?, "
							+ "d_LicExp=?, d_regFee=?, d_note=?, car=?, van=?, lorry=?, bus=?, jeep=?, d_licType=?, d_Availability=? "
							+ "WHERE d_id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, nic);
					preparedStatement.setInt(3, phone);
					preparedStatement.setString(4, address);
					preparedStatement.setString(5, licNo);
					preparedStatement.setString(6, licExp);
					preparedStatement.setDouble(7, fee);
					preparedStatement.setString(8, note);
					preparedStatement.setBoolean(9, car);
					preparedStatement.setBoolean(10, van);
					preparedStatement.setBoolean(11, lorry);
					preparedStatement.setBoolean(12, bus);
					preparedStatement.setBoolean(13, jeep);
					preparedStatement.setString(14, type);
					preparedStatement.setString(15, availability);
					preparedStatement.setInt(16, id);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Driver updated successfully!");
						clearbtn();
						displayTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to update Driver.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a driver to edit.");
		}
	}

	// Helper method to get selected license type for editDriver method
	private String getSelectedLicenseType() {
		if (dri_auto.isSelected()) {
			return dri_auto.getText();
		} else if (dri_mannual.isSelected()) {
			return dri_mannual.getText();
		} else {
			return ""; // Return an empty string if no radio button is selected
		}
	}

	// Helper method to get selected availability for editDriver method
	private String getSelectedAvailability() {
		if (dri_AvailableYes.isSelected()) {
			return dri_AvailableYes.getText();
		} else if (dri_AvailableNo.isSelected()) {
			return dri_AvailableNo.getText();
		} else {
			return ""; // Return an empty string if no radio button is selected
		}
	}
	

	 
	 // Validation method for phone number
		private boolean validatePhoneNumber(String phoneNumber) {
		    // Check if the phone number contains exactly 10 digits
		    return phoneNumber.matches("\\d{10}");
		//	return phoneNumber.matches("\\d{10}") && phoneNumber.matches("[0-9]+");
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

	
		// Validation method for driving license
		private boolean validateDrivinglicense(String driLic) {
		    // Check if the driving license contains exactly 8 digits
		    return driLic.matches("\\d{8}");
		}
}