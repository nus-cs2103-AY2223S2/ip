package duke;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * duke.Storage class encapsulates the whole storing, reading process of chatbot.
 */
public class Storage {
    private static final Path STORING_FILE_PATH = Paths.get(".", ".", ".", "data", "duke.txt");
    private static final Path TEMPORARY_STORING_FILE_PATH =
            Paths.get(".", ".", ".", "data", "temp_duke.txt");

    /**
     * Initializes a new duke.Storage object and try to read the storing file in hard disk, if there is not
     * a file existed then create a new one.
     */
    public Storage() {
        try {
            Files.createFile(STORING_FILE_PATH);
        } catch (FileAlreadyExistsException e) {
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save the tasks' information to the hard disk file
     * @param tasks
     */
    public static void writeFile(ArrayList<Task> tasks) {
        Scanner reader = null;
        String text = "";
        try {
            Files.deleteIfExists(TEMPORARY_STORING_FILE_PATH);
            Files.createFile(TEMPORARY_STORING_FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                text += tasks.get(i) + "\n";
            }
            Files.writeString(TEMPORARY_STORING_FILE_PATH, text);
            Files.deleteIfExists(STORING_FILE_PATH);
            Files.move(TEMPORARY_STORING_FILE_PATH, STORING_FILE_PATH);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Return an ArrayList of duke.Task encapsulting the tasks' information stored in hard disk file.
     *
     * @return Tasks list stored in hard disk
     */
    public ArrayList<Task> readFile() {
        Scanner reader = null;
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            reader = new Scanner(STORING_FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (reader != null) {
            String pointer;
            String description;
            Task newTask;
            while (reader.hasNext()) {
                pointer = reader.nextLine();

                //checks which kinds of task is stored in this current line
                if (pointer.startsWith("[T]")) {
                    description = pointer.substring(7);
                    newTask = new ToDo(description);
                } else if (pointer.startsWith("[E]")) {
                    description = pointer.substring(7, pointer.indexOf("(from: ") - 1);
                    LocalDate start = LocalDate.parse(
                            pointer.substring(pointer.indexOf("(from: ") + 7, pointer.indexOf(" to:")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    LocalDate end = LocalDate.parse(
                            pointer.substring(pointer.indexOf("to: ") + 4, pointer.lastIndexOf(")")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    newTask = new Event(description, start, end);
                } else {
                    description = pointer.substring(7, pointer.indexOf("(by: ") - 1);
                    LocalDate by = LocalDate.parse(
                            pointer.substring(pointer.indexOf("(by: ") + 5, pointer.lastIndexOf(")")),
                            DateTimeFormatter.ofPattern("MMM d yyyy"));
                    newTask = new Deadline(description, by);
                }

                tasks.add(newTask);
                //checks whether this tasks is marked as done or not
                if (pointer.substring(3).startsWith("[X]")) {
                    newTask.mark();
                }
            }
            reader.close();
        }
        return tasks;
    }
}
