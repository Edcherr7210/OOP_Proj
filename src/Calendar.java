import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

public class Calendar extends JFrame {
    public JTextArea assignmentArea = new JTextArea("No assignments selected.\n\nClick a date to view assignments.");
    public JLabel errorLabel;
    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String currentDate = dateTime.format(formatter);
    private JPanel calendarPanel = new JPanel(new BorderLayout());
    private JPanel assignmentPanel = new JPanel(new BorderLayout());
    private JLabel date = new JLabel(currentDate);
    private HashMap<LocalDate, JButton> DateButton = new HashMap<>();
    private YearMonth currentMonth = YearMonth.now();
    private HashMap<LocalDate, List<Assignments>> assignmentsByDate = new HashMap<>();
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


        prevMonth.addActionListener(e -> changeMonth(-1));
        nextMonth.addActionListener(e -> changeMonth(1));
        today.addActionListener(e -> goToToday());
        importer.addActionListener(e -> getFile());


        navPanel.add(prevMonth);
        navPanel.add(today);
        navPanel.add(nextMonth);
        navPanel.add(importer);
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
           // dayButton.addActionListener(e -> {
              dayButton.addActionListener(e -> {
                 displayAssignments(date);
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

    public void getFile() {
        JFileChooser chooser = new JFileChooser(".");
        int result = chooser.showOpenDialog(chooser);
        String filepath;
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectFile = chooser.getSelectedFile();
            filepath = selectFile.getAbsolutePath();
            CSVImportCode csv = new CSVImportCode(filepath);
            LoadCSVData(csv);
        }

    }


    public void displayAssignments(LocalDate date) {
        List<Assignments> list = assignmentsByDate.get(date);// Grabs the list on the spreadsheet with the assignments due date.
        if (list == null || list.isEmpty()) { //If an assignment is due it does't show it
            assignmentArea.setText("No assignments found");
        }
        else
        {
            StringBuilder tasks = new StringBuilder();
            for (Assignments t: list) {// How it will list out the assignments. When our list has 10 assignments it will run this loop 10 times each time our t will be the next assignment.
                tasks.append(t.ClassName).append(t.AssignmentName).append("Point of Assignment").append(t.points);
            }
            assignmentArea.setText(tasks.toString());// Makes sure all of these come out as strings
        }
    }
    public void LoadCSVData(CSVImportCode csv) {
        assignmentsByDate.clear();

        ArrayList<String> Assignments = csv.assignments;
        ArrayList<String> Date = csv.dates;
        ArrayList<String> Points = csv.points;
        ArrayList<String> Classes = csv.classes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy"); // Adjust this to match your CSV

        for (int i = 0; i < Date.size(); i++) {
            try {
                String dateStr = Date.get(i).trim();

                if (dateStr.isEmpty() || !dateStr.matches(".*\\d.*")) {
                    System.out.println("Skipping row with invalid date: '" + dateStr + "' for assignment: " + Assignments.get(i));
                    continue;
                }
                LocalDate date = LocalDate.parse(Date.get(i), formatter);
                Assignments assignment = new Assignments(Assignments.get(i), Date.get(i), Points.get(i), Classes.get(i));

                // Add assignment to the map
                if (!assignmentsByDate.containsKey(date)) {
                    assignmentsByDate.put(date, new ArrayList<>());
                }
                assignmentsByDate.get(date).add(assignment);

            } catch (Exception e) {
                System.err.println("Error parsing date: " + Date.get(i));
                e.printStackTrace();
            }
        }

        updateCalendarGrid();
    }
}

