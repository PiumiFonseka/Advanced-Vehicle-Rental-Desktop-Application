package piumi;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import piumi.customerInfo;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class customerProfile {

	private JFrame frame;
	private JTable table;
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblNIC;
	private JLabel lblContactNumber;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JLabel lblEmergencyName;
	private JLabel lblEmergencyNumber;
	private JLabel cusAge;
	private JLabel cusGender;
	private JLabel noOfRes;
	private JLabel loyaltyCus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerProfile window = new customerProfile();
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
	public customerProfile() {
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
				customerInfo.main(new String[] {});
			}
		});

		//Download Report Button
		JButton btnNewButton = new JButton("Download Report");
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        generatePDFReport();
		    }
		});
		btnNewButton.setBackground(new Color(253, 240, 2));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 27));
		btnNewButton.setBounds(979, 599, 233, 45);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(939, 205, 317, 379);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Vehicle ID", "Model", "Reservation ID" }));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_5.setBounds(37, 26, 177, 45);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("CUSTOMER PERSONAL PROFILE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(329, 23, 710, 83);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 240, 2));
		panel.setBounds(-25, 0, 1291, 118);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel_1 = new JLabel("Personal Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(10, 129, 249, 39);
		frame.getContentPane().add(lblNewLabel_1);

		lblID = new JLabel("Customer ID : ");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblID.setBounds(37, 178, 284, 33);
		frame.getContentPane().add(lblID);

		lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(37, 221, 239, 33);
		frame.getContentPane().add(lblName);

		lblNIC = new JLabel("NIC :");
		lblNIC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNIC.setBounds(37, 264, 235, 33);
		frame.getContentPane().add(lblNIC);

		lblContactNumber = new JLabel("Contact Number :");
		lblContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContactNumber.setBounds(37, 307, 331, 33);
		frame.getContentPane().add(lblContactNumber);

		lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(37, 350, 356, 33);
		frame.getContentPane().add(lblEmail);

		lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(37, 392, 361, 33);
		frame.getContentPane().add(lblAddress);

		JLabel lblNewLabel_1_1 = new JLabel("Emergency Contacts");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(392, 128, 249, 39);
		frame.getContentPane().add(lblNewLabel_1_1);

		lblEmergencyName = new JLabel("Name :");
		lblEmergencyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmergencyName.setBounds(392, 178, 337, 33);
		frame.getContentPane().add(lblEmergencyName);

		lblEmergencyNumber = new JLabel("Contact Number :");
		lblEmergencyNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmergencyNumber.setBounds(392, 221, 349, 33);
		frame.getContentPane().add(lblEmergencyNumber);

		noOfRes = new JLabel("Number of reservation with us :");
		noOfRes.setFont(new Font("Tahoma", Font.PLAIN, 27));
		noOfRes.setBounds(362, 435, 479, 39);
		frame.getContentPane().add(noOfRes);
	
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.YELLOW);
		separator.setBackground(new Color(192, 192, 192));
		separator.setBounds(380, 159, 263, 17);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.YELLOW);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(10, 159, 192, 17);
		frame.getContentPane().add(separator_1);

		JLabel lblNewLabel_1_4 = new JLabel("Additional Details");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1_4.setBounds(10, 474, 208, 39);
		frame.getContentPane().add(lblNewLabel_1_4);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.YELLOW);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(10, 508, 239, 17);
		frame.getContentPane().add(separator_2);

		cusAge = new JLabel("Age:");
		cusAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cusAge.setBounds(37, 537, 197, 33);
		frame.getContentPane().add(cusAge);

		// Get NIC number from the customer form and trim any leading or trailing spaces
		String nic = lblNIC.getText().trim();
		// Calculate age from NIC number
		int age = calculateAgeFromNIC(nic);
		// Display age in customer profile
		cusAge.setText("Age: " + age);

		cusGender = new JLabel("Gender: ");
		cusGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cusGender.setBounds(37, 580, 165, 33);
		frame.getContentPane().add(cusGender);

		loyaltyCus = new JLabel("Loyalty Customer : \r\n");
		loyaltyCus.setBackground(new Color(0, 255, 255));
		loyaltyCus.setFont(new Font("Tahoma", Font.PLAIN, 27));
		loyaltyCus.setBounds(368, 362, 390, 39);
		frame.getContentPane().add(loyaltyCus);

		JLabel lblNewLabel_1_6 = new JLabel("Vehicle that have rented");
		lblNewLabel_1_6.setBounds(926, 148, 294, 33);
		frame.getContentPane().add(lblNewLabel_1_6);
		lblNewLabel_1_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 27));

	}

	public static void showCustomerDetails(Object[] customerData) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerProfile window = new customerProfile();
					window.frame.setVisible(true);
					window.displayCustomerDetails(customerData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void displayCustomerDetails(Object[] customerData) {

		// Display customer details in the GUI components of the customerProfile window
		lblID.setText("Customer ID: " + customerData[0]);
		lblName.setText("Name: " + customerData[1] );
		lblNIC.setText("NIC: " + customerData[2] );
		lblContactNumber.setText("Contact Number: " + customerData[3] );
		lblEmail.setText("Email: " + customerData[4] );
		lblAddress.setText("Address: " + customerData[5] );
		lblEmergencyName.setText("Emergency Name: " + customerData[6] );
		lblEmergencyNumber.setText("Emergency Number: " + customerData[7] );

		// customerData[0] contains the customer ID
		int customerId = Integer.parseInt(customerData[0].toString());
		// Retrieve the total number of reservations for the customer from the database
		int totalReservations = getTotalReservationsForCustomer(customerId);
		// Display the total number of reservations in the GUI
		noOfRes.setText("Number of reservations with us: " + totalReservations);

		// Get NIC number from the driverData array
		String nic = customerData[2].toString(); // stored at index 2
		// Calculate age from NIC number
		int age = calculateAgeFromNIC(nic);
		// Display age
		cusAge.setText("Age : " + age);

		// Determine gender based on NIC
		char gender = getGenderFromNIC(nic);
		// display gender
		cusGender.setText("Gender:  " + (gender == 'M' ? "Male" : "Female") );

		// Determine if the customer is a loyalty customer
		boolean isLoyaltyCustomer = checkIfLoyaltyCustomer(customerId);
		if (isLoyaltyCustomer) {
			loyaltyCus.setText("Loyalty Customer: Yes");
			loyaltyCus.setForeground(Color.red); // to display loyalty customer from red colors
		} else {
			loyaltyCus.setText("Loyalty Customer:  No");
		}

		// Populate the vehicle with reservation table with data for the specific customer
		populateVehicleReservationTable(customerId);
		
		
		// Set driverData
			this.customerData = customerData;

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

	// To get Total Reservations For Customer
	private int getTotalReservationsForCustomer(int customerId) {
		int totalReservations = 0;
		String query = "SELECT COUNT(*) FROM reservations " + "WHERE cid = ?"; // Join condition on customer ID
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, customerId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					totalReservations = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any SQL exceptions
		}
		return totalReservations;
	}

	// Method to check if a customer is a loyalty customer
	private boolean checkIfLoyaltyCustomer(int customerId) {
		boolean isLoyaltyCustomer = false;
		String query = "SELECT COUNT(r.r_id) AS num_reservations " + "FROM customer c "
				+ "LEFT JOIN reservations r ON c.c_id = r.cid " + "WHERE c.c_id = ? " + "GROUP BY c.c_id "
				+ "HAVING COUNT(r.r_id) >= 3 " + "ORDER BY COUNT(r.r_id) DESC";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, customerId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int numReservations = resultSet.getInt("num_reservations");
					if (numReservations >= 3) {
						isLoyaltyCustomer = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any SQL exceptions
		}
		return isLoyaltyCustomer;
	}

	private void populateVehicleReservationTable(int customerId) {
		DefaultTableModel vehicleReservationTableModel = getCustomerVehicleDataFromDatabase(customerId);
		table.setModel(vehicleReservationTableModel);
	}

	private DefaultTableModel getCustomerVehicleDataFromDatabase(int customerId) {
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Vehicle ID", "Model", "Reservation ID" });

		// Fetch data from the database using JOIN operation to get vehicle information and reservation ID
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT vs.vehi_id, vs.vehi_brand, r.r_id " + "FROM vehicle_specifications vs "
						+ "INNER JOIN reservations r ON vs.vehi_id = r.vehi_id " + "WHERE r.cid = ?";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, customerId); // Bind customer ID to the query

				ResultSet resultSet = statement.executeQuery();

				// Populate the table model with retrieved data
				while (resultSet.next()) {
					Object[] rowData = new Object[3];
					rowData[0] = resultSet.getInt("vehi_id");
					rowData[1] = resultSet.getString("vehi_brand");
					rowData[2] = resultSet.getInt("r_id");

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

	
	
	private Object[] customerData; // Declare as a member variable

	// Method to set customerData
	public void setCustomerData(Object[] data) {
	    this.customerData = data;
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
	    String watermarkText = "Confidential"; // Watermark text
	    Document document = new Document();
	    try {
	        // Get the customer ID from the customerData array
	        String customerID = customerData[0].toString(); 

	        // Construct the filename with the customer ID
	        String filename = "Customer report id" + customerID + ".pdf";

	        // Provide the path to save the PDF report with the filename
	        String filePath = desktopPath + File.separator + filename;
	   
	     // Initialize the PdfWriter instance to write to the document
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
      
	        document.open();

	        // Add content to the PDF report
	        document.add(new Paragraph("Vass Enterprises", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.DARK_GRAY)));
	        document.add(new Paragraph("No. 31, KadolUyana, Negombo", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph("+94766150388", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph("vassEnterprises@gmail.com", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
			document.add(new Paragraph(" "));
	        document.add(new Paragraph("Customer Report", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));
	        
	        document.add(new Paragraph(
					"Confidential: The following report contains personal profiles. Access to this information is"
					+ " restricted to authorized personnel only. Any unauthorized distribution or use of"
					+ " this information is strictly prohibited.",FontFactory.getFont(FontFactory.HELVETICA, 10)));

	        document.add(new Paragraph(" ")); // to keep a line space after topic
	        document.add(new Paragraph(" ")); // to keep a line space after topic	 
	        
	        // Add customer details to the PDF report
	        addContentToDocument(document, "Customer ID", lblID);
	        addContentToDocument(document, "Name", lblName);
	        addContentToDocument(document, "NIC", lblNIC);
	        addContentToDocument(document, "Contact Number", lblContactNumber);
	        addContentToDocument(document, "Email", lblEmail);
	        addContentToDocument(document, "Address", lblAddress);
	        addContentToDocument(document, "Emergency Contact Name", lblEmergencyName);
	        addContentToDocument(document, "Emergency Contact Number", lblEmergencyNumber);
	        addContentToDocument(document, "Loyalty Customer", loyaltyCus);
	        addContentToDocument(document, "Number of reservations with us", noOfRes);
	        
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


	// Helper method to add content to the PDF document
		private void addContentToDocument(Document document, String label, JLabel component) throws DocumentException, IOException {
		    // Extract plain text from HTML string
		    String labelText = component.getText().replaceAll("<[^>]*>", "").trim();
		    
		    // Add label and corresponding text from JLabel to the PDF document
		    Chunk chunkText = new Chunk(labelText, FontFactory.getFont(FontFactory.HELVETICA));
		    Paragraph paragraph = new Paragraph();

		    paragraph.add(chunkText);
		    document.add(paragraph);
		    document.add(Chunk.NEWLINE); // Add newline after each entry
		}

	
	
	
	
	

	
	
	
}
