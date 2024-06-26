//Employee form
package Hirushima;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import org.jdesktop.swingx.JXDatePicker;

import Hiruni.Dashboard;
import Hiruni.DatabaseConnection;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;

public class employeeForm {

	JFrame frame;
	private JTextField eid;
	private JTextField eNIC;
	private JTextField phone;
	private JTextField eAdd;
	private JTextField ename;
	private JTextField add;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> e_posi;
	
	// date picker
		private JXDatePicker bday;
	
	List<employee> employeeList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeForm window = new employeeForm();
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
	public employeeForm() {
		initialize();
		displayTable();
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
		
		JLabel lblAddNewEmployee = new JLabel("ADD NEW EMPLOYEE");
		lblAddNewEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewEmployee.setFont(new Font("Times New Roman", Font.BOLD, 55));
		lblAddNewEmployee.setBounds(317, 58, 654, 65);
		frame.getContentPane().add(lblAddNewEmployee);
		
		JLabel back = new JLabel("Back");
		back.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        frame.dispose(); // Close the current frame
		        Dashboard.main(new String[] {});
		    }
		});

		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(23, 22, 58, 19);
		frame.getContentPane().add(back);
		
		JLabel e_id = new JLabel("Employee ID:");
		e_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id.setBounds(144, 182, 164, 32);
		frame.getContentPane().add(e_id);
		
		JLabel nic = new JLabel("NIC:");
		nic.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nic.setBounds(144, 225, 88, 32);
		frame.getContentPane().add(nic);
		
		JLabel cNo = new JLabel("Contact no:");
		cNo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cNo.setBounds(144, 268, 143, 32);
		frame.getContentPane().add(cNo);
		
		JLabel e_address = new JLabel("Email address:");
		e_address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_address.setBounds(144, 311, 171, 32);
		frame.getContentPane().add(e_address);
		
		JLabel e_name = new JLabel("Name:");
		e_name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_name.setBounds(660, 182, 82, 32);
		frame.getContentPane().add(e_name);
		
		JLabel dob = new JLabel("DOB:");
		dob.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dob.setBounds(660, 225, 73, 32);
		frame.getContentPane().add(dob);
		
		JLabel address = new JLabel("Address:");
		address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		address.setBounds(660, 268, 106, 32);
		frame.getContentPane().add(address);
		
		JLabel posi = new JLabel("Position:");
		posi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		posi.setBounds(660, 311, 106, 32);
		frame.getContentPane().add(posi);
		
		eid = new JTextField();
		eid.setColumns(10);
		eid.setBounds(325, 182, 196, 32);
		frame.getContentPane().add(eid);
		int nextEmployeeID = getNextE_ID();
		eid.setText(Integer.toString(nextEmployeeID));
		
		eNIC = new JTextField();
		eNIC.setColumns(10);
		eNIC.setBounds(325, 225, 196, 32);
		frame.getContentPane().add(eNIC);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(325, 268, 196, 32);
		frame.getContentPane().add(phone);
		
		eAdd = new JTextField();
		eAdd.setColumns(10);
		eAdd.setBounds(325, 311, 196, 32);
		frame.getContentPane().add(eAdd);
		
		ename = new JTextField();
		ename.setColumns(10);
		ename.setBounds(795, 182, 196, 32);
		frame.getContentPane().add(ename);
		
		add = new JTextField();
		add.setColumns(10);
		add.setBounds(795, 268, 196, 32);
		frame.getContentPane().add(add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 399, 818, 212);
		frame.getContentPane().add(scrollPane);
		
		bday = new JXDatePicker();
		bday.setBounds(795, 225, 196, 32);
		frame.getContentPane().add(bday);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Name", "NIC", "DOB", "Contact no", "Address", "Email address", "Position"
			}
		));
		
		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmailValid(eAdd.getText()) && isPhoneValid(phone.getText())&& isNICValid(eNIC.getText())){
					addEmployee();
				} else if (!isPhoneValid(phone.getText())) {
					JOptionPane.showMessageDialog(frame,
							"Invalid phone number.");
				} else if (!isEmailValid(eAdd.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid email address.");
				}else if (!isNICValid(eNIC.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid NIC number.");
				}
			}
		});
			
		Add.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Add.setBackground(new Color(243, 219, 50));
		Add.setBounds(993, 393, 134, 33);
		frame.getContentPane().add(Add);
		
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) { // Check if a row is selected
		            int employeeID = (int) table.getValueAt(selectedRow, 0); // Assuming the employee ID is in the first column
		            deleteEmployee(employeeID);
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
		        }
		    }
		});
		Delete.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Delete.setBackground(new Color(243, 219, 50));
		Delete.setBounds(993, 450, 134, 33);
		frame.getContentPane().add(Delete);
		
		JButton Edit = new JButton("Edit");
		Edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editEmployee();
            }
        });
		Edit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Edit.setBackground(new Color(243, 219, 50));
		Edit.setBounds(993, 506, 134, 33);
		frame.getContentPane().add(Edit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnClear.setBackground(new Color(243, 219, 50));
		btnClear.setBounds(993, 561, 134, 33);
		frame.getContentPane().add(btnClear);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(243, 219, 50));
		separator.setBounds(23, 368, 1195, 12);
		frame.getContentPane().add(separator);
		
		JButton View = new JButton("View details");
		View.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) { // If a row is selected
		            int selectedEmployeeID = (int) tableModel.getValueAt(selectedRow, 0); // Assuming employee ID is in the first column
		            employeeProfile profile = new employeeProfile();
		            profile.setVisible(true);
		            profile.fetchEmployeeDetails(selectedEmployeeID);
		            frame.dispose(); // Close the current frame
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to view.");
		        }
		    }
		});
		View.setFont(new Font("Times New Roman", Font.BOLD, 25));
		View.setBackground(new Color(243, 219, 50));
		View.setBounds(931, 616, 196, 33);
		frame.getContentPane().add(View);
		
		e_posi = new JComboBox<String>();
		e_posi.setBounds(795, 311, 196, 32);
		e_posi.addItem("Manager");
        e_posi.addItem("Customer Service Representative");
        e_posi.addItem("Fleet Manager");
        e_posi.addItem("Cleaning Staff");
        e_posi.addItem("Accountant");
        e_posi.addItem("Human Resources Manager");
        frame.getContentPane().add(e_posi);
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            eid.setText(tableModel.getValueAt(selectedRow, 0).toString());
		            ename.setText(tableModel.getValueAt(selectedRow, 1).toString());
		            eNIC.setText(tableModel.getValueAt(selectedRow, 2).toString());
		            String dobString = tableModel.getValueAt(selectedRow, 3).toString(); 
	                Date dob = parseDate(dobString);
	                bday.setDate(dob);
		            phone.setText(tableModel.getValueAt(selectedRow, 4).toString());
		            add.setText(tableModel.getValueAt(selectedRow, 5).toString());
		            eAdd.setText(tableModel.getValueAt(selectedRow, 6).toString());
		            e_posi.setSelectedItem(tableModel.getValueAt(selectedRow, 7)); 
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

	private void addEmployee() {
	    // Get data from text fields and combo box
	    String e_name = ename.getText();
	    String e_nic = eNIC.getText();
	    String e_dob = formatDate(bday.getDate());
	    int e_phone = Integer.parseInt(phone.getText());
	    String e_address = add.getText();
	    String e_email = eAdd.getText();
	    String e_position = (String) e_posi.getSelectedItem();

	    // Create an employee object with the entered data
	    employee emp = new employee(0, e_name, e_nic, e_dob, e_phone, e_address, e_email, e_position);

	    // Establish a database connection
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            // SQL query to insert a new employee into the database
	            String query = "INSERT INTO employees (e_name, e_nic, e_dob, e_phone, e_address, e_email, e_position) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

	            // Set values in the prepared statement
	            preparedStatement.setString(1, e_name);
	            preparedStatement.setString(2, e_nic);
	            preparedStatement.setString(3, e_dob);
	            preparedStatement.setInt(4, e_phone);
	            preparedStatement.setString(5, e_address);
	            preparedStatement.setString(6, e_email);
	            preparedStatement.setString(7, e_position);

	            // Execute the query
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                // Get the generated employee ID
	                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    int generatedEId = generatedKeys.getInt(1); // Get the generated e_id
	                    eid.setText(Integer.toString(generatedEId));
	                    JOptionPane.showMessageDialog(frame, "Employee added successfully!");
	                    displayTable();
	                }
	            } else {
	                JOptionPane.showMessageDialog(frame, "Failed to add Employee.");
	            }

	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseConnection.closeConnection(connection);
	        }
	    }
	}


			// Method to populate the JTable with reservation details
			void displayTable() {
				tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);

				Connection connection = DatabaseConnection.getConnection();
				if (connection != null) {
					try {
						Statement statement = connection.createStatement();
						String query = "SELECT * FROM employees";
						ResultSet resultSet = statement.executeQuery(query);

						while (resultSet.next()) {
							int e_id = resultSet.getInt("e_id");
							String e_name = resultSet.getString("e_name");
							String e_nic = resultSet.getString("e_nic");
							String e_dob = resultSet.getString("e_dob");
							int e_phone = resultSet.getInt("e_phone");
							String e_address = resultSet.getString("e_address");
							String e_email = resultSet.getString("e_email");
							String e_position = resultSet.getString("e_position");

							// Create an employee object
							employee emp = new employee(e_id,e_name, e_nic, e_dob, e_phone, e_address, e_email, e_position);
							employeeList.add(emp);

							// Create a vector containing reservation details
							Vector<Object> row = new Vector<>();
							row.add(emp.getEid());
							row.add(emp.getName());
							row.add(emp.getNIC());
							row.add(emp.getDOB());
							row.add(emp.getPhone());
							row.add(emp.getAddress());
							row.add(emp.getEmail());
							row.add(emp.getPosition());

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

				eid.setText("");
				eNIC.setText("");
				phone.setText("");
				eAdd.setText("");
				ename.setText("");
				add.setText("");
				e_posi.setSelectedIndex(-1);
				bday.setDate(null);
			}
			
			
			private void deleteEmployee(int employeeID) {
				int confirmDelete = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this employee?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				
				if (confirmDelete == JOptionPane.YES_OPTION) {
			    Connection connection = DatabaseConnection.getConnection();
			    if (connection != null) {
			        try {
			            String query = "DELETE FROM employees WHERE e_id = ?";
			            PreparedStatement preparedStatement = connection.prepareStatement(query);
			            preparedStatement.setInt(1, employeeID);
			            int rowsAffected = preparedStatement.executeUpdate();
			            if (rowsAffected > 0) {
			                JOptionPane.showMessageDialog(frame, "Employee deleted successfully!");
			                displayTable(); // Update the table after deletion
			            } else {
			                JOptionPane.showMessageDialog(frame, "Failed to delete employee.");
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
			
			// method to change date format 
			private String formatDate(Date date) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				return sdf.format(date);
			}
			
			private boolean isEmailValid(String email) {
				// Define a regular expression pattern for a valid email address
				String mail = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(mail);

				// Check if the provided email matches the pattern
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
			}
			
			private boolean isPhoneValid(String phone) {
			    // Check if the phone number contains exactly 10 digits
			    return phone.matches("\\d{10}");
			}
			
			// Method to validate NIC format
			private boolean isNICValid(String nic) {
			    // NIC format: Either 9 digits followed by 'V' or 'X' OR 12 digits
			    return nic.matches("\\d{9}[VX]|\\d{12}");
			}


			
	
	            // Get the next available employee ID by finding the maximum existing ID and
				// incrementing it
				private int getNextE_ID() {
					int nextID = 1; // Default value if no employees exist
					Connection connection = DatabaseConnection.getConnection();
					if (connection != null) {
						try {
							String query = "SELECT MAX(e_id) FROM employees";
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
				
				private void editEmployee() {
				    // Get the selected row from the table
				    int selectedRow = table.getSelectedRow();

				    // Check if a row is selected
				    if (selectedRow >= 0) {
				        int confirmEdit = JOptionPane.showConfirmDialog(frame, "Are you sure you want to edit this employee?", "Confirm Edit", JOptionPane.YES_NO_OPTION);

				        if (confirmEdit == JOptionPane.YES_OPTION) {
				            // Get the employee ID from the selected row
				            int e_id = (int) tableModel.getValueAt(selectedRow, 0);

				            // Get the data from the text fields
				            String e_name = ename.getText();
				            String e_nic = eNIC.getText();
				            String e_dob = formatDate(bday.getDate()); // Use a method to format the date if needed
				            int e_phone = Integer.parseInt(phone.getText());
				            String e_address = add.getText();
				            String e_email = eAdd.getText();
				            String e_position = (String) e_posi.getSelectedItem();

				            // Update the employee data in the database
				            Connection connection = DatabaseConnection.getConnection();
				            if (connection != null) {
				                try {
				                    // Update the employee data in the database
				                    String query = "UPDATE employees SET e_name = ?, e_nic = ?, e_dob = ?, e_phone = ?, e_address = ?, e_email = ?, e_position = ? WHERE e_id = ?";
				                    PreparedStatement preparedStatement = connection.prepareStatement(query);
				                    preparedStatement.setString(1, e_name);
				                    preparedStatement.setString(2, e_nic);
				                    preparedStatement.setString(3, e_dob);
				                    preparedStatement.setInt(4, e_phone);
				                    preparedStatement.setString(5, e_address);
				                    preparedStatement.setString(6, e_email);
				                    preparedStatement.setString(7, e_position);
				                    preparedStatement.setInt(8, e_id);

				                    int rowsAffected = preparedStatement.executeUpdate();
				                    if (rowsAffected > 0) {
				                        JOptionPane.showMessageDialog(frame, "Employee details updated successfully!");
				                        clearFields();
				                        displayTable();
				                    } else {
				                        JOptionPane.showMessageDialog(frame, "Failed to update employee details.");
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
				        JOptionPane.showMessageDialog(frame, "Please select an employee to update.");
				    }
				}



				public void setVisible(boolean b) {
					frame.setVisible(b);
				}
}
