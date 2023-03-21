package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * Provides functionality to store and load tasks from "/data/duke.txt"
 */
public class Storage {
    private static final Path DATA_PATH = Paths.get("data");
    private static final Path TASK_LIST_PATH = Paths.get("data", "duke.txt");

    /**
     * Returns the list of tasks from the default data file if it exists or an empty list otherwise.
     *
     * @return An ArrayList containing the list of tasks loaded from the default data file.
     */
    public ArrayList<Task> loadTaskList() {
        try {
            if (!Files.exists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH);
            }
            assert Files.exists(DATA_PATH) : "Data directory does not exist";

            if (!Files.exists(TASK_LIST_PATH)) {
                Files.createFile(TASK_LIST_PATH);
            }
            assert Files.exists(TASK_LIST_PATH) : "Task list file does not exist";

            return parseTaskList();

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private ArrayList<Task> parseTaskList() throws IOException {
        try {
            List<String> lines = Files.readAllLines(TASK_LIST_PATH);
            ArrayList<Task> taskList = new ArrayList<>();

            for (String line : lines) {
                Task task = parseTask(line);
                taskList.add(task);
            }
            return taskList;

        } catch (DukeStorageException e) {
            clearDataFile();
            return new ArrayList<>();
        }
    }

    private Task parseTask(String line) throws DukeStorageException {
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Malformed task string: " + line;

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            assert parts.length == 4 : "Malformed deadline task string: " + line;
            LocalDateTime dueDate = LocalDateTime.parse(parts[3]);
            task = new Deadline(description, dueDate);
            break;
        case "E":
            assert parts.length == 5 : "Malformed event task string: " + line;
            LocalDateTime fromDate = LocalDateTime.parse(parts[3]);
            LocalDateTime toDate = LocalDateTime.parse(parts[4]);
            task = new Event(description, fromDate, toDate);
            break;
        default:
            throw new DukeStorageException("Oops, something is wrong with the data file! :(\n");
        }

        if (isDone) {
            task.setDone();
        }
        return task;
    }

    private void clearDataFile() throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(TASK_LIST_PATH);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    /**
     * Stores the given taskList in the default data file.
     *
     * @param taskList The ArrayList containing the list of tasks to be stored.
     * @throws DukeStorageException If the file specified by {@code TASK_LIST_PATH} does not exist or
     *     if the data could not be stored successfully.
     */
    public void storeTaskList(ArrayList<Task> taskList) throws DukeStorageException {
        try {
            if (!Files.exists(TASK_LIST_PATH)) {
                throw new DukeStorageException("File error: The data file does not exist!");
            }

            BufferedWriter bufferedWriter = Files.newBufferedWriter(TASK_LIST_PATH);
            for (Task task : taskList) {
                String line = task.getTaskState();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            throw new DukeStorageException("File error: could not store tasks in data file!");
        }
    }
}
