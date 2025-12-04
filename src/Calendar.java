import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;


public class Calendar extends JFrame {
    public JLabel errorLabel;
    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String currentDate = dateTime.format(formatter);
    private JPanel calendarPanel = new JPanel(new BorderLayout());
    private JPanel assignmentPanel = new JPanel(new BorderLayout());
    private JLabel date = new JLabel(currentDate);
    private HashMap<LocalDate, JButton> DateButton = new HashMap<>();
    private YearMonth currentMonth = YearMonth.now();

    // Day labels
    private String[] dayNames = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    public String filepath;
    public Calendar() {
        filepath = "No File Selected";
        // Full Screen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PrioritiCal");

        JPanel mainBg = new JPanel(new BorderLayout());
        mainBg.setBackground(Color.DARK_GRAY);

        // ========== LEFT PANEL (75% width) ==========
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(240, 240, 245));

        // Calculate 75% of screen width
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        leftPanel.setPreferredSize(new Dimension((int)(screenWidth * 0.75), 0));

        // Top section with date and navigation
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        date.setFont(new Font("Roboto Mono", Font.BOLD, 48));
        date.setForeground(Color.BLACK);
        date.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(date, BorderLayout.CENTER);

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.setOpaque(false);
        JButton prevMonth = new JButton("← Previous");
        JButton nextMonth = new JButton("Next →");
        JButton today = new JButton("Today");
        JButton importer = new JButton("Import CSV file");
        JButton addCurrentGrade = new JButton("+ Current Grade");

        prevMonth.addActionListener(e -> changeMonth(-1));
        nextMonth.addActionListener(e -> changeMonth(1));
        today.addActionListener(e -> goToToday());
        importer.addActionListener(e -> getFile(filepath));
        addCurrentGrade.addActionListener(e -> openCurGradePage());

        navPanel.add(prevMonth);
        navPanel.add(today);
        navPanel.add(nextMonth);
        navPanel.add(importer);
        navPanel.add(addCurrentGrade);
        topPanel.add(navPanel, BorderLayout.SOUTH);

        leftPanel.add(topPanel, BorderLayout.NORTH);

        // Calendar panel setup
        calendarPanel.setOpaque(false);
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Create the calendar grid
        updateCalendarGrid();

        leftPanel.add(calendarPanel, BorderLayout.CENTER);
        mainBg.add(leftPanel, BorderLayout.WEST);

        // ========== RIGHT PANEL (25% width) ==========
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel assignmentTitle = new JLabel("Assignments");
        assignmentTitle.setFont(new Font("Roboto Mono", Font.BOLD, 24));
        assignmentTitle.setForeground(Color.WHITE);

        assignmentPanel.setBackground(Color.GRAY);
        assignmentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        JTextArea assignmentArea = new JTextArea("No assignments selected.\n\nClick a date to view assignments.");
        assignmentArea.setEditable(false);
        assignmentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        assignmentArea.setLineWrap(true);
        assignmentArea.setWrapStyleWord(true);
        assignmentArea.setBackground(Color.GRAY);
        assignmentArea.setForeground(Color.WHITE);
        assignmentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(assignmentArea);
        scrollPane.setBorder(null);
        assignmentPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.add(assignmentTitle, BorderLayout.NORTH);
        rightPanel.add(assignmentPanel, BorderLayout.CENTER);

        mainBg.add(rightPanel, BorderLayout.EAST);

        this.add(mainBg);
        this.setVisible(true);
    }

    private void updateCalendarGrid() {
        calendarPanel.removeAll();
        DateButton.clear();

        // Main grid panel
        JPanel gridPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        gridPanel.setOpaque(false);

        // Add day headers
        for (String day : dayNames) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            dayLabel.setForeground(Color.DARK_GRAY);
            gridPanel.add(dayLabel);
        }

        // Get first day of month and total days
        LocalDate firstOfMonth = currentMonth.atDay(1);
        int daysInMonth = currentMonth.lengthOfMonth();

        // Get day of week (1=Monday, 7=Sunday)
        int firstDayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        // Add empty cells before first day (Monday = 1, so 1 means no empty cells)
        for (int i = 1; i < firstDayOfWeek; i++) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setOpaque(false);
            gridPanel.add(emptyPanel);
        }

        // Add day buttons
        LocalDate today = LocalDate.now();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentMonth.atDay(day);
            JButton dayButton = new JButton(String.valueOf(day));

            dayButton.setFont(new Font("Arial", Font.PLAIN, 16));
            dayButton.setPreferredSize(new Dimension(60, 60));
            dayButton.setFocusPainted(false);

            // Highlight today
            if (date.equals(today)) {
                dayButton.setBackground(new Color(100, 149, 237));
                dayButton.setForeground(Color.WHITE);
                dayButton.setFont(new Font("Arial", Font.BOLD, 16));
            } else {
                dayButton.setBackground(Color.WHITE);
                dayButton.setForeground(Color.BLACK);
            }

            // Add click listener
            dayButton.addActionListener(e -> {
                System.out.println("Clicked: " + date);
                // You can add functionality here to show assignments for this date
            });

            DateButton.put(date, dayButton);
            gridPanel.add(dayButton);
        }

        calendarPanel.add(gridPanel, BorderLayout.CENTER);
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private void changeMonth(int offset) {
        currentMonth = currentMonth.plusMonths(offset);
        updateDateLabel();
        updateCalendarGrid();
    }

    private void goToToday() {
        currentMonth = YearMonth.now();
        updateDateLabel();
        updateCalendarGrid();
    }

    private void updateDateLabel() {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        date.setText(currentMonth.format(monthFormatter));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calendar());
    }

    public void getFile(String filepath) {
        JFileChooser chooser = new JFileChooser(".");
        int result = chooser.showOpenDialog(chooser);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectFile = chooser.getSelectedFile();
            filepath = selectFile.getAbsolutePath();
            CSVImportCode csv = new CSVImportCode(filepath);
        }
    }
    public void openCurGradePage() {
        dispose();
        new AddGrades();
    }
}

