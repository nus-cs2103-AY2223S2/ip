package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
            readScanner(sc);
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
            writeFromTasks(writer);
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
    public void readScanner(Scanner sc) {
        while (sc.hasNextLine()) {
            String Line = sc.nextLine();
            char Type = Line.charAt(0);
            int isDone = Integer.parseInt(Character.toString(Line.charAt(2)));
            if (Type == 'T') { // T 1 read book
                readToDo(Line, isDone);
            } else if (Type == 'D') { // D 1 read book | June 12 4pm
                readDeadline(Line, isDone);
            } else if (Type == 'E') { // E 1 read book | June 12 4pm | June 13 4pm
                readEvent(Line, isDone);
            }
        }
    }

    /**
     * Parses data from tasks and writes it into storage.
     *
     * @param writer The FileWriter writing into storage.
     */
    public void writeFromTasks(FileWriter writer) throws IOException {
        while (!tasks.isEmpty()) {
            Task task = tasks.remove(0);
            String isDone = "";
            String writeTask = "";

            if(task.isDone()) {
                isDone = "1";
            } else {
                isDone = "0";
            }
            if (task instanceof ToDo) {
                writeTask = "T " + isDone + " " + task.getDescription() + "\n";
            } else if (task instanceof Deadline) {
                writeTask += "D " + isDone + " " + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
            } else if (task instanceof Event) {
                writeTask += "E " + isDone + " " + task.getDescription() + " | " + ((Event) task).getFrom() + " | "
                        + ((Event) task).getTo() + "\n";
            }
            writer.write(writeTask);
        }
    }

    /**
     * Reads a ToDo task from storage.
     *
     * @param Line The next line in the data.
     * @param isDone Status of the task being read.
     */
    public void readToDo(String Line, int isDone) {
        String description = Line.substring(4);
        Task task = new ToDo(description);
        if (isDone == 1) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Reads a Deadline task from storage.
     *
     * @param Line The next line in the data.
     * @param isDone Status of the task being read.
     */
    public void readDeadline(String Line, int isDone) {
        int dividerIndex = Line.indexOf('|');
        String description = Line.substring(4, dividerIndex - 1);
        String by = Line.substring(dividerIndex + 2);
        try {
            Task task = new Deadline(description, by);
            if (isDone == 1) {
                task.markAsDone();
            }
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
     */
    public void readEvent(String Line, int isDone) {
        int firstDividerIndex = Line.indexOf('|');
        int lastDividerIndex = Line.lastIndexOf('|');
        String description = Line.substring(4, firstDividerIndex - 1);
        String from = Line.substring(firstDividerIndex + 2, lastDividerIndex - 1);
        String to = Line.substring(lastDividerIndex + 2);
        try {
            Task task = new Event(description, from, to);
            if (isDone == 1) {
                task.markAsDone();
            }
            tasks.add(task);
        } catch (DukeException e) {
            Ui.errorMessage(e);
        }
    }
}
