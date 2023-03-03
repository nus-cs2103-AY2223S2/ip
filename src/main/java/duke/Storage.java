package duke;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages all storage reading and writing functions. Parses TaskList from storage into Tasks.
 */
public class Storage {
    private String filePath;
    private TaskList tasks;

    /**
     * Constructor to create a Storage object to store the TaskList.
     *
     * @param filePath The file path to where the list of tasks is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new TaskList();
        try {
            File data = new File(filePath);
            data.createNewFile();
        } catch (IOException e) {
            Ui.errorMessage(e);
        }
    }

    /**
     * Reads data from storage into the current Task List.
     *
     * @return The Task List read from storage.
     */
    public TaskList readData() {
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            readFromStorage(sc);
        } catch (IOException e) {
            Ui.errorMessage(e);
        }
        return tasks;
    }

    /**
     * Writes data from the current Task List into storage.
     */
    public void writeData() {
        try {
            File data = new File(filePath);
            FileWriter writer = new FileWriter(data);
            writeToStorage(writer);
            writer.close();
        } catch (IOException e) {
            Ui.errorMessage(e);
        }

    }

    /**
     * Parses data from storage.
     *
     * @param sc The Scanner reading from storage.
     */
    public void readFromStorage(Scanner sc) {
        while (sc.hasNextLine()) {
            String Line = sc.nextLine();
            char Type = Line.charAt(0);
            char isDone = Line.charAt(2);
            char priority = Line.charAt(4);
            if (Type == 'T') { // T 1 read book
                readToDo(Line, isDone, priority);
            } else if (Type == 'D') { // D 1 read book | June 12 4pm
                readDeadline(Line, isDone, priority);
            } else if (Type == 'E') { // E 1 read book | June 12 4pm | June 13 4pm
                readEvent(Line, isDone, priority);
            }
        }
    }

    /**
     * Parses data from tasks and writes it into storage.
     *
     * @param writer The FileWriter writing into storage.
     */
    public void writeToStorage(FileWriter writer) throws IOException {
        while (!tasks.isEmpty()) {
            Task task = tasks.remove(0);
            String isDone = "";
            String writeTask = "";

            if (task.isDone()) {
                isDone = "1";
            } else {
                isDone = "0";
            }

            if (task instanceof ToDo) {
                writeTask = "T " + isDone + " " + task.getShortPriority() + " " + task.getDescription() + "\n";
            } else if (task instanceof Deadline) {
                writeTask += "D " + isDone + " " + task.getShortPriority() + " " +
                        task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
            } else if (task instanceof Event) {
                writeTask += "E " + isDone + " " + task.getShortPriority() + " " +
                        task.getDescription() + " | " + ((Event) task).getFrom() + " | " +
                        ((Event) task).getTo() + "\n";
            }
            writer.write(writeTask);
        }
    }

    /**
     * Reads a ToDo task from storage.
     *
     * @param Line The next line in the data.
     * @param isDone Status of the task being read.
     * @param priority Priority of the task.
     */
    public void readToDo(String Line, char isDone, char priority) {
        String description = Line.substring(6);
        Task task = new ToDo(description);
        if (isDone == '1') {
            task.markAsDone();
        }
        task.setPriority(Priority.priorityValue(priority));
        tasks.add(task);
    }

    /**
     * Reads a Deadline task from storage.
     *
     * @param Line The next line in the data.
     * @param isDone Status of the task being read.
     * @param priority Priority of the task.
     */
    public void readDeadline(String Line, char isDone, char priority) {
        int dividerIndex = Line.indexOf('|');
        String description = Line.substring(6, dividerIndex - 1);
        String by = Line.substring(dividerIndex + 2);
        try {
            Task task = new Deadline(description, by);
            if (isDone == '1') {
                task.markAsDone();
            }
            task.setPriority(Priority.priorityValue(priority));
            tasks.add(task);
        } catch (DukeException e) {
            Ui.errorMessage(e);
        }
    }

    /**
     * Reads a Event task from storage.
     *
     * @param Line The next line in the data.
     * @param isDone Status of the task being read.
     * @param priority Priority of the task.
     */
    public void readEvent(String Line, char isDone, char priority) {
        int firstDividerIndex = Line.indexOf('|');
        int lastDividerIndex = Line.lastIndexOf('|');
        String description = Line.substring(6, firstDividerIndex - 1);
        String from = Line.substring(firstDividerIndex + 2, lastDividerIndex - 1);
        String to = Line.substring(lastDividerIndex + 2);
        try {
            Task task = new Event(description, from, to);
            if (isDone == '1') {
                task.markAsDone();
            }
            task.setPriority(Priority.priorityValue(priority));
            tasks.add(task);
        } catch (DukeException e) {
            Ui.errorMessage(e);
        }
    }
}
