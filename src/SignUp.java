import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUp extends JFrame implements ActionListener, MouseListener {
    private JButton signUpButton;
    private JLabel signInLink;
    private JLabel errorLabel;
    private JLabel firstName;
    private JLabel lastName;

    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confPassLabel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    public SignUp() {
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

        JLabel signInLabel = new JLabel("Sign Up");
        signInLabel.setForeground(Color.WHITE);
        signInLabel.setFont(new Font("Arial", Font.BOLD, 26));

        firstName = new JLabel("First Name");
        firstName.setHorizontalAlignment(JLabel.CENTER);
        firstName.setForeground(Color.WHITE);
        firstName.setFont(new Font("Times New Roman", Font.BOLD, 14));

        firstNameField = new JTextField(20);
        firstNameField.setPreferredSize(new Dimension(300, 40));
        firstNameField.setFont(new Font("Arial", Font.BOLD, 14));

        lastName = new JLabel("Last Name");
        lastName.setHorizontalAlignment(JLabel.CENTER);
        lastName.setForeground(Color.WHITE);
        lastName.setFont(new Font("Times New Roman", Font.BOLD, 14));

        lastNameField = new JTextField(20);
        lastNameField.setPreferredSize(new Dimension(300, 40));
        lastNameField.setFont(new Font("Arial", Font.BOLD, 14));

        emailLabel = new JLabel("Email");
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(300, 40));
        emailField.setFont(new Font("Arial", Font.BOLD, 14));

        passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 40));

        confPassLabel = new JLabel("Confirm Password");
        confPassLabel.setHorizontalAlignment(JLabel.CENTER);
        confPassLabel.setForeground(Color.WHITE);
        confPassLabel.setFont(new Font("Times new Roman", Font.BOLD, 14));

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(300, 40));

        signUpButton = new JButton("Next");
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);
        signUpButton.setPreferredSize(new Dimension(300, 40));

        signInLink = new JLabel("<html><u>Already Signed Up?</u></html>");
        signInLink.setHorizontalAlignment(JLabel.CENTER);
        signInLink.setForeground(Color.WHITE);
        signInLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInLink.addMouseListener(this);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        gbc.gridx = 0;

        gbc.gridy = 0;
        rightPanel.add(signInLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        rightPanel.add(firstName, gbc);

        gbc.gridx = 1;
        rightPanel.add(lastName, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        rightPanel.add(firstNameField, gbc);

        gbc.gridx = 1;
        rightPanel.add(lastNameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        rightPanel.add(emailLabel, gbc);

        gbc.gridy = 4;
        rightPanel.add(emailField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        rightPanel.add(passwordLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        rightPanel.add(passwordField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        rightPanel.add(confPassLabel, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        rightPanel.add(confirmPasswordField, gbc);

        gbc.gridy = 7;
        rightPanel.add(signUpButton, gbc);

        gbc.gridy = 8;
        rightPanel.add(signInLink, gbc);

        gbc.gridy = 9;
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
        //Will add db later to save the info of student or admin
        if (e.getSource() == signUpButton) {

        }
    }

    private boolean authenticate(String user, String pass) {
        return !user.isEmpty() && !pass.isEmpty(); // Placeholder logic
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Will send to a class where you will register as a student
        if (e.getSource() == signInLink) {
            dispose();
            new Login();
        }
    }

    @Override public void mouseEntered(MouseEvent e) {
        if (e.getSource() == signInLink) signInLink.setForeground(Color.LIGHT_GRAY);
    }
    @Override public void mouseExited(MouseEvent e) {
        if (e.getSource() == signInLink) signInLink.setForeground(Color.WHITE);
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {
    }

}
