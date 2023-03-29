package duke.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 *      File name: duke.store.Storage.java
 *      @author: Jerome Neo
 *      Description: duke.store.Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static String savePath;

    /**
     * Constructor for the storage.
     */
    public Storage() {
        // Get the directory from our root.
        String root;
        try {
            root = Paths.get(".").toRealPath().normalize().toString();
            System.out.println(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Make sure that it is independent of the OS.
        savePath = java.nio.file.Paths.get(root, "src", "data", "duke.txt").toString();
        System.out.println(savePath);
    }

    /**
     * Storage overloaded constructor.
     * @param pathSave
     * @param pathLoad
     */
    public Storage(String pathSave, String pathLoad) {
        savePath = pathSave;
    }

    /**
     * Checks the existence of file path for saving, else creates a directory for it.
     * @throws IOException
     */
    public static void makeDirectory() throws IOException {
        String root = Paths.get(".").toRealPath().normalize().toString();
        String dir = java.nio.file.Paths.get(root, "src", "data").toString();
        File f = new File(dir);
        if (f.exists()) {
            System.out.println("Recording changes as usual...");
        } else {
            System.out.println("Missing file directory, creating one now.");
            f.mkdirs();
        }
    }

    /**
     * Saves all the tasks in the duke.task.TaskList objects by writing it into the savePath directory.
     *
     * @throws IOException if the directory is invalid.
     */
    public static void autoSave(TaskList tasks) throws IOException {
        File f = new File(savePath);
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        try {
            if (tasks.size() != 0) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTaskAtIndex(i);
                    fw.write(Storage.readerFriendly(task));
                    fw.write(System.lineSeparator()); // new line
                }
            } else {
                fw.write("");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file, check path: " + savePath);
        }
    }

    /**
     * Returns a string object of how the task object will be saved in the save file.
     *
     * @param task is the task to be converted into a String to be saved.
     * @return string that is to be saved.
     */
    private static String readerFriendly(Task task) {
        int status = task.getStatus() ? 1 : 0;
        if (task instanceof Todo) {
            Todo t = (Todo) task;
            return TODO_TYPE + " | " + status + " | " + t.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return DEADLINE_TYPE + " | " + status + " | " + d.getDescription() + " | by | "
                    + d.getByDateTime();
        } else { // instance of duke.task.Event
            Event e = (Event) task;
            return EVENT_TYPE + " | " + status + " | " + e.getDescription() + " | from | "
                    + e.getFromDateTime() + " | to | " + e.getToDateTime();
        }
    }

    /**
     * Loads from last save file.
     * @return
     */
    public static void readSave(TaskList tasks) throws IOException {
        makeDirectory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            File f = new File(savePath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String curr = scanner.nextLine();
                String[] tokens = curr.split("\\|");
                String taskType = tokens[0].trim();
                boolean isComplete = tokens[1].trim().equals("1");
                String description = tokens[2].trim();
                switch (taskType) {
                case TODO_TYPE:
                    Todo t = new Todo(description);
                    t.setStatus(isComplete);
                    tasks.addTask(t);
                    break;
                case DEADLINE_TYPE:
                    LocalDateTime by = LocalDateTime
                            .parse(tokens[4].trim());
                    Deadline d = new Deadline(description, by.format(formatter));
                    d.setStatus(isComplete);
                    tasks.addTask(d);
                    break;
                case EVENT_TYPE:
                    LocalDateTime from = LocalDateTime
                            .parse(tokens[4].trim());
                    LocalDateTime to = LocalDateTime
                            .parse(tokens[6].trim());
                    Event e = new Event(description, from.format(formatter), to.format(formatter));
                    e.setStatus(isComplete);
                    tasks.addTask(e);
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType + " or your file may have been corrupted.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File directory may be missing: " + savePath);
        }
    }
}
