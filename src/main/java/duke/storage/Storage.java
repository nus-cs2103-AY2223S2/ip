package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;
    private final Parser parser = new Parser();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads file if exists.
     * Create directory (if necessary) and file if the file do not exist.
     *
     * @return List of tasks that are loaded from local storage.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            int idx = 0;
            while (s.hasNext()) {
                idx++;
                Task task = parser.processTask(s.nextLine(), idx);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ioe) {
                throw new DukeException("Fake duke.duke can't create the file.");
            }
            throw new DukeException(String.format("Fake duke.duke can't find the file. I will create the file (%s) :D", this.filePath));
        }
        return tasks;
    }

    /**
     * Saves tasks to local storage.
     *
     * @param tasks All the tasks that should be saved to local storage.
     * @throws DukeException
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).getRawTask());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
