import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class driver {

    public static void CSVReader(String fileName) {

        try {
            // Create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(fileName));
            FileWriter writer = new FileWriter("bad_movie_record.txt", true);
            // Read each line from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                try {
                    int col1 = Integer.valueOf(columns[0]);
                    if (col1 < 1990 || col1 > 1999){

                    }
                } catch (NumberFormatException e) {}
                

                if (col1))
                
                String col2 = columns[1];
                String col3 = columns[2];

            }

            // Close the scanner when done
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "Movies1990.csv";
        CSVReader(filePath);
    }
}
