package bhagya;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Hiruni.Dashboard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;












public class advanced_Payment {

	protected static final JComponent payment = null;

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JFrame frame1;
    private JTable table_2;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					advanced_Payment window = new advanced_Payment();
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
	public advanced_Payment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ADVANCE PAYMENT");
        lblNewLabel.setBackground(new Color(128, 128, 128));
        lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 50));
        lblNewLabel.setBounds(296, 50, 628, 88);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose(); // Close the current frame
		        Dashboard.main(new String[] {});
        	}
        });
        btnNewButton.setBounds(10, 22, 85, 21);
        frame.getContentPane().add(btnNewButton);
        
        // total price text field
        
        textField = new JTextField();
        textField.setBounds(296, 227, 175, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Payment duration :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(526, 168, 196, 36);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Total price:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2.setBounds(132, 214, 203, 36);
        frame.getContentPane().add(lblNewLabel_2);

        //payment duration text field
        
        textField_1 = new JTextField();
        textField_1.setBounds(753, 181, 196, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        // customer id text field
        
        textField_4 = new JTextField();
        textField_4.setBounds(285, 181, 196, 19);
        frame.getContentPane().add(textField_4);
        textField_4.setColumns(10);
        
        
        JLabel lblNewLabel_1_1 = new JLabel("Discount:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(526, 214, 196, 36);
        frame.getContentPane().add(lblNewLabel_1_1);
        
        // advance payment
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(296, 285, 175, 19);
        frame.getContentPane().add(textField_2);
       
        // payment method text field
        
        JComboBox<String> comboBox2 = new JComboBox<>();
    	comboBox2.addItem("CARD");
    	comboBox2.addItem("CASH");
    	
    	
    	// Add more items as needed

    	comboBox2.setBounds(753, 285, 196, 19); // Set the position and size as per your requirement
    	frame.getContentPane().add(comboBox2); // Add the JComboBox to your frame
        
        
        
        // disscount
    	
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(753, 227, 196, 19);
        frame.getContentPane().add(textField_6);
        
        
        // search cutomer ID 
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(1000, 40, 105, 21);
        frame.getContentPane().add(textField_7);
        
        JLabel lblNewLabel_2_3 = new JLabel("Search Customer ID");
        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lblNewLabel_2_3.setBounds(1012, 10, 200, 31);
        frame.getContentPane().add(lblNewLabel_2_3);

        
        JLabel lblNewLabel_2_1 = new JLabel("Advance:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1.setBounds(132, 272, 203, 36);
        frame.getContentPane().add(lblNewLabel_2_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Payment method :");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1_1.setBounds(526, 272, 196, 36);
        frame.getContentPane().add(lblNewLabel_1_1_1);

       

        JButton searchButton = new JButton("view");
        searchButton.setBackground(new Color(255, 255, 0));
        searchButton.setBounds(1012, 400, 185, 21);
        frame.getContentPane().add(searchButton);
        
        
        
        JButton btnNewButton_1_3 = new JButton("clear");
        btnNewButton_1_3.setBackground(new Color(255, 255, 0));
        btnNewButton_1_3.setBounds(1150, 513, 85, 21);
        frame.getContentPane().add(btnNewButton_1_3);
        
        JButton btnNewButton_2 = new JButton("View full payment");
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_2.setBackground(new Color(255, 255, 0));
        btnNewButton_2.setBounds(1000, 554, 250, 21);
        frame.getContentPane().add(btnNewButton_2);
        
       
        
        JLabel lblNewLabel_2_2 = new JLabel("Customer ID:");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_2.setBounds(132, 168, 203, 36);
        frame.getContentPane().add(lblNewLabel_2_2);  
        
        JButton btnNewButton_1_7 = new JButton("search ID");
        btnNewButton_1_7.setBackground(new Color(255, 255, 0));
        btnNewButton_1_7.setBounds(1150, 40, 105, 21);
        frame.getContentPane().add(btnNewButton_1_7);
        
        
        
       
        
       
       
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 400, 979, 193);
        frame.getContentPane().add(scrollPane);

        table_2 = new JTable();
        scrollPane.setViewportView(table_2);
        table_2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CustomerID", "payDuration", "TotalPrice","Discount", "Advanced", "methods"}
        ));
        
        
        
        
     
        
        JButton btnNewButton_5_1 = new JButton("Download payment report");
        btnNewButton_5_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_5_1.setBackground(Color.YELLOW);
        btnNewButton_5_1.setBounds(945, 636, 303, 21);
        frame.getContentPane().add(btnNewButton_5_1);
        
        
        
        
        
        //////////////////////////////////////////// viewing part//////////////////////////////////////////////////////////////
        
        searchButton.addActionListener(new ActionListener() {
            private DefaultTableModel tableModel;

            @Override
            public void actionPerformed(ActionEvent e) {
                searchDatabase();
            }

            private void searchDatabase() {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
                    String query = "SELECT * FROM paid";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    ResultSet resultSet = stmt.executeQuery();

                    // Clear existing table data
                    tableModel = (DefaultTableModel) table_2.getModel();
                    tableModel.setRowCount(0);

                    while (resultSet.next()) {
                        String CustomerID = resultSet.getString("CustomerID");
                        String payDuration = resultSet.getString("payDuration");
                        String TotalPrice = resultSet.getString("TotalPrice");
                        String Discount = resultSet.getString("Discount");
                        String Advanced = resultSet.getString("Advanced");
                        String methods = resultSet.getString("methods");
                       

                        // Add the retrieved data to the table model
                        tableModel.addRow(new Object[]{CustomerID, payDuration, TotalPrice, Discount, Advanced, methods});
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
	
	
      
 ///////////////////////////////////////adding part /////////////////////////////////////////////////////////////////////////////////////     
        
        JButton btnNewButton_1 = new JButton("Add");
        btnNewButton_1.setBackground(new Color(255, 255, 0));
        btnNewButton_1.setBounds(1000, 471, 85, 21);
        frame.getContentPane().add(btnNewButton_1);
        
        
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String payDuration = textField_1.getText();
                String TotalPrice = textField.getText();
                String Discount = textField_6.getText();
                String Advanced = textField_2.getText();
                String CustomerID = textField_4.getText();
                
                // Get the selected payment method from the JComboBox
                String methods = comboBox2.getSelectedItem().toString();
                
                // This is a validation part
                if (payDuration.isEmpty() || TotalPrice.isEmpty() || Discount.isEmpty() || Advanced.isEmpty() || CustomerID.isEmpty() || methods.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                    return; // Exit the action if any field is empty
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

                    // Ensure that the table structure matches the columns in your SQL query
                    String sql = "INSERT INTO paid (payDuration, TotalPrice, Discount, Advanced, CustomerID, methods) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);

                    stmt.setString(1, payDuration);
                    stmt.setString(2, TotalPrice);
                    stmt.setString(3, Discount);
                    stmt.setString(4, Advanced);
                    stmt.setString(5, CustomerID);
                    stmt.setString(6, methods);

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

        
        //////////////////////////////////////////////////EDITING part/////////////////////////////////////////////////////////////////////
        
        JButton btnNewButton_1_1 = new JButton("Edit");
        btnNewButton_1_1.setBackground(new Color(255, 255, 0));
        btnNewButton_1_1.setBounds(1000, 513, 85, 21);
        frame.getContentPane().add(btnNewButton_1_1);
        
    
        
        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String CustomerID = textField_4.getText();
                String payDuration = textField_1.getText();

                // Check if the values are not empty
                if (CustomerID.isEmpty() || payDuration.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter CustomerID and Pay Duration.");
                    return;
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

                    // Ensure that the table structure matches the columns in your SQL query
                    String sql = "UPDATE paid SET payDuration = ? WHERE CustomerID = ?";

                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, payDuration);
                    stmt.setString(2, CustomerID);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Update Successful!");
                        setVisible(false);
                        advanced_Payment loginFrame = new advanced_Payment();
                        loginFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records updated.");
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

        
        //////////////////////////////////////////////////DELETING part /////////////////////////////////////////////////////////////
        
        JButton btnNewButton_1_2 = new JButton("Delete");
        btnNewButton_1_2.setBackground(new Color(255, 255, 0));
        btnNewButton_1_2.setBounds(1150, 471, 85, 21);
        frame.getContentPane().add(btnNewButton_1_2);
        
    
        btnNewButton_1_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String CustomerID = textField_4.getText();
                String TotalPrice = textField.getText();

                if (CustomerID.isEmpty() || TotalPrice.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter customer ID and Total Price.");
                    return; // Exit the action if any field is empty
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

                    String deleteQuery = "DELETE FROM paid WHERE CustomerID = ? AND TotalPrice = ?";
                    PreparedStatement stmt = con.prepareStatement(deleteQuery);
                    stmt.setString(1, CustomerID);
                    stmt.setString(2, TotalPrice);

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

        
       
        //////////////////////////////////////////////get PDF//////////////////////////////////////////////////////////
        
        btnNewButton_5_1 .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePDF();
            }
        
            private void generatePDF() {
                try {
                    String CustomerID = textField_7.getText();

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

                    String query = "SELECT * FROM paid WHERE CustomerID = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, CustomerID);
                    ResultSet resultSet = stmt.executeQuery();

                    if (resultSet.next()) {
                    	 String payDuration = resultSet.getString("payDuration");
                         String totalPrice = resultSet.getString("TotalPrice");
                         String discount = resultSet.getString("Discount");
                         String advance = resultSet.getString("Advanced");
                         String methods = resultSet.getString("methods");

                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream("PaySlip_" + CustomerID + ".pdf"));
                        document.open();
                        document.add(new Paragraph("     ADVANCE  PAYMENT DETAILS \n  "));
                        document.add(new Paragraph("Customer ID: " + CustomerID));
                        document.add(new Paragraph("payDuration: " + payDuration));
                        document.add(new Paragraph("discount: " + discount+" Rs"));
                        document.add(new Paragraph("advance: " + advance+" Rs"));
                        document.add(new Paragraph("methods: " + methods+ "\n"));
                        document.add(new Paragraph("     Advance payment  was paid \n \n "));
                        
                        
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
        
        
      /////////////////////////////////////////////////calaculate discount/////////////////////////////////////////////////////////////
        
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate1(DocumentEvent e) {
                calculateDiscount();
            }

            public void removeUpdate1(DocumentEvent e) {
                calculateDiscount();
            }

            public void changedUpdate1(DocumentEvent e) {
                calculateDiscount();
            }

            private void calculateDiscount() {
                String totalPriceText = textField.getText();
                if (!totalPriceText.isEmpty()) {
                    try {
                        double totalPrice = Double.parseDouble(totalPriceText);
                        double discount = totalPrice * 0.05; // Assuming 5% discount
                        textField_6.setText(String.valueOf(discount)); // Update the discount text field
                    } catch (NumberFormatException ex) {
                        textField_6.setText(""); // Clear the discount text field if totalPriceText is not a valid number
                    }
                } else {
                    textField_6.setText(""); // Clear the discount text field if totalPriceText is empty
                }
            }

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        
        //////////////////////////////////////////////////////////////////////// calculate advance//////////////////////////////////////////////////////////
        
        textField.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                calculateDiscountAndAdvance();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateDiscountAndAdvance();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateDiscountAndAdvance();
            }

            private void calculateDiscountAndAdvance() {
                String totalPriceText = textField.getText();
                if (!totalPriceText.isEmpty()) {
                    try {
                        double totalPrice = Double.parseDouble(totalPriceText);
                        double discount = totalPrice * 0.05; // Assuming 5% discount
                        double advance = (totalPrice - discount )*0.5; // Calculating advance
                        textField_6.setText(String.valueOf(discount)); // Update the discount text field
                        textField_2.setText(String.valueOf(advance)); // Update the advance text field
                    } catch (NumberFormatException ex) {
                        textField_6.setText(""); // Clear the discount text field if totalPriceText is not a valid number
                        textField_2.setText(""); // Clear the advance text field if totalPriceText is not a valid number
                    }
                } else {
                    textField_6.setText(""); // Clear the discount text field if totalPriceText is empty
                    textField_2.setText(""); // Clear the advance text field if totalPriceText is empty
                }
            }
        });
        
        ///////////////////////////////////////////////////////////go to the payment page ///////////////////////////////////////////
        
     // Inside the initialize() method of advanced_Payment class
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the payment class
                payment paymentPage = new payment();
                // Show the payment page
                paymentPage.frame_1.setVisible(true);
                // Hide the current frame
                frame.setVisible(false);
            }
        });
        
    

        ///////////////////////////////////////////// search customer ID's details//////////////////////////////////////////////////////////
        
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
                    String query = "SELECT * FROM paid WHERE CustomerID = ?";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, customerID);
                    ResultSet resultSet = stmt.executeQuery();

                    DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
                    tableModel.setRowCount(0); // Clear existing table data

                    while (resultSet.next()) {
                        String payDuration = resultSet.getString("payDuration");
                        String totalPrice = resultSet.getString("TotalPrice");
                        String discount = resultSet.getString("Discount");
                        String advance = resultSet.getString("Advanced");
                        String methods = resultSet.getString("methods");

                        // Add the retrieved data to the table model
                        tableModel.addRow(new Object[]{customerID, payDuration, totalPrice, discount, advance, methods});
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching customer details: " + ex.getMessage());
                }
            }
        });

       
       


        
    }

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}
}