import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Calendar extends JFrame{
    //private JMenuBar menuBar = new JMenuBar();
    //private JMenu classesMenu = new JMenu("Classes");

    private ImageIcon forward = new ImageIcon("forward.png");
    private ImageIcon backward = new ImageIcon("backward.png");
    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String currentDate = dateTime.format(formatter);
    private JPanel calendarPanel = new JPanel(new BorderLayout());
    private JPanel assignmentPanel = new JPanel(new BorderLayout());
    private JLabel date = new JLabel(currentDate);

    public Calendar() {
        //Full Screen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PrioritiCal");

        JPanel mainBg = new JPanel(new BorderLayout());
        mainBg.setBackground(Color.DARK_GRAY);

        // ========== LEFT PANEL (75% width) ==========
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.CYAN);
        leftPanel.setLayout(new GridBagLayout());

        // Add date panel
        date.setFont(new Font("Roboto Mono", Font.BOLD, 48));
        date.setForeground(Color.BLACK);

        calendarPanel.setOpaque(false);
        calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        calendarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        calendarPanel.add(date);
        calendarPanel.add(Box.createVerticalStrut(30));

        leftPanel.add(calendarPanel);
        
        // 75% of screen width automatically
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        leftPanel.setPreferredSize(new Dimension((int)(screenWidth * 0.75), 0));






        JLabel arrow1 = new JLabel(forward);
        JLabel arrow2 = new JLabel(backward);

        assignmentPanel.setBackground(Color.CYAN);
        //Adding menu bar
        //menuBar.add(classesMenu); //adds the classesMenu to the menu bar
        this.add(calendarPanel); //Add the main JPanel


        //this.setJMenuBar(menuBar); //sets the menu bar
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Calendar();
    }
}
