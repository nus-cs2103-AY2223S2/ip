package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.storage.serializer.TaskSerializer;
import duke.task.Task;

public class Storage {
    private static final String DATA_PATH = "data/duke.txt";

    public static List<Task> load() throws Exception {
        File f = null;
        Scanner sc = null;
        List<Task> tasks = new ArrayList<>();
        try {
            f = new File(DATA_PATH);
            sc = new Scanner(f);
            while (sc.hasNextLine()) {
                TaskSerializer ts = new TaskSerializer(sc.nextLine());
                tasks.add(ts.createTask());
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("No data file found");
        } finally {
            sc.close();
        }
        return tasks;
    }

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
