package Hiruni;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MaintenanceTotCost {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JComboBox<String> comboBox;
	private Connection connection;
	private PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaintenanceTotCost window = new MaintenanceTotCost();
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
	public MaintenanceTotCost() {
		initialize();
		populateComboBox();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null); // This will center the frame on the screen

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(6, 6, 6));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.WHITE);
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

		JLabel lblVehicleInventory = new JLabel("Maintenance Total Cost");
		lblVehicleInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleInventory.setForeground(new Color(243, 219, 50));
		lblVehicleInventory.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblVehicleInventory.setBackground(new Color(114, 224, 239));
		lblVehicleInventory.setBounds(225, 10, 813, 61);
		panel.add(lblVehicleInventory);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(243, 219, 50));
		panel_3.setBounds(0, 81, 1266, 602);
		panel.add(panel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1266, 500);
		panel_3.add(panel_1);

		comboBox = new JComboBox<>();
		comboBox.setMaximumRowCount(100);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox.setBackground(new Color(243, 219, 50));
		comboBox.setBounds(205, 37, 246, 39);
		panel_1.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedID = (String) comboBox.getSelectedItem();
				readVehicleDetails(selectedID);
				readVehicleMaintenanceDetails(selectedID);
				calculateTotalCost(selectedID);
			}
		});

		JLabel lblNewLabel_1_1 = new JLabel("Reg No");
		lblNewLabel_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(35, 34, 154, 44);
		panel_1.add(lblNewLabel_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(523, 37, 712, 322);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Vehicle ID", "Date", "Service Center Name", "Price" }));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1_1_1 = new JLabel("Brand");
		lblNewLabel_1_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(37, 178, 73, 44);
		panel_1.add(lblNewLabel_1_1_1);

		textField_1 = new JTextField();
		textField_1.setForeground(new Color(6, 6, 6));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(243, 219, 50));
		textField_1.setBounds(205, 181, 245, 40);
		panel_1.add(textField_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Colour");
		lblNewLabel_1_1_2.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_2.setBounds(37, 244, 73, 44);
		panel_1.add(lblNewLabel_1_1_2);

		textField_2 = new JTextField();
		textField_2.setForeground(new Color(6, 6, 6));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(243, 219, 50));
		textField_2.setBounds(205, 247, 245, 40);
		panel_1.add(textField_2);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fuel");
		lblNewLabel_1_1_1_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1_1.setBounds(37, 308, 73, 44);
		panel_1.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Gear Box");
		lblNewLabel_1_1_3_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_3_1.setBounds(37, 372, 144, 44);
		panel_1.add(lblNewLabel_1_1_3_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Reg No");
		lblNewLabel_1_1_2_1.setForeground(new Color(6, 6, 6));
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_2_1.setBounds(35, 117, 192, 44);
		panel_1.add(lblNewLabel_1_1_2_1);

		textField_3 = new JTextField();
		textField_3.setForeground(new Color(6, 6, 6));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(243, 219, 50));
		textField_3.setBounds(205, 120, 245, 40);
		panel_1.add(textField_3);

		textField = new JTextField();
		textField.setForeground(new Color(6, 6, 6));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setColumns(10);
		textField.setBackground(new Color(243, 219, 50));
		textField.setBounds(205, 312, 245, 40);
		panel_1.add(textField);

		textField_4 = new JTextField();
		textField_4.setForeground(new Color(6, 6, 6));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(243, 219, 50));
		textField_4.setBounds(205, 372, 245, 40);
		panel_1.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setForeground(new Color(6, 6, 6));
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(243, 219, 50));
		textField_5.setBounds(990, 403, 245, 40);
		panel_1.add(textField_5);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(128, 128, 128));
		panel_3_1.setBounds(489, 0, 777, 500);
		panel_1.add(panel_3_1);

		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(1204, 10, 33, 36);
		panel_3_1.add(lblNewLabel_2);
		
				JLabel lblNewLabel_1_1_3_1_1 = new JLabel("Total Cost Rs.");
				lblNewLabel_1_1_3_1_1.setBounds(328, 400, 169, 44);
				panel_3_1.add(lblNewLabel_1_1_3_1_1);
				lblNewLabel_1_1_3_1_1.setBackground(new Color(0, 0, 0));
				lblNewLabel_1_1_3_1_1.setForeground(new Color(0, 0, 0));
				lblNewLabel_1_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));

		JButton btnPrintMaintanenceDetails = new JButton("Print Report");
		btnPrintMaintanenceDetails.setBounds(36, 530, 267, 44);
		panel_3.add(btnPrintMaintanenceDetails);
		btnPrintMaintanenceDetails.setForeground(new Color(6, 6, 6));
		btnPrintMaintanenceDetails.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnPrintMaintanenceDetails.setBackground(Color.WHITE);

		JButton btnBack = new JButton("Exit");
		btnBack.setForeground(new Color(6, 6, 6));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(1060, 530, 178, 44);
		panel_3.add(btnBack);

		// Button for 'Exit' with an action
		btnBack.addMouseListener(new MouseAdapter() {
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VehicleMaintenance.main(new String[] {}); // Open the 'Dashboard' window
			}
		});
		btnPrintMaintanenceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePDFReport();
			}
		});
	}

	private void populateComboBox() {
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "SELECT vehi_reg_no FROM vehicle_specifications";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString("vehi_reg_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readVehicleDetails(String vehicleID) {
		try {
			String sql = "SELECT * FROM vehicle_specifications WHERE vehi_reg_no =?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, vehicleID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				// Set text fields with retrieved data
				textField_1.setText(rs.getString("vehi_brand"));
				textField_2.setText(rs.getString("vehi_colour"));
				textField_3.setText(rs.getString("vehi_reg_no"));
				textField_4.setText(rs.getString("vehi_gearbox"));
				textField.setText(rs.getString("vehi_fuel"));
				// You can similarly set other text fields
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readVehicleMaintenanceDetails(String selectedID) {
		try {
			String sql = "SELECT * FROM vehicle_maintenance WHERE m_vehi_reg_no = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, selectedID);
			ResultSet rs = pst.executeQuery();

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); // Clear the table before adding new rows

			while (rs.next()) {
				String id = rs.getString("m_vehi_reg_no");
				String date = rs.getString("m_date");
				String serviceCenter = rs.getString("m_servicecenter");
				String price = rs.getString("m_price");

				// Add row to the table
				model.addRow(new Object[] { id, selectedID, date, serviceCenter, price });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void calculateTotalCost(String selectedID) {

		try {
			String sql = "SELECT SUM(m_price) AS total_cost FROM vehicle_maintenance WHERE m_vehi_reg_no = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, selectedID);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String totalCost = rs.getString("total_cost");
				textField_5.setText(totalCost);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generatePDFReport() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showSaveDialog(frame);
		if (result != JFileChooser.APPROVE_OPTION) {
			return; // Exit if the user cancels the operation
		}

		File desktopDirectory = fileChooser.getSelectedFile();
		String desktopPath = desktopDirectory.getAbsolutePath();

		Document document = new Document();
		try {
			String filePath = desktopPath + File.separator + "Maintenance_Report.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			Paragraph title = new Paragraph("Maintenance Report");
			title.setAlignment(Paragraph.ALIGN_CENTER);
			title.setSpacingAfter(20);
			document.add(title);

			PdfPTable pdfTable = new PdfPTable(5);
			pdfTable.setWidthPercentage(100);
			pdfTable.setSpacingAfter(10);

			float[] columnWidths = { 2f, 3f, 3f, 3f, 2f };
			pdfTable.setWidths(columnWidths);

			// Add table headers
			addPdfTableCell(pdfTable, "ID");
			addPdfTableCell(pdfTable, "Vehicle ID");
			addPdfTableCell(pdfTable, "Date");
			addPdfTableCell(pdfTable, "Service Center Name");
			addPdfTableCell(pdfTable, "Price");

			// Add data rows from the table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				addPdfTableCell(pdfTable, model.getValueAt(i, 0).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 1).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 2).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 3).toString());
				addPdfTableCell(pdfTable, model.getValueAt(i, 4).toString());
			}

			document.add(pdfTable);

			// Add total cost
			Paragraph totalCostParagraph = new Paragraph("Total Cost: Rs." + textField_5.getText());
			totalCostParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(totalCostParagraph);

			document.close();
			JOptionPane.showMessageDialog(frame, "PDF Report generated successfully!");

			// Open the generated PDF file after creation
			File pdfFile = new File(filePath);
			if (pdfFile.exists()) {
				Desktop.getDesktop().open(pdfFile);
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace(); // Print any exceptions that occur
		}
	}

	private void addPdfTableCell(PdfPTable table, String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}
}
