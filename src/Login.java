import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener, MouseListener {

    String username;
    String password;
    private JButton loginButton;
    private JLabel signUpLink;
    private JLabel errorLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        ImageIcon image = new ImageIcon("Project.png");
        // ========== FULL SCREEN ==========
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Assign Check");

        // Main background uses BorderLayout so everything scales
        JPanel mainBg = new JPanel(new BorderLayout());
        mainBg.setBackground(Color.DARK_GRAY);

        // ========== LEFT PANEL (35% width) ==========
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.CYAN);
        leftPanel.setLayout(new GridBagLayout());

        // Title + image stacked vertically
        JLabel appTitle = new JLabel("PrioritiCal");
        appTitle.setFont(new Font("Roboto Mono", Font.BOLD, 48));
        appTitle.setForeground(Color.BLACK);

        JLabel imageLabel = new JLabel(new ImageIcon("Project.png"));

        JPanel leftContent = new JPanel();
        leftContent.setOpaque(false);
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftContent.add(appTitle);
        leftContent.add(Box.createVerticalStrut(30));
        leftContent.add(imageLabel);

        leftPanel.add(leftContent);

        // 35% of screen width automatically
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        leftPanel.setPreferredSize(new Dimension((int)(screenWidth * 0.35), 0));

        // ========== RIGHT PANEL (LOGIN FORM) ==========
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setForeground(Color.WHITE);
        signInLabel.setFont(new Font("Arial", Font.BOLD, 26));

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(300, 40));
        usernameField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 40));


        loginButton = new JButton("Next");
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setPreferredSize(new Dimension(300, 40));

        signUpLink = new JLabel("<html><u>Don't Have An Account?</u></html>");
        signUpLink.setForeground(Color.WHITE);
        signUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLink.addMouseListener(this);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        gbc.gridx = 0;

        gbc.gridy = 0;
        rightPanel.add(signInLabel, gbc);

        gbc.gridy = 1;
        rightPanel.add(usernameField, gbc);

        gbc.gridy = 2;
        rightPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        rightPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        rightPanel.add(signUpLink, gbc);

        gbc.gridy = 5;
        rightPanel.add(errorLabel, gbc);

        // Add panels to main container
        mainBg.add(leftPanel, BorderLayout.WEST);
        mainBg.add(rightPanel, BorderLayout.CENTER);

        // Add to frame
        this.add(mainBg);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String email = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate email format
        if (!ValidEmail(email)) {
            errorLabel.setText("Invalid Email must end with @snhu.edu");
            return;
        }

        // Validate password requirements
        if (!AllowedPassword(password)) {
            errorLabel.setText("Invalid Password. Must be 12+ Characters, must include one uppercase, one lowercase, special character and a numerical number");
            return;
        }

        // Authenticate against database using email
        if (authenticate(email, password)) {
            System.out.println("Login successful for: " + email);
            errorLabel.setText("");

            // Close login window and open calendar
            this.dispose();
            // TODO: Replace MainCalendar with your actual calendar class name
            new Calendar();

        } else {
            errorLabel.setText("Incorrect email or password. Please try again.");
            passwordField.setText(""); // Clear password field
        }
    }

    public boolean ValidEmail(String email) {
        if (email == null)
            return false;
        return email.contains("@snhu.edu");
    }

    public boolean AllowedPassword(String password) {
        if (password == null)
            return false;
        if (password.length() < 12)
            return false;
        boolean cap = false;
        boolean low = false;
        boolean num = false;
        boolean specialChar = false;
        for (char p : password.toCharArray()) {
            if (Character.isLowerCase(p)) low = true;
            else if (Character.isUpperCase(p)) cap = true;
            else if (Character.isDigit(p)) num = true;
            else specialChar = true;
        }
        return cap && low && num && specialChar;
    }

    /**
     * Authenticates user against the database
     * @param email The email entered by user
     * @param password The password entered by user
     * @return true if credentials match, false otherwise
     */
    private boolean authenticate(String email, String password) {
        String sql = "SELECT Password FROM students WHERE Email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                // Direct password comparison (consider using hashed passwords in production)
                return password.equals(storedPassword);
            }

        } catch (SQLException ex) {
            System.err.println("Database error during authentication: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false; // User not found or error occurred
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == signUpLink) {
            dispose();
            new SignUp();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == signUpLink) signUpLink.setForeground(Color.LIGHT_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == signUpLink) signUpLink.setForeground(Color.WHITE);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}