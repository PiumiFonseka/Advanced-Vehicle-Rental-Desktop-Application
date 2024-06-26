package Hiruni;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Desktop;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.itextpdf.text.Chunk;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

// Class for managing vehicle maintenance

public class VehicleMaintenance extends JFrame {

	// Declaration of instance variables
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	
	private JTextField textField_3;
	private JTextField textField_4;
	
	private DefaultTableModel tableModel; // Table model for displaying data in the table
	private JTable table; // Table for displaying vehicle maintenance information
	private List<Maintenance> Maintenance = new ArrayList<>(); // List to hold maintenance data
	private JComboBox comboBox; // Dropdown box for selecting vehicles
	private String formattedDate;
	private JDateChooser dateChooser;

	// Main method to launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleMaintenance window = new VehicleMaintenance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor to initialize the application
	public VehicleMaintenance() {
		initialize(); // Initialize the contents of the frame
		populateTable(); // Populate the table with existing maintenance data
		populateVehicleDropdown(); // Populate the vehicle dropdown with available vehicles
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Create the main frame
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a panel to hold components
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Create a label for closing the window
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		// Center the frame on the screen
		frame.setLocationRelativeTo(null);

		// Add a click listener to the label to exit the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		// Create a label for displaying "Vehicle Maintenance"
		JLabel lblVehicleMaintanence = new JLabel("Vehicle Maintenance");
		lblVehicleMaintanence.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleMaintanence.setForeground(new Color(243, 219, 50));
		lblVehicleMaintanence.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleMaintanence.setBackground(new Color(114, 224, 239));
		lblVehicleMaintanence.setBounds(225, 10, 813, 61);
		panel.add(lblVehicleMaintanence);

		// Create a panel to hold maintenance details
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(243, 219, 50));
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(0, 81, 1266, 602);
		panel.add(panel_2);

		// Create a label and text field for entering price
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Price");
		lblNewLabel_1_1_3_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_3_1.setBounds(37, 309, 144, 44);
		panel_2.add(lblNewLabel_1_1_3_1);

		// Create text field for entering price
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(6, 6, 6));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(243, 219, 50));
		textField_4.setBounds(293, 309, 245, 40);
		panel_2.add(textField_4);

		// Create text field for entering ID
		textField = new JTextField();
		textField.setBounds(293, 28, 245, 40);
		panel_2.add(textField);
		textField.setText("1");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(6, 6, 6));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setEditable(true);
		textField.setColumns(10);
		textField.setBackground(new Color(243, 219, 50));

		// Fetch the next maintenance ID and set it in the text field
		int nextCarID = getNextMaintenanceID();
		textField.setText(Integer.toString(nextCarID));

		// Create text field for entering vehicle ID
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(293, 165, 245, 40);
		panel_2.add(dateChooser);

		// Create text field for entering service center name
		textField_3 = new JTextField();
		textField_3.setBounds(293, 239, 245, 40);
		panel_2.add(textField_3);
		textField_3.setForeground(new Color(6, 6, 6));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(243, 219, 50));

		// Create labels for ID
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(37, 28, 73, 44);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Create labels for Vehicle ID
		JLabel lblNewLabel_1_1 = new JLabel("Registration Number");
		lblNewLabel_1_1.setBounds(37, 95, 251, 44);
		panel_2.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Create label for service center name
		JLabel lblNewLabel_1_1_3 = new JLabel("Service Center Name");
		lblNewLabel_1_1_3.setBounds(37, 239, 251, 44);
		panel_2.add(lblNewLabel_1_1_3);
		lblNewLabel_1_1_3.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Create label for date
		JLabel lblNewLabel_1_1_2 = new JLabel("Date");
		lblNewLabel_1_1_2.setBounds(37, 165, 73, 44);
		panel_2.add(lblNewLabel_1_1_2);
		lblNewLabel_1_1_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));

		// Create button for exiting
		JButton btnBack = new JButton("Exit");
		btnBack.setForeground(new Color(6, 6, 6));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setBounds(1050, 521, 178, 44);
		panel_2.add(btnBack);

		// Create panel for table display
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 1266, 484);
		panel_2.add(panel_1);

		// Create combo box for selecting vehicle ID
		comboBox = new JComboBox();
		comboBox.setMaximumRowCount(100);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox.setBackground(new Color(243, 219, 50));
		comboBox.setBounds(292, 93, 246, 39);
		panel_1.add(comboBox);

		// Create button for adding maintenance details
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(25, 402, 178, 44);
		panel_1.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = dateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				formattedDate = dateFormat.format(selectedDate);

				addMaintenance();

			}
		});
		btnAdd.setForeground(new Color(6, 6, 6));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdd.setBackground(new Color(255, 255, 255));

		// Creating a JButton for deleting maintenance records
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(6, 6, 6));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(360, 402, 178, 44);
		panel_1.add(btnDelete); // Adding the button to the panel_1

		// Creating a JPanel for managing images related to maintenance
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(243, 219, 50));
		panel_3.setBounds(558, 0, 708, 484);
		panel_1.add(panel_3); // Adding the panel_3 to panel_1

		// Creating a JLabel for closing the image management panel
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(1204, 10, 33, 36);
		panel_3.add(lblNewLabel_2); // Adding the label to panel_3

		// Add scroll pane for table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 25, 657, 343);
		panel_3.add(scrollPane);

		// Create table for displaying maintenance details
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Vehicle Id", "Date", "Service Center Name", "Price" }));
		scrollPane.setViewportView(table);

		// Create button for printing maintenance details
		JButton btnPrintMaintanenceDetails = new JButton("Print Report");
		btnPrintMaintanenceDetails.setBounds(413, 393, 267, 53);
		panel_3.add(btnPrintMaintanenceDetails);
		btnPrintMaintanenceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrintMaintanenceDetails.setForeground(new Color(6, 6, 6));
		btnPrintMaintanenceDetails.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnPrintMaintanenceDetails.setBackground(new Color(255, 255, 255));

		JButton btnViewTotalCost = new JButton("View Total Cost");
		btnViewTotalCost.setForeground(new Color(6, 6, 6));
		btnViewTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnViewTotalCost.setBackground(Color.WHITE);
		btnViewTotalCost.setBounds(24, 521, 297, 44);
		panel_2.add(btnViewTotalCost);
		
		JButton btnDemo = new JButton("Demo");
		btnDemo.setForeground(new Color(6, 6, 6));
		btnDemo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDemo.setBackground(Color.WHITE);
		btnDemo.setBounds(20, 22, 126, 36);
		panel.add(btnDemo);
		btnPrintMaintanenceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePDFReport(); // Call the method to generate the PDF report
			}
		});
		
		// Add action listener to the Demo button
		btnDemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        fillDemoData(); // Call method to fill text fields with example data
		    }
		});

		// Button for 'View total cost' with an action
		btnViewTotalCost.addMouseListener(new MouseAdapter() {
		});
		btnViewTotalCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MaintenanceTotCost.main(new String[] {}); // Open the 'View total cost' window
			}
		});

		// Button for 'Exit' with an action
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {}); // Open the 'Dashboard' window
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected row index
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
					return; // Exit if no row is selected
				}
				int option = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to delete this maintenance record?", "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {

				// Get the ID of the record to be deleted from the selected row
				int idToDelete = (int) table.getValueAt(selectedRow, 0);

				// Delete the record from the database
				deleteMaintenance(idToDelete);

				// Remove the selected row from the table
				tableModel.removeRow(selectedRow);
				}
			}
		});
	}

	private void clearFields() {
	    textField.setText(Integer.toString(getNextMaintenanceID())); 
	    textField_3.setText("");  
	    textField_4.setText(""); 

	    dateChooser.setDate(null);  // Resetting the date picker

	    if (comboBox.getItemCount() > 0) {
	        comboBox.setSelectedIndex(0);  // Resetting the combo box to the first item
	    }
	}
	// Method to populate the table with maintenance data from the database
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
				String query = "SELECT * FROM vehicle_maintenance";
				ResultSet resultSet = statement.executeQuery(query);

				// Iterate through the result set and populate the JTable
				while (resultSet.next()) {
					// Retrieve data from the result set
					int id = resultSet.getInt("m_id");
					String vehiid = resultSet.getString("m_vehi_reg_no");
					String date = resultSet.getString("m_date");
					String servicecenter = resultSet.getString("m_servicecenter");
					double price = resultSet.getDouble("m_price");

					// Create a 'maintenance' object with retrieved data
					Maintenance maintenance = new Maintenance(id, vehiid, date, servicecenter, price);
					Maintenance.add(maintenance);

					// Create a vector to add data to the table model
					Vector<Object> row = new Vector<>();
					row.add(maintenance.getId());
					row.add(maintenance.getvehi_reg_no());
					row.add(maintenance.getDate());
					row.add(maintenance.getServicecenter());
					row.add(maintenance.getPrice());

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

	// Method to get the next available maintenance ID from the database
	private int getNextMaintenanceID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(m_id) FROM vehicle_maintenance";
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

	// Method to populate the vehicle dropdown with vehicle IDs from the database
	private void populateVehicleDropdown() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT vehi_reg_no FROM vehicle_specifications";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String vehi_reg_no = resultSet.getString("vehi_reg_no");
					comboBox.addItem(vehi_reg_no);
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

	// Method to add maintenance data to the database
	private void addMaintenance() {
		// Get data from input fields
		String vehiid = (String) comboBox.getSelectedItem();
		String date = formattedDate;
		String servicecenter = textField_3.getText();

		// Validate input fields
		if (vehiid.isEmpty() || date.isEmpty() || servicecenter.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
			return; // Exit the method if any field is empty
		}

		String checkprice;
		double price;

		try {
			checkprice = textField_4.getText();

			if (checkprice.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please fill in all the details.");
				return; // Exit the method if any field is empty
			} else {
				price = Double.parseDouble(checkprice);
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Price should be numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
			return; // Exit the method if parsing fails
		}

		if (price == 0.0) {
			JOptionPane.showMessageDialog(frame, "Price cannot be zero.");
			return; // Exit the method if price or total price is zero
		}

		// Create a 'maintenance' object with the entered data
		Maintenance maintenance = new Maintenance(0, vehiid, date, servicecenter, price);
		Maintenance.add(maintenance);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Insert the maintenance data into the database
				String query = "INSERT INTO vehicle_maintenance (m_vehi_reg_no, m_date, m_servicecenter, m_price) VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, maintenance.getvehi_reg_no());
				preparedStatement.setString(2, maintenance.getDate());
				preparedStatement.setString(3, maintenance.getServicecenter());
				preparedStatement.setDouble(4, maintenance.getPrice());

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated id

						// Update the ID field with the generated ID
						textField.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Maintenance added successfully!");
						populateTable();
						clearFields();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to add the Maintenance.");
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

	private void deleteMaintenance(int idToDelete) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Create SQL query to delete the record with the specified ID
				String query = "DELETE FROM vehicle_maintenance WHERE m_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idToDelete);

				// Execute the delete query
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(frame, "Maintenance record deleted successfully!");
				} else {
					JOptionPane.showMessageDialog(frame, "Failed to delete maintenance record.");
				}

				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	private void generatePDFReport() {
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
			String filePath = desktopPath + File.separator + "Maintenance_Report.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			Paragraph title = new Paragraph("Maintenance Report");
			title.setAlignment(Paragraph.ALIGN_CENTER);
			title.setSpacingAfter(20);
			document.add(title);

			PdfPTable pdfTable = new PdfPTable(5);
			pdfTable.setWidthPercentage(100);
			pdfTable.setSpacingAfter(10);

			float[] columnWidths = { 2f, 3f, 3f, 3f, 2f };
			pdfTable.setWidths(columnWidths);

			// Add table headers
			addPdfTableCell(pdfTable, "ID");
			addPdfTableCell(pdfTable, "Vehicle ID");
			addPdfTableCell(pdfTable, "Date");
			addPdfTableCell(pdfTable, "Service Center Name");
			addPdfTableCell(pdfTable, "Price");

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				addPdfTableCell(pdfTable, model.getValueAt(i, 0).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 1).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 2).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 3).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 4).toString());
			}

			document.add(pdfTable);

			// Add total cost

			document.close();
			JOptionPane.showMessageDialog(frame, "PDF Report generated successfully!");

			// Open the generated PDF file after creation
			File pdfFile = new File(filePath);
			if (pdfFile.exists()) {
				Desktop.getDesktop().open(pdfFile);
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace(); // Print any exceptions that occur
		}
	}

	private void addPdfTableCell(PdfPTable table, String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}
	
	// Method to fill text fields with example data
	private void fillDemoData() {
	    // Example data
	    String registrationNumber = "ABC123";
	    String serviceCenterName = "Wash Me Center";
	    Date exampleDate = new Date(); // Create a new Date object with the current date
	    
	    // Populate text fields with example data
	    comboBox.setSelectedItem(registrationNumber);
	    textField_3.setText(serviceCenterName);
	    dateChooser.setDate(exampleDate); // Set the date picker to the example date
	}
	
}

