import java.time.LocalDate;
import java.time.LocalDate;
public class Assignments {
    public String ClassName;
    public String AssignmentName;
    public String AssignmentDate;
    public LocalDate dueDate;
    public String points;

    public Assignments(String assignmentName, String date, String points, String name) {
        this.AssignmentName = assignmentName;
        this.AssignmentDate = date;
        this.points = points;
        this.ClassName = name;
    }
}