package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.FileInputParser;
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
     * Creates directory (if necessary) and file if the file do not exist.
     *
     * @return List of tasks that are loaded from local storage.
     * @throws DukeException if file cannot be found locally or created.
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
     * @param tasks that should be saved to local storage.
     * @throws DukeException if task cannot be obtained.
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

    /**
     * Reads tasks from scanner.
     *
     * @param scanner used to read tasks.
     * @return Array of tasks read from scanner.
     * @throws DukeException if there is error with parsing.
     */
    private ArrayList<Task> readTasks(Scanner scanner) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            Task task = FileInputParser.parse(scanner.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Creates file and/or directory if they do not exist.
     *
     * @param file object used to create directory and/or file.
     * @throws DukeException if file cannot be created.
     */
    private void createFileAndDir(File file) throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ioe) {
            throw new DukeException("Fake Duke can't create the file.");
        }
    }
}
