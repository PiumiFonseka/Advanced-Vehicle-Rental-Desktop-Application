//Admin form
package Hirushima;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import org.jdesktop.swingx.JXDatePicker;

import Hiruni.Dashboard;
import piumi.driver;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class adminForm extends JFrame {

	private JFrame frame;
	private JTextField textField_ID;
	private JTextField textField_NIC;
	private JTextField textField_no;
	private JTextField textField_email;
	private JTextField textField_name;
	private JTextField textField_user;
	private JTable table;
	private JTextField textField_street;
	private JTextField textField_city;
	private List<adminDetails> adminDetails = new ArrayList<>();
	private JPasswordField pwd;
	private JPasswordField confirm_pwd;
	private DefaultTableModel tableModel;

	// date picker
	private JXDatePicker datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminForm window = new adminForm();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminForm() {
		initialize();
		populateTable();
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

		JLabel lblAddNewEmployee = new JLabel("ADD NEW ADMIN");
		lblAddNewEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewEmployee.setFont(new Font("Times New Roman", Font.BOLD, 55));
		lblAddNewEmployee.setBounds(317, 58, 654, 65);
		frame.getContentPane().add(lblAddNewEmployee);

		JLabel back = new JLabel("Back");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.main(null);
				frame.dispose();
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(23, 22, 58, 19);
		frame.getContentPane().add(back);

		JLabel e_id = new JLabel("Admin ID:");
		e_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id.setBounds(45, 182, 164, 32);
		frame.getContentPane().add(e_id);

		JLabel nic = new JLabel("NIC:");
		nic.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nic.setBounds(45, 225, 88, 32);
		frame.getContentPane().add(nic);

		JLabel cNo = new JLabel("Contact no:");
		cNo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cNo.setBounds(45, 262, 143, 32);
		frame.getContentPane().add(cNo);

		JLabel e_address = new JLabel("Email address:");
		e_address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_address.setBounds(45, 305, 171, 32);
		frame.getContentPane().add(e_address);

		JLabel e_name = new JLabel("Name:");
		e_name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_name.setBounds(505, 182, 82, 32);
		frame.getContentPane().add(e_name);

		JLabel dob = new JLabel("Username:");
		dob.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dob.setBounds(505, 225, 127, 32);
		frame.getContentPane().add(dob);

		JLabel address = new JLabel("Address:");
		address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		address.setBounds(981, 225, 96, 32);
		frame.getContentPane().add(address);

		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(237, 182, 196, 32);
		frame.getContentPane().add(textField_ID);

		textField_NIC = new JTextField();
		textField_NIC.setColumns(10);
		textField_NIC.setBounds(237, 225, 196, 32);
		frame.getContentPane().add(textField_NIC);

		textField_no = new JTextField();
		textField_no.setColumns(10);
		textField_no.setBounds(237, 268, 196, 32);
		frame.getContentPane().add(textField_no);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(236, 311, 196, 32);
		frame.getContentPane().add(textField_email);

		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(652, 182, 196, 32);
		frame.getContentPane().add(textField_name);

		textField_user = new JTextField();
		textField_user.setColumns(10);
		textField_user.setBounds(652, 225, 196, 32);
		frame.getContentPane().add(textField_user);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 399, 995, 229);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Admin ID", "Name", "DOB", "Conatct No",
				"Street", "City", "NIC", "Email", "Username"}));

		// Set default ID value in the text field
		int nextID = getNextID();
		textField_ID.setText(Integer.toString(nextID));

		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = textField_email.getText();
		        String password = new String(pwd.getPassword()).trim(); // Trim the password
		        String confirmPassword = new String(confirm_pwd.getPassword()).trim(); // Trim the confirm password

		        if (isEmailValid(email) && isPasswordValid(password) && password.equals(confirmPassword)&& isNICValid(textField_NIC.getText())&& isPhoneValid(textField_no.getText())) {
		            addAdmin();  // Call method to add admin details to database
		        } else {
		            if (!isEmailValid(email)) {
		                JOptionPane.showMessageDialog(frame, "Invalid email address.");
		            } else if (!isPasswordValid(password)) {
		                JOptionPane.showMessageDialog(frame, "Invalid password. Password must contain at least 8 characters, including both uppercase and lowercase letters.");
		            } else if (!password.equals(confirmPassword)) {
		                JOptionPane.showMessageDialog(frame, "Password and Confirm Password do not match.");
		            }else if (!isNICValid(textField_NIC.getText())) {
						JOptionPane.showMessageDialog(frame, "Invalid NIC number.");
					}else if (!isPhoneValid(textField_no.getText())) {
						JOptionPane.showMessageDialog(frame,"Invalid phone number.");
					}
		        }
		    }
		});


		Add.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Add.setBackground(new Color(243, 219, 50));
		Add.setBounds(1074, 404, 134, 33);
		frame.getContentPane().add(Add);

		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Check if a row is selected
					int adminID = (int) table.getValueAt(selectedRow, 0); // Assuming the admin ID is in the first
																			// column
					deleteAdmin(adminID);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
				}
			}
		});
		Delete.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Delete.setBackground(new Color(243, 219, 50));
		Delete.setBounds(1074, 459, 134, 33);
		frame.getContentPane().add(Delete);

		JButton Edit = new JButton("Edit");
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textField_email.getText();
		        String password = new String(pwd.getPassword()).trim(); // Trim the password
		        String confirmPassword = new String(confirm_pwd.getPassword()).trim(); // Trim the confirm password

		        if (isEmailValid(email) && isPasswordValid(password) && password.equals(confirmPassword)&& isNICValid(textField_NIC.getText())&& isPhoneValid(textField_no.getText())) {
		            editAdmin(); 
		        } else {
		            if (!isEmailValid(email)) {
		                JOptionPane.showMessageDialog(frame, "Invalid email address.");
		            } else if (!isPasswordValid(password)) {
		                JOptionPane.showMessageDialog(frame, "Invalid password. Password must contain at least 8 characters, including both uppercase and lowercase letters.");
		            } else if (!password.equals(confirmPassword)) {
		                JOptionPane.showMessageDialog(frame, "Password and Confirm Password do not match.");
		            }else if (!isNICValid(textField_NIC.getText())) {
						JOptionPane.showMessageDialog(frame, "Invalid NIC number.");
					}else if (!isPhoneValid(textField_no.getText())) {
						JOptionPane.showMessageDialog(frame,"Invalid phone number.");
					}}
			}
		});
		Edit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Edit.setBackground(new Color(243, 219, 50));
		Edit.setBounds(1074, 513, 134, 33);
		frame.getContentPane().add(Edit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
			});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnClear.setBackground(new Color(243, 219, 50));
		btnClear.setBounds(1074, 567, 134, 33);
		frame.getContentPane().add(btnClear);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(243, 219, 50));
		separator.setBounds(27, 369, 1153, 13);
		frame.getContentPane().add(separator);

		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblStreet.setBounds(945, 268, 88, 32);
		frame.getContentPane().add(lblStreet);

		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCity.setBounds(945, 311, 73, 32);
		frame.getContentPane().add(lblCity);

		JLabel e_name_1 = new JLabel("Confirm pwd:");
		e_name_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_name_1.setBounds(505, 311, 164, 32);
		frame.getContentPane().add(e_name_1);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(505, 268, 122, 32);
		frame.getContentPane().add(lblPassword);

		textField_street = new JTextField();
		textField_street.setColumns(10);
		textField_street.setBounds(1030, 268, 196, 32);
		frame.getContentPane().add(textField_street);

		textField_city = new JTextField();
		textField_city.setColumns(10);
		textField_city.setBounds(1028, 311, 196, 32);
		frame.getContentPane().add(textField_city);

		JLabel dob_1 = new JLabel("DOB:");
		dob_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dob_1.setBounds(945, 182, 80, 32);
		frame.getContentPane().add(dob_1);

		datePicker = new JXDatePicker();
		datePicker.setBounds(1032, 182, 194, 32);
		frame.getContentPane().add(datePicker);

		pwd = new JPasswordField();
		pwd.setBounds(652, 268, 196, 32);
		frame.getContentPane().add(pwd);

		confirm_pwd = new JPasswordField();
		confirm_pwd.setBounds(652, 311, 196, 32);
		frame.getContentPane().add(confirm_pwd);

		JButton dash = new JButton("Dashboard");
		dash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {});
			}
		});

		dash.setBackground(new Color(243, 219, 50));
		dash.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dash.setBounds(1074, 18, 152, 32);
		frame.getContentPane().add(dash);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					textField_ID.setText(tableModel.getValueAt(selectedRow, 0).toString());
					textField_name.setText(tableModel.getValueAt(selectedRow, 1).toString());
					String dobString = tableModel.getValueAt(selectedRow, 2).toString();
					Date dob = parseDate(dobString);
					datePicker.setDate(dob);
					textField_no.setText(tableModel.getValueAt(selectedRow, 3).toString());
					textField_street.setText(tableModel.getValueAt(selectedRow, 4).toString());
					textField_city.setText(tableModel.getValueAt(selectedRow, 5).toString());
					textField_NIC.setText(tableModel.getValueAt(selectedRow, 6).toString());
					textField_email.setText(tableModel.getValueAt(selectedRow, 7).toString());
					textField_user.setText(tableModel.getValueAt(selectedRow, 8).toString());
					
					int adminID = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
		            String password = getPasswordFromDatabase(adminID);
		            pwd.setText(password);
		            confirm_pwd.setText(password);
	        }
			}
	    });
		}

	private Date parseDate(String dateString) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void populateTable() {
		tableModel = (DefaultTableModel) table.getModel();// Get the table model
		tableModel.setRowCount(0); // Clear existing data

		// Establish a database connection
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Create a SQL statement and query the database
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM admin";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("a_id");
					String name = resultSet.getString("a_name");
					String dob = resultSet.getString("a_dob");
					int phone = resultSet.getInt("a_contactNo");
					String street = resultSet.getString("a_street");
					String city = resultSet.getString("a_city");
					String nic = resultSet.getString("a_NIC");
					String email = resultSet.getString("a_email");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					// String con_pwd = resultSet.getString("con_pwd");

					adminDetails admin = new adminDetails(id, name, dob, phone, street, city, nic, email, username,password);
					adminDetails.add(admin);

					// Create a vector to add data to the table model
					Vector<Object> row = new Vector<>();
					row.add(admin.getId());
					row.add(admin.getName());
					row.add(admin.getDOB());
					row.add(admin.getPhone());
					row.add(admin.getStreet());
					row.add(admin.getCity());
					row.add(admin.getNIC());
					row.add(admin.getEmail());
					row.add(admin.getUsername());
					//row.add(admin.getPassword());

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

	private void clearFields() {
		// clear text fields

		textField_ID.setText("");
		textField_NIC.setText("");
		textField_no.setText("");
		textField_email.setText("");
		textField_name.setText("");
		textField_user.setText("");
		pwd.setText("");
		confirm_pwd.setText("");
		textField_street.setText("");
		textField_city.setText("");
		datePicker.setDate(null);
	}

	private void addAdmin() {
		try {
			// Get data from input fields
			String name = textField_name.getText();
			String dob = formatDate(datePicker.getDate());
			int phone = Integer.parseInt(textField_no.getText());
			String street = textField_street.getText();
			String city = textField_city.getText();
			String NIC = textField_NIC.getText();
			String email = textField_email.getText();
			String username = textField_user.getText();
			String password = pwd.getText();
			String con_pwd = confirm_pwd.getText();

			Date selectedDate = datePicker.getDate();
			String a_dob;
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				a_dob = dateFormat.format(selectedDate);
			} else {
				JOptionPane.showMessageDialog(frame, "Please select date of birth.");
				return; // or handle the situation accordingly
			}

			// Create an adminDetails object with the entered details
			adminDetails admin = new adminDetails(0, name, dob, phone, street, city, NIC, email, username, password);
			adminDetails.add(admin);

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					// Insert the admin data into the database
					String query = "INSERT INTO admin (a_name,a_dob,a_contactNo,a_street,a_city,a_NIC,a_email,username,password) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);

					preparedStatement.setString(1, admin.getName());
					preparedStatement.setString(2, admin.getDOB());
					preparedStatement.setInt(3, admin.getPhone());
					preparedStatement.setString(4, admin.getStreet());
					preparedStatement.setString(5, admin.getCity());
					preparedStatement.setString(6, admin.getNIC());
					preparedStatement.setString(7, admin.getEmail());
					preparedStatement.setString(8, admin.getUsername());
					preparedStatement.setString(9, admin.getPassword());

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
						if (generatedKeys.next()) {
							int generatedRId = generatedKeys.getInt(1); // Get the generated id

							// Update the ID field with the generated ID
							textField_ID.setText(Integer.toString(generatedRId));
							JOptionPane.showMessageDialog(frame, "Admin added successfully!");
							clearFields();
							populateTable();
						} else {
							JOptionPane.showMessageDialog(frame, "Failed to add admin.");
						}

						preparedStatement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} catch (NumberFormatException e) {
			// Handle number format exceptions (e.g., when parsing ID or phone)
			e.printStackTrace();
		}

	}

//method to change date format 
	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	private boolean isPasswordValid(String password) {
	    // Regular expression to check for at least one lowercase, at least one uppercase letter, and at least 8 characters long
		String passRegex = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
	    Pattern pattern = Pattern.compile(passRegex);
	    Matcher matcher = pattern.matcher(password);
	    return matcher.matches();
	}


	private boolean isEmailValid(String email) {
	    // Regular expression for validating email
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	// Method to validate NIC format
				private boolean isNICValid(String nic) {
				    // NIC format: Either 9 digits followed by 'V' or 'X' OR 12 digits
				    return nic.matches("\\d{9}[VX]|\\d{12}");
				}
				
				private boolean isPhoneValid(String phone) {
				    // Check if the phone number contains exactly 10 digits
				    return phone.matches("\\d{10}");
				}


	private int getNextID() {
		int nextID = 1;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(a_id) FROM admin";
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

	private void deleteAdmin(int adminID) {
		int confirmDelete = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this admin?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION);

		if (confirmDelete == JOptionPane.YES_OPTION) {
			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "DELETE FROM admin WHERE a_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, adminID);
					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Admin deleted successfully!");
						populateTable(); // Update the table after deletion
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete admin.");
					}
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		}
	}

	private void editAdmin() {
	    int selectedRowIndex = table.getSelectedRow();

	    if (selectedRowIndex >= 0) {

	        int id = (int) tableModel.getValueAt(selectedRowIndex, 0);
	        String name = textField_name.getText();
	        String dob = formatDate(datePicker.getDate());
	        int phone = Integer.parseInt(textField_no.getText());
	        String street = textField_street.getText();
	        String city = textField_city.getText();
	        String NIC = textField_NIC.getText();
	        String email = textField_email.getText();
	        String username = textField_user.getText();
	        // String password = pwd.getText(); // Remove this line

	        // Retrieve password from the database
	        String password = getPasswordFromDatabase(id);

	        // Update driver object in the list
	        for (adminDetails ad : adminDetails) {
	            if (ad.getId() == id) {
	                ad.setName(name);
	                ad.setDOB(dob);
	                ad.setPhone(phone);
	                ad.setStreet(street);
	                ad.setCity(city);
	                ad.setNIC(NIC);
	                ad.setEmail(email);
	                ad.setUsername(username);
	                ad.setPassword(password);
	                break;
	            }
	        }

	        Connection connection = DatabaseConnection.getConnection();
	        if (connection != null) {
	            try {

	                String query = "UPDATE admin SET a_name=?, a_dob=?, a_contactNo=?, a_street=?, a_city=?, "
	                        + "a_NIC=?, a_email=?, username=?, password=? " + "WHERE a_id=?";
	                PreparedStatement preparedStatement = connection.prepareStatement(query);
	                preparedStatement.setString(1, name);
	                preparedStatement.setString(2, dob);
	                preparedStatement.setInt(3, phone);
	                preparedStatement.setString(4, street);
	                preparedStatement.setString(5, city);
	                preparedStatement.setString(6, NIC);
	                preparedStatement.setString(7, email);
	                preparedStatement.setString(8, username);
	                preparedStatement.setString(9, password);
	                preparedStatement.setInt(10, id);

	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(frame, "Admin updated successfully!");
	                    clearFields();
	                    populateTable();
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Unable to update admin.");
	                }

	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                DatabaseConnection.closeConnection(connection);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(frame, "Please select an admin to edit.");
	    }
	}

	private String getPasswordFromDatabase(int id) {
	    String password = "";
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            String query = "SELECT password FROM admin WHERE a_id=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                password = resultSet.getString("password");
	            }
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseConnection.closeConnection(connection);
	        }
	    }
	    return password;
	}

}