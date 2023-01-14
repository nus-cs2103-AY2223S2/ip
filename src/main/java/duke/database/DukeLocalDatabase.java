package duke.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeParser;
import duke.exception.DukeException;
import duke.task.Task;

class DukeLocalDatabase {

    private static final String DATA_FILE_DIR = "data";
    private static final String DATA_FILE_PATH = "duke.txt";
    private List<Task> tasks;

    public DukeLocalDatabase() {
        tasks = new ArrayList<Task>();
        open();
    }

    public List<Task> getAllTask() {
        return tasks;
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task updateTask(int taskid, Task task) {
        return tasks.set(taskid, task);
    }

    public Task removeTask(int taskId) {
        return tasks.remove(taskId);
    }

    /**
     * Load tasks from local file.
     */
    void open() {
        try {
            File dataFile = new File(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                DukeParser dp = DukeParser.parseCsv(sc.nextLine());
                tasks.add(DukeParser.toTask(dp));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // file no found, start with empty list.
            return;
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the on memory task list to local file.
     */
    public void close() {
        try {
            File dataDir = new File(DATA_FILE_DIR);
            if (!dataDir.exists())
                dataDir.mkdirs();

            File dataFile = new File(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            if (!dataFile.exists())
                dataFile.createNewFile();

            FileWriter fw = new FileWriter(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toCsv() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int count() {
        return tasks.size();
    }
}
