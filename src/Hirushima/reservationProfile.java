//reservation profile

package Hirushima;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;

import piumi.DatabaseConnection;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class reservationProfile {

	private JFrame frame;
	private JLabel lblrID;
	private JLabel lblcID;
	private JLabel lblvID;
	private JLabel lblDriver;
	private JLabel lblpd;
	private JLabel lblpt;
	private JLabel lblrd;
	private JLabel lblrt;
	private JLabel lbldays;
	private JLabel lblcost;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservationProfile window = new reservationProfile();
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
	public reservationProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblReservationInformation = new JLabel("RESERVATION INFORMATION");
		lblReservationInformation.setBackground(new Color(243, 219, 50));
		lblReservationInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservationInformation.setFont(new Font("Times New Roman", Font.BOLD, 55));
		lblReservationInformation.setBounds(212, 60, 841, 65);
		frame.getContentPane().add(lblReservationInformation);
		
		JLabel back = new JLabel("Back");
		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(22, 22, 58, 19);
		frame.getContentPane().add(back);
		back.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            reservationForm form = new reservationForm();
	            form.setVisible(true);
	            frame.dispose(); // Close the current frame
	        }
	    });
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 219, 50));
		panel.setBounds(201, 51, 863, 81);
		frame.getContentPane().add(panel);
		
		JLabel r_id = new JLabel("Reservation ID:");
		r_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		r_id.setBounds(122, 192, 180, 32);
		frame.getContentPane().add(r_id);
		
		JLabel c_id = new JLabel("Customer ID:");
		c_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		c_id.setBounds(122, 254, 180, 32);
		frame.getContentPane().add(c_id);
		
		JLabel v_id = new JLabel("Vehicle ID:");
		v_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		v_id.setBounds(122, 313, 180, 32);
		frame.getContentPane().add(v_id);
		
		JLabel driver = new JLabel("Driver needed?");
		driver.setFont(new Font("Tahoma", Font.PLAIN, 25));
		driver.setBounds(122, 374, 180, 32);
		frame.getContentPane().add(driver);
		
		JLabel p_date = new JLabel("Pickup date:");
		p_date.setFont(new Font("Tahoma", Font.PLAIN, 25));
		p_date.setBounds(122, 430, 180, 32);
		frame.getContentPane().add(p_date);
		
		JLabel r_date = new JLabel("Return date:");
		r_date.setFont(new Font("Tahoma", Font.PLAIN, 25));
		r_date.setBounds(122, 484, 180, 32);
		frame.getContentPane().add(r_date);
		
		JLabel noDays = new JLabel("No of days:");
		noDays.setFont(new Font("Tahoma", Font.PLAIN, 25));
		noDays.setBounds(122, 543, 180, 32);
		frame.getContentPane().add(noDays);
		
		JLabel cost = new JLabel("Cost:");
		cost.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cost.setBounds(122, 600, 180, 32);
		frame.getContentPane().add(cost);
		
		JLabel p_time = new JLabel("Pickup time:");
		p_time.setFont(new Font("Tahoma", Font.PLAIN, 25));
		p_time.setBounds(502, 430, 192, 32);
		frame.getContentPane().add(p_time);
		
		JLabel r_time = new JLabel("Return time:");
		r_time.setFont(new Font("Tahoma", Font.PLAIN, 25));
		r_time.setBounds(502, 484, 153, 32);
		frame.getContentPane().add(r_time);
		
		lblrID = new JLabel(""); // Remove the re-declaration
	    lblrID.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    lblrID.setBounds(312, 192, 180, 29);
	    frame.getContentPane().add(lblrID);
		
		lblcID = new JLabel("");
		lblcID.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblcID.setBounds(312, 257, 180, 29);
		frame.getContentPane().add(lblcID);
		
		lblvID = new JLabel("");
		lblvID.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblvID.setBounds(312, 313, 180, 29);
		frame.getContentPane().add(lblvID);
		
		lblDriver = new JLabel("");
		lblDriver.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDriver.setBounds(312, 377, 180, 29);
		frame.getContentPane().add(lblDriver);
		
		lblpd = new JLabel("");
		lblpd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblpd.setBounds(312, 430, 153, 29);
		frame.getContentPane().add(lblpd);
		
		lblrd = new JLabel("");
		lblrd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblrd.setBounds(312, 487, 180, 29);
		frame.getContentPane().add(lblrd);
		
		lbldays = new JLabel("");
		lbldays.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbldays.setBounds(312, 546, 180, 29);
		frame.getContentPane().add(lbldays);
		
		lblcost = new JLabel("");
		lblcost.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblcost.setBounds(312, 600, 180, 29);
		frame.getContentPane().add(lblcost);
		
		lblpt = new JLabel("");
		lblpt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblpt.setBounds(649, 433, 192, 29);
		frame.getContentPane().add(lblpt);
		
		lblrt = new JLabel("");
		lblrt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblrt.setBounds(649, 487, 180, 29);
		frame.getContentPane().add(lblrt);
		
		JButton btnNewButton = new JButton("Generate report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 generateReport();
			}
		});
		
		btnNewButton.setBackground(new Color(243, 219, 50));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(931, 614, 251, 41);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(80, 168, 784, 502);
		frame.getContentPane().add(panel_1);
		
						
	}
	
	public void fetchReservationDetails(int reservationID) {
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            String reservationQuery = "SELECT * FROM reservations WHERE r_id = ?";
	            PreparedStatement reservationStatement = connection.prepareStatement(reservationQuery);
	            reservationStatement.setInt(1, reservationID);
	            ResultSet reservationResult = reservationStatement.executeQuery();

	            // Check if the reservation exists
	            if (reservationResult.next()) {
	                int customerID = reservationResult.getInt("cid");
	                int vehicleID = reservationResult.getInt("vehi_id");
	                String driverNeeded = reservationResult.getString("driver_needed");
	                String pickDate = reservationResult.getString("pick_date");
	                String pickTime = reservationResult.getString("pick_time");
	                String returnDate = reservationResult.getString("return_date");
	                String returnTime = reservationResult.getString("return_time");
	                int days = reservationResult.getInt("days");
	                double cost = reservationResult.getDouble("cost");

	                // Populate the labels with the fetched reservation details
	                lblrID.setText(Integer.toString(reservationID));
	                lblcID.setText(Integer.toString(customerID));
	                lblvID.setText(Integer.toString(vehicleID));
	                lblDriver.setText(driverNeeded);
	                lblpd.setText(pickDate);
	                lblpt.setText(pickTime);
	                lblrd.setText(returnDate);
	                lblrt.setText(returnTime);
	                lbldays.setText(Integer.toString(days));
	                lblcost.setText(Double.toString(cost));
	            } else {
	                System.out.println("No reservation found with ID: " + reservationID);
	            }

	            reservationResult.close();
	            reservationStatement.close();

	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        } finally {
	            DatabaseConnection.closeConnection(connection);
	        }
	    } else {
	        System.out.println("Failed to connect to the database.");
	    }
	}


	public void setVisible(boolean b) {
		frame.setVisible(b);	
	}
	
	private void generateReport() {
	    // Extract data from the reservation form components
	    String reservationID = lblrID.getText();
	    int customerID = Integer.parseInt(lblcID.getText());
	    String customerName = getCustomerName(customerID);
	    int vehicleID = Integer.parseInt(lblvID.getText());
	    String v_regNo = getRegisterNo(vehicleID);
	    String driverNeeded = lblDriver.getText();
	    String pickDate = lblpd.getText();
	    String pickTime = lblpt.getText();
	    String returnDate = lblrd.getText();
	    String returnTime = lblrt.getText();
	    String days = lbldays.getText();
	    String cost = lblcost.getText();

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int result = fileChooser.showSaveDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) {
	        return; // Exit if the user cancels the operation
	    }

	    File desktopDirectory = fileChooser.getSelectedFile();
	    String desktopPath = desktopDirectory.getAbsolutePath();
	    String fileName = "Reservation_" + reservationID + ".pdf";
	    File pdfFile = new File(desktopPath, fileName);

	    // Create a PDF document
	    Document document = new Document();

	    try {
	        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
	        document.open();
	        
	        document.add(new Paragraph("Vass Enterprises", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.DARK_GRAY)));
	        document.add(new Paragraph(" "));
	        
	        document.add(new Paragraph("Reservation Report", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));

	        // Add content to the PDF report
	        document.add(new Paragraph("1.  Reservation ID: " + reservationID));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("2.  Customer Name :"+ customerName));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("3.  Vehicle Registration number: " + v_regNo));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("4.  Driver Needed: " + driverNeeded));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("5.  Pickup Date: " + pickDate));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("6.  Pickup Time: " + pickTime));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("7.  Return Date: " + returnDate));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("8.  Return Time: " + returnTime));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("9.  Number of Days: " + days));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("10. Cost: " + cost));
	        document.add(Chunk.NEWLINE);
	        document.add(Chunk.NEWLINE);

	        JOptionPane.showMessageDialog(frame, "Reservation report generated successfully!");

	        // Open the PDF file automatically
	        if (Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().open(pdfFile);
	        } else {
	            JOptionPane.showMessageDialog(frame, "Desktop is not supported, please open the PDF manually.");
	        }

	    } catch (FileNotFoundException | DocumentException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (document != null) {
	            document.close();
	        }
	    }
	}


	private String getCustomerName(int customerID) {
	    String customerName = "";
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        try {
	            String query = "SELECT c_name FROM customer WHERE c_id = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, customerID);
	            resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                customerName = resultSet.getString("c_name");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            DatabaseConnection.closeConnection(connection);
	        }
	    }
	    return customerName;
	}
	private String getRegisterNo(int vehicleID) {
	    String customerName = "";
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        try {
	            String query = "SELECT vehi_reg_no FROM vehicle_specifications WHERE vehi_id = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, vehicleID);
	            resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                customerName = resultSet.getString("vehi_reg_no");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            DatabaseConnection.closeConnection(connection);
	        }
	    }
	    return customerName;
	}
}