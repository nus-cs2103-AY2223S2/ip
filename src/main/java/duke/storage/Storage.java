package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.storage.serializer.TaskSerializer;
import duke.task.Task;

/**
 * The duke component that is responsible for saving and loading tasks from local storage.
 */
public class Storage {
    private static final String DATA_PATH = System.getProperty("user.dir") + "/data/duke.txt";

    /**
     * Loads and returns a list of tasks stored locally.
     *
     * @return List of tasks that was stored locally.
     * @throws DukeException
     */
    public static List<Task> load() throws DukeException {
        Scanner sc = null;
        File file = new File(DATA_PATH);
        List<Task> tasks = new ArrayList<>();
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                TaskSerializer ts = new TaskSerializer(sc.nextLine());
                tasks.add(ts.createTask());
            }
        } catch (Exception e) {
            throw new DukeException("No data file found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return tasks;
    }

    /**
     * Saves a list of tasks locally.
     *
     * @param tasks The list of tasks to be saved
     * @throws DukeException
     */
    public static void save(List<Task> tasks) throws DukeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(DATA_PATH);
            StringBuilder sb = new StringBuilder();
            for (Task task: tasks) {
                sb.append(task.serialize());
                sb.append('\n');
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file");
        }
    }
}
