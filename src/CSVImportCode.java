import java.io.*;
public class CSVImportCode extends Calendar{
    public CSVImportCode(String filepath) {

        String filelocation = filepath;//Get the csv file from srv this may be why we are having this error
        System.out.println(filepath);
        BufferedReader reader = null; //shows if reader doesn't go to anything.
        String line = "";//Reads each line of file

        try {
            reader = new BufferedReader(new FileReader(filelocation));
                  while ((line = reader.readLine()) != null) { // reads the next line every time
                String[] row = line.split(","); // splits line at all commas
                for (String index : row) { // displays all indexes
                    System.out.printf("%-1s", index); // puts a space between all strings
                }
                System.out.println(); // prints all lines
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


