package bhagya;

import java.awt.EventQueue;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Hiruni.Dashboard;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


import javax.swing.JFrame;

public class supinformation {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table_2;
	
	public JFrame frame_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supinformation window = new supinformation();
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
	public supinformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setBounds(100, 100, 1280,720 );
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the current frame
		        Dashboard.main(new String[] {});

			}
		});
		btnNewButton.setBounds(10, 10, 72, 21);
		
		JLabel lblNewLabel = new JLabel(" ADD NEW SUPPLIER");
		lblNewLabel.setBounds(369, 67, 639, 99);
		lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 48));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(192, 190, 104, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
		JLabel lblNewLabel_1_4 = new JLabel("Vehicle type:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(680, 300, 118, 21);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("VehiRegNo:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(670, 264, 109, 25);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vehicle name:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(680, 221, 118, 21);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email address:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(680, 190, 128, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contact no:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(192, 300, 104, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NIC:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(192, 264, 104, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_5 = new JLabel("Supplier name:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_5.setBounds(193, 221, 138, 21);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		
		  
    	// supplier id
		textField = new JTextField();
		textField.setBounds(319, 193, 223, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//SupplierName
		textField_1 = new JTextField();
		textField_1.setBounds(319, 224, 223, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//NIC
		
		textField_2 = new JTextField();
		textField_2.setBounds(319, 264, 220, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		//ContactNo
		textField_3 = new JTextField();
		textField_3.setBounds(319, 303, 223, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
        
//email
	textField_4 = new JTextField();
	textField_4.setBounds(818, 193, 245, 19);
	frame.getContentPane().add(textField_4);
	textField_4.setColumns(10);
	
	
	// vehicle name
	textField_5 = new JTextField();
	textField_5.setBounds(818, 224, 245, 19);
	frame.getContentPane().add(textField_5);
	textField_5.setColumns(10);
	
	
	
	
	
	// vehicle reg no
	textField_7 = new JTextField();
	textField_7.setBounds(818, 270, 277, 19);
	frame.getContentPane().add(textField_7);
	textField_7.setColumns(10);
	
	//vehicle type
	JComboBox<String> comboBox2 = new JComboBox<>();
	comboBox2.addItem("CAR");
	comboBox2.addItem("VAN");
	comboBox2.addItem("BIKE");
	comboBox2.addItem("BUS");
	comboBox2.addItem("LORRY");
	comboBox2.addItem("JEEP");
	
	
	// Add more items as needed

	comboBox2.setBounds(816, 303, 277, 19); // Set the position and size as per your requirement
	frame.getContentPane().add(comboBox2); // Add the JComboBox to your frame

	
	// supplier payment
	
	textField_8 = new JTextField();
	textField_8.setBounds(600, 358, 277, 19);
	frame.getContentPane().add(textField_8);
	textField_8.setColumns(10);
	

	JButton btnNewButton_1_3 = new JButton("CLEAR");
	btnNewButton_1_3.setBackground(new Color(255, 255, 0));
	btnNewButton_1_3.setBounds(1107, 551, 85, 21);
	frame.getContentPane().add(btnNewButton_1_3);
	
	///View supplier deatails button 
	JButton btnNewButton_1_4 = new JButton(" View supplier details");
	btnNewButton_1_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	
	btnNewButton_1_4.setBackground(new Color(255, 255, 0));
	btnNewButton_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnNewButton_1_4.setBounds(971, 592, 195, 52);
	frame.getContentPane().add(btnNewButton_1_4);
	
	

	JLabel lblNewLabel_3 = new JLabel("Supplier payment :");
	lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_3.setBounds(434, 350, 244, 38);
	frame.getContentPane().add(lblNewLabel_3);
	

	// scroll pane
	  JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 400, 979, 193);
        frame.getContentPane().add(scrollPane);

        table_2 = new JTable();
        scrollPane.setViewportView(table_2);
        table_2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"SupplierID","SupplierName", "NIC"," ContactNo", "Email"," vehicleName" ,"vehicleType","RegNo","SupplierPay"}
        ));
	
		
		
		
		//////////////////////////////////////////////////deleting part//////////////////////////////////////////////////
	
		
		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1_1.setBounds(1107, 507, 85, 21);
		frame.getContentPane().add(btnNewButton_1_1);
		
		
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String SupplierID = textField.getText();
                String Email = textField_4.getText();

                if (SupplierID.isEmpty() || Email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter SupplierID and Email.");
                    return; // Exit the action if any field is empty
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

                    String deleteQuery = "DELETE FROM suppi WHERE SupplierID = ? AND Email = ?";
                    PreparedStatement stmt = con.prepareStatement(deleteQuery);
                    stmt.setString(1, SupplierID);
                    stmt.setString(2, Email);

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


		
		
		
		////////////////////////////////////////////editing part//////////////////////////////////////////////////////////////////////
		
		
		JButton btnNewButton_1_2 = new JButton("EDIT");
		btnNewButton_1_2.setBackground(new Color(255, 255, 0));
		btnNewButton_1_2.setBounds(985, 551, 85, 21);
		frame.getContentPane().add(btnNewButton_1_2);
		
		 btnNewButton_1_2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                String SupplierID = textField.getText();
	                String Email = textField_4.getText();

	                try {
	                    Class.forName("com.mysql.jdbc.Driver");
	                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

	                    // Ensure that the table structure matches the columns in your SQL query
	                    String sql = "UPDATE suppi SET Email = ? WHERE SupplierID = ?";

	                    PreparedStatement stmt = con.prepareStatement(sql);
	                    stmt.setString(1, Email);
	                    stmt.setString(2, SupplierID);

	                    int rowsAffected = stmt.executeUpdate();
	                    if (rowsAffected > 0) {
	                        JOptionPane.showMessageDialog(null, "Update Successful!");
	                        setVisible(false);
	                        advanced_Payment loginFrame = new advanced_Payment();
	                        loginFrame.setVisible(true);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Please enter SupplierID and Email");
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

		
		

		
		
	////////////////////////////////////////////adding part//////////////////////////////////////////////////////////////////////
	
	//add button
			JButton btnNewButton_1 = new JButton("ADD");
			btnNewButton_1.setForeground(new Color(0, 0, 0));
			btnNewButton_1.setBackground(new Color(255, 255, 0));
			btnNewButton_1.setBounds(985, 507, 85, 21);
			frame.getContentPane().add(btnNewButton_1);
			frame.setBounds(100, 100,1280,720);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	
	
	btnNewButton_1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        String SupplierID = textField.getText();
	        String SupplierName = textField_1.getText();
	        String NIC = textField_2.getText();
	        String ContactNo = textField_3.getText();
	        String Email = textField_4.getText();
	        String vehicleName = textField_5.getText();
	        String vehicleType = comboBox2.getSelectedItem().toString(); // Get the selected item from comboBox2
	        String RegNo = textField_7.getText();
	        String SupplierPay = textField_8.getText();

	        
	        // validation part
	        
	        if (SupplierID.isEmpty() || SupplierName.isEmpty() || NIC.isEmpty() || ContactNo.isEmpty() || Email.isEmpty() || vehicleName.isEmpty() || vehicleType.isEmpty() || RegNo.isEmpty() || SupplierPay.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
	            return; // Exit the action if any field is empty
	        }
	      

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "");

	            // Ensure that the table structure matches the columns in your SQL query
	            String sql = "INSERT INTO suppi (SupplierID , SupplierName , NIC , ContactNo , Email , vehicleName , vehicleType , RegNo , Supplierpay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = con.prepareStatement(sql);

	            stmt.setString(1, SupplierID);
	            stmt.setString(2, SupplierName);
	            stmt.setString(3, NIC);
	            stmt.setString(4, ContactNo);
	            stmt.setString(5, Email);
	            stmt.setString(6, vehicleName);
	            stmt.setString(7, vehicleType);
	            stmt.setString(8, RegNo);
	            stmt.setString(9, SupplierPay);

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Supplier information added successfully!");
	                setVisible(false);
	                advanced_Payment advanced_PaymentFrame = new advanced_Payment();
	                advanced_PaymentFrame.setVisible(true);
	            } else {
	                JOptionPane.showMessageDialog(null, "Supplier information could not be added!");
	            }

	            con.close();
	        } catch (Exception ae) {
	            ae.printStackTrace();
	        }
	    }
	});

	        
		
		
		
		////////////////////////////////////viewing part //////////////////////////////////////////////////////////////////////////////
	
		  JButton searchButton = new JButton("View");
	        searchButton.setBackground(new Color(255, 255, 0));
	        searchButton.setBounds(1012, 440, 185, 21);
	        frame.getContentPane().add(searchButton);
	        
		
		
		
	        searchButton.addActionListener(new ActionListener() {
	            private DefaultTableModel tableModel;

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                searchDatabase();
	            }

	            private void searchDatabase() {
	                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
	                    String query = "SELECT * FROM suppi";
	                    PreparedStatement stmt = connection.prepareStatement(query);
	                    ResultSet resultSet = stmt.executeQuery();

	                    // Clear existing table data
	                    tableModel = (DefaultTableModel) table_2.getModel();
	                    tableModel.setRowCount(0);

	                    while (resultSet.next()) {
	                        String SupplierID = resultSet.getString("SupplierID");
	                        String SupplierName = resultSet.getString("SupplierName");
	                        String NIC = resultSet.getString("NIC");
	                        String ContactNo = resultSet.getString("ContactNo");
	                        String Email = resultSet.getString("Email");
	                        String vehicleName = resultSet.getString("vehicleName");
	                        String vehicleType = resultSet.getString("vehicleType");
	                        String RegNo = resultSet.getString("RegNo");
	                        String SupplierPay = resultSet.getString("SupplierPay");

	                        // Add the retrieved data to the table model
	                        tableModel.addRow(new Object[]{SupplierID, SupplierName, NIC, ContactNo, Email, vehicleName, vehicleType, RegNo, SupplierPay});
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
		
		
		
		
		
		
	      
		
		/////////////////////////////////////// click view supplier deatils and go to the supplier part //////////////////////////////////////////
		
		// Inside the initialize() method of your class where btnNewButton_1_4 is defined
		btnNewButton_1_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Create an instance of the supplier class
		        supplier supplierPage = new supplier();
		        // Show the supplier page
		        supplierPage.frame.setVisible(true);
		        // Hide the current frame (assuming frame is the JFrame of the current class)
		        frame.setVisible(false);
		    }
		});
		
		/////////////////////////////////////////////////VALIADATION PART VEHICLE REGNO/////////////////////////////////////////////////
		
		textField_7.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String regNo = textField_7.getText().trim();
		        String regex = "^[A-Za-z]{0,3}-\\d{4}$"; // ABC-8888 or AB-8888 or 88-8888 or 888-8888

		        if (!regNo.matches(regex)) {
		            JOptionPane.showMessageDialog(null, "Invalid vehicle registration number format. Please enter in the format ABC-8888, AB-8888, 88-8888, or 888-8888.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            // Clear the text field or take appropriate action
		            textField_7.setText("");
		        }
		    }
		});

		
		/////////////////////////////////////////////////////   contact number validation ////////////////////////////////////////////////// 
		textField_3.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String contactNumber = textField_3.getText().trim();
		        if (!contactNumber.matches("\\d{10}")) {
		            JOptionPane.showMessageDialog(null, "Contact number must be exactly ten digits long.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            // Clear the text field or take appropriate action
		            textField_3.setText("");
		        }
		    }
		});
       
/////////////////////////////////////////////////////   contact number validation ////////////////////////////////////////////////// 
textField_2.addFocusListener(new FocusAdapter() {
@Override
public void focusLost(FocusEvent e) {
String contactNumber = textField_2.getText().trim();
if (!contactNumber.matches("\\d{12}")) {
JOptionPane.showMessageDialog(null, "ID number must be exactly 12 digits long.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
// Clear the text field or take appropriate action
textField_2.setText("");
}
}
});
		
		
		
		
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////// contact number validation///////////////////////////////////////////////////
		
		textField_4.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String email = textField_4.getText().trim();
		        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		        if (!email.matches(emailRegex)) {
		            JOptionPane.showMessageDialog(null, "Invalid email address format. Please enter a valid email address.(eg-samidi@gmail.com)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            // Clear the text field or take appropriate action
		            textField_4.setText("");
		        }
		    }
		});

		
		
		
		

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}



		
		
	}
