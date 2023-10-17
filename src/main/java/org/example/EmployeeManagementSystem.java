package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeManagementSystem extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel employeeTableModel;

    private Connection connection;
    private Statement statement;

    public EmployeeManagementSystem() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Connect to the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           String url = "jdbc:mysql://containers-us-west-89.railway.app:5970/railway";
            String MYSQLUSER = "root";
            String MYSQLPASSWORD = "MK9hQNvAibnbFC7rWbg8";

            connection = DriverManager.getConnection(url, MYSQLUSER, MYSQLPASSWORD);
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
            System.exit(1);
        }

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Create the employee table
        employeeTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Last Name", "Sex", "Phone", "Birth Date", "Document Type"}, 0);
        employeeTable = new JTable(employeeTableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the add employee button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(e -> addEmployee());
        mainPanel.add(addEmployeeButton, BorderLayout.SOUTH);

        // Load the employee data into the table
        loadEmployees();

        // Set the main window visible
        setVisible(true);
    }

    private void loadEmployees() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleado");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_EMPLEADO");
                String name = resultSet.getString("NOMBRE");
                String lastName = resultSet.getString("APELLIDOS");
                String sex = resultSet.getString("SEXO");
                String phone = resultSet.getString("TELEFONO");
                Date birthDate = resultSet.getDate("FECHANACIMIENTO");
                String documentType = resultSet.getString("TIPDOCUMENTO");
                // EL ID_USUARIO GENERAR AUTOMATICAMENTE DIFEERENTE DE 1 Y 2




                employeeTableModel.addRow(new Object[]{id, name, lastName, sex, phone, birthDate, documentType});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load employee data.");
        }
    }

    private void addEmployee() {
        // Create the add employee dialog
        JDialog addEmployeeDialog = new JDialog(this, "Add Employee", true);
        addEmployeeDialog.setSize(400, 300);
        addEmployeeDialog.setLocationRelativeTo(this);

        // Create the employee form
        JPanel employeeForm = new JPanel();
        employeeForm.setLayout(new GridLayout(7, 2));
        employeeForm.add(new JLabel("Name:"));
        employeeForm.add(new JTextField());
        employeeForm.add(new JLabel("Last Name:"));
        employeeForm.add(new JTextField());
        employeeForm.add(new JLabel("Sex:"));
        employeeForm.add(new JTextField());
        employeeForm.add(new JLabel("Phone:"));
        employeeForm.add(new JTextField());
        employeeForm.add(new JLabel("Birth Date:"));
        employeeForm.add(new JTextField());
        employeeForm.add(new JLabel("Document Type:"));
        employeeForm.add(new JTextField());
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Get the employee data from the form
            String name = ((JTextField) employeeForm.getComponent(1)).getText();
            String lastName = ((JTextField) employeeForm.getComponent(3)).getText();
            String sex = ((JTextField) employeeForm.getComponent(5)).getText();
            String phone = ((JTextField) employeeForm.getComponent(7)).getText();
            String birthDate = ((JTextField) employeeForm.getComponent(9)).getText();
            String documentType = ((JTextField) employeeForm.getComponent(11)).getText();

            // Insert the employee data into the database
            try {
                statement.executeUpdate("INSERT INTO empleado (NOMBRE, APELLIDOS, SEXO, TELEFONO, FECHANACIMIENTO, TIPDOCUMENTO) VALUES ('" + name + "', '" + lastName + "', '" + sex + "', '" + phone + "', '" + birthDate + "', '" + documentType + "')");
                JOptionPane.showMessageDialog(addEmployeeDialog, "Employee added successfully.");
                addEmployeeDialog.dispose();
                loadEmployees();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addEmployeeDialog, "Failed to add employee.");
            }
        });
        employeeForm.add(addButton);
        addEmployeeDialog.add(employeeForm);

        // Show the add employee dialog
        addEmployeeDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Employee();
    }
}