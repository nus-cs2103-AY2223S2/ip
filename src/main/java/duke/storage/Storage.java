package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads file if exists.
     * Create directory (if necessary) and file if the file do not exist.
     *
     * @return List of tasks that are loaded from local storage.
     * @throws DukeException Throws exception if file cannot be found locally or created.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        ArrayList<Task> tasks;
        try {
            Scanner s = new Scanner(f);
            tasks = readTasks(s);
        } catch (FileNotFoundException fnfe) {
            createFileAndDir(f);
            throw new DukeException(String.format("Fake Duke can't find the file. I have created the file (%s) :D",
                    filePath));
        }
        return tasks;
    }

    /**
     * Saves tasks to local storage.
     *
     * @param tasks All the tasks that should be saved to local storage.
     * @throws DukeException Throws exception if task cannot be obtained to save to file.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).getRawTask());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Task> readTasks(Scanner s) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            Task task = Parser.processTask(s.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    private void createFileAndDir(File f) throws DukeException {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException ioe) {
            throw new DukeException("Fake Duke can't create the file.");
        }
    }
}
