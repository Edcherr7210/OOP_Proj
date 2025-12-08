import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // This is your SQLite file
    private static final String URL = "jdbc:sqlite:PrioritiCal.db";

    // Get a connection to the DB file
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create tables if they don't exist
    public static void init() {
        String sql = """
                CREATE TABLE IF NOT EXISTS "Student" (
                "First_name" TEXT PRIMARY KEY,
                "Last_name" TEXT NOT NULL,
                "Password" TEXT NOT NULL,
                "CSV-File" TEXT
            );
            CREATE TABLE  IF NOT EXISTS  "StudentCourse"(
                "StudentID" TEXT PRIMARY KEY AUTOINCREMENT,
                "First_name" TEXT  NOT NULL,
                "CurrentGrade" REAL NOT NULL,
                "AttendancePercentage" REAL NOT NULL,
                "ParticipationPercentage" REAL NOT NULL,
                "DiscussionPercentage" REAL NOT NULL,
                "HomeworkPercentage" REAL NOT NULL,
                "EvaluationPercentage" REAL NOT NULL,
                "QuizzesPercentage" REAL NOT NULL,
                "ExamPercentage" REAL NOT NULL,
                "MidTermPercentage" REAL NOT NULL,
                "ProjectPercentage" REAL NOT NULL,
                "FinalPercentage" REAL NOT NULL,
                FOREIGN KEY (First_name) REFERENCES Student(First_name)
            );
            CREATE TABLE  IF NOT EXISTS  "Course" (
                "CourseID" INTEGER PRIMARY KEY AUTOINCREMENT,
                "StudentCourseID" TEXT NOT NULL,
                "CourseName" TEXT NOT NULL,
                "ClassTimes" TEXT NOT NULL,
                "Year" INTEGER NOT NULL,
                FOREIGN KEY (StudentCourseID) REFERENCES StudentCourse(StudentCourseID)
            
            );
            CREATE TABLE  IF NOT EXISTS  "StudentAssignment"(
                "AssignmentID" INTEGER PRIMARY KEY AUTOINCREMENT,
                "AssignmentName" TEXT NOT NULL,
                "CourseID" INTEGER NOT NULL,
                "AssignmentType" TEXT NOT NULL,
                "TimeDue" TIME NOT NULL,
                "DueDate" DATE NOT NULL,
                "PossiblePointsPerAssignment" REAL NOT NULL,
                FOREIGN KEY (StudentID) REFERENCES Course(StudentID)
            
            );""" ;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("DB ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
