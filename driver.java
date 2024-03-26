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
            BadRatingException, BadScoreException, BadYearException, BadTitleException, Exception {

        String[] movieGenres = { "musical", "comedy", "animation", "adventure", "drama", "crime", "biography", "horror",
                "action", "documentary", "fantasy", "mystery",
                "sci-fi", "family", "western", "romance", "thriller" };

        String[] movieRatings = { "PG", "Unrated", "G", "R", "PG-13", "NC-17" };

        // ------------------------------
        // Do we need to add something that verifies if each field is empty?
        // like
        // aStrings[0].trim().length()==0? for each?

        try {
            if (aStrings[0].trim().length() == 0) {
                throw new BadYearException("Invalid Year. Missing the Year in the field");
            } else if (Integer.parseInt(aStrings[0]) < 1990 || Integer.parseInt(aStrings[0]) > 1999) {
                throw new BadYearException("Invalid Year. Year must be from 1990 through 1999");
            }
        } catch (NumberFormatException e) {
            throw new Exception(e.getMessage());

        }
        // ---------------------------------
        if (aStrings[1].trim().length() == 0) {
            throw new BadTitleException("Invalid Title. Missing the Title in the field");
        }

        // ---------------------------------------
        try {
            if (aStrings[2].trim().length() == 0) {
                throw new BadDurationException("Invalid Duration. Missing the Duration in the field");
            } else if (Integer.parseInt(aStrings[2]) < 30 && Integer.parseInt(aStrings[2]) > 300) {
                throw new BadDurationException(
                        "Invalid Duration. Duration should be between between 30 minutes to 300 minutes");
            }
        } catch (NumberFormatException e) {
            throw new Exception(e.getMessage());
        }

        // ----------------------------------------
        if (aStrings[3].trim().length() == 0) {
            throw new BadTitleException("Invalid Genre. Missing the Genre in the field");
        }

        boolean invalidGenre = true;
        for (String genre : movieGenres) {
            if (aStrings[3].equals(genre)) {

                invalidGenre = false;
                break;

            }
        }
        //
        if (invalidGenre) {
            throw new BadGenreException("Invalid Genre. Genre is misspelled");
        }

        // ---------------------------------------------
        if (aStrings[4].trim().length() == 0) {
            throw new BadRatingException("Invalid Rating. Missing the Rating in the field");
        }

        boolean invalidRating = true;
        for (String rating : movieRatings) {
            if (aStrings[4].equals(rating)) {
                invalidRating = false;
                break;
            }
        }
        if (invalidRating) {
            throw new BadRatingException("Invalid Rating. Rating is misspelled.");
        }
        // --------------------------------------------
        try {
            if (Double.parseDouble(aStrings[5]) < 10.0 && Double.parseDouble(aStrings[5]) > 0.0) {
                throw new BadDurationException();
            }
        } catch (NumberFormatException e) {
            throw new Exception(e.getMessage());
        }

        // ------------------------------------------------

        for (int i = 7; i < aStrings.length; i++) {
            if (aStrings[i].trim().length() == 0) {
                throw new BadNameException("Invalid Actor Name. No Actor name in the field.");
            }

        }

        // ----------------------------------------------------

    }

    public static void CSVReader(String fileName) {
        System.out.println("asdsda");
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
