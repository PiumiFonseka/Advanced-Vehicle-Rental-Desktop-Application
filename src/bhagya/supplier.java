package bhagya;

import java.awt.EventQueue;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class supplier {

    JFrame frame;
    private JTable table_2;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    supplier window = new supplier();
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
    public supplier() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        frame.setBounds(100, 100, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("SUPPLIER PROFILE");
        lblNewLabel.setBounds(389, 75, 583, 47);
        lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 50));
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setBounds(10, 10, 85, 21);
        frame.getContentPane().add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 350, 979, 193);
        frame.getContentPane().add(scrollPane);

        table_2 = new JTable();
        scrollPane.setViewportView(table_2);
        table_2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"SupplierID", "SupplierName", "NIC", "ContactNo", "Email", "vehicleName", "vehicleType", "RegNo", "SupplierPay"}
        ));

        textField_1 = new JTextField();
        textField_1.setBounds(500, 215, 204, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1_4 = new JButton("search");
        btnNewButton_1_4.setBackground(new Color(255, 255, 0));
        btnNewButton_1_4.setBounds(500, 258, 196, 36);
        frame.getContentPane().add(btnNewButton_1_4);

        JLabel lblNewLabel_1 = new JLabel("search supplier");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(526, 168, 196, 36);
        frame.getContentPane().add(lblNewLabel_1);

        btnNewButton_1_4.addActionListener(new ActionListener() {
            private DefaultTableModel tableModel;

            @Override
            public void actionPerformed(ActionEvent e) {
                searchDatabase();
            }

            private void searchDatabase() {
                String searchTextSupplierID = textField_1.getText().trim();

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
                    String query = "SELECT * FROM suppi WHERE SupplierID = ?";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, searchTextSupplierID);
                    ResultSet resultSet = stmt.executeQuery();

                    // Clear existing table data
                    tableModel = (DefaultTableModel) table_2.getModel();
                    tableModel.setRowCount(0);

                    while (resultSet.next()) {
                        String SupplierID = resultSet.getString("SupplierID");
                        String SupplierName = resultSet.getString("SupplierName");
                        String NIC = resultSet.getString("NIC");
                        String ContactNo = resultSet.getString("ContactNo");
                        String Email = resultSet.getString("Email");
                        String vehicleName = resultSet.getString("vehicleName");
                        String vehicleType = resultSet.getString("vehicleType");
                        String RegNo = resultSet.getString("RegNo");
                        String SupplierPay = resultSet.getString("SupplierPay");

                        // Add the retrieved data to the table model
                        tableModel.addRow(new Object[]{SupplierID, SupplierName, NIC, ContactNo, Email, vehicleName, vehicleType, RegNo, SupplierPay});
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton btnNewButton_1 = new JButton("Print supplier details");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1.setBackground(new Color(255, 255, 0));
        btnNewButton_1.setBounds(475, 570, 291, 21);
        frame.getContentPane().add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printSupplierDetails();
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supinformation supplierPage = new supinformation();
                supplierPage.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    // Method to print supplier details as PDF
    private void printSupplierDetails() {
        String supplierID = textField_1.getText().trim();

        if (supplierID.isEmpty()) {
            // Handle empty input if needed
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vassenterprises", "root", "")) {
            String query = "SELECT * FROM suppi WHERE SupplierID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, supplierID);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String supplierName = resultSet.getString("SupplierName");
                String nic = resultSet.getString("NIC");
                String contactNo = resultSet.getString("ContactNo");
                String email = resultSet.getString("Email");
                String vehicleName = resultSet.getString("vehicleName");
                String vehicleType = resultSet.getString("vehicleType");
                String regNo = resultSet.getString("RegNo");
                String supplierPay = resultSet.getString("SupplierPay");

                // Create PDF document
                Document document = new Document();
                try {
                    // Set the file path for saving the PDF
                    String filePath = "supplier_details.pdf";
                    com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    // Add content to the PDF
                    com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
                    Paragraph para = new Paragraph("Supplier Details\n\n");
                    para.add(new Chunk("Supplier ID: " + supplierID + "\n"));
                    para.add(new Chunk("Supplier Name: " + supplierName + "\n"));
                    para.add(new Chunk("NIC: " + nic + "\n"));
                    para.add(new Chunk("Contact No: " + contactNo + "\n"));
                    para.add(new Chunk("Email: " + email + "\n"));
                    para.add(new Chunk("Vehicle Name: " + vehicleName + "\n"));
                    para.add(new Chunk("Vehicle Type: " + vehicleType + "\n"));
                    para.add(new Chunk("Reg No: " + regNo + "\n"));
                    para.add(new Chunk("Supplier Pay: " + supplierPay + "\n\n"));
                    document.add(para);

                    document.close();

                    // Open the PDF file after creation
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File(filePath));
                    } else {
                        System.out.println("Desktop not supported.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}