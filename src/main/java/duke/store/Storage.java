package duke.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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
    private static final String COMPLETE_MSG = "    ==> COMPLETED!";
    private static final String INCOMPLETE_MSG = "    ==> INCOMPLETE!";
    private static String savePath;
    private static String loadPath;

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
        loadPath = pathLoad;
    }

    /**
     * Checks the existence of file path for saving, else creates a directory for it.
     * @throws IOException
     */
    public static void makeDirectory() throws IOException {
        String root = Paths.get(".").toRealPath().normalize().toString();
        String dir = java.nio.file.Paths.get(root, "src", "data").toString();
        File f = new File(dir);
        File src = new File(java.nio.file.Paths.get(root, "src").toString());
        if (src.exists()) {
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
        makeDirectory();
        File f = new File(savePath);
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        // Header of the save file
        LocalDateTime now = LocalDateTime.now();
        fw.write("Last Saved at " + now);
        fw.write(System.lineSeparator()); // new line
        fw.write(System.lineSeparator()); // new line
        // Body of the save file
        try {
            if (tasks.size() == 0) {
                fw.write("");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTaskAtIndex(i);
                    fw.write(Storage.readerFriendly(task));
                    fw.write(System.lineSeparator()); // new line
                }
                fw.close();
            }
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
        String status = task.getStatus() ? COMPLETE_MSG : INCOMPLETE_MSG;
        if (task instanceof Todo) {
            Todo t = (Todo) task;
            return "Todo: " + t.getDescription() + " " + status;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "Deadline: " + d.getDescription() + " by " + d.getBy() + " " + status;
        } else { // instance of duke.task.Event
            Event e = (Event) task;
            return "Event: " + e.getDescription() + " from " + e.getFrom() + " to " + e.getTo() + " " + status;
        }
    }
}
