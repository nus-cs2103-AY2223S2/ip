package reborn.storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import reborn.data.exception.RebornException;
import reborn.data.task.Deadline;
import reborn.data.task.Event;
import reborn.data.task.Task;
import reborn.data.task.Todo;

/**
 * Represents storage of saved tasks.
 */
public class Storage {

    private String filePath;
    private File f;

    /**
     * Constructor for Storage.
     *
     * @param filePath Filepath to create file for loading and saving.
     * @throws RebornException If file creation fails.
     */
    public Storage(String filePath) throws RebornException {
        this.filePath = filePath;
        f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RebornException(e.getMessage());
            }
        }
    }

    /**
     * Converts string input into appropriate Task.
     *
     * @param input String input that represents a saved Task.
     * @return Task processed using given String.
     */
    public Task processTask(String[] input) {
        String type = input[0];
        assert type == "T" || type == "D" || type == "E" : "Type of task should be T, D or E";
        String mark = input[1];
        assert mark == "1" || mark == "0" : "Task mark status should be 1 or 0";
        Task x = null;
        if (type.equals("T")) {
            x = new Todo(input[2]);
        } else if (type.equals("D")) {
            x = new Deadline(input[2], input[3]);
        } else if (type.equals("E")) {
            x = new Event(input[2], input[3], input[4]);
        }
        if (mark.equals("1")) {
            x.mark();
        }
        return x;
    }

    /**
     * Loads in stored tasks from file.
     *
     * @return ArrayList of stored tasks.
     * @throws RebornException If load fails.
     */
    public ArrayList<Task> load() throws RebornException {
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                // convert text to duke.data.task.Task
                String[] input = s.nextLine().split(" \\| ");
                Task x = processTask(input);
                tasks.add(x);
            }
            return tasks;
        } catch (Exception e) {
            throw new RebornException(e.getMessage());
        }

    }

    /**
     * Saves current tasks to file.
     *
     * @param taskListString String containing current tasks.
     * @throws RebornException If file writing fails
     */
    public void save(String taskListString) throws RebornException {
        try {
            FileWriter fw = new FileWriter("src/main/java/reborn/data/reborn.txt");
            fw.write(taskListString);
            fw.close();
        } catch (Exception e) {
            throw new RebornException(e.getMessage());
        }
    }


}
