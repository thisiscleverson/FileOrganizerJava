package org.fileOrganizer;

import java.io.IOException;
import java.nio.file.*;

public class Organizer extends FileCategorize {

    private final Path directory;
    private Path file;


    /**
     * Constructs an Organizer with the specified directory as the source.
     *
     * @param directory the path to the source directory as a String.
     * @throws IllegalArgumentException if the directory is null or empty.
     *
     * <p>Example usage:</p>
     * <pre>
     * {@code
     * // Create an Organizer instance for the specified directory
     * Organizer organizer = new Organizer("C:/Users/USER/Downloads/");
     *
     * // Call the fileCheck method to start organizing files
     * organizer.fileCheck();
     * }
     * </pre>
     */
    public Organizer(String directory){
        this.directory = Paths.get(directory);
    }


    private String filesObserver() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.directory)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    this.file = file;
                    return file.getFileName().toString().toLowerCase();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void moveFile(String fileName) {
        try {
            if (this.file != null) {
                if (this.file.getFileName().toString().endsWith(".tmp") || this.file.getFileName().toString().endsWith(".crdownload")) {
                    System.out.println("File ignored: " + file.getFileName() + " due to its extension");
                    return; // If the extension is "tmp", return without doing anything
                }

                String destinationDirectory = categorizeFile(fileName);
                Path destination = Paths.get(destinationDirectory).resolve(this.file.getFileName());

                Files.createDirectories(destination);
                Files.move(this.file, destination, StandardCopyOption.REPLACE_EXISTING);
                this.file = null;

                System.out.println("File moved: " + fileName + " to folder: " + destination);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loop(long interval){
        if (interval <= 0) {
            throw new IllegalArgumentException("Interval must be greater than zero.");
        }

        while (true) {
            String fileName = this.filesObserver();
            System.out.println(fileName);

            moveFile(fileName);

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                System.err.println("Error while waiting for the next interval: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restore the interrupted status
                break;
            }
        }
    }
}
