import java.io.File;

public class FileDeletion {
    public static void main(String[] args) {
        // Array of file names to delete
        String[] filesToDelete = { "action.csv", "adventure.csv", "biography.csv", "comedy.csv",
                "crime.csv", "drama.csv", "fantasy.csv", "bad_movie_record.txt" };

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
        } else {
            System.out.println("File not found: " + fileName);
        }
    }
}
