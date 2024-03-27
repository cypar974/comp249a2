import java.io.File;

public class FileDeletion {
    public static void main(String[] args) {
        // Array of file names to delete
        String[] filesToDelete = { "musical.csv", "comedy.csv", "animation.csv", "adventure.csv", "drama.csv",
                "crime.csv", "biography.csv", "horror.csv", "action.csv", "documentary.csv", "fantasy.csv",
                "mystery.csv", "sci-fi.csv", "family.csv", "western.csv", "romance.csv", "thriller.csv",
                "bad_movie_record.txt", "part2_manifest.txt", "musical.ser", "comedy.ser", "animation.ser",
                "adventure.ser", "drama.ser", "crime.ser", "biography.ser", "horror.ser", "action.ser",
                "documentary.ser", "fantasy.ser", "mystery.ser", "sci-fi.ser", "family.ser", "western.ser",
                "romance.ser", "thriller.ser", "part3_manifest.txt" };

        // Iterate through each file name and delete the corresponding file
        for (String fileName : filesToDelete) {
            deleteFile(fileName);
        }

        System.out.println("Files deleted successfully.");
    }

    // Method to delete a file
    private static void deleteFile(String fileName) {
        File file = new File(fileName);

        // Check if the file exists and is a file (not a directory)
        if (file.exists() && file.isFile()) {
            // Attempt to delete the file
            if (file.delete()) {
                System.out.println("Deleted: " + fileName);
            } else {
                System.out.println("Failed to delete: " + fileName);
            }
        }
    }
}
