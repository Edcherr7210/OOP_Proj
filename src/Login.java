import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel signUpLabel;
    private JLabel errorLabel;

    public Login() {
        setTitle("PrioritiCal - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Title
        JLabel titleLabel = new JLabel("PrioritiCal Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel for fields
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Email label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userLabel = new JLabel("SNHU Email:");
        centerPanel.add(userLabel, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(20);
        centerPanel.add(usernameField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password:");
        centerPanel.add(passwordLabel, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        centerPanel.add(passwordField, gbc);

        // Error label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        centerPanel.add(errorLabel, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        centerPanel.add(loginButton, gbc);

        // Signup label (clickable link style)
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        signUpLabel = new JLabel("<HTML><U>Don't have an account? Sign up</U></HTML>", SwingConstants.CENTER);
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        centerPanel.add(signUpLabel, gbc);

        // Signup click opens SignUp window (if you have that class)
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open SignUp window
                // new SignUp();
                // dispose();
                System.out.println("SignUp clicked (hook your SignUp window here).");
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == loginButton) {
            String email = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            // Check email format
            if (!ValidEmail(email)) {
                errorLabel.setText("Invalid email. Must end with @snhu.edu");
                return;
            }

            // Check password rules
            if (!AllowedPassword(password)) {
                errorLabel.setText("Password must be 12+ chars w/ upper, lower, number & special char.");
                return;
            }

            // Check against database
            if (authenticate(email, password)) {
                errorLabel.setText("");

                String name = email.split("@")[0];
                System.out.println("Login successful for: " + name);

                // TODO: open your calendar/dashboard window here
                // Example:
                // new CalendarDashboard(name);
                // dispose();

                JOptionPane.showMessageDialog(this,
                        "Welcome, " + name + "!",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE);

                new Calendar();
            } else {
                errorLabel.setText("Incorrect email or password.");
            }
        }
    }

    // Validate SNHU email
    private boolean ValidEmail(String email) {
        if (email == null) return false;
        return email.toLowerCase().endsWith("@snhu.edu");
    }

    // Validate password strength (12+ chars, upper, lower, digit, special)
    private boolean AllowedPassword(String password) {
        if (password == null || password.length() < 12) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    // Actual DB check: students(email, password)
    private boolean authenticate(String email, String pass) {
        String sql = "SELECT * FROM students WHERE email = ? AND password = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                // True if at least one row found
                return rs.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            errorLabel.setText("Database error. Please try again.");
            return false;
        }
    }
}
