package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class RegistroForm extends JFrame implements ActionListener {

    private static final String URL = "jdbc:mysql://containers-us-west-89.railway.app:5970/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "MK9hQNvAibnbFC7rWbg8";


    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 500;


    // Components
    private JPanel panel;
    private JLabel titleLabel, nameLabel, lastNameLabel, cargoLabel;
    private JTextField nameField, lastNameField;
    private JComboBox<String>  cargoComboBox;
    private JButton saveButton, regresarButton;

    // Constructor
    public RegistroForm() {

        super("Resgistro");

        // Set up the panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Set up the title label
        titleLabel = new JLabel("Add User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(50, 20, 300, 30);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        // Set up the name label and field
        nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(50, 70, 100, 30);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 30);
        panel.add(nameField);

        // Set up the last name label and field
        lastNameLabel = new JLabel("Clave:");
        lastNameLabel.setBounds(50, 120, 100, 30);
        panel.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(150, 120, 200, 30);
        panel.add(lastNameField);



        // Set up the cargo label and combo box
        cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(50, 170, 100, 30);
        panel.add(cargoLabel);
        cargoComboBox = new JComboBox<>(new String[]{"admin", "empleado"});
        cargoComboBox.setBounds(150, 170, 200, 30);
        panel.add(cargoComboBox);

        // Set up the save button
        saveButton = new JButton("Save");
        saveButton.setBounds(80, 270, 100, 30);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        // Set up the regresar button
        regresarButton = new JButton("Regresar");
        // alejar mas a la derecha
        regresarButton.setBounds(210, 270, 100, 30);
        regresarButton.addActionListener(this);
        panel.add(regresarButton);


        // Set up the frame
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Load the cargos from the database into the combo box
        // mi base de datos
        /*
        * TABLE `cargo` (
                `ID_CARGO` int PRIMARY KEY NOT NULL AUTO_INCREMENT ,
                `NOMBRECARGO` varchar(200),
                `ESTADO` bit(1)
                );
        * */
        //loadCargos();
    }
/*
    private void loadCargos() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cargo");

            while (rs.next()) {
                String cargo = rs.getString("NOMBRECARGO");
                cargoComboBox.addItem(cargo);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/

    // ActionListener method
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() == regresarButton) {

                LoginRegistrationForm loginRegistrationForm = new LoginRegistrationForm();
                loginRegistrationForm.setVisible(true);

                dispose();
            }


        if (e.getSource() == saveButton) {
            // Save the employee to the database
            String nombre = nameField.getText();
            String clave = lastNameField.getText();
            String cargo = (String) cargoComboBox.getSelectedItem();

            if (nombre.equals("") || clave.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter a name and password.");
                return;
            }

            try {

                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = null;

                if (cargo.equals("admin")) {
                    stmt = conn.prepareStatement("INSERT INTO usuario (NOMBREUSUARIO, CLAVE, ID_CARGO) VALUES (?, ?, 1)");
                    stmt.setString(1, nombre);
                    stmt.setString(2, clave);
                    stmt.executeUpdate();
                    conn.close();
                } else if (cargo.equals("empleado")) {
                    stmt = conn.prepareStatement("INSERT INTO usuario (NOMBREUSUARIO, CLAVE, ID_CARGO) VALUES (?, ?, 2)");
                    stmt.setString(1, nombre);
                    stmt.setString(2, clave);
                    stmt.executeUpdate();
                    conn.close();
                }

                JOptionPane.showMessageDialog(panel, "Employee saved successfully!");

                nameField.setText("");
                lastNameField.setText("");


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    public static void main(String[] args) {
        new RegistroForm();
    }
}
