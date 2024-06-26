package Hiruni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VehicleImages extends JFrame {

	private JFrame frame;
	private JTable table;
	private JPanel panel_2;
	private int vehicleId;
	private JLabel lblNewLabel_1_2_1_1;

	public VehicleImages(int vehicleId) {
		this.vehicleId = vehicleId;
		initialize();
		populateTable();
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			int vehicleId = Integer.parseInt(args[0]);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VehicleImages window = new VehicleImages(vehicleId);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			System.out.println("No vehicle ID provided.");
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JLabel lblVehicleInventory = new JLabel("Vehicle Images");
		lblVehicleInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleInventory.setForeground(new Color(243, 219, 50));
		lblVehicleInventory.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleInventory.setBounds(225, 10, 813, 61);
		panel.add(lblVehicleInventory);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(243, 219, 50));
		panel_3.setBounds(0, 81, 1266, 602);
		panel.add(panel_3);

		JLabel lblNewLabel_1_2_2 = new JLabel("Vehicle ID :");
		lblNewLabel_1_2_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_2_2.setBounds(47, 20, 121, 72);
		panel_3.add(lblNewLabel_1_2_2);

		JLabel lblNewLabel_1_2_2_1 = new JLabel("ID");
		lblNewLabel_1_2_2_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_2_2_1.setBounds(172, 20, 70, 72);
		panel_3.add(lblNewLabel_1_2_2_1);
		lblNewLabel_1_2_2_1.setText(Integer.toString(vehicleId));

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.GRAY);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(618, 30, 648, 572);
		panel_3.add(panel_2);
		this.panel_2 = panel_2;

		lblNewLabel_1_2_1_1 = new JLabel("                         Preview Image");
		lblNewLabel_1_2_1_1.setBounds(10, 123, 628, 439);
		panel_2.add(lblNewLabel_1_2_1_1);
		lblNewLabel_1_2_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 0, 648, 113);
		panel_2.add(panel_2_1);
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.GRAY);
		panel_2_1.setBackground(new Color(255, 255, 255));

		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.setBounds(79, 39, 132, 44);
		panel_2_1.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(6, 6, 6));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1.setBackground(Color.WHITE);

		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.setBounds(442, 39, 132, 44);
		panel_2_1.add(btnDelete_1);
		btnDelete_1.setForeground(new Color(6, 6, 6));
		btnDelete_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete_1.setBackground(Color.WHITE);
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to delete this maintenance record?", "Confirm Deletion",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					if (row != -1) {
						int imageId = (int) table.getValueAt(row, 0);
						deleteImageFromDatabase(imageId);
					} else {
						JOptionPane.showMessageDialog(frame, "Please select an image to delete.");
					}
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (getNumberOfImages() >= 4) {
					JOptionPane.showMessageDialog(frame, "Maximum limit of 4 images reached for this vehicle.");
					return;
				} else {
					JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						try {
							FileInputStream fis = new FileInputStream(selectedFile);
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							byte[] buffer = new byte[1024];
							int bytesRead;
							while ((bytesRead = fis.read(buffer)) != -1) {
								baos.write(buffer, 0, bytesRead);
							}
							byte[] imageBytes = baos.toByteArray();
							fis.close();
							baos.close();
							addImageToDatabase(imageBytes);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		JButton btnBack = new JButton("EXIT");
		btnBack.setBounds(463, 519, 136, 44);
		panel_3.add(btnBack);
		btnBack.setForeground(new Color(6, 6, 6));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnBack.setBackground(Color.WHITE);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				VehicleSpecifications.main(new String[] {});
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 102, 598, 362);
		panel_3.add(scrollPane_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Image Id", "Image Path" }));
		scrollPane_1.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					// Display image preview
					displayImagePreview((String) table.getValueAt(row, 1));
					btnNewButton_1.setVisible(true);
					btnDelete_1.setVisible(true);

				} else {
					// If no row is selected, remove the image preview
					panel_2.removeAll();
					panel_2.revalidate();
					panel_2.repaint();

				}
			}
		});
	}

	private void displayImagePreview(String imageName) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT image FROM vehicle_images WHERE image_name = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, imageName);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				byte[] imageData = resultSet.getBytes("image");
				try {
					ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
					Image image = ImageIO.read(bis);
					ImageIcon imageIcon = new ImageIcon(image);
					Image scaledImage = imageIcon.getImage().getScaledInstance(lblNewLabel_1_2_1_1.getWidth(),
							lblNewLabel_1_2_1_1.getHeight(), Image.SCALE_SMOOTH);
					lblNewLabel_1_2_1_1.setIcon(new ImageIcon(scaledImage));
					bis.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addImageToDatabase(byte[] imageBytes) {

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String imageName = generateUniqueImageName();
			String query = "INSERT INTO vehicle_images (vehicle_id, image_name, image) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, vehicleId);
			preparedStatement.setString(2, imageName);
			preparedStatement.setBytes(3, imageBytes);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(frame, "Image added successfully.");
			populateTable();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String generateUniqueImageName() {
		long timeStamp = System.currentTimeMillis();
		return "image_" + timeStamp + ".jpg";
	}

	private void populateTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		String query = "SELECT id, image_name FROM vehicle_images WHERE vehicle_id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, vehicleId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int imageId = resultSet.getInt("id");
				String imagePath = resultSet.getString("image_name");
				model.addRow(new Object[] { imageId, imagePath });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// Method to delete image from the database
	private void deleteImageFromDatabase(int imageId) {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "DELETE FROM vehicle_images WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, imageId);
			int deletedRows = preparedStatement.executeUpdate();
			if (deletedRows > 0) {
				JOptionPane.showMessageDialog(frame, "Image deleted successfully.");
				populateTable();
				lblNewLabel_1_2_1_1.setIcon(null); // Clear image preview
			} else {
				JOptionPane.showMessageDialog(frame, "Failed to delete image.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private int getNumberOfImages() {
	    int count = 0;
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM vehicle_images WHERE vehicle_id = ?")) {
	        preparedStatement.setInt(1, vehicleId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            count = resultSet.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
}
