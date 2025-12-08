import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class CSVImportCode {

    public ArrayList<String> dates = new ArrayList<>();
    public ArrayList<String> assignments = new ArrayList<>();
    public ArrayList<String> points = new ArrayList<>();
    public ArrayList<String> classes = new ArrayList<>();
    public CSVImportCode(String filepath) {

        String filelocation = filepath;//Get the csv file from srv this may be why we are having this error
        System.out.println(filepath);
        BufferedReader reader = null; //shows if reader doesn't go to anything.
        String line = ""; //Reads each line of file

        try {
            reader = new BufferedReader(new FileReader(filelocation));
            reader.readLine();
                while ((line = reader.readLine()) != null) { // reads the next line every time
                    String[] row = line.split(",");
                        if(row.length >= 7) {
                            classes.add(row[1]);
                            dates.add(row[5]);
                            assignments.add(row[2]);
                            points.add(row[6]);
                            System.out.println(row[2] + " : " + row[5] + " : " + row[6]);
                        }
                        else {
                            System.out.println("Skipping row with only " + row.length + " columns: " + line);
            }

            }
        }
        catch (Exception e) {
            e.printStackTrace(); // In case something goes wrong
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


