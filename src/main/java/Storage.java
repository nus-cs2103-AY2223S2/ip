import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static String savePath;
    private static String loadPath;
    public Storage() {
        // Get the directory of from our root.
        String root;
        try {
            root = Paths.get(".").toRealPath().normalize().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(root);
        // Make sure that it is independent of the OS.
        savePath = java.nio.file.Paths.get(root,"src", "data", "duke.txt").toString();
        // System.out.println(PATH);
    }

    public Storage(String pathSave, String pathLoad) {
        savePath = pathSave;
        loadPath = pathLoad;
    }

    public static void autoSave(TaskList tasks) throws IOException {
        File f = new File(savePath);
        FileWriter fw = new FileWriter(f.getAbsolutePath());
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

    private static String readerFriendly(Task task) {
        String status = task.getStatus() ? "๑(◕‿◕)๑ COMPLETED! ๑(◕‿◕)๑" : "(｡-_-｡ ) INCOMPLETE ( ｡-_-｡)";
        if (task instanceof Todo) {
            Todo t = (Todo) task;
            return "duke.task.Todo: " + t.getDescription() + " " + status;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "duke.task.Deadline: " + d.getDescription() + " by " + d.getBy() + " " + status;
        } else { // instance of duke.task.Event
            Event e = (Event) task;
            return "duke.task.Event: " + e.getDescription() + " from " + e.getFrom() + " to " + e.getTo() + " " + status;
        }
    }
}
