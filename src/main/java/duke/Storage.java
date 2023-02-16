package duke;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a store that stores a file containing the list of tasks.
 */
public class Storage {
    private File file;

    /**
     * Creates a new Storage object.
     * @param filepath The path of the file to be created to store the list.
     */
    Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Stores the current TaskList into a file
     * @param list The TaskList object containing an ArrayList of Tasks
     * @throws DukeException Throws a DukeException
     */
    public void save(TaskList list) throws DukeException {
        ArrayList<String> temp = new ArrayList<>();
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 1; i <= list.numberOfTasks(); i++) {
                temp.add(list.getTask(i).sendOutputToFile());
            }
            fw.write(String.join("\n", temp));
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Error when adding file");
        }
    }

    /**
     * Loads Tasks from the saved text file to return the ArrayList of Tasks
     * @return The ArrayList of tasks from the peviously saved text file
     * @throws DukeException Throws a DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] curr = scanner.nextLine().split(" \\| ");
                String taskType = curr[0];
                Task task;
                switch (taskType) {
                case "E":
                    LocalDateTime from = LocalDateTime.parse(curr[3], DateTimeFormatter.ofPattern("MMM dd yyyy H:mm"));
                    LocalDateTime to = LocalDateTime.parse(curr[4], DateTimeFormatter.ofPattern("MMM dd yyyy H:mm"));
                    task = new Event(curr[2], from, to);
                    handleTask(task, curr);
                    tasks.add(task);
                    break;
                case "D":
                    LocalDate temp = LocalDate.parse(curr[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Deadline(curr[2], temp);
                    handleTask(task, curr);
                    tasks.add(task);
                    break;
                case "T":
                    task = new Todo(curr[2]);
                    handleTask(task, curr);
                    tasks.add(task);
                    break;
                default:
                    throw new DukeException("Error: Wrong task encountered");
                }
            }
            scanner.close();
        } catch (Exception ex) {
            throw new DukeException("Exception has occurred");
        }
        return tasks;
    }

    void handleTask(Task t, String[] parsedString) {
        if (Integer.parseInt(parsedString[1]) == 1) {
            t.markAsDone();
        } else {
            t.markAsNotDone();
        }
    }
}
