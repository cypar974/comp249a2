import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class driver {
    public static String[] fields2(String record_line) throws Exception {
        boolean hasInvComa = false;
        for (int i = 0; i < record_line.length(); i++) {
            if (record_line.charAt(i) == '"') {
                hasInvComa = true;
                break;
            }
        }
        if (hasInvComa) {
            return fields(record_line);
        } else {
            String[] fields = record_line.split(",");

            // Check if the number of fields is exactly 10
            if (fields.length != 10) {
                if (fields.length < 10) {
                    throw new MissingFieldsException();
                } else {
                    throw new ExcessFieldsException();
                }
            }

            // Return the fields array
            return fields;
        }

    }

    public static String[] fields(String record_line) throws Exception {
        if (record_line.endsWith(",")) {
            record_line = record_line.substring(0, record_line.length() - 1);
        }
        String[] fields = null;
        int nbFields = 1;
        boolean missingQuote = false;
        boolean hasQuotes = false;

        for (int i = 0; i < record_line.length(); i++) {

            if (record_line.charAt(i) == '"') {
                missingQuote = !missingQuote;
                continue;
            }

            if (record_line.charAt(i) == ',' && !missingQuote) {
                nbFields++;
            }
        }

        if (missingQuote) {
            throw new MissingQuotesException();
        }

        if (nbFields < 10) {
            throw new MissingFieldsException();
        } else if (nbFields > 10) {
            throw new ExcessFieldsException();
        }

        fields = new String[nbFields];

        int currentField = 0;
        fields[0] = "";

        for (int i = 0; i < record_line.length(); i++) {
            if (record_line.charAt(i) == '"') {
                hasQuotes = !hasQuotes;
                continue;
            }

            if (record_line.charAt(i) == ',' && !hasQuotes) {
                fields[currentField] = fields[currentField].trim();
                currentField++;

                if (currentField == 10) {
                    return fields;
                }

                fields[currentField] = "";
                continue;

            }

            fields[currentField] += record_line.charAt(i);

        }

        return fields;

    }

    public static Movie lineReader(String[] aString)
            throws BadDurationException, BadGenreException, BadNameException, BadRatingException, Exception {
        return new Movie();
    }

    public static void movieChecker(String[] aStrings) throws BadDurationException, BadGenreException, BadNameException,
            BadRatingException, BadScoreException, BadYearException, BadTittleException, Exception {

        try {
            if (Integer.parseInt(aStrings[0]) < 1990 || Integer.parseInt(aStrings[0]) > 1999) {
                throw new BadYearException();
            }
            if (Integer.parseInt(aStrings[2]) < 30 || Integer.parseInt(aStrings[2]) > 300) {
                throw new BadDurationException();
            }
        } catch (NumberFormatException e) {
            throw new Exception(e.getMessage());
        }

        if (aStrings[1].trim().length() == 0) {
            throw new BadTittleException();
        }

        try {

        } catch (NumberFormatException e) {
            throw new BadDurationException();
        }

    }

    public static void CSVReader(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] a = fields2(line);
                    movieChecker(a);
                    // PUT TO THE CORRESPONDING FILE

                } catch (Exception e) {
                    System.out.println(
                            "------------------------------------\n\r" + e
                                    + "\n\r------------------------------------\n\r");
                    try {
                        PrintWriter badFileWriter = new PrintWriter(new FileOutputStream("bad.txt", true));
                        badFileWriter.println(line);
                        badFileWriter.close();
                    } catch (IOException ex) {
                        System.err.println("Error writing to bad.txt: " + ex.getMessage());
                    }
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "Movies1990.csv";
        CSVReader(filePath);
    }
}
