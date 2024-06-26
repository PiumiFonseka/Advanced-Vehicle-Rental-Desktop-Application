
//reservation form

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
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import piumi.driverInfo;
import Hiruni.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class reservationForm {

	private JFrame frame;
	private JTextField id;
	private JTextField cus_id;
	private JTextField pt;
	private JTextField rt;
	private JTextField textField;
	private JTextField Co;
	private JTable table;

	private DefaultTableModel tableModel;

	private List<reservation> reservationList = new ArrayList<>();
	private JTextField vid;

	private JXDatePicker pd;
	private JXDatePicker rd;

	private JRadioButton yes_rbtn;
	private JRadioButton no_rbtn;
	private ButtonGroup buttonGroup;

	private String driver_needed;
	private static JTextField ysno;

	private static String driverID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservationForm window = new reservationForm();
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
	public reservationForm() {
		initialize();
		populateTable();
		updateYSNOField();
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

		JLabel reserv = new JLabel("MAKE A RESERVATION");
		reserv.setHorizontalAlignment(SwingConstants.CENTER);
		reserv.setFont(new Font("Times New Roman", Font.BOLD, 55));
		reserv.setBounds(313, 49, 654, 65);
		frame.getContentPane().add(reserv);

		JLabel back = new JLabel("Back");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Hiruni.Dashboard.main(new String[] {});

			}
		});
		
		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(21, 11, 58, 19);
		frame.getContentPane().add(back);

		JLabel r_id = new JLabel("Reservation ID:");
		r_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		r_id.setBounds(120, 183, 196, 32);
		frame.getContentPane().add(r_id);

		JLabel c_id = new JLabel("Customer ID:");
		c_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		c_id.setBounds(120, 293, 154, 32);
		frame.getContentPane().add(c_id);

		JLabel v_id = new JLabel("Vehicle ID:");
		v_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		v_id.setBounds(120, 336, 154, 32);
		frame.getContentPane().add(v_id);

		JLabel driver = new JLabel("Need a driver?");
		driver.setFont(new Font("Tahoma", Font.PLAIN, 25));
		driver.setBounds(120, 239, 185, 32);
		frame.getContentPane().add(driver);

		JLabel Date = new JLabel("Date:");
		Date.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Date.setBounds(666, 226, 79, 32);
		frame.getContentPane().add(Date);

		JLabel Time = new JLabel("Time:");
		Time.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Time.setBounds(666, 274, 79, 32);
		frame.getContentPane().add(Time);

		JLabel ret = new JLabel("Return details");
		ret.setFont(new Font("Times New Roman", Font.BOLD, 25));
		ret.setBounds(1055, 179, 161, 32);
		frame.getContentPane().add(ret);

		JLabel pickup = new JLabel("Pickup details");
		pickup.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pickup.setBounds(789, 179, 161, 32);
		frame.getContentPane().add(pickup);

		id = new JTextField();
		id.setBounds(315, 183, 196, 32);
		frame.getContentPane().add(id);
		id.setColumns(10);
		// Get the next available Reservation ID and set it in the id field
		int nextReservationID = getNextR_ID();
		id.setText(Integer.toString(nextReservationID));

		cus_id = new JTextField();
		cus_id.setColumns(10);
		cus_id.setBounds(313, 293, 198, 32);
		frame.getContentPane().add(cus_id);

		pd = new JXDatePicker();
		pd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateNumberOfDays();
			}
		});
		pd.setBounds(771, 226, 196, 33);
		frame.getContentPane().add(pd);

		rd = new JXDatePicker();
		rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateNumberOfDays();
			}
		});
		rd.setBounds(1037, 226, 196, 32);
		frame.getContentPane().add(rd);

		pt = new JTextField();
		pt.setColumns(10);
		pt.setBounds(771, 280, 196, 32);
		frame.getContentPane().add(pt);

		rt = new JTextField();
		rt.setColumns(10);
		rt.setBounds(1037, 280, 196, 32);
		frame.getContentPane().add(rt);

		JLabel days = new JLabel("No. of days:");
		days.setFont(new Font("Tahoma", Font.PLAIN, 25));
		days.setBounds(813, 331, 154, 32);
		frame.getContentPane().add(days);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(977, 334, 196, 32);
		frame.getContentPane().add(textField);

		JLabel cost = new JLabel("Cost:");
		cost.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cost.setBounds(120, 379, 154, 32);
		frame.getContentPane().add(cost);

		Co = new JTextField();
		Co.setColumns(10);
		Co.setBounds(313, 385, 196, 32);
		frame.getContentPane().add(Co);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(243, 219, 50));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(619, 167, 18, 264);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(243, 219, 50));
		separator_1.setBounds(0, 442, 1380, 19);
		frame.getContentPane().add(separator_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 461, 946, 209);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Customer ID", "Vehicle ID",
				"Driver?", "Pickup date", "Pickup time", "Return date", "Return time", "No of days", "Cost" }));

		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReservation(); // Calls the addReservation method when clicked
			}
		});
		Submit.setBackground(new Color(243, 219, 50));
		Submit.setFont(new Font("Tahoma", Font.BOLD, 20));
		Submit.setBounds(1058, 391, 132, 32);
		frame.getContentPane().add(Submit);

		JButton pay = new JButton("Make payment");
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); 
				bhagya.advanced_Payment.main(new String[] {});
			}
		});
		pay.setFont(new Font("Tahoma", Font.BOLD, 20));
		pay.setBackground(new Color(243, 219, 50));
		pay.setBounds(1037, 609, 185, 32);
		frame.getContentPane().add(pay);

		JButton View = new JButton("View");
		View.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) { // If a row is selected
		            int selectedReservationID = (int) tableModel.getValueAt(selectedRow, 0); // Assuming reservation ID is in the first column
		            reservationProfile profile = new reservationProfile();
		            profile.setVisible(true);
		            profile.fetchReservationDetails(selectedReservationID); // Pass the selected reservation ID
		            frame.dispose(); // Close the current frame
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to view.");
		        }
		    }
		});

		View.setFont(new Font("Tahoma", Font.BOLD, 20));
		View.setBackground(new Color(243, 219, 50));
		View.setBounds(1129, 554, 125, 32);
		frame.getContentPane().add(View);

		vid = new JTextField();
		vid.setColumns(10);
		vid.setBounds(313, 342, 198, 32);
		frame.getContentPane().add(vid);

		yes_rbtn = new JRadioButton("Yes");
		yes_rbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yes_rbtn.setBounds(313, 222, 51, 21);
		frame.getContentPane().add(yes_rbtn);

		no_rbtn = new JRadioButton("No");
		no_rbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		no_rbtn.setBounds(313, 259, 51, 21);
		frame.getContentPane().add(no_rbtn);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(yes_rbtn);
		buttonGroup.add(no_rbtn);

		ysno = new JTextField();
		ysno.setColumns(10);
		ysno.setBounds(381, 239, 130, 32);
		frame.getContentPane().add(ysno);

		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editReservation();
			}
		});
		edit.setFont(new Font("Tahoma", Font.BOLD, 20));
		edit.setBackground(new Color(243, 219, 50));
		edit.setBounds(992, 498, 119, 32);
		frame.getContentPane().add(edit);

		JButton Clear = new JButton("Clear");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		Clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		Clear.setBackground(new Color(243, 219, 50));
		Clear.setBounds(1129, 498, 125, 32);
		frame.getContentPane().add(Clear);

		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Check if a row is selected
					int adminID = (int) table.getValueAt(selectedRow, 0); // Assuming the admin ID is in the first
																			// column
					deleteReservation(adminID);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
				}
			}
		});
		Delete.setFont(new Font("Tahoma", Font.BOLD, 20));
		Delete.setBackground(new Color(243, 219, 50));
		Delete.setBounds(992, 554, 119, 32);
		frame.getContentPane().add(Delete);

		yes_rbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				driverInfo.main(null);
				frame.dispose();
			}
		});

		no_rbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ysno.setText("No");
			}
		});


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					id.setText(tableModel.getValueAt(selectedRow, 0).toString()); // Assuming id is a JTextField
					cus_id.setText(tableModel.getValueAt(selectedRow, 1).toString());
					vid.setText(tableModel.getValueAt(selectedRow, 2).toString());
					String driverNeeded = tableModel.getValueAt(selectedRow, 3).toString(); // Assuming driverNeeded is
																							// a String field
					if (driverNeeded.equals("Yes")) {
						yes_rbtn.setSelected(true);
					} else {
						no_rbtn.setSelected(true);
					}
					String pickDate = tableModel.getValueAt(selectedRow, 4).toString(); // Assuming pickDate is a String
																						// field
					pd.setDate(parseDate(pickDate)); // Assuming pd is a JXDatePicker
					pt.setText(tableModel.getValueAt(selectedRow, 5).toString());
					String returnDate = tableModel.getValueAt(selectedRow, 6).toString(); // Assuming returnDate is a
																							// String field
					rd.setDate(parseDate(returnDate)); // Assuming rd is a JXDatePicker
					rt.setText(tableModel.getValueAt(selectedRow, 7).toString());
					textField.setText(tableModel.getValueAt(selectedRow, 8).toString());
					Co.setText(tableModel.getValueAt(selectedRow, 9).toString());
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

	public static void setDriverID(String id) {
		driverID = id;
	}

	public static void updateYSNOField() {
		ysno.setText(driverID);
	}

	// Method to add a reservation when the ADD button is clicked
	private void addReservation() {
		int cid = Integer.parseInt(cus_id.getText());
		int vehi_id = Integer.parseInt(vid.getText());
		String driver_needed = ysno.getText();
		String pick_date = formatDate(pd.getDate());
		String pick_time = pt.getText();
		String return_date = formatDate(rd.getDate());
		String return_time = rt.getText();
		int days = Integer.parseInt(textField.getText());

		// Retrieve the price per day of the selected vehicle from the database
		double pricePerDay = getPricePerDay(vehi_id);

		// Calculate the total cost
		double cost = pricePerDay * days;

		// Set the calculated cost in the "Cost" field
		Co.setText(String.valueOf(cost));

		// Create a reservation object with the entered data
		reservation res = new reservation(0, cid, vehi_id, driver_needed, pick_date, pick_time, return_date,
				return_time, days, cost);

		// Establish a database connection
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			PreparedStatement preparedStatement = null;
			try {
				// SQL query to insert a new reservation into the database
				String query = "INSERT INTO reservations (cid, vehi_id, driver_needed, pick_date, pick_time, return_date, return_time, days, cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				// Set values in the prepared statement
				preparedStatement.setInt(1, cid);
				preparedStatement.setInt(2, vehi_id);
				preparedStatement.setString(3, driver_needed);
				preparedStatement.setString(4, pick_date);
				preparedStatement.setString(5, pick_time);
				preparedStatement.setString(6, return_date);
				preparedStatement.setString(7, return_time);
				preparedStatement.setInt(8, days);
				preparedStatement.setDouble(9, cost);

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedRId = generatedKeys.getInt(1); // Get the generated r_id

						// Update the rID field with the generated ID
						id.setText(Integer.toString(generatedRId));
						JOptionPane.showMessageDialog(frame, "Reservation added successfully!");
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to add Reservation.");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Failed to add Reservation.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	// Method to populate the JTable with reservation details
	void populateTable() {
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM reservations";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					int id = resultSet.getInt("r_id");
					int cid = resultSet.getInt("cid");
					int vehi_id = resultSet.getInt("vehi_id");
					String driver_needed = resultSet.getString("driver_needed");
					String pick_date = resultSet.getString("pick_date");
					String pick_time = resultSet.getString("pick_time");
					String return_date = resultSet.getString("return_date");
					String return_time = resultSet.getString("return_time");
					int days = resultSet.getInt("days");
					double cost = resultSet.getDouble("cost");

					// Create a reservation object
					reservation res = new reservation(id, cid, vehi_id, driver_needed, pick_date, pick_time,
							return_date, return_time, days, cost);
					reservationList.add(res);

					// Create a vector containing reservation details
					Vector<Object> row = new Vector<>();
					row.add(res.getRid());
					row.add(res.getCid());
					row.add(res.getVehiID());
					row.add(res.getDriver_needed());
					row.add(res.getPick_date());
					row.add(res.getPick_time());
					row.add(res.getReturn_date());
					row.add(res.getReturn_time());
					row.add(res.getDays());
					row.add(res.getCost());

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
		// Clear text fields
		id.setText(""); // Assuming id is a JTextField
		cus_id.setText("");
		vid.setText("");
		pt.setText("");
		rt.setText("");
		textField.setText("");
		Co.setText("");

		// Clear radio buttons if needed
		buttonGroup.clearSelection();

		// Clear date pickers
		pd.setDate(null);
		rd.setDate(null);
	}

	private void editReservation() {
		int selectedRowIndex = table.getSelectedRow();

		if (selectedRowIndex >= 0) {

			int rid = (int) tableModel.getValueAt(selectedRowIndex, 0);
			int cid = Integer.parseInt(cus_id.getText());
			int vehi_id = Integer.parseInt(vid.getText());
			String driver_needed = ysno.getText();
			String pick_date = formatDate(pd.getDate());
			String pick_time = pt.getText();
			String return_date = formatDate(rd.getDate());
			String return_time = rt.getText();
			int days = Integer.parseInt(textField.getText());
			double cost = Double.parseDouble(Co.getText());

			// Update driver object in the list
			for (reservation rs : reservationList) {
				if (rs.getRid() == rid) {
					rs.setCid(cid);
					rs.setVehiID(vehi_id);
					rs.setDriver_needed(driver_needed);
					rs.setPick_date(pick_date);
					rs.setPick_time(pick_time);
					rs.setReturn_date(return_date);
					rs.setReturn_time(return_time);
					rs.setDays(days);
					rs.setCost(cost);
					break;
				}
			}

			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {

					String query = "UPDATE reservations SET cid=?, vehi_id=?, driver_needed=?, pick_date=?, pick_time=?, "
							+ "return_date=?, return_time=?, days=?, cost=? " + "WHERE r_id=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, cid);
					preparedStatement.setInt(2, vehi_id);
					preparedStatement.setString(3, driver_needed);
					preparedStatement.setString(4, pick_date);
					preparedStatement.setString(5, pick_time);
					preparedStatement.setString(6, return_date);
					preparedStatement.setString(7, return_time);
					preparedStatement.setInt(8, days);
					preparedStatement.setDouble(9, cost);
					preparedStatement.setInt(10, rid);

					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Reservation updated successfully!");
						clearFields();
						populateTable();
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to update reservation.");
					}

					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DatabaseConnection.closeConnection(connection);
				}
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Please select a reservation to edit.");
		}
	}

	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	// Get the next available reservation ID by finding the maximum existing ID and
	// incrementing it
	private int getNextR_ID() {
		int nextID = 1; // Default value if no reservations exist
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT MAX(r_id) FROM reservations";
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

	private void deleteReservation(int reservationID) {
		int confirmDelete = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this reservation?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION);

		if (confirmDelete == JOptionPane.YES_OPTION) {
			Connection connection = DatabaseConnection.getConnection();
			if (connection != null) {
				try {
					String query = "DELETE FROM reservations WHERE r_id = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, reservationID);
					int rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(frame, "Reservation deleted successfully!");
						populateTable(); // Update the table after deletion
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to delete reservation.");
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

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	private void updateNumberOfDays() {
		// Get selected pickup and return dates
		Date pickupDate = pd.getDate();
		Date returnDate = rd.getDate();

		if (pickupDate != null && returnDate != null) {
			// Calculate the difference between the dates in milliseconds
			long difference = returnDate.getTime() - pickupDate.getTime();
			// Convert milliseconds to days
			int daysDifference = (int) (difference / (1000 * 60 * 60 * 24));
			// Set the calculated number of days in the "No. of days" text field
			textField.setText(Integer.toString(daysDifference));

			// Recalculate the cost based on the new number of days
			double pricePerDay = getPricePerDay(Integer.parseInt(vid.getText())); // Assuming vid is the vehicle ID
																					// field
			double cost = pricePerDay * daysDifference;
			Co.setText(String.valueOf(cost));
		}
	}

	private double getPricePerDay(int vehicleID) {
		double pricePerDay = 0.0;
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT vehi_price FROM vehicle_specifications WHERE vehi_id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, vehicleID);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					pricePerDay = resultSet.getDouble("vehi_price");
				}
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return pricePerDay;
	}
}

