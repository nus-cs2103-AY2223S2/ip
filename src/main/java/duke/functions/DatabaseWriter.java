package duke.functions;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.tasks.Task;

/**
 * The main class for manipulating the database records in duke.txt.
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class DatabaseWriter {
    private static final String folderPath = "data";
    private static final String filePath = "data/duke.txt";
    private static Path path = Paths.get(filePath);

    /**
     * Default constructor for a DatabaseWriter object.
     *
     * @param path File path of database.
     */
    public DatabaseWriter(Path path) {
        this.path = path;
    }

    /**
     * Inserts task to database.
     *
     * @param t Task object to be added.
     */
    public void addToDb(Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String formattedTask = t.toStringDb();
            fw.write(formattedTask + '\n');
            fw.close();
        } catch (IOException exception) {
            System.out.println("Error while reading database.");
        }
    }

    /**
     * Removes task from database.
     *
     * @param index Index of Task object to be removed (1-indexed).
     */
    public void removeFromDb(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.set(index, "");
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    /**
     * Marks a task as done in the database.
     *
     * @param index Index of Task object to be marked done (1-indexed).
     */
    public void setDone(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(index);
            String newLine = replaceChar(line, 2, 'X');
            lines.set(index, newLine);
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    /**
     * Marks a task as undone in the database.
     *
     * @param index Index of Task object to be marked undone (1-indexed).
     */
    public void setUndone(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(index);
            String newLine = replaceChar(line, 2, ' ');
            lines.set(index, newLine);
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    /**
     * Helper method to edit the done status of task records in the database.
     *
     * @param s The record string in the database.
     * @param index Index of character to be replaced.
     * @param newValue The new char value.
     * @return The new string with updated done status.
     */
    public static String replaceChar(String s, int index, char newValue) {
        StringBuilder string = new StringBuilder(s);
        string.setCharAt(index, newValue);
        return string.toString();
    }

}
