import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class driverTester {

    public static String[] genresSeen = {};
    public static Movie[] aMovies = {};
    public static Movie[][] allMovies = {};
    public static int cursor = 0;
    public static int localCursor = 0;

    public static void do_part3(String fileInputName) {
        int numGenres = genresSeen.length;
        allMovies = new Movie[numGenres][];

        try (BufferedReader br = new BufferedReader(new FileReader(fileInputName))) {
            String fileName;
            int genreIndex = 0;
            while ((fileName = br.readLine()) != null) {
                allMovies[genreIndex] = deserializeMovies(fileName);
                genreIndex++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file part3_manifest.txt: " + e.getMessage());
        }
    }

    private static Movie[] deserializeMovies(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Movie[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing file " + fileName + ": " + e.getMessage());
            return new Movie[0]; // Return an empty array if deserialization fails
        }
    }

    public static void aMoviesCleaner() {
        aMovies = new Movie[0];
    }

    public static void part3manifestMaker() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("part3_manifest.txt"))) {
            int count = 0;
            for (String genre : genresSeen) {
                writer.write(genre.toLowerCase() + ".ser");
                if (++count < genresSeen.length) {
                    writer.newLine(); // Add a new line if it's not the last genre
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static String do_part2(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String serFileName = line.substring(0, line.length() - 4);
                aMoviesMaker(line);
                serializeArray(serFileName);
                aMoviesCleaner();
            }
            part3manifestMaker();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "part3_manifest.txt";
    }

    public static void aMoviesAdder(Movie m) {
        Movie[] newArray = new Movie[aMovies.length + 1];
        System.arraycopy(aMovies, 0, newArray, 0, aMovies.length);
        newArray[newArray.length - 1] = m;
        aMovies = newArray;
    }

    public static void aMoviesMaker(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] a = fields2(line);
                aMoviesAdder(new Movie(a));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serializeArray(String serFileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(serFileName + ".ser"))) {
            outputStream.writeObject(aMovies);
        } catch (IOException e) {
            System.err.println("Error serializing movies: " + e.getMessage());
        }
    }

    public static void part2manifestMaker() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("part2_manifest.txt"))) {
            int count = 0;
            for (String genre : genresSeen) {
                writer.write(genre.toLowerCase() + ".csv");
                if (++count < genresSeen.length) {
                    writer.newLine(); // Add a new line if it's not the last genre
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void GenreAdder(String genre) {
        for (int i = 0; i < genresSeen.length; i++) {
            if (genresSeen[i].equals(genre)) {
                return;
            }
        }
        String[] newGenresSeen = Arrays.copyOf(genresSeen, genresSeen.length + 1);
        newGenresSeen[newGenresSeen.length - 1] = genre;
        genresSeen = newGenresSeen;
    }

    public static String[] fields2(String record_line) throws Exception {
        if (record_line.endsWith(",")) {
            record_line = record_line.substring(0, record_line.length() - 1);
        }
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

            // Checking if the number of fields is exactly 10
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
        String[] fields = null;
        int nbFields = 1;
        boolean missingQuote = false;
        @SuppressWarnings("unused")
        boolean hasQuotes = false;

        for (int i = 0; i < record_line.length(); i++) {
            if (record_line.charAt(i) == '"') {
                missingQuote = !missingQuote;
                hasQuotes = true; // Set hasQuotes to true if a quote is encountered
                continue;
            }

            if (record_line.charAt(i) == ',' && !missingQuote) {
                nbFields++;
                hasQuotes = false; // Reset hasQuotes when encountering a comma
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

        StringBuilder currentField = new StringBuilder();
        int fieldIndex = 0;
        boolean inQuotes = false;

        for (int i = 0; i < record_line.length(); i++) {
            char c = record_line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields[fieldIndex++] = currentField.toString();
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        fields[fieldIndex] = currentField.toString();

        // Encapsulate fields within quotes if they contain commas or double quotes
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].contains(",") || fields[i].contains("\"")) {
                fields[i] = "\"" + fields[i] + "\"";
            }
        }

        return fields;
    }

    public static void movieChecker(String[] aStrings) throws BadDurationException, BadGenreException, BadNameException,
            BadRatingException, BadScoreException, BadYearException, BadTitleException, Exception {

        String[] movieGenres = { "Musical", "Comedy", "Animation", "Adventure", "Drama", "Crime", "Biography", "Horror",
                "Action", "Documentary", "Fantasy", "Mystery",
                "Sci-fi", "Family", "Western", "Romance", "Thriller" };

        String[] movieRatings = { "PG", "Unrated", "G", "R", "PG-13", "NC-17" };

        // ------------------------------ Year
        try {
            if (aStrings[0].trim().length() == 0) {
                throw new BadYearException("Invalid Year. Missing the Year in the field");
            } else if (Integer.parseInt(aStrings[0]) != 2004) {
                throw new BadYearException("Invalid Year. Year must be from 1990 through 1999");
            }
        } catch (NumberFormatException e) {
            throw new BadYearException("Invalid year. Not a number.");
        }
        // --------------------------------- Name
        if (aStrings[1].trim().length() == 0) {
            throw new BadTitleException("Invalid Title. Missing the Title in the field");
        }

        // --------------------------------------- Length

        try {
            if (aStrings[2].trim().length() == 0) {

                throw new BadDurationException("Invalid Duration. Missing the Duration in the field");
            } else if (Integer.parseInt(aStrings[2]) < 30 || Integer.parseInt(aStrings[2]) > 300) {

                throw new BadDurationException(
                        "Invalid Duration. Duration should be between 30 minutes to 300 minutes");
            }
        } catch (NumberFormatException e) {
            throw new BadDurationException("Invalid Duration. Not a number.");
        }

        // ---------------------------------------- Genre
        if (aStrings[3].trim().length() == 0 || aStrings[3] == null) {
            throw new BadTitleException("Invalid Genre. Missing the Genre in the field");
        }

        boolean invalidGenre = true;
        for (String genre : movieGenres) {
            if (aStrings[3].equals(genre)) {

                invalidGenre = false;
                break;

            }
        }
        if (invalidGenre) {
            throw new BadGenreException("Invalid Genre. Genre is misspelled");
        }

        // --------------------------------------------- Age rating
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

        // ------------------------------------------------ Grade

        try {
            if (aStrings[5].trim().length() == 0) {
                throw new BadRatingException("Invalid Rating. Missing the Rating in the field");
            } else if (Double.parseDouble(aStrings[5]) > 10.0 || Double.parseDouble(aStrings[5]) < 0.0) {
                throw new BadRatingException("Invalid Rating. Rating should be between 0.0 and 10.00");
            }
        } catch (NumberFormatException e) {
            throw new BadRatingException("Invalid Rating. Rating should be a number");
        }

        // ------------------------------------------------ Names
        for (int i = 6; i < 10; i++) {
            if (aStrings[i].trim().length() == 0) {

                if (i == 6) {
                    throw new BadNameException("Invalid Director Name. No director name in teh field.");
                } else {
                    throw new BadNameException("Invalid Actor Name. No Actor name in the field.");
                }
            }

        }

        // ----------------------------------------------------

    }

    public static void doPartOne(String[] movie) throws IOException { // Filing the line
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(movie[3].toLowerCase()).append(".csv");
        GenreAdder(movie[3]);
        try {
            PrintWriter FileWriter = new PrintWriter(new FileOutputStream(stringBuilder.toString(), true));
            for (int i = 0; i < 10; i++) {
                FileWriter.print(movie[i]);
                if (i != 9) {
                    FileWriter.print(",");
                }
            }
            FileWriter.print("\r");
            FileWriter.close();
        } catch (IOException ex) {
            System.err.println("Error writing to crime.txt: " + ex.getMessage());
        }
    }

    public static void CSVReader(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] a = fields2(line);
                    movieChecker(a);
                    doPartOne(a);
                } catch (Exception e) {
                    try {
                        PrintWriter badFileWriter = new PrintWriter(new FileOutputStream("bad_movie_record.txt", true));
                        badFileWriter
                                .println(e + " --> in file: " + fileName + " --> on line: " + count + " --> " + line);
                        badFileWriter.close();
                    } catch (IOException ex) {
                        System.err.println("Error writing to bad.txt: " + ex.getMessage());
                    }
                }
                count++;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }
    }

    public static String do_part1(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                CSVReader(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        part2manifestMaker();

        return "part2_manifest.txt";
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        boolean breakLoop = false;

        while (!breakLoop) {
            System.out.print(
                    "--------------------------\r\n\tMain MENU\r\n--------------------------\r\n\r  s  Select a movie array to navigate\r\n  n  Navigate to "
                            + genresSeen[cursor] + " movies (" + allMovies[cursor].length
                            + " records)\r\n  x  Exit\r\n--------------------------\r\n\r\nEnter your choice: ");

            String choice = input.nextLine();
            switch (choice) {
                case "s":
                    System.out.println(
                            "\r\n--------------------------\r\n    Genre Sub-Menu\r\n--------------------------\r\n\r");
                    for (int i = 0; i < genresSeen.length; i++) {
                        System.out.println(
                                (i + 1) + "  " + genresSeen[i] + "\t\t" + "(" + allMovies[i].length
                                        + " movies)");
                    }
                    System.out.print("\r\n--------------------------\r\nEnter your choice: ");
                    int newCursorMain = input.nextInt();
                    @SuppressWarnings("unused")
                    String buffer = input.nextLine();
                    cursor = newCursorMain - 1;
                    localCursor = 0;
                    break;
                case "x":
                    breakLoop = true;
                    break;
                case "n":
                    System.out
                            .println("Navigating " + genresSeen[cursor] + " movies (" + allMovies[cursor].length + ")");
                    System.out.print("Enter your choice: ");
                    int choiceN = input.nextInt();
                    buffer = input.nextLine();
                    int decalage = Math.abs(choiceN) - 1;
                    switch (choiceN) {
                        case 0:
                            System.out.println("\r\nGoing back to main menu...\r\n");
                            break;
                        default:

                            if (choiceN < 0) {
                                int newCursor = localCursor - decalage;
                                System.out.println("\r\n" + allMovies[cursor][localCursor].getTitle());
                                if (newCursor == localCursor) {
                                    break;
                                }
                                if (newCursor < 0) {
                                    System.out.println("BOF has been reached.");
                                    newCursor = 0;
                                }
                                for (int i = localCursor - 1; i >= newCursor; i--) {
                                    System.out.println(allMovies[cursor][i].getTitle());
                                }
                                localCursor = newCursor;
                                break;
                            }
                            int newCursor = localCursor + decalage;
                            System.out.println("\r\n" + allMovies[cursor][localCursor].getTitle());
                            if (newCursor == localCursor) {
                                break;
                            }
                            if (newCursor > allMovies[cursor].length - 1) {
                                newCursor = allMovies[cursor].length - 1;
                                System.out.println("EOF has been reached.");
                            }
                            for (int i = localCursor + 1; i <= newCursor; i++) {
                                System.out.println(allMovies[cursor][i].getTitle());
                            }
                            localCursor = newCursor;
                            break;

                    }
                    break;
                default:
                    System.out.println("\r\nWrong input...\r\n");
                    break;
            }
        }
        System.out.println("Thank you for using our system. See you next time.");
        input.close();
    }

    public static void main(String[] args) {
        String part1_manifest = "part1_manifest.txt";
        String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        menu();
    }
}
