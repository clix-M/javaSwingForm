package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LoginRegistrationForm extends JFrame implements ActionListener {
    // Global variables
    private static final String URL = "jdbc:mysql://containers-us-west-81.railway.app:6154/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "9kFumuSs26Xy8oU8B34l";
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;

    // Components
    private JPanel panel;
    private JLabel titleLabel, emailLabel, passwordLabel, confirmPasswordLabel;
    private JTextField emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton loginButton, registerButton;

    // Constructor
    public LoginRegistrationForm() {

        super("Login or Register");
        // icon -> src/img/online-survey.png
        String iconURL = "src/img/online-survey.png";
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        // Set up the panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Set up the title label
        titleLabel = new JLabel("Login or Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        // Set up the email label and field
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        panel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        panel.add(emailField);

        // Set up the password label and field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 30);


        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);
        panel.add(passwordField);

        // Set up the confirm password label and field
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 200, 100, 30);
        panel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 200, 200, 30);
        panel.add(confirmPasswordField);

        // Set up the login and register buttons
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 250, 100, 30);
        loginButton.setBackground(Color.decode("#00b894"));

        loginButton.addActionListener(this);
        panel.add(loginButton);
        registerButton = new JButton("Register");
        registerButton.setBounds(250, 250, 100, 30);
        registerButton.setBackground(Color.decode("#00b894"));
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // Set up the frame
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ActionListener method
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Validate the credentials of the user and show an error alert if they are incorrect

            if (emailField.getText().equals("") || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(panel, "Please enter an email and password.");
                return;
            }

            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter an email and password.");
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(panel, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid email or password.");
                }
                conn.close();
                // al cerrar que el color de fondo sea normal







            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == registerButton) {
            // Validate the data of the user and create a new account in the database
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(panel, "Passwords do not match.");
            } else {
                try {
                    if (email.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter an email and password.");
                        return;
                    }

                    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='" + email + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(panel, "Email already exists.");
                    } else {
                        stmt.executeUpdate("INSERT INTO users (email, password) VALUES ('" + email + "', '" + password + "')");
                        JOptionPane.showMessageDialog(panel, "Registration successful!");
                    }
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        new LoginRegistrationForm();
    }
}
