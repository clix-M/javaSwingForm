package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginRegistrationForm extends JFrame implements ActionListener {

   // mysql://root:MK9hQNvAibnbFC7rWbg8@containers-us-west-89.railway.app:5970/railway
    // Global variables
    private static final String URL = "jdbc:mysql://containers-us-west-89.railway.app:5970/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "MK9hQNvAibnbFC7rWbg8";

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;

    // Components
    private JPanel panel;
    private JLabel titleLabel, nombreLabel, passwordLabel, confirmPasswordLabel;
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
        nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(50, 100, 100, 30);
        panel.add(nombreLabel);
        emailField = new JTextField();
        emailField.setBounds(174, 100, 176, 30);
        panel.add(emailField);

        // Set up the password label and field
        passwordLabel = new JLabel("Clave:");
        passwordLabel.setBounds(50, 150, 100, 30);


        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(174, 150, 176, 30);
        panel.add(passwordField);

        // Set up the confirm password label and field
        confirmPasswordLabel = new JLabel("Confirmar Clave:");
        confirmPasswordLabel.setBounds(50, 200, 100, 30);
        panel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(174, 200, 176, 30);
        panel.add(confirmPasswordField);

        // Set up the login and register buttons
        loginButton = new JButton("Inisiar Sesión");
        loginButton.setBounds(222, 291, 128, 30);
        loginButton.setBackground(Color.decode("#00b894"));

        loginButton.addActionListener(this);
        panel.add(loginButton);
        registerButton = new JButton("Registrarse");
        registerButton.setBounds(62, 291, 117, 30);
        registerButton.setBackground(Color.decode("#00b894"));
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // Set up the frame
        getContentPane().add(panel);
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
                JOptionPane.showMessageDialog(panel, "Please enter an name and password.");
                return;
            }

            String username = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter an name and password.");
                return;
            }

            // si el usuario y la contraseña son correctos y ID_CARGO = 1 -> admin ABRIR EmployeeManagementSystem


            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE NOMBREUSUARIO='" + username + "' AND CLAVE='" + password + "'");
                if (rs.next()) {
                    //JOptionPane.showMessageDialog(panel, "Login successful!");
                    //System.out.println("Login successful!");
                    //System.out.println(rs.getInt("ID_CARGO"));
                    if (rs.getInt("ID_CARGO") == 1) {
                        //System.out.println("admin");
                        // abrir EmployeeManagementSystem
                        EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem();
                        employeeManagementSystem.setVisible(true);
                        employeeManagementSystem.pack();
                        employeeManagementSystem.setLocationRelativeTo(null);
                        employeeManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();
                    } else {
                        //System.out.println("empleado");
                        Employee  employee = new Employee();
                        employee.setVisible(true);
                        employee.pack();
                        employee.setLocationRelativeTo(null);
                        employee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid email or password.");
                }

                // conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        } else if (e.getSource() == registerButton) {
            // Validate the data of the user and create a new account in the database

            // is a la clase AddEmployeeForm
            RegistroForm addEmployeeForm = new RegistroForm();
            addEmployeeForm.setVisible(true);
            addEmployeeForm.pack();
            addEmployeeForm.setLocationRelativeTo(null);
            addEmployeeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();

        }
    }

    // Main method
    public static void main(String[] args) {
        new LoginRegistrationForm();
    }
}
