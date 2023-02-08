package duke.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.constant.Message;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;

/**
 * A DAO layer for file storage
 */
class DukeLocalDatabase {

    private static final String DATA_FILE_DIR = "data";
    private static final String DATA_FILE_PATH = "duke.txt";
    private List<Task> tasks;

    /**
     * Default constructor
     */
    public DukeLocalDatabase() {
        this(false);
    }

    /**
     * Default constructor
     */
    public DukeLocalDatabase(boolean isTestMode) {
        tasks = new ArrayList<Task>();
        if (!isTestMode) {
            open();
        }
    }

    /**
     * Retrives all entries from list
     *
     * @return {@link Task} List
     */
    public List<Task> getAllTask() {
        return tasks;
    }

    /**
     * Retrives an entry from list by id
     *
     * @param taskId int
     * @return {@link Task} object
     */
    public Task getTask(int taskId) {
        assert taskId > 0;
        return tasks.get(taskId - 1);
    }

    /**
     * Add an entry to list
     *
     * @param task {@link Task} object
     * @return {@link Task} object
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Update an entry from list by id
     *
     * @param taskId int
     * @return {@link Task} object
     */
    public Task updateTask(int taskId, Task task) {
        assert taskId > 0;
        return tasks.set(taskId - 1, task);
    }

    /**
     * Remove an entry from list by id
     *
     * @param taskId
     * @return {@link Task} object
     */
    public List<Task> removeTask(int... taskIds) throws IndexOutOfBoundsException {
        List<Task> res = new ArrayList<Task>();
        for (int i = taskIds.length - 1; i >= 0; i--) {
            try {
                res.add(tasks.remove(taskIds[i] - 1));
            } catch (IndexOutOfBoundsException e) {
                System.err.println(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
            }
        }
        return res;
    }

    /**
     * Load tasks from local csv file.
     */
    void open() {
        try {
            File dataFile = new File(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                try {
                    AddCommand command = Parser.parseCsv(sc.nextLine());
                    addTask(command.getTask());
                } catch (DateTimeParseException | DukeException e) {
                    System.err.println("Corrupted data file: " + e.getMessage());
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // file no found, start with empty list.
            return;
        }
    }

    /**
     * Save the on memory task list to local csv file.
     */
    public void close() {
        try {
            File dataDir = new File(DATA_FILE_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            File dataFile = new File(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            FileWriter fw = new FileWriter(DATA_FILE_DIR + "/" + DATA_FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toCsv() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The number of entries in list.
     *
     * @return int
     */
    public int count() {
        return tasks.size();
    }
}
