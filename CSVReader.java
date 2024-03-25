import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {
    
    public static void main(String[] args) {
        // Define the path to your CSV file
        String filePath = "Movies1990.csv";

        try {
            // Create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(filePath));

            // Read each line from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split the line into columns based on the comma delimiter
                String[] columns = line.split(",");

                // Now you can access each column using array index
                // For example, if you have three columns: col1, col2, col3
                // you can access them as follows:
                String col1 = columns[0];
                String col2 = columns[1];
                String col3 = columns[2];

                // Do something with the data from the columns
                System.out.println("Column 1: " + col1);
                System.out.println("Column 2: " + col2);
                System.out.println("Column 3: " + col3);
            }

            // Close the scanner when done
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
