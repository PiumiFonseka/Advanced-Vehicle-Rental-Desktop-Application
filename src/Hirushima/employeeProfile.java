//Employee profile
package Hirushima;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class employeeProfile {

	private JFrame frame;
	private JTable table;
	private JLabel employeeIDLabel;
	private JLabel nameLabel;
	private JLabel nicLabel;
	private JLabel dobLabel;
	private JLabel phoneLabel;
	private JLabel addressLabel;
	private JLabel emailLabel;
	private JLabel positionLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeProfile window = new employeeProfile();
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
	public employeeProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblEmployeeProfile = new JLabel("EMPLOYEE PROFILE");
		lblEmployeeProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeProfile.setFont(new Font("Times New Roman", Font.BOLD, 55));
		lblEmployeeProfile.setBounds(300, 35, 654, 65);
		frame.getContentPane().add(lblEmployeeProfile);

		JLabel back = new JLabel("Back");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				employeeForm form = new employeeForm();
				form.setVisible(true);
				frame.dispose(); // Close the current frame
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		back.setBounds(25, 24, 58, 19);
		frame.getContentPane().add(back);

		JLabel e_id = new JLabel("Employee ID:");
		e_id.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_id.setBounds(72, 175, 164, 32);
		frame.getContentPane().add(e_id);

		JLabel nic = new JLabel("NIC:");
		nic.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nic.setBounds(72, 261, 88, 32);
		frame.getContentPane().add(nic);

		JLabel e_name = new JLabel("Name:");
		e_name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_name.setBounds(72, 218, 82, 32);
		frame.getContentPane().add(e_name);

		JLabel dob = new JLabel("DOB:");
		dob.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dob.setBounds(72, 304, 73, 32);
		frame.getContentPane().add(dob);

		JLabel cNo = new JLabel("Contact no:");
		cNo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cNo.setBounds(72, 348, 143, 32);
		frame.getContentPane().add(cNo);

		JLabel address = new JLabel("Address:");
		address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		address.setBounds(72, 391, 106, 32);
		frame.getContentPane().add(address);

		JLabel e_address = new JLabel("Email address:");
		e_address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		e_address.setBounds(72, 434, 171, 32);
		frame.getContentPane().add(e_address);

		JLabel posi = new JLabel("Position:");
		posi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		posi.setBounds(72, 477, 106, 32);
		frame.getContentPane().add(posi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(836, 200, 330, 395);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Month", "Attendance", "Salary" }));

		JLabel e_id_1 = new JLabel("Salary details:");
		e_id_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		e_id_1.setBounds(766, 158, 164, 32);
		frame.getContentPane().add(e_id_1);

		JButton btnViewSalaryCalculations = new JButton("View salary calculations");
		btnViewSalaryCalculations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the employeeSalary page when the button is clicked
				employeeSalary salary = new employeeSalary();
				salary.setVisible(true);
				frame.dispose(); // Close the current frame
			}
		});
		btnViewSalaryCalculations.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewSalaryCalculations.setBackground(new Color(243, 219, 50));
		btnViewSalaryCalculations.setBounds(919, 620, 296, 32);
		frame.getContentPane().add(btnViewSalaryCalculations);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 219, 50));
		panel.setBounds(0, 0, 1380, 120);
		frame.getContentPane().add(panel);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(243, 219, 50));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(673, 131, 9, 521);
		frame.getContentPane().add(separator);

		employeeIDLabel = new JLabel("");
		employeeIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		employeeIDLabel.setBounds(240, 180, 232, 29);
		frame.getContentPane().add(employeeIDLabel);

		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(240, 218, 232, 29);
		frame.getContentPane().add(nameLabel);

		nicLabel = new JLabel("");
		nicLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nicLabel.setBounds(240, 264, 232, 29);
		frame.getContentPane().add(nicLabel);

		dobLabel = new JLabel("");
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dobLabel.setBounds(240, 307, 232, 29);
		frame.getContentPane().add(dobLabel);

		phoneLabel = new JLabel("");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneLabel.setBounds(240, 351, 232, 29);
		frame.getContentPane().add(phoneLabel);

		addressLabel = new JLabel("");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressLabel.setBounds(240, 394, 232, 29);
		frame.getContentPane().add(addressLabel);

		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setBounds(240, 437, 232, 29);
		frame.getContentPane().add(emailLabel);

		positionLabel = new JLabel("");
		positionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		positionLabel.setBounds(240, 480, 232, 29);
		frame.getContentPane().add(positionLabel);

	}

	// Method to fetch and display employee details including salary details
	public void fetchEmployeeDetails(int employeeID) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				// Fetch employee details
				String employeeQuery = "SELECT * FROM employees WHERE e_id = ?";
				PreparedStatement employeeStatement = connection.prepareStatement(employeeQuery);
				employeeStatement.setInt(1, employeeID);
				ResultSet employeeResult = employeeStatement.executeQuery();

				// Populate employee details if found
				if (employeeResult.next()) {
					String e_name = employeeResult.getString("e_name");
					String e_nic = employeeResult.getString("e_nic");
					String e_dob = employeeResult.getString("e_dob");
					int e_phone = employeeResult.getInt("e_phone");
					String e_address = employeeResult.getString("e_address");
					String e_email = employeeResult.getString("e_email");
					String e_position = employeeResult.getString("e_position");

					// Populate the labels with the fetched employee details
					employeeIDLabel.setText(Integer.toString(employeeID));
					nameLabel.setText(e_name);
					nicLabel.setText(e_nic);
					dobLabel.setText(e_dob);
					phoneLabel.setText(Integer.toString(e_phone));
					addressLabel.setText(e_address);
					emailLabel.setText(e_email);
					positionLabel.setText(e_position);
				}
				employeeResult.close();
				employeeStatement.close();

				// Fetch and display salary details
				String salaryQuery = "SELECT month, attendance, salary FROM salary WHERE emp_id = ?";
				PreparedStatement salaryStatement = connection.prepareStatement(salaryQuery);
				salaryStatement.setInt(1, employeeID);
				ResultSet salaryResult = salaryStatement.executeQuery();

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0); // Clear previous data

				// Populate salary details into the table
				while (salaryResult.next()) {
					String month = salaryResult.getString("month");
					String attendance = salaryResult.getString("attendance");
					double salary = salaryResult.getDouble("salary");

					// Add salary details to the table model
					tableModel.addRow(new Object[] { month, attendance, salary });
				}
				salaryResult.close();
				salaryStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
	}

	public void setVisible(boolean b) {
        frame.setVisible(b);
	}
}