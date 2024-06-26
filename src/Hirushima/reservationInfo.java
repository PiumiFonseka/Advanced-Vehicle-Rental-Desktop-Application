//reservation info
package Hirushima;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;

public class reservationInfo {

    private JFrame frame;
    private JLabel availabilityLabel;
    private JXDatePicker pick_date;
    private JXDatePicker return_date;
    private JTextField pick_time;
    private JTextField return_time;
    private int vehicleId;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        if(args.length > 0) {
            int vehicleId = Integer.parseInt(args[0]); // Parse the first argument as an integer
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        reservationInfo window = new reservationInfo(vehicleId);
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



    /**
     * Create the application.
     */
    public reservationInfo(int vehicleId) {
    	this.vehicleId = vehicleId;
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

        JLabel lblNewLabel = new JLabel("RESERVATIONS");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 55));
        lblNewLabel.setBounds(445, 11, 438, 96);
        frame.getContentPane().add(lblNewLabel);

        JLabel p_date = new JLabel("Pickup date:");
        p_date.setFont(new Font("Tahoma", Font.PLAIN, 25));
        p_date.setBounds(184, 236, 144, 37);
        frame.getContentPane().add(p_date);

        JLabel p_time = new JLabel("Pickup time:");
        p_time.setFont(new Font("Tahoma", Font.PLAIN, 25));
        p_time.setBounds(737, 236, 144, 37);
        frame.getContentPane().add(p_time);

        JLabel r_date = new JLabel("Return date:");
        r_date.setFont(new Font("Tahoma", Font.PLAIN, 25));
        r_date.setBounds(184, 323, 144, 37);
        frame.getContentPane().add(r_date);
		
		return_date = new JXDatePicker();
		return_date.setBounds(367, 323, 212, 34);
		frame.getContentPane().add(return_date);
		
		pick_date = new JXDatePicker();
		pick_date.setBounds(367, 242, 212, 34);
		frame.getContentPane().add(pick_date);

        JLabel r_time = new JLabel("Return time:");
        r_time.setFont(new Font("Tahoma", Font.PLAIN, 25));
        r_time.setBounds(737, 323, 144, 37);
        frame.getContentPane().add(r_time);

        JLabel v_type = new JLabel("Vehicle ID:");
        v_type.setFont(new Font("Tahoma", Font.PLAIN, 25));
        v_type.setBounds(457, 401, 144, 43);
        frame.getContentPane().add(v_type);
        
        
        JFormattedTextField vehid = new JFormattedTextField();
        vehid.setBounds(611, 410, 212, 34);
        frame.getContentPane().add(vehid);
        vehid.setText(String.valueOf(vehicleId));

        JButton checkAvailabilityButton = new JButton("Search here..");
        checkAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Extract input values
                String pickupDate = formatDate(pick_date.getDate());
                String pickupTime = pick_time.getText();
                String returnDate = formatDate(return_date.getDate());
                String returnTime = return_time.getText();
              int vehicleID = vehicleId;
                
                // Check availability
                checkAvailability(pickupDate, pickupTime, returnDate, returnTime, vehicleID);
            }
        });
        checkAvailabilityButton.setBackground(new Color(192, 192, 192));
        checkAvailabilityButton.setFont(new Font("Sitka Banner", Font.ITALIC, 18));
        checkAvailabilityButton.setBounds(786, 478, 172, 34);
        frame.getContentPane().add(checkAvailabilityButton);

        availabilityLabel = new JLabel("");
        availabilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        availabilityLabel.setBounds(184, 550, 800, 37);
        frame.getContentPane().add(availabilityLabel);
        
        pick_time = new JTextField();
        pick_time.setBounds(908, 243, 212, 34);
        frame.getContentPane().add(pick_time);
        pick_time.setColumns(10);
        
        return_time = new JTextField();
        return_time.setBounds(908, 323, 212, 34);
        frame.getContentPane().add(return_time);
        return_time.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(134, 183, 1038, 412);
        frame.getContentPane().add(panel_1);
        
        JLabel back = new JLabel("Back");
        back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Hiruni.VehicleSelection.main(new String[] {});

			}
		});
        back.setFont(new Font("Tahoma", Font.PLAIN, 18));
        back.setBounds(23, 11, 58, 19);
        frame.getContentPane().add(back);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(243, 219, 50));
        panel.setBounds(0, 0, 1264, 107);
        frame.getContentPane().add(panel);
    }

    private void checkAvailability(String pickupDate, String pickupTime, String returnDate, String returnTime, int vehicleID) {
        try {
            // Format pickup and return date time strings
            String pickupDateTime = pickupDate + " " + pickupTime;
            String returnDateTime = returnDate + " " + returnTime;

            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Query to check for overlapping reservations
                String reservationQuery = "SELECT * FROM reservations WHERE "
                             + "((pick_date <= ? AND return_date >= ?) OR "
                             + "(pick_date >= ? AND pick_date <= ?) OR "
                             + "(return_date >= ? AND return_date <= ?)) "
                             + "AND vehi_id = ?";
                PreparedStatement reservationStatement = connection.prepareStatement(reservationQuery);
                reservationStatement.setString(1, pickupDateTime);
                reservationStatement.setString(2, pickupDateTime);
                reservationStatement.setString(3, returnDateTime);
                reservationStatement.setString(4, returnDateTime);
                reservationStatement.setString(5, pickupDateTime);
                reservationStatement.setString(6, returnDateTime);
                reservationStatement.setInt(7, vehicleID);

                ResultSet reservationResultSet = reservationStatement.executeQuery();

                // Check if the vehicle is reserved at the specified date and time
                if (reservationResultSet.next()) {
                    JOptionPane.showMessageDialog(frame, "Vehicle is already reserved at the specified date and time.");
                } else {
                    // Query to check for maintenance bookings
                	String maintenanceQuery = "SELECT v.vehi_id FROM vehicle_specifications v INNER JOIN vehicle_maintenance m ON v.vehi_reg_no = m.m_vehi_reg_no WHERE m.m_vehi_reg_no = ? AND m.m_date = ?";
                    PreparedStatement maintenanceStatement = connection.prepareStatement(maintenanceQuery);
                    maintenanceStatement.setInt(1, vehicleID);
                    maintenanceStatement.setString(2, pickupDate);

                    ResultSet maintenanceResultSet = maintenanceStatement.executeQuery();

                    // Check if maintenance is scheduled for the specified date
                    if (maintenanceResultSet.next()) {
                        JOptionPane.showMessageDialog(frame, "Maintenance is scheduled for the specified date.");
                    } else {
                        // If the vehicle is not reserved and maintenance is not scheduled, open the reservation form
                        frame.setVisible(false); // Hide current frame
                        reservationForm reservationForm = new reservationForm();
                        reservationForm.setVisible(true); // Show reservation form
                    }

                    // Close maintenance ResultSet and PreparedStatement
                    maintenanceResultSet.close();
                    maintenanceStatement.close();
                }

                // Close reservation ResultSet and PreparedStatement
                reservationResultSet.close();
                reservationStatement.close();

                // Close Connection
                connection.close();
            }
        } catch (SQLException e) {
            availabilityLabel.setText("Error checking availability: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    
 // method to change date format 
 	private String formatDate(Date date) {
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		return sdf.format(date);
 	}

	public void setVisible(boolean b) {
		frame.setVisible(b);	
	}
}