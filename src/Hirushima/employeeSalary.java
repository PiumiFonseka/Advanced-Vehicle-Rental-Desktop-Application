//Employee salary
package Hirushima;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;

import Hiruni.Cars;
import Hiruni.DatabaseConnection;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class employeeSalary {

	private JFrame frame;
	private JTextField mon;
	private JTextField off;
	private JTextField rpd;
	private JTextField atten;
	private JTextField sal;
	private JTable table;
	
	private DefaultTableModel tableModel;
	private JComboBox<Integer> empID;
	
	List<salary> salaryList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeSalary window = new employeeSalary();
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
	public employeeSalary() {
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
		
		JLabel lblSalaryCalculation = new JLabel("SALARY CALCULATION");
		lblSalaryCalculation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalaryCalculation.setFont(new Font("Times New Roman", Font.BOLD, 55));
		lblSalaryCalculation.setBounds(238, 49, 756, 65);
		frame.getContentPane().add(lblSalaryCalculation);
		
		JLabel back = new JLabel("Back");
		back.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            employeeProfile prof = new employeeProfile();
	            prof.setVisible(true);
	            frame.dispose(); // Close the current frame
	        }
	    });
		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(10, 11, 58, 19);
		frame.getContentPane().add(back);
		
		JLabel e_id = new JLabel("Employee ID:");
		e_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id.setBounds(169, 176, 164, 32);
		frame.getContentPane().add(e_id);
		
		JLabel e_id_1 = new JLabel("Month:");
		e_id_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id_1.setBounds(169, 232, 164, 32);
		frame.getContentPane().add(e_id_1);
		
		JLabel e_id_2 = new JLabel("Offers:");
		e_id_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id_2.setBounds(169, 287, 164, 32);
		frame.getContentPane().add(e_id_2);
		
		JLabel e_id_3 = new JLabel("Rate per day:");
		e_id_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id_3.setBounds(742, 176, 164, 32);
		frame.getContentPane().add(e_id_3);
		
		JLabel e_id_4 = new JLabel("Attendance:");
		e_id_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id_4.setBounds(742, 232, 164, 32);
		frame.getContentPane().add(e_id_4);
		
		JLabel e_id_5 = new JLabel("Net salary:");
		e_id_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id_5.setBounds(407, 345, 164, 32);
		frame.getContentPane().add(e_id_5);
		
		mon = new JTextField();
	    mon.setColumns(10);
	    mon.setBounds(343, 232, 211, 32);
	    frame.getContentPane().add(mon);
		
		off = new JTextField();
		off.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }
		});
		off.setColumns(10);
		off.setBounds(343, 287, 211, 32);
		frame.getContentPane().add(off);
		
		rpd = new JTextField();
		rpd.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }
		});
		rpd.setColumns(10);
		rpd.setBounds(916, 176, 213, 32);
		frame.getContentPane().add(rpd);
		
		atten = new JTextField();
		atten.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        calculateAndSetSalary();
		    }
		});
		atten.setColumns(10);
		atten.setBounds(916, 232, 213, 32);
		frame.getContentPane().add(atten);
		
		sal = new JTextField();
		sal.setColumns(10);
		sal.setBounds(581, 345, 211, 32);
		frame.getContentPane().add(sal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 423, 900, 208);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Month", "Rate per day", "Attendance", "Offers", "Net salary"
			}
		));
		
		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					addSalary();
			}
		});
		Add.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Add.setBackground(new Color(243, 219, 50));
		Add.setBounds(979, 463, 119, 33);
		frame.getContentPane().add(Add);
		
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) { // Check if a row is selected
		            int employeeID = (int) table.getValueAt(selectedRow, 0); // Assuming the employee ID is in the first column
		            deleteSalary(employeeID);
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
		        }
		    }
		});
		Delete.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Delete.setBackground(new Color(243, 219, 50));
		Delete.setBounds(1123, 463, 119, 33);
		frame.getContentPane().add(Delete);
		
		JButton Edit = new JButton("Edit");
		Edit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        editSalary();
		    }
		});
		Edit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Edit.setBackground(new Color(243, 219, 50));
		Edit.setBounds(979, 518, 119, 33);
		frame.getContentPane().add(Edit);

		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnClear.setBackground(new Color(243, 219, 50));
		btnClear.setBounds(1123, 518, 119, 33);
		frame.getContentPane().add(btnClear);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(243, 219, 50));
		separator.setBounds(33, 403, 1172, 9);
		frame.getContentPane().add(separator);
		
		JButton btnDownloadPaySlip = new JButton("Download pay slip");
		btnDownloadPaySlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateReport();
			}
		});
		btnDownloadPaySlip.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDownloadPaySlip.setBackground(new Color(243, 219, 50));
		btnDownloadPaySlip.setBounds(999, 572, 224, 32);
		frame.getContentPane().add(btnDownloadPaySlip);
		
		empID = new JComboBox<>();
		empID.setBounds(343, 176, 211, 32);
		frame.getContentPane().add(empID);
		
		// Populate the employee IDs into the JComboBox
        populateEmployeeIDs();
        
        table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow >= 0) {
	            	empID.setSelectedItem(tableModel.getValueAt(selectedRow, 0));
	                mon.setText(tableModel.getValueAt(selectedRow, 1).toString());
	                rpd.setText(tableModel.getValueAt(selectedRow, 2).toString());
	                atten.setText(tableModel.getValueAt(selectedRow, 3).toString());
	                off.setText(tableModel.getValueAt(selectedRow, 4).toString());
	                sal.setText(tableModel.getValueAt(selectedRow, 5).toString()); 
	            }
	        }
	    });
	}
	
	
	
	private double calculateNetSalary(double ratePerDay, String attendance, double offers) {
	    double netSalary = 0.0;

	    int daysWorked = Integer.parseInt(attendance);
	    
	    netSalary = (ratePerDay * daysWorked) + offers;
	    
	    return netSalary;
	}
	
	private void calculateAndSetSalary() {
	    try {
	        String month = mon.getText();
	        double rate = Double.parseDouble(rpd.getText());
	        String attendance = atten.getText();
	        double offers = Double.parseDouble(off.getText());
	        
	        // Check if all fields are filled
	        if (!month.isEmpty() && !attendance.isEmpty()) {
	            // Calculate net salary using the calculateNetSalary method
	            double salary = calculateNetSalary(rate, attendance, offers);
	            
	            // Set the calculated salary to the salary text field
	            sal.setText(String.valueOf(salary));
	        } else {
	            sal.setText("");
	        }
	    } catch (NumberFormatException ex) {
	        // Handle if any of the fields are not valid numbers
	        sal.setText("");
	    }
	}
	
	// Method to add salary when the ADD button is clicked
	private void addSalary() {
	    int emp_id = empID.getSelectedItem() != null ? (int) empID.getSelectedItem() : -1;
	    String month = mon.getText();
	    double rate = Double.parseDouble(rpd.getText()); 
	    String attendance = atten.getText();
	    double offers = Double.parseDouble(off.getText());
	    
	    // Calculate net salary using the calculateNetSalary method
	    double salary = calculateNetSalary(rate, attendance, offers);
	    
	    // Set the calculated salary to the salary text field
	    sal.setText(String.valueOf(salary));

	    // Check if the salary entry already exists for the employee ID and month
	    boolean entryExists = checkSalaryEntryExists(emp_id, month);

	    // Establish a database connection
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            if (entryExists) {
	                // If the entry already exists, display an error message
	                JOptionPane.showMessageDialog(frame, "Salary entry for the selected employee and month already exists!", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                // If the entry doesn't exist, insert a new entry into the database
	                String query = "INSERT INTO salary (emp_id, month, rate, attendance, offers, salary) VALUES (?, ?, ?, ?, ?, ?)";
	                PreparedStatement preparedStatement = connection.prepareStatement(query);
	                preparedStatement.setInt(1, emp_id);
	                preparedStatement.setString(2, month);
	                preparedStatement.setDouble(3, rate);
	                preparedStatement.setString(4, attendance);
	                preparedStatement.setDouble(5, offers);
	                preparedStatement.setDouble(6, salary);
	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(frame, "Salary added successfully!");
	                    displayTable();
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

	// Method to check if a salary entry already exists for the given employee ID and month
	private boolean checkSalaryEntryExists(int emp_id, String month) {
	    Connection connection = DatabaseConnection.getConnection();
	    boolean entryExists = false;
	    if (connection != null) {
	        try {
	            String query = "SELECT COUNT(*) AS count FROM salary WHERE emp_id=? AND month=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, emp_id);
	            preparedStatement.setString(2, month);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                int count = resultSet.getInt("count");
	                if (count > 0) {
	                    entryExists = true;
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
	    return entryExists;
	}

				
				// Method to populate the JTable with reservation details
				void displayTable() {
					tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);

					Connection connection = DatabaseConnection.getConnection();
					if (connection != null) {
						try {
							Statement statement = connection.createStatement();
							String query = "SELECT * FROM salary";
							ResultSet resultSet = statement.executeQuery(query);

							while (resultSet.next()) {
								int emp_id = resultSet.getInt("emp_id");
								String month = resultSet.getString("month");
								double rate = resultSet.getDouble("rate");
								String attendance = resultSet.getString("attendance");
								double offers = resultSet.getDouble("offers");
								double salary = resultSet.getDouble("salary");

								// Create an employee object
								salary sal = new salary(emp_id,month, rate, attendance, offers, salary);
								salaryList.add(sal);

								// Create a vector containing reservation details
								Vector<Object> row = new Vector<>();
								row.add(sal.getEid());
								row.add(sal.getMonth());
								row.add(sal.getRate());
								row.add(sal.getAttendance());
								row.add(sal.getOffers());
								row.add(sal.getSalary());
								
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
					empID.setSelectedItem(null);
					mon.setText("");
					rpd.setText("");
					atten.setText("");
					off.setText("");
					sal.setText("");
				}
				
				private void populateEmployeeIDs() {
			        Connection connection = DatabaseConnection.getConnection();
			        if (connection != null) {
			            try {
			                Statement statement = connection.createStatement();
			                String query = "SELECT e_id FROM employees"; 
			                ResultSet resultSet = statement.executeQuery(query);

			                // Add each employee ID to the JComboBox
			                while (resultSet.next()) {
			                    int employeeID = resultSet.getInt("e_id");
			                    empID.addItem(employeeID);
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
				
				private void deleteSalary(int employeeID) {
				    int selectedRow = table.getSelectedRow();
				    if (selectedRow != -1) { // Check if a row is selected
				        int confirmDelete = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this salary detail?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				        
				        if (confirmDelete == JOptionPane.YES_OPTION) {
				            Connection connection = DatabaseConnection.getConnection();
				            if (connection != null) {
				                try {
				                    String query = "DELETE FROM salary WHERE emp_id = ? AND month = ?"; // Assuming month is unique
				                    PreparedStatement preparedStatement = connection.prepareStatement(query);
				                    preparedStatement.setInt(1, employeeID);
				                    String month = (String) table.getValueAt(selectedRow, 1); // Assuming month is in the second column
				                    preparedStatement.setString(2, month);
				                    int rowsAffected = preparedStatement.executeUpdate();
				                    if (rowsAffected > 0) {
				                        JOptionPane.showMessageDialog(frame, "Salary detail deleted successfully!");
				                        displayTable(); // Update the table after deletion
				                    } else {
				                        JOptionPane.showMessageDialog(frame, "Failed to delete salary detail.");
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
				        JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
				    }
				}
				
				private void editSalary() {
				    // Get the selected row from the table
				    int selectedRow = table.getSelectedRow();

				    // Check if a row is selected
				    if (selectedRow >= 0) {
				        // Get the employee ID and month from the selected row
				        int emp_id = (int) tableModel.getValueAt(selectedRow, 0);
				        String month = (String) tableModel.getValueAt(selectedRow, 1);

				        // Get the updated data from input fields
				        double rate = Double.parseDouble(rpd.getText());
				        String attendance = atten.getText();
				        double offers = Double.parseDouble(off.getText());

				        // Calculate the net salary using the calculateNetSalary method
				        double salary = calculateNetSalary(rate, attendance, offers);

				        // Update the salary in the database
				        Connection connection = DatabaseConnection.getConnection();
				        if (connection != null) {
				            try {
				                // Update the salary data in the database
				                String query = "UPDATE salary SET rate = ?, attendance = ?, offers = ?, salary = ? WHERE emp_id = ? AND month = ?";
				                PreparedStatement preparedStatement = connection.prepareStatement(query);
				                preparedStatement.setDouble(1, rate);
				                preparedStatement.setString(2, attendance);
				                preparedStatement.setDouble(3, offers);
				                preparedStatement.setDouble(4, salary);
				                preparedStatement.setInt(5, emp_id);
				                preparedStatement.setString(6, month);

				                int rowsAffected = preparedStatement.executeUpdate();
				                if (rowsAffected > 0) {
				                    JOptionPane.showMessageDialog(frame, "Salary details updated successfully!");
				                    clearFields();
				                    displayTable();
				                } else {
				                    JOptionPane.showMessageDialog(frame, "Failed to update salary details.");
				                }

				                preparedStatement.close();
				            } catch (SQLException e) {
				                e.printStackTrace();
				            } finally {
				                DatabaseConnection.closeConnection(connection);
				            }
				        }
				    } else {
				        JOptionPane.showMessageDialog(frame, "Please select a salary detail to update.");
				    }
				}

		
	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
	
	private void generateReport() {
	    // Extract data from the employee form components
	    int employeeID = empID.getSelectedItem() != null ? (int) empID.getSelectedItem() : -1;
	    String month = mon.getText();
	    double offers = Double.parseDouble(off.getText());
	    double rate = Double.parseDouble(rpd.getText());
	    String attendance = atten.getText();
	    double salary = Double.parseDouble(sal.getText());
	    String employeeName = getEmployeeName(employeeID);

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int result = fileChooser.showSaveDialog(frame);
	    if (result != JFileChooser.APPROVE_OPTION) {
	        return; // Exit if the user cancels the operation
	    }

	    File desktopDirectory = fileChooser.getSelectedFile();
	    String desktopPath = desktopDirectory.getAbsolutePath();
	    String fileName = "PaySlip_" + employeeID + "_" + month + ".pdf";
	    File pdfFile = new File(desktopPath, fileName);

	    // Create a PDF document
	    Document document = new Document();

	    try {
	        // Initialize the PdfWriter instance to write to the document
	        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

	        document.open();

	        document.add(new Paragraph("Vass Enterprises", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.DARK_GRAY)));
	        document.add(new Paragraph(" "));

	        document.add(new Paragraph("Pay Slip", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));

	        // Add content to the PDF report
	        document.add(new Paragraph("1. Employee Name: " + employeeName));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("2. Employee ID: " + employeeID));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("3. Month: " + month));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("4. Offers: " + offers));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("5. Rate per day: " + rate));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("6. Attendance: " + attendance));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("7. Salary: " + salary));
	        document.add(Chunk.NEWLINE);

	        JOptionPane.showMessageDialog(frame, "Pay slip generated successfully!");

	        // Open the generated PDF file after creation
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
	
	private String getEmployeeName(int employeeID) {
	    String employeeName = "";
	    Connection connection = DatabaseConnection.getConnection();
	    if (connection != null) {
	        try {
	            String query = "SELECT e_name FROM employees WHERE e_id = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, employeeID);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                employeeName = resultSet.getString("e_name");
	            }
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseConnection.closeConnection(connection);
	        }
	    }
	    return employeeName;
	}

}

