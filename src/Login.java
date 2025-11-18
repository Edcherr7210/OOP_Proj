import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener, MouseListener {

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
        JLabel appTitle = new JLabel("Assignment Calendar");
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
        //Will add db later to save the info of student or admin
        if (e.getSource() == loginButton) {

        }
    }

    private boolean authenticate(String user, String pass) {
        return !user.isEmpty() && !pass.isEmpty(); // Placeholder logic
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Will send to a class where you will register as a student or admin
        if (e.getSource() == signUpLink) {
            new SignUp();
        }
    }

    @Override public void mouseEntered(MouseEvent e) {
        if (e.getSource() == signUpLink) signUpLink.setForeground(Color.LIGHT_GRAY);
    }
    @Override public void mouseExited(MouseEvent e) {
        if (e.getSource() == signUpLink) signUpLink.setForeground(Color.WHITE);
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {
        if(e.getSource() == signUpLink) new SignUp();
    }


}
