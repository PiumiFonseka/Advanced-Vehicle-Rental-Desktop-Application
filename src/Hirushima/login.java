//login

package Hirushima;

//import the necessary libraries
import java.awt.Color;

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JTextField Uname_txtF;
	private JPasswordField pwd_txtF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(80, 80, 80));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JButton login_btn = new JButton("Login");
		login_btn.setBackground(new Color(192, 192, 192));
		login_btn.setBounds(729, 460, 150, 36);
		login_btn.setFont(new Font("Tahoma", Font.PLAIN, 21));
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredUsername = Uname_txtF.getText();
				String enteredPassword = new String(pwd_txtF.getPassword());

				String adminName = authenticateUser(enteredUsername, enteredPassword);
				if (adminName != null) {
					Hiruni.Dashboard.main(new String[] { adminName });
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
				}
			}
		});
		JLabel lbl_X = new JLabel(" X");
		lbl_X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lbl_X.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lbl_X.setBounds(1218, 11, 46, 44);
		frame.getContentPane().add(lbl_X);

		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(login_btn);

		Uname_txtF = new JTextField();
		Uname_txtF.setBackground(new Color(192, 192, 192));
		Uname_txtF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Uname_txtF.setBounds(729, 270, 325, 30);
		frame.getContentPane().add(Uname_txtF);
		Uname_txtF.setColumns(10);

		pwd_txtF = new JPasswordField();
		pwd_txtF.setBackground(new Color(192, 192, 192));
		pwd_txtF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwd_txtF.setBounds(729, 330, 325, 30);
		frame.getContentPane().add(pwd_txtF);

		JLabel Uname_lbl = new JLabel("Username:");
		Uname_lbl.setForeground(new Color(255, 255, 255));
		Uname_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Uname_lbl.setBounds(560, 270, 136, 22);
		frame.getContentPane().add(Uname_lbl);

		JLabel pwd_lbl = new JLabel(" Password:");
		pwd_lbl.setForeground(new Color(255, 255, 255));
		pwd_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pwd_lbl.setBounds(560, 330, 150, 22);
		frame.getContentPane().add(pwd_lbl);

		JCheckBox pwd_chkbox = new JCheckBox("Show password");
		pwd_chkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pwd_chkbox.isSelected()) {
					// Show the password
					pwd_txtF.setEchoChar((char) 0);
				} else {
					// Hide the password with dots (•)
					pwd_txtF.setEchoChar('\u2022');
				}
			}

		});
		pwd_chkbox.setBackground(new Color(192, 192, 192));
		pwd_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pwd_chkbox.setBounds(941, 384, 113, 23);
		frame.getContentPane().add(pwd_chkbox);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(192, 192, 192));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(421, 53, 18, 594);
		frame.getContentPane().add(separator_4);

		JLabel lblNewLabel = new JLabel("VASS");
		lblNewLabel.setForeground(new Color(243, 219, 50));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.ITALIC, 55));
		lblNewLabel.setBounds(59, 262, 380, 60);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enterprises");
		lblNewLabel_1.setForeground(new Color(243, 219, 50));
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.ITALIC, 55));
		lblNewLabel_1.setBounds(106, 314, 316, 60);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel Login_title = new JLabel("LOGIN");
		Login_title.setBounds(715, 120, 199, 70);
		frame.getContentPane().add(Login_title);
		Login_title.setVerticalAlignment(SwingConstants.BOTTOM);
		Login_title.setHorizontalAlignment(SwingConstants.CENTER);
		Login_title.setBackground(new Color(202, 255, 255));
		Login_title.setForeground(new Color(243, 219, 50));
		Login_title.setFont(new Font("Times New Roman", Font.BOLD, 60));
		
		JButton signUp = new JButton("Sign up");
		signUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		signUp.setBounds(873, 545, 113, 30);
		signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminForm.main(null);
                frame.dispose();
            }
        });
		frame.getContentPane().add(signUp);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account?");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(678, 552, 158, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 0, 0));
				panel.setBounds(478, 72, 673, 540);
				frame.getContentPane().add(panel);
	}

	private String authenticateUser(String username, String password) {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);

				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					// User found with the provided credentials
					String adminUsername = resultSet.getString("username");
					DatabaseConnection.closeConnection(connection);
					return adminUsername;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closeConnection(connection);
			}
		}
		return null;
	}
}