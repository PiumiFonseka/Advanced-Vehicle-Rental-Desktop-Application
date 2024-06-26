package Hiruni;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Hirushima.reservationInfo;

public class VehicleSelection {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnCars;
	private JButton btnVans;
	private JButton btnJeeps;
	private JButton btnBikes;
	private JButton btnLorries;
	private JButton btnBusses;
	private JButton btnCars_1;
	private JComboBox<String> comboBoxPassengers;
	private JComboBox<String> comboBoxGearType;
	private JComboBox<String> comboBoxPrice;
	private JComboBox<String> comboBoxFuelType;
	private String selectedVehicleType;
	private String selectedPassengers;
	private String selectedGearType;
	private String selectedPrice;
	private String selectedFuelType;
	private Map<Integer, List<ImageIcon>> vehicleImageMap = new HashMap<>();
	private int currentImageIndex = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleSelection window = new VehicleSelection();
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
	public VehicleSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(0, 78, 1266, 80);
		frame.getContentPane().add(panel_2);

		btnCars = new JButton("Cars");
		btnCars.setForeground(new Color(6, 6, 6));
		btnCars.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCars.setBackground(new Color(255, 255, 255));
		btnCars.setBounds(192, 10, 167, 57);
		panel_2.add(btnCars);

		btnVans = new JButton("Vans");
		btnVans.setForeground(new Color(6, 6, 6));
		btnVans.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnVans.setBackground(new Color(255, 255, 255));
		btnVans.setBounds(369, 10, 167, 57);
		panel_2.add(btnVans);

		btnJeeps = new JButton("Jeeps");
		btnJeeps.setForeground(new Color(6, 6, 6));
		btnJeeps.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnJeeps.setBackground(new Color(255, 255, 255));
		btnJeeps.setBounds(546, 10, 167, 57);
		panel_2.add(btnJeeps);

		btnBikes = new JButton("Bikes");
		btnBikes.setForeground(new Color(6, 6, 6));
		btnBikes.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnBikes.setBackground(new Color(255, 255, 255));
		btnBikes.setBounds(723, 10, 167, 57);
		panel_2.add(btnBikes);

		btnLorries = new JButton("Lorries");
		btnLorries.setForeground(new Color(6, 6, 6));
		btnLorries.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnLorries.setBackground(new Color(255, 255, 255));
		btnLorries.setBounds(899, 10, 167, 57);
		panel_2.add(btnLorries);

		btnBusses = new JButton("Busses");
		btnBusses.setForeground(new Color(6, 6, 6));
		btnBusses.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnBusses.setBackground(new Color(255, 255, 255));
		btnBusses.setBounds(1076, 10, 167, 57);
		panel_2.add(btnBusses);
		
		JButton btnAll = new JButton("All");
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    // Resetting all filters to default or unselected states
			    comboBoxPassengers.setSelectedIndex(0);  // Assuming the first item is the default
			    comboBoxGearType.setSelectedIndex(0);
			    comboBoxPrice.setSelectedIndex(0);
			    comboBoxFuelType.setSelectedIndex(0);
			    selectedVehicleType = null;  // Reset the vehicle type filter as well

			    displayAllVehicles();
			}
		});
		btnAll.setForeground(new Color(6, 6, 6));
		btnAll.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAll.setBackground(Color.WHITE);
		btnAll.setBounds(21, 10, 161, 57);
		panel_2.add(btnAll);

		// Button for 'Exit' with an action
		btnCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Cars");
			}
		});

		btnVans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Vans");
			}
		});

		btnJeeps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Jeeps");
			}
		});

		btnBikes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Bikes");
			}
		});

		btnLorries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Lorries");
			}
		});

		btnBusses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateVehicleType("Busses");
			}
		});

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		panel.setBounds(0, 0, 1266, 241);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(1204, 10, 33, 36);
		panel.add(lblNewLabel);

		// Add a click listener to the 'lblNewLabel' for exiting the application
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JLabel lblVehicleSelection = new JLabel("Vehicle Selection");
		lblVehicleSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleSelection.setForeground(new Color(243, 219, 50));
		lblVehicleSelection.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleSelection.setBackground(new Color(114, 224, 239));
		lblVehicleSelection.setBounds(225, 10, 813, 61);
		panel.add(lblVehicleSelection);

		btnCars_1 = new JButton("Exit");
		btnCars_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Dashboard.main(new String[] {});
			}
		});
		btnCars_1.setBounds(25, 14, 158, 50);
		panel.add(btnCars_1);
		btnCars_1.setForeground(new Color(6, 6, 6));
		btnCars_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnCars_1.setBackground(Color.WHITE);

		comboBoxPassengers = new JComboBox<>();
		comboBoxPassengers.setBackground(new Color(243, 219, 50));
		comboBoxPassengers.setBounds(214, 187, 169, 36);
		panel.add(comboBoxPassengers);
		initializeComboBox(comboBoxPassengers, "Number of Passengers");

		comboBoxPassengers.addItem("2");
		comboBoxPassengers.addItem("3");
		comboBoxPassengers.addItem("4");
		comboBoxPassengers.addItem("5");
		comboBoxPassengers.addItem("more");

		JLabel lblNewLabel_1 = new JLabel("Number of Passengers");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(10, 183, 210, 36);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Gear Type");
		lblNewLabel_1_1.setToolTipText("");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(401, 183, 109, 36);
		panel.add(lblNewLabel_1_1);

		comboBoxGearType = new JComboBox<>();
		comboBoxGearType.setBackground(new Color(243, 219, 50));
		comboBoxGearType.setBounds(500, 187, 169, 36);
		panel.add(comboBoxGearType);
		initializeComboBox(comboBoxGearType, "Gear Type");
		comboBoxGearType.addItem("Auto");
		comboBoxGearType.addItem("Manual");

		JLabel lblNewLabel_1_1_1 = new JLabel("Price per day");
		lblNewLabel_1_1_1.setToolTipText("");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(687, 183, 134, 36);
		panel.add(lblNewLabel_1_1_1);

		comboBoxPrice = new JComboBox<>();
		comboBoxPrice.setBackground(new Color(243, 219, 50));
		comboBoxPrice.setBounds(810, 187, 169, 36);
		panel.add(comboBoxPrice);
		initializeComboBox(comboBoxPrice, "Price per day");
		comboBoxPrice.addItem("Low to High");
		comboBoxPrice.addItem("High to Low");

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fuel Type");
		lblNewLabel_1_1_1_1.setToolTipText("");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(995, 187, 134, 36);
		panel.add(lblNewLabel_1_1_1_1);

		comboBoxFuelType = new JComboBox<>();
		comboBoxFuelType.setBackground(new Color(243, 219, 50));
		comboBoxFuelType.setBounds(1087, 187, 169, 36);
		panel.add(comboBoxFuelType);
		initializeComboBox(comboBoxFuelType, "Fuel Type");
		comboBoxFuelType.addItem("Petrol");
		comboBoxFuelType.addItem("Diesel");

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(243, 219, 50));
		panel_1.setBounds(1, 1, 1264, 671);
		frame.getContentPane().add(panel_1);

		JScrollPane scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(0, 241, 1266, 442);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Add your JScrollPane to the frame's content pane
		frame.getContentPane().add(scrollPane);

		comboBoxPassengers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterVehicles();
			}
		});

		comboBoxGearType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterVehicles();
			}
		});

		comboBoxPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterVehicles();
			}
		});

		comboBoxFuelType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterVehicles();
			}
		});

		displayAllVehicles();
	}
	

	private void initializeComboBox(JComboBox<String> comboBox, String title) {
		comboBox.addItem(" ");
		comboBox.setBorder(null);
		comboBox.setToolTipText("");
		comboBox.addItemListener(null);
		comboBox.setBackground(new Color(243, 219, 50));
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setToolTipText(title);
	}

	private void updateVehicleType(String vehicleType) {
		selectedVehicleType = vehicleType;
		filterVehicles();
	}

	private void filterVehicles() {
		selectedPassengers = (String) comboBoxPassengers.getSelectedItem();
		selectedGearType = (String) comboBoxGearType.getSelectedItem();
		selectedPrice = (String) comboBoxPrice.getSelectedItem();
		selectedFuelType = (String) comboBoxFuelType.getSelectedItem();
		displayVehicles(selectedPassengers, selectedGearType, selectedPrice, selectedFuelType);
	}

	private void displayVehicles(String passengers, String gearType, String price, String fuelType) {
		panel_1.removeAll();
		Connection connection = DatabaseConnection.getConnection();
		try {
			StringBuilder queryBuilder = new StringBuilder("SELECT * FROM vehicle_specifications");
			boolean whereAdded = false;
			if (selectedVehicleType != null && !selectedVehicleType.isEmpty()) {
				queryBuilder.append(" WHERE vehi_type = ?");
				whereAdded = true;
			}
			if (!passengers.equals(" ")) {
				if (passengers.equals("more")) {
					if (whereAdded) {
						queryBuilder.append(" AND vehi_passengers > ?");
						// Adjust filter for more than 5 passengers
					} else {
						queryBuilder.append(" WHERE vehi_passengers > ?");

						whereAdded = true;
					}
					passengers = "5";
				} else {
					if (whereAdded) {
						queryBuilder.append(" AND vehi_passengers = ?");
					} else {
						queryBuilder.append(" WHERE vehi_passengers = ?");
						whereAdded = true;
					}
				}

			}
			if (!gearType.equals(" ")) {
				if (whereAdded) {
					queryBuilder.append(" AND vehi_gearbox = ?");
				} else {
					queryBuilder.append(" WHERE vehi_gearbox = ?");
					whereAdded = true;
				}
			}

			if (!fuelType.equals(" ")) {
				if (whereAdded) {
					queryBuilder.append(" AND vehi_fuel = ?");
				} else {
					queryBuilder.append(" WHERE vehi_fuel = ?");
					whereAdded = true;
				}
			}
			if (!price.equals(" ")) {
				if (whereAdded) {
					queryBuilder.append(" ORDER BY vehi_price "); // Order by price
					if (price.equals("Low to High")) {
						queryBuilder.append(" ASC");
					} else if (price.equals("High to Low")) {
						queryBuilder.append(" DESC");
					}
				} else {
					queryBuilder.append(" ORDER BY vehi_price "); // Order by price
					if (price.equals("Low to High")) {
						queryBuilder.append(" ASC");
					} else if (price.equals("High to Low")) {
						queryBuilder.append(" DESC");
					}
				}
			}
			// Add conditions for price and fuel type similarly if needed
			PreparedStatement ps = connection.prepareStatement(queryBuilder.toString());
			if (whereAdded) {
				if (selectedVehicleType != null && !selectedVehicleType.isEmpty()) {
					// Construct the query with WHERE clause
					ps = connection.prepareStatement(queryBuilder.toString());
					ps.setString(1, selectedVehicleType);
					int parameterIndex = 2; // Start index for parameters
					if (!passengers.equals(" ")) {
						ps.setString(parameterIndex++, passengers);
					}
					if (!gearType.equals(" ")) {
						ps.setString(parameterIndex++, gearType);
					}

					if (!fuelType.equals(" ")) {
						ps.setString(parameterIndex++, fuelType);
					}
				} else {
					// Construct the query without WHERE clause
					ps = connection.prepareStatement(queryBuilder.toString());
					int parameterIndex = 1; // Start index for parameters
					if (!passengers.equals(" ")) {
						ps.setString(parameterIndex++, passengers);
					}
					if (!gearType.equals(" ")) {
						ps.setString(parameterIndex++, gearType);
					}

					if (!fuelType.equals(" ")) {
						ps.setString(parameterIndex++, fuelType);
					}
				}
			}

			// Set parameters for price and fuel type similarly if needed
			ResultSet rs = ps.executeQuery();

			int x = 271;
			int y = 23;
			int row = 0;

			while (rs.next()) {
				int vehiID = rs.getInt("vehi_id");
				int passengers1 = rs.getInt("vehi_passengers");
				String gear = rs.getString("vehi_gearbox");
				String fuel = rs.getString("vehi_fuel");
				double price1 = rs.getDouble("vehi_price");

				List<ImageIcon> vehicleImages = new ArrayList<>(); // Temporary list to store images for the current
				// vehicle
				PreparedStatement imageStatement = connection
						.prepareStatement("SELECT image FROM vehicle_images WHERE vehicle_id = ?");
				imageStatement.setInt(1, vehiID);
				ResultSet imageResultSet = imageStatement.executeQuery();

				while (imageResultSet.next()) {
					Blob imageBlob = imageResultSet.getBlob("image");
					InputStream imageStream = imageBlob.getBinaryStream();
					BufferedImage vehicleImage = ImageIO.read(imageStream);
					ImageIcon imageIcon = new ImageIcon(vehicleImage.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
					vehicleImages.add(imageIcon);
				}

				vehicleImageMap.put(vehiID, vehicleImages);
				JLabel lblVehicleImage;

				if (vehicleImages.isEmpty()) {
					lblVehicleImage = new JLabel("No Image Available");
					lblVehicleImage.setHorizontalAlignment(SwingConstants.CENTER);
					lblVehicleImage.setBounds(x - 205, y - 13, 150, 150);
					panel_1.add(lblVehicleImage);
				} else {
					lblVehicleImage = new JLabel(vehicleImages.get(0));
					lblVehicleImage.setBounds(x - 205, y - 13, 150, 150);
					panel_1.add(lblVehicleImage);
				}
// Add a button for cycling through images
				JButton btnNextImage = new JButton("Next Image");

// Add action listener for cycling images
				if (vehicleImages.size() > 1) {

					btnNextImage.setForeground(new Color(6, 6, 6));
					btnNextImage.setFont(new Font("Tahoma", Font.PLAIN, 20));
					btnNextImage.setBackground(new Color(255, 255, 255));
					btnNextImage.setBounds(x + 160, y + 136, 150, 32);
					panel_1.add(btnNextImage);

// Add action listener for cycling images
					int finalVehiID = vehiID;
					btnNextImage.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							List<ImageIcon> images = vehicleImageMap.get(finalVehiID);
							if (images != null && !images.isEmpty()) {
								currentImageIndex = (currentImageIndex + 1) % images.size();
// Update the JLabel displaying the vehicle image with the next image from the list
								lblVehicleImage.setIcon(images.get(currentImageIndex));
// Repaint the panel to reflect the updated image
								panel_1.repaint();
							}
						}
					});
				} else {
// Disable the button if there is only one image
					btnNextImage.setEnabled(false);
				}

				JLabel lblPassenger = new JLabel("Number of passengers: " + passengers1);
				lblPassenger.setForeground(new Color(6, 6, 6));
				lblPassenger.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPassenger.setBounds(x, y, 281, 53);
				panel_1.add(lblPassenger);

				JLabel lblGearType = new JLabel("Gear Type: " + gear);
				lblGearType.setForeground(new Color(6, 6, 6));
				lblGearType.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblGearType.setBounds(x, y + 32, 281, 53);
				panel_1.add(lblGearType);

				JLabel lblPricePerDay = new JLabel("Price per day: " + price1);
				lblPricePerDay.setForeground(new Color(6, 6, 6));
				lblPricePerDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPricePerDay.setBounds(x, y + 64, 281, 53);
				panel_1.add(lblPricePerDay);

				JLabel lblFuelType = new JLabel("Fuel Type: " + fuel);
				lblFuelType.setForeground(new Color(6, 6, 6));
				lblFuelType.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblFuelType.setBounds(x, y + 96, 281, 53);
				panel_1.add(lblFuelType);

				JButton btnReserve = new JButton("Reserve");
				btnReserve.setForeground(new Color(6, 6, 6));
				btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnReserve.setBackground(new Color(255, 255, 255));
				btnReserve.setBounds(x, y + 136, 129, 32);
				panel_1.add(btnReserve);

				btnReserve.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reservationInfo.main(new String[] { Integer.toString(vehiID) });
						frame.dispose();
					}
				});

				row++; // Increment the row counter

				// Adjust the positions for the next set of vehicle details
				if (row % 2 == 0) { // Start a new row after every two vehicles
					x = 271; // Reset X position for the first column
					y += 210; // Move to the next row
				} else {
					x += 595; // Move to the next column
				}
				int panelHeight = Math.max(y + 210, 671); // Ensure the panel is at least 671 pixels in height
				panel_1.setPreferredSize(new Dimension(1264, panelHeight));
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		panel_1.revalidate();
		panel_1.repaint();
	}

	private void displayAllVehicles() {
		System.out.println("Displaying all vehicles...");
		displayVehicles(" ", " ", " ", " ");
	}
}