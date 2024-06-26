package bhagya;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextPane;




import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class payment {

	
	public JFrame frame_1;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTable table_2;

	protected JComponent frame_11;

	protected Object frame1;

	protected advanced_Payment frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payment window = new payment();
					window.frame_1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public payment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		        frame_1 = new JFrame();
		        frame_1.getContentPane().setBackground(new Color(128, 128, 128));
		        frame_1.setBounds(100, 100, 1280, 720);
		        frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame_1.getContentPane().setLayout(null);

		        JButton btnNewButton = new JButton("back");
		        btnNewButton.setBounds(10, 10, 85, 21);
		        frame_1.getContentPane().add(btnNewButton);
		        
		        
		        
		        
		        
		        
		        

		        JLabel lblNewLabel = new JLabel("FULL PAYMENT");
		        lblNewLabel.setBounds(397, 53, 529, 85);
		        lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 54));
		        frame_1.getContentPane().add(lblNewLabel);

		        JLabel lblNewLabel_1 = new JLabel("Fine:");
		        lblNewLabel_1.setBounds(437, 208, 145, 25);
		        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(lblNewLabel_1);

		        
		        JLabel lblNewLabel_1_1 = new JLabel("Full payment:");
		        lblNewLabel_1_1.setBounds(437, 243, 185, 25);
		        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(lblNewLabel_1_1);


		        JLabel lblNewLabel_2 = new JLabel("Payment method:");
		        lblNewLabel_2.setBounds(430, 290, 178, 21);
		        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(lblNewLabel_2);

		        
		        JLabel lblNewLabel_3 = new JLabel("Customer ID:");
		        lblNewLabel_3.setBounds(437, 170, 145, 28);
		        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(lblNewLabel_3);
		        
		        
		        //methods
		        JComboBox<String> comboBox2 = new JComboBox<>();
		    	comboBox2.addItem("CARD");
		    	comboBox2.addItem("CASH");
		    	
		    	
		    	// Add more items as needed

		    	comboBox2.setBounds(610, 300, 194, 19); // Set the position and size as per your requirement
		    	frame_1.getContentPane().add(comboBox2); // Add the JComboBox to your frame
		        

		        
		        //search id
		        textField_7 = new JTextField();
		        textField_7.setColumns(10);
		        textField_7.setBounds(1000, 40, 105, 21);
		        frame_1.getContentPane().add(textField_7);
		        
		        
		        //search id button
		        JButton btnNewButton_1_7 = new JButton("search ID");
		        btnNewButton_1_7.setBackground(new Color(255, 255, 0));
		        btnNewButton_1_7.setBounds(1150, 40, 105, 21);
		        frame_1.getContentPane().add(btnNewButton_1_7);
		        
		        
		        // search customer ID button
		        
		        JLabel lblNewLabel_2_3 = new JLabel("Search Customer ID");
		        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
		        lblNewLabel_2_3.setBounds(1012, 10, 200, 31);
		        frame_1.getContentPane().add(lblNewLabel_2_3);
		     
                   // downlaod pay slip button 
		        JButton btnNewButton_1 = new JButton("Download pay slip");
		        btnNewButton_1.setBounds(945, 636, 303, 21);
		        btnNewButton_1.setBackground(new Color(255, 255, 0));
		        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_1);

		        
		        //////////////////////////////////////////////////////////////// download pdf file////////////////////////////////////////////////
		        btnNewButton_1.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                generatePDF();
		            }
		        
		            private void generatePDF() {
		                try {
		                    String CustomerID = textField_7.getText();

		                    Class.forName("com.mysql.jdbc.Driver");
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

		                    String query = "SELECT * FROM fully WHERE CustomerID = ?";
		                    PreparedStatement stmt = con.prepareStatement(query);
		                    stmt.setString(1, CustomerID);
		                    ResultSet resultSet = stmt.executeQuery();

		                    if (resultSet.next()) {
		                        String Fine = resultSet.getString("Fine");
		                        String Fpay = resultSet.getString("Fpay");
		                        String methods = resultSet.getString("methods");

		                        Document document = new Document();
		                        PdfWriter.getInstance(document, new FileOutputStream("PaySlip_" + CustomerID + ".pdf"));
		                        document.open();
		                        document.add(new Paragraph("    FULL PAYMENT DETAILS \n  "));
		                        
		                        document.add(new Paragraph("     Customer ID: " + CustomerID));
		                        document.add(new Paragraph("     Fine: " + Fine+" Rs"));
		                        document.add(new Paragraph("     Full payment: " + Fpay+" Rs"));
		                        document.add(new Paragraph("     Payment method:" + methods +"\n \n" ));
		                        document.add(new Paragraph("     Full payment  was paid \n  "));
		                        document.add(new Paragraph("     THANK YOU COME AGAIN !	 \n \n "));
		                        
		                       
		                        
		                        document.close();
		                        if (Desktop.isDesktopSupported()) {
		                            File pdfFile = new File("PaySlip_" + CustomerID + ".pdf");
		                            Desktop.getDesktop().open(pdfFile);
		                        } else {
		                            JOptionPane.showMessageDialog(null, "PDF generated but could not be opened automatically.");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Customer ID not found.");
		                    }


		                    con.close();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error generating PDF: " + ex.getMessage());
		                }
		            }
		        });
		        
		        

		        
		       /////////////////////////////////////////// adding part////////////////////////////////////////////////////////////////////////////////
		        
		        JButton btnNewButton_2 = new JButton("ADD");
		        btnNewButton_2.setBounds(1000, 507, 111, 21);
		        btnNewButton_2.setBackground(new Color(255, 255, 0));
		        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_2);
		        
		        
		        btnNewButton_2.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                String Fine = textField_1.getText();
		                String Fpay = textField_2.getText();
		                String CustomerID = textField.getText();
		                
		                // Get the selected payment method from the JComboBox
		                String methods = comboBox2.getSelectedItem().toString();

		                if (Fine.isEmpty() || Fpay.isEmpty() || CustomerID.isEmpty() || methods.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
		                    return; // Exit the action if any field is empty
		                }

		                try {
		                    Class.forName("com.mysql.jdbc.Driver");
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

		                    // Ensure that the table structure matches the columns in your SQL query
		                    String sql = "INSERT INTO fully (CustomerID, Fine, Fpay, methods) VALUES (?, ?, ?, ?)";
		                    PreparedStatement stmt = con.prepareStatement(sql);

		                    stmt.setString(1, CustomerID);
		                    stmt.setString(2, Fine);
		                    stmt.setString(3, Fpay);
		                    stmt.setString(4, methods);

		                    int rowsAffected = stmt.executeUpdate();
		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(null, "Payments Successful!");
		                        setVisible(false);
		                        advanced_Payment advanced_PaymentFrame = new advanced_Payment();
		                        advanced_PaymentFrame.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Payments Failed!");
		                    }

		                    con.close();
		                } catch (Exception ae) {
		                    ae.printStackTrace();
		                }
		            }
		        });

		        
		        
		        
		        
		       
		        
		        ////////////////////////////////////////////////////////////deleting part /////////////////////////////////////////////////////////
		        

		        JButton btnNewButton_2_1 = new JButton("Delete");
		        btnNewButton_2_1.setBounds(1150, 507, 108, 21);
		        btnNewButton_2_1.setBackground(new Color(255, 255, 0));
		        btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_2_1);
		        

		        btnNewButton_2_1.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                String CustomerID = textField.getText();
		                String Fine = textField_1.getText();

		                if (CustomerID.isEmpty() || Fine.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please enter CustomerID and Fine.");
		                    return; // Exit the action if any field is empty
		                }

		                try {
		                    Class.forName("com.mysql.jdbc.Driver");
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

		                    String deleteQuery = "DELETE FROM fully WHERE CustomerID = ? AND Fine = ?";
		                    PreparedStatement stmt = con.prepareStatement(deleteQuery);
		                    stmt.setString(1, CustomerID);
		                    stmt.setString(2, Fine);

		                    int rowsAffected = stmt.executeUpdate();
		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(null, "Payment record deleted successfully!");
		                        // Optionally, refresh the frame or UI component if needed
		                    } else {
		                        JOptionPane.showMessageDialog(null, "No payment record found for deletion.");
		                    }

		                    con.close();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "An error occurred while deleting the payment record: " + ex.getMessage());
		                }
		            }
		        });
		        
		        
		        /////////////////////////////////////////////// viewing part ////////////////////////////////////////////////////
		        
		        
		        JButton btnNewButton_2_4 = new JButton("view");
		        btnNewButton_2_4.setBounds(1050, 450, 108, 21);
		        btnNewButton_2_4.setBackground(new Color(255, 255, 0));
		        btnNewButton_2_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_2_4);
		        
		        
		        JScrollPane scrollPane = new JScrollPane();
		        scrollPane.setBounds(0, 400, 979, 193);
		        frame_1.getContentPane().add(scrollPane);

		        table_2 = new JTable();
		        scrollPane.setViewportView(table_2);
		        table_2.setModel(new DefaultTableModel(
		                new Object[][]{},
		                new String[]{ "CustomerID" ,"Fine", "Fpay",  "methods"}
		        ));
		        
		        
		        
		        
		        
		        
		        
		        btnNewButton_2_4.addActionListener(new ActionListener() {
		            private DefaultTableModel tableModel;

		            @Override
		            public void actionPerformed(ActionEvent e) {
		                searchDatabase();
		            }

		            private void searchDatabase() {
		                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
		                    String query = "SELECT * FROM fully";
		                    PreparedStatement stmt = connection.prepareStatement(query);
		                    ResultSet resultSet = stmt.executeQuery();

		                    // Clear existing table data
		                    tableModel = (DefaultTableModel) table_2.getModel();
		                    tableModel.setRowCount(0);

		                    while (resultSet.next()) {
		                        String CustomerID  = resultSet.getString("CustomerID");
		                        String Fine  = resultSet.getString("Fine");
		                        String Fpay = resultSet.getString("Fpay");
		                        
		                        String methods = resultSet.getString("methods");


		                        // Add the retrieved data to the table model
		                        tableModel.addRow(new Object[]{CustomerID, Fine, Fpay, methods});
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        });
		        
		        
		        
		        
		        
		        //////////////////////////////////////////////////editing part////////////////////////////////////////////////////////

		        JButton btnNewButton_2_2 = new JButton("Edit");
		        btnNewButton_2_2.setBounds(1000, 552, 111, 21);
		        btnNewButton_2_2.setBackground(new Color(255, 255, 0));
		        btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_2_2);
		        
		        
		        
		        btnNewButton_2_2.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                String CustomerID = textField.getText();
		                String Fine = textField_1.getText();

		                try {
		                    Class.forName("com.mysql.jdbc.Driver");
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

		                    // Ensure that the table structure matches the columns in your SQL query
		                    String sql = "UPDATE fully SET Fine = ? WHERE CustomerID = ?";

		                    PreparedStatement stmt = con.prepareStatement(sql);
		                    stmt.setString(1, Fine);
		                    stmt.setString(2, CustomerID);

		                    int rowsAffected = stmt.executeUpdate();
		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(null, "Update Successful!");
		                        setVisible(false);
		                        advanced_Payment loginFrame = new advanced_Payment();
		                        loginFrame.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Please enter CustomerID and Fine.");
		                    }

		                    con.close();
		                } catch (ClassNotFoundException ex) {
		                    JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
		                    ex.printStackTrace();
		                } catch (SQLException ex) {
		                    JOptionPane.showMessageDialog(null, "SQL Exception: " + ex.getMessage());
		                    ex.printStackTrace();
		                } catch (Exception ex) {
		                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
		                    ex.printStackTrace();
		                }
		            }
		        });
		        
		        
		     


		        
		        
		        
		        

		        JButton btnNewButton_2_3 = new JButton("Clear");
		        btnNewButton_2_3.setBounds(1150, 556, 108, 21);
		        btnNewButton_2_3.setBackground(new Color(255, 255, 0));
		        btnNewButton_2_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		        frame_1.getContentPane().add(btnNewButton_2_3);
		        
		        
		        
		        
		        
		        
		        ////////////////////////////////////////////////////////search customer part ////////////////////////////////////////////////////
		        
		       //  customer id text field
		        
		        textField = new JTextField();
		        textField.setBounds(600, 179, 204, 21);
		        frame_1.getContentPane().add(textField);
		        textField.setColumns(10);
		        
		        // fine text field
		        
		        textField_1 = new JTextField();
		        textField_1.setBounds(600, 215, 204, 19);
		        frame_1.getContentPane().add(textField_1);
		        textField_1.setColumns(10);
		        
		        // full payment
		        textField_2 = new JTextField();
		        textField_2.setBounds(610, 249, 194, 19);
		        frame_1.getContentPane().add(textField_2);
		        textField_2.setColumns(10);
		        
		       
		        
		        
		        
		        
		        
		        frame_1.setVisible(true);
		        
		     
		        
		        
		       
		        // search customer ID's details
		        
		        
		        btnNewButton_1_7.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                String searchCustomerID = textField_7.getText();
		                if (!searchCustomerID.isEmpty()) {
		                    displayCustomerDetails(searchCustomerID);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Please enter a Customer ID.");
		                }
		            }

		            private void displayCustomerDetails(String customerID) {
		                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
		                    String query = "SELECT * FROM fully WHERE CustomerID = ?";
		                    PreparedStatement stmt = connection.prepareStatement(query);
		                    stmt.setString(1, customerID);
		                    ResultSet resultSet = stmt.executeQuery();

		                    DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
		                    tableModel.setRowCount(0); // Clear existing table data

		                    while (resultSet.next()) {
		                    	String CustomerID  = resultSet.getString("CustomerID");
		                    	String Fine  = resultSet.getString("Fine");
		                        String Fpay = resultSet.getString("Fpay");
		                        String methods = resultSet.getString("methods");

		                        // Add the retrieved data to the table model
		                        tableModel.addRow(new Object[]{customerID, Fine , Fpay , methods});
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error fetching customer details: " + ex.getMessage());
		                }
		            }
		        });
		        
		        
		        //////////////////////////////////////////////////go to the advance payment part //////////////////////////////////// 
		        
		     // Inside the initialize() method of advanced_Payment class
		        btnNewButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // Create an instance of the payment class
		                advanced_Payment paymentPage = new  advanced_Payment();
		                // Show the payment page
		                paymentPage.frame.setVisible(true);
		                // Hide the current frame
		                frame_1.setVisible(false);
		            }
		        });
		              
		  
		        
		        
		        
	}
		        
		           
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	}
