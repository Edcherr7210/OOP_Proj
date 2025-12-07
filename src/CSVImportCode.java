import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImportCode extends Calendar{

    private String date = "";
    private String assignment = "";
    private float points = 0;
    public CSVImportCode(String filepath) {

        String filelocation = filepath;//Get the csv file from srv this may be why we are having this error
        System.out.println(filepath);
        BufferedReader reader = null; //shows if reader doesn't go to anything.
        String line = "";//Reads each line of file

        try {
            reader = new BufferedReader(new FileReader(filelocation));
            reader.readLine();
                while ((line = reader.readLine()) != null) { // reads the next line every time
                    String[] row = line.split(","); // splits line at all commas
                    for (String index : row) { // loop through all indexes
                        String sql = "INSERT INTO StudentAssignment (AssignmentName, DueDate, PossiblePointsPerAssignment)" + "VALUES(?, ?, ?)";
                        try (Connection conn = Database.getConnection();
                             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                            pstmt.setString(1, row[2]);
                            pstmt.setString(2, row[5]);
                            pstmt.setString(3, row[6]);

                            pstmt.executeUpdate();
                            System.out.println("Account created! You can sign in now.");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            System.out.println("Error creating account: " + ex.getMessage());
                        }
                    }
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // In case something goes wrong
        }
        finally {
            try {
                reader.close();//closes the file
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }// I like labeling things so you guys can understand and I don't forget something.
    }
}


